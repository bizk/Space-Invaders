package valueobject;

import elementos.HitBox;
import valueobject.HitBoxVO;

public class ProyectilVO extends HitBoxVO {
	private int velocidadProyectil;
	private int direccionProyectilY;
	
	
	public  ProyectilVO(int x,int y,int vel,int dir) {
	super(5, 16, x, y); //crea la hitbox del proyectil
	this.velocidadProyectil=vel;
	this.direccionProyectilY=dir;
	
	}
	public int getVelocidad() {
		return this.velocidadProyectil;
	}
	public int getDireccion() {
		return this.direccionProyectilY;
	}
	
}
