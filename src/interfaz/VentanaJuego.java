package interfaz;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.HashMap;

import javax.swing.JPanel;

import weightedGraph.WeightedGraph;

public class VentanaJuego extends JPanel {

	private static final long serialVersionUID = 1L;
	private WeightedGraph<String> grafo;

	public VentanaJuego (WeightedGraph<String> grafo) {
		this.grafo = grafo;
		iniciarJuego();
	}

	public void iniciarJuego() {
		removeAll();
		setName("Temible Operario del RecontraEspionaje");
		setBounds(100, 100, 640, 480);
		setLayout(new BorderLayout());

	}

	public void crearGrafo(String[] espias, String[][] conexiones) {
		this.grafo = new WeightedGraph<>();
		HashMap<String, Integer> nodos = new HashMap<>();
		for (String espia : espias) {
			int idNodo = grafo.put(espia);
			nodos.put(espia, idNodo);
		}

		for (String[] conexion : conexiones) {
			String espia1 = conexion[0];
			String espia2 = conexion[1];
			double probabilidad = Double.parseDouble(conexion[2]);

			int nodoA = nodos.get(espia1);
			int nodoB = nodos.get(espia2);

			grafo.setConnection(nodoA, nodoB, probabilidad);
		}
		repaint();

	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D dibujo = (Graphics2D) g;
		dibujo.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		// TODO: Verlo como un metodo, donde poner
		if (grafo == null || grafo.tamaño() == 0) {
			return;
		}

		// TODO : Coordenadas para los nodos pero esta limitado a 5. Ver como hacer para
		// poner los que sea
		int[][] coordenadas = { { 100, 100 }, { 200, 200 }, { 300, 100 }, { 400, 200 }, { 500, 100 } };

		// Dibuja nodos
		for (int i = 0; i < grafo.tamaño(); i++) {
			dibujo.fillOval(coordenadas[i][0], coordenadas[i][1], 20, 20); // Tamaño nodo
			dibujo.drawString(grafo.getData(i), coordenadas[i][0], coordenadas[i][1] - 10); // Etiqueta del nodo
		}

		// Dibuja conexiones (aristas)
		for (int i = 0; i < grafo.tamaño(); i++) {
			for (int j = 0; j < grafo.tamaño(); j++) {
				if (grafo.checkEdge(i, j)) {
					dibujo.drawLine(coordenadas[i][0] + 15, coordenadas[i][1] + 15, coordenadas[j][0] + 15,
							coordenadas[j][1] + 15);
					dibujo.drawString(String.format("%.2f", grafo.getEdgeWeight(i, j)),
							(coordenadas[i][0] + coordenadas[j][0]) / 2, (coordenadas[i][1] + coordenadas[j][1]) / 2); // Peso
																														// de
																														// la
																														// arista
				}
			}
		}
	}
}
