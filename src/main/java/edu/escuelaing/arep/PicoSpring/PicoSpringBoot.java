package edu.escuelaing.arep.PicoSpring;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import edu.escuelaing.arep.HttpServer.HttpServer;
import edu.escuelaing.arep.HttpServer.Processors;
import edu.escuelaing.arep.PicoSpring.RequestMapping;

public class PicoSpringBoot implements Processors
{
	private static PicoSpringBoot _instance = new PicoSpringBoot();
	private Map<String, Method> requestProcessors = new HashMap();
	private HttpServer hServer;
	
	/*
	 * Constructor del pico spring
	 */
	private PicoSpringBoot() {}
	
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
	
	private void loadComponent(String componentName) throws ClassNotFoundException {
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
		hServer = new HttpServer();
		hServer.registerProcessor("/springapp", this);
		hServer.startServer(8080);
	}
	
	public static void main(String[] args) throws IOException, ClassNotFoundException
	{
		PicoSpringBoot.getInstance().loadComponent(args);
		PicoSpringBoot.getInstance().startServer();
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
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return validOkHttpHeader() + resp;
	}
}
