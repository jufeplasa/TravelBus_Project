package model;

import java.util.ArrayList;
import java.util.Hashtable;

public class Ciudad {
	
	private String ciudad, departamento;
	
	public Ciudad(String ciudad, String departamento) {
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
	
	
	public String toString() {
		return ciudad+" "+departamento;
	}
	
}

