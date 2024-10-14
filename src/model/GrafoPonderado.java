package model;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

import controller.Observador;

public class GrafoPonderado<T> extends Observador {

	private HashMap<Integer, T> nodos;
	private HashMap<Integer, Arista> aristas;
	private int generadorIdNodo;
	private int generadorIdArista;

	public GrafoPonderado() { 
		this.generadorIdNodo = 0;
		this.generadorIdArista = 0;
		this.nodos = new HashMap<Integer, T>();
		this.aristas = new HashMap<Integer, Arista>();
	}

	public int put(T data) {
		int idNodo = this.generadorIdNodo;
		this.nodos.put(idNodo, data);
		this.generadorIdNodo++;
		notificarObservador();
		return idNodo;
	}

	public boolean contains(T data) {
		return nodos.containsValue(data);
	}

	public T getData(int id) {
		if (!this.contieneNodo(id)) {
			throw new RuntimeException("No se encuentra ese elemento.");
		}
		return this.nodos.get(id);
	}

	public Set<Integer> getAllKeys() {
		return this.nodos.keySet();
	}

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
		notificarObservador();
		return true;
	}

	public int setConexion(int nodeA, int nodeB, double weight) {
		// TODO: Chequear esto, ya que con el if tira error al generar el AGS
		//if (!this.contieneNodo(nodeA) || !this.contieneNodo(nodeB)) {
		//	throw new RuntimeException("Uno de los nodos no existe");
		//}

		int idArista = this.generadorIdArista;
		this.aristas.put(idArista, new Arista(nodeA, nodeB, weight));
		this.generadorIdArista++;
		notificarObservador();
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

	public HashMap<Integer, Arista> getTodasLasAristas() {
		return this.aristas;
	}

	public int tamaño() {
		return this.nodos.size();
	}

	public boolean esNulo() {
		return this.nodos.isEmpty();
	}

	// devuelve la lista de los nodos alcanzados en el orden del recorrido
	public LinkedList<Integer> bfs() {

		LinkedList<Integer> visitados = new LinkedList<Integer>();

		// caso trivial
		if (this.nodos.size() < 1) {
			return visitados;
		}

		// obtenemos primer nodo. cualquiera sirve.
		int primerNodo = nodos.keySet().iterator().next();

		Queue<Integer> colaNodos = new LinkedList<Integer>();
		colaNodos.add(primerNodo);

		while (!colaNodos.isEmpty()) {

			int nodoActual = colaNodos.remove();

			if (!visitados.contains(nodoActual)) {
				visitados.add(nodoActual);

				HashSet<Integer> vecinos = this.getNeighbors(nodoActual);

				for (Integer v : vecinos) {
					if (!colaNodos.contains(v)) {
						colaNodos.add(v);
					}
				}
			}
		}

		return visitados;
	}

	public boolean esConexo() {
		return (this.bfs().size() == this.nodos.size());
	}

	public double obtenerMaximoPeso() {
		double res = 0;
		for (Arista e : this.aristas.values()) {
			if (res < e.getPeso()) {
				res = e.getPeso();
			}
		}
		return res;
	}

	public HashSet<Integer> getNeighbors(int nodo) {

		if (!this.contieneNodo(nodo)) {
			throw new RuntimeException("No se encuentra ese elemento.");
		}

		HashSet<Integer> res = new HashSet<Integer>();

		for (Arista e : this.aristas.values()) {

			if (e.connects(nodo)) {
				res.add(e.getOtroExtremo(nodo));
			}
		}

		return res;

	}

}
