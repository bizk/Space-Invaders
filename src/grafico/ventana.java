package grafico;

import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.Timer;

import app.Juego;
import naves.Enemigo;


public class ventana extends JFrame {
	//private JButton btndos, btnuno, btntres;
	//private JTextField txtMensaje;
	
	private JLabel naveEnemiga1;
	private JLabel naveEnemiga2;
	private JLabel naveEnemiga3;
	private JLabel naveJugador;
	private JLabel camposFuerza;
	private Container c;
	private ArrayList<JLabel> enemigosJL;
	
	public ventana(){
		c = this.getContentPane();
		configurar();
		eventos();
		this.setSize(Juego.getInstancia().getAnchoPantalla(), Juego.getInstancia().getLargoPamtalla());
		this.setResizable(false);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//Esto no va
		jugar();
	}
	
	public void jugar() {
		int x = Juego.getInstancia().getJugador().getPosicionX();
		int y = Juego.getInstancia().getJugador().getPosicionY();

		ImageIcon nave = new ImageIcon("nave.png");
		this.naveJugador = new JLabel(nave);
		this.naveJugador.setBounds(x, y, 32, 32);
		c.add(naveJugador);
		
		enemigosJL = new ArrayList<JLabel>();
		ImageIcon enemigo1 = new ImageIcon("enemigoA.png");
		for (Enemigo enemigo : Juego.getInstancia().getEnemigos()) {
			JLabel enem = new JLabel(enemigo1);
			enem.setBounds(enemigo.getPosicionX(), enemigo.getPosicionY(), 32, 32);
			enemigosJL.add(enem);
			c.add(enem);
		}
		MovimientoEnemigo movEnem = new MovimientoEnemigo();
		Timer timer = new Timer(Juego.getInstancia().getTIEMPO_MOVIMIENTO_ENEMIGOS(), movEnem);
		timer.start();
	}
	
	class EventoTeclas implements KeyListener{
		public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
			int tecla = e.getKeyCode();	
			if(tecla == KeyEvent.VK_LEFT) {
				Juego.getInstancia().getJugador().moverseEjeX(-5);
				naveJugador.setBounds(Juego.getInstancia().getJugador().getPosicionX(), Juego.getInstancia().getJugador().getPosicionY(), 32, 32);
				System.out.println(Juego.getInstancia().getJugador().getPosicionX());
			}
			if (tecla == KeyEvent.VK_RIGHT) {
				Juego.getInstancia().getJugador().moverseEjeX(5);
				naveJugador.setBounds(Juego.getInstancia().getJugador().getPosicionX(), Juego.getInstancia().getJugador().getPosicionY(), 32, 32);
				System.out.println(Juego.getInstancia().getJugador().getPosicionX());
			} 
			if (tecla == KeyEvent.VK_ESCAPE) System.out.println("Juego terminado");
		}

		@Override
		public void keyReleased(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void keyTyped(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}
	}
	
	class MovimientoEnemigo implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			// No estoy convencido si es la mejor manera, pero funciona.
			int x, y;
			Iterator<JLabel> itr = enemigosJL.iterator();
			for (Enemigo enemigo : Juego.getInstancia().getEnemigos()) {
				System.out.print("- x:" + enemigo.getPosicionX());
				System.out.println();
				JLabel enemigoLabel = itr.next();
				enemigoLabel.setBounds(enemigo.getPosicionX(), enemigo.getPosicionY(), 32, 32);
			}
		}
		
	}
	
	private void eventos() {
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.addKeyListener(new EventoTeclas());
		//this.add
	}
	
	private void configurar(){
		c = this.getContentPane();
		
		c.setBackground(Color.BLACK);
		ImageIcon enemigo1 = new ImageIcon("enemigoA.png");
		ImageIcon enemigo2 = new ImageIcon("enemigoB.png");
		ImageIcon enemigo3 = new ImageIcon("enemigoC.png");
		ImageIcon nave = new ImageIcon("nave.png");
		
		/*
		 for(int i=0;i<50;i++){ //spawn PRUEBA SEGUNDA FILA
		 naveEnemiga2=new JLabel(enemigo2);
		naveEnemiga2.setBounds(j, j, 32, 32);//mantener los 32 por el tama�o de imagen
		c.add(naveEnemiga2);
		 }
		 */
		
		/*
		 for(int i=0;i<50;i++){ //spawn PRUEBA TECERA FILA
		 naveEnemiga3=new JLabel(enemigo3);
		naveEnemiga3.setBounds(j, j, 32, 32);//mantener los 32 por el tama�o de imagen
		c.add(naveEnemiga3);
		 }
		 */
		
		
		/*
		 for(int i=0;i<5;i++){ //spawn PRUEBA CAMPOS DE FUERZA
		 campoDeFuerza=new JLabel(***BUSCAR PNG DE 32X32***);
		campoDeFuerza.setBounds(j, j, 32, 32);//mantener los 32 por el tama�o de imagen
		c.add(campoDeFuerza);
		 }
		 */
		
		//navePrincipal=new JLabel(nave);  //spawn PRUEBA NAVE PRINCIPAL
		//navePrincipal.setBounds(240, 322, 32, 32);
		//c.add(navePrincipal);
		
		 // addKeyListener(this); //falta a�adir uno q ande
		
		
	}

}
