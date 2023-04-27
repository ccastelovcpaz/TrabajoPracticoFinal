package modelo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Listados {

	// public static void listarPuntosPersonas(ArrayList<Persona> personas)
	// public static void listarPronosticos(ArrayList<Pronostico> pronosticos)
	// public static void listarFases(ArrayList<Fase> fases)
	// public static void listarRondas(ArrayList<Ronda> rondas)
	// public static void listarPartidos(ArrayList<Partido> partidos)
	// public static void listarEquipos(ArrayList<Equipo> equipos)
	// public static void listarPronosticosCSV(LectorArchivosCSV lectorArchivos)
	// public static void listarResultadosCSV(LectorArchivosCSV lectorArchivos)
	
	public static void listarInformacion(Configuracion config) {
		System.out.println("INFORMACION GENERAL\n-------------------\n");
		System.out.println(config.getInformacion());
		System.out.println("Puntos por acierto GANADOR: "+config.getPuntosSiAciertaGanador());
		System.out.println("Puntos por acierto EMPATE: "+config.getPuntosSiAciertaEmpate());
		System.out.println("Puntos por acierto PERDEDOR: "+config.getPuntosSiAciertaPerdedor());
		System.out.println("Puntos extra por acierto de ronda: "+config.getPuntosExtraAciertaRonda());
		System.out.println("Puntos extra por acierto de fase: "+config.getPuntosExtraAciertaFase());
		System.out.println("\n");
	}
	
	public static void listarPuntosPersonas(ArrayList<Persona> personas, ArrayList<Fase> fases, ArrayList<Ronda> rondas, ArrayList<Partido> partidos, ArrayList<Equipo> equipos) {
		ordenarPersonasPorPuntaje(personas);
		System.out.println("LISTADO DE PARTICIPANTES Y PUNTOS OBTENIDOS\n-------------------------------------------\n");
		for (Persona persona : personas) {
        	System.out.println("Participante: "+persona.getNombre()+", Total de puntos obtenidos: "+(persona.puntajeFinal())+
        			           "\n         Cantidad de pronosticos acertados: "+persona.getCantidadDeAciertos()+", Puntos obtenidos por pronósticos: "+persona.getPuntosPartidos()+
        			           "\n         Cantidad de rondas acertadas: "+persona.getCantidadRondasAcertadas()+", Puntos obtenidos por rondas: "+persona.getPuntosRondas()+
        			           "\n         Cantidad de fases acertadas: "+persona.getCantidadFasesAcertadas()+", Puntos obtenidos por fases: "+persona.getPuntosFases()+"\n");
        }		
	}
    
	public static void listarPodio(ArrayList<Persona> personas) {
        ordenarPersonasPorPuntaje(personas);
		System.out.println("PODIO DE GANADORES\n------------------\n");
		int i=1;
		for (Persona persona : personas) {
        	System.out.println(i+"° lugar: "+persona.getNombre().toUpperCase()+", Total de puntos obtenidos: "+(persona.puntajeFinal())+
        			           "\n          Cantidad de pronosticos acertados: "+persona.getCantidadDeAciertos()+", Puntos obtenidos por pronósticos: "+persona.getPuntosPartidos()+
        			           "\n          Cantidad de rondas acertadas: "+persona.getCantidadRondasAcertadas()+", Puntos obtenidos por rondas: "+persona.getPuntosRondas()+
        			           "\n          Cantidad de fases acertadas: "+persona.getCantidadFasesAcertadas()+", Puntos obtenidos por fases: "+persona.getPuntosFases()+"\n");
        	i++;
        	if (i==4) break;
		}		
	}
	
	public static void listarPersonas(ArrayList<Persona> personas) {
		ordenarPersonasPorNombre(personas);
		System.out.println("LISTADO DE PARTICIPANTES\n------------------------\n");
		for (Persona persona : personas) {
			System.out.println("        - Nombre: "+persona.getNombre());
			System.out.println("        - Cantidad de partidos acertados: "+persona.getCantidadDeAciertos()+", puntos obtenidos: "+persona.getPuntosPartidos());
			System.out.println("        - Cantidad de rondas acertadas: "+persona.getCantidadRondasAcertadas()+", puntos obtenidos: "+persona.getPuntosRondas());
			System.out.println("        - Cantidad de fases acertadas: "+persona.getCantidadFasesAcertadas()+", puntos obtenidos: "+persona.getPuntosFases());
			System.out.println("        - Cantidad de pronósticos realizados: "+persona.getPronosticos().size());
			System.out.println("");
		}
	}
	
	public static void listarPronosticos(ArrayList<Persona> personas) {
		ordenarPersonasPorNombre(personas);
		System.out.println("LISTADO DE PRONOSTICOS\n----------------------\n");
		boolean primera=true;
        String personaTmp = null;
        for (Persona persona : personas) {
        	if (primera) {
        		personaTmp = persona.getNombre();
        		primera=false;
        	}
        	if (!personaTmp.equals(persona.getNombre())) {
        		personaTmp = persona.getNombre();
        	}
        	System.out.println("Participante: "+personaTmp.toUpperCase());
        	for (int i=0; i<persona.getPronosticos().size();i++) {
        		System.out.print("        - Pronóstico "+(i+1)+": ");
        		System.out.print("partido "+persona.getPronosticos().get(i).getPartido().getIdPartido()+" ("
        				+persona.getPronosticos().get(i).getPartido().getEquipo1().getNombreEquipo()+" - "
        				+persona.getPronosticos().get(i).getPartido().getEquipo2().getNombreEquipo()+"), ");
        		System.out.print(persona.getPronosticos().get(i).getEquipo().getNombreEquipo());
        		System.out.print(" "+persona.getPronosticos().get(i).getPronostico());
        		System.out.print("\n");
        		//			System.out.println("Pronóstico"+(i+1)+": "+pronosticos.get(i).toString());
        	}
        	System.out.print("\n");
        }
//		System.out.print("\n");
	}

	public static void listarFases(ArrayList<Fase> fases) {
		System.out.println("LISTADO DE FASES\n----------------\n");
		for (int i=0; i<fases.size();i++) {
			System.out.println("        - Fase "+(i+1)+": id "+fases.get(i).getIdFase()+", número: "+fases.get(i).getNroFase());
			System.out.println("          Cantidad de rondas: "+fases.get(i).getRondas().size());
			System.out.print("\n");
		}
	}
	
	public static void listarRondas(ArrayList<Ronda> rondas) {
		System.out.println("LISTADO DE RONDAS\n-----------------\n");
		for (int i=0; i<rondas.size();i++) {
			System.out.println("        - Ronda "+(i+1)+": id "+rondas.get(i).getIdRonda()+", número: "+rondas.get(i).getNroRonda());
			System.out.println("          Cantidad de partidos: "+rondas.get(i).getPartidos().size());
			System.out.print("\n");
		}
	}
	
	public static void listarPartidos(ArrayList<Partido> partidos) {
		System.out.println("LISTADO DE PARTIDOS\n-------------------\n");
		for (int i=0; i<partidos.size();i++) {
			System.out.println("        - Partido "+(i+1)+": id "+partidos.get(i).getIdPartido());
			System.out.println("          Equipos: "+partidos.get(i).getEquipo1().getNombreEquipo()+"(id:"+partidos.get(i).getEquipo1().getIdEquipo()+"), "
													+partidos.get(i).getEquipo2().getNombreEquipo()+"(id:"+partidos.get(i).getEquipo2().getIdEquipo()+")");
			System.out.println("          Resultado: "+partidos.get(i).getGolesEquipo1()+" - "+partidos.get(i).getGolesEquipo2());
			System.out.print("\n");
		}
	}

	public static void listarEquipos(ArrayList<Equipo> equipos) {
		System.out.println("LISTADO DE EQUIPOS\n------------------\n");
		for (int i=0; i<equipos.size();i++) {
			System.out.println("        - Equipo "+(i+1)+": "+equipos.get(i).getNombreEquipo()+" (id "+equipos.get(i).getIdEquipo()+")");
			System.out.println("          Descripción: "+equipos.get(i).getDescripcionEquipo());
			System.out.println("");
		}
	}
	
    public static void listarPronosticosCSV(LectorArchivosCSV lectorArchivos) {
    	lectorArchivos.listarPronosticosCSV();
    }
	
    public static void listarResultadosCSV(LectorArchivosCSV lectorArchivos) {
    	lectorArchivos.listarResultadosCSV();
    }

    public static int cantidadDePronosticos(ArrayList<Persona> personas) {
    	int cantidadDePronosticos=0;
    	for (Persona persona : personas) {
    		cantidadDePronosticos+=persona.getPronosticos().size();
    	}
    	return cantidadDePronosticos;
    }
    
	public static void ordenarPersonasPorPuntaje(ArrayList<Persona> personas) {
		Collections.sort(personas, new Comparator<Persona>() {
			@Override
			public int compare(Persona p1, Persona p2) {
//				return new Integer(p1.puntajeFinal()).compareTo(new Integer(p2.puntajeFinal()));  //orden ascendente
				return new Integer(p2.puntajeFinal()).compareTo(new Integer(p1.puntajeFinal()));  //orden descendente
			}
		});
	}
	
	public static void ordenarPersonasPorNombre(ArrayList<Persona> personas) {
		Collections.sort(personas, new Comparator<Persona>() {
			@Override
			public int compare(Persona p1, Persona p2) {
				return new String(p1.getNombre()).compareTo(new String(p2.getNombre()));  //orden ascendente
//				return new String(p2.getNombre()).compareTo(new String(p1.getNombre()));  //orden descendente
			}
		});
	}
	
}
