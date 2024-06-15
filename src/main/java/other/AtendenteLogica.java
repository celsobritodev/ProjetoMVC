package other;

import java.io.BufferedReader;
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
import model.Atendente;
import persistency.collection.ColecaoDeAtendente;
import persistency.collection.ColecaoDeAtendenteEmBDR;
import persistency.connection.ConexaoSingleton;

public class AtendenteLogica {
	

	public static void get(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		PrintWriter pw = response.getWriter();
		Gson gson = new Gson();
		String json = "";
		try {

			Connection con = ConexaoSingleton.getConexao();
			ColecaoDeAtendente ca = new ColecaoDeAtendenteEmBDR(con);

			String strId = request.getParameter("id");
			String codAtend = request.getParameter("codAtend");

			if (strId != null) {
				int id = Integer.parseInt(strId);
				Atendente a = ca.porId(id);
				if (a != null) {
					json = gson.toJson(a);
					response.setStatus(200);
					pw.append(json);
				}
			} else if (codAtend != null) {
				Atendente a = ca.porCodAtend(Integer.parseInt(codAtend));
				if (a != null) {
					json = gson.toJson(a);
					response.setStatus(200);
					pw.append(json);
				}
			} else {
				List<Atendente> la = ca.todos();
				json = gson.toJson(la);
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

	
	
	

	public static void post(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		PrintWriter pw = response.getWriter();
		Gson gson = new Gson();
		String json = getCorpoReq(request);

		try {
			Connection con = ConexaoSingleton.getConexao();
			ColecaoDeAtendente ca = new ColecaoDeAtendenteEmBDR(con);

			if (json != "") {
				Atendente a = gson.fromJson(json, Atendente.class);
				ca.inserir(a);
				response.setStatus(200);

			} else {
				response.setStatus(400);
				pw.append("Dados do atendente não informados!");
			}

		} catch (ConexaoException e) {
			response.setStatus(500);
			pw.append("Erro ao estabelecer conexao com o banco de dados");
		} catch (ColecaoException e) {
			response.setStatus(500);
			pw.append("Erro ao realizar operacao no banco de dados");
		}
	}


	public static void put(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		PrintWriter pw = response.getWriter();
		Gson gson = new Gson();
		String json = getCorpoReq(request);

		try {
			Connection con = ConexaoSingleton.getConexao();
			ColecaoDeAtendente ca = new ColecaoDeAtendenteEmBDR(con);

			if (json != "") {
				Atendente a = gson.fromJson(json, Atendente.class);
				ca.alterar(a);
				response.setStatus(200);

			} else {
				response.setStatus(400);
				pw.append("Dados do atendente não informados!");
			}

		} catch (ConexaoException e) {
			response.setStatus(500);
			pw.append("Erro ao estabelecer conexao com o banco de dados");
		} catch (ColecaoException e) {
			response.setStatus(500);
			pw.append("Erro ao realizar operacao no banco de dados");
		}

	}
	
	

	public static void delete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		PrintWriter pw = response.getWriter();

		try {
			Connection con = ConexaoSingleton.getConexao();
			ColecaoDeAtendente ca = new ColecaoDeAtendenteEmBDR(con);

			String strId = request.getParameter("id");

			if (strId != null) {
				int id = Integer.parseInt(strId);
				Atendente a = new Atendente(id);
				ca.remover(a);
				response.setStatus(200);
			} else {
				response.setStatus(400);
				pw.append("Id do atendenteveterinario não informado!");
			}

		} catch (ConexaoException e) {
			response.setStatus(500);
			pw.append("Erro ao estabelecer conexao com o banco de dados");
		} catch (ColecaoException e) {
			response.setStatus(500);
			pw.append("Erro ao realizar operacao no banco de dados");
		}
	}

	private static String getCorpoReq(HttpServletRequest request) throws IOException {
		BufferedReader br = request.getReader();
		String str, corpo = "";
		str = br.readLine();
		while (str != null) {
			corpo += str;
			str = br.readLine();
		}
		return corpo;
	}

	

}
