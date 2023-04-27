package modelo;

import java.util.ArrayList;

public class CalcularPuntos {
	
	private ArrayList<Persona> personas; 
	
	public static void CalcularPuntosPartidos(ArrayList<Persona> personas,ArrayList<Fase> fases, Configuracion config) {
		
		for (Persona persona : personas) {
			int cantidadDeAciertosPartidos=0;
			int cantidadDeAciertosRondas=0;
			int cantidadDeAciertosFases=0;
			int puntosPartidosTmp=0;
			int puntosPartidos=0;
			int puntosRondas=0;
			int puntosFases=0;
			int i=0;
			while (i<persona.getPronosticos().size()) {
				Fase fase = getFasePorIdPartido(persona.getPronosticos().get(i).getPartido().getIdPartido(),fases);
				int cantidadDeAciertosRondasTMP=0;
				while (fase == getFasePorIdPartido(persona.getPronosticos().get(i).getPartido().getIdPartido(),fases)
						&& i<persona.getPronosticos().size()) {
					Ronda ronda = getRondaPorIdPartido(persona.getPronosticos().get(i).getPartido().getIdPartido(),fase.getRondas());
					int cantidadDeAciertosPartidosTmp=0;
					while (ronda == getRondaPorIdPartido(persona.getPronosticos().get(i).getPartido().getIdPartido(),fase.getRondas())
							&& fase == getFasePorIdPartido(persona.getPronosticos().get(i).getPartido().getIdPartido(),fases)
									&& i<persona.getPronosticos().size()) {
						puntosPartidosTmp = persona.getPronosticos().get(i).getPuntos(config);
						if (puntosPartidosTmp>0) {
							cantidadDeAciertosPartidosTmp++;
							puntosPartidos+= puntosPartidosTmp;
						}
						i++;
						if (i==persona.getPronosticos().size()) break;
					}
					cantidadDeAciertosPartidos+=cantidadDeAciertosPartidosTmp;
					if (cantidadDeAciertosPartidosTmp==ronda.getPartidos().size()) {
						puntosRondas+=config.getPuntosExtraAciertaRonda();
						cantidadDeAciertosRondasTMP++;
					}
					if (i==persona.getPronosticos().size()) break;
				}
				if (cantidadDeAciertosRondasTMP==fase.getRondas().size()) {
					puntosFases+=config.getPuntosExtraAciertaFase();
					cantidadDeAciertosFases++;
				}
				cantidadDeAciertosRondas+=cantidadDeAciertosRondasTMP;
				if (i==persona.getPronosticos().size()) break;
			}
			persona.setPuntosPartidos(puntosPartidos);
			persona.setPuntosRondas(puntosRondas);
			persona.setPuntosFases(puntosFases);
			persona.setCantidadDeAciertos(cantidadDeAciertosPartidos);
			persona.setCantidadRondasAcertadas(cantidadDeAciertosRondas);
			persona.setCantidadFasesAcertadas(cantidadDeAciertosFases);
		}
	}

	private static Ronda getRondaPorIdPartido(int idPartido, ArrayList<Ronda> rondas) {
		for (Ronda ronda : rondas) {
			for (Partido partido : ronda.getPartidos()) {
				if (partido.getIdPartido() == idPartido) {
					return ronda;
				}
			}
		}
		return null;
	}

	private static Fase getFasePorIdPartido(int idPartido, ArrayList<Fase> fases) {
		for (Fase fase : fases) {
			for (Ronda ronda : fase.getRondas()) {
				for (Partido partido : ronda.getPartidos()) {
					if (partido.getIdPartido() == idPartido) {
						return fase;
					}
				}
			}
		}
		return null;
	}
	
}
