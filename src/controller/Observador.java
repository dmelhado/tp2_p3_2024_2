package controller;

import java.util.ArrayList;
import java.util.List;

public abstract class Observador {
	private List<ObservadorInterfaz> observadores = new ArrayList<>();
	
	public void añadirObservador(ObservadorInterfaz observer)
	{
		observadores.add(observer);
	}
	
	public void eliminarObservador(ObservadorInterfaz observer)
	{
		observadores.remove(observer);
	}
	
	protected void notificarObservador()
	{
		for (ObservadorInterfaz observer : observadores)
		{
			observer.actualizar();
		}
	}
}
