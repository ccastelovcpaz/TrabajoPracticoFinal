package modelo;

import com.opencsv.bean.CsvBindByPosition;

public class ArchivoPronosticos {

	@CsvBindByPosition(position = 0)
    private String persona;
	@CsvBindByPosition(position = 1)
    private Integer idPartido;
    @CsvBindByPosition(position = 2)
    private Integer idEquipo1;
    @CsvBindByPosition(position = 3)
    private String gana1;
    @CsvBindByPosition(position = 4)
//    @CsvDate(value = "yyyy-MM-dd")
    private String empata;
    @CsvBindByPosition(position = 5)
    private String gana2;
    @CsvBindByPosition(position = 6)
    private Integer idEquipo2;

    public Integer getIdPartido() {
    	return this.idPartido;
    }
    
	public String getPersona() {
		return persona;
	}

	public void setPersona(String persona) {
		this.persona = persona;
	}

	public Integer getIdEquipo1() {
		return idEquipo1;
	}

	public void setIdEquipo1(Integer idEquipo1) {
		this.idEquipo1 = idEquipo1;
	}

	public String getGana1() {
		return gana1;
	}

	public void setGana1(String gana1) {
		this.gana1 = gana1;
	}

	public String getEmpata() {
		return empata;
	}

	public void setEmpata(String empata) {
		this.empata = empata;
	}

	public String getGana2() {
		return gana2;
	}

	public void setGana2(String gana2) {
		this.gana2 = gana2;
	}

	public Integer getIdEquipo2() {
		return idEquipo2;
	}

	public void setIdEquipo2(Integer idEquipo2) {
		this.idEquipo2 = idEquipo2;
	}

	public void setIdPartido(Integer idPartido) {
		this.idPartido = idPartido;
	}

	@Override
	public String toString() {
		return "ArchivoPronosticos [persona=" + persona + ", idPartido=" + idPartido + ", idEquipo1=" + idEquipo1
				+ ", gana1=" + gana1 + ", empata=" + empata + ", gana2=" + gana2 + ", idEquipo2=" + idEquipo2 + "]\n";
	}
    
    
    
}
