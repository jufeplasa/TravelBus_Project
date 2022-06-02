package model;

import java.io.BufferedReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Hashtable;
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
	//private ArrayList<ArrayList<String>> dataCostos;
	
	public TravelBus() {
		dataDistancias=new ArrayList<ArrayList<String>>();
		dataCiudadesList=new ArrayList<String>();
		distancia=new ArrayList<ArrayList<Integer>> ();
		ciudadesList=new ArrayList<Ciudad>();
		departmentList=new ArrayList<String>();
		htCD=new Hashtable<String, String>();
		setRecorrido(new Stack<Ciudad>());
		//dataCostos=new ArrayList<ArrayList<String>>();
		dataDistancias=leerArchivo(DATADISTANCIA);
		leerCyD(DATACIUDADES);
		crearciudadesList();
		crearMatrizD();
		//dataCostos=leerArchivo(DATACOSTOS);
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
	
	
	
	public  void leerCyD(String nameFile) {
		
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
	
	public void establecerRuta(String origin, String destiny){
		int indexO=getIndexCity(origin);
		int indexD=getIndexCity(destiny);
		int valueDistance=distancia.get(indexO).get(indexD);
		
		for(int i=0;i<distancia.get(indexO).size();i++) {
			int value=distancia.get(indexO).get(i)+distancia.get(i).get(indexD);
			if(value<valueDistance) {
				valueDistance=value;
				recorrido.push(ciudadesList.get(i));
			}
		}
		
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
	/**public static ArrayList<ArrayList<String>> costos() {
		return dataCostos;
	}**/
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
