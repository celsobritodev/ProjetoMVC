package persistency.collection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import exception.ColecaoException;
import model.Secretario;


public class ColecaoDeSecretarioEmBDR implements ColecaoDeSecretario {
	
	
	private Connection conexao;

	public ColecaoDeSecretarioEmBDR(Connection conexao) {
		this.conexao = conexao;
	}

	@Override
	public List<Secretario> todos() throws ColecaoException {
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Secretario> ls = null;
		try {
			ls = new ArrayList<Secretario>();
			String sql = "SELECT id, nome, codSecret,salario FROM secretario";
			ps = this.conexao.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				Secretario s = new Secretario(rs.getInt("id"), rs.getString("nome"), rs.getInt("codSecret"),
						rs.getDouble("salario"));
				ls.add(s);
			}
		} catch (SQLException e) {
			throw new ColecaoException("Erro ao obter os secretarios do banco de dados!", e);

		} finally {
			try {
				ps.close();
				if (rs != null)
					rs.close();
			} catch (SQLException e) {
				throw new ColecaoException("Erro ao fechar manipuladores de banco de dados!", e);
			}

		}
		return ls;
	}

	@Override
	public Secretario porId(int id) throws ColecaoException {
		PreparedStatement ps = null;
		ResultSet rs = null;
		Secretario s = null;
		try {
			String sql = "SELECT id, nome, codSecret,salario FROM secretario WHERE id=?";
			ps = this.conexao.prepareStatement(sql);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			if (rs.next()) {
				s = new Secretario(rs.getInt("id"), rs.getString("nome"), rs.getInt("codSecret"),
						rs.getDouble("salario"));
			}
		} catch (SQLException e) {
			throw new ColecaoException("Erro ao obter o secretario do banco de dados!", e);
		} finally {
			try {
				ps.close();
				if (rs != null)
					rs.close();
			} catch (SQLException e) {
				throw new ColecaoException("Erro ao fechar manipuladores de banco de dados!", e);
			}

		}
		return s;
	}

	@Override
	public List<Secretario> porNome(String nome) throws ColecaoException {
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Secretario> ls = null;
		try {
			ls = new ArrayList<Secretario>();
			String sql = "SELECT id, nome, codSecret,salario FROM secretario WHERE nome LIKE ?";
			ps = this.conexao.prepareStatement(sql);
			ps.setString(1, "%" + nome + "%");
			rs = ps.executeQuery();
			while (rs.next()) {
				Secretario s = new Secretario(rs.getInt("id"), rs.getString("nome"), rs.getInt("codSecret"),
						rs.getDouble("salario"));
				ls.add(s);
			}
		} catch (SQLException e) {
			throw new ColecaoException("Erro ao obter os secretarios do banco de dados!", e);

		} finally {
			try {
				ps.close();
				if (rs != null)
					rs.close();
			} catch (SQLException e) {
				throw new ColecaoException("Erro ao fechar manipuladores de banco de dados!", e);
			}

		}
		return ls;
	}

	@Override
	public Secretario porCodSecret(int codSecret) throws ColecaoException {
		PreparedStatement ps = null;
		ResultSet rs = null;
		Secretario s = null;
		try {
			String sql = "SELECT id, nome, codSecret,salario FROM secretario WHERE codSecret=?";
			ps = this.conexao.prepareStatement(sql);
			ps.setInt(1, codSecret);
			rs = ps.executeQuery();
			if (rs.next()) {
				s = new Secretario(rs.getInt("id"), rs.getString("nome"), rs.getInt("codSecret"),
						rs.getDouble("salario"));
			}
		} catch (SQLException e) {
			throw new ColecaoException("Erro ao obter o secretario do banco de dados!", e);
		} finally {
			try {
				ps.close();
				if (rs != null)
					rs.close();
			} catch (SQLException e) {
				throw new ColecaoException("Erro ao fechar manipuladores de banco de dados!", e);
			}

		}
		return s;

	}

	@Override
	public void inserir(Secretario secretario) throws ColecaoException {
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			String sql = "INSERT INTO secretario (nome,codSecret,salario) VALUES (?,?,?)";
			ps = this.conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, secretario.getNome());
			ps.setInt(2, secretario.getCodSecret());
			ps.setDouble(3, secretario.getSalario());
			ps.execute();
			rs = ps.getGeneratedKeys();
			if (rs.next()) {
				secretario.setId(rs.getInt(1));
			}
		} catch (SQLException e) {
			throw new ColecaoException("Erro ao inserir o secretario no banco de dados!", e);
		} finally {
			try {
				ps.close();
				if (rs != null)
					rs.close();
			} catch (SQLException e) {
				throw new ColecaoException("Erro ao fechar manipuladores de banco de dados!", e);
			}

		}

	}

	@Override
	public void alterar(Secretario secretario) throws ColecaoException {
		PreparedStatement ps = null;
		try {
			String sql = "UPDATE secretario SET nome=?,codSecret=?,salario=? WHERE id=?";
			ps = this.conexao.prepareStatement(sql);
			ps.setString(1, secretario.getNome());
			ps.setInt(2, secretario.getCodSecret());
			ps.setDouble(3, secretario.getSalario());
			ps.setInt(4, secretario.getId());
			ps.execute();
		} catch (SQLException e) {
			throw new ColecaoException("Erro ao alterar o secretario no banco de dados!", e);
		} finally {
			try {
				ps.close();
			} catch (SQLException e) {
				throw new ColecaoException("Erro ao fechar manipuladores de banco de dados!", e);
			}

		}

	}

	@Override
	public void remover(Secretario secretario) throws ColecaoException {
		PreparedStatement ps = null;
		try {
			String sql = "DELETE FROM secretario WHERE id=?";
			ps = this.conexao.prepareStatement(sql);
			ps.setInt(1, secretario.getId());
			ps.execute();
		} catch (SQLException e) {
			throw new ColecaoException("Erro ao remover o secretario no banco de dados!", e);
		} finally {
			try {
				ps.close();
			} catch (SQLException e) {
				throw new ColecaoException("Erro ao fechar manipuladores de banco de dados!", e);
			}

		}

	}	
	
	

}
