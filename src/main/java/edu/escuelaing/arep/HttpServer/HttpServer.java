package edu.escuelaing.arep.HttpServer;

import java.net.*;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.io.*;
import org.apache.commons.io.FilenameUtils;

public class HttpServer {
	
	private int port;
    private Map<String,Processors> routes = new HashMap();
    private ServerSocket serverSocket;
    private Socket clientSocket;
    private PrintWriter writer;
	
    public void registerProcessor(String path, Processors proc) {
        routes.put(path,proc);
    }
  
    public void startServer(int port) throws IOException {
    	this.port = port;
	   //ServerSocket serverSocket = null;
    	serverSocket = null;
	   try { 
	      serverSocket = new ServerSocket(this.port);
	   } catch (IOException e) {
	      System.err.println("Could not listen on port: " + this.port);
	      System.exit(1);
	   }
	   boolean runing = true;
	   while(runing)
	   {
	   //Socket clientSocket = null;
		   clientSocket = null;

	   try {
	       System.out.println("Listo para recibir ...");
	       clientSocket = serverSocket.accept();
	   } catch (IOException e) {
	       System.err.println("Accept failed.");
	       System.exit(1);
	   }
	   PrintWriter out = new PrintWriter(
	                         clientSocket.getOutputStream(), true);
	   BufferedReader in = new BufferedReader(
	                         new InputStreamReader(clientSocket.getInputStream()));
	   String inputLine, outputLine;
	   boolean isFirstLine = true;
	   String path = "";
	   String res = null;
	   
	   while ((inputLine = in.readLine()) != null) {
	      System.out.println("Recibí: " + inputLine);
	      if(isFirstLine){
	          path=inputLine.split(" ")[1];
	          isFirstLine=false;
	      }
	      if (!in.ready()) {break; }
	   }
	   
	   for(String key: routes.keySet()){
	       System.out.println(key);
	       if(path.startsWith(key)){
	           String newPath = path.substring(key.length());
	           System.out.println(newPath);
	           res= routes.get(key).handle(newPath,null,null);
	       }
	   }
	   if(res == null)
	   {
		   outputLine = 
				   	  "HTTP/1.1 200 OK\r\n"
				   	  + "Content-Type: text/html\r\n"
					  + "\r\n"
			          + "<!DOCTYPE html>" + 
			          "<html>" + 
			          "<head>" + 
			          "<meta charset=\"UTF-8\">" + 
			          "<title>AREP LAB 4</title>\n" + 
			          "</head>" + 
			          "<body>" + 
			          "<h1>Mi propio mensaje</h1>" + 
			          "</body>" + 
			          "</html>" + inputLine; 
	   }
	   else
	   {
		   outputLine = res;
	   }

    out.println(outputLine);
    out.close(); 
    in.close();
    clientSocket.close();
	   }
    serverSocket.close(); 
  }
    
    public void request(String fichero) throws IOException 
    {
		String path = "src/main/resources/";
        String change = FilenameUtils.getExtension(fichero);
        boolean value = false;
        
        switch (change) 
        {
            case "png":
            	path += "imagen/" + fichero;
            	value = true;
                break;
            case "js":
            	path += "js/" + fichero;
                break;
            case "html":
            	path += "index/" + fichero;
                break;       
        }

        File file = new File(path);
        
        if (file.exists() && !file.isDirectory()) 
        {	
            String header = headerGenerate(value, change, file.length());
            
            if (value) 
            {
                FileInputStream input = new FileInputStream(path);
                OutputStream output = clientSocket.getOutputStream();
                
                for (char c : header.toCharArray()) 
                {
                	output.write(c);
                }
                
                int a;
                while ((a = input.read()) > -1) 
                {
                	output.write(a);
                }
                
                output.flush();
                input.close();
                output.close();
                
            } 
            else 
            {	
                writer.println(header);
                BufferedReader br = new BufferedReader(new FileReader(file));

                StringBuilder stringBuilder = new StringBuilder();
                String string;
                
                while ((string = br.readLine()) != null)
                {
                    stringBuilder.append(string);
                }
                
                writer.println(stringBuilder.toString());
                br.close();
            }
        } 
        else
        {
        	writer.println("HTTP/1.1 404\r\nAccess-Control-Allow-Origin: *\r\n\r\n<html><body><h1>404 NOT FOUND (" + fichero + ")</h1></body></html>");
        }
	}
    
	private String headerGenerate(boolean value, String change, long length) {
		
        String encabezado = null;
        if (value) {
        	encabezado = "HTTP/1.1 200 \r\nAccess-Control-Allow-Origin: *\r\nContent-Type: imagen /" + change + "\r\nConnection: close\r\nContent-Length:" + length + "\r\n\r\n";
        } else {
        	encabezado = "HTTP/1.1 200 \r\nAccess-Control-Allow-Origin: *\r\nContent-Type: text/html\r\n\r\n";
        }
        
        return encabezado;
        
	}
    
}