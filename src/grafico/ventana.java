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
import elementos.CampoDeFuerza;
import elementos.Proyectil;
import naves.Enemigo;
import naves.Nave;
import valueobject.HitBoxVO;
import valueobject.ProyectilVO;


public class ventana extends JFrame {
	//private JButton btndos, btnuno, btntres;
	//private JTextField txtMensaje;
	
	private JLabel naveEnemiga1;
	private JLabel naveEnemiga2;
	private JLabel naveEnemiga3;
	private JLabel naveJugador;
	private Container c;
	private ArrayList<JLabel> enemigosJL;
	private ArrayList<JLabel> ListMuro;
	private ArrayList<JLabel> ListProy;
	
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
		for (HitBoxVO enemigo : Juego.getInstancia().getEnemigos()) {
			JLabel enem = new JLabel(enemigo1);
			enem.setBounds(enemigo.getPosicionX(), enemigo.getPosicionY(), 32, 32);
			enemigosJL.add(enem);
			c.add(enem);
		}
		
		ListMuro= new ArrayList<JLabel>();
		
		for(CampoDeFuerza pared: Juego.getInstancia().getCampo()) {
			JLabel muro= new JLabel(new ImageIcon ("muroMedio.png"));
			muro.setBounds(pared.getPosicionX(),pared.getPosicionY(),32,32);
			ListMuro.add(muro);
			muro.setVisible(true);
			c.add(muro);
		}
		
		ListProy= new ArrayList<JLabel>();
		
		MovimientoEnemigo movEnem = new MovimientoEnemigo();
		Timer timer = new Timer(Juego.getInstancia().getTIEMPO_MOVIMIENTO_ENEMIGOS(), movEnem);
		timer.start();
		
		ManejoDisparo mandis = new ManejoDisparo();
		Timer timer2 = new Timer(200,mandis);
		timer2.start();
	
		ManejoColisiones mancol = new ManejoColisiones();
		Timer timer3 = new Timer(200, mancol);
		timer3.start();
		
		DisparoEnemigo DisparoEnem=new DisparoEnemigo();
		Timer timer4=new Timer(4000,DisparoEnem);
		timer4.start();
		
	c.repaint();
		
	}
	class DisparoEnemigo implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
				
					Juego.getInstancia().dispararEnemigo();
					JLabel misilEnem=new JLabel();
					ListProy.add(misilEnem);
					c.add(misilEnem);
	 			
				c.repaint();
			}
		}
	
	class ManejoColisiones implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			Juego.getInstancia().chequearImpactos();
			//Acá debe recorrer todas las listas de JLabels de la ventana para eliminar las correspondientes.
			Iterator<JLabel> it=ListProy.iterator();

			Juego.getInstancia().eliminarImpactados();
		}
		
	}
	class ManejoDisparo implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			Juego.getInstancia().moverProyectiles();								//Muevo todos los proyectiles en pantalla
			Iterator<JLabel> itproy= ListProy.iterator();							//Debo recorrer en paralelo ambas listas de proyectiles y de JLabels
			for(ProyectilVO tiro : Juego.getInstancia().getListaProyectiles()) {		//Por cada nuevo proyectil
				JLabel aux= (JLabel) itproy.next();									//cambio al JLabel siguiente
				aux.setBounds(tiro.getPosicionX(), tiro.getPosicionY(), 16, 16);
				aux.setVisible(true);
				if(tiro.getDireccion()>0){
					aux.setIcon(new ImageIcon("misil2.png"));//
				}
				else{
					aux.setIcon(new ImageIcon("misil.png"));
				}
			}
			while(itproy.hasNext()){
				itproy.next().setVisible(false);
		}
	}
}	
 
	class EventoTeclas implements KeyListener{
		public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
			int tecla = e.getKeyCode();	
			if(tecla == KeyEvent.VK_LEFT) {
				Juego.getInstancia().getJugador().moverseEjeX(-5);
				naveJugador.setBounds(Juego.getInstancia().getJugador().getPosicionX(), Juego.getInstancia().getJugador().getPosicionY(), 32, 32);
				//System.out.println(Juego.getInstancia().getJugador().getPosicionX());
			}
			if (tecla == KeyEvent.VK_RIGHT) {
				Juego.getInstancia().getJugador().moverseEjeX(5);
				naveJugador.setBounds(Juego.getInstancia().getJugador().getPosicionX(), Juego.getInstancia().getJugador().getPosicionY(), 32, 32);
				//System.out.println(Juego.getInstancia().getJugador().getPosicionX());
			} 
			if(tecla==KeyEvent.VK_SPACE) {
				Juego.getInstancia().dispararJugador();
				JLabel misil = new JLabel(new ImageIcon("misil.png"));
				ListProy.add(misil);
				c.add(misil);				
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
			Juego.getInstancia().moverEnemigos();
			Iterator<JLabel> itr = enemigosJL.iterator();
			for (HitBoxVO enemigo : Juego.getInstancia().getEnemigos()) {
				JLabel enemigoLabel = itr.next();
				//System.out.print("- x:" + enemigo.getPosicionX());
				//System.out.println();
				enemigoLabel.setBounds(enemigo.getPosicionX(), enemigo.getPosicionY(), 32, 32);
			}
			while(itr.hasNext()) {
				itr.next().setVisible(false);
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
		
		
	}


}