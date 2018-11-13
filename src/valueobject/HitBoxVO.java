package valueobject;

public class HitBoxVO {
	private int ancho;
	private int alto;
	private int posicionX;
	private int posicionY;
	
	public HitBoxVO(int an, int al, int x, int y) {
		this.ancho= an;
		this.alto= al;
		this.posicionX=x;
		this.posicionY=y;
	}

	public int getAncho() {
		return ancho;
	}

	public int getAlto() {
		return alto;
	}

	public int getPosicionX() {
		return posicionX;
	}

	public int getPosicionY() {
		return posicionY;
	}



}
