package weightedGraph;

import java.util.HashMap;
import java.util.Set;

public class WeightedGraph<T> {

	private HashMap<Integer, T> nodes;
	private HashMap<Integer, Edge> edges;
	private int nodeIDGen;
	private int edgeIDGen;

	public WeightedGraph() {
		this.nodeIDGen = 0;
		this.edgeIDGen = 0;
		this.nodes = new HashMap<Integer, T>();
		this.edges = new HashMap<Integer, Edge>();
	}

	public int put(T data) {
		int nodeID = this.nodeIDGen;
		this.nodes.put(this.nodeIDGen, data);
		this.nodeIDGen++;
		return nodeID;
	}

	public T getData(int id) {
		return this.nodes.get(id);
	}

	public Set<Integer> getAllKeys() {
		return this.nodes.keySet();
	}

	/*
	 * public Set<Integer> getNeighboursEdges(int id) { Set<Integer> res; for(Inte)
	 * return this.nodes.get(id).getNeighbourSet(); }
	 */

	public boolean removeNode(int nodeID) {
		if (!this.containsNode(nodeID)) {
			return false;
		}

		for (Integer i : this.edges.keySet()) {
			if (this.edges.get(i).connects(nodeID)) {
				this.edges.remove(i);
			}
		}

		this.nodes.remove(nodeID);
		return true;
	}

	public int setConnection(int nodeA, int nodeB, double weight) {
		if (!this.containsNode(nodeA) || !this.containsNode(nodeB)) {
			throw new RuntimeException("uno de los nodos no existe");
		}

		int edgeID = this.edgeIDGen;
		this.edges.put(edgeID, new Edge(nodeA, nodeB, weight));
		this.edgeIDGen++;
		return edgeID;
	}

	public boolean containsNode(int k) {
		return this.nodes.containsKey(k);
	}

	// -1 si no se encuentra arista
	// 0+ si esta
	public int searchEdge(int nodeA, int nodeB) {
		for (Integer i : this.edges.keySet()) {
			if (this.edges.get(i).areConnected(nodeA, nodeB)) {
				return i;
			}
		}
		return -1;
	}

	public boolean checkEdge(int nodeA, int nodeB) {
		if (!this.containsNode(nodeA)) {
			return false;
		}
		return (this.searchEdge(nodeA, nodeB) >= 0);
	}

	public double getEdgeWeight(int nodeA, int nodeB) {

		int edgeID = this.searchEdge(nodeA, nodeB);

		if (edgeID < 0) {
			throw new RuntimeException("no hay conexion entre los nodos");
		}

		return this.edges.get(edgeID).getWeight();
	}

	public boolean removeConnection(int nodeA, int nodeB) {

		int edgeID = this.searchEdge(nodeA, nodeB);

		if (edgeID < 0) {
			return false;
		}
		this.edges.remove(edgeID);
		return true;
	}
	
	HashMap<Integer, T> getAllNodes(){
		return this.nodes;
	}
	
	HashMap<Integer, Edge> getAllEdges(){
		return this.edges;
	}
}
