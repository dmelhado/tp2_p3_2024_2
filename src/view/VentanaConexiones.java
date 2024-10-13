package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Hashtable;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import controller.VentanaConexionesControlador;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VentanaConexiones {

	private JFrame frame;
	private VentanaMenu ventanaMenu;
	private JComboBox<String> comboBoxEspia1;
	private JComboBox<String> comboBoxEspia2;
	private VentanaConexionesControlador controlador;

	public VentanaConexiones(VentanaMenu ventanaMenu) {
		this.ventanaMenu = ventanaMenu;
		ventanaConexiones();
	}

	private void ventanaConexiones() {

		frame = new JFrame();
		frame.setBounds(100, 100, 400, 400);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));

		// PANELES //
		JPanel panelIzquierdo = new JPanel();
		frame.getContentPane().add(panelIzquierdo, BorderLayout.WEST);
		panelIzquierdo.setPreferredSize(new Dimension(50, 100));

		JPanel panelDerecho = new JPanel();
		frame.getContentPane().add(panelDerecho, BorderLayout.EAST);
		panelDerecho.setPreferredSize(new Dimension(50, 100));

		JPanel panelInferior = new JPanel();
		frame.getContentPane().add(panelInferior, BorderLayout.SOUTH);

		JPanel panelCentral = new JPanel();
		frame.getContentPane().add(panelCentral, BorderLayout.CENTER);
		panelCentral.setLayout(new GridLayout(6, 0, 0, 0));

		JLabel labelVacio = new JLabel("");
		panelCentral.add(labelVacio);

		JPanel panelComboBoxEspia = new JPanel();
		panelCentral.add(panelComboBoxEspia);
		panelComboBoxEspia.setLayout(new GridLayout(2, 2, 0, 0));
		
				JLabel labelSeleccionarEspia1 = new JLabel("Seleccione Espia 1");
				labelSeleccionarEspia1.setHorizontalAlignment(SwingConstants.CENTER);
				panelComboBoxEspia.add(labelSeleccionarEspia1);
		
				JLabel labelSeleccionarEspia2 = new JLabel("Seleccione Espia 2");
				labelSeleccionarEspia2.setHorizontalAlignment(SwingConstants.CENTER);
				panelComboBoxEspia.add(labelSeleccionarEspia2);

		comboBoxEspia1 = new JComboBox<String>();
		panelComboBoxEspia.add(comboBoxEspia1);

		comboBoxEspia2 = new JComboBox<String>();
		panelComboBoxEspia.add(comboBoxEspia2);

		JPanel panelTexto = new JPanel();
		panelCentral.add(panelTexto);
		panelTexto.setLayout(new GridLayout(0, 1, 0, 0));

		JSlider sliderProbabilidad = new JSlider(0, 100, 50);
		sliderProbabilidad.setPaintTicks(true);
		sliderProbabilidad.setPaintLabels(true);

		panelCentral.add(sliderProbabilidad);

		JPanel panelTextoSlider = new JPanel();
		panelCentral.add(panelTextoSlider);
		panelTextoSlider.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JPanel panelBoton = new JPanel();
		panelCentral.add(panelBoton);
		panelBoton.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		// LABELS //
		JLabel labelSuperiorTexto = new JLabel("Asigna las Conexiones");
		labelSuperiorTexto.setHorizontalAlignment(SwingConstants.CENTER);
		labelSuperiorTexto.setFont(new Font("Comic Sans MS", Font.BOLD, 30));
		frame.getContentPane().add(labelSuperiorTexto, BorderLayout.NORTH);

		JLabel labelProbabilidad = new JLabel("Ingrese Probabilidad");
		labelProbabilidad.setVerticalAlignment(SwingConstants.BOTTOM);
		labelProbabilidad.setHorizontalAlignment(SwingConstants.CENTER);
		panelTexto.add(labelProbabilidad);

		JLabel labelSliderProbabilidad = new JLabel(" " + sliderProbabilidad.getValue() / 100.0);
		panelTextoSlider.add(labelSliderProbabilidad);
		sliderProbabilidad.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				double decimalValue = sliderProbabilidad.getValue() / 100.0;
				labelSliderProbabilidad.setText(" " + decimalValue);
			}
		});

		Hashtable<Integer, JLabel> labelTable = new Hashtable<>();
		labelTable.put(0, new JLabel("0"));
		labelTable.put(100, new JLabel("1"));
		sliderProbabilidad.setLabelTable(labelTable);

		// BOTONES //
		JButton botonVolverAlMenu = new JButton("Volver al Menu");
		botonVolverAlMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});
		panelInferior.add(botonVolverAlMenu);

		JButton botonAgregarArista = new JButton("Agregar Arista");
		botonAgregarArista.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String espia1 = (String) comboBoxEspia1.getSelectedItem();
				String espia2 = (String) comboBoxEspia2.getSelectedItem();
				String probabilidadTexto = labelSliderProbabilidad.getText();
				controlador.agregarArista(espia1, espia2, probabilidadTexto);
			}
		});
		panelBoton.add(botonAgregarArista);
	}

	public void abrirVentana() {
		actualizarComboBoxes();
		frame.setVisible(true);
	}

	public void actualizarComboBoxes() {
		ArrayList<String> listaEspias = ventanaMenu.obtenerEspias();
		DefaultComboBoxModel<String> espia1 = new DefaultComboBoxModel<>(listaEspias.toArray(new String[0]));
		DefaultComboBoxModel<String> espia2 = new DefaultComboBoxModel<>(listaEspias.toArray(new String[0]));

		comboBoxEspia1.setModel(espia1);
		comboBoxEspia2.setModel(espia2);
	}

	public void limpiarComboBox() {
		comboBoxEspia1.removeAllItems();
		comboBoxEspia2.removeAllItems();
	}

	public void setControlador(VentanaConexionesControlador controlador) {
		this.controlador = controlador;
		
	}
}
