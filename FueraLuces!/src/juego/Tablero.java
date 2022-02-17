package juego;


public class Tablero {

	public Luces[][] matrizLuces;
	public int filas;
	public int columnas;
	
	
	
	
	public Tablero(int x)
	{
		this.filas = x;
		this.columnas = x;
		
		this.matrizLuces = new Luces[filas][columnas];
		
		for (int i = 0; i< columnas; i++)
		{
			for (int j = 0; j<filas; j++)
			{
				this.matrizLuces[i][j] = new Luces();
				this.matrizLuces[i][j].numFil = j;
				this.matrizLuces[i][j].numCol = i;
			}
		}
	}

	public void matrizVacia()
	{
		for (int i = 0; i< columnas; i++)
		{
			for (int j = 0; j<filas; j++)
			{
				this.matrizLuces[i][j] = new Luces();
				this.matrizLuces[i][j].numFil = j;
				this.matrizLuces[i][j].numCol = i;
			}
		}
			
	}
	
	public int getColumnas()
	{
		return this.columnas;
	}
	
	public int getFilas()
	{
		return this.filas;
	}
	
	public void cambiaLuces(int c, int f)
	{
		// Cambia la luz central, la cual fue seleccionada
		if(this.matrizLuces[c][f].getEstado() == 0)
			this.matrizLuces[c][f].setEstado(1);
		else
			this.matrizLuces[c][f].setEstado(0);
		
		// Cambia la luz de arriba
		if (this.matrizLuces[c][f].numFil>0)
			this.matrizLuces[c][f-1].invertirLuz();
		
		// Cambia la luz de abajo
		if (this.matrizLuces[c][f].numFil<this.filas-1)
			this.matrizLuces[c][f+1].invertirLuz();
				
		// Cambia la luz de izquierda
		if (this.matrizLuces[c][f].numCol>0)
			this.matrizLuces[c-1][f].invertirLuz();	
		
		// Cambia la luz de derecha
		if (this.matrizLuces[c][f].numCol<this.columnas-1)
			this.matrizLuces[c+1][f].invertirLuz();		
	}
	
	public boolean checkGano()
	{
		int contador = 0;
		for (int i = 0; i< this.getColumnas(); i++)
		{
			for (int j = 0; j<this.getFilas(); j++)
			{
				if (this.matrizLuces[i][j].getEstado() == 0)
					contador++;
			}
		}
		
		if (contador == (this.columnas*this.filas))
			return true;
		else
			return false;
			
	}
	
}
