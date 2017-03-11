package exemplo.model;

import java.sql.SQLException;

public interface UsuarioDAO {
	public void adicionar(String login, String senha, String email) throws SQLException;
	public void remover(String login);
	//public void alterar(Usuario usuario);
	
	public boolean procurarPorLogin(String login) throws SQLException;
	public boolean procurarPorSenha(String senha) throws SQLException;
	public void procurarPorEmail(String email) throws SQLException;
	public void retornaTodos() throws SQLException;
}
