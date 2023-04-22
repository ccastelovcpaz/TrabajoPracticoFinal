package org.example;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import modelo.LectorArchivosCSV;
import modelo.LineaIncorrectaException;
import modelo.ArchivoResultados;
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
    	LectorArchivosCSV lectorArchivos = new LectorArchivosCSV(args[0],config.getSeparadorCSV());        
    	
    	lectorArchivos = new LectorArchivosCSV(args[0],config.getSeparadorCSV());
//    	lectorArchivos.setRutaArchivo("src\\main\\resources\\ResultadosDos.csv");
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
        
        lectorArchivos.setRutaArchivo(args[1]);
//        lectorArchivos.setRutaArchivo("src\\main\\resources\\PronosticosDos.csv");
//        lectorArchivos.setRutaArchivo("src\\main\\resources\\PronosticosTres.csv");
        try {
        	lectorArchivos.controlOKCantidadArgumentosArhivo(config.getCantidadArgumentosArchivoPronosticos());
        	lectorArchivos.parsearArchivoPronosticos();
        } catch (LineaIncorrectaException e) {
        	System.out.println(e.getMessage());
        	System.exit(2);
        }
        PronosticosBD.setPronosticosBD(lectorArchivos,config.getBaseDeDatos());
        ArrayList<Persona> personas = PronosticosBD.getPronosticosBD(partidos,equipos,config.getBaseDeDatos());
//        ArrayList<Pronostico> pronosticos = lectorArchivos.cargarPronosticos(partidos,equipos);
        
        CalcularPuntos.CalcularPuntosPartidos(personas,fases,config);
        System.out.println("Se juegan "+partidos.size()+" partidos distribuídos en "+fases.size()+" fases y "+rondas.size()+" rondas, y participan "+equipos.size()+" equipos.\n"
                +"Hay "+personas.size()+" personas que han realizado "+cantidadDePronosticos(personas)+" pronósticos.\n\n");
        Listados.listarPuntosPersonas(personas);
    }

    private static int cantidadDePronosticos(ArrayList<Persona> personas) {
    	int cantidadDePronosticos=0;
    	for (Persona persona : personas) {
    		cantidadDePronosticos+=persona.getPronosticos().size();
    	}
    	return cantidadDePronosticos;
    }
    
	private static void mostrarFormato() {
    	System.out.println("ERROR! No se han especificado parámetros.\nDebe especificar en primer parámetro el archivo con datos de resultados, en segundo parámetro el archivo con datos de pronosticos y en tercer parámetro el archivo de configuracion.");
    }

}