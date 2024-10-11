package controller;

import view.VentanaAgentes;
import view.VentanaMenu;

public class VentanaAgentesControlador {
	private VentanaAgentes ventanaAgentes;
	private VentanaMenu ventanaMenu;
	
	public VentanaAgentesControlador(VentanaAgentes ventanaAgentes, VentanaMenu ventanaMenu)
	{
		this.ventanaAgentes = ventanaAgentes;
		this.ventanaMenu = ventanaMenu;		
		
		this.ventanaAgentes.setControlador(this);
	}
	
	public void agregarEspia(String espia)
	{
		if (!espia.isEmpty()) {
			ventanaMenu.agregarTablaEspias(espia);
		}
	}
	
	

}
