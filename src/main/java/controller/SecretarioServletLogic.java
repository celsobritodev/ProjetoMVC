package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.List;

import com.google.gson.Gson;

import exception.ColecaoException;
import exception.ConexaoException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Secretario;
import persistency.collection.ColecaoDeSecretario;
import persistency.collection.ColecaoDeSecretarioEmBDR;
import persistency.connection.ConexaoSingleton;
import util.ServletUtil;

public class SecretarioServletLogic implements ServletLogic {

	@Override
	public void buscar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter pw = response.getWriter();
		Gson gson = new Gson();
		String json = "";
		try {

			Connection con = ConexaoSingleton.getConexao();
			ColecaoDeSecretario cs = new ColecaoDeSecretarioEmBDR(con);

			String strId = request.getParameter("id");
			String codSecret = request.getParameter("codSecret");

			if (strId != null) {
				int id = Integer.parseInt(strId);
				Secretario s = cs.porId(id);
				if (s != null) {
					json = gson.toJson(s);
					response.setStatus(200);
					pw.append(json);
				}
			} else if (codSecret != null) {
				Secretario s = cs.porCodSecret(Integer.parseInt(codSecret));
				if (s != null) {
					json = gson.toJson(s);
					response.setStatus(200);
					pw.append(json);
				}
			} else {
				List<Secretario> ls = cs.todos();
				json = gson.toJson(ls);
				response.setStatus(200);
				pw.append(json);
			}

		} catch (ConexaoException e) {
			response.setStatus(500);
			pw.append("Erro ao estabelecer conexao com o banco de dados");
		} catch (ColecaoException e) {
			response.setStatus(500);
			pw.append("Erro ao realizar operacao no banco de dados");
		
		}
		
	}

	@Override
	public void inserir(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter pw = response.getWriter();
		Gson gson = new Gson();
		String json = ServletUtil.getCorpoReq(request);

		try {
			Connection con = ConexaoSingleton.getConexao();
			ColecaoDeSecretario cs = new ColecaoDeSecretarioEmBDR(con);

			if (json != "") {
				Secretario s = gson.fromJson(json, Secretario.class);
				cs.inserir(s);
				response.setStatus(200);

			} else {
				response.setStatus(400);
				pw.append("Dados do secretario não informados!");
			}

		} catch (ConexaoException e) {
			response.setStatus(500);
			pw.append("Erro ao estabelecer conexao com o banco de dados");
		} catch (ColecaoException e) {
			response.setStatus(500);
			pw.append("Erro ao realizar operacao no banco de dados");
		}
		
	}

	@Override
	public void alterar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter pw = response.getWriter();
		Gson gson = new Gson();
		String json = ServletUtil.getCorpoReq(request);

		try {
			Connection con = ConexaoSingleton.getConexao();
			ColecaoDeSecretario cs = new ColecaoDeSecretarioEmBDR(con);

			if (json != "") {
				Secretario s = gson.fromJson(json, Secretario.class);
				cs.alterar(s);
				response.setStatus(200);

			} else {
				response.setStatus(400);
				pw.append("Dados do secretario não informados!");
			}

		} catch (ConexaoException e) {
			response.setStatus(500);
			pw.append("Erro ao estabelecer conexao com o banco de dados");
		} catch (ColecaoException e) {
			response.setStatus(500);
			pw.append("Erro ao realizar operacao no banco de dados");
		}
		
	}

	@Override
	public void remover(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		PrintWriter pw = response.getWriter();

		try {
			Connection con = ConexaoSingleton.getConexao();
			ColecaoDeSecretario cs = new ColecaoDeSecretarioEmBDR(con);

			String strId = request.getParameter("id");

			if (strId != null) {
				int id = Integer.parseInt(strId);
				Secretario s = new Secretario(id);
				cs.remover(s);
				response.setStatus(200);
			} else {
				response.setStatus(400);
				pw.append("Id do secretario não informado!");
			}

		} catch (ConexaoException e) {
			response.setStatus(500);
			pw.append("Erro ao estabelecer conexao com o banco de dados");
		} catch (ColecaoException e) {
			response.setStatus(500);
			pw.append("Erro ao realizar operacao no banco de dados");
		}
		
	}

}
