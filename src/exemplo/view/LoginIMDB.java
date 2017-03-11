package exemplo.view;

import java.awt.EventQueue;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import exemplo.model.UsuarioDAOmemoria;
import javax.swing.JPanel;
import java.awt.Color;


public class LoginIMDB extends JFrame {

	
	private JFrame frame;
	private JTextField username;
	private JLabel lblSenha;
	private JPasswordField passwordfield;
	private JButton btnLogin;

	
	/**
	 * Launch the application.
	 * @throws SQLException 
	 */
	public static void main(String[] args) throws SQLException {
		//con.close();
		EventQueue.invokeLater(new Runnable() {
			
			public void run() {
				try {
					LoginIMDB window = new LoginIMDB();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public LoginIMDB() {
		initialize();
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		
		frame.getContentPane().setLayout(null);
		
		
		JLabel Username = new JLabel("Username");
		Username.setBounds(50, 109, 81, 26);
		frame.getContentPane().add(Username);
		
		//cria campo de texto para o login
		username = new JTextField();
		username.setBounds(231, 113, 143, 19);
		frame.getContentPane().add(username);
		username.setColumns(10);
		
		lblSenha = new JLabel("Senha");
		lblSenha.setBounds(61, 171, 70, 15);
		frame.getContentPane().add(lblSenha);
		
		//cria campo de texto para senha
		passwordfield = new JPasswordField();
		passwordfield.setBounds(231, 169, 143, 19);
		frame.getContentPane().add(passwordfield);
		
		//botao para confirmar login
		btnLogin = new JButton("login");
		btnLogin.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				String name = username.getText();
				String password = passwordfield.getText();
				System.out.println(name);
				System.out.println(password);
				UsuarioDAOmemoria u = new UsuarioDAOmemoria();
			
				//String realname = user.getLogin();
				try { //verifica se os dados passados se encontram no banco de dados
					if (u.procurarPorLogin(name) && u.procurarPorSenha(password)){
						
						JOptionPane.showMessageDialog(frame, "Login realizado com sucesso");
						IMDBjanela1 secondbox = new IMDBjanela1(name);
						secondbox.setVisible(true);
						frame.setVisible(false);
						
					}
					else{ //se o usuario ou a senha estiverem incorretos uma caixa de alerta é colocada na tela
						JOptionPane.showMessageDialog(frame, "Usuario ou senha incorretos");
					}
				} catch (HeadlessException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			
			}
			
			
		});
		btnLogin.setBounds(164, 200, 125, 15);
		frame.getContentPane().add(btnLogin);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.YELLOW);
		panel.setBounds(25, 12, 413, 32);
		frame.getContentPane().add(panel);
		
		JButton btnCadastrar = new JButton("cadastrar");
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				Cadastro thirdBox = new Cadastro( );
				thirdBox.setVisible(true);
				
			}
		});
		btnCadastrar.setBounds(164, 227, 117, 25);
		frame.getContentPane().add(btnCadastrar);
		
		
	}
}
