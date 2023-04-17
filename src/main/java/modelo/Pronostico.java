package modelo;

public class Pronostico {
	
	private String persona;
	private Partido partido;
	private Equipo equipo;
//	private String pronostico;
	private ResultadoEquipoEnum pronostico;
	
	public Pronostico(String persona, Partido partido, Equipo equipo, ResultadoEquipoEnum pronostico) {
		this.persona = persona;
		this.partido = partido;
		this.equipo = equipo;
		this.pronostico = pronostico;
	}

	public Pronostico() {
	}
	
	public String getPersona() {
		return this.persona;
	}

	public void setPersona(String persona) {
		this.persona = persona;
	}
	
	public Partido getPartido() {
		return this.partido;
	}

	public void setPartido(Partido partido) {
		this.partido = partido;
	}

	public Equipo getEquipo() {
		return this.equipo;
	}

	public void setEquipo(Equipo equipo) {
		this.equipo = equipo;
	}

	public ResultadoEquipoEnum getPronostico() {
		return this.pronostico;
	}

	public void setPronostico(ResultadoEquipoEnum pronostico) {
		this.pronostico = pronostico;
	}
	
	@Override
	public String toString() {
		return "[persona=" + persona + ", partido=" + partido + ", equipo=" + equipo + ", pronostico=" + pronostico + "]";
	}

	public int getPuntos(Configuracion config) {
		int puntos = 0;
		ResultadoEquipoEnum resultadoRealParaEquipo = partido.resultado(this.equipo.getIdEquipo());
		if (resultadoRealParaEquipo == this.pronostico) {
			if (this.pronostico == ResultadoEquipoEnum.GANA) {
				puntos=config.getPuntosSiAciertaGanador();
			}
			if (this.pronostico == ResultadoEquipoEnum.EMPATA) {
				puntos=config.getPuntosSiAciertaEmpate();
			}
			if (this.pronostico == ResultadoEquipoEnum.PIERDE) {
				puntos=config.getPuntosSiAciertaPerdedor();
			}
		}
		return puntos;
	}
}
