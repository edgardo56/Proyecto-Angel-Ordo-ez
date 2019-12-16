package implementacion;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JOptionPane;

import clases.Animacion;
import clases.Cubo;
import clases.ItemMalo;
import clases.Personaje;
import clases.Player;
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
	private static int puntuacion = 0;	
	private Personaje personaje;
	//PARA LOS MOVIMEITNOS
	public static boolean arriba = false;
	public static boolean abajo = false;
	public static boolean derecha = false;
	public static boolean izquierda = false;
	public static boolean finJuego =false;
	public static HashMap<String, Image> imagenes;		//UN ARREGLO QUE PODRE ALMACENAR IMAGENS Y LLAMARLOS POR SU NOMBRE
	
	private ArrayList<Tile> tiles;
	private ArrayList<Cubo> cubos;
	private ArrayList<ItemMalo> itemMalos;
	private static ArrayList<Player> jugadores;
	
	
	int randomx,randomy,randomyy;		//ALEATORIA MOVIMIENTOS PARA LOS ITEMS
	int randomxV,randomyV,randomyyV;
	
	private int[][] mapa = {
			
			//1520, 912      26*12
			
			//VACIO
			/*{2,0,0,0,0,3,0,0,0,0,1,10},
			{2,0,0,0,0,2,0,0,0,0,1,10},
			{2,0,0,0,0,2,0,0,0,0,1,10},
			{2,0,0,0,0,2,0,0,0,0,1,10},
			{2,0,0,0,0,2,0,0,0,0,1,10},
			{2,0,0,0,0,2,0,0,0,0,1,10},
			{2,0,0,0,0,2,0,0,0,0,1,10},
			{2,0,0,0,0,2,0,0,0,0,1,10},
			{2,0,0,0,0,2,0,0,0,0,1,10},
			{2,0,0,0,0,2,0,0,0,0,1,10},
			{2,0,0,0,0,2,0,0,0,0,1,10},
			{2,0,0,0,0,2,0,0,0,0,1,10},
			{2,0,0,0,0,2,0,0,0,0,1,10},
			{2,0,0,0,0,2,0,0,0,0,1,10},
			{2,0,0,0,0,2,0,0,0,0,1,10},
			{2,0,0,0,0,2,0,0,0,0,1,10},
			{2,0,0,0,0,2,0,0,0,0,1,10},
			{2,0,0,0,0,2,0,0,0,0,1,10},
			{2,0,0,0,0,2,0,0,0,0,1,10},
			{2,0,0,0,0,2,0,0,0,0,1,10},
			{2,0,0,0,0,2,0,0,0,0,1,10},
			{2,0,0,0,0,2,0,0,0,0,1,10},
			
			{2,0,0,0,0,2,0,0,0,0,1,10},
			{2,0,0,0,0,2,0,0,0,0,1,10},
			{2,0,0,0,0,2,0,0,0,0,1,10},
			{2,0,0,0,0,2,0,0,0,0,1,10},
			{2,0,0,0,0,2,0,0,0,0,1,10},
			{2,0,0,0,0,2,0,0,0,0,1,10},
			{2,0,0,0,0,2,0,0,0,0,1,10},
			{2,0,0,0,0,2,0,0,0,0,1,10},
			{2,0,0,0,0,2,0,0,0,0,1,10},
			{2,0,0,0,0,2,0,0,0,0,1,10},
			{2,0,0,0,0,2,0,0,0,0,1,10},
			{2,0,0,0,0,2,0,0,0,0,1,10},
			{2,0,0,0,0,2,0,0,0,0,1,10},
			{2,0,0,0,0,2,0,0,0,0,1,10},
			{2,0,0,0,0,2,0,0,0,0,1,10},
			{2,0,0,0,0,2,0,0,0,0,1,10},
			{2,0,0,0,0,4,0,0,0,0,1,10},
			{2,0,0,0,0,7,0,0,0,0,1,10},
			{2,0,0,0,0,7,0,0,0,0,1,10},
			{2,0,0,0,0,7,0,0,0,0,1,10},
			{2,0,0,0,0,7,0,0,0,0,1,10},
			{2,0,0,0,0,7,0,0,0,0,1,10},*/
			
			{2,0,0,0,0,7,0,0,0,0,1,10},
			{2,0,0,0,0,7,0,0,0,0,1,10},
			{2,0,0,0,0,7,0,0,0,0,1,10},
			{2,0,0,0,0,7,0,0,0,0,1,10},
			{2,0,0,0,0,7,0,0,0,0,1,10},
			{2,0,0,0,0,7,0,0,0,0,1,10},
			{2,0,0,0,0,7,0,0,0,0,1,10},
			{2,0,0,0,0,7,0,0,0,0,1,10},
			{2,0,0,0,0,7,0,0,0,0,1,10},
			{2,0,0,0,0,7,0,0,0,0,1,10},
			{2,0,0,0,0,7,0,0,0,0,1,10},
			{2,0,0,0,0,7,0,0,0,0,1,10},
			{2,0,0,0,0,7,0,0,0,0,1,10},
			{2,0,0,0,0,7,0,0,0,0,1,10},
			{2,0,0,0,0,7,0,0,0,0,1,10},
			{2,0,0,0,0,7,0,0,0,0,1,10},
			{2,0,0,0,0,7,0,0,0,0,1,10},
			{2,0,0,0,0,7,0,0,0,0,1,10},
			{2,0,0,0,0,7,0,0,0,0,1,10},
			{2,0,0,0,0,7,0,0,0,0,1,10},
			{2,0,0,0,0,7,0,0,0,0,1,10},
			{2,0,0,0,0,7,0,0,0,0,1,10},
			
			{2,0,0,0,0,3,0,0,0,0,1,10},
			{2,0,0,0,0,2,0,0,0,0,1,10},
			{2,0,0,0,0,2,0,0,0,0,1,10},
			{2,0,0,0,0,2,0,0,0,0,1,10},
			{2,0,0,0,0,2,0,0,0,0,1,10},
			{2,0,0,0,0,2,0,0,0,0,1,10},
			{2,0,0,0,0,2,0,0,0,0,1,10},
			{2,0,0,0,0,2,0,0,0,0,1,10},
			{2,0,0,0,0,2,0,0,0,0,1,10},
			{2,0,0,0,0,2,0,0,0,0,1,10},
			{2,0,0,0,0,2,0,0,0,0,1,10},
			{2,0,0,0,0,4,0,0,0,0,1,10},
			{2,0,0,0,0,7,0,0,0,0,1,10},
			{2,0,0,0,0,3,0,0,0,0,1,10},
			{2,0,0,0,0,2,0,0,0,0,1,10},
			{2,0,0,0,0,2,0,0,0,0,1,10},
			{2,0,0,0,0,2,0,0,0,0,1,10},
			{2,0,0,0,0,2,0,0,0,0,1,10},
			{2,0,0,0,0,2,0,0,0,0,1,10},
			{2,0,0,0,0,2,0,0,0,0,1,10},
			{2,0,0,0,0,2,0,0,0,0,1,10},
			{2,0,0,0,0,4,0,0,0,0,1,10},

			{2,0,0,0,0,3,0,0,0,0,1,10},
			{2,0,0,0,0,2,0,0,0,0,1,10},
			{2,0,0,0,0,2,0,0,0,0,1,10},
			{2,0,0,0,0,2,0,0,0,0,1,10},
			{2,0,0,0,0,2,0,0,0,0,1,10},
			{2,0,0,0,0,2,0,0,0,0,1,10},
			{2,0,0,0,0,2,0,0,0,0,1,10},
			{2,0,0,0,0,2,0,0,0,0,1,10},
			{2,0,0,0,0,2,0,0,0,0,1,10},
			{2,0,0,0,0,2,0,0,0,0,1,10},
			{2,0,0,0,0,2,0,0,0,0,1,10},
			{2,0,0,0,0,4,0,0,0,0,1,10},
			{2,0,0,0,0,7,0,0,0,0,1,10},
			{2,0,0,0,0,3,0,0,0,0,1,10},
			{2,0,0,0,0,2,0,0,0,0,1,10},
			{2,0,0,0,0,2,0,0,0,0,1,10},
			{2,0,0,0,0,2,0,0,0,0,1,10},
			{2,0,0,0,0,2,0,0,0,0,1,10},
			{2,0,0,0,0,2,0,0,0,0,1,10},
			{2,0,0,0,0,2,0,0,0,0,1,10},
			{2,0,0,0,0,2,0,0,0,0,1,10},
			{2,0,0,0,0,4,0,0,0,0,1,10},
			
			{2,0,0,0,0,3,0,0,0,0,1,10},
			{2,0,0,0,0,2,0,0,0,0,1,10},
			{2,0,0,0,0,2,0,0,0,0,1,10},
			{2,0,0,0,0,2,0,0,0,0,1,10},
			{2,0,0,0,0,2,0,0,0,0,1,10},
			{2,0,0,0,0,2,0,0,0,0,1,10},
			{2,0,0,0,0,2,0,0,0,0,1,10},
			{2,0,0,0,0,2,0,0,0,0,1,10},
			{2,0,0,0,0,2,0,0,0,0,1,10},
			{2,0,0,0,0,2,0,0,0,0,1,10},
			{2,0,0,0,0,2,0,0,0,0,1,10},
			{2,0,0,0,0,4,0,0,0,0,1,10},
			{2,0,0,0,0,7,0,0,0,0,1,10},
			{2,0,0,0,0,3,0,0,0,0,1,10},
			{2,0,0,0,0,2,0,0,0,0,1,10},
			{2,0,0,0,0,2,0,0,0,0,1,10},
			{2,0,0,0,0,2,0,0,0,0,1,10},
			{2,0,0,0,0,2,0,0,0,0,1,10},
			{2,0,0,0,0,2,0,0,0,0,1,10},
			{2,0,0,0,0,2,0,0,0,0,1,10},
			{2,0,0,0,0,2,0,0,0,0,1,10},
			{2,0,0,0,0,4,0,0,0,0,1,10}, 
			
			{2,0,0,0,0,7,0,0,0,0,1,10},
			{2,0,0,0,0,7,0,0,0,0,1,10},
			{2,0,0,0,0,7,0,0,0,0,1,10},
			{2,0,0,0,0,7,0,0,0,0,1,10},
			{2,0,0,0,0,7,0,0,0,0,1,10},
			{2,0,0,0,0,7,0,0,0,0,1,10},
			{2,0,0,0,0,7,0,0,0,0,1,10},
			{2,0,0,0,0,7,0,0,0,0,1,10},
			{2,0,0,0,0,7,0,0,0,0,1,10},
			{2,0,0,0,0,7,0,0,0,0,1,10},
			{2,0,0,0,0,7,0,0,0,0,1,10},
			{2,0,0,0,0,7,0,0,0,0,1,10},
			{2,0,0,0,0,7,0,0,0,0,1,10},
			{2,0,0,0,0,7,0,0,0,0,1,10},
			{2,0,0,0,0,7,0,0,0,0,1,10},
			{2,0,0,0,0,7,0,0,0,0,1,10},
			{2,0,0,0,0,7,0,0,0,0,1,10},
			{2,0,0,0,0,7,0,0,0,0,1,10},
			{2,0,0,0,0,7,0,0,0,0,1,10},
			{2,0,0,0,0,7,0,0,0,0,1,10},
			{2,0,0,0,0,7,0,0,0,0,1,10},
			{2,0,0,0,0,7,0,0,0,0,1,10},
			
			{2,0,0,0,0,3,0,0,0,0,1,10},
			{2,0,0,0,0,2,0,0,0,0,1,10},
			{2,0,0,0,0,2,0,0,0,0,1,10},
			{2,0,0,0,0,2,0,0,0,0,1,10},
			{2,0,0,0,0,2,0,0,0,0,1,10},
			{2,0,0,0,0,2,0,0,0,0,1,10},
			{2,0,0,0,0,2,0,0,0,0,1,10},
			{2,0,0,0,0,2,0,0,0,0,1,10},
			{2,0,0,0,0,2,0,0,0,0,1,10},
			{2,0,0,0,0,2,0,0,0,0,1,10},
			{2,0,0,0,0,2,0,0,0,0,1,10},
			{2,0,0,0,0,4,0,0,0,0,1,10},
			{2,0,0,0,0,7,0,0,0,0,1,10},
			{2,0,0,0,0,3,0,0,0,0,1,10},
			{2,0,0,0,0,2,0,0,0,0,1,10},
			{2,0,0,0,0,2,0,0,0,0,1,10},
			{2,0,0,0,0,2,0,0,0,0,1,10},
			{2,0,0,0,0,2,0,0,0,0,1,10},
			{2,0,0,0,0,2,0,0,0,0,1,10},
			{2,0,0,0,0,2,0,0,0,0,1,10},
			{2,0,0,0,0,2,0,0,0,0,1,10},
			{2,0,0,0,0,4,0,0,0,0,1,10}
		  		
			
							
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
		ventana.setTitle("KANOHA");
		movimientos();
		ventana.show();
		jugadores=new ArrayList<Player>();
		cicloJuego();	
	}
	
	public void inicializarComponentes() {
		
		
		personaje = new Personaje(50, 695, "finn",5 , "descanso");
		
		//COSAS PRINCIPALES PARA CREAR UNA VENTANA
		root = new Group();	
		escena = new Scene(root, 1672,912);		//TAMAÑO DE LA VENTANA
		canvas = new Canvas(1672,912);			//TAMAÑO DE LOS GRAFICOS EN SI
	
		imagenes = new HashMap<String, Image>();
		
		//PARA EL MOVIMIENTO DE LOS ITEMS DE PUNTUACION
		cubos = new ArrayList<Cubo>();
		int m=((int)(Math.random()*16+25));
		for (int z=0;z<m;z++) {
			randomyy=(int)(Math.random()*5+1);
			randomx=(int)(Math.random()*20000+1000);
			if (randomyy==1)
				randomy=70;
			if(randomyy==2)
				randomy=280;
			if(randomyy==3)
				randomy=490;
			cubos.add(new Cubo(randomx,randomy,0,0,"items",4,"mover"));
		}
		
		//PARA EL MOVIMIENTO DE LOS ITEMS NEGATIVOS
		itemMalos = new ArrayList<ItemMalo>();
		int n=((int)(Math.random()*30+30));
		for (int z=0;z<n;z++) {
			randomyy=(int)(Math.random()*3+1);
			randomx=(int)(Math.random()*15000+1000);
			if (randomyy==1)
				randomy=70;
			if(randomyy==2)
				randomy=280;
			if(randomyy==3)
				randomy=490;
			itemMalos.add(new ItemMalo(randomx,randomy,0,0,"items",8,"mover"));
		}
		
		
		cargarImagenes();
		cargarTiles();
	}

	public void cargarImagenes() {
		//MIOS
		imagenes.put("tilesmap", new Image("tilesmap.png"));
		imagenes.put("fondo3", new Image("fondo3.png"));
		imagenes.put("items",new Image ("itemsKanoha.png"));
		imagenes.put("finn", new Image("finn.png"));	
	}
	
	public void pintar() {
		
		graficos.setFill(Color.BLACK);			//Color del rectangulo
		graficos.fillRect(0, 0, 1672, 912);		//Lo necesito para la imagen que inserte no dejerastro de sus movimientos
		graficos.setFill(Color.WHITE);
		graficos.fillText("Puntuacion: " + puntuacion, 0, 0);
		graficos.setFill(Color.BLACK);
		graficos.drawImage(imagenes.get("fondo3"), 0, 0);
		personaje.pintar(graficos);
		
		//PINTAR TILES
		
		
			for(int i =0; i < tiles.size(); i++) 
				tiles.get(i).pintar(graficos);
			
			
			
			for (int i=0;i<cubos.size();i++)
				cubos.get(i).pintar(graficos);
			
			
			for (int i=0;i<itemMalos.size();i++)
				itemMalos.get(i).pintar(graficos);
		
		
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
					Personaje.animacionActual="fantasma";
					
					break;
				case "LEFT":	//Izquierda
					izquierda = true;
					//personaje = new Personaje(50, 400, "finn",1 , "caminar");
					break;
				case "UP":		//Arriba
					arriba = true;
					if(Personaje.y!=315)    //72
						Personaje.y-=200;  //220
					break;
				case "DOWN":	//Abajo
					abajo = true;
					if(Personaje.y!=492)	//492
						Personaje.y+=200;	//210
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
					Personaje.animacionActual="descanso";
					Animacion.setDuracion(0.3);
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
					if(!Kanoha.finJuego) {
						pintar();
						actualizar(t);
					}else {
						leerPuntuaciones();
					}
			}
		};
		animationTimer.start();		//COMIENZA EL CICLO	
	}
	
	
	
	
	public void actualizar(double t) {
		
		for(int z=0;z<cubos.size();z++) {
			cubos.get(z).mover();
			cubos.get(z).actualizarAnimacion(t);
		}
		for (int i=0;i<cubos.size();i++)
			personaje.verificarColisiones(cubos.get(i));
		

		for(int z=0;z<itemMalos.size();z++) {
			itemMalos.get(z).mover();
			itemMalos.get(z).actualizarAnimacion(t);
		}
		for (int i=0;i<itemMalos.size();i++)
			personaje.verificarColisiones2(itemMalos.get(i));
		
		personaje.actualizarAnimacion(t);
		
	}
	
	
	
	
	public static void guardarPuntuaciones() {
		jugadores.add(new Player(JOptionPane.showInputDialog("Ingrese su nombre:"),puntuacion));
		try {
			BufferedWriter archivo=new BufferedWriter(new FileWriter("puntuaciones.csv",true));
			archivo.write(Player.toCSV());
			archivo.flush();
			archivo.close();
		}catch (IOException e) {
			e.printStackTrace();
		}
		leerPuntuaciones();
		System.exit(0);
	}
	
	
	
	private static void leerPuntuaciones() {
		String linea="";
		String cadena="";
		
		try {
			BufferedReader flujo = new BufferedReader(new FileReader("puntuaciones.csv"));
			while ((linea=flujo.readLine())!=null) {
				String partes[]=linea.split(",");
				jugadores.add(new Player(partes[0],Integer.parseInt(partes[1])));
				cadena+=partes[0]+":"+partes[1]+"\n";
			}
			flujo.close();
		}
		catch (FileNotFoundException e) {
			System.out.println("no hay puntuaciones");
		}catch (IOException e) {
			System.out.println(".");
		}
		JOptionPane.showMessageDialog(null, "puntuaciones:"+"\n"+cadena);
	}
	
	
	

}
