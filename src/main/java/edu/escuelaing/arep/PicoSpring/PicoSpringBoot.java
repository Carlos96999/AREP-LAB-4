package edu.escuelaing.arep.PicoSpring;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
//import java.util.logging.Level;
//import java.util.logging.Logger;
import java.util.regex.Matcher;
import edu.escuelaing.arep.HttpServer.HttpServer;
import edu.escuelaing.arep.HttpServer.Processors;
//import edu.escuelaing.arep.PicoSpring.RequestMapping;

public class PicoSpringBoot implements Processors
{
	private static PicoSpringBoot _instance = new PicoSpringBoot();
	private Map<String, Method> requestProcessors = new HashMap();
	private HttpServer hServer;
	private int port;
	
	/*
	 * Constructor del pico spring
	 */
	public PicoSpringBoot() {}
	
	public static PicoSpringBoot getInstance() 
	{
		return _instance;
	}
	
	public void loadComponent(String[] componentsList) throws ClassNotFoundException 
	{
		for(String componentName: componentsList)
		{
			loadComponent(componentName);
		}
	}
	
	public void loadComponent(String componentName) throws ClassNotFoundException 
	{
        Class componentClass = Class.forName(componentName);
        Method[] ComponentMethods = componentClass.getDeclaredMethods();
        for(Method m: ComponentMethods){
            if(m.isAnnotationPresent(RequestMapping.class)){
                requestProcessors.put(m.getAnnotation(RequestMapping.class).value(),m);
            }
        }
    }
	
	public void startServer() throws IOException
	{
		Matcher matcher = null;
		System.out.println("inicio -----------------------");
		port = getPort();
		hServer = new HttpServer();
		hServer.registerProcessor("/springapp", this);
		hServer.startServer(getPort());
		String archivo = matcher.group().substring(4);
		hServer.request(archivo);
	}
	
	public static void main(String[] args) throws IOException, ClassNotFoundException
	{
		getInstance().loadComponent("edu.escuelaing.arep.picoSpringDemo");
		getInstance().startServer();
	}

	public String validOkHttpHeader()
	{
		return "HTTP/1.1 200 OK\r\n"
				+ "Content-Type: text/html\r\n"
				+ "\r\n";
	}
	
	@Override
	public String handle(String path, String re, String res) 
	{
		String resp = "";
		if(requestProcessors.containsKey(path))
		{
			System.out.println("Executing method attached to: " +path);
			try {
				resp = requestProcessors.get(path).invoke(null, null).toString();
			} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
				e.printStackTrace();
			}
		}
		System.out.println(validOkHttpHeader() + resp);
		return validOkHttpHeader() + resp;
	}
	
	private static int getPort()
	{
		if(System.getenv("PORT") != null)
		{
			return Integer.parseInt(System.getenv("PORT"));
		}
		return 8080; 
	}
}
