package ejercicio10;

import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;

public class LibroFirmas {

	static Scanner sc = new Scanner(System.in);
	static String archivo = "src/ejercicio10/firmas.txt";
	static ArrayList<String> firmas = new ArrayList<>();

	public static void main(String[] args) {
		cargarFirmas();
		int opcion = 0;

		do {
			mostrarMenu();
			opcion = sc.nextInt();
			sc.nextLine();

			switch (opcion) {
			case 1:
				mostrarFirmas();
				break;
			case 2:
				insertarFirma();
				break;
			case 3:
				System.out.println("Cerrando el libro de firmas...");
				break;
			default:
				System.out.println("Opción incorrecta.");
			}
		} while (opcion != 3);

		sc.close();
	}

	public static void mostrarMenu() {
		System.out.println("\n>>> LIBRO DE FIRMAS <<<");
		System.out.println("1. Mostrar firmas.");
		System.out.println("2. Insertar nueva firma.");
		System.out.println("3. Salir.");
		System.out.print("Elige una opción: ");
	}

	public static void cargarFirmas() {
		try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
			String linea = br.readLine();
			while (linea != null) {
				firmas.add(linea);
				linea = br.readLine();
			}
		} catch (IOException e) {
		}
	}

	public static void mostrarFirmas() {
		if (firmas.isEmpty()) {
			System.out.println("El libro de firmas está vacío.");
		} else {
			System.out.println("\n--- FIRMAS ---");
			for (String firma : firmas) {
				System.out.println("- " + firma);
			}
		}
	}

	public static void insertarFirma() {
		System.out.print("Introduce tu nombre: ");
		String nombre = sc.nextLine();

		if (firmas.contains(nombre)) {
			System.out.println("Ese nombre ya está en el libro de firmas.");
		} else {
			firmas.add(nombre);
			try (BufferedWriter bw = new BufferedWriter(new FileWriter(archivo, true))) {
				bw.write(nombre);
				bw.newLine();
				System.out.println("Firma guardada con éxito.");
			} catch (IOException e) {
				System.out.println("Error al guardar la firma.");
			}
		}
	}
}