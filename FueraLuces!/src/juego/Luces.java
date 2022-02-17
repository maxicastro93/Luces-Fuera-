package juego;

import javax.swing.JButton;

public class Luces {

	public JButton luz;
	
	private int id ;
	public int numCol;
	public int numFil;
	
	public Luces ()
	{
		luz = new JButton();
		id = (int) (Math.random() * 2);
		numCol = 0;
		numFil = 0;
		
	}
	// Devuelve 1 si la luz esta encendida, devuelve 0 si esta apagada
	
	public int getEstado ()
	{
		return id;
	}
	
	public void setEstado(int x)
	{
		this.id = x;
	}
	
	public void invertirLuz()
	{
		if (this.getEstado() == 0)
			this.setEstado(1);
		else
			this.setEstado(0);
	}
	
		
}
