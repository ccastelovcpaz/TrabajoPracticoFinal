package modelo;

import com.opencsv.bean.CsvToBeanBuilder;

import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class LectorArchivosCSV {

    String rutaArchivo;
    String separadorCSV;
    List<ArchivoResultados> lineasArchivoResultados;
    List<ArchivoPronosticos> lineasArchivoPronosticos;
    
    public LectorArchivosCSV(String rutaArchivo, String separadorCSV) {
        this.rutaArchivo = rutaArchivo;
        this.separadorCSV = separadorCSV;
        this.lineasArchivoResultados = new ArrayList<>();
        this.lineasArchivoPronosticos = new ArrayList<>();
    }
    
    public void setRutaArchivo (String rutaArchivo) {
    	this.rutaArchivo=rutaArchivo;
    }
   
    public void controlOKCantidadArgumentosArhivo(int cantidadArgumentos) throws LineaIncorrectaException {
    	String[] lineaCortada = null;
    	try {
    		for (String linea : Files.readAllLines(Paths.get(this.rutaArchivo))) {
    			lineaCortada = linea.split(this.separadorCSV);
    			if (lineaCortada.length!=cantidadArgumentos) {
    				throw new LineaIncorrectaException(linea.toString()+"\n[Se esperaban "+cantidadArgumentos+" argumentos y se recibieron "+lineaCortada.length+"]");
    			}
    		}
    	} catch (IOException e) {
    		System.out.println(e);
    	}
    }
    
    public void parsearArchivoResultados() throws LineaIncorrectaException {
        List<ArchivoResultados> listaDeResultados = null;
        try {
            // En esta primera línea definimos el archivos que va a ingresar
        	listaDeResultados = new CsvToBeanBuilder(new FileReader(this.rutaArchivo))
                    // con esta configuración podemos skipear la primera línea de nuestro archivo CSV
                    .withSkipLines(1)
                    // con esta configuración podemos elegir cual es el caracter que vamos a usar para delimitar
                    .withSeparator(';')
                    // Es necesario definir el tipo de dato que va a generar el objeto que estamos queriendo parsear a partir del CSV
                    .withType(ArchivoResultados.class)
                    .build()
                    .parse();

        } catch (IOException e) {
        	e.printStackTrace();
        }
        for (int i=0; i<listaDeResultados.size(); i++) {
//        	if (listaDeResultados.get(i).getIdRonda()==null) {
//        		listaDeResultados.remove(i);
//        	}
        	if (listaDeResultados.get(i).getIdFase()==null || listaDeResultados.get(i).getIdRonda()==null || listaDeResultados.get(i).getIdPartido()==null
        			|| listaDeResultados.get(i).getIdEquipo1()==null || listaDeResultados.get(i).getIdEquipo2()==null
        			|| listaDeResultados.get(i).getNombreEquipo1()==null || listaDeResultados.get(i).getNombreEquipo2()==null
        			|| listaDeResultados.get(i).getGolesEquipo1()==null || listaDeResultados.get(i).getGolesEquipo2()==null
        			|| listaDeResultados.get(i).getDescripcionEquipo1()==null || listaDeResultados.get(i).getDescripcionEquipo2()==null) {
        		throw new LineaIncorrectaException(listaDeResultados.get(i).toString()+"\n[Se encontraron campos con valor nulo]");
        	}
        	if (!esNumero(listaDeResultados.get(i).getGolesEquipo1()) || !esNumero(listaDeResultados.get(i).getGolesEquipo2())) {
        		throw new LineaIncorrectaException(listaDeResultados.get(i).toString()+"\n[Los valores de goles de los equipos deben ser numéricos]");
        	}
        }
        this.lineasArchivoResultados = listaDeResultados;
    }
	
	private static boolean esNumero(String cadena){
		try {
			Integer.parseInt(cadena);
			return true;
		} catch (NumberFormatException nfe){
			return false;
		}
	}
	
    public ArrayList<Equipo> cargarEquipos() {
    	ArrayList<Equipo> equipos = new ArrayList<Equipo>();
    	for (ArchivoResultados lineaArchivoResultados : this.lineasArchivoResultados) {
    		if (!existeEquipo(lineaArchivoResultados.getIdEquipo1(),equipos)) {
    			equipos.add(new Equipo(lineaArchivoResultados.getIdEquipo1(),
    								   lineaArchivoResultados.getNombreEquipo1(),
    								   lineaArchivoResultados.getDescripcionEquipo1()));
    		}
    		if (!existeEquipo(lineaArchivoResultados.getIdEquipo2(),equipos)) {
    			equipos.add(new Equipo(lineaArchivoResultados.getIdEquipo2(),
    								   lineaArchivoResultados.getNombreEquipo2(),
    								   lineaArchivoResultados.getDescripcionEquipo2()));
    		}
    	}
    	return equipos;
    }
    
    public ArrayList<Partido> cargarPartidos(ArrayList<Equipo> equipos) {
    	ArrayList<Partido> partidos = new ArrayList<Partido>();
    	Partido nuevoPartido = null;
    	for (ArchivoResultados lineaArchivoResultados : this.lineasArchivoResultados) {
    		nuevoPartido = new Partido(lineaArchivoResultados.getIdPartido(),
    				                 Integer.parseInt(lineaArchivoResultados.getGolesEquipo1()),
    				                 Integer.parseInt(lineaArchivoResultados.getGolesEquipo2()));
    		nuevoPartido.setEquipo1(getEquipoPorId(lineaArchivoResultados.getIdEquipo1(),equipos));
    		nuevoPartido.setEquipo2(getEquipoPorId(lineaArchivoResultados.getIdEquipo2(),equipos));
    		partidos.add(nuevoPartido);
    	}   	
    	return partidos;
    }
    
    private Equipo getEquipoPorId (Integer idEquipo,ArrayList<Equipo> equipos) {
		for (Equipo equipo : equipos) {
			if (equipo.getIdEquipo()==idEquipo) {
				return equipo;
			}
		}
		return null;
	}
    
    private boolean existeEquipo(Integer idEquipo,ArrayList<Equipo> equipos) {
		for (Equipo equipo : equipos) {
			if (equipo.getIdEquipo()==idEquipo) {
				return true;
			}
		}
		return false;
	}

	public void listarResultadosCSV() {
    	for (ArchivoResultados lineaArchivoResultados : this.lineasArchivoResultados) {
    		System.out.println(lineaArchivoResultados.toString());
    	}
    }

    public void parsearArchivoPronosticos() throws LineaIncorrectaException {
        List<ArchivoPronosticos> listaDeResultados = null;
        try {
            // En esta primera línea definimos el archivos que va a ingresar
        	listaDeResultados = new CsvToBeanBuilder(new FileReader(this.rutaArchivo))
                    // con esta configuración podemos skipear la primera línea de nuestro archivo CSV
                    .withSkipLines(1)
                    // con esta configuración podemos elegir cual es el caracter que vamos a usar para delimitar
                    .withSeparator(';')
                    // Es necesario definir el tipo de dato que va a generar el objeto que estamos queriendo parsear a partir del CSV
                    .withType(ArchivoPronosticos.class)
                    .build()
                    .parse();

        } catch (IOException e) {
        e.printStackTrace();
        }
        for (int i=0; i<listaDeResultados.size(); i++) {
//        	if (listaDeResultados.get(i).getIdPartido()==null) {
//        		listaDeResultados.remove(i);
//        	}
        	if (listaDeResultados.get(i).getPersona()==null || listaDeResultados.get(i).getIdPartido()==null
        			|| listaDeResultados.get(i).getIdEquipo1()==null || listaDeResultados.get(i).getIdEquipo2()==null
        			|| listaDeResultados.get(i).getGana1()==null || listaDeResultados.get(i).getEmpata()==null
        			|| listaDeResultados.get(i).getGana2()==null) {
        		throw new LineaIncorrectaException(listaDeResultados.get(i).toString()+"\n[Se encontraron campos con valor nulo]");
        	}
        }
        
        
        this.lineasArchivoPronosticos = listaDeResultados;
    }
    
    public void listarPronosticosCSV() {
    	for (ArchivoPronosticos lineaArchivoPronosticos : this.lineasArchivoPronosticos) {
    		System.out.println(lineaArchivoPronosticos.toString());
    	}
    }

	public ArrayList<Pronostico> cargarPronosticos(ArrayList<Partido> partidos, ArrayList<Equipo> equipos) {
		ArrayList<Pronostico> pronosticos = new ArrayList<Pronostico>();
		for (ArchivoPronosticos lineaArchivoPronosticos : this.lineasArchivoPronosticos) {
			Pronostico nuevoPronostico = new Pronostico();
//
//			nuevoPronostico.setPersona(lineaArchivoPronosticos.getPersona());
//
			nuevoPronostico.setPartido(getPartidoPorId(lineaArchivoPronosticos.getIdPartido(),partidos));
			nuevoPronostico.setEquipo(getEquipoPorId(lineaArchivoPronosticos.getIdEquipo1(),equipos));
			nuevoPronostico.setPronostico(getPronostico(lineaArchivoPronosticos.getGana1(), 
					                                    lineaArchivoPronosticos.getEmpata(),
					                                    lineaArchivoPronosticos.getGana2()));
    		pronosticos.add(nuevoPronostico);
//    		System.out.println(nuevoPronostico);
    	}
		return pronosticos;
	}

	private Partido getPartidoPorId(Integer idPartido, ArrayList<Partido> partidos) {
		for (Partido partido : partidos) {
			if (partido.getIdPartido()==idPartido) {
				return partido;
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

	public ArrayList<Ronda> cargarRondas(ArrayList<Partido> partidos) {
		ArrayList<Ronda> rondas = new ArrayList<Ronda>();
		Ronda nuevaRonda;
		int i=0;
		int ronda;
		while (i<this.lineasArchivoResultados.size()) {
			ronda = this.lineasArchivoResultados.get(i).getIdRonda();
			nuevaRonda = new Ronda(ronda,String.valueOf(ronda));
			do {
				nuevaRonda.agregarPartido(getPartidoPorId(this.lineasArchivoResultados.get(i).getIdPartido(), partidos));
				i++;
				if (i>=this.lineasArchivoResultados.size()) break;
			} while (ronda==this.lineasArchivoResultados.get(i).getIdRonda());
			rondas.add(nuevaRonda);
		}
		return rondas;
	}
	
	public ArrayList<Fase> cargarFases(ArrayList<Ronda> rondas) {
		ArrayList<Fase> fases = new ArrayList<Fase>();
		Fase nuevaFase;
		Ronda ronda;
		int i=0;
		int fase;
		while (i<this.lineasArchivoResultados.size()) {
			fase = this.lineasArchivoResultados.get(i).getIdFase();
			nuevaFase = new Fase(fase,String.valueOf(fase));
			do {
				ronda=getRondaPorIdPartido(this.lineasArchivoResultados.get(i).getIdPartido(),rondas);
				nuevaFase.agregarRonda(ronda);
				do {
					i++;
					if (i>=this.lineasArchivoResultados.size()) break;
				} while (getRondaPorIdPartido(this.lineasArchivoResultados.get(i).getIdPartido(),rondas)==ronda
						  && fase==this.lineasArchivoResultados.get(i).getIdFase());
				if (i>=this.lineasArchivoResultados.size()) break;
			} while (fase==this.lineasArchivoResultados.get(i).getIdFase());
			fases.add(nuevaFase);
		}
		return fases;
	}

	private Ronda getRondaPorIdPartido(Integer idPartido, ArrayList<Ronda> rondas) {
		for (Ronda ronda : rondas) {
			for (Partido partido : ronda.getPartidos()) {
				if (partido.getIdPartido()==idPartido) {
					return ronda;
				}
			}
		}
		return null;
	}

}
