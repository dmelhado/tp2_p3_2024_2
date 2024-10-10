package controller;

import java.util.ArrayList;
import java.util.List;

public abstract class Observador {
	private List<Observer> observadores = new ArrayList<>();
	
	public void añadirObservador(Observer observer)
	{
		observadores.add(observer);
	}
	
	public void eliminarObservador(Observer observer)
	{
		observadores.remove(observer);
	}
	
	protected void notificarObservador()
	{
		for (Observer observer : observadores)
		{
			observer.actualizar();
		}
	}

}
