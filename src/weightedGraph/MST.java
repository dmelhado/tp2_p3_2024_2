package weightedGraph;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;

public class MST {

	public static <T> WeightedGraph<T> generateMST(WeightedGraph<T> g) {
		
		if(!g.esConexo()) {
			throw new RuntimeException("No se crear un AGM de un grafo disconexo.");
		}
		
		// generamos un arbol vacio al que le vamos a meter las cosas
		WeightedGraph<T> result = new WeightedGraph<T>();
				
		// traemos toda la data
		HashMap<Integer, T> nodes = g.getAllNodes();
		HashMap<Integer, Edge> edges = g.getAllEdges();
		
		if(nodes.size() == 0) {
			return result;
		}
		
		// llevamos rastro de cuales nodos ya metimos
		HashSet<Integer> setNodes = new HashSet<Integer>();
	
		// obtenemos primer nodo. cualquiera sirve.
		int addedNode = nodes.keySet().iterator().next();
		
		// lo metemos al arbol nuevo
		result.put(g.getData(addedNode));
		setNodes.add(addedNode);
		
		// ciclamos hasta haber agregado todos los nodos
		while(setNodes.size() < nodes.size()) {
			
			// traemos la minima arista disponible
			Edge minimumEdge = minimumAvailableEdge(setNodes, edges.values());
			
			// realmente no sabemos cual es el nodo de la arista que hay que agregar
			if(setNodes.contains(minimumEdge.a())) {
				addedNode = minimumEdge.b();
			}
			if(setNodes.contains(minimumEdge.b())) {
				addedNode = minimumEdge.a();
			}
			
			// agregamos todo
			result.put(g.getData(addedNode));
			result.setConnection(minimumEdge.a(), minimumEdge.b(), minimumEdge.getWeight());
			setNodes.add(addedNode);
		}		
		return result;
		
	}
	
	// devuelve la arista con menor peso que no este conectada a alguno de los nodos
	private static Edge minimumAvailableEdge(HashSet<Integer> nodes, Collection<Edge> allEdges) {
		double minWeight = Double.POSITIVE_INFINITY;
		Edge result = null;
		
		// TODO: esta implementacion es horrible. considerar usar un MinHeap de aristas ordenados por peso en lugar de for's anidados
		for(Edge edge : allEdges) {
			for(Integer n : nodes) {
				
				// si se cumplen estas 3 condiciones, actualizamos el minimo
				boolean isConnected = edge.connects(n);
				boolean otherEndIsNotInCurrentNodes = !nodes.contains(edge.getOtherEnd(n));
				boolean weightLowerThanResult = minWeight > edge.getWeight();
				
				if(isConnected && otherEndIsNotInCurrentNodes && weightLowerThanResult) {
						minWeight = edge.getWeight();
						result = edge;
				}
			}
		}
		return result;
	}

}
