package clases;

import java.io.Serializable;

public class Player implements Serializable{

	private static String nombrePlayer;
	private static int puntuacion;
	
	public Player(String nombrePlayer, int puntuacion) {
		this.nombrePlayer = nombrePlayer;
		this.puntuacion = puntuacion;
	}

	public Player() {}
	
	public static String getNombrePlayer() {
		return nombrePlayer;
	}
	public void setNombre(String nombrePlayer) {
		this.nombrePlayer = nombrePlayer;
	}
	public static int getPuntuacion() {
		return puntuacion;
	}
	public void setPuntuacion(int puntuacion) {
		this.puntuacion = puntuacion;
	}
	
	
	

	@Override
	public String toString() {
		return "Player [getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString()
				+ "]";
	}

	public static String toCSV() {
		return getNombrePlayer()+","+getPuntuacion()+"\n";
	}

	
}
