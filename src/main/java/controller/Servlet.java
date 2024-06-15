package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import other.AtendenteLogica;
import other.SecretarioLogica;
import other.VeterinarioLogica;
import java.io.IOException;

/**
 * Servlet implementation class Servlet
 */
public class Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String logica = request.getParameter("logica");

		if (logica != null)

			switch (logica) {
			case "veterinario": {
			     VeterinarioLogica.get(request,response);
			 	break;
				}
			case "atendente": {
			     AtendenteLogica.get(request,response);
			     break;
				}
			case "secretario": {
			    SecretarioLogica.get(request,response);
				break;
			    }
			}
			
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {


		String logica = request.getParameter("logica");

		if (logica != null)
			switch (logica) {
			case "veterinario": {
			     VeterinarioLogica.post(request,response);
			 	break;
				}
			case "atendente": {
			     AtendenteLogica.post(request,response);
			     break;
				}
			case "secretario": {
			    SecretarioLogica.post(request,response);
				break;
			    }
			
			}
	}

	protected void doPut(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String logica = request.getParameter("logica");

		if (logica != null)
			switch (logica) {
			case "veterinario": {
			     VeterinarioLogica.put(request,response);
			 	break;
				}
			case "atendente": {
			     AtendenteLogica.put(request,response);
			     break;
				}
			case "secretario": {
			    SecretarioLogica.put(request,response);
				break;
			    }
			
			}
	}

	protected void doDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String logica = request.getParameter("logica");

		if (logica != null)
			switch (logica) {
			case "veterinario": {
			     VeterinarioLogica.delete(request,response);
			 	break;
				}
			case "atendente": {
			     AtendenteLogica.delete(request,response);
			     break;
				}
			case "secretario": {
			    SecretarioLogica.delete(request,response);
				break;
			    }
			
			}
	}
	
	
	


}
