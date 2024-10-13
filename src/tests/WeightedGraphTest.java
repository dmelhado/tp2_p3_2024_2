package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.LinkedList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.WeightedGraph;

public class WeightedGraphTest {

	private WeightedGraph<String> graph;

	@BeforeEach
	public void setUp() {
		graph = new WeightedGraph<String>();
	}

	@Test
	public void testPutNodes() {
		int id1 = graph.put("Nodo 1");
		int id2 = graph.put("Nodo 2");

		assertTrue(graph.contieneNodo(id1), "Deberia tener el primer nodo");
		assertTrue(graph.contieneNodo(id2), "Deberia tener el segundo nodo");
		assertFalse(graph.contieneNodo(999), "No deberia tener uno inexistente");
	}

	@Test
	public void testSetConnectionValidNodes() {
		int id1 = graph.put("Nodo 1");
		int id2 = graph.put("Nodo 2");

		graph.setConexiones(id1, id2, 0.5);

		assertTrue(graph.consultarArista(id1, id2), "Deberia haber arista entre 1 y 2");
		assertTrue(graph.consultarArista(id2, id1), "Deberia valer la inversa");
		assertEquals(0.5, graph.getPesoArista(id1, id2), "El peso deberia ser el correcto.");
	}

	@Test
	public void testSetConnectionInvalidNodes() {
		int id1 = graph.put("Nodo 1");

		assertThrows(RuntimeException.class, () -> graph.setConexiones(id1, 999, 0.5),
				"No deberia haber conexion entre nodos inexistentes");
	}

	@Test
	public void testCheckEdgeNonExistentEdge() {
		int id1 = graph.put("Node 1");
		int id2 = graph.put("Node 2");

		assertFalse(graph.consultarArista(id1, id2), "No deberia haber conexion en nodos sin conectar");
	}

	@Test
	public void testGetEdgeWeightNonExistentEdge() {
		int id1 = graph.put("Node 1");
		int id2 = graph.put("Node 2");

		assertThrows(RuntimeException.class, () -> graph.getPesoArista(id1, id2),
				"Deberia tirar excepcion por nodos sin conectar.");
	}
	
	@Test
	public void testGrafoConexo() {
		int id1 = graph.put("Node 1");
		int id2 = graph.put("Node 2");
		int id3 = graph.put("Node 3");
		int id4 = graph.put("Node 4");
		int id5 = graph.put("Node 5");
		
		graph.setConexiones(id1, id2, 1);
		graph.setConexiones(id1, id3, 1);
		graph.setConexiones(id1, id4, 1);
		graph.setConexiones(id2, id5, 1);
		
		assertTrue(graph.esConexo());
	}
	
	@Test
	public void testGrafoDisconexo() {
		int id1 = graph.put("Node 1");
		int id2 = graph.put("Node 2");
		int id3 = graph.put("Node 3");
		int id4 = graph.put("Node 4");
		int id5 = graph.put("Node 5");
		
		graph.setConexiones(id1, id2, 1);
		graph.setConexiones(id2, id3, 1);
		graph.setConexiones(id4, id5, 1);
		
		assertFalse(graph.esConexo());
	}
	
	@Test
	public void testBFS() {
		int id1 = graph.put("Node 1");
		int id2 = graph.put("Node 2");
		int id3 = graph.put("Node 3");
		int id4 = graph.put("Node 4");
		int id5 = graph.put("Node 5");
		
		graph.setConexiones(id1, id2, 1);
		graph.setConexiones(id1, id3, 1);
		graph.setConexiones(id1, id4, 1);
		graph.setConexiones(id2, id5, 1);
		
		LinkedList<Integer> bfsResult = graph.bfs();
	}
}
