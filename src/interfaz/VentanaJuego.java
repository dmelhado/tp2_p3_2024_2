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

	public VentanaJuego(WeightedGraph<String> grafo) {
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

			grafo.setConexiones(nodoA, nodoB, probabilidad);
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

		int tamañoNodos = grafo.tamaño();
		int ancho = getWidth();
		int altura = getHeight();

		int[][] coordenadas = new int[tamañoNodos][2];
		int radio = Math.min(ancho, altura) / 3;
		double centerX = ancho / 2.0;
		double centerY = altura / 2.0;
		
		for (int i = 0; i < tamañoNodos; i++) {
			double angle = 2 * Math.PI * i / tamañoNodos;
			coordenadas[i][0] = (int) (centerX + radio * Math.cos(angle));
			coordenadas[i][1] = (int) (centerY + radio * Math.sin(angle));
		}
		// Dibuja nodos
		for (int i = 0; i < grafo.tamaño(); i++) {
			dibujo.fillOval(coordenadas[i][0], coordenadas[i][1], 20, 20); // Tamaño nodo
			dibujo.drawString(grafo.getData(i), coordenadas[i][0], coordenadas[i][1] - 10); // Etiqueta del nodo
		}
		// Dibuja conexiones (aristas)
		for (int i = 0; i < tamañoNodos; i++) {
			for (int j = 0; j < tamañoNodos; j++) {
				if (grafo.consultarArista(i, j)) {
					dibujo.drawLine(coordenadas[i][0] + 10, coordenadas[i][1] + 10, coordenadas[j][0] + 10,
							coordenadas[j][1] + 10);
					dibujo.drawString(String.format("%.2f", grafo.getPesoArista(i, j)),
							(coordenadas[i][0] + coordenadas[j][0]) / 2, (coordenadas[i][1] + coordenadas[j][1]) / 2);
				}
			}
		}
	
	}
	
	
	
}
