package tests;

import java.util.Random;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import model.GrafoPonderado;
import model.AGM;

import java.text.DecimalFormat;

public class EstresTest {

	@Test
	public void stress() {

		int min = 10;
		int max = 300;

		for (int i = min; i < max; i++) {

			DecimalFormat df = new DecimalFormat("#.##");

			long startGen = System.nanoTime();
			GrafoPonderado<Integer> g = this.randomConexGraph(i);
			long stopGen = System.nanoTime();

			double genTiempo = (stopGen - startGen) / 1_000_000_000.0;
			System.out.println("\n- Tamaño grafo: " + i + " -\nTiempo generacion: " + df.format(genTiempo) + " segundos");

			long startAGM = System.nanoTime();
			GrafoPonderado<Integer> agm = AGM.obtenerAGM(g);
			long stopAGM = System.nanoTime();

			double agmTiempo = (stopAGM - startAGM) / 1_000_000_000.0;
			System.out.println("Tiempo  calc. AGM: " + df.format(agmTiempo) + " segundos");

		}

	}

	private GrafoPonderado<Integer> randomConexGraph(int n) {
		GrafoPonderado<Integer> res = new GrafoPonderado<Integer>();
		for (int i = 0; i < n; i++) {
			res.put(0);
		}
		while (!res.esConexo()) {
			Random r = new Random();
			int a = r.nextInt(n);
			int b;
			do {
				b = r.nextInt(n);
			} while (b == a);
			res.setConexion(a, b, r.nextDouble());
		}
		return res;
	}

}
