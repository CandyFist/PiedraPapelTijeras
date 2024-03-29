package gui;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import bd.MovRegistros;
import bd.ConexionDB;
import modelo.Jugador;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.JRadioButton;



public class Registro {


	Connection conexion;
	private JFrame frame;
	private JTextField nombre;
	public static Jugador jug;
	private JPasswordField contraseņa;
	private JTextField contraseņaV;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Registro window = new Registro();
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
	public Registro() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		

		ConexionDB.createConnection();
		conexion = ConexionDB.getConnection();
		
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.WHITE);
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblAcceso = new JLabel("Acceso");
		lblAcceso.setBounds(193, 11, 57, 14);
		frame.getContentPane().add(lblAcceso);
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setHorizontalAlignment(SwingConstants.CENTER);
		lblNombre.setBackground(Color.WHITE);
		lblNombre.setBounds(75, 66, 86, 14);
		frame.getContentPane().add(lblNombre);
		
		JLabel lblContrasea = new JLabel("Contrase\u00F1a");
		lblContrasea.setHorizontalAlignment(SwingConstants.CENTER);
		lblContrasea.setBounds(254, 66, 86, 14);
		frame.getContentPane().add(lblContrasea);
		
		nombre = new JTextField();
		nombre.setBounds(75, 91, 86, 20);
		frame.getContentPane().add(nombre);
		nombre.setColumns(10);
		
		contraseņa = new JPasswordField();
		contraseņa.setBounds(254, 91, 86, 20);
		frame.getContentPane().add(contraseņa);

		JLabel lblUsuarioDesconocido = new JLabel("");
		lblUsuarioDesconocido.setBounds(205, 41, 185, 14);
		frame.getContentPane().add(lblUsuarioDesconocido);
		
		JButton btnLogIn = new JButton("Log in");
		btnLogIn.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent arg0) {
				Jugador j = null;
				if (contraseņa.isVisible()){
				j = new Jugador(nombre.getText(), contraseņa.getText());
				System.out.println(contraseņa.getText());
				}
				else if (contraseņaV.isVisible()) {
				j = new Jugador(nombre.getText(), contraseņaV.getText());
				System.out.println(contraseņaV.getText());
				}
				else {
					System.out.println("ERROR EN LA VISIBILIDAD DE LOS CAMPOS CONTRASEŅA");
				}
				MovRegistros.loguearse(conexion, j, lblUsuarioDesconocido, frame);
				inicializarValores();
			}
		});
		btnLogIn.setBounds(163, 144, 89, 23);
		frame.getContentPane().add(btnLogIn);
		
		JButton btnRegistro = new JButton("Registro");
		btnRegistro.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent arg0) {
				Jugador j = null;
				if (contraseņa.isVisible()){
				j = new Jugador(nombre.getText(), contraseņa.getText());
				System.out.println(contraseņa.getText());
				}
				else if (contraseņaV.isVisible()) {
				j = new Jugador(nombre.getText(), contraseņaV.getText());
				System.out.println(contraseņaV.getText());
				}
				else {
					System.out.println("ERROR EN LA VISIBILIDAD DE LOS CAMPOS CONTRASEŅA");
				}
				MovRegistros.crear(conexion, j, lblUsuarioDesconocido);
				inicializarValores();
			}
		});
		btnRegistro.setBounds(163, 186, 89, 23);
		frame.getContentPane().add(btnRegistro);
		
		JRadioButton rdbtnDestapar = new JRadioButton("Destapar");
		rdbtnDestapar.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent arg0) {
				contraseņaV.setVisible(true);
				contraseņa.setVisible(false);
				contraseņaV.setText(contraseņa.getText());
			}
		});
		rdbtnDestapar.setBounds(347, 102, 109, 23);
		frame.getContentPane().add(rdbtnDestapar);
		
		JRadioButton rdbtnTapar = new JRadioButton("Tapar");
		rdbtnTapar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				contraseņa.setVisible(true);
				contraseņaV.setVisible(false);
				contraseņa.setText(contraseņaV.getText());
			}
		});
		rdbtnTapar.setBounds(346, 76, 109, 23);
		frame.getContentPane().add(rdbtnTapar);
		rdbtnTapar.setSelected(true);
		
	    //Agrupar los rdButton
	    ButtonGroup group = new ButtonGroup();
	    group.add(rdbtnDestapar);
	    group.add(rdbtnTapar);
	    
	    contraseņaV = new JTextField();
	    contraseņaV.setBounds(254, 91, 86, 20);
	    frame.getContentPane().add(contraseņaV);
	    contraseņaV.setColumns(10);
		
	}
	
	

	
	private void inicializarValores()
	{
		nombre.setText("");
		contraseņa.setText("");
		contraseņaV.setText("");
	}
}
