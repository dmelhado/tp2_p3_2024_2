package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import controller.VentanaMenuControlador;

public class VentanaMenu extends JPanel {
	private static final long serialVersionUID = 1L;

	private JTable tablaConexiones;
	private DefaultTableModel modeloTabla;
	private DefaultTableModel modeloTablaEspias;
	private JTable tablaNombreEspias;
	private ArrayList<String> listaEspias;
	private VentanaMenuControlador controlador;

	public VentanaMenu(PantallaPrincipal pantallaPrincipal) {
		this.listaEspias = new ArrayList<>();
		this.controlador = new VentanaMenuControlador(this, pantallaPrincipal);
		MenuJuego();

	}

	public void MenuJuego() {
		removeAll();
		repaint();
		setBounds(100, 100, 640, 480);
		setLayout(new BorderLayout(0, 0));

		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
		// PANELES y SCROLLSPANE
		JPanel panelSuperior = new JPanel();
		add(panelSuperior, BorderLayout.NORTH);

		JPanel panelCentral = new JPanel();
		add(panelCentral, BorderLayout.CENTER);
		panelCentral.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JPanel panelInferior = new JPanel();
		add(panelInferior, BorderLayout.SOUTH);

		JPanel panelIzquierdo = new JPanel();
		add(panelIzquierdo, BorderLayout.WEST);
		panelIzquierdo.setPreferredSize(new Dimension(200, 100));
		panelIzquierdo.setLayout(new BorderLayout(0, 0));

		JPanel panelDerecho = new JPanel();
		add(panelDerecho, BorderLayout.EAST);
		panelDerecho.setLayout(new BorderLayout(0, 0));

		JPanel panelBoton1 = new JPanel();
		panelDerecho.add(panelBoton1, BorderLayout.SOUTH);
		panelBoton1.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JPanel panelBoton = new JPanel();
		panelIzquierdo.add(panelBoton, BorderLayout.SOUTH);

		modeloTablaEspias = new DefaultTableModel(new Object[] { "Espías" }, 0);
		tablaNombreEspias = new JTable(modeloTablaEspias);
		JScrollPane scrollPaneNombreEspias = new JScrollPane(tablaNombreEspias);
		panelIzquierdo.add(scrollPaneNombreEspias, BorderLayout.CENTER);
		scrollPaneNombreEspias.setPreferredSize(new Dimension(600, 150));
		tablaNombreEspias.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);

		JLabel labelTitulo = new JLabel("Temible Operario del recontraespionaje");
		labelTitulo.setFont(new Font("Comic Sans MS", Font.BOLD, 30));
		panelSuperior.add(labelTitulo);

		modeloTabla = new DefaultTableModel(new Object[] { "Arista", "Probabilidad" }, 0);

		tablaConexiones = new JTable(modeloTabla);
		JScrollPane scrollPaneConexionesEspias = new JScrollPane(tablaConexiones);
		panelDerecho.add(scrollPaneConexionesEspias, BorderLayout.CENTER);
		scrollPaneConexionesEspias.setPreferredSize(new Dimension(250, 150));
		tablaConexiones.getColumnModel().getColumn(0).setPreferredWidth(110);
		tablaConexiones.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
		tablaConexiones.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);

		// BOTONES
		JButton botonAgregarEspias = new JButton("Agregar Espias");
		panelBoton.add(botonAgregarEspias);
		botonAgregarEspias.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controlador.abrirVentanaAgentes();
			}

		});

		JButton botonLimpiarTablas = new JButton("Limpiar Tablas");
		botonLimpiarTablas.setPreferredSize(new Dimension(150, 50));
		botonLimpiarTablas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controlador.limpiarTablas();
			}
		});
		panelInferior.add(botonLimpiarTablas);

		JLabel labelVacio = new JLabel("                              ");
		panelInferior.add(labelVacio);

		JButton botonCrearGrafo = new JButton("Dibujar Grafo");
		botonCrearGrafo.setPreferredSize(new Dimension(150, 50));
		panelInferior.add(botonCrearGrafo);

		JButton botonAgregarConexiones = new JButton("Agregar Conexiones");
		panelBoton1.add(botonAgregarConexiones);
		botonAgregarConexiones.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controlador.abrirVentanaConexiones();
			}

		});
		botonCrearGrafo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controlador.crearGrafo();

			}
		});
	}

	public void agregarTablaEspias(String nombreEspia) {

		DefaultTableModel modeloTablaEspias = (DefaultTableModel) tablaNombreEspias.getModel();
		modeloTablaEspias.addRow(new Object[] { nombreEspia });
		listaEspias.add(nombreEspia);
		controlador.actualizarComboBoxes();

	}

	public ArrayList<String> obtenerEspias() {
		return listaEspias;
	}

	public void agregarTablaAristaProbabilidad(String espia1, String espia2, String probabilidad) {
		DefaultTableModel modeloTablaAristas = (DefaultTableModel) tablaConexiones.getModel();
		modeloTablaAristas.addRow(new Object[] { espia1 + "-" + espia2, probabilidad });

	}

	public void limpiarTablas() {

		modeloTabla.setRowCount(0);
		modeloTablaEspias.setRowCount(0);
		listaEspias.clear();

	}

	public void mostrarMensajeNoConexiones() {
		JOptionPane.showMessageDialog(this,
				"No se puede generar un Arbol Minimo. La Red es disconexa.", "Error",
				JOptionPane.ERROR_MESSAGE);
		return;

	}

	public void mostrarMensajeVerificacionEspia(String nombreEspia) {
		JOptionPane.showMessageDialog(this, "El espía '" + nombreEspia + "' ya existe, ingrese otro.", "Error",
				JOptionPane.WARNING_MESSAGE);
		return;
	}

	public void setControlador(VentanaMenuControlador controlador) {
		this.controlador = controlador;
	}

	public DefaultTableModel getModeloTabla() {
		return modeloTabla;
	}

	public ArrayList<String> getListaEspias() {
		return listaEspias;
	}

	public VentanaMenuControlador getControlador() {
		return controlador;
	}

}