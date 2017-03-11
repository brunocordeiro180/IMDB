package exemplo.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import exemplo.controller.ConnectionFactory;

public class FilmeDAOmemoria implements FilmeDAO {

	
	private Connection con;
	
	@Override   //Adiciona uma linha na tabela filme
	public void adicionar(String nome, String genero, int ano, int duracao) throws SQLException {
		
		con = null; //Inicializa variavel que realiza a conexao
		PreparedStatement stmt = null;
		
		//Query insert
		String postgresql = "insert into filme " + "(nome,genero,ano, duracao) " + "values (?,?,?,?)";

		
		try {
			//Conexao aberta
			con = new ConnectionFactory().getConnection();
			stmt = con.prepareStatement(postgresql);
			
			//Coloca os dados passados no metodo para a query
			stmt.setString(1, nome); 
			stmt.setString(2, genero);
			stmt.setInt(3, ano);
			stmt.setDouble(4, duracao);
			//Pedro pedro = new Pedro(pedro);

			stmt.execute();
		} catch (SQLException e) {
			System.out.println(e);
		} finally {
			if (stmt != null) {

				stmt.close();
			}
			//fecha conexao
			if (con != null) {
				con.close();
			}
		}
	}

	@Override //realiza query delete na tabela filme 
	public void remover(String nome) throws SQLException {
		
		String sql = "DELETE FROM filme WHERE id = ?";

		con = null; //inicializa variavel
		PreparedStatement pstm = null;
		
		try {
			
			//abre conexao atraves do localhost:5432
			con = new ConnectionFactory().getConnection();

			pstm = con.prepareStatement(sql); //lendo query

			pstm.setString(1, nome); //Coloca o dado passado no metodo para a query

			pstm.execute(); //executa a query

		} catch (Exception e) {
			System.out.println(e);
		} finally {
			if (pstm != null) {

				pstm.close();
			}

			if (con != null) {
				//fecha conexao
				con.close();
			}
		}

	}

	@Override //metodo que tem como funcao realizar select na tabela filme
	public void procurarPorNome(String nome) throws SQLException {
		
		
		String sql = "SELECT nome FROM filme WHERE nome = ?";
		con = null;
		PreparedStatement pstm = null;
		
		try {
			con = new ConnectionFactory().getConnection();//pega conexao na porta 5432

			pstm = con.prepareStatement(sql);
			
			pstm.setString(1, nome);
			
			ResultSet rs = pstm.executeQuery(); //executa query
			
			//laco de repeticao para imprimir os filmes com o nome passado no metodo
			while (rs.next()) {
				  String Name = rs.getString("nome");
				  System.out.println(Name);
			}
			
		}catch (SQLException e){
			System.out.println(e);
		} finally{
			pstm.close();
			con.close();
		}
		
	}

	@Override
	public void procurarPorAno(int ano) throws SQLException {
		
		String sql = "SELECT nome,ano FROM filme WHERE ano = ? order by nome";
		con = null;
		PreparedStatement pstm = null;
		
		try {
			con = new ConnectionFactory().getConnection();

			pstm = con.prepareStatement(sql);
			
			pstm.setInt(1, ano);
			
			ResultSet rs = pstm.executeQuery();
			
			while (rs.next()) {
				  String Name = rs.getString("nome");
				  int Ano = rs.getInt("ano");
				  System.out.println(Name + ", "+Ano);
			}
			
		}catch (SQLException e){
			System.out.println(e);
		} finally{
			pstm.close();
			con.close();
		}
		
	}

	@Override
	public void procurarPorDiretor(String nome) throws SQLException {

		
		
		String sql = "SELECT nome,ano FROM filme WHERE diretor = ? order by nome";
		con = null;
		PreparedStatement pstm = null;
		
		try {
			con = new ConnectionFactory().getConnection();

			pstm = con.prepareStatement(sql);
			
			pstm.setString(1, nome);
			
			ResultSet rs = pstm.executeQuery();
			
			while (rs.next()) {
				  String Name = rs.getString("nome");
				  int Ano = rs.getInt("ano");
				  System.out.println(Name + ", "+Ano);
			}
			
		}catch (SQLException e){
			System.out.println(e);
		} finally{
			pstm.close();
			con.close();
		}
	}

	@Override //realiza select para imprimir todos os dados da tabela
	public void retornaTodos() throws SQLException {
		String sql = "SELECT * from filme";
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
