package modelo;

//import static modelo.ConectorSQL.DB_URL;
import static modelo.ConectorSQL.USER;
import static modelo.ConectorSQL.PASS;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

public class PronosticosBD {

	public static int setPronosticosBD(LectorArchivosCSV lectorArchivos, String baseDeDatos) {
		String persona;
		Integer idPartido;
		Integer idEquipo1;
		String gana1;
		String empata;
		String gana2;
		Integer idEquipo2;
		Connection conexion = null;
		Statement consulta = null;
		String sql;
		int contadorRegistros=0;

		try {
			conexion = DriverManager.getConnection(baseDeDatos, USER, PASS);
			consulta = conexion.createStatement();
			consulta.executeUpdate("delete from pronosticosCSV;");
			consulta.executeUpdate("alter table pronosticosCSV AUTO_INCREMENT = 1;");
			for (ArchivoPronosticos lineaArchivoPronosticos : lectorArchivos.lineasArchivoPronosticos) {
				persona=lineaArchivoPronosticos.getPersona();
				idPartido=lineaArchivoPronosticos.getIdPartido();
				idEquipo1=lineaArchivoPronosticos.getIdEquipo1();
				gana1=lineaArchivoPronosticos.getGana1();
				empata=lineaArchivoPronosticos.getEmpata();
				gana2=lineaArchivoPronosticos.getGana2();
				idEquipo2=lineaArchivoPronosticos.getIdEquipo2();
				sql = "INSERT INTO pronosticoscsv (persona,idPartido,idEquipo1,gana1,empata,gana2,idEquipo2) "+
					  "VALUES (\""+persona+"\","+idPartido+","+idEquipo1+",\""+gana1+"\",\""+empata+"\",\""+gana2+"\","+idEquipo2+");";
				contadorRegistros += consulta.executeUpdate(sql);
//				System.out.println(sql);
			}
//			System.out.println("Registros agregados: "+contadorRegistros);
            consulta.close();
            conexion.close();
		} catch (SQLException se) {
			// Execpción ante problemas de conexión
			org.example.Main.limpiarConsola();
			se.printStackTrace();
			org.example.Main.pausa("\nPresione <enter> para terminar...");
		} finally {
			// Esta sentencia es para que ante un problema con la base igual se cierren las conexiones
			try {
				if (consulta != null)
					consulta.close();
			} catch (SQLException se2) {
			}
			try {
				if (conexion != null)
					conexion.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}
		return contadorRegistros;
	}

	public static ArrayList<Persona> getPronosticosBD(ArrayList<Partido> partidos, ArrayList<Equipo> equipos, String baseDeDatos) {
		ArrayList<Persona> personas = new ArrayList<Persona>();
		Connection conexion = null;
		Statement consulta = null;
		String sql;

		try {
			conexion = DriverManager.getConnection(baseDeDatos, USER, PASS);
			consulta = conexion.createStatement();
			sql = "SELECT * FROM pronosticoscsv ORDER BY persona, idPartido";
            ResultSet resultado = consulta.executeQuery(sql);
            
            boolean primerRegistro=true;
            Persona persona = new Persona();
            while (resultado.next()) {
            	if (primerRegistro) {
            		persona.setNombre(resultado.getString("persona"));
            		primerRegistro=false;
            	}
        		Pronostico nuevoPronostico = new Pronostico();
    			nuevoPronostico.setPartido(getPartidoPorId(resultado.getInt("idPartido"),partidos));
    			nuevoPronostico.setEquipo(getEquipoPorId(resultado.getInt("idEquipo1"),equipos));
    			nuevoPronostico.setPronostico(getPronostico(resultado.getString("gana1"), 
    														resultado.getString("empata"),
    					                                    resultado.getString("gana2")));
            	if (persona.getNombre().equals(resultado.getString("persona"))) {
            		persona.agregarPronostico(nuevoPronostico);
            	} else {
            		personas.add(persona);
            		persona = new Persona();
            		persona.setNombre(resultado.getString("persona"));
            		persona.agregarPronostico(nuevoPronostico);
            	}
            } 
            personas.add(persona);
            resultado.close();
			consulta.close();
            conexion.close();
		} catch (SQLException se) {
			// Execpción ante problemas de conexión
			org.example.Main.limpiarConsola();
			se.printStackTrace();
			org.example.Main.pausa("\nPresione <enter> para terminar...");
		} finally {
			// Esta sentencia es para que ante un problema con la base igual se cierren las conexiones
			try {
				if (consulta != null)
					consulta.close();
			} catch (SQLException se2) {
			}
			try {
				if (conexion != null)
					conexion.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}
		return personas;
	}
	
	private static Partido getPartidoPorId(Integer idPartido, ArrayList<Partido> partidos) {
		for (Partido partido : partidos) {
			if (partido.getIdPartido()==idPartido) {
				return partido;
			}
		}
		return null;
	}
	
    private static Equipo getEquipoPorId (Integer idEquipo,ArrayList<Equipo> equipos) {
		for (Equipo equipo : equipos) {
			if (equipo.getIdEquipo()==idEquipo) {
				return equipo;
			}
		}
		return null;
	}
    
	private static ResultadoEquipoEnum  getPronostico(String gana, String empata, String pierde) {
		if (gana.toUpperCase().equals("X")) {
			return ResultadoEquipoEnum.GANA;
		}
		if (empata.toUpperCase().equals("X")) {
			return ResultadoEquipoEnum.EMPATA;
		}
		if (pierde.toUpperCase().equals("X")) {
			return ResultadoEquipoEnum.PIERDE;
		}
		return null;
	}
}
