package model;

import java.util.ArrayList;
import java.util.Hashtable;

public class Ciudad {
	private Hashtable<String,Integer> hTDistancias;
	private String ciudad, departamento;
	//private static ArrayList<ArrayList<String>> dis =  data.distancias();
	//private static ArrayList<ArrayList<String>> ciu =  data.ciudades();
	//private ArrayList<ArrayList<String>> cost =  data.costos();
	
	public Ciudad(String ciudad, String departamento,ArrayList<String> distancias,ArrayList<String> ciudades) {
			this.ciudad = ciudad;
			this.departamento = departamento;
			hTDistancias = new Hashtable<String, Integer>();	
			addAristas(ciudades,distancias);
		}
	
	public String getCiudad() {
		return ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	public String getDepartamento() {
		return departamento;
	}

	public void setDepartamento(String departamento) {
		this.departamento = departamento;
	}
	
	public Hashtable<String,Integer> getDistancias() {
		return hTDistancias;
	}
	
	public  void addAristas(ArrayList<String> ciudad, ArrayList<String> distancia) {
		
	
		
		//----------------------CIUDADES-------------------------------------------------
		for (int i = 0; i < distancia.size(); i++) {
			String nameC=ciudad.get(i);
			String distance=distancia.get(i);
			if(!distance.isEmpty()&&!(distance.equals(" "))) {
				int distanceD=Integer.parseInt(distance);
				if(!(this.ciudad.equals(nameC))) {
					hTDistancias.put(nameC, distanceD);
				}
			}
		}
	}
	
	public String toString() {
		return ciudad+" "+departamento;
	}
	
}

