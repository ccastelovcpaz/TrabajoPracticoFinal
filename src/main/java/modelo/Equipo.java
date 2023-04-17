package modelo;

public class Equipo {

	private int idEquipo;
	private String nombreEquipo;
	private String descripcionEquipo;
	
	public Equipo(int idEquipo, String nombreEquipo, String descripcionEquipo) {
		this.idEquipo = idEquipo;
		this.nombreEquipo = nombreEquipo;
		this.descripcionEquipo = descripcionEquipo;
	}

	public int getIdEquipo() {
		return idEquipo;
	}

	public void setIdEquipo(int idEquipo) {
		this.idEquipo = idEquipo;
	}

	public String getNombreEquipo() {
		return nombreEquipo;
	}

	public void setNombreEquipo(String nombreEquipo) {
		this.nombreEquipo = nombreEquipo;
	}

	public String getDescripcionEquipo() {
		return descripcionEquipo;
	}

	public void setDescripcionEquipo(String descripcionEquipo) {
		this.descripcionEquipo = descripcionEquipo;
	}
	
	@Override
	public String toString() {
		return "[idEquipo=" + idEquipo + ", nombreEquipo=" + nombreEquipo + ", descripcionEquipo="
				+ descripcionEquipo + "]";
	}
	
	
}
