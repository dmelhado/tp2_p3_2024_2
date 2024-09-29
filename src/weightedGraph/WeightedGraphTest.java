package weightedGraph;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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

		assertTrue(graph.containsNode(id1), "Deberia tener el primer nodo");
		assertTrue(graph.containsNode(id2), "Deberia tener el segundo nodo");
		assertFalse(graph.containsNode(999), "No deberia tener uno inexistente");
	}

	@Test
	public void testSetConnectionValidNodes() {
		int id1 = graph.put("Nodo 1");
		int id2 = graph.put("Nodo 2");

		graph.setConnection(id1, id2, 0.5);

		assertTrue(graph.checkEdge(id1, id2), "Deberia haber arista entre 1 y 2");
		assertTrue(graph.checkEdge(id2, id1), "Deberia valer la inversa");
		assertEquals(0.5, graph.getEdgeWeight(id1, id2), "El peso deberia ser el correcto.");
	}

	@Test
	public void testSetConnectionInvalidNodes() {
		int id1 = graph.put("Nodo 1");

		assertThrows(RuntimeException.class, () -> graph.setConnection(id1, 999, 0.5),
				"No deberia haber conexion entre nodos inexistentes");
	}

	@Test
	public void testCheckEdgeNonExistentEdge() {
		int id1 = graph.put("Node 1");
		int id2 = graph.put("Node 2");

		assertFalse(graph.checkEdge(id1, id2), "No deberia haber conexion en nodos sin conectar");
	}

	@Test
	public void testGetEdgeWeightNonExistentEdge() {
		int id1 = graph.put("Node 1");
		int id2 = graph.put("Node 2");

		assertThrows(RuntimeException.class, () -> graph.getEdgeWeight(id1, id2),
				"Deberia tirar excepcion por nodos sin conectar.");
	}
}
