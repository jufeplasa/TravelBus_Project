package model;

import java.io.BufferedReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Hashtable;

public class importData2 {
	private static Hashtable<Ciudades, Double> hTDistancias;
	private static Hashtable<Ciudades, Double> hTCostos;
	
	public static ArrayList<ArrayList<String>> leerArchivo(String nameFile) {
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
			e.printStackTrace();
		}
		System.out.println(data);
		return data;
	}
	
	public static ArrayList<ArrayList<String>> distancias() {
		importData2 fileDistancias = new importData2();
		ArrayList<ArrayList<String>> dataDistancias = leerArchivo("src/data/distancias.csv");
		return dataDistancias;
	}
	public static ArrayList<ArrayList<String>> costos() {
		importData2 fileCostos = new importData2();
		ArrayList<ArrayList<String>> dataCostos = leerArchivo("src/data/costos.csv");
		return dataCostos;
	}
	public static ArrayList<ArrayList<String>> ciudades() {
		importData2 fileCiudades = new importData2();
		ArrayList<ArrayList<String>> dataCiudades = leerArchivo("src/data/ciudades.csv");
		return dataCiudades;
	}
	public static Hashtable<Ciudades, Double> add(ArrayList<ArrayList<String>> ciudad, ArrayList<ArrayList<String>> distancia) {
		hTDistancias = new Hashtable<Ciudades, Double>();
		for (int i = 0; i < ciudad.size(); i++) {
			
			ArrayList<String> temp = ciudad.get(i);
			
			for (int j = 0; j < temp.size(); j++) {
				Ciudades c = new Ciudades(temp.get(j), temp.get(j+1));
				hTDistancias.put(c, null);
			}
		}
		return hTDistancias;
	}
	
}
