package tests;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.AGM;
import model.GrafoPonderado;

public class AGMTest {

	private GrafoPonderado<String> graph;

	@BeforeEach
	public void setUp() {
		graph = new GrafoPonderado<String>();
	}

	@Test
	public void testMiniMST() {
		this.graph.put("0");
		this.graph.put("1");
		this.graph.put("2");
		this.graph.put("3");

		this.graph.setConexion(0, 1, 0.2);
		this.graph.setConexion(0, 2, 0.3);
		this.graph.setConexion(0, 3, 0.4);
		this.graph.setConexion(1, 2, 0.01);
		this.graph.setConexion(1, 3, 0.6);
		this.graph.setConexion(2, 3, 0.02);

		GrafoPonderado<String> test_mst = AGM.obtenerAGM(this.graph);

		assertTrue(test_mst.consultarArista(0, 1), "Deberia contener la arista 0-1 con 0.2");
		assertFalse(test_mst.consultarArista(0, 2), "No deberia contener la arista 0-2 con 0.3");
		assertFalse(test_mst.consultarArista(0, 3), "No deberia contener la arista 0-3 con 0.4");
		assertTrue(test_mst.consultarArista(1, 2), "Deberia contener la arista 1-2 con 0.01");
		assertFalse(test_mst.consultarArista(1, 3), "No deberia contener la arista 1-3 con 0.6");
		assertTrue(test_mst.consultarArista(2, 3), "Deberia contener la arista 2-3 con 0.02");
	}

}
