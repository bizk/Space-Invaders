package valueobject;

public class CampoDeFuerzaVO {
	private int vida;
	private boolean existe;
	
	public  CampoDeFuerzaVO(int lives,boolean exist) {
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
