package clases;

//DIAMANTE AZUL
import implementacion.Kanoha;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.shape.Rectangle;

public class Item {

	private int x;
	private int y;
	/*private int ancho;
	private int alto;*/
	private String indiceImagen;
	private boolean capturado;
	
	public Item(int x, int y, int ancho, int alto, String indiceImagen) {
		super();
		this.x = x;
		this.y = y;
		/*this.ancho = ancho;
		this.alto = alto;*/
		this.indiceImagen = indiceImagen;
	}
	
	public void pintar(GraphicsContext graficos) {
		if(!capturado)
			graficos.drawImage(Kanoha.imagenes.get(indiceImagen), this.x, this.y);
		//graficos.fillRect(this.x, this.y, 18, 18);
	}
	
	//esfera item
	/*public Rectangle obtenerRectangulo() {
		return new Rectangle(this.x, this.y, 18, 18);
	}*/
	
	//Posicion 1
	public Rectangle obtenerRectangulo() {
		return new Rectangle(this.x, this.y, 34, 36);
	}
	
	
	public boolean isCapturado(){
		return capturado;
	}
	
	public void setCapturado(boolean capturado) {
		this.capturado = capturado;
	}
	
}
