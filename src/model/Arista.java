package model;

public class Arista {

	private int nodoA;
	private int nodoB;
	private double peso;

	public Arista(int a, int b, double p) {
		if (p < 0 || p > 1) {
			throw new RuntimeException("No se admiten pesos fuera de [0,1]");
		}
		if (a == b) {
			throw new RuntimeException("No se admiten bucles");
		}
		if (a < 0 || b < 0) {
			throw new RuntimeException("ID de algun nodo inválido");
		}

		// por una razon de consistencia, prefiero que los nodos esten en orden
		if (a > b) {
			a -= b;
			b += a;
			a = b - a;
		}
		this.nodoA = a;
		this.nodoB = b;
		this.peso = p;
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
		if (this.a() == in) {
			out = this.b();
		}
		if (this.b() == in) {
			out = this.a();
		}
		return out;
	}

}
