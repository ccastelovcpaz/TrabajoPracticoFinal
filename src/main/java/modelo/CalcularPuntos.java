package modelo;

import java.util.ArrayList;

public class CalcularPuntos {
	
	private ArrayList<Persona> personas; 
	
	public static ArrayList<Persona> CalcularPuntosPartidos(ArrayList<Pronostico> pronosticos,ArrayList<Fase> fases, Configuracion config) {
		ArrayList<Persona> personas = new ArrayList<Persona>();
		String persona = null;
		Fase fase;
		Ronda ronda;
		int puntosPartidos;
		int puntosRondas;
		int puntosFases;
		int puntosPartidosTmp;
		int cantidadDeAciertos;	
		int cantidadDeAciertosRondas;
		int cantidadDeAciertosFases;
		int i=0;
		
		while (i<pronosticos.size()) {
			persona = pronosticos.get(i).getPersona();
			puntosPartidos=0;
			puntosRondas=0;
			puntosFases=0;
			cantidadDeAciertos=0;
			cantidadDeAciertosRondas=0;
			cantidadDeAciertosFases=0;
			while (pronosticos.get(i).getPersona().equals(persona)
								&& i<pronosticos.size()) {
				
				fase = getFasePorIdPartido(pronosticos.get(i).getPartido().getIdPartido(),fases);
				int cantidadRondasFase=fase.getRondas().size();
				int cantidadDeAciertosRondasTMP=0;
				while (fase==getFasePorIdPartido(pronosticos.get(i).getPartido().getIdPartido(),fases)
								&& pronosticos.get(i).getPersona().equals(persona)
								&& i<pronosticos.size()) {
					
					ronda = getRondaPorIdPartido(pronosticos.get(i).getPartido().getIdPartido(),fase.getRondas());
					int cantidadPartidosRonda = ronda.getPartidos().size();
					int cantidadDeAciertosTmp=0;
					while (ronda==getRondaPorIdPartido(pronosticos.get(i).getPartido().getIdPartido(),fase.getRondas())
								&& fase==getFasePorIdPartido(pronosticos.get(i).getPartido().getIdPartido(),fases)
								&& pronosticos.get(i).getPersona().equals(persona)
								&& i<pronosticos.size()) {
						
						puntosPartidosTmp = pronosticos.get(i).getPuntos(config);
						if (puntosPartidosTmp>0) {
							cantidadDeAciertosTmp++;
							puntosPartidos+= puntosPartidosTmp;
						}
						i++;
						if (i==pronosticos.size()) break;
					} //fin ronda
					cantidadDeAciertos+=cantidadDeAciertosTmp;
					if (cantidadDeAciertosTmp==cantidadPartidosRonda) {
						puntosRondas+=config.getPuntosExtraAciertaRonda();
						cantidadDeAciertosRondasTMP++;
					}
					if (i==pronosticos.size()) break;
				} // fin fase
				if (cantidadDeAciertosRondasTMP==cantidadRondasFase) {
					puntosFases+=config.getPuntosExtraAciertaFase();
					cantidadDeAciertosFases++;
				}
				cantidadDeAciertosRondas+=cantidadDeAciertosRondasTMP;
				if (i==pronosticos.size()) break;
			} // fin pronosticos
			personas.add(new Persona(persona,puntosPartidos,puntosRondas,puntosFases,cantidadDeAciertos,cantidadDeAciertosRondas,cantidadDeAciertosFases));
		}
		return personas;
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
