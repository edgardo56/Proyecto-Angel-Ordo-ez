package clases;

import java.util.HashMap;

import javax.swing.JOptionPane;


import implementacion.Kanoha;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;

public class Personaje {
	
	public static int x;
	public static int y;
	private String indiceImagen;
	private int velocidad;
	private HashMap<String, Animacion> animaciones;
	
	public static String animacionActual;
	private int puntuacion = 0;
	private int vidas = 3;
	
	//VARIABLES PARA LAS COORDENADAS DE LA IMAGEN A PINTAR  " LAS DIFERENTES POSICIONES DE LOS ITEMS Y PDEL PERSONAJE"
	private int xImagen;
	private int yImagen;
	private int anchoImagen;
	private int altoImagen;
	
public Personaje(int x, int y, String indiceImagen, int velocidad, String animacionActual) {
		super();
		this.x = x;
		this.y = y;
		this.indiceImagen = indiceImagen;
		this.velocidad = velocidad;
		this.animacionActual = animacionActual;
		inicializarAnimaciones();
	}

	public int getVelocidad() {
		return velocidad;
	}
	public void setVelocidad(int velocidad) {
		this.velocidad = velocidad;
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
	public String getIndiceImagen() {
		return indiceImagen;
	}
	public void setIndiceImagen(String indiceImagen) {
		this.indiceImagen = indiceImagen;
	}
	
	

	//PARA OBTENER LAS COORDENADAS DEL FRAGMENTO DE LA IMAGEN A PINTAR
	public void actualizarAnimacion(double t) {
		Rectangle coordenadasActuales = this.animaciones.get(animacionActual).calcularFrame(t);
		this.xImagen = (int)coordenadasActuales.getX();
		this.yImagen = (int)coordenadasActuales.getY();
		this.anchoImagen = (int)coordenadasActuales.getWidth();
		this.altoImagen = (int)coordenadasActuales.getHeight();	
	}


	public void pintar(GraphicsContext graficos) {			//ESTOY USANDO EL METODO PINTAR DEL PRINCIPAL, PUEDO PORQUE NO ES static
		//EN CANVAS QUE LE PUSE graficos QUIERO AGREGAR UNA IMAGEN DEL PERSONAJE	drawImage -> Dibujar Imagen
		//ESTOY ALMACENANDO UNA IMAGEN CON MOVIMIENTOS   (image, x, y)
		graficos.drawImage(
				Kanoha.imagenes.get(this.indiceImagen), 
				this.xImagen ,this.yImagen, 
				this.anchoImagen, this.altoImagen,
				this.x, this.y,
				this.anchoImagen, this.altoImagen 
				);
	
		
		graficos.setStroke(Color.WHITE);
		graficos.setFont(new Font("Comic Sans MS", 35));	//PARA CAMBIAR EL TIPO YA TAMA�O DEL TEXTO	
		graficos.strokeText("Puntuacion: " + puntuacion, 5, 82);
		graficos.strokeText("Vidas: " + vidas, 5, 120);
		
		//TEXTO EN EL JUEGO
		graficos.setStroke(Color.WHITE);
		graficos.setFont(new Font("Comic Sans MS", 70));	//PARA CAMBIAR EL TIPO YA TAMA�O DEL TEXTO	
		graficos.strokeText("Angel Edgardo Ordo�ez presenta:   Kanoha", 110, 700);
	}
	
	
	//LOS DATAOS QUE VA PEDIR EL Rectangle
	public Rectangle obtenerRectangulo() {
		return new Rectangle(this.x, this.y, this.anchoImagen, this.altoImagen);
	}
	
	
	//TODAS LAS ANIMACIONES DEL PERSONAJE DE finn
	public void inicializarAnimaciones() {
		
		animaciones = new HashMap<String, Animacion>();
		
		//ANIMACION DESCANSO
		Rectangle coordenadasDescanso[] = {
				new Rectangle(14, 646, 51, 68),
				new Rectangle(93, 647, 52, 67),
				new Rectangle(168, 647, 54, 67),
				new Rectangle(247, 644, 56, 70),
				new Rectangle(329, 646, 53, 68),
				new Rectangle(413, 647, 52, 68),
				new Rectangle(495, 648, 50, 66)
				
		};
		
		//Lo agrego
		Animacion animacionDescanso = new Animacion("descanso", coordenadasDescanso, 0.2);
		animaciones.put("descanso", animacionDescanso);
		
		
		//ANIMACION DE CAMINAR
		Rectangle coordenadasCaminar[] = {
				new Rectangle(18, 11, 45, 62),
				new Rectangle(98, 11, 45, 64),
				new Rectangle(179, 11, 44, 65),
				new Rectangle(257, 11, 46, 64),
				new Rectangle(336, 11, 47, 63),
				new Rectangle(418, 11, 45, 63),
				new Rectangle(497, 11, 46, 63),
				new Rectangle(577, 11, 46, 66),
				new Rectangle(657, 11, 46, 66)		
		};
		
		//Lo agrego al arreglo
		Animacion animacionCaminar = new Animacion("caminar", coordenadasCaminar, 0.1);
		animaciones.put("caminar", animacionCaminar);
		
		//ANIMACION CORRER	
		Rectangle coordenadasCorrer[] = {
				new Rectangle(8, 248, 50, 70),
				new Rectangle(81, 248, 58, 68),
				new Rectangle(173, 246, 50, 68),
				new Rectangle(256, 249, 50, 65),
				new Rectangle(342, 249, 52, 65),
				new Rectangle(418, 250, 63, 64),
				new Rectangle(499, 246, 55, 69),
				new Rectangle(575, 249, 86, 65),
				new Rectangle(654, 249, 58, 75),
				new Rectangle(732, 249, 60, 73),
				new Rectangle(813, 245, 59, 75),
				new Rectangle(893, 245, 58, 69)		
		};
		//Lo agrego al arreglo
		Animacion animacionCorrer = new Animacion("saltar", coordenadasCorrer, 0.2);
		animaciones.put("correr", animacionCorrer);
		
		//ANIMACION FANSTAMAS
		Rectangle coordenadasFantasma[] = {
				new Rectangle(13, 809, 52, 70),
				new Rectangle(93, 807, 52, 71),
				new Rectangle(171, 803, 54, 77),
				new Rectangle(253, 808, 51, 71),
				new Rectangle(332, 809, 52, 70),
				new Rectangle(411, 811, 51, 67),
				new Rectangle(491, 810, 56, 69),
				new Rectangle(565, 814, 70, 70),
				new Rectangle(642, 813, 76, 68),
				new Rectangle(731, 809, 55, 71)	
		};
		//Lo agrego al arreglo
				Animacion animacionFantasma = new Animacion("fantasma", coordenadasFantasma, 0.3);
				animaciones.put("fantasma", animacionFantasma);
	}
	
	
	
	
	public void verificarColisiones(Cubo cubos) {
		if(this.obtenerRectangulo().intersects(cubos.obtenerRectangulo().getBoundsInLocal())) {
			
			
			if(!cubos.isCapturado())
				this.setPuntuacion(this.getPuntuacion() + 1 );
			cubos.setCapturado(true);
		}	
	}
	
	
		
		
		public void verificarColisiones2(ItemMalo itemMalos) {
			if (this.obtenerRectangulo().intersects(itemMalos.obtenerRectangulo().getBoundsInLocal())) {
					if (!itemMalos.isCapturado())
						JOptionPane.showMessageDialog(null, "GAME OVER");
						Kanoha.guardarPuntuaciones();
					itemMalos.setCapturado(true);				
			}
		}

		public int getPuntuacion() {
			return puntuacion;
		}

		public void setPuntuacion(int puntuacion) {
			this.puntuacion = puntuacion;
		}
		
		
		
		
	}


	
	

