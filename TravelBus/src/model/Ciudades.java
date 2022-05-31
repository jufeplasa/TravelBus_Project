package model;

import java.util.ArrayList;
import java.util.Hashtable;

public class Ciudades {
	private static Hashtable<Ciudades, Hashtable<Ciudades, String>> hTDistancias;
	private String ciudad, departamento;
	private static importData2 data;
	private static ArrayList<ArrayList<String>> dis =  data.distancias();
	private static ArrayList<ArrayList<String>> ciu =  data.ciudades();
	//private ArrayList<ArrayList<String>> cost =  data.costos();
	
	public Ciudades(String ciudad, String departamento) {
			this.ciudad = ciudad;
			this.departamento = departamento;
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
	
	public static void addAristas(ArrayList<ArrayList<String>> ciudad, ArrayList<ArrayList<String>> tipoDeArista) {
		hTDistancias = new Hashtable<Ciudades, Hashtable<Ciudades, String>>();	
		Hashtable<Ciudades, String> hTDestino = new Hashtable<Ciudades, String>();
		
		//----------------------CIUDADES-------------------------------------------------
		for (int i = 0; i < ciudad.size(); i++) {
			ArrayList<String> temp = ciudad.get(i);
			for (int j = 0; j < temp.size(); j++) {
				Ciudades c = new Ciudades(temp.get(0), temp.get(1));
				//-------------------ARISTAS---------------------------------------------
				for (int k = 0; k < tipoDeArista.size(); k++) {
					ArrayList<String> temp2 = tipoDeArista.get(i);
					
					for (int k2 = 0; k2 < temp2.size(); k2++) {
						String tipoArista = temp2.get(k2);
						hTDestino.put(c, tipoArista);
					}
				}
			}
		}
		for (int i = 0; i < ciudad.size(); i++) {
			ArrayList<String> tem = ciudad.get(i);
			for (int j = 0; j < tem.size(); j++) {
				Ciudades c = new Ciudades(tem.get(0), tem.get(1));
				hTDistancias.put(c, hTDestino);
			}
		}
		System.out.println(hTDistancias);
	}

	public static void main(String[] args) {
		addAristas(ciu, dis);
		
	}
}

