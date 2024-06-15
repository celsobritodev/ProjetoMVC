package persistency.collection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import exception.ColecaoException;
import model.Atendente;

public class ColecaoDeAtendenteEmBDR implements ColecaoDeAtendente {

	private Connection conexao;

	public ColecaoDeAtendenteEmBDR(Connection conexao) {
		this.conexao = conexao;
	}

	@Override
	public List<Atendente> todos() throws ColecaoException {
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Atendente> la = null;
		try {
			la = new ArrayList<Atendente>();
			String sql = "SELECT id, nome, codAtend,salario FROM atendente";
			ps = this.conexao.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				Atendente a = new Atendente(rs.getInt("id"), rs.getString("nome"), rs.getInt("codAtend"),
						rs.getDouble("salario"));
				la.add(a);
			}
		} catch (SQLException e) {
			throw new ColecaoException("Erro ao obter os veterinarios do banco de dados!", e);

		} finally {
			try {
				ps.close();
				if (rs != null)
					rs.close();
			} catch (SQLException e) {
				throw new ColecaoException("Erro ao fechar manipuladores de banco de dados!", e);
			}

		}
		return la;
	}

	@Override
	public Atendente porId(int id) throws ColecaoException {
		PreparedStatement ps = null;
		ResultSet rs = null;
		Atendente a = null;
		try {
			String sql = "SELECT id, nome, codAtend,salario FROM atendente WHERE id=?";
			ps = this.conexao.prepareStatement(sql);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			if (rs.next()) {
				a = new Atendente(rs.getInt("id"), rs.getString("nome"), rs.getInt("codAtend"),
						rs.getDouble("salario"));
			}
		} catch (SQLException e) {
			throw new ColecaoException("Erro ao obter o atendente do banco de dados!", e);
		} finally {
			try {
				ps.close();
				if (rs != null)
					rs.close();
			} catch (SQLException e) {
				throw new ColecaoException("Erro ao fechar manipuladores de banco de dados!", e);
			}

		}
		return a;
	}

	@Override
	public List<Atendente> porNome(String nome) throws ColecaoException {
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Atendente> la = null;
		try {
			la = new ArrayList<Atendente>();
			String sql = "SELECT id, nome, codAtend,salario FROM veterinario WHERE nome LIKE ?";
			ps = this.conexao.prepareStatement(sql);
			ps.setString(1, "%" + nome + "%");
			rs = ps.executeQuery();
			while (rs.next()) {
				Atendente a = new Atendente(rs.getInt("id"), rs.getString("nome"), rs.getInt("codAtend"),
						rs.getDouble("salario"));
				la.add(a);
			}
		} catch (SQLException e) {
			throw new ColecaoException("Erro ao obter os atendentes do banco de dados!", e);

		} finally {
			try {
				ps.close();
				if (rs != null)
					rs.close();
			} catch (SQLException e) {
				throw new ColecaoException("Erro ao fechar manipuladores de banco de dados!", e);
			}

		}
		return la;
	}

	@Override
	public Atendente porCodAtend(int codAtend) throws ColecaoException {
		PreparedStatement ps = null;
		ResultSet rs = null;
		Atendente a = null;
		try {
			String sql = "SELECT id, nome, codAtend,salario FROM atendente WHERE codAtend=?";
			ps = this.conexao.prepareStatement(sql);
			ps.setInt(1, codAtend);
			rs = ps.executeQuery();
			if (rs.next()) {
				a = new Atendente(rs.getInt("id"), rs.getString("nome"), rs.getInt("codAtend"),
						rs.getDouble("salario"));
			}
		} catch (SQLException e) {
			throw new ColecaoException("Erro ao obter o atendente do banco de dados!", e);
		} finally {
			try {
				ps.close();
				if (rs != null)
					rs.close();
			} catch (SQLException e) {
				throw new ColecaoException("Erro ao fechar manipuladores de banco de dados!", e);
			}

		}
		return a;

	}

	@Override
	public void inserir(Atendente atendente) throws ColecaoException {
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			String sql = "INSERT INTO atendente (nome,codAtend,salario) VALUES (?,?,?)";
			ps = this.conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, atendente.getNome());
			ps.setInt(2, atendente.getCodAtend());
			ps.setDouble(3, atendente.getSalario());
			ps.execute();
			rs = ps.getGeneratedKeys();
			if (rs.next()) {
				atendente.setId(rs.getInt(1));
			}
		} catch (SQLException e) {
			throw new ColecaoException("Erro ao inserir o atendente no banco de dados!", e);
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
	public void alterar(Atendente atendente) throws ColecaoException {
		PreparedStatement ps = null;
		try {
			String sql = "UPDATE atendente SET nome=?,codAtend=?,salario=? WHERE id=?";
			ps = this.conexao.prepareStatement(sql);
			ps.setString(1, atendente.getNome());
			ps.setInt(2, atendente.getCodAtend());
			ps.setDouble(3, atendente.getSalario());
			ps.setInt(4, atendente.getId());
			ps.execute();
		} catch (SQLException e) {
			throw new ColecaoException("Erro ao alterar o atendente no banco de dados!", e);
		} finally {
			try {
				ps.close();
			} catch (SQLException e) {
				throw new ColecaoException("Erro ao fechar manipuladores de banco de dados!", e);
			}

		}

	}

	@Override
	public void remover(Atendente atendente) throws ColecaoException {
		PreparedStatement ps = null;
		try {
			String sql = "DELETE FROM atendente WHERE id=?";
			ps = this.conexao.prepareStatement(sql);
			ps.setInt(1, atendente.getId());
			ps.execute();
		} catch (SQLException e) {
			throw new ColecaoException("Erro ao remover o atendente no banco de dados!", e);
		} finally {
			try {
				ps.close();
			} catch (SQLException e) {
				throw new ColecaoException("Erro ao fechar manipuladores de banco de dados!", e);
			}

		}

	}

}
