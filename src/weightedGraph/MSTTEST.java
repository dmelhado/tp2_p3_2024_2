package weightedGraph;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MSTTEST {

	private WeightedGraph<String> graph;
	
	@BeforeEach
    public void setUp() {
        graph = new WeightedGraph<String>();
    }
	
	@Test
    public void testMiniMST() {
		this.graph.put("0");
		this.graph.put("1");
		this.graph.put("2");
		this.graph.put("3");
		
		this.graph.setConnection(0, 1, 0.2);
		this.graph.setConnection(0, 2, 0.3);
		this.graph.setConnection(0, 3, 0.4);
		this.graph.setConnection(1, 2, 0.01);
		this.graph.setConnection(1, 3, 0.6);
		this.graph.setConnection(2, 3, 0.02);
		
		WeightedGraph<String> test_mst = MST.generateMST(this.graph);
		
		assertTrue(test_mst.checkEdge(0, 1), "Deberia contener la arista 0-1 con 0.2");
		assertFalse(test_mst.checkEdge(0, 2), "No deberia contener la arista 0-2 con 0.3");
		assertFalse(test_mst.checkEdge(0, 3), "No deberia contener la arista 0-3 con 0.4");
		assertTrue(test_mst.checkEdge(1, 2), "Deberia contener la arista 1-2 con 0.01");
		assertFalse(test_mst.checkEdge(1, 3), "No deberia contener la arista 1-3 con 0.6");
		assertTrue(test_mst.checkEdge(2, 3), "Deberia contener la arista 2-3 con 0.02");
	}
	
}
