package naves;
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
	
	public Proyectil disparar() {
		Proyectil proyectil = new Proyectil(super.getPosicionX(), super.getPosicionY());
		return proyectil;
				//Falta el lugar de inciio del proyectil
	}
	
	/*
	public Void moverse() {
	
	}
	*/
	public void morir() {
		this.vivo = false;
	}
	
	public void spawn() {
		this.vivo = true;
	}
	
	public boolean estaVivo() {
		return this.vivo;
	}
}
