package modelo;

import java.util.ArrayList;

public class Ronda {

	private int idRonda;
	private String nroRonda;
	private ArrayList<Partido> partidos;
	   	
	public Ronda(int idRonda, String nroRonda) {
		this.idRonda = idRonda;
		this.nroRonda = nroRonda;
		this.partidos = new ArrayList<Partido>();
	}
	
	public int getIdRonda() {
		return this.idRonda;
	}
	
	public void setIdRonda(int idRonda) {
		this.idRonda = idRonda;
	}
	
	public String getNroRonda() {
		return this.nroRonda;
	}
	
	public void setNroRonda(String nroRonda) {
		this.nroRonda=nroRonda;
	}
	
	public ArrayList<Partido> getPartidos() {
		return this.partidos;
	}
	
	public void setPartidos(ArrayList<Partido> partidos) {
		this.partidos=partidos;
	}
	
	public void agregarPartido(Partido partido) {
		this.partidos.add(partido);
	}

	@Override
	public String toString() {
		return "Ronda [idRonda=" + idRonda + ", nroRonda=" + nroRonda + ", partidos=" + partidos + "]";
	}
	
	
}
