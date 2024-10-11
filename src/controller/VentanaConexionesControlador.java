package controller;


import view.VentanaConexiones;
import view.VentanaMenu;

public class VentanaConexionesControlador {
	private VentanaConexiones ventanaConexiones;
	private VentanaMenu ventanaMenu;
	
	public VentanaConexionesControlador(VentanaConexiones ventanaConexiones, VentanaMenu ventanaMenu)
	{
		this.ventanaConexiones = ventanaConexiones;
		this.ventanaMenu = ventanaMenu;
		
		this.ventanaConexiones.setControlador(this);
	}

	public void agregarArista(String espia1, String espia2, String probabilidadTexto) {
		if (espia1 != null && espia2 != null) {
			Double.parseDouble(probabilidadTexto.replace(",", "."));
			ventanaMenu.agregarTablaAristaProbabilidad(espia1, espia2, probabilidadTexto);

		}
		
		
		
	}
	
	
	

}
