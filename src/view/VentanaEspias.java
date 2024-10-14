package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import controller.VentanaEspiasControlador;

import javax.swing.ImageIcon;
import java.awt.Image;
import java.awt.Color;

public class VentanaEspias {

	private JFrame frameAgentes;
	private JTextField fieldNombreAgente;
	private VentanaEspiasControlador controlador;

	public VentanaEspias(VentanaMenu ventanaMenu) {
		this.controlador = new VentanaEspiasControlador(this, ventanaMenu);
		ventanaEspias();
	}

	private void ventanaEspias() {
		frameAgentes = new JFrame("Agregar Agente");
		frameAgentes.setTitle("Agregar Agentes");
		frameAgentes.getContentPane().setBackground(Color.BLACK);
		frameAgentes.getContentPane().setForeground(Color.BLACK);
		frameAgentes.setBounds(100, 100, 300, 300);
		frameAgentes.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frameAgentes.getContentPane().setLayout(new BorderLayout(0, 0));

		JPanel panelCentral = new JPanel();
		frameAgentes.getContentPane().add(panelCentral, BorderLayout.CENTER);
		panelCentral.setLayout(new GridLayout(5, 1, 0, 0));
		ImageIcon imagenAgente = new ImageIcon("docs/Spy.jpg");
		Image image = imagenAgente.getImage();
		Image newimg = image.getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH);
		imagenAgente = new ImageIcon(newimg);
		JLabel labelImagen = new JLabel();
		labelImagen.setHorizontalAlignment(SwingConstants.CENTER);
		labelImagen.setIcon(imagenAgente);
		panelCentral.add(labelImagen);

		JPanel panelDerecho = new JPanel();
		frameAgentes.getContentPane().add(panelDerecho, BorderLayout.EAST);
		panelDerecho.setPreferredSize(new Dimension(50, 90));

		JPanel panelIzquierdo = new JPanel();
		frameAgentes.getContentPane().add(panelIzquierdo, BorderLayout.WEST);
		panelIzquierdo.setPreferredSize(new Dimension(50, 90));

		fieldNombreAgente = new JTextField();
		panelCentral.add(fieldNombreAgente);
		fieldNombreAgente.setColumns(10);
		fieldNombreAgente.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String espia = fieldNombreAgente.getText();
				controlador.agregarEspia(espia);
				fieldNombreAgente.setText("");

			

			}
		});

		JLabel labelVacio = new JLabel("");
		panelCentral.add(labelVacio);

		JPanel panelInferior = new JPanel();
		frameAgentes.getContentPane().add(panelInferior, BorderLayout.SOUTH);

		// LABELS //
		JLabel lblNewLabel = new JLabel("Agrega un Agente");
		lblNewLabel.setForeground(new Color(192, 192, 192));
		// BOTONES //
		JButton botonAgregarAgente = new JButton("Agregar");
		panelCentral.add(botonAgregarAgente);
		botonAgregarAgente.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String espia = fieldNombreAgente.getText();
				controlador.agregarEspia(espia);
				fieldNombreAgente.setText("");

			}

		});

		JLabel labelVacio1 = new JLabel("");
		panelCentral.add(labelVacio1);

		JLabel labelTextoAgregarAgente = new JLabel("Agrega un Agente");
		labelTextoAgregarAgente.setForeground(new Color(255, 255, 255));
		labelTextoAgregarAgente.setHorizontalAlignment(SwingConstants.CENTER);
		labelTextoAgregarAgente.setFont(new Font("Comic Sans MS", Font.BOLD, 30));
		frameAgentes.getContentPane().add(labelTextoAgregarAgente, BorderLayout.NORTH);

		JButton botonVolverAlMenu = new JButton("Volver al Menu");
		panelInferior.add(botonVolverAlMenu);
		botonVolverAlMenu.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				cerrarVentana();

			}

		});

	}

	public void abrirVentana() {
		frameAgentes.setVisible(true);
		
	}

	public void cerrarVentana() {
		frameAgentes.dispose();
	}

	public void setControlador(VentanaEspiasControlador controlador) {
		this.controlador = controlador;

	}

}
