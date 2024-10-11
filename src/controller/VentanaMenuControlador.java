package controller;

import model.WeightedGraph;
import view.PantallaPrincipal;
import view.VentanaConexiones;
import view.VentanaMenu;

public class VentanaMenuControlador {
	private VentanaMenu ventanaMenu;
	private PantallaPrincipal pantallaPrincipal;
	private VentanaConexiones ventanaConexiones;
	
	
	public VentanaMenuControlador(VentanaMenu ventanaMenu, PantallaPrincipal pantallaPrincipal)
	{
		this.ventanaMenu = ventanaMenu;
		this.pantallaPrincipal = pantallaPrincipal;
		
	}
}
