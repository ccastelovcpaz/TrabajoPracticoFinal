package modelo;

import java.time.LocalDate;

import com.opencsv.bean.CsvBindByPosition;
import com.opencsv.bean.CsvDate;

public class ArchivoResultados {
	@CsvBindByPosition(position = 0)
    private Integer idFase;
	@CsvBindByPosition(position = 1)
    private Integer idRonda;
    @CsvBindByPosition(position = 2)
    private Integer idPartido;
    @CsvBindByPosition(position = 3)
    private Integer idEquipo1;
    @CsvBindByPosition(position = 4)
//    @CsvDate(value = "yyyy-MM-dd")
    private String nombreEquipo1;
    @CsvBindByPosition(position = 5)
    private String descripcionEquipo1;
    @CsvBindByPosition(position = 6)
    private String golesEquipo1;
    @CsvBindByPosition(position = 7)
    private Integer idEquipo2;
    @CsvBindByPosition(position = 8)
    private String nombreEquipo2;
    @CsvBindByPosition(position = 9)
    private String descripcionEquipo2;
    @CsvBindByPosition(position = 10)
    private String golesEquipo2;
	
    public Integer getIdFase() {
		return idFase;
	}
	public void setIdFase(Integer idFase) {
		this.idFase = idFase;
	}
    public Integer getIdRonda() {
		return idRonda;
	}
	public void setIdRonda(Integer idRonda) {
		this.idRonda = idRonda;
	}
	public Integer getIdPartido() {
		return idPartido;
	}
	public void setIdPartido(Integer idPartido) {
		this.idPartido = idPartido;
	}
	public Integer getIdEquipo1() {
		return idEquipo1;
	}
	public void setIdEquipo1(Integer idEquipo1) {
		this.idEquipo1 = idEquipo1;
	}
	public String getNombreEquipo1() {
		return nombreEquipo1;
	}
	public void setNombreEquipo1(String nombreEquipo1) {
		this.nombreEquipo1 = nombreEquipo1;
	}
	public String getDescripcionEquipo1() {
		return descripcionEquipo1;
	}
	public void setDescripcionEquipo1(String descripcionEquipo1) {
		this.descripcionEquipo1 = descripcionEquipo1;
	}
	public String getGolesEquipo1() {
		return golesEquipo1;
	}
	public void setGolesEquipo1(String golesEquipo1) {
		this.golesEquipo1 = golesEquipo1;
	}
	public Integer getIdEquipo2() {
		return idEquipo2;
	}
	public void setIdEquipo2(Integer idEquipo2) {
		this.idEquipo2 = idEquipo2;
	}
	public String getNombreEquipo2() {
		return nombreEquipo2;
	}
	public void setNombreEquipo2(String nombreEquipo2) {
		this.nombreEquipo2 = nombreEquipo2;
	}
	public String getDescripcionEquipo2() {
		return descripcionEquipo2;
	}
	public void setDescripcionEquipo2(String descripcionEquipo2) {
		this.descripcionEquipo2 = descripcionEquipo2;
	}
	public String getGolesEquipo2() {
		return golesEquipo2;
	}
	public void setGolesEquip2(String golesEquipo2) {
		this.golesEquipo2 = golesEquipo2;
	}
	
	@Override
	public String toString() {
		return "ArchivoResultados [idFase="+ idFase +", idRonda=" + idRonda + ", idPartido=" + idPartido + ", \nidEquipo1=" + idEquipo1
				+ ", nombreEquipo1=" + nombreEquipo1 + ", descripcionEquipo1=" + descripcionEquipo1 + ", golesEquipo1="
				+ golesEquipo1 + ", \nidEquipo2=" + idEquipo2 + ", nombreEquipo2=" + nombreEquipo2
				+ ", descripcionEquipo2=" + descripcionEquipo2 + ", golesEquip2=" + golesEquipo2 + "]\n";
	}

}
