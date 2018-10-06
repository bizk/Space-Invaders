package app;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;

import elementos.CampoDeFuerza;
import naves.Enemigo;
import naves.Jugador;

//
//
//  Generated by StarUML(tm) Java Add-In
//
//  @ Project : Trabajo Practico Obligatorio
//  @ File Name : space Invaders v1.0
//  @ Date : 2/10/2018
//  @ Author : Parodi Federico, Salvioli Santiago, Yanzon Carlos Santiago
//
//

public class Juego {
	private int nivel;
	private String Difficultad;
	private Jugador jugador;
	private int anchoPantalla;
	private int largoPamtalla;
	private int enemigoSpawnX;
	private int enemigoSpawnY;
	private ArrayList<Enemigo> enemigos;
	private ArrayList<CampoDeFuerza> camposDeFuerza;
	
	public Juego() {
		this.anchoPantalla = 100;
		this.largoPamtalla = 30;
		this.enemigoSpawnX = 4;
		this.enemigoSpawnY = 26;
	}
	
	//Revisar si va aca
	// ###########################
	public void movimientoJugador(String input) {
		if("a".equals(input) && estaEnLaPantalla(jugador.getPosicionX()-1, jugador.getPosicionY())) {
			jugador.setPosicionX(jugador.getPosicionX()-1);
		} else if ("s".equals(input) && estaEnLaPantalla(jugador.getPosicionX()+1, jugador.getPosicionY())) {
			jugador.setPosicionX(jugador.getPosicionX()+1);
		}
		System.out.println("[" + jugador.getPosicionX() + ":" + jugador.getPosicionY() + "]");
	}
	// ###########################
	
	public void nuevoJuego() {
		this.jugador = new Jugador("PLAYER 1", 0, 0);
		enemigos = new ArrayList<Enemigo>();
		camposDeFuerza = new ArrayList<CampoDeFuerza>();
		spawnEnemigos();
		spawnCamposDeFuerza();
		jugador.spawn();
		
		//Timer
		// 1000 = 1 seg
		//*****************************************************************
		javax.swing.Timer timer = new javax.swing.Timer(500, new ActionListener() { //mueve los enemigos automaticamente cada x segundos
			public void actionPerformed(ActionEvent e) {
				for (Enemigo enemigo : enemigos) { //recorremos enemigo por enemigo
					if(enemigo.getPosicionY() <= jugador.getPosicionY()) { //Si algun enemigo llega a la altura del jugador o menos se termina el juego
						if(jugador.vidasRestantes() >= 2) { //Si tiene mas de 2 vidas
							jugador.Reaparecer(); //Pierde una vida y reaparece a todos los enemigos y campos de fuerza
							
						} else {
							terminarJuego();
							break;
						}
					} else if(!estaEnLaPantalla(enemigo.getPosicionX() + 5, enemigo.getPosicionY())) { //mientras del eje x la posicion sea < al ancho
						for (Enemigo enem : enemigos) { //pasamos el limite movemos a cada enemigo para abajo
							System.out.print("("+ enem.getPosicionX()+":"+ enem.getPosicionY()+")");
							enem.setPosicionX(enemigoSpawnX);
							enem.setPosicionY(enem.getPosicionY() - 5);
						}
						System.out.println();
						break;
					} else {
						enemigo.setPosicionX(enemigo.getPosicionX() + 5); //Y si no. movemos al enemigo a la derecha
					}
				}
				if(jugador.vidasRestantes() >= 2) {
					spawnEnemigos();
					spawnCamposDeFuerza();	
				}

			}
		}
		);
		//******************************************************************
		
		timer.start();
	}
	
	public void terminarJuego() {
		//Elimina los enemigos y campos de fuerza
		enemigos.clear();
		camposDeFuerza.clear();
		
		System.out.println("Termino el Juego");
		System.out.println("Tu puntaje fue de: " + this.jugador.getPuntaje() + "pts");
	}
	
	/**
 	* @Funcion: siguienteNivel
 	* @Descripcion: pasa al siguiente nivel, respawnea  a los enemigos y campos de fuerza y le da el respectivo puntaje al jugador
 	* @Devuelve:
 	* @Parametros:
 	*/
	public void siguienteNivel() {
		this.nivel++;
		spawnEnemigos();
		spawnCamposDeFuerza();
		int aux = this.jugador.getPuntaje();
		this.jugador.setPuntaje(aux + 500);
	}
	
	/**
 	* @Funcion: estaEnLaPantalla
 	* @Descripcion: chequea si un objeto esta o no en el campo de juego
 	* @Devuelve: true/false
 	* @Parametros: posicion X e Y
 	*/
	public Boolean estaEnLaPantalla(int x, int y) {
		if(x <= anchoPantalla && x >= 0) {
			if(y <= largoPamtalla && y >= 0) return true;
			else return false;
		} else return false;
	}
	
	/**
 	* @Funcion: spawnEnemigos
 	* @Descripcion: Crea y ubica 15 enemigos en el mapa.
 	* @Devuelve:
 	* @Parametros:
 	*/
	private void spawnEnemigos() {
		int auxX = enemigoSpawnX; //De donde se va a ubicar el primer enemigo en el eje X
		int auxY = enemigoSpawnY; //De donde se va a ubicar el primer enemigo en el eje y

		//La funcion itera durante 15 veces y cada 5 enemigos, baja un lugar en y para formar filas
		for(int i = 0; i < 15; i++) {
			if(i == 0) {
				auxX = enemigoSpawnX;
				auxY = enemigoSpawnY;
			} else if(i == 5) {
				auxX = enemigoSpawnX;
				auxY = enemigoSpawnY - 5;
			} else if(i == 10) {
				auxX = enemigoSpawnX;
				auxY = enemigoSpawnY - 10;
			}
			Enemigo enemigo = new Enemigo(auxX, auxY);
			enemigos.add(enemigo);
			auxX += 5;
		}		
	}

	/**
 	* @Funcion: SpawnCamposDeFuerza
 	* @Descripcion: Crea y ubica los campos de fuerza
 	* @Devuelve: 
 	* @Parametros:
 	*/
	private void spawnCamposDeFuerza() {
		int auxX = 10;
		int auxY = 20;
		
		for (int i = 0; i < 4; i++) {
			CampoDeFuerza campo = new CampoDeFuerza(13, 6, auxX, auxY);
			camposDeFuerza.add(campo);
			auxX += 15;
		}
	}
	/*
	public Boolean esEnemigo(HitBox objeto) {
		while
	}
	
	public Boolean esCampoDeFuerza(HitBox objeto) {
		
	}
	
	public Boolean esJugador(HitBox objeto) {
		jugador
	}
	*/
}
