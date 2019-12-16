package clases;

import javafx.scene.shape.Rectangle;

public class Animacion {

	private String nombreAnimacion;
	private Rectangle coordenadasImagenes[];
	private int cantidadFrames;
	private int frameActual = 0;
	private static double duracion;		//DURACION PARA CAMBIAR ENTRE CADA FRAGMENTO DE IMAGEN
	
	public Animacion(String nombreAnimacion, Rectangle[] coordenadasImagenes, double duracion) {
		super();
		this.nombreAnimacion = nombreAnimacion;
		this.coordenadasImagenes = coordenadasImagenes;
		this.duracion = duracion;
	}

	public String getNombreAnimacion() {
		return nombreAnimacion;
	}
	public void setNombreAnimacion(String nombreAnimacion) {
		this.nombreAnimacion = nombreAnimacion;
	}
	public Rectangle[] getCoordenadasImagenes() {
		return coordenadasImagenes;
	}
	public void setCoordenadasImagenes(Rectangle[] coordenadasImagenes) {
		this.coordenadasImagenes = coordenadasImagenes;
	}
	public int getCantidadFrames() {
		return cantidadFrames;
	}
	public void setCantidadFrames(int cantidadFrames) {
		this.cantidadFrames = cantidadFrames;
	}
	public int getFrameActual() {
		return frameActual;
	}
	public void setFrameActual(int frameActual) {
		this.frameActual = frameActual;
	}
	public double getDuracion() {
		return duracion;
	}
	public static void setDuracion(double duracion) {
		duracion = duracion;
	}
	
	//PARA PODER ANIMAR EL PERSONAJE EL frameActual DEBE SER DINAMICO
	
	public Rectangle calcularFrame(double t) {
		this.cantidadFrames = coordenadasImagenes.length;
		this.frameActual = (int)((t % (cantidadFrames * duracion))/duracion);
		
		//System.out.println("Duracion " + duracion);
		System.out.println(frameActual);		//solo esque quiero ver que imagen esta en momento
		return coordenadasImagenes[frameActual];
	}
	
	
	
	
}