package modelo;

public class Partido {

	private int idPartido;
	private Equipo equipo1;
	private int golesEquipo1;
	private Equipo equipo2;
	private int golesEquipo2;

//	public Partido(String[] lineaCSV) {
//		this.idPartido=Integer.parseInt(lineaCSV[0]);
//	    this.equipo1=new Equipo(Integer.parseInt(lineaCSV[1]),lineaCSV[2],lineaCSV[3]);
//	    this.golesEquipo1=Integer.parseInt(lineaCSV[4]);
//	    this.equipo2=new Equipo(Integer.parseInt(lineaCSV[5]),lineaCSV[6],lineaCSV[7]);
//	    this.golesEquipo2=Integer.parseInt(lineaCSV[8]);
//	}

	public Partido(int idPartido, Equipo equipo1, int golesEquipo1, Equipo equipo2, int golesEquipo2) {
		this.idPartido = idPartido;
		this.equipo1 = equipo1;
		this.golesEquipo1 = golesEquipo1;
		this.equipo2 = equipo2;
		this.golesEquipo2 = golesEquipo2;
	}
	
	public Partido(int idPartido, int golesEquipo1, int golesEquipo2) {
		this.idPartido = idPartido;
		this.golesEquipo1 = golesEquipo1;
		this.golesEquipo2 = golesEquipo2;
	}
	
	public int getIdPartido() {
		return idPartido;
	}

	public void setIdPartido(int idPartido) {
		this.idPartido = idPartido;
	}

	public Equipo getEquipo1() {
		return equipo1;
	}

	public void setEquipo1(Equipo equipo1) {
		this.equipo1 = equipo1;
	}

	public int getGolesEquipo1() {
		return golesEquipo1;
	}

	public void setGolesEquipo1(int golesEquipo1) {
		this.golesEquipo1 = golesEquipo1;
	}

	public Equipo getEquipo2() {
		return equipo2;
	}

	public void setEquipo2(Equipo equipo2) {
		this.equipo2 = equipo2;
	}

	public int getGolesEquipo2() {
		return golesEquipo2;
	}

	public void setGolesEquipo2(int golesEquipo2) {
		this.golesEquipo2 = golesEquipo2;
	}
	
	@Override
	public String toString() {
		return "[idPartido=" + idPartido + ", equipo1=" + equipo1 + ", golesEquipo1=" + golesEquipo1
				+ ", equipo2=" + equipo2 + ", golesEquipo2=" + golesEquipo2 + "]";
	}

	public ResultadoEquipoEnum resultado(int idEquipo) {
		if (idEquipo == this.equipo1.getIdEquipo()) {
			if (this.golesEquipo1 > this.golesEquipo2) {
				return ResultadoEquipoEnum.GANA;
			}
			if (this.golesEquipo1 < this.golesEquipo2) {
				return ResultadoEquipoEnum.PIERDE;
			}
			return ResultadoEquipoEnum.EMPATA;
		}
		if (idEquipo == this.equipo2.getIdEquipo()) {
			if (this.golesEquipo2 > this.golesEquipo1) {
				return ResultadoEquipoEnum.GANA;
			}
			if (this.golesEquipo2 < this.golesEquipo1) {
				return ResultadoEquipoEnum.PIERDE;
			}
			return ResultadoEquipoEnum.PIERDE;
		}
		return null;
	}
}
