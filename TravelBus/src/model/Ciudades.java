package model;

public class Ciudades {
	
	private String ciudad, departamento;
	
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
}
