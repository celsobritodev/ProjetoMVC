package controller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class Servlet extends HttpServlet {
		private static final long serialVersionUID = 1L;

	private ServletLogic invocarLogica ( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
		
		String logica  = request.getParameter("logica");
		ServletLogic logic = null;
		if(logica!=null) {
			String nomeClasse = logica +"ServletLogic";
			String nomeCompletoDaClasse = "controller."+nomeClasse;
			try {
				Class<?> classe = Class.forName(nomeCompletoDaClasse);
				logic = (ServletLogic) (classe.getDeclaredConstructor().newInstance());
					
			} catch (ClassNotFoundException e) {
				throw new ServletException ("A lógica informada não é válida!");
			} catch (InstantiationException e) {
				throw new ServletException (e);
			} catch (IllegalAccessException e) {
				throw new ServletException(e);
			} catch (NoSuchMethodException e) {
				throw new ServletException(e); 
			} catch (InvocationTargetException e) {
				throw new ServletException (e);
			}
		}
		return logic;
		
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServletLogic logica = this.invocarLogica(request, response);
		logica.buscar(request, response);
		
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServletLogic logica = this.invocarLogica(request, response);
		logica.inserir(request, response);

		
	}
	
	@Override
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServletLogic logica = this.invocarLogica(request, response);
		logica.alterar(request, response);

		
	}
	
	@Override
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServletLogic logica = this.invocarLogica(request, response);
		logica.remover(request, response);

		
	}

}
