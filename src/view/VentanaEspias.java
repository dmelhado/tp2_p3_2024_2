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

	private JFrame frameEspias;
	private JTextField fieldNombreEspia;
	private VentanaEspiasControlador controlador;

	public VentanaEspias(VentanaMenu ventanaMenu) {
		this.controlador = new VentanaEspiasControlador(this, ventanaMenu);
		ventanaEspias();
	}

	private void ventanaEspias() {
		frameEspias = new JFrame("Agregar Espia");
		frameEspias.setTitle("Agregar Espias");
		frameEspias.getContentPane().setBackground(Color.BLACK);
		frameEspias.getContentPane().setForeground(Color.BLACK);
		frameEspias.setBounds(100, 100, 300, 300);
		frameEspias.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frameEspias.getContentPane().setLayout(new BorderLayout(0, 0));

		JPanel panelCentral = new JPanel();
		frameEspias.getContentPane().add(panelCentral, BorderLayout.CENTER);
		panelCentral.setLayout(new GridLayout(5, 1, 0, 0));
		ImageIcon imagenEspia = new ImageIcon("docs/Spy.jpg");
		Image image = imagenEspia.getImage();
		Image newimg = image.getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH);
		imagenEspia = new ImageIcon(newimg);
		JLabel labelImagen = new JLabel();
		labelImagen.setHorizontalAlignment(SwingConstants.CENTER);
		labelImagen.setIcon(imagenEspia);
		panelCentral.add(labelImagen);

		JPanel panelDerecho = new JPanel();
		frameEspias.getContentPane().add(panelDerecho, BorderLayout.EAST);
		panelDerecho.setPreferredSize(new Dimension(50, 90));

		JPanel panelIzquierdo = new JPanel();
		frameEspias.getContentPane().add(panelIzquierdo, BorderLayout.WEST);
		panelIzquierdo.setPreferredSize(new Dimension(50, 90));

		fieldNombreEspia = new JTextField();
		panelCentral.add(fieldNombreEspia);
		fieldNombreEspia.setColumns(10);
		fieldNombreEspia.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String espia = fieldNombreEspia.getText();
				controlador.agregarEspia(espia);
				fieldNombreEspia.setText("");

			}
		});

		JLabel labelVacio = new JLabel("");
		panelCentral.add(labelVacio);

		JPanel panelInferior = new JPanel();
		frameEspias.getContentPane().add(panelInferior, BorderLayout.SOUTH);

		JButton botonAgregarEspia = new JButton("Agregar");
		panelCentral.add(botonAgregarEspia);
		botonAgregarEspia.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String espia = fieldNombreEspia.getText();
				controlador.agregarEspia(espia);
				fieldNombreEspia.setText("");

			}

		});

		JLabel labelVacio1 = new JLabel("");
		panelCentral.add(labelVacio1);

		JLabel labelTextoAgregarEspia = new JLabel("Agrega un Espia");
		labelTextoAgregarEspia.setForeground(new Color(255, 255, 255));
		labelTextoAgregarEspia.setHorizontalAlignment(SwingConstants.CENTER);
		labelTextoAgregarEspia.setFont(new Font("Comic Sans MS", Font.BOLD, 30));
		frameEspias.getContentPane().add(labelTextoAgregarEspia, BorderLayout.NORTH);

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
		frameEspias.setVisible(true);

	}

	public void cerrarVentana() {
		frameEspias.dispose();
	}

	public void setControlador(VentanaEspiasControlador controlador) {
		this.controlador = controlador;

	}

}
