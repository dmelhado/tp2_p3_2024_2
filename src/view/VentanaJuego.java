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
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.table.DefaultTableModel;

import controller.Observer;
import controller.VentanaJuegoControlador;
import model.Edge;
import model.WeightedGraph;

import java.awt.FlowLayout;
import javax.swing.JLabel;

public class VentanaJuego extends JPanel implements Observer {

	private static final long serialVersionUID = 1L;
	private WeightedGraph<String> grafo;
	private WeightedGraph<String> mst;
	private JTable tabla;
	private DefaultTableModel modeloTabla;
	private JPanel panelCentral;
	private VentanaJuegoControlador controlador;

	public VentanaJuego(WeightedGraph<String> grafo) {
		this.grafo = grafo;
		this.grafo.añadirObservador(this);
		iniciarJuego();
	}

	public void iniciarJuego() {
		removeAll();
		setName("Temible Operario del RecontraEspionaje");
		setBounds(100, 100, 640, 480);
		setLayout(new BorderLayout());

		JPanel panel = new JPanel();
		add(panel, BorderLayout.SOUTH);
		panel.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));

		JLabel lblNewLabel = new JLabel("                       ");
		panel.add(lblNewLabel);

		JButton btnNewButton = new JButton("Generar Arbol Minimo");
		panel.add(btnNewButton);
		btnNewButton.setPreferredSize(new Dimension(170, 40));

		JPanel panelDerecho = new JPanel();
		add(panelDerecho, BorderLayout.EAST);
		panelDerecho.setPreferredSize(new Dimension(200, 200));
		panelDerecho.setLayout(new BorderLayout(0, 0));

		JPanel panel_1 = new JPanel();
		panelDerecho.add(panel_1, BorderLayout.SOUTH);

		JLabel lblNewLabel_1 = new JLabel("Riesgo Minimo: ");
		panel_1.add(lblNewLabel_1);

		JPanel panelSuperior = new JPanel();
		add(panelSuperior, BorderLayout.NORTH);
		panelSuperior.setPreferredSize(new Dimension(200, 50));

		modeloTabla = new DefaultTableModel(new Object[] { "Arista", "Probabilidad" }, 0);

		tabla = new JTable(modeloTabla);
		JScrollPane scrollPane = new JScrollPane(tabla);
		panelDerecho.add(scrollPane, BorderLayout.CENTER);
		scrollPane.setPreferredSize(new Dimension(250, 150));

		panelCentral = new JPanel() {
			private static final long serialVersionUID = 1L;

			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				dibujarGrafo(g);
			}
		};
		add(panelCentral, BorderLayout.CENTER);
		btnNewButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controlador.calcularMST();
				double riesgoMinimo = controlador.calcularRiesgoMinimo();
				lblNewLabel_1.setText("Riesgo Minimo: " + String.format("%.2f", riesgoMinimo));
				panelCentral.revalidate();
				panelCentral.repaint();
			}

		});

	}

	public void actualizarGrafo(WeightedGraph<String> nuevoGrafo) {
		this.grafo = nuevoGrafo;
		repaint();
	}

	public void dibujarGrafo(Graphics g) {
		super.paintComponent(g);
		Graphics2D dibujo = (Graphics2D) g;
		dibujo.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		// TODO: Verlo como un metodo, donde poner
		if (grafo == null || grafo.tamaño() == 0) {
			return;
		}
		dibujo.setStroke(new BasicStroke(2.0f));

		int ancho = panelCentral.getWidth();
		int altura = panelCentral.getHeight();
		HashMap<Integer, int[]> coordenadas = controlador.calcularCoordenadas(ancho, altura);

		// Dibuja nodos
		for (int i = 0; i < grafo.tamaño(); i++) {
			int[] coords = coordenadas.get(i);
			dibujo.fillOval(coords[0], coords[1], 20, 20); // Tamaño nodo
			dibujo.drawString(grafo.getData(i), coords[0], coords[1] - 10); // Etiqueta del nodo
		}

		// Dibuja aristas
		for (int i = 0; i < grafo.tamaño(); i++) {
			for (int j = 0; j < grafo.tamaño(); j++) {
				if (grafo.consultarArista(i, j)) {
					int[] coordsA = coordenadas.get(i);
					int[] coordsB = coordenadas.get(j);
					dibujo.drawLine(coordsA[0] + 10, coordsA[1] + 10, coordsB[0] + 10, coordsB[1] + 10);
					dibujo.drawString(String.format("%.2f", grafo.getPesoArista(i, j)), (coordsA[0] + coordsB[0]) / 2,
							(coordsA[1] + coordsB[1]) / 2);
				}
			}
		}

		// Dibuja MST
		if (mst != null) {
			dibujo.setColor(Color.RED);
			dibujo.setStroke(new BasicStroke(5.0f));
			for (Integer idArista : mst.getTodasLasAristas().keySet()) {
				Edge arista = mst.getTodasLasAristas().get(idArista);
				int nodoA = arista.a();
				int nodoB = arista.b();
				dibujo.drawLine(coordenadas.get(nodoA)[0] + 10, coordenadas.get(nodoA)[1] + 10,
						coordenadas.get(nodoB)[0] + 10, coordenadas.get(nodoB)[1] + 10);
			}
		}
	}

	public void actualizarTablaMST() {
		modeloTabla.setRowCount(0);
		if (mst != null) {
			for (Integer idArista : mst.getTodasLasAristas().keySet()) {
				Edge arista = mst.getTodasLasAristas().get(idArista);
				String nodoA = grafo.getData(arista.a());
				String nodoB = grafo.getData(arista.b());
				double probabilidad = arista.getPeso();

				modeloTabla.addRow(new Object[] { nodoA + "-" + nodoB, String.format("%.2f", probabilidad) });
			}
		}
	}

	@Override
	public void actualizar() {
		repaint();
		actualizarTablaMST();

	}

	public void setControlador(VentanaJuegoControlador controlador) {
		this.controlador = controlador;
	}

	public void setMST(WeightedGraph<String> nuevoMST) {
		this.mst = nuevoMST;
		repaint();
	}

}