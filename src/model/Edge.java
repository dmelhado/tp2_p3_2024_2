package model;

public class Edge {

	private int nodoA;
	private int nodoB;
	private double peso;
	
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
		this.nodoA = a;
		this.nodoB = b;
		this.peso = w;
	}
	
	public double getPeso() {
		return this.peso;
	}
	
	public void setPeso(double nuevoPeso) {
		this.peso = nuevoPeso;
	}

	public int a() {
		return nodoA;
	}
	
	public int b() {
		return nodoB;
	}

	public boolean estanConectados(int x, int y) {
		return (x != y && this.connects(x) && this.connects(y));
	}
	
	public boolean connects(int x) {
		return (this.a() == x || this.b() == x);
	}
	
	// devuelve la otra punta de la arista
	public int getOtroExtremo(int in) {
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
