package org.example;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import modelo.LectorArchivosCSV;
import modelo.LineaIncorrectaException;
import modelo.CalcularPuntos;
import modelo.Configuracion;
import modelo.Equipo;
import modelo.Fase;
import modelo.Partido;
import modelo.Pronostico;
import modelo.PronosticosBD;
import modelo.Persona;
import modelo.Ronda;
import modelo.Listados;

public class Main {
    public static void main(String[] args) {
    	if (args.length<3) {
    		mostrarFormato();
    		System.exit(1);
    	}
    	
    	Configuracion config = new Configuracion(args[2]);    	
//    	System.out.println(config);
    	
    	LectorArchivosCSV lectorArchivos = new LectorArchivosCSV(args[0],config.getSeparadorCSV());        
    	
//    	String rutaArchivoPrueba = "src\\main\\resources\\ResultadosDos.csv";
//    	lectorArchivos = new LectorArchivosCSV(rutaArchivoPrueba,config.getSeparadorCSV());
    	lectorArchivos = new LectorArchivosCSV(args[0],config.getSeparadorCSV());
    	try {
        	lectorArchivos.controlOKCantidadArgumentosArhivo(config.getCantidadArgumentosArchivoResultados());
        	lectorArchivos.parsearArchivoResultados();
        } catch (LineaIncorrectaException e) {
        	System.out.println(e.getMessage());
        	System.exit(2);
        }
        ArrayList<Equipo> equipos = lectorArchivos.cargarEquipos();
        ArrayList<Partido> partidos = lectorArchivos.cargarPartidos(equipos);
        ArrayList<Ronda> rondas = lectorArchivos.cargarRondas(partidos);
        ArrayList<Fase> fases = lectorArchivos.cargarFases(rondas);
        
//        String rutaArchivoPrueba2 = "src\\main\\resources\\PronosticosDos.csv";
//        String rutaArchivoPrueba2 = "src\\main\\resources\\PronosticosTres.csv";
//        lectorArchivos = new LectorArchivosCSV(rutaArchivoPrueba2,config.getSeparadorCSV());
        lectorArchivos = new LectorArchivosCSV(args[1],config.getSeparadorCSV());
        try {
        	lectorArchivos.controlOKCantidadArgumentosArhivo(config.getCantidadArgumentosArchivoPronosticos());
        	lectorArchivos.parsearArchivoPronosticos();
        } catch (LineaIncorrectaException e) {
        	System.out.println(e.getMessage());
        	System.exit(2);
        }
        PronosticosBD.setPronosticosBD(lectorArchivos,config.getBaseDeDatos());
        ArrayList<Pronostico> pronosticos = PronosticosBD.getPronosticosBD(partidos,equipos,config.getBaseDeDatos());
//        ArrayList<Pronostico> pronosticos = lectorArchivos.cargarPronosticos(partidos,equipos);

        listadosVarios(lectorArchivos,equipos,partidos,rondas,fases,pronosticos);
        
        ArrayList<Persona> personas = CalcularPuntos.CalcularPuntosPartidos(pronosticos,fases,config);
        
        System.out.println("Se juegan "+partidos.size()+" partidos distribuídos en "+rondas.size()+" rondas, y participan "+equipos.size()+" equipos.\n"
                +"Hay "+personas.size()+" personas que han realizado "+pronosticos.size()+" pronósticos.\n\n");
        
        listarPuntosPersonas(personas);
    }

	private static void listarPuntosPersonas(ArrayList<Persona> personas) {
		System.out.println("Los resultados son:\n");
		for (Persona persona : personas) {
        	System.out.println("Persona: "+persona.getNombre()+", Total de puntos obtenidos: "+(persona.getPuntosPartidos()+persona.getPuntosRondas()+persona.getPuntosFases())+
        			           "\n         Cantidad de pronosticos acertados: "+persona.getCantidadDeAciertos()+", Puntos obtenidos por pronósticos: "+persona.getPuntosPartidos()+
        			           "\n         Cantidad de rondas acertadas: "+persona.getCantidadRondasAcertadas()+", Puntos obtenidos por rondas: "+persona.getPuntosRondas()+
        			           "\n         Cantidad de fases acertadas: "+persona.getCantidadFasesAcertadas()+", Puntos obtenidos por fases: "+persona.getPuntosFases()+"\n");
        }		
	}

    private static void listadosVarios(LectorArchivosCSV lectorArchivos, ArrayList<Equipo> equipos,
			ArrayList<Partido> partidos, ArrayList<Ronda> rondas, ArrayList<Fase> fases,
			ArrayList<Pronostico> pronosticos) {
//      lectorArchivos.listarResultados();        
//      lectorArchivos.listarPronosticos();
      
//      Listados.listarEquipos(equipos);
//      Listados.listarPartidos(partidos);     
//      Listados.listarRondas(rondas); 
//      Listados.listarFases(fases);
//      Listados.listarPronosticos(pronosticos);
    }
    
	private static void mostrarFormato() {
    	System.out.println("ERROR! No se han especificado parámetros.\nDebe especificar en primer parámetro el archivo con datos de resultados, en segundo parámetro el archivo con datos de pronosticos y en tercer parámetro el archivo de configuracion.");
    }

}