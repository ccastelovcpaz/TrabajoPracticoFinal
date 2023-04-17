package modelo;

public class Persona {
	
	private String nombre;
	private int puntosPartidos;
	private int puntosRondas;
	private int puntosFases;
	private int cantidadDeAciertos;
	private int cantidadRondasAcertadas;
	private int cantidadFasesAcertadas;
	
	public Persona(String nombre, int puntosPartidos, int puntosRondas, int puntosFases, int cantidadDeAciertos, int cantidadRondasAcertadas, int cantidadFasesAcertadas) {
		super();
		this.nombre = nombre;
		this.puntosPartidos = puntosPartidos;
		this.puntosRondas = puntosRondas;
		this.puntosFases = puntosFases;
		this.cantidadDeAciertos = cantidadDeAciertos;
		this.cantidadRondasAcertadas = cantidadRondasAcertadas;
		this.cantidadFasesAcertadas = cantidadFasesAcertadas;
	}
	
	public String getNombre() {
		return this.nombre;
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
	
}
