package tests;

import java.util.Scanner;

import app.Juego;

public class pruebasMixtas {
	// Test 01 - leer teclado de forma continua
	public static void main(String[] args) {
		Juego juego = Juego.getInstancia();
		juego.nuevoJuego();
		
		Scanner scanner = new Scanner(System.in);	
		while(true) {
			System.out.println("Escribi algo mono:");
			String input = scanner.nextLine();
			juego.movimientoJugador(input);
			if("q".equals(input)) {
				juego.terminarJuego();
				break;
			}
			//juego.siguienteNivel();
		}
		
		System.out.println("\n### Programa terminado con exito ###");
	}
}
