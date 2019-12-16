package clases;

import java.util.HashMap;

import implementacion.Kanoha;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.shape.Rectangle;


public class ItemMalo extends Herencia {

	private String indiceImagen;
	private boolean capturado;
	public static String animacionActual;
	private HashMap<String, Animacion> animaciones;
	private int xImagen;
	private int yImagen;
	private int anchoImagen;
	private int altoImagen;	
		
	public ItemMalo(int x, int y, int ancho, int alto, String indiceImagen, int velocidad, String animacionActual) {
		super(x,y,ancho,alto,velocidad);
		this.indiceImagen = indiceImagen;
		this.capturado = capturado;
		this.animacionActual=animacionActual;
		inicializarAnimaciones();
	}

	public void actualizarAnimacion(double t) {
		Rectangle coordenadasActuales = this.animaciones.get(animacionActual).calcularFrame(t);
		this.xImagen = (int)coordenadasActuales.getX();
		this.yImagen = (int)coordenadasActuales.getY();
		this.anchoImagen = (int)coordenadasActuales.getWidth();
		this.altoImagen = (int)coordenadasActuales.getHeight();
		
	}
	
	public void inicializarAnimaciones() {
			animaciones = new HashMap<String, Animacion>();
			Rectangle coordenadasMover[]= {
					
					//new Rectangle(460, 738, 46, 41),			//Cubo AMARILLO
					
					new Rectangle(1, 0, 43, 44),				//CUBO ROJO
					new Rectangle(46, 0, 45, 44),
					new Rectangle(323, 0, 45, 44)		
		
			};
			
			Animacion animacionMover = new Animacion("mover",coordenadasMover,0.5);
			animaciones.put("mover",animacionMover);
	}
	
	

	public String getIndiceImagen() {
		return indiceImagen;
	}

	public void setIndiceImagen(String indiceImagen) {
		this.indiceImagen = indiceImagen;
	}


	public void mover(){
		if (Kanoha.derecha)
			setX(getX()-getVelocidad());
	}
	
	public void pintar(GraphicsContext graficos) {
		if (!capturado)
			graficos.drawImage(
					Kanoha.imagenes.get(this.indiceImagen), 
					this.xImagen, this.yImagen, 
					this.anchoImagen, this.altoImagen,
					getX(), getY(),
					this.anchoImagen, this.altoImagen
			);
		//graficos.fillRect(this.x, this.y, 18, 18);
	}
	
	public Rectangle obtenerRectangulo() {
		return new Rectangle(getX(), getY(), this.anchoImagen, this.altoImagen);
	}

	public boolean isCapturado() {
		return capturado;
	}

	public void setCapturado(boolean capturado) {
		this.capturado = capturado;
	}
	
	
}