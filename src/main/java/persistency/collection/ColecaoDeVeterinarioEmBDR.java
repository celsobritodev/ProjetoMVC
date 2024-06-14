package persistency.collection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import exception.ColecaoException;
import model.Veterinario;

public class ColecaoDeVeterinarioEmBDR implements ColecaoDeVeterinario {

	private Connection conexao;
	
	public ColecaoDeVeterinarioEmBDR (Connection conexao) {
		this.conexao = conexao;
	}
	
	
	
	@Override
	public List<Veterinario> todos() throws ColecaoException {
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Veterinario> lv = null;
		try {
			lv = new ArrayList<Veterinario>();
			String sql = "SELECT id, nome, crmv,salario FROM veterinario";
			ps = this.conexao.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				Veterinario v = new Veterinario (
						rs.getInt("id"),
						rs.getString("nome"),
						rs.getString("crmv"),
						rs.getDouble("salario")
						);
				lv.add(v);
			  }		
			} catch (SQLException e) {
				throw new ColecaoException("Erro ao obter os veterinarios do banco de dados!",e);
				
			} finally {
				try {
					ps.close();
					if(rs!=null)
						rs.close();
				} catch ( SQLException e) {
					throw new ColecaoException("Erro ao fechar manipuladores de banco de dados!",e);
				}
				
			}
		return lv;
	}

	@Override
	public Veterinario porId(int id) throws ColecaoException {
		PreparedStatement ps = null;
		ResultSet rs = null;
		Veterinario v = null;
		try {
			String sql = "SELECT id, nome, crmv,salario FROM veterinario WHERE id=?";
			ps = this.conexao.prepareStatement(sql);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			if (rs.next()) {
				v = new Veterinario( rs.getInt("id"), rs.getString("nome"),
						rs.getString("crmv"), rs.getDouble("salario"));
			}
		} catch (SQLException e) {
			throw new ColecaoException("Erro ao obter o veterinario do banco de dados!",e);
		} finally {
			try {
				ps.close();
				if(rs!=null)
					rs.close();
			} catch ( SQLException e) {
				throw new ColecaoException("Erro ao fechar manipuladores de banco de dados!",e);
			}
			
		}
		return v;
	}
	
	
	@Override
	public List<Veterinario> porNome(String nome) throws ColecaoException {
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Veterinario> lv = null;
		try {
			lv = new ArrayList<Veterinario>();
			String sql = "SELECT id, nome, crmv,salario FROM veterinario WHERE nome LIKE ?";
			ps = this.conexao.prepareStatement(sql);
			ps.setString(1,"%"+nome+"%");
			rs = ps.executeQuery();
			while (rs.next()) {
				Veterinario v = new Veterinario (
						rs.getInt("id"),
						rs.getString("nome"),
						rs.getString("crmv"),
						rs.getDouble("salario")
						);
				lv.add(v);
			  }		
			} catch (SQLException e) {
				throw new ColecaoException("Erro ao obter os veterinarios do banco de dados!",e);
				
			} finally {
				try {
					ps.close();
					if(rs!=null)
						rs.close();
				} catch ( SQLException e) {
					throw new ColecaoException("Erro ao fechar manipuladores de banco de dados!",e);
				}
				
			}
		return lv;
	}
	

	@Override
	public Veterinario porCrmv(String crmv) throws ColecaoException {
		PreparedStatement ps = null;
		ResultSet rs = null;
		Veterinario v = null;
		try {
			String sql = "SELECT id, nome, crmv,salario FROM veterinario WHERE crmv=?";
			ps = this.conexao.prepareStatement(sql);
			ps.setString(1, crmv);
			rs = ps.executeQuery();
			if (rs.next()) {
				 v = new Veterinario (
						rs.getInt("id"),
						rs.getString("nome"),
						rs.getString("crmv"),
						rs.getDouble("salario")
						);
			}
		} catch (SQLException e) {
			throw new ColecaoException("Erro ao obter o veterinario do banco de dados!",e);
		} finally {
			try {
				ps.close();
				if(rs!=null)
					rs.close();
			} catch ( SQLException e) {
				throw new ColecaoException("Erro ao fechar manipuladores de banco de dados!",e);
			}
			
		}
		return v;
	
	}
	
	

	@Override
	public void inserir(Veterinario veterinario) throws ColecaoException {
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			String sql = "INSERT INTO veterinario (nome,crmv,salario) VALUES (?,?,?)";
			ps= this.conexao.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
			ps.setString(1,veterinario.getNome());
			ps.setString(2,veterinario.getCrmv());
			ps.setDouble(3,veterinario.getSalario());
			ps.execute();
			rs=ps.getGeneratedKeys();
			if(rs.next()) {
				veterinario.setId(rs.getInt(1));
			}
		} catch (SQLException e) {
			throw new ColecaoException("Erro ao inserir o veterinario no banco de dados!",e);
		}  finally {
			try {
				ps.close();
				if(rs!=null)
					rs.close();
			} catch ( SQLException e) {
				throw new ColecaoException("Erro ao fechar manipuladores de banco de dados!",e);
			}
			
		}
		
	}

	@Override
	public void alterar(Veterinario veterinario) throws ColecaoException {
		PreparedStatement ps = null;
		try {
			String sql = "UPDATE veterinario SET nome=?,crmv=?,salario=? WHERE id=?";
			ps= this.conexao.prepareStatement(sql);
			ps.setString(1,veterinario.getNome());
			ps.setString(2,veterinario.getCrmv());
			ps.setDouble(3,veterinario.getSalario());
			ps.setInt(4, veterinario.getId());
			ps.execute();
		} catch (SQLException e) {
			throw new ColecaoException("Erro ao alterar o veterinario no banco de dados!",e);
		}  finally {
			try {
				ps.close();
			} catch ( SQLException e) {
				throw new ColecaoException("Erro ao fechar manipuladores de banco de dados!",e);
			}
			
		}
		
		
	}

	@Override
	public void remover(Veterinario veterinario) throws ColecaoException {
		PreparedStatement ps = null;
		try {
			String sql = "DELETE FROM veterinario WHERE id=?";
			ps= this.conexao.prepareStatement(sql);
			ps.setInt(1, veterinario.getId());
			ps.execute();
		} catch (SQLException e) {
			throw new ColecaoException("Erro ao remover o veterinario no banco de dados!",e);
		}  finally {
			try {
				ps.close();
			} catch ( SQLException e) {
				throw new ColecaoException("Erro ao fechar manipuladores de banco de dados!",e);
			}
			
		}
		
	}

	


}
