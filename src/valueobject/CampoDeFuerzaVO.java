package valueobject;


import elementos.HitBox;
import valueobject.HitBoxVO;

public class CampoDeFuerzaVO extends HitBoxVO {
	private int vida;
	private boolean existe;
	
	public  CampoDeFuerzaVO(int lives,boolean exist, int ancho, int alto, int x, int y) {
		super(ancho, alto, x, y);
		this.vida=lives;
		this.existe=exist;
	}

	
	public boolean getExiste() {
		return this.existe;
	}
	
	public int getVida() {
		return this.vida;
	}
	
	
}
