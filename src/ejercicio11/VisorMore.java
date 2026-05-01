package ejercicio11;

import java.io.*;
import java.util.Scanner;

public class VisorMore {

	static Scanner sc = new Scanner(System.in);
	static String archivo = "";

	public static void main(String[] args) {
		pedirRuta();
		mostrarFichero();
	}

	public static void pedirRuta() {
		System.out.print("Introduce el nombre o la ruta del fichero: "); // Puedes usar la que he creado:
																			// src/ejercicio11/ficheroMore.txt
		archivo = sc.nextLine();
	}

	public static void mostrarFichero() {
		try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
			String linea = br.readLine();
			int contador = 0;

			while (linea != null) {
				System.out.println(linea);
				contador++;

				if (contador == 24) {
					System.out.print("--- Pulsa ENTER para ver más ---");
					sc.nextLine();
					contador = 0;
				}

				linea = br.readLine();
			}

		} catch (FileNotFoundException e) {
			System.out.println("No se ha encontrado el archivo.");
		} catch (IOException e) {
			System.out.println("Error al leer el archivo.");
		}
	}
}