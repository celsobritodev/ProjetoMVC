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
import model.Veterinario;
import persistency.collection.ColecaoDeVeterinario;
import persistency.collection.ColecaoDeVeterinarioEmBDR;
import persistency.connection.ConexaoSingleton;

public class VeterinarioLogica {
	
	public static void get(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		PrintWriter pw = response.getWriter();
		Gson gson = new Gson();
		String json = "";
		try {

			Connection con = ConexaoSingleton.getConexao();
			ColecaoDeVeterinario cv = new ColecaoDeVeterinarioEmBDR(con);

			String strId = request.getParameter("id");
			String crmv = request.getParameter("crmv");

			if (strId != null) {
				int id = Integer.parseInt(strId);
				Veterinario v = cv.porId(id);
				if (v != null) {
					json = gson.toJson(v);
					response.setStatus(200);
					pw.append(json);
				}
			} else if (crmv != null) {
				Veterinario v = cv.porCrmv(crmv);
				if (v != null) {
					json = gson.toJson(v);
					response.setStatus(200);
					pw.append(json);
				}
			} else {
				List<Veterinario> lv = cv.todos();
				json = gson.toJson(lv);
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
			ColecaoDeVeterinario cv = new ColecaoDeVeterinarioEmBDR(con);

			if (json != "") {
				Veterinario v = gson.fromJson(json, Veterinario.class);
				cv.inserir(v);
				response.setStatus(200);

			} else {
				response.setStatus(400);
				pw.append("Dados do veterinario não informados!");
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
			ColecaoDeVeterinario cv = new ColecaoDeVeterinarioEmBDR(con);

			if (json != "") {
				Veterinario v = gson.fromJson(json, Veterinario.class);
				cv.alterar(v);
				response.setStatus(200);

			} else {
				response.setStatus(400);
				pw.append("Dados do veterinario não informados!");
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
			ColecaoDeVeterinario cv = new ColecaoDeVeterinarioEmBDR(con);

			String strId = request.getParameter("id");

			if (strId != null) {
				int id = Integer.parseInt(strId);
				Veterinario v = new Veterinario(id);
				cv.remover(v);
				response.setStatus(200);
			} else {
				response.setStatus(400);
				pw.append("Id do veterinario não informado!");
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
