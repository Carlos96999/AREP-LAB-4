package edu.escuelaing.arep;

import edu.escuelaing.arep.PicoSpring.RequestMapping;

public class picoSpringDemo 
{
	@RequestMapping("/mensaje")
	public static String prueba()
	{
		return
		          "<!DOCTYPE html>" + 
		          "<html>" + 
		          "<head>" + 
		          "<meta charset=\"UTF-8\">" + 
		          "<title>AREP LAB 4</title>\n" + 
		          "</head>" + 
		          "<body bgcolor='#cddf89'>"
					+ "<font size='6' color='#df7644' face='Tahoma'>"
						+ "Menú en formato texto con font"
					+ "</font>"
					+ "<br><br>"

					+ "<nav class='menu'>"

					+ "<ul>"
						+ "<li><a href='#'>"
							+ "<font size='6' color='#0f6a9d' face='Comics Sans CM'>"
								+ "Inicio"
							+ "</font>"
						+ "</a></li>"

					+ "<li><a href='#'>"
						+ "<font size='6' color='#1f6dfd' face='Comics Sans CM'>"
							+ "Pagina 1"
						+ "</font>"
					+ "</a></li>"

					+ "<li><a href='#'>"
						+ "<font size='6' color='#df619d' face='Comics Sans CM'>"
							+ "Pagina 2"
						+ "</font>"
					+ "</a></li>"

					+ "<li><a href='#'>"
						+ "<font size='6' color='#49029d' face='Comics Sans CM'>"
							+ "Pagina 3"
						+ "</font>"
					+ "</a></li>"

				+ "</ul>"

			+ "</nav>"

		+ "</body>"
		          +"</html>";
	}
	
	@RequestMapping("/mensaje2")
	public static String prueba2()
	{
		return "<html><title>thanks</title><head><script alert(\"Que la Fuerza te Acompañe!\");></script></head>"
        		+ "<body style = \"background: url(https://i.pinimg.com/originals/44/ac/f0/44acf0c89a96f3cd5e3aaaa6c7c61dfc.jpg) no-repeat ; background-size: 100% 100%;\">\r\n"
        		+ "</body>"
        		+ "</html>";
	}
}
