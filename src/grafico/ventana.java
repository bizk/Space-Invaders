package grafico;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;


public class ventana extends JFrame {
	
	//private JButton btndos, btnuno, btntres;
	//private JTextField txtMensaje;
	
	private JLabel naveEnemiga1;
	private JLabel naveEnemiga2;
	private JLabel naveEnemiga3;
	private JLabel navePrincipal;
	private JLabel camposFuerza;
	
	
	public ventana(){
		configurar();
		this.setSize(250*2, 322*2);
		this.setResizable(false);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	private void configurar(){
		Container c = this.getContentPane();
		
		c.setBackground(Color.BLACK);
		ImageIcon enemigo1 = new ImageIcon("enemigoA.png");
		ImageIcon enemigo2 = new ImageIcon("enemigoB.png");
		ImageIcon enemigo3 = new ImageIcon("enemigoC.png");
		ImageIcon nave = new ImageIcon("nave.png");
		
		
		for(int i=0;i<50;i++){  //spawn PRUEBA PRIMERA FILA
			int j=50;
		naveEnemiga1=new JLabel(enemigo1);
		naveEnemiga1.setBounds(j, j, 32, 32);//mantener los 32 por el tamaño de imagen
		c.add(naveEnemiga1);
		j=j+32;
	
		}
		
		/*
		 for(int i=0;i<50;i++){ //spawn PRUEBA SEGUNDA FILA
		 naveEnemiga2=new JLabel(enemigo2);
		naveEnemiga2.setBounds(j, j, 32, 32);//mantener los 32 por el tamaño de imagen
		c.add(naveEnemiga2);
		 }
		 */
		
		/*
		 for(int i=0;i<50;i++){ //spawn PRUEBA TECERA FILA
		 naveEnemiga3=new JLabel(enemigo3);
		naveEnemiga3.setBounds(j, j, 32, 32);//mantener los 32 por el tamaño de imagen
		c.add(naveEnemiga3);
		 }
		 */
		
		
		/*
		 for(int i=0;i<5;i++){ //spawn PRUEBA CAMPOS DE FUERZA
		 campoDeFuerza=new JLabel(***BUSCAR PNG DE 32X32***);
		campoDeFuerza.setBounds(j, j, 32, 32);//mantener los 32 por el tamaño de imagen
		c.add(campoDeFuerza);
		 }
		 */
		
		navePrincipal=new JLabel(nave);  //spawn PRUEBA NAVE PRINCIPAL
		navePrincipal.setBounds(240, 322, 32, 32);
		c.add(navePrincipal);
		

		 // addKeyListener(this); //falta añadir uno q ande
		
		
	}

}
