package grafico;


import java.awt.*;
import java.awt.event.*;

import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import javax.swing.*;
import app.Juego;
import elementos.CampoDeFuerza;
import valueobject.CampoDeFuerzaVO;
import valueobject.HitBoxVO;
import valueobject.ProyectilVO;

import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.plaf.ColorUIResource;

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
	private JLabel puntaje;
	private JLabel vidas;
	private int randomizador=0;

	private JPanel menuPanel;
	private Timer timer;

	
	public ventana(){
		c = this.getContentPane();
		eventos();
		c.setBackground(Color.BLACK);
		this.setSize(Juego.getInstancia().getAnchoPantalla(), Juego.getInstancia().getLargoPamtalla());
		this.setResizable(false);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		menuPanel = generarMenu();
		c.add(menuPanel);

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

		for(CampoDeFuerzaVO pared: Juego.getInstancia().getCampo()) {
			JLabel muro = new JLabel(new ImageIcon ("muroMedio.png"));
			muro.setBounds(pared.getPosicionX(), pared.getPosicionY(), pared.getAncho(), pared.getAlto());
			ListMuro.add(muro);
			muro.setVisible(true);
			c.add(muro);
		}
		
		ListProy= new ArrayList<JLabel>();
		
		puntaje= new JLabel();
		puntaje.setText("Puntos: "+ Juego.getInstancia().getJugador().getPuntaje());
		puntaje.setFont(new Font("OCR A Extended", Font.BOLD,14));
		puntaje.setBounds(350, 570, 150, 30);
		puntaje.setForeground(Color.WHITE);
		puntaje.setVisible(true);
		c.add(puntaje);
		
		vidas=new JLabel();
		vidas.setText("Vidas: "+ Juego.getInstancia().getJugador().vidasRestantes());
		vidas.setFont(new Font("OCR A Extended", Font.BOLD,14));
		vidas.setBounds(30, 570, 100, 30);
		vidas.setForeground(Color.WHITE);
		vidas.setVisible(true);
		c.add(vidas);
		
		Manejo refresco = new Manejo();
		timer = new Timer(20, refresco);
		timer.start();

	}

	class Manejo implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			//Manejo de colisiones
			Juego.getInstancia().chequearImpactos();					//AcÃƒÂ¡ debe recorrer todas las listas de JLabels de la ventana para eliminar las correspondientes.
			Juego.getInstancia().eliminarImpactados();								//Manejo de proyectiles, mover y dibujar
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

			//Manejo del dibujo de muros
			Iterator<JLabel> itrMuro = ListMuro.iterator();
			for (CampoDeFuerzaVO campo : Juego.getInstancia().getCampo()){
				JLabel campoLabel = itrMuro.next();
				campoLabel.setBounds(campo.getPosicionX() + 10, campo.getPosicionY(), campo.getAncho(), campo.getAlto());					campoLabel.setVisible(true);
			}
			while(itrMuro.hasNext()){
				itrMuro.next().setVisible(false); 
				}
		

			//Manejo de enemigos, movimiento y dibujar. Control del nuevo nivel y juego terminado.
			Juego.getInstancia().moverEnemigos();
			if(Juego.getInstancia().hayEnemigos()) {
				Iterator<JLabel> itr = enemigosJL.iterator();
				for (HitBoxVO enemigo : Juego.getInstancia().getEnemigos()) {
					JLabel enemigoLabel = itr.next();
					enemigoLabel.setBounds(enemigo.getPosicionX(), enemigo.getPosicionY(), 32, 32);
					if(enemigo.getPosicionY()>=Juego.getInstancia().getJugador().getPosicionY()) {
						JOptionPane.showMessageDialog(c, "Tu puntaje final es de " + Juego.getInstancia().getJugador().getPuntaje() + "puntos", "Perdiste!", JOptionPane.INFORMATION_MESSAGE);
						System.exit(0);
					}
				}
				while(itr.hasNext()) {
					itr.next().setVisible(false);
				}
			} else if(Juego.getInstancia().getNivel()<3&&Juego.getInstancia().getJugador().vidasRestantes()>0){
				Juego.getInstancia().siguienteNivel();

				ListMuro.removeAll(ListMuro);
				for(CampoDeFuerzaVO pared: Juego.getInstancia().getCampo()) {
					JLabel muro = new JLabel(new ImageIcon ("muroMedio.png"));
					muro.setBounds(pared.getPosicionX(), pared.getPosicionY(), pared.getAncho(), pared.getAlto());
					ListMuro.add(muro);
					muro.setVisible(true);
					c.add(muro);
				}

				JOptionPane.showMessageDialog(null, "Has ganado 500 puntos. Continuas?", "Has pasado de nivel!", JOptionPane.WARNING_MESSAGE);
				Iterator<JLabel> itr = enemigosJL.iterator();
				for (HitBoxVO enemigo : Juego.getInstancia().getEnemigos()) {
					JLabel enemigoLabel = itr.next();
					enemigoLabel.setBounds(enemigo.getPosicionX(), enemigo.getPosicionY(), 32, 32);
					enemigoLabel.setVisible(true);
				}
			}
			else {
				if(Juego.getInstancia().getJugador().vidasRestantes()>0)
					JOptionPane.showMessageDialog(c, "Tu puntaje final es de " + Juego.getInstancia().getJugador().getPuntaje() + "puntos", "Ganaste!", JOptionPane.INFORMATION_MESSAGE);
				else
					JOptionPane.showMessageDialog(c, "Tu puntaje final es de " + Juego.getInstancia().getJugador().getPuntaje() + "puntos", "Perdiste!", JOptionPane.INFORMATION_MESSAGE);
				System.exit(0);
			}

			//Actualización del mensaje de puntaje y de vidas del jugador
			puntaje.setText("Puntos: "+ Juego.getInstancia().getJugador().getPuntaje());
			vidas.setText("Vidas: "+ Juego.getInstancia().getJugador().vidasRestantes());
			
			//Manejo del disparo de los enemigos. Sólo disparan si logran aumentar al randomizador a más de 500.
			if(randomizador > 100) {
				Juego.getInstancia().dispararEnemigo();
				JLabel tiro = new JLabel();
				ListProy.add(tiro);
				c.add(tiro);
				randomizador=0;
			}
			else {
				randomizador= randomizador + (int) (Math.random()*Juego.getInstancia().getDistancia_mov_enem())+1;
			}
		
		}

	}

	private void eventos() {
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.addKeyListener(new EventoTeclas());
		//this.add
	}


	class EventoTeclas implements KeyListener{
		public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub

			int tecla = e.getKeyCode();
			//Movimiento del jugador
			if(timer.isRunning()) {
				if(tecla == KeyEvent.VK_LEFT) {
					Juego.getInstancia().getJugador().moverseEjeX(-5);
					naveJugador.setBounds(Juego.getInstancia().getJugador().getPosicionX(), Juego.getInstancia().getJugador().getPosicionY(), 32, 32);
				} else if (tecla == KeyEvent.VK_RIGHT) {
					Juego.getInstancia().getJugador().moverseEjeX(5);
					naveJugador.setBounds(Juego.getInstancia().getJugador().getPosicionX(), Juego.getInstancia().getJugador().getPosicionY(), 32, 32);
				}

			}
			//Pausa
			if (tecla == KeyEvent.VK_ESCAPE) {
				if(timer.isRunning()) {
					timer.stop();
					menuPanel.setVisible(true);
				}
				else {
					menuPanel.setVisible(false);
					timer.start();
				}
				System.out.println("Juego en pausa");
			}

		}

		@Override
		public void keyReleased(KeyEvent e) {
			int tecla=e.getKeyCode(); 
			//Disparo
			if(tecla==KeyEvent.VK_SPACE) {
				Juego.getInstancia().dispararJugador();
				JLabel misil = new JLabel(new ImageIcon("misil.png"));
				ListProy.add(misil);
				c.add(misil);
			}
			
		}

		@Override
		public void keyTyped(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}
	}

	public JPanel generarMenu() {
		JPanel menuPanel = new JPanel(new BorderLayout());

		int ancho = Juego.getInstancia().getAnchoPantalla() - Juego.getInstancia().getAnchoPantalla()/5;
		int alto = Juego.getInstancia().getLargoPamtalla() - Juego.getInstancia().getLargoPamtalla()/5;

		menuPanel.setBounds(Juego.getInstancia().getAnchoPantalla()/10, Juego.getInstancia().getLargoPamtalla()/10, ancho, alto);
		menuPanel.setBackground(Color.white);
		menuPanel.setVisible(false);

		JButton botonJugar = crearBotonMenu("Continuar", ancho, alto);
		botonJugar.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) {
				timer.start();
				menuPanel.setVisible(false);
			}

			@Override
			public void mousePressed(MouseEvent e) {

			}

			@Override
			public void mouseReleased(MouseEvent e) {

			}

			@Override
			public void mouseEntered(MouseEvent e) {

			}

			@Override
			public void mouseExited(MouseEvent e) {

			}
		});
		menuPanel.add(botonJugar, BorderLayout.NORTH);

	/*	JButton botonExtra = crearBotonMenu("Extra", ancho, alto);
		menuPanel.add(botonExtra, BorderLayout.CENTER);*/ 

		JButton botonSalir = crearBotonMenu("Salir", ancho, alto);
		botonSalir.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JOptionPane.showMessageDialog(c, "Tu puntaje final es de " + Juego.getInstancia().getJugador().getPuntaje() + "puntos", "Ganaste!", JOptionPane.INFORMATION_MESSAGE);
				System.exit(0);
			}

			@Override
			public void mousePressed(MouseEvent e) {

			}

			@Override
			public void mouseReleased(MouseEvent e) {

			}

			@Override
			public void mouseEntered(MouseEvent e) {

			}

			@Override
			public void mouseExited(MouseEvent e) {

			}
		});
		menuPanel.add(botonSalir, BorderLayout.SOUTH);



		return menuPanel;
	}

	private JButton crearBotonMenu(String str, int alto, int ancho) {
		JButton boton = new JButton(str);
		//boton.setContentAreaFilled(false);
		boton.setEnabled(false);
		boton.setBackground(Color.black);
		boton.setForeground(Color.white);
		boton.setFont(new Font("OCR A Extended", Font.BOLD,14));
		boton.setFocusPainted(false);
		boton.setPreferredSize(new Dimension(ancho, alto/3));
		boton.setBorder(new LineBorder(Color.white));
		boton.setSelected(false);

		boton.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				boton.setBackground(Color.black);
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				boton.setBackground(Color.darkGray);
			}
			public void mouseEntered(MouseEvent e) {
				boton.setBackground(Color.darkGray);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				boton.setBackground(Color.black);
			}
		});

		return boton;


	}
}
