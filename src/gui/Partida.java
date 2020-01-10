package gui;

import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;

import bd.ConexionDB;
import bd.MovPartida;
import javax.swing.SwingConstants;
import java.awt.Font;

public class Partida {

	private JFrame frame;
	private JTextField movmaq;
	int movMaquina;
	private JTextField resultado;
	Connection conexion;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Partida window = new Partida();
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
	public Partida() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	public void initialize() {
		
		ConexionDB.createConnection();
		conexion = ConexionDB.getConnection();
		
		frame = new JFrame();
		frame.setBounds(100, 100, 652, 509);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblEligeJugada = new JLabel("Elige jugada");
		lblEligeJugada.setFont(new Font("Sylfaen", Font.PLAIN, 18));
		lblEligeJugada.setHorizontalAlignment(SwingConstants.CENTER);
		lblEligeJugada.setBounds(10, 11, 616, 23);
		frame.getContentPane().add(lblEligeJugada);
		
		JButton btnPiedra = new JButton("Piedra");
		btnPiedra.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				MovPartida.resolucion(conexion, 2, resultado, movmaq, movMaquina, Registro.jug);
			}
		});
		btnPiedra.setBounds(175, 69, 89, 23);
		frame.getContentPane().add(btnPiedra);
		
		JButton button = new JButton("Papel");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
                System.out.println(Registro.jug);
				MovPartida.resolucion(conexion, 1, resultado, movmaq, movMaquina, Registro.jug);
                System.out.println("Papel2");
			}
		});
		button.setBounds(175, 157, 89, 23);
		frame.getContentPane().add(button);
		
		JButton button_1 = new JButton("Tijeras");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MovPartida.resolucion(conexion, 3, resultado, movmaq, movMaquina, Registro.jug);
			}
		});
		button_1.setBounds(175, 244, 89, 23);
		frame.getContentPane().add(button_1);
		
		JLabel lblJugadaDelContrincante = new JLabel("Jugada del contrincante");
		lblJugadaDelContrincante.setHorizontalAlignment(SwingConstants.CENTER);
		lblJugadaDelContrincante.setBounds(355, 73, 166, 14);
		frame.getContentPane().add(lblJugadaDelContrincante);
		
		movmaq = new JTextField();
		movmaq.setFont(new Font("Tahoma", Font.PLAIN, 16));
		movmaq.setBounds(355, 98, 166, 35);
		frame.getContentPane().add(movmaq);
		movmaq.setColumns(10);
		
		resultado = new JTextField();
		resultado.setFont(new Font("Sylfaen", Font.PLAIN, 26));
		resultado.setBounds(355, 244, 166, 50);
		frame.getContentPane().add(resultado);
		resultado.setColumns(10);
		
		JLabel lblPiedra = new JLabel("");
		Image piedra = new ImageIcon (this.getClass().getResource("/rock.JPG")).getImage().getScaledInstance(52, 45, Image.SCALE_DEFAULT);
		lblPiedra.setIcon(new ImageIcon(piedra));
		lblPiedra.setBounds(106, 45, 52, 62);
		frame.getContentPane().add(lblPiedra);
		
		JLabel lblPapel = new JLabel("");
		Image papel = new ImageIcon (this.getClass().getResource("/paper.JPG")).getImage().getScaledInstance(52, 45, Image.SCALE_DEFAULT);
		lblPapel.setIcon(new ImageIcon(papel));
		lblPapel.setBounds(106, 131, 59, 74);
		frame.getContentPane().add(lblPapel);
		
		JLabel lblTijeras = new JLabel("");
		Image tijeras = new ImageIcon (this.getClass().getResource("/scissors.JPG")).getImage().getScaledInstance(52, 45, Image.SCALE_DEFAULT);
		lblTijeras.setIcon(new ImageIcon(tijeras));
		lblTijeras.setBounds(106, 216, 59, 74);
		frame.getContentPane().add(lblTijeras);
		
		JButton btnVerRatio = new JButton("Ver ratio");
		btnVerRatio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "Tu ratio de partidas ganadas es: "+MovPartida.ratio(conexion));
			}
		});
		btnVerRatio.setFont(new Font("Sylfaen", Font.PLAIN, 24));
		btnVerRatio.setBounds(226, 380, 158, 50);
		frame.getContentPane().add(btnVerRatio);
	}
}
