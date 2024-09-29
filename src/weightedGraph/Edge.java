package weightedGraph;

public class Edge {

	private int aNode;
	private int bNode;
	private double weight;
	
	public Edge(int a, int b, double w) {
		if(w < 0 || w > 1) {
			throw new RuntimeException("no se admiten pesos fuera de [0,1]");
		}
		if(a == b) {
			throw new RuntimeException("no se admiten bucles");
		}
		
		// por una razon de consistencia, prefiero que los nodos esten en orden
		if(a > b) {
			a -= b;
			b += a;
			a = b-a;
		}
		this.aNode = a;
		this.bNode = b;
		this.weight = w;
	}
	
	public double getWeight() {
		return this.weight;
	}
	
	public void setWeight(double newWeight) {
		this.weight = newWeight;
	}

	public int a() {
		return aNode;
	}
	
	public int b() {
		return bNode;
	}

	public boolean areConnected(int x, int y) {
		return (x != y && this.connects(x) && this.connects(y));
	}
	
	public boolean connects(int x) {
		return (this.a() == x || this.b() == x);
	}
	
	// devuelve la otra punta de la arista
	public int getOtherEnd(int in) {
		int out = -1;
		if(this.a() == in) {
			out = this.b();
		}
		if(this.b() == in) {
			out = this.a();
		}
		return out;
	}
	
	
}
