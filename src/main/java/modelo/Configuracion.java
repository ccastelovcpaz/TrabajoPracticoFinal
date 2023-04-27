package modelo;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Configuracion {

	private int puntosSiAciertaGanador;
	private int puntosSiAciertaEmpate;
	private int puntosSiAciertaPerdedor;
	private int puntosExtraAciertaRonda;
	private int puntosExtraAciertaFase;
	private int cantidadArgumentosArchivoResultados;
	private int cantidadArgumentosArchivoPronosticos;
	private String separadorCSV;
	private String baseDeDatos;
	private String informacion;
	
	public Configuracion() {} ;
	
//	public Configuracion(int puntosSiAciertaGanador, int puntosSiAciertaEmpate, int puntosSiAciertaPerdedor,
//			int puntosExtraAciertaRonda, int puntosExtraAciertaFase, int cantidadArgumentosArchivoResultados,
//			int cantidadArgumentosArchivoPronosticos, String separadorCSV, String baseDeDatos) {
//		this.puntosSiAciertaGanador = puntosSiAciertaGanador;
//		this.puntosSiAciertaEmpate = puntosSiAciertaEmpate;
//		this.puntosSiAciertaPerdedor = puntosSiAciertaPerdedor;
//		this.puntosExtraAciertaRonda = puntosExtraAciertaRonda;
//		this.puntosExtraAciertaFase = puntosExtraAciertaFase;
//		this.cantidadArgumentosArchivoResultados = cantidadArgumentosArchivoResultados;
//		this.cantidadArgumentosArchivoPronosticos = cantidadArgumentosArchivoPronosticos;
//		this.separadorCSV = separadorCSV;
//		this.baseDeDatos = baseDeDatos;
//	}

	public Configuracion(String rutaArchivoConfiguracion) {
        try {
        	List<String> lineasConfiguracion = Files.readAllLines(Paths.get(rutaArchivoConfiguracion));
        	this.puntosSiAciertaGanador = Integer.parseInt(lineasConfiguracion.get(0).substring(23));
    		this.puntosSiAciertaEmpate = Integer.parseInt(lineasConfiguracion.get(1).substring(22));
    		this.puntosSiAciertaPerdedor = Integer.parseInt(lineasConfiguracion.get(2).substring(24));
    		this.puntosExtraAciertaRonda = Integer.parseInt(lineasConfiguracion.get(3).substring(24));
    		this.puntosExtraAciertaFase = Integer.parseInt(lineasConfiguracion.get(4).substring(23));
    		this.cantidadArgumentosArchivoResultados = Integer.parseInt(lineasConfiguracion.get(5).substring(36));
    		this.cantidadArgumentosArchivoPronosticos = Integer.parseInt(lineasConfiguracion.get(6).substring(37));
    		this.separadorCSV = lineasConfiguracion.get(7).substring(13);
    		this.baseDeDatos = lineasConfiguracion.get(8).substring(12);
        } catch (IOException e) {
        	System.out.println(e);
        }
	}

	public int getPuntosSiAciertaGanador() {
		return this.puntosSiAciertaGanador;
	}

	public void setPuntosSiAciertaGanador(int puntosSiAciertaGanador) {
		this.puntosSiAciertaGanador = puntosSiAciertaGanador;
	}

	public int getPuntosSiAciertaEmpate() {
		return this.puntosSiAciertaEmpate;
	}

	public void setPuntosSiAciertaEmpate(int puntosSiAciertaEmpate) {
		this.puntosSiAciertaEmpate = puntosSiAciertaEmpate;
	}

	public int getPuntosSiAciertaPerdedor() {
		return this.puntosSiAciertaPerdedor;
	}

	public void setPuntosSiAciertaPerdedor(int puntosSiAciertaPerdedor) {
		this.puntosSiAciertaPerdedor = puntosSiAciertaPerdedor;
	}

	public int getPuntosExtraAciertaRonda() {
		return this.puntosExtraAciertaRonda;
	}

	public void setPuntosExtraAciertaRonda(int puntosExtraAciertaRonda) {
		this.puntosExtraAciertaRonda = puntosExtraAciertaRonda;
	}

	public int getPuntosExtraAciertaFase() {
		return this.puntosExtraAciertaFase;
	}

	public void setPuntosExtraAciertaFase(int puntosExtraAciertaFase) {
		this.puntosExtraAciertaFase = puntosExtraAciertaFase;
	}

	public int getCantidadArgumentosArchivoResultados() {
		return this.cantidadArgumentosArchivoResultados;
	}

	public void setCantidadArgumentosArchivoResultados(int cantidadArgumentosArchivoResultados) {
		this.cantidadArgumentosArchivoResultados = cantidadArgumentosArchivoResultados;
	}

	public int getCantidadArgumentosArchivoPronosticos() {
		return this.cantidadArgumentosArchivoPronosticos;
	}

	public void setCantidadArgumentosArchivoPronosticos(int cantidadArgumentosArchivoPronosticos) {
		this.cantidadArgumentosArchivoPronosticos = cantidadArgumentosArchivoPronosticos;
	}
	
	public String getSeparadorCSV() {
		return this.separadorCSV;
	}

	public void setSeparadorCSV(String separadorCSV) {
		this.separadorCSV = separadorCSV;
	}
	
	public String getBaseDeDatos() {
		return this.baseDeDatos;
	}

	public void setBaseDeDatos(String baseDeDatos) {
		this.baseDeDatos = baseDeDatos;
	}
	
	public String getInformacion() {
		return this.informacion;
	}

	public void setInformacion(String informacion) {
		this.informacion = informacion;
	}

	@Override
	public String toString() {
		return "Configuracion [puntosSiAciertaGanador=" + puntosSiAciertaGanador + "\npuntosSiAciertaEmpate="
				+ puntosSiAciertaEmpate + "\npuntosSiAciertaPerdedor=" + puntosSiAciertaPerdedor
				+ "\npuntosExtraAciertaRonda=" + puntosExtraAciertaRonda + "\npuntosExtraAciertaFase="
				+ puntosExtraAciertaFase + "\ncantidadArgumentosArchivoResultados="
				+ cantidadArgumentosArchivoResultados + "\ncantidadArgumentosArchivoPronosticos="
				+ cantidadArgumentosArchivoPronosticos + "\nseparadorCSV=" + separadorCSV + "\nbaseDeDatos="
				+ baseDeDatos + "]";
	}
	
	
}
