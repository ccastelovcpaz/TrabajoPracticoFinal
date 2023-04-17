package modelo;

import java.util.ArrayList;

public class Fase {

	private int idFase;
	private String nroFase;
	private ArrayList<Ronda> rondas;
	
	public Fase(int idFase, String nroFase) {
		super();
		this.idFase = idFase;
		this.nroFase = nroFase;
		this.rondas = new ArrayList<Ronda>();
	}
	public int getIdFase() {
		return idFase;
	}
	public void setIdFase(int idFase) {
		this.idFase = idFase;
	}
	public String getNroFase() {
		return nroFase;
	}
	public void setNroFase(String nroFase) {
		this.nroFase = nroFase;
	}
	public ArrayList<Ronda> getRondas() {
		return rondas;
	}
	public void agregarRonda(Ronda ronda) {
		this.rondas.add(ronda);
	}
	public void setRondas(ArrayList<Ronda> rondas) {
		this.rondas = rondas;
	}
	@Override
	public String toString() {
		return "Fase [idFase=" + idFase + ", nroFase=" + nroFase + ", rondas=" + rondas + "]";
	}
	
	
}
