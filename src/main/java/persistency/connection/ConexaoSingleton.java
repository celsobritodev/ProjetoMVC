package persistency.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import exception.ConexaoException;

public class ConexaoSingleton {
	
	public static final String DRIVER = "com.mysql.cj.jdbc.Driver";
	public static final String URL = "jdbc:mysql://localhost:3306/local_bd";
	public static final String USER_LOGIN = "root";
	public static final String USER_PASSWD = "admin";
	
	private static Connection conexao;
	
	private static Connection novaConexao() throws ConexaoException {
		Connection con = null;
		try {
			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER_LOGIN, USER_PASSWD);
		} catch (SQLException e) {
			throw new ConexaoException("Erro ao se conectar com o  banco de dados",e);
		} catch (ClassNotFoundException e) {
			throw new ConexaoException("Erro no driver do banco de dados",e);
		}
		return con;
		
	}
	
	public static Connection getConexao() throws ConexaoException {
		if (conexao == null)
			conexao = novaConexao();
		return conexao;
	}
	
	public static void finalizarConexao() throws ConexaoException {
		try {
			conexao.close();
		} catch (SQLException e) {
			throw new ConexaoException("Erro ao fechar a conexao com o banco de dados", e);
		}
		 conexao= null;
	}
	

}
