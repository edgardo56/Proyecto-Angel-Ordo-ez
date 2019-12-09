package clases;

import implementacion.Kanoha;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.shape.Rectangle;

//ITEM CUBO ROJO

public class ItemMalo {

	private int x;
	private int y;
	/*private int ancho;
	private int alto;*/
	private String indiceImagen;
	private boolean capturado2;
	
	public ItemMalo(int x, int y, int ancho, int alto, String indiceImagen) {
		super();
		this.x = x;
		this.y = y;
		/*this.ancho = ancho;
		this.alto = alto;*/
		this.indiceImagen = indiceImagen;
	}
	
	public void pintar(GraphicsContext graficos) {
		if(!capturado2)
			graficos.drawImage(Kanoha.imagenes.get(indiceImagen), this.x, this.y);
		//graficos.fillRect(this.x, this.y, 18, 18);
	}
	
	//esfera item
	/*public Rectangle obtenerRectangulo() {
		return new Rectangle(this.x, this.y, 18, 18);
	}*/
	
	//Posicion 1
	public Rectangle obtenerRectangulo() {
		return new Rectangle(this.x, this.y, 36, 34);
	}
	
	
	public boolean isCapturado2(){
		return capturado2;
	}
	
	public void setCapturado2(boolean capturado) {
		this.capturado2 = capturado;
	}
	
	
	
}
