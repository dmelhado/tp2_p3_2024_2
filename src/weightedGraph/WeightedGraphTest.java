package weightedGraph;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class WeightedGraphTest {
    
    private WeightedGraph<String> graph;

    @BeforeEach
    public void setUp() {
        graph = new WeightedGraph<>();
    }

    @Test
    public void testPutNodes() {
        int id1 = graph.put("Node 1");
        int id2 = graph.put("Node 2");
        
        assertTrue(graph.containsNode(id1), "Graph should contain the first node.");
        assertTrue(graph.containsNode(id2), "Graph should contain the second node.");
        assertFalse(graph.containsNode(999), "Graph should not contain non-existing node.");
    }

    @Test
    public void testSetConnectionValidNodes() {
        int id1 = graph.put("Node 1");
        int id2 = graph.put("Node 2");
        
        graph.setConnection(id1, id2, 0.5);
        
        assertTrue(graph.checkEdge(id1, id2), "Graph should have an edge between Node 1 and Node 2.");
        assertEquals(5.0, graph.getEdgeWeight(id1, id2), "Edge weight should be correct.");
    }

    @Test
    public void testSetConnectionInvalidNodes() {
        int id1 = graph.put("Node 1");
        
        graph.setConnection(id1, 999, 0.5);
        
        assertFalse(graph.checkEdge(id1, 999), "Connection should not be set for invalid nodes.");
    }

    @Test
    public void testCheckEdgeNonExistentEdge() {
        int id1 = graph.put("Node 1");
        int id2 = graph.put("Node 2");
        
        assertFalse(graph.checkEdge(id1, id2), "Graph should not have an edge between unconnected nodes.");
    }

    @Test
    public void testGetEdgeWeightNonExistentEdge() {
        int id1 = graph.put("Node 1");
        int id2 = graph.put("Node 2");

        assertThrows(RuntimeException.class, () -> graph.getEdgeWeight(id1, id2), 
                     "Should throw an exception for non-existent edge.");
    }
}
