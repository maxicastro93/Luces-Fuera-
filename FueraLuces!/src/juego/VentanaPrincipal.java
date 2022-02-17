package juego;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import javax.swing.JButton;
import javax.swing.JDialog;

import java.awt.event.ActionListener;

import java.awt.event.ActionEvent;

import java.awt.Color;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;

public class VentanaPrincipal {


	private JFrame frame;
	public Integer contMovim;
	public Integer mejorMovim;
	public Tablero tableroJuego;
	public JLabel movimientos;
	public JLabel record;
	public int contadorVecesQueGano;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaPrincipal window = new VentanaPrincipal();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public VentanaPrincipal() {
		initialize();
		contadorVecesQueGano = 0;
	
		
	}
	
	private void clickEnLuz(int x, int y, JDialog ventana) {
		tableroJuego.matrizLuces[x][y].luz.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) 
			{
				contMovim++;
				movimientos.setText("Movimientos = " + contMovim.toString());
				tableroJuego.cambiaLuces(x, y);
				actualizaColor();
				if(tableroJuego.checkGano())
				{
					contadorVecesQueGano++;
					JOptionPane.showMessageDialog(frame, "Todas las luces se apagaron, Ganaste el Juego !!!");
					if (contMovim < mejorMovim ) 
						mejorMovim = contMovim;
						record.setVisible(true);
					ventana.dispose();
										
				}
				
			}
		});
	}
	
	public void actualizaColor()
	{
		for (int i = 0; i< tableroJuego.getColumnas(); i++)
		{
			for (int j = 0; j<tableroJuego.getFilas(); j++)
			{
				if (this.tableroJuego.matrizLuces[i][j].getEstado() == 1)
					{
						tableroJuego.matrizLuces[i][j].luz.setForeground(Color.ORANGE);
						tableroJuego.matrizLuces[i][j].luz.setBackground(Color.ORANGE);
					}
					else
					{	
						tableroJuego.matrizLuces[i][j].luz.setBackground(Color.LIGHT_GRAY);
						tableroJuego.matrizLuces[i][j].luz.setForeground(Color.LIGHT_GRAY);
					}
			}
		}
		
			
	}
	
	public void muestraBotones(JPanel panel)
	{
		int ubicCol = 155;
		int ubicFil = 0;
		
		for (int i = 0; i< tableroJuego.getColumnas(); i++)
		{	
			ubicFil= 200;
			for (int j = 0; j<tableroJuego.getFilas(); j++)
			{
				tableroJuego.matrizLuces[i][j].luz.setBounds(ubicCol,ubicFil, 100, 100);
				panel.add(tableroJuego.matrizLuces[i][j].luz);
				ubicFil= ubicFil + 116;
			}
			ubicCol = ubicCol + 115;
		}
	}
	

	private void initialize() {
		
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(178, 34, 34));
		frame.setBounds(100, 100, 923, 692);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		// Label Titulo del Frame
		JLabel lblBienvenidoALuces = new JLabel("Bienvenido a Fuera Luces!");
		lblBienvenidoALuces.setForeground(new Color(255, 255, 255));
		lblBienvenidoALuces.setBounds(84, 35, 742, 49);
		lblBienvenidoALuces.setFont(new Font("Tahoma", Font.PLAIN, 35));
		frame.getContentPane().add(lblBienvenidoALuces);
		lblBienvenidoALuces.setHorizontalAlignment(0);
		 
		String textoLabel = "<html><body>Fuera Luces es un juego de Puzzles que consiste en intentar   apagar  todas las luces el tablero."
				+ " Las Instrucciones son <br>sencillas: Haciendo clic con el mouse en cada luz  "
				+ "cambia el estado (Prendida o Apagada) y  el de todas las que están"
				+ " alrededor <br>(Arriba, Abajo, Izquierda y Derecha).</body></html>";
		
		// Label Instrucciones del juego
		JLabel lblNewLabel = new JLabel(textoLabel);
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setBounds(102, 150, 743, 198);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 25));
		frame.getContentPane().add(lblNewLabel);
		lblNewLabel.setVerticalAlignment(SwingConstants.TOP);
		lblNewLabel.setHorizontalAlignment(0);
		
		JLabel lblEstasListo = new JLabel("Estas listo ?");
		lblEstasListo.setForeground(new Color(255, 255, 255));
		lblEstasListo.setBounds(230, 347, 432, 71);
		lblEstasListo.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblEstasListo.setHorizontalAlignment(SwingConstants.CENTER);
		frame.getContentPane().add(lblEstasListo);
		
		
		// Boton dificultad Media
		JButton medio = new JButton("Medio (4x4)");
		medio.setBounds(361, 466, 221, 51);
		frame.getContentPane().add(medio);
		medio.setFont(new Font("Tahoma", Font.PLAIN, 18));
		
		// Boton dificultad Dificil
		JButton dificil = new JButton("Dificil (5x5)");
		dificil.setBounds(613, 466, 221, 51);
		frame.getContentPane().add(dificil);
		dificil.setFont(new Font("Tahoma", Font.PLAIN, 18));
		
		// Boton dificultad Facil
		JButton facil = new JButton("Facil (3x3)");
		facil.setBounds(102, 466, 221, 51);
		frame.getContentPane().add(facil);
		facil.setFont(new Font("Tahoma", Font.PLAIN, 18));
		
		record = new JLabel();
		mejorMovim = 1000000;
		record.setVisible(false);
		
		
		
		// Opcion de Juego Facil
		
		facil.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				//Inicializa variable Movimientos
				contMovim = 0;
				tableroJuego = new Tablero(3);
				JDialog contenedorJuego = new JDialog();
				contenedorJuego.setVisible(true);
				JPanel panel = new JPanel();
				contenedorJuego.setBounds(0, 0, 651, 650);
				panel.setForeground(new Color(178, 34, 34));
				panel.setBackground(new Color(178, 34, 34));
				panel.setBounds(100, 100, 901, 650);
				contenedorJuego.getContentPane().add(panel);
				panel.setLayout(null);
				
				JLabel fueraLuces= new JLabel("Fuera Luces!");
				fueraLuces.setBounds(-20, 0, 720, 49);
				fueraLuces.setFont(new Font("Tahoma", Font.PLAIN, 35));
				fueraLuces.setForeground(new Color(255, 255, 255));
				panel.add(fueraLuces);
				fueraLuces.setHorizontalAlignment(0);
				
				movimientos = new JLabel("Movimientos = " + contMovim.toString());
				movimientos.setBounds(410, 0, 450, 300);
				movimientos.setFont(new Font("Tahoma", Font.PLAIN, 20));
				panel.add(movimientos);
				
				record = new JLabel("Record = " + mejorMovim.toString());
				record.setBounds(50, 0, 650, 300);
				record.setFont(new Font("Tahoma", Font.PLAIN, 20));
				panel.add(record);
				
				if(contadorVecesQueGano>0)
					record.setVisible(true);
				else
					record.setVisible(false);
								
				panel.setVisible(true);
									
				muestraBotones(panel);
				actualizaColor();
				
			
				for (int i = 0; i< tableroJuego.getColumnas(); i++)
				{
					for (int j = 0; j<tableroJuego.getFilas(); j++)
					{
						clickEnLuz(i, j, contenedorJuego);
					}
					
				}				
			}
				
		});

		// Opcion de Juego Medio
		
		medio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				contMovim = 0;
				tableroJuego = new Tablero(4);
				JDialog contenedorJuego = new JDialog();
				contenedorJuego.setVisible(true);
				JPanel panel = new JPanel();
				contenedorJuego.setBounds(0, 0, 800, 800);
				panel.setForeground(new Color(178, 34, 34));
				panel.setBackground(new Color(178, 34, 34));
				panel.setBounds(100, 100, 901, 1200);
				contenedorJuego.getContentPane().add(panel);
				panel.setLayout(null);
				JLabel fueraLuces= new JLabel("Fuera Luces!");
				fueraLuces.setBounds(84, 35, 720, 49);
				fueraLuces.setFont(new Font("Tahoma", Font.PLAIN, 35));
				fueraLuces.setForeground(new Color(255, 255, 255));
				panel.add(fueraLuces);
				fueraLuces.setHorizontalAlignment(0);
				
				movimientos = new JLabel("Movimientos = " + contMovim.toString());
				movimientos.setBounds(574, 25, 650, 300);
				movimientos.setFont(new Font("Tahoma", Font.PLAIN, 20));
				
				panel.add(movimientos);
				panel.setVisible(true);
				
				record = new JLabel("Record = " + mejorMovim.toString());
				record.setBounds(50, 0, 650, 300);
				record.setFont(new Font("Tahoma", Font.PLAIN, 20));
				panel.add(record);
				if(contadorVecesQueGano>0)
					record.setVisible(true);
				else
					record.setVisible(false);
				
				muestraBotones(panel);
				actualizaColor();
				
				for (int i = 0; i< tableroJuego.getColumnas(); i++)
				{
					for (int j = 0; j<tableroJuego.getFilas(); j++)
					{
						clickEnLuz(i, j, contenedorJuego);
					}
					
				}	
				
				
				
			}
				
		});

		// Opcion de Juego Dificil
		
		dificil.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				contMovim = 0;
				tableroJuego = new Tablero(5);
				JDialog contenedorJuego = new JDialog();
				contenedorJuego.setVisible(true);
				JPanel panel = new JPanel();
				contenedorJuego.setBounds(0, 0, 901, 900);
				panel.setForeground(new Color(178, 34, 34));
				panel.setBackground(new Color(178, 34, 34));
				panel.setBounds(100, 100, 901, 1200);
				contenedorJuego.getContentPane().add(panel);
				panel.setLayout(null);
				JLabel fueraLuces= new JLabel("Fuera Luces!");
				fueraLuces.setBounds(84, 35, 720, 49);
				fueraLuces.setFont(new Font("Tahoma", Font.PLAIN, 35));
				fueraLuces.setForeground(new Color(255, 255, 255));
				panel.add(fueraLuces);
				fueraLuces.setHorizontalAlignment(0);
				
				movimientos = new JLabel("Movimientos = " + contMovim.toString());
				movimientos.setBounds(674, 35, 650, 300);
				movimientos.setFont(new Font("Tahoma", Font.PLAIN, 20));
				
				record = new JLabel("Record = " + mejorMovim.toString());
				record.setBounds(50, 0, 650, 300);
				record.setFont(new Font("Tahoma", Font.PLAIN, 20));
				panel.add(record);
				if(contadorVecesQueGano>0)
					record.setVisible(true);
				else
					record.setVisible(false);
				
				panel.add(movimientos);
				panel.setVisible(true);
									
				muestraBotones(panel);
				actualizaColor();
				
				for (int i = 0; i< tableroJuego.getColumnas(); i++)
				{
					for (int j = 0; j<tableroJuego.getFilas(); j++)
					{
						clickEnLuz(i, j, contenedorJuego);
						
					}
					
				}	
			}
				
		});
	
	}

		
}	

