package exemplo.model;

import java.sql.SQLException;

//Define maneiras de acessar dados da tabela filme
//quem implementa esta interface e obrigado a implementar todos os metodos ditos por ela
public interface PessoaDAO {
	public void adicionar(String nome, int idade);
	public void remover(String nome) throws SQLException;
	//public void alterar(Pessoa pessoa);
	
	public void procurarPorNome(String nome) throws SQLException;
	public void procurarPorIdade(int idade) throws SQLException;
	public void retornaTodos() throws SQLException;
	
	
}
