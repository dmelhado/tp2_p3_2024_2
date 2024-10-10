package controller;

import java.util.HashMap;

import model.Edge;
import model.MST;
import model.WeightedGraph;
import view.VentanaJuego;

public class VentanaJuegoControlador {
	private WeightedGraph<String> grafo;
	private WeightedGraph<String> mst;
	private VentanaJuego ventana;
	
	public VentanaJuegoControlador(WeightedGraph<String> grafo, VentanaJuego ventana)
	{
		this.grafo = new WeightedGraph<String>();
		this.ventana = ventana;
		this.ventana.setControlador(this);
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

		ventana.actualizarGrafo(grafo);

	}
	
	 public HashMap<Integer, int[]> calcularCoordenadas(int ancho, int altura) {
	        HashMap<Integer, int[]> coordenadas = new HashMap<>();
	        int tamañoNodos = grafo.tamaño();
	        double radio = Math.min(ancho, altura) / 3;
	        double centerX = ancho / 2.0;
	        double centerY = altura / 2.0;

	        for (int i = 0; i < tamañoNodos; i++) {
	            double angle = 2 * Math.PI * i / tamañoNodos;
	            int x = (int) (centerX + radio * Math.cos(angle));
	            int y = (int) (centerY + radio * Math.sin(angle));
	            coordenadas.put(i, new int[] { x, y });
	        }

	        return coordenadas;
	    }
	
	public void calcularMST()
	{
		if (grafo != null && grafo.esConexo()) {
			mst = MST.generateMST(grafo);
			ventana.setMST(mst);
			ventana.actualizarTablaMST();
			System.out.println(grafo);
		} else {
			mst = null;
		}
	}
	
	public void agregarNodo(String espia)
	{
		grafo.put(espia);
	}
	
	public void agregarConexion(String espia1, String espia2, double probabilidad) {
        int nodoA = grafo.put(espia1);
        int nodoB = grafo.put(espia2);
        grafo.setConexiones(nodoA, nodoB, probabilidad);
    }
	
	public double calcularRiesgoMinimo()
	{
		if (mst == null)
			return 0.0;
		double riesgoMinimo = 0.0;
		for (Integer idArista : mst.getTodasLasAristas().keySet())
		{
			Edge arista = mst.getTodasLasAristas().get(idArista);
			riesgoMinimo += arista.getPeso();
		}
		return riesgoMinimo;
	}

}
