package modelo;

import java.util.ArrayList;

public class Persona {
	
	private String nombre;
	private int puntosPartidos;
	private int puntosRondas;
	private int puntosFases;
	private int cantidadDeAciertos;
	private int cantidadRondasAcertadas;
	private int cantidadFasesAcertadas;
	private ArrayList<Pronostico> pronosticos;
	
	public Persona(String nombre, int puntosPartidos, int puntosRondas, int puntosFases, int cantidadDeAciertos, int cantidadRondasAcertadas, int cantidadFasesAcertadas) {
		this.nombre = nombre;
		this.puntosPartidos = puntosPartidos;
		this.puntosRondas = puntosRondas;
		this.puntosFases = puntosFases;
		this.cantidadDeAciertos = cantidadDeAciertos;
		this.cantidadRondasAcertadas = cantidadRondasAcertadas;
		this.cantidadFasesAcertadas = cantidadFasesAcertadas;
		this.pronosticos=new ArrayList<Pronostico>();
	}
	
	public Persona() {
		this.nombre = null;
		this.puntosPartidos = 0;
		this.puntosRondas = 0;
		this.puntosFases = 0;
		this.cantidadDeAciertos = 0;
		this.cantidadRondasAcertadas = 0;
		this.cantidadFasesAcertadas = 0;
		this.pronosticos=new ArrayList<Pronostico>();
	}
	
	public String getNombre() {
		return this.nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre=nombre;
	}
	
	public int getPuntosPartidos() {
		return this.puntosPartidos;
	}
	
	public void setCantidadDeAciertos(int cantidadDeAciertos) {
		this.cantidadDeAciertos=cantidadDeAciertos;
	}	
	public int getCantidadDeAciertos() {
		return this.cantidadDeAciertos;
	}
	
	public void setCantidadRondasAcertadas(int cantidadRondasAcertadas) {
		this.cantidadRondasAcertadas=cantidadRondasAcertadas;
	}	
	public int getCantidadRondasAcertadas() {
		return this.cantidadRondasAcertadas;
	}
	
	public void setCantidadFasesAcertadas(int cantidadFasesAcertadas) {
		this.cantidadFasesAcertadas=cantidadFasesAcertadas;
	}	
	public int getCantidadFasesAcertadas() {
		return this.cantidadFasesAcertadas;
	}
	
	public void setPuntosPartidos(int puntos) {
		this.puntosPartidos=puntos;
	}

	public int getPuntosRondas() {
		return puntosRondas;
	}

	public void setPuntosRondas(int puntosRondas) {
		this.puntosRondas = puntosRondas;
	}

	public int getPuntosFases() {
		return puntosFases;
	}

	public void setPuntosFases(int puntosFases) {
		this.puntosFases = puntosFases;
	}
	
	public ArrayList<Pronostico> getPronosticos() {
		return this.pronosticos;
	}
	
	public void setPronosticos(ArrayList<Pronostico> pronosticos) {
		this.pronosticos=pronosticos;
	}
	
	public void agregarPronostico(Pronostico nuevoPronostico) {
		this.pronosticos.add(nuevoPronostico);
	}
	
	public int puntajeFinal() {
		return puntosPartidos+puntosRondas+puntosFases;
	}
	
	@Override
	public String toString() {
		return "Persona [nombre=" + nombre + ", puntosPartidos=" + puntosPartidos + ", puntosRondas=" + puntosRondas
				+ ", puntosFases=" + puntosFases + ", cantidadDeAciertos=" + cantidadDeAciertos
				+ ", cantidadRondasAcertadas=" + cantidadRondasAcertadas + ", cantidadFasesAcertadas="
				+ cantidadFasesAcertadas + ", pronosticos=" + pronosticos + "]";
	}
}
