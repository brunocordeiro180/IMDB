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
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;

import exemplo.controller.ConnectionFactory;
import net.proteanit.sql.DbUtils;

public class VerAtores extends JFrame {

	private JPanel contentPane;
	private Connection con = null;
	private int id_pessoa;
	private JTable table;
	private JButton btnCarregaTabela;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VerAtores frame = new VerAtores();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public void fillComboBox(JComboBox<String> comboBox) throws SQLException{
		try {
			con = new ConnectionFactory().getConnection();
			String query = "select nome from pessoa";
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
	public VerAtores() throws SQLException {
		
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(Color.ORANGE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JComboBox comboBox = new JComboBox();
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				con = new ConnectionFactory().getConnection();
				
				String query = "select id from pessoa where nome = ?";
				ResultSet rs;
				try {
					PreparedStatement p =con.prepareStatement(query);
					p.setString(1, (String)comboBox.getSelectedItem());
					rs = p.executeQuery();
					while(rs.next()){
						id_pessoa =(rs.getInt("id"));
					}
					
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
			
		});
		comboBox.setBounds(12, 31, 182, 24);
		contentPane.add(comboBox);
		
		fillComboBox(comboBox);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(251, 31, 164, 180);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		btnCarregaTabela = new JButton("Carrega Tabela");
		btnCarregaTabela.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String query1 = "select filme.nome from filme "
						+ "join ator_filme on ator_id = ? and filme.id = ator_filme.filme_id "
						+ "group by filme.nome;";
				ResultSet rs1;
				
				try {
					PreparedStatement p =con.prepareStatement(query1);
					p.setInt(1, id_pessoa);
					rs1 = p.executeQuery();
					
					table.setModel(DbUtils.resultSetToTableModel(rs1));
					
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnCarregaTabela.setBounds(28, 128, 164, 25);
		contentPane.add(btnCarregaTabela);
	}
}
