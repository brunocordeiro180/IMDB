package exemplo.model;

import java.sql.SQLException;

// Define maneiras de acessar dados da tabela filme
public interface FilmeDAO {
	
	public void adicionar(String nome, String genero, int ano, int duracao) throws SQLException;
	public void remover(String nome) throws SQLException;
	public void procurarPorNome(String nome) throws SQLException;
	public void procurarPorAno(int ano) throws SQLException;
	public void procurarPorDiretor(String nome) throws SQLException;
	
	public void retornaTodos() throws SQLException;
	
	
}
