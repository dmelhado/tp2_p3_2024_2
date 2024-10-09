package interfaz;

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

import weightedGraph.Edge;
import weightedGraph.MST;
import weightedGraph.WeightedGraph;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import java.awt.GridLayout;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.JLabel;

public class VentanaJuego extends JPanel {

	private static final long serialVersionUID = 1L;
	private WeightedGraph<String> grafo;
	private WeightedGraph<String> mst;
	private JTable tabla;
	private DefaultTableModel modeloTabla;
	private JPanel panelCentral;

	public VentanaJuego(WeightedGraph<String> grafo) {
		this.grafo = grafo;
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
				
				JLabel lblNewLabel_1 = new JLabel("Riesgo Minimo:");
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
				calcularMST();
				panelCentral.revalidate();
				panelCentral.repaint();
			}

		});

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

	public void dibujarGrafo(Graphics g) {
		super.paintComponent(g);
		Graphics2D dibujo = (Graphics2D) g;
		dibujo.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		// TODO: Verlo como un metodo, donde poner
		if (grafo == null || grafo.tamaño() == 0) {
			return;
		}
		dibujo.setStroke(new BasicStroke(2.0f));
		

		int tamañoNodos = grafo.tamaño();
		int ancho = panelCentral.getWidth();
		int altura = panelCentral.getHeight();

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

		if (mst != null) {
			dibujo.setColor(Color.RED);
			dibujo.setStroke(new BasicStroke(5.0f));
			for (Integer idArista : mst.getTodasLasAristas().keySet()) {
				Edge arista = mst.getTodasLasAristas().get(idArista);
				int nodoA = arista.a();
				int nodoB = arista.b();
				dibujo.drawLine(coordenadas[nodoA][0] + 10, coordenadas[nodoA][1] + 10, coordenadas[nodoB][0] + 10,
						coordenadas[nodoB][1] + 10);

			}

		}

	}

	public void calcularMST() {
		if (grafo != null && grafo.esConexo()) {
			mst = MST.generateMST(grafo);
			actualizarTablaMST();
			System.out.println(grafo);
		} else {
			mst = null;
		}
	}



	public void actualizarTablaMST()
	{
		modeloTabla.setRowCount(0);
		if (mst != null)
		{
			for (Integer idArista : mst.getTodasLasAristas().keySet())
			{
				Edge arista = mst.getTodasLasAristas().get(idArista);
				String nodoA = grafo.getData(arista.a());
				String nodoB = grafo.getData(arista.b());
				double probabilidad = arista.getPeso();
				
				modeloTabla.addRow(new Object[] { nodoA + "-" + nodoB, String.format("%.2f", probabilidad) });
			}
		}
	}
}