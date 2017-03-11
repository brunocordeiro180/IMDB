package exemplo.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import exemplo.controller.ConnectionFactory;

//pessoa pode ser Ator ou Diretor 
public class PessoaDAOmemoria implements PessoaDAO {
	
	private Connection con;

	@Override //realiza query insert 
	public void adicionar(String nome, int idade) {
		
		//Conexao aberta
		con = new ConnectionFactory().getConnection();
		
		try{
		String postgresql = "insert into pessoa " +
				"(nome,idade) " +
				"values (?,?)";
		PreparedStatement stmt = con.prepareStatement(postgresql);
		
		//coloca os dados passados no metodo para a query
		stmt.setString(1, nome);
		stmt.setInt(2, idade);
		
		stmt.execute();
		} catch (SQLException e){
			System.out.println(e);
		}
	}

	@Override
	public void remover(String nome) throws SQLException {
		String sql = "delete from pessoa WHERE nome = ?";
		con = null;
		PreparedStatement pstm = null;
		
		try {
			con = new ConnectionFactory().getConnection();

			pstm = con.prepareStatement(sql);
			
			pstm.setString(1, nome);
			
			pstm.execute();
			
		}catch (SQLException e){
			System.out.println(e);
		} finally{
			pstm.close();
			con.close();
		}

	}

	@Override
	public void procurarPorNome(String nome) throws SQLException {

		String sql = "SELECT nome FROM pessoa WHERE nome = ?";
		con = null;
		PreparedStatement pstm = null;
		
		try {
			con = new ConnectionFactory().getConnection();

			pstm = con.prepareStatement(sql);
			
			pstm.setString(1, nome);
			
			ResultSet rs = pstm.executeQuery();
			
			while (rs.next()) {
				  String Name = rs.getString("nome");
				  System.out.println(Name + "\n");
			}
			
		}catch (SQLException e){
			System.out.println(e);
		} finally{
			pstm.close();
			con.close();
		}
		

	}

	@Override
	public void procurarPorIdade(int idade) throws SQLException {

		String sql = "SELECT nome,idade FROM pessoa WHERE idade = ? order by nome";
		con = null;
		PreparedStatement pstm = null;
		
		try {
			con = new ConnectionFactory().getConnection();

			pstm = con.prepareStatement(sql);
			
			pstm.setInt(1, idade);
			
			ResultSet rs = pstm.executeQuery();
			
			while (rs.next()) {
				  String Name = rs.getString("nome");
				  int Idade = rs.getInt("idade");
				  System.out.println(Name + ", "+Idade);
			}
			
		}catch (SQLException e){
			System.out.println(e);
		} finally{
			pstm.close();
			con.close();
		}
	}

	
	public void retornaTodos() throws SQLException {
		String sql = "SELECT * FROM pessoa";
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
