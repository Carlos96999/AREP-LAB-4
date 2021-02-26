package edu.escuelaing.arep;

import edu.escuelaing.arep.PicoSpring.RequestMapping;

public class picoSpringDemo 
{
	@RequestMapping("/mensaje")
	public static String prueba()
	{
		return "Hola";
	}
}
