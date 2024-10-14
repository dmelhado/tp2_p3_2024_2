package controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import model.Arista;
import model.AGM;
import model.GrafoPonderado;
import view.VentanaJuego;

public class VentanaJuegoControlador {
	private GrafoPonderado<String> grafo;
	private GrafoPonderado<String> mst;
	private HashMap<Integer, int[]> coordenadas = new HashMap<>();
	private VentanaJuego ventana;
	
	public VentanaJuegoControlador(GrafoPonderado<String> grafo, VentanaJuego ventana)
	{
		this.grafo = new GrafoPonderado<String>();
		this.mst = new GrafoPonderado<String>();
		this.ventana = ventana;
		this.ventana.setControlador(this);
	}
	
	public void crearGrafo(String[] espias, String[][] conexiones) {
		this.grafo = new GrafoPonderado<>();		
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

	}
	
	 public HashMap<Integer, int[]> calcularCoordenadas(int ancho, int altura) {
	        int tamañoNodos = grafo.tamaño();
	        if (tamañoNodos == 0)
	        {
	        	return coordenadas;
	        }
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
	 
	 public List<int[]> obtenerDatosAristas() {
			List<int[]> aristasDatos = new ArrayList<>();
			for(int i = 0; i < grafo.tamaño(); i++) {
				for (int j = i + 1; j < grafo.tamaño(); j++) {
					if (grafo.consultarArista(i, j))
					{
						int[] datos = {i, j};
						aristasDatos.add(datos);
					}
				}
			}
			return aristasDatos;
		}
	 
	 public List<int[]> obtenerDatosMST() {
			List<int[]> mstDatos = new ArrayList<>();
			if (mst != null) {
				for (Arista arista : mst.getTodasLasAristas().values()) {
					int[] datos = { arista.a(), arista.b() };
					mstDatos.add(datos);
				} 
			}
			return mstDatos;
		}
	 
	
	public void calcularMST()
	{
		if (grafo != null && grafo.esConexo()) {
			mst = AGM.generateMST(grafo);
			
			Object[][] datos = new Object[mst.getTodasLasAristas().size()][2];
			int puntero = 0;
			for (Integer idArista : mst.getTodasLasAristas().keySet())
			{
				Arista arista = mst.getTodasLasAristas().get(idArista);
				String nodoA = grafo.getData(arista.a());
				String nodoB = grafo.getData(arista.b());
				double probabilidad = arista.getPeso();
				datos[puntero++] = new Object[] { nodoA + "-" + nodoB, String.format("%.2f", probabilidad) };

			}
			ventana.actualizarTablaMST(datos);
			ventana.actualizar();
			
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
	
	public double calcularCuelloDeBotella()
	{
		if (mst == null)
			return 0.0;
		double cuelloDeBotella = 0.0;
		cuelloDeBotella = grafo.obtenerMaximoPeso();
		return cuelloDeBotella;
	}

	public String getNombreNodo(int i) {
		return grafo.getData(i);
	}


	public String getPesoArista(int nodoA, int nodoB) {
		
		return String.format("%.2f", grafo.getPesoArista(nodoA, nodoB));
	}
	
	public VentanaJuego getVentanaJuego() {
	    return ventana;
	}

	public void limpiarTodo() {
		grafo = new GrafoPonderado<String>();
		mst = new GrafoPonderado<String>();
		
	}
		
}
