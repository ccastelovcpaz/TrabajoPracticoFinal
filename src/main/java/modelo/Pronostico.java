package modelo;

public class Pronostico {
	private Partido partido;
	private Equipo equipo;
	private ResultadoEquipoEnum pronostico;
	
	public Pronostico(Partido partido, Equipo equipo, ResultadoEquipoEnum pronostico) {
		this.partido = partido;
		this.equipo = equipo;
		this.pronostico = pronostico;
	}

	public Pronostico() {
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
		return "[partido=" + partido + ", equipo=" + equipo + ", pronostico=" + pronostico + "]";
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
