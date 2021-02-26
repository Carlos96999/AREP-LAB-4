package edu.escuelaing.arep.HttpServer;

import java.net.*;
import java.util.HashMap;
import java.util.Map;
import java.io.*;

public class HttpServer {
	
	private int port;
    Map<String,Processors> routes = new HashMap();
	
    public void registerProcessor(String path, Processors proc) {
        routes.put(path,proc);
    }
  
    public void startServer(int port) throws IOException {
    	this.port = port;
	   ServerSocket serverSocket = null;
	   try { 
	      serverSocket = new ServerSocket(this.port);
	   } catch (IOException e) {
	      System.err.println("Could not listen on port: " + this.port);
	      System.exit(1);
	   }
	
	   Socket clientSocket = null;
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
			          "<title>Title of the document</title>\n" + 
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
    serverSocket.close(); 
  }
}