package controller;

import view.VentanaEspias;
import view.VentanaMenu;

public class VentanaEspiasControlador {
	private VentanaEspias ventanaAgentes;
	private VentanaMenu ventanaMenu;
	
	public VentanaEspiasControlador(VentanaEspias ventanaAgentes, VentanaMenu ventanaMenu)
	{
		this.ventanaAgentes = ventanaAgentes;
		this.ventanaMenu = ventanaMenu;		
		
		this.ventanaAgentes.setControlador(this);
	}
	
	public void agregarEspia(String espia)
	{
		if (!espia.isEmpty()) {
			ventanaMenu.getControlador().verificarEspia(espia);
		}
	}
	
	

}
