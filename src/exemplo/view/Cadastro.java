package exemplo.view;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import exemplo.controller.ConnectionFactory;
import javax.swing.JLabel;

public class Cadastro extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JPasswordField passwordField;
	private Connection con;
	private JLabel lblNome;
	private JLabel lblEmail;
	private JLabel lblSenha;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Cadastro frame = new Cadastro();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Cadastro() {
		
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(193, 47, 195, 19);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(193, 106, 191, 19);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(193, 159, 195, 19);
		contentPane.add(passwordField);
		
		JButton btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String query = "insert into usuario (login, email, senha) "
						+ "values (?,?,?)";
				PreparedStatement pst;
				
				try {
					con = new ConnectionFactory().getConnection();
					pst = con.prepareStatement(query);
					pst.setString(1, textField.getText());
					pst.setString(2, textField_1.getText());
					pst.setString(3, passwordField.getText());
					pst.execute();
					JOptionPane.showMessageDialog(contentPane, "Cadastro realizado com sucesso");
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnCadastrar.setBounds(154, 227, 117, 25);
		contentPane.add(btnCadastrar);
		
		lblNome = new JLabel("nome");
		lblNome.setBounds(107, 49, 70, 15);
		contentPane.add(lblNome);
		
		lblEmail = new JLabel("email");
		lblEmail.setBounds(105, 108, 70, 15);
		contentPane.add(lblEmail);
		
		lblSenha = new JLabel("senha");
		lblSenha.setBounds(107, 161, 70, 15);
		contentPane.add(lblSenha);
	}

}
