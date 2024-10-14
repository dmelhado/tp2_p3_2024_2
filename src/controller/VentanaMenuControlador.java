package controller;

import java.util.HashMap;

import javax.swing.table.DefaultTableModel;
import view.PantallaPrincipal;
import view.VentanaEspias;
import view.VentanaConexiones;
import view.VentanaMenu;

public class VentanaMenuControlador {
	private VentanaMenu ventanaMenu;
	private PantallaPrincipal pantallaPrincipal;
	private VentanaConexiones ventanaConexiones;

	public VentanaMenuControlador(VentanaMenu ventanaMenu, PantallaPrincipal pantallaPrincipal) {
		this.ventanaMenu = ventanaMenu;
		this.pantallaPrincipal = pantallaPrincipal;

		ventanaMenu.setControlador(this);

	}

	public void abrirVentanaAgentes() {
		VentanaEspias ventanaAgentes = new VentanaEspias(ventanaMenu);
		ventanaAgentes.abrirVentana();
	}

	public void abrirVentanaConexiones() {
		if (ventanaConexiones == null) {
			ventanaConexiones = new VentanaConexiones(ventanaMenu);
			VentanaConexionesControlador controlador = new VentanaConexionesControlador(ventanaConexiones, ventanaMenu);
			ventanaConexiones.setControlador(controlador);
		}
		ventanaConexiones.abrirVentana();
	}

	public void limpiarTablas() {
		System.out.println("Limpiando Tablas..");
		ventanaMenu.limpiarTablas();
		if (ventanaConexiones != null) {
			ventanaConexiones.limpiarComboBox();
		}
		pantallaPrincipal.getControladorJuego().getVentanaJuego().limpiarTabla();
		pantallaPrincipal.getControladorJuego().limpiarTodo();

	}

	public void crearGrafo() {
		DefaultTableModel modeloTabla = ventanaMenu.getModeloTabla();
		int numeroFila = modeloTabla.getRowCount();
		if (numeroFila == 0) {
			ventanaMenu.mostrarMensajeNoConexiones();
			return;
		}
		HashMap<String, Boolean> mapaEspias = new HashMap<>();
		for (int i = 0; i < numeroFila; i++) {
			String espia1 = modeloTabla.getValueAt(i, 0).toString().split("-")[0];
			String espia2 = modeloTabla.getValueAt(i, 0).toString().split("-")[1];
			mapaEspias.put(espia1, true);
			mapaEspias.put(espia2, true);
		}

		String[] espias = mapaEspias.keySet().toArray(new String[0]);
		String[][] conexiones = new String[numeroFila][3];
		for (int i = 0; i < numeroFila; i++) {
			String espia1 = modeloTabla.getValueAt(i, 0).toString().split("-")[0];
			String espia2 = modeloTabla.getValueAt(i, 0).toString().split("-")[1];
			String probabilidad = modeloTabla.getValueAt(i, 1).toString();
			conexiones[i][0] = espia1;
			conexiones[i][1] = espia2;
			conexiones[i][2] = probabilidad;
		}

		pantallaPrincipal.cambiarVentana(pantallaPrincipal.getS_VENTANAJUEGO());
		pantallaPrincipal.getControladorJuego().crearGrafo(espias, conexiones);

	}

	public void actualizarComboBoxes() {
		if (ventanaConexiones != null) {
			ventanaConexiones.actualizarComboBoxes();
		}

	}

	public void verificarEspia(String nombreEspia) {
		boolean existeEspia = ventanaMenu.getListaEspias().stream()
				.anyMatch(espia -> espia.equalsIgnoreCase(nombreEspia));
		if (existeEspia) {
			ventanaMenu.mostrarMensajeVerificacionEspia(nombreEspia);
		} else {
			ventanaMenu.agregarTablaEspias(nombreEspia);
		}

	}

	public boolean existeConexion(String espia1, String espia2) {
		DefaultTableModel modeloTabla = ventanaMenu.getModeloTabla();
		int numRows = modeloTabla.getRowCount();
		for (int i = 0; i < numRows; i++) {
			String conexion = (String) modeloTabla.getValueAt(i, 0);
			if (conexion.contains(espia1) && conexion.contains(espia2)) {
				return true;
			}
		}
		return false;
	}
}
