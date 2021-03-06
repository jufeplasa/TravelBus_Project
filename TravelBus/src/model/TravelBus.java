package model;

import java.io.BufferedReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.PriorityQueue;
import java.util.Stack;

public class TravelBus {
	//private static Hashtable<Ciudades, Double> hTCostos;
	private static final String DATADISTANCIA="src/data/distancias.csv";
	private static final String DATACIUDADES="src/data/ciudades.csv";
	private static final String DATACOSTOS="src/data/costos.csv";
	
	private ArrayList<ArrayList<String>> dataDistancias;
	private ArrayList<ArrayList<Integer>> distancia;
	private ArrayList<String> dataCiudadesList;
	private ArrayList<Ciudad> ciudadesList;
	private ArrayList<String> departmentList;
	private Hashtable<String, String> htCD;
	private Stack<Ciudad> recorrido;
	private ArrayList<ArrayList<String>> dataCostos;
	private ArrayList<ArrayList<Integer>> costos;
	
	public TravelBus() {
		dataDistancias=new ArrayList<ArrayList<String>>();
		dataCiudadesList=new ArrayList<String>();
		distancia=new ArrayList<ArrayList<Integer>> ();
		ciudadesList=new ArrayList<Ciudad>();
		departmentList=new ArrayList<String>();
		htCD=new Hashtable<String, String>();
		setRecorrido(new Stack<Ciudad>());
		dataCostos=new ArrayList<ArrayList<String>>();
		costos = new ArrayList<ArrayList<Integer>>();
		dataDistancias=leerArchivo(DATADISTANCIA);
		leerCyD(DATACIUDADES);
		crearciudadesList();
		crearMatrizD();
		dataCostos=leerArchivo(DATACOSTOS);
		crearMatrizC();
	}
	
	public  ArrayList<ArrayList<String>> leerArchivo(String nameFile) {
		ArrayList<ArrayList<String>> data = new ArrayList<ArrayList<String>>();
		Path filePath = Paths.get(nameFile);
		try {
			BufferedReader br = Files.newBufferedReader(filePath);
			String linea;
			while((linea= br.readLine()) != null) {
				String datosLinea[] = linea.split(",");
				ArrayList<String> temp = new ArrayList<>();
				for (String dato : datosLinea) {
					temp.add(dato);
				}
				data.add(temp);
			}
		} catch (Exception e) {
			//e.printStackTrace();
		}
		//System.out.println(data);
		return data;
	}
	
	public void crearMatrizD() {
		for(int i=0;i<dataDistancias.size();i++) {
			ArrayList<Integer> temp = new ArrayList<>();
			for(int j=0;j<dataDistancias.get(i).size();j++) {
				String num=dataDistancias.get(i).get(j);
				if(num.isEmpty()||num.equals(" ")) {
					temp.add(99999);
				}
				else {
					temp.add(Integer.parseInt(num));
				}
			}
			distancia.add(temp);
		}
	}
	public void crearMatrizC() {
		for(int i=0;i<dataCostos.size();i++) {
			ArrayList<Integer> temp = new ArrayList<>();
			for(int j=0;j<dataCostos.get(i).size();j++) {
				String num=dataCostos.get(i).get(j);
				if(num.isEmpty()||num.equals(" ")) {
					temp.add(999999);
				}
				else {
					temp.add(Integer.parseInt(num));
				}
			}
			costos.add(temp);
		}
	}
	public Stack<Ciudad> rutaDijkstra(int inicio, int fina) {
		int[] distancia = new int[ciudadesList.size()];
		int[] padre = new int[ciudadesList.size()];
		boolean[] visto = new boolean[ciudadesList.size()];
		for (int i = 0; i < ciudadesList.size(); i++) {
			distancia[i] = 99999;
			padre[i] = -1;
			visto[i] = false;
		}
		distancia[inicio]=0;
		Stack<Ciudad> pilaCiudad = new Stack<>();
		PriorityQueue<Integer> pila = new PriorityQueue<>();
		pila.add(inicio);
		while (!pila.isEmpty()) {
			int u = pila.poll();
			visto[u] = true;
			for (int i = 0; i < ciudadesList.size(); i++) {
				if (this.distancia.get(u).get(i) != 0) {
					if ((distancia[u] + this.distancia.get(u).get(i))<distancia[i] ) {
						
						distancia[i] = distancia[u] + this.distancia.get(u).get(i);
						padre[i] = u;
						pila.add(i);
						if(i==fina) {
							pilaCiudad.add(ciudadesList.get(u));
						}
					}
				}
			}
			
		}
		pilaCiudad.add(ciudadesList.get(fina));
		return pilaCiudad;
	}
	public Stack<Ciudad> rutaDijkstraCosto(int inicio, int fina) {
		int[] distancia = new int[ciudadesList.size()];
		int[] padre = new int[ciudadesList.size()];
		boolean[] visto = new boolean[ciudadesList.size()];
		for (int i = 0; i < ciudadesList.size(); i++) {
			distancia[i] = 999999;
			padre[i] = -1;
			visto[i] = false;
		}
		distancia[inicio]=0;
		Stack<Ciudad> pilaCiudad = new Stack<>();
		PriorityQueue<Integer> pila = new PriorityQueue<>();
		pila.add(inicio);
		while (!pila.isEmpty()) {
			int u = pila.poll();
			visto[u] = true;
			for (int i = 0; i < ciudadesList.size(); i++) {
				if (this.costos.get(u).get(i) != 0) {
					if ((distancia[u] + this.costos.get(u).get(i))<distancia[i] ) {
						
						distancia[i] = distancia[u] + this.costos.get(u).get(i);
						padre[i] = u;
						pila.add(i);
						if(i==fina) {
							pilaCiudad.add(ciudadesList.get(u));
						}
					}
				}
			}
			
		}
		pilaCiudad.add(ciudadesList.get(fina));
		return pilaCiudad;
	}
	public void leerCyD(String nameFile) {
		
		Path filePath = Paths.get(nameFile);
		try {
			BufferedReader br = Files.newBufferedReader(filePath);
			String linea;
			while((linea= br.readLine()) != null) {
				String datosLinea[] = linea.split(",");
				htCD.put(datosLinea[0],datosLinea[1]);
				dataCiudadesList.add(datosLinea[0]);
				if(!isRepetido(datosLinea[1])) {
					departmentList.add(datosLinea[1]);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public boolean isRepetido(String department) {
		boolean repeat=false;
		for(int i=0;i<departmentList.size()&&!repeat;i++) {
			if(departmentList.get(i).equals(department)) {
				repeat=true;
			}
		}
		return repeat;
	}

	public int getIndexCity(String cityName) {
		boolean found=false;
		int index=0;
		for(int i=0;i<ciudadesList.size()&&!found;i++) {
			if(ciudadesList.get(i).getCiudad().equals(cityName)) {
				index=i;
				found=true;
			}
		}
		return index;
	}
	
	public void crearciudadesList() {
		for (int i=0;i<dataCiudadesList.size();i++) {
			Ciudad nCiudad=crearCiudad(dataCiudadesList.get(i),i);
			if(nCiudad!=null) {
				ciudadesList.add(nCiudad);
			}
		}
	}
//	public int[] dijkistra(int inicio, int fina) {
//		int[] distancia = new int[ciudadesList.size()];
//		int[] padre = new int[ciudadesList.size()];
//		boolean[] visto = new boolean[ciudadesList.size()];
//		for (int i = 0; i < ciudadesList.size(); i++) {
//			distancia[i] = 999999;
//			padre[i] = -1;
//			visto[i] = false;
//		}
//		distancia[inicio]=0;
//		Stack<Ciudad> pilaCiudad = new Stack<>();
//		PriorityQueue<Integer> pila = new PriorityQueue<>();
//		pilaCiudad.add(ciudadesList.get(inicio));
//		pila.add(inicio);
//		while (!pila.isEmpty()) {
//			int u = pila.poll();
//			visto[u] = true;
//			for (int i = 0; i < ciudadesList.size(); i++) {
//				if (this.distancia.get(u).get(i) != 0) {
//					if ((distancia[u] + this.distancia.get(u).get(i))<distancia[i] ) {
//						distancia[i] = distancia[u] + this.distancia.get(u).get(i);
//						padre[i] = u;
//						pila.add(i);
//						pilaCiudad.add(ciudadesList.get(i));
//						
//					}
//				}
//			}
//			System.out.println(pilaCiudad);
//		}
//		System.out.println(pilaCiudad.toString());
//		return distancia;
//	}
	
	public ArrayList<String> getCityByDep(String departamento){
		ArrayList<String> cities=new ArrayList<String>();
		for(int i=0;i<dataCiudadesList.size();i++) {
			if(htCD.get(dataCiudadesList.get(i)).equals(departamento)) {
				cities.add(dataCiudadesList.get(i));
			}
		}
		return cities;
	}
	
	public Ciudad crearCiudad(String nombreC, int index) {
		String departamento=htCD.get(nombreC);
		Ciudad city=new Ciudad(nombreC,departamento);
		return city;
	}
	
	public  ArrayList<ArrayList<Integer>> distancias() {
		
		return distancia;
	}
	
	public Hashtable<String, String> departCiudad() {
		return htCD;
	}
	public ArrayList<ArrayList<String>> costos() {
		return dataCostos;
	}
	public  ArrayList<String> dataCiudades() {
		return dataCiudadesList;
	}
	public  ArrayList<Ciudad> ciudades() {
		return ciudadesList;
	}
	public  ArrayList<String> departamentos() {
		return departmentList;
	}

	public Stack<Ciudad> getRecorrido() {
		return recorrido;
	}

	public void setRecorrido(Stack<Ciudad> recorrido) {
		this.recorrido = recorrido;
	}
	
}
