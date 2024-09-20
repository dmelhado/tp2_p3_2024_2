package weightedGraph;

import java.util.HashMap;
import java.util.Set;

public class WeightedGraph<T> {

	private HashMap<Integer, Node<T>> nodes;
	private int idGen;

	public WeightedGraph() {
		this.idGen = 0;
		this.nodes = new HashMap<Integer, Node<T>>();
	}

	public int put(T data) {
		int nodeID = this.idGen;
		this.nodes.put(this.idGen, new Node<T>(data));
		this.idGen++;
		return nodeID;
	}

	public T getData(int id) {
		return this.nodes.get(id).getData();
	}

	public Set<Integer> getNeighbours(int id) {
		return this.nodes.get(id).getNeighbourSet();
	}

	public boolean removeNode(int nodeID) {
		if (!this.containsNode(nodeID)) {
			return false;
		}
		for (Integer i : this.getNeighbours(nodeID)) {
			this.nodes.get(i).removeEdge(nodeID);
		}
		this.nodes.remove(nodeID);
		return true;
	}

	public boolean setConnection(int nodeA, int nodeB, double weight) {
		if (!this.checkEdge(nodeA, nodeB)) {
			return false;
		}
		this.nodes.get(nodeA).setEdge(nodeB, weight);
		this.nodes.get(nodeB).setEdge(nodeA, weight);
		return true;
	}

	public boolean containsNode(int k) {
		return this.nodes.containsKey(k);
	}

	public boolean checkEdge(int nodeA, int nodeB) {
		return this.containsNode(nodeA) && this.nodes.get(nodeA).hasConnectionTo(nodeB);
	}

	public double getEdgeWeight(int nodeA, int nodeB) {
		return this.nodes.get(nodeA).getEdgeWeight(nodeB);
	}

	public boolean removeConnection(int nodeA, int nodeB) {
		if (!this.checkEdge(nodeA, nodeB)) {
			return false;
		}
		this.nodes.get(nodeA).removeEdge(nodeB);
		this.nodes.get(nodeB).removeEdge(nodeA);
		return true;
	}

}
