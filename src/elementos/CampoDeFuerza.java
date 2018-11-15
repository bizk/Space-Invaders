package elementos;
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


public class CampoDeFuerza extends HitBox {
	private int vida;
	private boolean existe;
	
	public CampoDeFuerza(int an, int al, int x, int y){
		super(an, al, x, y);
		this.vida = 100;
		this.existe = true;
	}
	
	public void reAparecer() {
		this.vida = 100;
		this.existe = true;
	}
	
	public void serDanado(int dano) {
		this.vida -= dano;
		if(this.vida <= 0) this.setImpactada(true);
	}
	
	public void desaparecer() {
		this.vida = 0;
		this.existe = false;
	}
	
	public boolean getExiste() {
		return this.existe;
	}
}
