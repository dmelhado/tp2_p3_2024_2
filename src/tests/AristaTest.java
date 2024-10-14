package tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import model.Arista;

public class AristaTest {

	private Arista arista;

	@BeforeEach
	public void setup() {
		arista = new Arista(1, 2, 0.5);
	}

	@Test
	@DisplayName("La arista conecto los nodos correctamente")
	public void aristaConectaNodosCorrectamente() {
		Assertions.assertTrue(arista.connects(1));
		Assertions.assertTrue(arista.connects(2));
		Assertions.assertFalse(arista.connects(3));
	}

	@Test
	@DisplayName("La arista devuelve el otro extremo correctamente")
	public void aristaDevuelveElOtroExtremoCorrectamente() {
		Assertions.assertEquals(2, arista.getOtroExtremo(1));
		Assertions.assertEquals(1, arista.getOtroExtremo(2));
	}

	@Test
	@DisplayName("La arista lanza una excepcion para nodos iguales")
	public void aristaExcepcionNodosIguales() {
		Assertions.assertThrows(RuntimeException.class, () -> new Arista(1, 1, 0.5));
	}

	@Test
	@DisplayName("la arista lanza una excepcion para un peso invalido")
	public void aristaExcepcionPesoInvalido() {
		Assertions.assertThrows(RuntimeException.class, () -> new Arista(1, 2, -0.5));
		Assertions.assertThrows(RuntimeException.class, () -> new Arista(1, 2, 1.5));
	}

	@Test
	@DisplayName("La arista coloca y obtiene el peso correctamente")
	public void aristaSetGetPeso() {
		arista.setPeso(0.8);
		Assertions.assertEquals(0.8, arista.getPeso());
	}

	@Test
	@DisplayName("La arista verifica la conexion correctamente")
	public void aristaVerificaConexionCorrectamente() {
		Assertions.assertTrue(arista.estanConectados(1, 2));
		Assertions.assertTrue(arista.estanConectados(2, 1));
		Assertions.assertFalse(arista.estanConectados(1, 1));
		Assertions.assertFalse(arista.estanConectados(2, 3));
	}
}