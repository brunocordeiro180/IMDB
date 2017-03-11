package exemplo.view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;

import exemplo.controller.ConnectionFactory;

public class JanelaAvaliacao extends JFrame {

	private JPanel contentPane;
	private JTextField nota;
	private JComboBox<String> comboBox;
	Connection con = null;
	int id_filme = 1;
	static String title;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JanelaAvaliacao frame = new JanelaAvaliacao(title);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	
	public void fillComboBox(JComboBox<String> comboBox) throws SQLException{
		try {//realiza query para colocar no comboBox os nomes das pessoas 
			con = new ConnectionFactory().getConnection();
			String query = "select nome from filme";
			PreparedStatement pst = con.prepareStatement(query);
			ResultSet rs = pst.executeQuery();
			
			while(rs.next()){
				String titulo = rs.getString("nome");		
				comboBox.addItem(titulo);
		
			}
			
		} catch (SQLException e) {
			System.out.println(e);
		}finally{
			con.close();
		}
		
		//return comboBox;
	}
	
	
	/**
	 * Create the frame.
	 * @throws SQLException 
	 */
	public JanelaAvaliacao(String nome) throws SQLException {
		
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(Color.ORANGE);
		contentPane.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		nota = new JTextField();
		nota.setBounds(103, 106, 218, 26);

		contentPane.add(nota);
		nota.setColumns(10);
		
		comboBox = new JComboBox<String>();
		comboBox.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				con = new ConnectionFactory().getConnection();
				
				String query = "select id from filme where nome = ?";
				ResultSet rs;
				try {
					PreparedStatement p =con.prepareStatement(query);
					p.setString(1, (String)comboBox.getSelectedItem());
					rs = p.executeQuery();
					while(rs.next()){
						id_filme =(rs.getInt("id"));
					}
					
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		comboBox.setBounds(126, 70, 162, 16);
		contentPane.add(comboBox);
		
		fillComboBox(comboBox);
		
		JButton btnOk = new JButton("ok");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				con = new ConnectionFactory().getConnection();
			
				int avaliacao = Integer.parseInt(nota.getText());
	
				String query2 = "select id from usuario where login = ?";
				
				
				ResultSet rs;
				System.out.println(nome);
				try {
					PreparedStatement pstm2 = con.prepareStatement(query2);
					pstm2.setString(1, nome);
					rs = pstm2.executeQuery();
					int id_usuario = 0, count = 0;
					
					while (rs.next()){
						id_usuario = (rs.getInt(1));
					}
					
					//realiza query para mostrar filmes nos quais a pessoa escolhida atuou
					String validacao = "select filme_id from usuario_avaliacao"
							+ " where usuario_id = ? and filme_id = ?";
					PreparedStatement pvalida = con.prepareStatement(validacao);
					pvalida.setInt(1, id_usuario);
					pvalida.setInt(2, id_filme);
					ResultSet rvalida = pvalida.executeQuery();
					while (rvalida.next()){
						count++;
					}
					if(count >= 1){
						JOptionPane.showMessageDialog(contentPane, "Usuario ja realizou avaliacao nesse filme");
					}
					else{
						String queryinsert = "insert into usuario_avaliacao (usuario_id, filme_id, avaliacao)"
								+ "values(?,?,?)";
						PreparedStatement pstm3 = con.prepareStatement(queryinsert);
						pstm3.setInt(1, id_usuario);
						pstm3.setInt(2, id_filme);
						pstm3.setInt(3, avaliacao);
						
						pstm3.execute();
						
						
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
				
			}
		});
		btnOk.setBounds(160, 156, 117, 25);
		contentPane.add(btnOk);
		
		
		
		 
	}
}
