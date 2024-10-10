package model;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;

public class MST {

	public static <T> WeightedGraph<T> generateMST(WeightedGraph<T> g) {
		
		if(!g.esConexo()) {
			throw new RuntimeException("No se crear un AGM de un grafo disconexo.");
		}
		
		// generamos un arbol vacio al que le vamos a meter las cosas
		WeightedGraph<T> resultado = new WeightedGraph<T>();
				
		// traemos toda la data
		HashMap<Integer, T> nodos = g.getTodosLosNodos();
		HashMap<Integer, Edge> aristas = g.getTodasLasAristas();
		
		if(nodos.size() == 0) {
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
		while(setNodos.size() < nodos.size()) {
			
			// traemos la minima arista disponible
			Edge aristaMinima = minimumAvailableEdge(setNodos, aristas.values());
			
			// realmente no sabemos cual es el nodo de la arista que hay que agregar
			if(setNodos.contains(aristaMinima.a())) {
				nodoAñadido = aristaMinima.b();
			}
			if(setNodos.contains(aristaMinima.b())) {
				nodoAñadido = aristaMinima.a();
			}
			
			// agregamos todo
			resultado.put(g.getData(nodoAñadido));
			resultado.setConexiones(aristaMinima.a(), aristaMinima.b(), aristaMinima.getPeso());			setNodos.add(nodoAñadido);
		}		
		return resultado;	
	}
	
	// devuelve la arista con menor peso que no este conectada a alguno de los nodos
	private static Edge minimumAvailableEdge(HashSet<Integer> nodos, Collection<Edge> todasLasAristas) {
		double pesoMinimo = Double.POSITIVE_INFINITY;
		Edge resultado = null;
		
		// TODO: esta implementacion es horrible. considerar usar un MinHeap de aristas ordenados por peso en lugar de for's anidados
		for(Edge arista : todasLasAristas) {
			for(Integer n : nodos) {
				
				// si se cumplen estas 3 condiciones, actualizamos el minimo
				boolean estaConectado = arista.connects(n);
				boolean otroExtremoNoEstaEnNodosActuales = !nodos.contains(arista.getOtroExtremo(n));
				boolean pesoInferiorAlResultado = pesoMinimo > arista.getPeso();
				
				if(estaConectado && otroExtremoNoEstaEnNodosActuales && pesoInferiorAlResultado) {
						pesoMinimo = arista.getPeso();
						resultado = arista;
				}
			}
		}
		return resultado;
	}

}
