package controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface ServletLogic {
	
	public void buscar ( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException;
	public void inserir ( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException;
	public void alterar ( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException;
	public void remover ( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException;

}
