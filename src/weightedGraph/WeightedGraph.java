package weightedGraph;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class WeightedGraph<T> {

	private HashMap<Integer, T> nodos;
	private HashMap<Integer, Edge> aristas;
	private int generadorIdNodo;
	private int generadorIdArista;

	public WeightedGraph() {
		this.generadorIdNodo = 0;
		this.generadorIdArista = 0;
		this.nodos = new HashMap<Integer, T>();
		this.aristas = new HashMap<Integer, Edge>();
	}

	public int put(T data) {
		int idNodo = this.generadorIdNodo;
		this.nodos.put(idNodo, data);
		this.generadorIdNodo++;
		return idNodo;
	}

	public T getData(int id) {
		if(!this.contieneNodo(id)) {
			throw new RuntimeException("No se encuentra ese elemento.");
		}
		return this.nodos.get(id);
	}

	public Set<Integer> getAllKeys() {
		return this.nodos.keySet();
	}
	/*
	 * public Set<Integer> getNeighboursEdges(int id) { Set<Integer> res; for(Inte)
	 * return this.nodes.get(id).getNeighbourSet(); }
	 */

	public boolean eliminarNodo(int nodeID) {
		if (!this.contieneNodo(nodeID)) {
			return false;
		}

		for (Integer i : this.aristas.keySet()) {
			if (this.aristas.get(i).connects(nodeID)) {
				this.aristas.remove(i);
			}
		}

		this.aristas.remove(nodeID);
		return true;
	}

	public int setConexiones(int nodeA, int nodeB, double weight) {
		//TODO: Chequear esto, ya que con el if tira error al generar el AGS
	//	if (!this.contieneNodo(nodeA) || !this.contieneNodo(nodeB)) {
	//		throw new RuntimeException("Uno de los nodos no existe");
	//	}

		int idArista = this.generadorIdArista;
		this.aristas.put(idArista, new Edge(nodeA, nodeB, weight));
		this.generadorIdArista++;
		return idArista;
	}

	public boolean contieneNodo(int k) {
		return this.nodos.containsKey(k);
	}

	// -1 si no se encuentra arista
	// 0+ si esta
	public int buscarArista(int nodeA, int nodeB) {
		for (Integer i : this.aristas.keySet()) {
			if (this.aristas.get(i).estanConectados(nodeA, nodeB)) {
				return i;
			}
		}
		return -1;
	}

	public boolean consultarArista(int nodeA, int nodeB) {
		return (this.contieneNodo(nodeA) && this.buscarArista(nodeA, nodeB) >= 0);
	}

	public double getPesoArista(int nodeA, int nodeB) {

		int edgeID = this.buscarArista(nodeA, nodeB);

		if (edgeID < 0) {
			throw new RuntimeException("No hay conexion entre los nodos");
		}

		return this.aristas.get(edgeID).getPeso();
	}

	public boolean eliminarConexion(int nodeA, int nodeB) {

		int edgeID = this.buscarArista(nodeA, nodeB);

		if (edgeID < 0) {
			return false;
		}
		this.aristas.remove(edgeID);
		return true;
	}

	HashMap<Integer, T> getTodosLosNodos() {
		return this.nodos;
	}

	public HashMap<Integer, Edge> getTodasLasAristas() {
		return this.aristas;
	}

	public int tamaño() {
		return this.nodos.size();
	}

	public boolean esNulo() {
		return this.nodos.isEmpty();
	}

	public boolean esConexo() {

		// caso trivial
		if (this.nodos.size() <= 1) {
			return true;
		}

		// obtenemos primer nodo. cualquiera sirve.
		int primerNodo = nodos.keySet().iterator().next();

		// armamos lista de nodos recorridos
		HashSet<Integer> visitados = new HashSet<Integer>();
		visitados.add(primerNodo);

		// recursion time!!
		this.explorarVecinos(primerNodo, visitados);

		return (visitados.size() == this.nodos.size());

	}

	private void explorarVecinos(int nodo, HashSet<Integer> visitados) {

		HashSet<Integer> vecinosNodo = this.getNeighbors(nodo);

		for (Integer v : vecinosNodo) {
			if (!visitados.contains(v)) {
				visitados.add(v);
				this.explorarVecinos(v, visitados);
			}
		}
	}

	public HashSet<Integer> getNeighbors(int nodo) {

		if(!this.contieneNodo(nodo)) {
			throw new RuntimeException("No se encuentra ese elemento.");
		}
		
		HashSet<Integer> res = new HashSet<Integer>();

		for (Edge e : this.aristas.values()) {
			
			if (e.connects(nodo)) {
				res.add(e.getOtroExtremo(nodo));
			}
		}

		return res;

	}

}
