package edu.escuelaing.arep;

import edu.escuelaing.arep.PicoSpring.RequestMapping;

public class picoSpringDemo 
{
	@RequestMapping("/inicio")
	public static String prueba()
	{
		return    "<!DOCTYPE html>" + 
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
						+ "<li><a href='/springapp/inicio'>"
							+ "<font size='6' color='#0f6a9d' face='Comics Sans CM'>"
								+ "Inicio"
							+ "</font>"
						+ "</a></li>"

					+ "<li><a href='/springapp/pruebajavascript'>"
						+ "<font size='6' color='#1f6dfd' face='Comics Sans CM'>"
							+ "Pagina 1"
						+ "</font>"
					+ "</a></li>"

					+ "<li><a href='/springapp/pruebaimagen'>"
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
	
	@RequestMapping("/pruebajavascript")
	public static String pruebaJavaScript()
	{
		return "<html><title>thanks</title><head><script type=\"text/javascript\">\r\n"
				+ "  alert(\"Laboratorio de AREP :3\");\r\n"
				+ "</script></head>"
        		+ "<body>\r\n"
        		+ "</body>"
        		+ "</html>";
	}
	
	@RequestMapping("/pruebaimagen")
    public static String pruebaImagen()
	{
        return "<html><title>Imagen</title><body><img src=\"https://www.vhv.rs/dpng/d/423-4235481_teem-lucario-hd-png-download.png\"></body></html>";
    }
}
