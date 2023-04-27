package org.example;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

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
//      lectorArchivos.setRutaArchivo("src\\main\\resources\\PronosticosDos.csv");
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
        
		config.setInformacion("Se jugaron "+partidos.size()+" partidos distribuídos en "+fases.size()+" fases y "+rondas.size()+" rondas, y participaron "+equipos.size()+" equipos.\n"
                +"Hay "+personas.size()+" participantes que han realizado "+Listados.cantidadDePronosticos(personas)+" pronósticos.\n");
        CalcularPuntos.CalcularPuntosPartidos(personas,fases,config);
        
        menu(personas, fases, rondas, partidos, equipos, config);
    }
    
    private static void menu(ArrayList<Persona> personas, ArrayList<Fase> fases, ArrayList<Ronda> rondas, ArrayList<Partido> partidos, ArrayList<Equipo> equipos, Configuracion config) {
    	Scanner scn = new Scanner(System.in);
    	int opcion;
    	do {
    		mostrarMenu();
    		opcion=scn.nextInt();
    		if (opcion!=9) { limpiarConsola(); }
    		if (opcion==0) { Listados.listarInformacion(config); pausa(); }
    		if (opcion==1) { Listados.listarEquipos(equipos); pausa(); }
    		if (opcion==2) { Listados.listarPartidos(partidos); pausa(); }
    		if (opcion==3) { Listados.listarRondas(rondas); pausa(); }
    		if (opcion==4) { Listados.listarFases(fases); pausa(); }
    		if (opcion==5) { Listados.listarPersonas(personas); pausa(); }
    		if (opcion==6) { Listados.listarPronosticos(personas); pausa(); }
    		if (opcion==7) { Listados.listarPuntosPersonas(personas, fases, rondas, partidos, equipos); pausa(); }
    		if (opcion==8) { Listados.listarPodio(personas); pausa(); }
    	} while (opcion!=9);
    	scn.close();
    }
    
    private static void mostrarMenu() {
    	limpiarConsola();
    	System.out.println("MENU PRINCIPAL \"PRONOSTICO DEPORTIVO\"\n");
    	System.out.println("0) Información general.");
    	System.out.println("1) Listar equipos.");
    	System.out.println("2) Listar partidos.");
    	System.out.println("3) Listar rondas.");
    	System.out.println("4) Listar fases.");
    	System.out.println("5) Listar participantes.");
    	System.out.println("6) Listar pronosticos.");
    	System.out.println("7) Listar participantes y puntos obtenidos.");
    	System.out.println("8) Listar podio.");
    	System.out.println("9) Salir.");
    	System.out.print("\nIngrese su opcion: ");
    }
    
    private static void limpiarConsola() {
    	try {
    		new ProcessBuilder("cmd","/c","cls").inheritIO().start().waitFor();
    	} catch (IOException | InterruptedException e) {
    		System.out.println(e);
    	}
    }
    
    private static void pausa() {
    	System.out.print("\nPresione <enter> para volver al menú...");
    	new Scanner(System.in).nextLine();
    }
    
    private static void mostrarFormato() {
    	System.out.println("ERROR! No se han especificado parámetros.\nDebe especificar en primer parámetro el archivo con datos de resultados, en segundo parámetro el archivo con datos de pronosticos y en tercer parámetro el archivo de configuracion.");
    }

}