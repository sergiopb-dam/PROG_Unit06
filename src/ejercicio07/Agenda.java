package ejercicio07;

import java.io.*;
import java.util.*;

public class Agenda {

	static TreeMap<String, String> contactos = new TreeMap<>();
	static String archivo = "src/ejercicio07/Agenda.txt";
	static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {
		cargarAgenda();
		int opcion = 0;

		do {
			mostrarMenu();
			opcion = sc.nextInt();
			sc.nextLine();

			switch (opcion) {
			case 1:
				nuevoContacto();
				break;
			case 2:
				buscarContacto();
				break;
			case 3:
				mostrarTodos();
				break;
			case 4:
				guardarAgenda();
				break;
			default:
				System.out.println("Opción incorrecta.");
			}
		} while (opcion != 4);

		sc.close();
	}

	public static void mostrarMenu() {
		System.out.println("\n--- MI AGENDA ---");
		System.out.println("1. Nuevo contacto.");
		System.out.println("2. Buscar por nombre.");
		System.out.println("3. Mostrar todos.");
		System.out.println("4. Salir.");
		System.out.print("Elige una opción: ");
	}

	public static void cargarAgenda() {
		try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
			String linea = br.readLine();
			while (linea != null && contactos.size() < 20) {
				String[] partes = linea.split(":");
				contactos.put(partes[0], partes[1]);
				linea = br.readLine();
			}
		} catch (IOException e) {
		}
	}

	public static void nuevoContacto() {
		if (contactos.size() >= 20) {
			System.out.println("La agenda está llena, no caben más de 20.");
		} else {
			System.out.print("Introduce el nombre: ");
			String nombre = sc.nextLine();

			if (contactos.containsKey(nombre)) {
				System.out.println("Ese nombre ya existe en la agenda.");
			} else {
				System.out.print("Introduce el teléfono: ");
				String telefono = sc.nextLine();
				contactos.put(nombre, telefono);
				System.out.println("Contacto guardado.");
			}
		}
	}

	public static void buscarContacto() {
		System.out.print("Introduce el nombre a buscar: ");
		String nombreBuscado = sc.nextLine();

		if (contactos.containsKey(nombreBuscado)) {
			System.out.println("Su teléfono es: " + contactos.get(nombreBuscado));
		} else {
			System.out.println("No hay nadie con ese nombre.");
		}
	}

	public static void mostrarTodos() {
		if (contactos.isEmpty()) {
			System.out.println("La agenda está vacía.");
		} else {
			System.out.println("\n--- LISTA DE CONTACTOS ---");
			for (String n : contactos.keySet()) {
				System.out.println(n + " - " + contactos.get(n));
			}
		}
	}

	public static void guardarAgenda() {
		try (BufferedWriter bw = new BufferedWriter(new FileWriter(archivo))) {
			for (String n : contactos.keySet()) {
				bw.write(n + ":" + contactos.get(n));
				bw.newLine();
			}
			System.out.println("Datos guardados en el archivo.");
			System.out.println("Hasta la próxima!");
		} catch (IOException e) {
			System.out.println("Error al guardar: " + e.getMessage());
		}
	}
}