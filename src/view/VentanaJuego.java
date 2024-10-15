package view;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.table.DefaultTableModel;
import controller.ObservadorInterfaz;
import controller.VentanaJuegoControlador;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class VentanaJuego extends JPanel implements ObservadorInterfaz {

	private static final long serialVersionUID = 1L;

	private JTable tabla;
	private DefaultTableModel modeloTabla;
	private JPanel panelCentral;
	private VentanaJuegoControlador controlador;
	private JLabel labelCuelloDeBotella;

	public VentanaJuego(VentanaJuegoControlador controlador) {
		this.controlador = controlador;
		iniciarJuego();
	}

	public void iniciarJuego() {
		removeAll();
		setName("Temible Operario del RecontraEspionaje");
		setBounds(100, 100, 640, 480);
		setLayout(new BorderLayout());

		JPanel panelInferior = new JPanel();
		add(panelInferior, BorderLayout.SOUTH);
		panelInferior.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JButton botonGenerarArbolMinimo = new JButton("Generar Arbol Minimo");
		panelInferior.add(botonGenerarArbolMinimo);
		botonGenerarArbolMinimo.setPreferredSize(new Dimension(170, 40));

		JPanel panelDerecho = new JPanel();
		add(panelDerecho, BorderLayout.EAST);
		panelDerecho.setPreferredSize(new Dimension(200, 200));
		panelDerecho.setLayout(new BorderLayout(0, 0));

		JPanel panelTexto = new JPanel();
		panelDerecho.add(panelTexto, BorderLayout.SOUTH);

		labelCuelloDeBotella = new JLabel("Cuello de Botella: ");
		panelTexto.add(labelCuelloDeBotella);

		JPanel panelSuperior = new JPanel();
		add(panelSuperior, BorderLayout.NORTH);
		panelSuperior.setPreferredSize(new Dimension(200, 50));

		modeloTabla = new DefaultTableModel(new Object[] { "Arista", "Probabilidad" }, 0);

		tabla = new JTable(modeloTabla);
		JScrollPane scrollPaneAristasArbolMinimo = new JScrollPane(tabla);
		panelDerecho.add(scrollPaneAristasArbolMinimo, BorderLayout.CENTER);
		scrollPaneAristasArbolMinimo.setPreferredSize(new Dimension(250, 150));

		panelCentral = new JPanel() {
			private static final long serialVersionUID = 1L;

			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				dibujarGrafo(g);
			}
		};
		add(panelCentral, BorderLayout.CENTER);
		botonGenerarArbolMinimo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controlador.calcularMST();
				double cuelloDeBotella = controlador.calcularCuelloDeBotella();
				labelCuelloDeBotella.setText("Cuello de Botella: " + String.format("%.2f", cuelloDeBotella));
				panelCentral.revalidate();
				panelCentral.repaint();
			}

		});

	}

	public void dibujarGrafo(Graphics g) {
		super.paintComponent(g);
		Graphics2D dibujo = (Graphics2D) g;
		dibujo.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		dibujo.setStroke(new BasicStroke(2.0f));

		int ancho = panelCentral.getWidth();
		int altura = panelCentral.getHeight();
		HashMap<Integer, int[]> coordenadas = controlador.calcularCoordenadas(ancho, altura);
		dibujarNodos(dibujo, coordenadas);
		dibujarAristas(dibujo, coordenadas);
		dibujarMST(dibujo, coordenadas);
	}

	public void dibujarNodos(Graphics dibujo, HashMap<Integer, int[]> coordenadas) {
		for (int i = 0; i < coordenadas.size(); i++) {
			int[] coords = coordenadas.get(i);
			dibujo.fillOval(coords[0], coords[1], 20, 20); // Tamaño nodo
			dibujo.drawString(controlador.getNombreEspia(i), coords[0], coords[1] - 10); // Etiqueta del nodo
		}
	}

	public void dibujarAristas(Graphics dibujo, HashMap<Integer, int[]> coordenadas) {
		List<int[]> aristasDatos = controlador.obtenerDatosAristas();
		for (int[] arista : aristasDatos) {

			int nodoA = arista[0];
			int nodoB = arista[1];
			int[] coordsA = coordenadas.get(nodoA);
			int[] coordsB = coordenadas.get(nodoB);
			dibujo.drawLine(coordsA[0] + 10, coordsA[1] + 10, coordsB[0] + 10, coordsB[1] + 10);
			dibujo.drawString(controlador.getProbabilidad(nodoA, nodoB), (coordsA[0] + coordsB[0]) / 2,
					(coordsA[1] + coordsB[1]) / 2);
		}

	}

	public void dibujarMST(Graphics dibujo, HashMap<Integer, int[]> coordenadas) {
		if (coordenadas.isEmpty())
			return;
		dibujo.setColor(Color.RED);
		((Graphics2D) dibujo).setStroke(new BasicStroke(5.0f));
		List<int[]> mstDatos = controlador.obtenerDatosMST();
		for (int[] arista : mstDatos) {
			int nodoA = arista[0];
			int nodoB = arista[1];
			int[] coordsA = coordenadas.get(nodoA);
			int[] coordsB = coordenadas.get(nodoB);
			if (coordsA != null && coordsB != null) {
				dibujo.drawLine(coordenadas.get(nodoA)[0] + 10, coordenadas.get(nodoA)[1] + 10,
						coordenadas.get(nodoB)[0] + 10, coordenadas.get(nodoB)[1] + 10);
			}
		}

	}

	public void actualizarTablaMST(Object[][] datos) {
		modeloTabla.setRowCount(0);
		for (Object[] fila : datos) {
			modeloTabla.addRow(fila);
		}
	}

	public void limpiarTabla() {
		modeloTabla.setRowCount(0);
		labelCuelloDeBotella.setText("Cuello de Botella: ");

	}

	@Override
	public void actualizar() {
		repaint();

	}

	public void setControlador(VentanaJuegoControlador controlador) {
		this.controlador = controlador;
	}

	public void mostrarMensajeNoEsConexo() {
		JOptionPane.showMessageDialog(this,
				"No se puede generar un Arbol Minimo. La Red es disconexa.", "Error",
				JOptionPane.ERROR_MESSAGE);
		return;
		
	}

}