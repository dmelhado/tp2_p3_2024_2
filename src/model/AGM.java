package model;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;

public class AGM {

	public static <T> GrafoPonderado<T> obtenerAGM(GrafoPonderado<T> g) {

		if (!g.esConexo()) {
			throw new RuntimeException("No se puede crear un AGM de un grafo disconexo.");
		}

		// generamos un arbol vacio al que le vamos a meter las cosas
		GrafoPonderado<T> resultado = new GrafoPonderado<T>();

		// traemos toda la data
		HashMap<Integer, T> nodos = g.getTodosLosNodos();
		HashMap<Integer, Arista> aristas = g.getTodasLasAristas();

		if (nodos.size() == 0) {
			return resultado;
		}

		// llevamos rastro de cuales nodos ya metimos
		HashSet<Integer> setNodos = new HashSet<Integer>();

		// obtenemos primer nodo. cualquiera sirve.
		int nodoAñadido = nodos.keySet().iterator().next();

		// lo metemos al arbol nuevo
		resultado.put(g.getData(nodoAñadido));
		setNodos.add(nodoAñadido);

		// ciclamos hasta haber agregado todos los nodos
		while (setNodos.size() < nodos.size()) {

			Arista mejorArista = buscarMejorArista(setNodos, aristas.values());

			// realmente no sabemos cual es el nodo de la arista que hay que agregar
			if (setNodos.contains(mejorArista.a())) {
				nodoAñadido = mejorArista.b();
			}
			if (setNodos.contains(mejorArista.b())) {
				nodoAñadido = mejorArista.a();
			}

			// agregamos todo
			resultado.put(g.getData(nodoAñadido));
			resultado.setConexion(mejorArista.a(), mejorArista.b(), mejorArista.getPeso());
			setNodos.add(nodoAñadido);
		}
		return resultado;
	}

	// devuelve la arista con menor peso que esta conectada a un solo nodo agregado
	private static Arista buscarMejorArista(HashSet<Integer> nodos, Collection<Arista> todasLasAristas) {
		double pesoMinimo = Double.POSITIVE_INFINITY;
		Arista resultado = null;

		for (Arista arista : todasLasAristas) {
			for (Integer n : nodos) {

				// si se cumplen estas 3 condiciones, actualizamos el minimo
				boolean estaConectado = arista.connects(n);
				boolean otroExtremoNoEstaEnNodosActuales = !nodos.contains(arista.getOtroExtremo(n));
				boolean pesoInferiorAlResultado = pesoMinimo > arista.getPeso();

				if (estaConectado && otroExtremoNoEstaEnNodosActuales && pesoInferiorAlResultado) {
					pesoMinimo = arista.getPeso();
					resultado = arista;
				}
			}
		}
		return resultado;
	}

}
