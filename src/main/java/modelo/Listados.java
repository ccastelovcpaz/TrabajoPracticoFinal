package modelo;

import java.util.ArrayList;

public class Listados {

	// public static void listarPuntosPersonas(ArrayList<Persona> personas)
	// public static void listarPronosticos(ArrayList<Pronostico> pronosticos)
	// public static void listarFases(ArrayList<Fase> fases)
	// public static void listarRondas(ArrayList<Ronda> rondas)
	// public static void listarPartidos(ArrayList<Partido> partidos)
	// public static void listarEquipos(ArrayList<Equipo> equipos)
	// public static void listarPronosticosCSV(LectorArchivosCSV lectorArchivos)
	// public static void listarResultadosCSV(LectorArchivosCSV lectorArchivos)
	
	public static void listarPuntosPersonas(ArrayList<Persona> personas) {
		System.out.println("Los resultados son:\n");
		for (Persona persona : personas) {
        	System.out.println("Persona: "+persona.getNombre()+", Total de puntos obtenidos: "+(persona.getPuntosPartidos()+persona.getPuntosRondas()+persona.getPuntosFases())+
        			           "\n         Cantidad de pronosticos acertados: "+persona.getCantidadDeAciertos()+", Puntos obtenidos por pronósticos: "+persona.getPuntosPartidos()+
        			           "\n         Cantidad de rondas acertadas: "+persona.getCantidadRondasAcertadas()+", Puntos obtenidos por rondas: "+persona.getPuntosRondas()+
        			           "\n         Cantidad de fases acertadas: "+persona.getCantidadFasesAcertadas()+", Puntos obtenidos por fases: "+persona.getPuntosFases()+"\n");
        }		
	}
    
	public static void listarPronosticos(ArrayList<Pronostico> pronosticos) {
		System.out.println("- Pronósticos:");
		for (int i=0; i<pronosticos.size();i++) {
			System.out.println("        - Pronóstico"+(i+1)+":");
			System.out.println("                - id: ");
			System.out.println("                - Partido: "+pronosticos.get(i).getPartido().getIdPartido()+" ("
															+pronosticos.get(i).getPartido().getEquipo1().getNombreEquipo()+" - "
															+pronosticos.get(i).getPartido().getEquipo2().getNombreEquipo()+")");
			System.out.println("                - Equipo: "+pronosticos.get(i).getEquipo().getNombreEquipo());
			System.out.println("                - Resultado: "+pronosticos.get(i).getPronostico());
			System.out.print("\n");
//			System.out.println("Pronóstico"+(i+1)+": "+pronosticos.get(i).toString());
		}
//		System.out.print("\n");
	}

	public static void listarFases(ArrayList<Fase> fases) {
		System.out.println("- Fases:");
		for (int i=0; i<fases.size();i++) {
			System.out.println("        - Fase"+(i+1)+":");
			System.out.println("                - id: "+fases.get(i).getIdFase());
			System.out.println("                - Número: "+fases.get(i).getNroFase());
			System.out.println("                - Cantidad de rondas: "+fases.get(i).getRondas().size());
			System.out.print("\n");
		}
	}
	
	public static void listarRondas(ArrayList<Ronda> rondas) {
		System.out.println("- Rondas:");
		for (int i=0; i<rondas.size();i++) {
			System.out.println("        - Ronda"+(i+1)+":");
			System.out.println("                - id: "+rondas.get(i).getIdRonda());
			System.out.println("                - Número: "+rondas.get(i).getNroRonda());
			System.out.println("                - Cantidad de partidos: "+rondas.get(i).getPartidos().size());
			System.out.print("\n");
		}
	}
	
	public static void listarPartidos(ArrayList<Partido> partidos) {
		System.out.println("- Partidos:");
		for (int i=0; i<partidos.size();i++) {
			System.out.println("        - Partido"+(i+1)+":");
			System.out.println("                - id: "+partidos.get(i).getIdPartido());
			System.out.println("                - Equipo1: "+partidos.get(i).getEquipo1().getNombreEquipo()+"(id:"+partidos.get(i).getEquipo1().getIdEquipo()+")");
			System.out.println("                - Equipo2: "+partidos.get(i).getEquipo2().getNombreEquipo()+"(id:"+partidos.get(i).getEquipo2().getIdEquipo()+")");
			System.out.println("                - Goles equipo 1: "+partidos.get(i).getGolesEquipo1());
			System.out.println("                - Goles equipo 2: "+partidos.get(i).getGolesEquipo2());
			System.out.print("\n");
//			System.out.println("Partido"+(i+1)+": "+partidos.get(i).toString());
		}
//		System.out.print("\n");
	}

	public static void listarEquipos(ArrayList<Equipo> equipos) {
		System.out.println("- Equipos:");
		for (int i=0; i<equipos.size();i++) {
			System.out.println("        - Equipo"+(i+1)+":");
			System.out.println("                - id: "+equipos.get(i).getIdEquipo());
			System.out.println("                - Nombre: "+equipos.get(i).getNombreEquipo());
			System.out.println("                - Descripción: "+equipos.get(i).getDescripcionEquipo());
//			System.out.println("Equipo"+(i+1)+": "+equipos.get(i).toString());
		}
		System.out.print("\n");
	}
	
    public static void listarPronosticosCSV(LectorArchivosCSV lectorArchivos) {
    	lectorArchivos.listarPronosticosCSV();
    }
	
    public static void listarResultadosCSV(LectorArchivosCSV lectorArchivos) {
    	lectorArchivos.listarResultadosCSV();
    }

}
