package modelo;

//import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CalculoPuntajeTest {

	private ArrayList<Equipo> equipos = new ArrayList<Equipo>();
	private ArrayList<Partido> partidos = new ArrayList<Partido>();
	private ArrayList<Ronda> rondas = new ArrayList<Ronda>();
	private ArrayList<Fase> fases = new ArrayList<Fase>();
	Configuracion config = new Configuracion();
	
	@BeforeEach
	void inicializarClases() {
    	config.setPuntosSiAciertaGanador(1);
    	config.setPuntosSiAciertaEmpate(1);
    	config.setPuntosSiAciertaPerdedor(1);
    	config.setPuntosExtraAciertaRonda(1);
    	config.setPuntosExtraAciertaFase(1);
		equipos.add(new Equipo(1,"Argentina","Selección de Argentina"));
		equipos.add(new Equipo(1,"Chile","Selección de Chile"));
		equipos.add(new Equipo(1,"Colombia","Selección de Colombia"));
		equipos.add(new Equipo(1,"Uruguay","Selección de Uruguay"));
		partidos.add(new Partido(1,equipos.get(0),1,equipos.get(1),0)); // GANA
		partidos.add(new Partido(2,equipos.get(2),2,equipos.get(3),2)); // EMPATA
		partidos.add(new Partido(3,equipos.get(0),1,equipos.get(3),1)); // EMPATA
		partidos.add(new Partido(4,equipos.get(0),0,equipos.get(2),1)); // PIERDE
		partidos.add(new Partido(5,equipos.get(1),2,equipos.get(3),2)); // EMPATA
		Ronda nuevaRonda = new Ronda(1,"1");
		nuevaRonda.agregarPartido(partidos.get(0));
		nuevaRonda.agregarPartido(partidos.get(1));
		nuevaRonda.agregarPartido(partidos.get(2));
		rondas.add(nuevaRonda);
		nuevaRonda = new Ronda(2,"2");
		nuevaRonda.agregarPartido(partidos.get(3));
		nuevaRonda.agregarPartido(partidos.get(4));
		rondas.add(nuevaRonda);
		Fase nuevaFase = new Fase(1,"1");
		nuevaFase.setRondas(rondas);
		fases.add(nuevaFase);
	}
	
	@Test
	void testCalculoPuntos1() {
		ArrayList<Pronostico> pronosticos = new ArrayList<Pronostico>();
		pronosticos.add(new Pronostico("Claudio",partidos.get(0),equipos.get(0),ResultadoEquipoEnum.GANA));
		pronosticos.add(new Pronostico("Claudio",partidos.get(1),equipos.get(2),ResultadoEquipoEnum.GANA));
		pronosticos.add(new Pronostico("Claudio",partidos.get(2),equipos.get(0),ResultadoEquipoEnum.EMPATA));
		pronosticos.add(new Pronostico("Claudio",partidos.get(3),equipos.get(0),ResultadoEquipoEnum.PIERDE));
		pronosticos.add(new Pronostico("Claudio",partidos.get(4),equipos.get(1),ResultadoEquipoEnum.EMPATA));
		ArrayList<Persona> personas = CalcularPuntos.CalcularPuntosPartidos(pronosticos,fases,config);
		assertEquals(4,personas.get(0).getPuntosPartidos());
		assertEquals(4,personas.get(0).getCantidadDeAciertos());
	}

	@Test
	void testCalculoPuntos2() {
		ArrayList<Pronostico> pronosticos = new ArrayList<Pronostico>();
		pronosticos.add(new Pronostico("Claudio",partidos.get(0),equipos.get(0),ResultadoEquipoEnum.GANA));
		pronosticos.add(new Pronostico("Claudio",partidos.get(1),equipos.get(2),ResultadoEquipoEnum.EMPATA));
		pronosticos.add(new Pronostico("Claudio",partidos.get(2),equipos.get(0),ResultadoEquipoEnum.EMPATA));
		pronosticos.add(new Pronostico("Claudio",partidos.get(3),equipos.get(0),ResultadoEquipoEnum.PIERDE));
		pronosticos.add(new Pronostico("Claudio",partidos.get(4),equipos.get(1),ResultadoEquipoEnum.EMPATA));
		ArrayList<Persona> personas = CalcularPuntos.CalcularPuntosPartidos(pronosticos,fases,config);
		assertFalse(personas.get(0).getPuntosPartidos()==4);
		assertFalse(personas.get(0).getCantidadDeAciertos()==4);
	}
}
