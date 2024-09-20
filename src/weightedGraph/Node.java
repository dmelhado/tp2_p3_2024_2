package weightedGraph;

import java.util.HashMap;
import java.util.Set;

public class Node<T> {

	private T data;
	private HashMap<Integer, Double> edges;

	public Node(T inData) {
		this.setData(inData);
		this.edges = new HashMap<Integer, Double>();
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public void setEdge(int nodeID, double weight) {
		this.edges.put(nodeID, weight);
	}

	public void removeEdge(int nodeID) {
		this.edges.remove(nodeID);
	}

	public boolean hasConnectionTo(int destinyNode) {
		return this.edges.containsKey(destinyNode);
	}

	public double getEdgeWeight(int destinyNode) {
		if (!this.hasConnectionTo(destinyNode)) {
			throw new RuntimeException("No hay conexion a ese destino");
		}
		return this.edges.get(destinyNode);
	}

	public Set<Integer> getNeighbourSet() {
		return this.edges.keySet();
	}

}
