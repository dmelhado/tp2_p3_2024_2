package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.LinkedList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.GrafoPonderado;

public class GrafoPonderadoTest {

	private GrafoPonderado<String> grafo;

	@BeforeEach
	public void setUp() {
		grafo = new GrafoPonderado<String>();
	}

	@Test
	public void testPutNodes() {
		int id1 = grafo.put("Nodo 1");
		int id2 = grafo.put("Nodo 2");

		assertTrue(grafo.contieneNodo(id1), "Deberia tener el primer nodo");
		assertTrue(grafo.contieneNodo(id2), "Deberia tener el segundo nodo");
		assertFalse(grafo.contieneNodo(999), "No deberia tener uno inexistente");
	}

	@Test
	public void testSetConnectionValidNodes() {
		int id1 = grafo.put("Nodo 1");
		int id2 = grafo.put("Nodo 2");

		grafo.setConexion(id1, id2, 0.5);

		assertTrue(grafo.consultarArista(id1, id2), "Deberia haber arista entre 1 y 2");
		assertTrue(grafo.consultarArista(id2, id1), "Deberia valer la inversa");
		assertEquals(0.5, grafo.getPesoArista(id1, id2), "El peso deberia ser el correcto.");
	}

	@Test
	public void testSetConnectionInvalidNodes() {
		int id1 = grafo.put("Nodo 1");

		assertThrows(RuntimeException.class, () -> grafo.setConexion(id1, 999, 0.5),
				"No deberia haber conexion entre nodos inexistentes");
	}

	@Test
	public void testCheckEdgeNonExistentEdge() {
		int id1 = grafo.put("Node 1");
		int id2 = grafo.put("Node 2");

		assertFalse(grafo.consultarArista(id1, id2), "No deberia haber conexion en nodos sin conectar");
	}

	@Test
	public void testGetEdgeWeightNonExistentEdge() {
		int id1 = grafo.put("Node 1");
		int id2 = grafo.put("Node 2");

		assertThrows(RuntimeException.class, () -> grafo.getPesoArista(id1, id2),
				"Deberia tirar excepcion por nodos sin conectar.");
	}

	@Test
	public void testGrafoConexo() {
		int id1 = grafo.put("Node 1");
		int id2 = grafo.put("Node 2");
		int id3 = grafo.put("Node 3");
		int id4 = grafo.put("Node 4");
		int id5 = grafo.put("Node 5");

		grafo.setConexion(id1, id2, 1);
		grafo.setConexion(id1, id3, 1);
		grafo.setConexion(id1, id4, 1);
		grafo.setConexion(id2, id5, 1);

		assertTrue(grafo.esConexo());
	}

	@Test
	public void testGrafoDisconexo() {
		int id1 = grafo.put("Node 1");
		int id2 = grafo.put("Node 2");
		int id3 = grafo.put("Node 3");
		int id4 = grafo.put("Node 4");
		int id5 = grafo.put("Node 5");

		grafo.setConexion(id1, id2, 1);
		grafo.setConexion(id2, id3, 1);
		grafo.setConexion(id4, id5, 1);

		assertFalse(grafo.esConexo());
	}

	@Test
	public void testBFS() {
		int id1 = grafo.put("Node 1");
		int id2 = grafo.put("Node 2");
		int id3 = grafo.put("Node 3");
		int id4 = grafo.put("Node 4");
		int id5 = grafo.put("Node 5");

		grafo.setConexion(id1, id2, 1);
		grafo.setConexion(id1, id3, 1);
		grafo.setConexion(id1, id4, 1);
		grafo.setConexion(id2, id5, 1);

		LinkedList<Integer> bfsResult = grafo.bfs();
	}
}
