package exemplo.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import exemplo.controller.ConnectionFactory;

public class UsuarioDAOmemoria implements UsuarioDAO {
	
	private Connection con;
	@Override
	public void adicionar(String login, String senha,String email) throws SQLException {
		con = new ConnectionFactory().getConnection();
		//System.out.println("Conexão aberta!");
		try{
		String postgresql = "insert into usuario " +
				"(login,senha,email) " +
				"values (?,?,?)";
		PreparedStatement stmt = con.prepareStatement(postgresql);
		stmt.setString(1,login);
		stmt.setString(2, senha);
		stmt.setString(3, email);
		
		stmt.execute();
		} catch (SQLException e){
			System.out.println(e);
		}
	}

	@Override
	public void remover(String login) {
		con = new ConnectionFactory().getConnection();
		//System.out.println("Conexão aberta!");
		try{
		String postgresql = "delete from usuario where login = ?";
		PreparedStatement stmt = con.prepareStatement(postgresql);
		stmt.setString(1,login);
		stmt.execute();
		
		} catch (SQLException e){
			System.out.println(e);
		}
	}

	@Override
	public boolean procurarPorLogin(String login) throws SQLException {

		boolean encontrado = false;
		
		String sql = "SELECT login FROM usuario WHERE login = ?";
		con = null;
		PreparedStatement pstm = null;
		
		try {
			con = new ConnectionFactory().getConnection();

			pstm = con.prepareStatement(sql);
			
			pstm.setString(1, login);
			
			ResultSet rs =pstm.executeQuery();
			while(rs.next()){
				encontrado = true;
			}
			
	
			
		}catch (SQLException e){
			System.out.println(e);
		} finally{
			pstm.close();
			con.close();
		}
		//System.out.println(usuarios.get(id));
		return encontrado;
	}

	@Override
	public boolean procurarPorSenha(String senha) throws SQLException {
		
		boolean encontrado = false;
		
		String sql = "SELECT login,senha FROM usuario WHERE senha = ? order by login";
		con = null;
		PreparedStatement pstm = null;
		
		try {
			con = new ConnectionFactory().getConnection();

			pstm = con.prepareStatement(sql);
			
			pstm.setString(1, senha);
			
			ResultSet rs = pstm.executeQuery();
			
			while (rs.next()) {
				encontrado = true;  
				String Name = rs.getString("login");
				  String Senha = rs.getString("senha");
				  System.out.println(Name + ", "+Senha);
			}
			
		}catch (SQLException e){
			System.out.println(e);
		} finally{
			pstm.close();
			con.close();
		}
		
		return encontrado;
	}

	@Override
	public void procurarPorEmail(String email) throws SQLException {
		
		String sql = "SELECT login,email FROM usuario WHERE email = ? order by login";
		con = null;
		PreparedStatement pstm = null;
		
		try {
			con = new ConnectionFactory().getConnection();

			pstm = con.prepareStatement(sql);
			
			pstm.setString(1, email);
			
			ResultSet rs = pstm.executeQuery();
			
			while (rs.next()) {
				  String Name = rs.getString("login");
				  String Email = rs.getString("email");
				  System.out.println(Name + ", "+Email);
			}
			
		}catch (SQLException e){
			System.out.println(e);
		} finally{
			pstm.close();
			con.close();
		}
		
	}
	
	public void retornaTodos() throws SQLException{
		String sql = "SELECT login,email FROM usuario";
		con = null;
		PreparedStatement pstm = null;
		
		try {
			con = new ConnectionFactory().getConnection();

			pstm = con.prepareStatement(sql);
			
			pstm.execute();
			
			
		}catch (SQLException e){
			System.out.println(e);
		} finally{
			pstm.close();
			con.close();
		}
	}
	


}
