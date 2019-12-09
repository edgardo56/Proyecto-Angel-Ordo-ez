package implementacion;

import java.util.ArrayList;
import java.util.HashMap;

import clases.Item;
import clases.ItemMalo;
import clases.Personaje;
import clases.Tile;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Kanoha extends Application{
	//Ventana
	private Scene escena;
	private Group root;
	private Canvas canvas;
	private GraphicsContext graficos;
	private int puntuacion = 0;	
	private Personaje personaje;
	//PARA LOS MOVIMEITNOS
	public static boolean arriba = false;
	public static boolean abajo = false;
	public static boolean derecha = false;
	public static boolean izquierda = false;
	public static HashMap<String, Image> imagenes;		//UN ARREGLO QUE PODRE ALMACENAR IMAGENS Y LLAMARLOS POR SU NOMBRE
	
	private Item itemd1;	//items de diamante
	private Item itemd2;
	private Item itemd3;
	private Item itemd4;
	
	private Item itemd5;	//items de diamante
	private Item itemd6;
	private Item itemd7;
	private Item itemd8;
	
	private ItemMalo itemm1;	//item quita vida
	private ItemMalo itemm2;
	private ItemMalo itemm3;
	private ItemMalo itemm4;
	
	
	private ArrayList<Tile> tiles;
	
	private int[][] mapa = {
			
			//15*7
			
			//VACIO
			{2,0,0,0,0,0,1},
			{2,0,0,0,0,0,1},
			{2,0,0,0,0,0,1},
			{2,0,0,0,0,6,1},
			{2,0,0,0,0,0,1},
			{2,0,0,0,0,6,1},
			{2,0,0,0,0,0,1},
			{2,0,0,0,0,0,1},
			{2,0,0,0,0,0,1},
			{2,0,0,0,0,0,1},
			{2,0,0,0,0,6,1},
			{2,0,0,0,0,0,1},
			{2,0,0,0,0,0,1},
			{2,0,0,0,0,0,1},	
			{2,0,0,0,0,0,1},		// DE 0 - 1140
			
			//ALGO CARGADO
			{2,0,0,0,0,6,1},
			{2,0,0,0,0,0,1},
			{2,0,0,0,0,5,1},
			{2,0,0,3,0,0,1},
			{2,0,0,2,0,0,1},
			{2,0,0,2,0,0,1},
			{2,0,8,2,0,0,1},
			{2,0,0,2,0,5,1},
			{2,0,0,4,0,5,1},
			{2,0,0,0,0,0,1},
			{2,0,0,0,0,6,1},
			{2,0,0,0,0,6,1},
			{2,0,0,0,0,6,1},
			{2,0,0,0,0,0,1},
			{2,0,0,0,0,6,1},		//1140 - 2280
			
			//CAJAS
			{2,0,0,0,7,0,1},
			{2,0,0,0,7,0,1},
			{2,0,0,0,7,0,1},
			{2,0,0,0,7,0,1},
			{2,0,0,0,7,0,1},
			{2,0,0,0,7,0,1},
			{2,0,0,0,7,0,1},
			{2,0,0,0,7,0,1},
			{2,0,0,0,7,0,1},
			{2,0,0,0,7,0,1},
			{2,0,0,0,7,6,1},
			{2,0,0,0,7,0,1},
			{2,0,0,0,7,0,1},
			{2,0,0,0,7,0,1},
			{2,0,0,0,7,0,1},	//2280 - 3420
			
			//VACIO
			{2,0,0,0,0,0,1},
			{2,0,0,0,0,0,1},
			{2,0,0,0,0,0,1},
			{2,0,0,0,0,6,1},
			{2,0,0,0,0,0,1},
			{2,0,0,0,0,6,1},
			{2,0,0,0,0,0,1},
			{2,0,0,0,0,0,1},
			{2,0,0,0,0,0,1},
			{2,0,0,0,0,0,1},
			{2,0,0,0,0,6,1},
			{2,0,0,0,0,0,1},
			{2,0,0,0,0,0,1},
			{2,0,0,0,0,0,1},	
			{2,0,0,0,0,0,1}
					
	};

	
	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage ventana) throws Exception {
		inicializarComponentes();
		graficos = canvas.getGraphicsContext2D();
		root.getChildren().add(canvas);
		ventana.setScene(escena);
		ventana.setTitle("JUEGO KANOHA");
		movimientos();
		ventana.show();
		cicloJuego();	
	}
	
	public void inicializarComponentes() {
		
		
		personaje = new Personaje(50, 282, "finn",4 , "fantasma");
		
		//COSAS PRINCIPALES PARA CREAR UNA VENTANA
		root = new Group();	
		escena = new Scene(root, 1140,532);		//TAMAÑO DE LA VENTANA
		canvas = new Canvas(1140,532);			//TAMAÑO DE LOS GRAFICOS EN SI
	
		imagenes = new HashMap<String, Image>();
		
		//diamante 
		itemd1 = new Item(505,280,0,0, "diamante");		
		itemd2 = new Item(415,280,0,0, "diamante");		
		itemd3 = new Item(325,280,0,0, "diamante");	
		itemd4 = new Item(235,280,0,0, "diamante");
		
		itemm1 = new ItemMalo(880,280,0,0, "cuboRojo");	
		itemm2 = new ItemMalo(805,280,0,0, "cuboRojo");
		
		/*
		 ESTOS NO FUNCIONAN, INTENTAR CON ARREGLO COMO LOS TILES 
		 
		// VAN DE X; 2280 - 3420
		itemd5 = new Item(2505,280,0,0, "diamante");		
		itemd6 = new Item(2415,280,0,0, "diamante");		
		itemd7 = new Item(2325,280,0,0, "diamante");	
		itemd8 = new Item(2235,280,0,0, "diamante");	
		
		//items malos
		//VAN DE X; 2280 - 3420
		itemm1 = new ItemMalo(2880,280,0,0, "cuboRojo");	
		itemm2 = new ItemMalo(2805,280,0,0, "cuboRojo");	
		itemm3 = new ItemMalo(2805,280,0,0, "cuboRojo");	
		itemm4 = new ItemMalo(2805,280,0,0, "cuboRojo");*/	
		
		cargarImagenes();
		cargarTiles();
	}

	public void cargarImagenes() {
		//MIOS
		imagenes.put("tilesmap", new Image("tilesmap.png"));
		imagenes.put("cuboRojo", new Image("cuboRojo.png"));
		imagenes.put("diamante", new Image("diamante.png"));
		
		
		imagenes.put("tilemap", new Image("tilemap.png"));
		//imagenes.put("item", new Image("item.png"));
		imagenes.put("finn", new Image("finn.png"));	
	}
	
	public void pintar() {
		
		graficos.setFill(Color.BLACK);			//Color del rectangulo
		graficos.fillRect(0, 0, 1140, 532);		//Lo necesito para la imagen que inserte no dejerastro de sus movimientos
		graficos.setFill(Color.WHITE);
		graficos.fillText("Puntuacion: " + puntuacion, 0, 0);
		personaje.pintar(graficos);
		
		//PINTAR TILES
		
		
			for(int i =0; i < tiles.size(); i++) 
				tiles.get(i).pintar(graficos);
		
		//ITEMS
		itemd1.pintar(graficos);
		itemd2.pintar(graficos);
		itemd3.pintar(graficos);
		itemd4.pintar(graficos);
		
		/*itemd5.pintar(graficos);		NO FUNCIONAN AUN, ARREGLO INTENTAR
		itemd6.pintar(graficos);
		itemd7.pintar(graficos);
		itemd8.pintar(graficos);*/
		
		//Items DE PERDER VIDAS
		itemm1.pintar(graficos);
		itemm2.pintar(graficos);
		
		/*itemm3.pintar(graficos);
		itemm4.pintar(graficos);*/		
		
	}
	
	
	public void cargarTiles() {
		tiles = new ArrayList<Tile>();
		
		
			for(int i = 0; i <mapa.length; i++) {
				for(int j = 0; j < mapa[i].length; j++) {
					if(mapa[i][j]!=0)
						tiles.add(new Tile(mapa[i][j], i*76, j *76, "tilesmap", 0));		//ESTE 70 HAY QUE CORREGIRLO POR EL TAMAÑO DE DIFERENCIA DE LOS ITEMS MIOS
				}
			}				
	}
	
	
	
	public void movimientos() {
		
		//EVENTO CUANDO VOY A PRESIONAR UNA TECLA Y ME LA LEE
		escena.setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent evento) {
				
				//QUE BOTON PRESIONARE
				switch(evento.getCode().toString()) {
				case "RIGHT":	//Derecha
					derecha = true;
					//personaje = new Personaje(50, 400, "finn",1 , "caminar");
					break;
				case "LEFT":	//Izquierda
					izquierda = true;
					//personaje = new Personaje(50, 400, "finn",1 , "caminar");
					break;
				case "UP":		//Arriba
					arriba = true;
					//personaje = new Personaje(50, 400, "finn",1 , "caminar");
					break;
				case "DOWN":	//Abajo
					abajo = true;
					//personaje = new Personaje(50, 400, "finn",1 , "caminar");
					break;
				case "SPACE":	//Velocidad
					//personaje = new Personaje(50, 400, "finn",5 , "fantasma");
					break;
				}
			}
		});
		
		
		//CUANDO DEJE DE PRESIONAR LA TECLA
		escena.setOnKeyReleased(new EventHandler<KeyEvent>() {
			
			@Override
			public void handle(KeyEvent evento) {
				
				switch(evento.getCode().toString()) {	//LEE LA TECLA QUE PRESIONO
				case "RIGHT":	//Derecha
					derecha = false;
					//personaje = new Personaje(50, 400, "finn",1 , "descanso");
					break;
				case "LEFT":	//Izquierda
					izquierda = false;
					//personaje = new Personaje(50, 400, "finn",1 , "descanso");
					break;
				case "UP":		//Arriba
					arriba = false;
					//personaje = new Personaje(50, 400, "finn",1 , "descanso");
					break;
				case "DOWN":	//Abajo
					abajo = false;
					//personaje = new Personaje(50, 400, "finn",1 , "descanso");
					break;
				case "SPACE":	//Velocidad
					//personaje = new Personaje(50, 400, "finn",3 , "fantasma");
					break;
				}	
			}	
		});	
		
	}
	
	
	public void cicloJuego() {
		
		long tiempoInicial = System.nanoTime();
		AnimationTimer animationTimer = new AnimationTimer() {
			//ESTA RUTINA SIMULA UN CICLO DE 60FPS		FPS -> Fotogramas por segundos
			
			@Override
			public void handle(long tiempoActualNanoSegundos) {
				double t = (tiempoActualNanoSegundos - tiempoInicial) / 1000000000.0;
				pintar();
				actualizar(t);
			}
		};
		animationTimer.start();		//COMIENZA EL CICLO	
	}
	
	public void actualizar(double t) {
		personaje.mover();
		personaje.verificarColisiones(itemd1);
		personaje.verificarColisiones(itemd2);
		personaje.verificarColisiones(itemd3);
		personaje.verificarColisiones(itemd4);
		
		/*personaje.verificarColisiones(itemd5);
		personaje.verificarColisiones(itemd6);
		personaje.verificarColisiones(itemd7);
		personaje.verificarColisiones(itemd8);*/
	
		personaje.verificarColisiones2(itemm1);
		personaje.verificarColisiones2(itemm2);
		/*personaje.verificarColisiones2(itemm3);
		personaje.verificarColisiones2(itemm4);*/
		
		
		personaje.actualizarAnimacion(t);
		
	}
	
	

}
