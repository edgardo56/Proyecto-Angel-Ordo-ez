package clases;

import implementacion.Kanoha;
import javafx.scene.canvas.GraphicsContext;

public class Tile {
	
	private int x;
	private int y;
	//PARAMETRO DENTRO DE LA IMAGEN PRINCIPAL
	private int altoImagen;
	private int anchoImagen;
	private int xImagen;
	private int yImagen;
	private int velocidad;
	private String indiceImagen;
		
	public Tile(int x, int y, int anchoImagen, int altoImagen, int xImagen, int yImagen, String indiceImagen, int velocidad) {
		super();
		this.x = x;
		this.y = y;
		this.altoImagen = altoImagen;
		this.anchoImagen = anchoImagen;
		this.xImagen = xImagen;
		this.yImagen = yImagen;
		this.indiceImagen = indiceImagen;
		this.velocidad = velocidad;	
	}

	public Tile() {}
	
	public Tile(int tipoTile, int x, int y, String indiceImagen, int velocidad) {
		this.x = x;
		this.y = y;
		this.indiceImagen = indiceImagen;
		this.velocidad = velocidad;

		
		//tilesMap
		
		switch(tipoTile) {
		
		case 1:	//COMIENZO
			this.altoImagen = 76;
			this.anchoImagen = 76;
			this.xImagen = 184;
			this.yImagen = 18;
		break;
		
		case 2:	// SESPED ALTO
			this.altoImagen = 76;
			this.anchoImagen = 76;
			this.xImagen = 184;
			this.yImagen = 272;
		break;
		
		case 3:	//COMIENZO SESPED ALTO
			this.altoImagen = 76;
			this.anchoImagen = 76;
			this.xImagen = 97;
			this.yImagen = 272;
		break;
		
		case 4:	//FINAL SESPED ALTO
			this.altoImagen = 76;
			this.anchoImagen = 76;
			this.xImagen = 268;
			this.yImagen = 272;
		break;

		case 5:	//ARBUSTO
			this.altoImagen = 76;
			this.anchoImagen = 76;
			this.xImagen = 570;
			this.yImagen = 266;
		break;
		
		case 6:	//HONGOS
			this.altoImagen = 76;
			this.anchoImagen = 76;
			this.xImagen = 610;
			this.yImagen = 1;
		break;
		
		case 7:	//CAJA
			this.altoImagen = 76;
			this.anchoImagen = 76;
			this.xImagen = 437;
			this.yImagen = 5;
		break;
		
		case 8:	//CAJA
			this.altoImagen = 76;
			this.anchoImagen = 76;
			this.xImagen = 268;
			this.yImagen = 103;
		break;
		
		case 9:	//INFERIOR 
			this.altoImagen = 77;
			this.anchoImagen = 77;
			this.xImagen = 183;
			this.yImagen = 102;
		break;
		
		case 10:	//INFERIOR 
			this.altoImagen = 76;
			this.anchoImagen = 76;
			this.xImagen = 184;
			this.yImagen = 189;
		break;
		
		}
	
	}

	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public int getAnchoImagen() {
		return anchoImagen;
	}
	public void setAnchoImagen(int anchoImagen) {
		this.anchoImagen = anchoImagen;
	}
	public int getAltoImagen() {
		return altoImagen;
	}
	public void setAltoImagen(int altoImagen) {
		this.altoImagen = altoImagen;
	}
	public int getxImagen() {
		return xImagen;
	}
	public void setxImagen(int xImagen) {
		this.xImagen = xImagen;
	}
	public int getyImagen() {
		return yImagen;
	}
	public void setyImagen(int yImagen) {
		this.yImagen = yImagen;
	}
	public int getVelocidad() {
		return velocidad;
	}
	public void setVelocidad(int velocidad) {
		this.velocidad = velocidad;
	}

	public String getIndiceImagen() {
		return indiceImagen;
	}
	public void setIndiceImagen(String indiceImagen) {
		this.indiceImagen = indiceImagen;
	}
	
	
	public void pintar(GraphicsContext graficos) {
		graficos.drawImage(
				Kanoha.imagenes.get(this.indiceImagen),
				this.xImagen, this.yImagen,
				this.anchoImagen, this.altoImagen,
				this.x--, this.y,
				this.anchoImagen, this.altoImagen
				);
		
		
	}
}

