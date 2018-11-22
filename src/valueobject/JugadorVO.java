package valueobject;


public class JugadorVO extends HitBoxVO {
	private int puntaje;
	private int vida;
	
	
	public JugadorVO(int x,int y, int puntos, int vidas) {
		super(32, 32, x, y);
		this.vida = vidas;
		this.puntaje=puntos;	
	}
	
	public int vidasRestantes() {
		return this.vida;
	}

	public int getPuntaje() {
		return puntaje;
	}

	
	
	
}
