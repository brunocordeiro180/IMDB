package exemplo.view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;

import exemplo.controller.ConnectionFactory;
import net.proteanit.sql.DbUtils;

public class IMDBjanela1 extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	static String title;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					IMDBjanela1 frame = new IMDBjanela1(title);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	Connection con = null;
	private JTable table_1;
	
	//Atualiza tabela apos nova avaliacao
	public void refreshTable(){
		try {
			String query = "select filme.nome,avg(avaliacao) as MediaDeAvaliacoes from filme "
					+ "join usuario_avaliacao on filme.id = usuario_avaliacao.filme_id "
					+ "group by filme.id";
			PreparedStatement pst = con.prepareStatement(query);
			ResultSet rs = pst.executeQuery();
			table_1.setModel(DbUtils.resultSetToTableModel(rs));
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}
	
	/**
	 * Create the frame.
	 * @throws SQLException 
	 */
	public IMDBjanela1(String nome) throws SQLException {
		
		con = new ConnectionFactory().getConnection();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(200, 200, 1500, 1500);
		contentPane = new JPanel();
		contentPane.setBackground(Color.DARK_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JLabel label = new JLabel("");
		label.setBounds(37, 36, 148, 128);
		Image img = new ImageIcon(this.getClass().getResource("/imdb-128.png")).getImage();
		contentPane.setLayout(null);
		label.setIcon(new ImageIcon(img));
		contentPane.add(label);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(27, 296, 614, 175);
		contentPane.add(scrollPane);

		table = new JTable();
		scrollPane.setViewportView(table);

		JButton CarregaTabela = new JButton("Mostrar filmes");
		CarregaTabela.setBounds(27, 262, 160, 25);
		CarregaTabela.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String query = "select filme.nome,genero,ano,duracao, pessoa.nome "
							+ "from filme,pessoa where pessoa.id = filme.diretor;";
					PreparedStatement pst = con.prepareStatement(query);
					ResultSet rs = pst.executeQuery();
					table.setModel(DbUtils.resultSetToTableModel(rs));
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				refreshTable();
			}
		});
		contentPane.add(CarregaTabela);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(37, 568, 395, 153);
		contentPane.add(scrollPane_2);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_2.setViewportView(scrollPane_1);
		
		table_1 = new JTable();
		scrollPane_1.setViewportView(table_1);
		refreshTable();
		//calcula media das avaliacoes que os usuarios deram nos filmes
//		try {
//			String query = "select filme.nome,avg(avaliacao) as MediaDeAvaliacoes from filme "
//					+ "join usuario_avaliacao on filme.id = usuario_avaliacao.filme_id "
//					+ "group by filme.id";
//			PreparedStatement pst = con.prepareStatement(query);
//			pst.execute();
//		} catch (Exception e1) {
//			e1.printStackTrace();
//		}
		
		JLabel lblAvaliacoes = new JLabel("Avaliacoes");
		lblAvaliacoes.setBounds(45, 546, 90, 15);
		contentPane.add(lblAvaliacoes);
		
		JButton btnRealizarAvaliacao = new JButton("Realizar avaliacao");
		btnRealizarAvaliacao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JanelaAvaliacao thirdbox = null;
				try {
					thirdbox = new JanelaAvaliacao(nome);
					thirdbox.setVisible(true);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				thirdbox.setVisible(true);
				
			}
			
		});
		
		btnRealizarAvaliacao.setBounds(466, 541, 175, 25);
		contentPane.add(btnRealizarAvaliacao);
		
		//acessa janela VerAtores
		JButton acessar_atores = new JButton("Ver Atores");
		acessar_atores.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VerAtores AvaliacaoBox = null;
				try {
					AvaliacaoBox = new VerAtores();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				AvaliacaoBox.setVisible(true);
				AvaliacaoBox.setVisible(true);
			}
		});
		acessar_atores.setBounds(481, 605, 160, 30);
		contentPane.add(acessar_atores);
		
	}
}
