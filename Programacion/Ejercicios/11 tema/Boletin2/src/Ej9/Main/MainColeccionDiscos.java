package Ej9.Main;

import Ej9.Clases.Disco;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class MainColeccionDiscos {
	
	public static void mostrarMenu(){
		System.out.println("\n\nCOLECCIÓN DE DISCOS");
		System.out.println("===================");
		System.out.println("1. Listado");
		System.out.println("2. Nuevo disco");
		System.out.println("3. Modificar");
		System.out.println("4. Borrar");
		System.out.println("5. Salir");
		System.out.print("Introduzca una opción: ");
	}
	
	public static void mostrarSubMenu(){
		System.out.println("\nListado DE DISCOS");
		System.out.println("===================");
		System.out.println("1. Listado completo");
		System.out.println("2. Listado por autor");
		System.out.println("3. Listado por genero");
		System.out.println("4. Listado por rango de duracion");
		System.out.println("5. Volver");
		System.out.print("Introduzca una opción: ");
	}
	
	public static void main(String[] args){
		
		//Declaración de variables
		int posicionComa = 0;

		int opcionMenu = 0;
		int opcionSubMenu = 0;
		
		String autorBusqueda = "";
		String generoBusqueda = "";
		int minutosMinimos = 0;
		int minutosMaximos = 0;
		
		String codigoABuscar = "";
		boolean encontrado = false;
		
		int codigoInt = 1;
		String codigo = "";
		String autor = "";
		String titulo = "";
		String genero = "";
		int duracion = 0;
		
		//Inicializaciones
		Scanner tecladoN = new Scanner(System.in);
		Scanner tecladoS = new Scanner(System.in);
		
		ArrayList<Disco> arrayDiscos = new ArrayList<Disco>();
		
		//Inicio
		//Cargar los datos de los discos
		try{
			BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\Familia\\Desktop\\GitHub\\DAW\\Programacion\\Ejercicios\\11 tema\\Boletin2\\src\\Ej9\\Main\\discos.txt"));

			String linea = br.readLine();

			while(linea != null){

				if(arrayDiscos.size() == 5){
					System.out.println("Solo se han introducido los 5 primeros discos de la lista");
					linea = null;
				}
				else{
					posicionComa = linea.indexOf(',');
					codigo = linea.substring(0,posicionComa);
					System.out.println(codigoInt);
					linea = linea.substring(posicionComa+1);

					posicionComa = linea.indexOf(',');
					autor = linea.substring(0,posicionComa);
					System.out.println(autor);
					linea = linea.substring(posicionComa+1);

					posicionComa = linea.indexOf(',');
					titulo = linea.substring(0,posicionComa);
					System.out.println(titulo);
					linea = linea.substring(posicionComa+1);

					posicionComa = linea.indexOf(',');
					genero = linea.substring(0,posicionComa);
					System.out.println(genero);
					linea = linea.substring(posicionComa+1);

					duracion = Integer.parseInt(linea.substring(0));
					System.out.println(duracion);

					arrayDiscos.add(new Disco(codigo, autor, titulo, genero, duracion));

					//codigoInt++;
				}

				linea = br.readLine();
			}

			br.close();

		}
		catch(FileNotFoundException e){
			System.out.println(e.getMessage());
		}
		catch(IOException e){
			System.out.println(e.getMessage());
		}

		//Hacer
		do {
			//mostrarMenu, leer y validar opcion
			do {
				mostrarMenu();
				opcionMenu = tecladoN.nextInt();
			}
			while (opcionMenu < 1 || opcionMenu > 5);
			
			System.out.println();
			
			//Segun la opcion del menu
			switch (opcionMenu) {
				case 1:
					do{
						mostrarSubMenu();
						opcionSubMenu = tecladoN.nextInt();
						
						if (arrayDiscos.isEmpty() && opcionSubMenu!=5) {
							System.out.println("\nLa lista está vacía");
						}
						else {
							switch(opcionSubMenu){
								case 1:
									for (Disco disco : arrayDiscos) {
										System.out.println(disco.toString());
									}
									break;
								
								case 2:
									System.out.print("Introduzca el nombre del autor: ");
									autorBusqueda = tecladoS.nextLine();
									
									for (Disco disco : arrayDiscos) {
										if(disco.getAutor().equals(autorBusqueda)){
											System.out.println(disco.toString());
										}
									}
									break;
								
								case 3:
									System.out.print("Introduzca el genero: ");
									generoBusqueda = tecladoS.nextLine();
									
									for (Disco disco : arrayDiscos) {
										if(disco.getGenero().equals(generoBusqueda)){
											System.out.println(disco.toString());
										}
									}
									break;
									
								case 4:
									do{
										System.out.print("Introduzca los minutos minimos: ");
										minutosMinimos = tecladoN.nextInt();
									}
									while(minutosMinimos<=0);
									
									do{
										System.out.print("Introduzca los minutos maximos: ");
										minutosMaximos = tecladoN.nextInt();
									}
									while(minutosMaximos<=0);
									
									for (Disco disco : arrayDiscos) {
										if(disco.getDuracion()>=minutosMinimos && disco.getDuracion()<=minutosMaximos){
											System.out.println(disco.toString());
										}
									}
									break;
							}
						}
					}
					while(opcionSubMenu!=5);
				break;
				
				case 2:
					System.out.println("      Nuevo disco      ");
					System.out.println("=======================");
					
					if(arrayDiscos.size() == 5){
						System.out.println("No se permitirá introducir los datos de ningún disco hasta que no se borre alguno de la lista.");
					}
					else{
						codigo = Integer.toString(codigoInt);
						
						System.out.print("Introduzca el autor: ");
						autor = tecladoS.nextLine();
						
						System.out.print("Introduzca el titulo: ");
						titulo = tecladoS.nextLine();
						
						System.out.print("Introduzca el genero: ");
						genero = tecladoS.nextLine();
						
						System.out.print("Introduzca la duracion (en minutos): ");
						duracion = tecladoN.nextInt();
						
						arrayDiscos.add(new Disco(codigo, autor, titulo, genero, duracion));
						
						codigoInt++;
					}
					break;
				
				case 3:
					System.out.println("      Modificar disco      ");
					System.out.println("===========================");
					
					System.out.print("Introduzca el codigo del disco que quiere modificar: ");
					codigoABuscar = tecladoS.nextLine();
					
					for (int i = 0; i <= arrayDiscos.size() - 1 && encontrado == false; i++) {
						if (arrayDiscos.get(i).getCodigo().equals(codigoABuscar)) {
							encontrado = true;
							
							System.out.println("Autor: " + arrayDiscos.get(i).getAutor());
							System.out.print("Introduzca el nuevo autor: ");
							autor = tecladoS.nextLine();
							
							System.out.println();
							
							System.out.println("Titulo: " + arrayDiscos.get(i).getTitulo());
							System.out.print("Introduzca el nuevo titulo: ");
							titulo = tecladoS.nextLine();
							
							System.out.println();
							
							System.out.println("Genero: " + arrayDiscos.get(i).getGenero());
							System.out.print("Introduzca el nuevo genero: ");
							genero = tecladoS.nextLine();
							
							System.out.println();
							
							System.out.println("Duracion: " + arrayDiscos.get(i).getDuracion());
							System.out.print("Introduzca la nueva duracion (en minutos): ");
							duracion = tecladoN.nextInt();
							
							arrayDiscos.set(i, new Disco(codigo, autor, titulo, genero, duracion));
						}
					}
					
					break;
				
				case 4:
					System.out.println("      Borrar disco      ");
					System.out.println("========================");
					
					do{
						System.out.print("Introduzca el codigo del disco que quiere eliminar: ");
						codigoABuscar = tecladoS.nextLine();
						
						for (int i = 0; i <= arrayDiscos.size() - 1 && encontrado == false; i++) {
							if (arrayDiscos.get(i).getCodigo().equals(codigoABuscar)) {
								encontrado = true;
								arrayDiscos.remove(i);
								System.out.println("\nSe ha borrado el elemento seleccionado");
							}
						}
						
						if(encontrado == false){
							System.out.println("\nNo se ha encontrado el disco con el codigo escrito.\n");
						}
						
					}
					while(encontrado==false);
					
					encontrado = false;
					break;
			}
			//Fin_Segun
		}
		while(opcionMenu != 5);	//Mientras la opcion no sea salir

		try{
			BufferedWriter bw = new BufferedWriter(new FileWriter("C:\\Users\\Familia\\Desktop\\GitHub\\DAW\\Programacion\\Ejercicios\\11 tema\\Boletin2\\src\\Ej9\\Main\\discos.txt"));

			for(Disco dato : arrayDiscos){
				//System.out.println(dato.getCodigo()+","+dato.getAutor()+","+dato.getTitulo()+","+dato.getGenero()+","+dato.getDuracion());
				bw.write(dato.getCodigo()+","+dato.getAutor()+","+dato.getTitulo()+","+dato.getGenero()+","+dato.getDuracion());
				bw.newLine();
			}

			bw.close();

		}
		catch(IOException e){
			System.out.println(e.getMessage());
		}

		System.out.println("Ashta luego!");
		
		//Fin
		
	}
}
