package naves;
import app.Juego;
import elementos.HitBox;
import elementos.Proyectil;


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




public abstract class Nave extends HitBox {
	protected int identificador;
	protected boolean vivo;
	
	public Nave(int an, int al, int x, int y) {
		super(an, al, x, y);
		this.identificador = 213421;
		this.vivo = true;
	}
	
	public Proyectil disparar(int direccion) {
		Proyectil proyectil = new Proyectil(super.getPosicionX(), super.getPosicionY(),direccion);
		return proyectil;
	}
	
	public void moverseEjeX(int x) {
		//ESTO NO ESTA BIEN
		super.setPosicionX(super.getPosicionX() + x);
	}
	
	public void morir() {
		this.vivo = false;
	}
	
	public void spawn(int posicionX, int posicionY) {
		super.setPosicionX(posicionX);
		super.setPosicionY(posicionY);
		this.vivo = true;
	}
	
	public boolean estaVivo() {
		return this.vivo;
	}
}
