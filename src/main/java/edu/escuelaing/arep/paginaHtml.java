package edu.escuelaing.arep;

import edu.escuelaing.arep.PicoSpring.RequestMapping;

public class paginaHtml
{
	@RequestMapping("/index.html")
	public static String index()
	{
		return "<!doctype html>"

					+ "<head>"
						+ "<meta http-equiv='Content-type' content='text/html; charset=utf-8' />"
						+ "<link rel='stylesheet' href='/menu.css'>"
						+ "<title>Ejemplo de menu 2</title>"
					+ "</head>"

					+ "<body bgcolor='#cddf89'>"
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

			+ "</body></html>";
	}
}
