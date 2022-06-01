package model;

import java.io.BufferedReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Hashtable;

public class TravelBus {
	//private static Hashtable<Ciudades, Double> hTCostos;
	private static final String DATADISTANCIA="src/data/distancias.csv";
	private static final String DATACIUDADES="src/data/ciudades.csv";
	private static final String DATACOSTOS="src/data/costos.csv";
	
	private ArrayList<ArrayList<String>> dataDistancias;
	private ArrayList<String> dataCiudadesList;
	private ArrayList<Ciudad> ciudadesList;
	private ArrayList<String> departmentList;
	private Hashtable<String, String> htCD;
	//private ArrayList<ArrayList<String>> dataCostos;
	
	public TravelBus() {
		dataDistancias=new ArrayList<ArrayList<String>>();
		dataCiudadesList=new ArrayList<String>();
		ciudadesList=new ArrayList<Ciudad>();
		departmentList=new ArrayList<String>();
		htCD=new Hashtable<String, String>();
		//dataCostos=new ArrayList<ArrayList<String>>();
		dataDistancias=leerArchivo(DATADISTANCIA);
		leerCyD(DATACIUDADES);
		crearciudadesList();
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
		Ciudad city=new Ciudad(nombreC,departamento,dataDistancias.get(index),dataCiudadesList);
		return city;
	}
	
	public  ArrayList<ArrayList<String>> distancias() {
		
		return dataDistancias;
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
	
}
