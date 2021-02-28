package edu.escuelaing.arep;

import java.io.IOException;

import edu.escuelaing.arep.PicoSpring.PicoSpringBoot;

public class App {

	public static void main(String[] args) throws IOException, ClassNotFoundException
	{
		PicoSpringBoot pico = new PicoSpringBoot();
		pico.getInstance().loadComponent("edu.escuelaing.arep.picoSpringDemo");
		pico.getInstance().startServer();
	}

}
