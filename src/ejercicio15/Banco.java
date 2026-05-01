package ejercicio15;

import java.io.*;
import java.time.LocalDate;
import java.util.Scanner;
import java.util.TreeMap;

public class Banco {

	static TreeMap<String, Cliente> clientes = new TreeMap<>();
	static Scanner sc = new Scanner(System.in);
	static String archivo = "src/ejercicio15/clientes.txt";

	public static void main(String[] args) {
		cargarClientes();
		int opcion = 0;

		do {
			mostrarMenu();
			opcion = sc.nextInt();
			sc.nextLine();

			switch (opcion) {
			case 1:
				altaCliente();
				break;
			case 2:
				bajaCliente();
				break;
			case 3:
				listarClientes();
				break;
			case 4:
				guardarClientes();
				break;
			default:
				System.out.println("Opción incorrecta.");
				break;
			}
		} while (opcion != 4);

		sc.close();
	}

	public static void mostrarMenu() {
		System.out.println("\n=== GESTIÓN DE CLIENTES ===");
		System.out.println("1. Alta cliente.");
		System.out.println("2. Baja cliente.");
		System.out.println("3. Listar clientes.");
		System.out.println("4. Salir.");
		System.out.print("Elige una opción: ");
	}

	public static void cargarClientes() {
		try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
			String linea = br.readLine();
			while (linea != null) {
				String[] partes = linea.split(";");
				if (partes.length == 4) {
					String dni = partes[0];
					String nombre = partes[1];
					LocalDate fecha = LocalDate.parse(partes[2]);
					double saldo = Double.parseDouble(partes[3]);

					Cliente c = new Cliente(dni, nombre, fecha, saldo);
					clientes.put(dni, c);
				}
				linea = br.readLine();
			}
		} catch (IOException e) {
		}
	}

	public static void altaCliente() {
		System.out.print("Introduce el DNI: ");
		String dni = sc.nextLine();

		if (clientes.containsKey(dni)) {
			System.out.println("El cliente ya existe.");
			return;
		}

		System.out.print("Introduce el nombre completo: ");
		String nombre = sc.nextLine();

		System.out.print("Introduce la fecha de nacimiento (AAAA-MM-DD): ");
		LocalDate fecha = LocalDate.parse(sc.nextLine());

		System.out.print("Introduce el saldo: ");
		double saldo = sc.nextDouble();
		sc.nextLine();

		clientes.put(dni, new Cliente(dni, nombre, fecha, saldo));
		System.out.println("Cliente registrado.");
	}

	public static void bajaCliente() {
		System.out.print("Introduce el DNI a borrar: ");
		String dni = sc.nextLine();

		if (clientes.remove(dni) != null) {
			System.out.println("Cliente eliminado.");
		} else {
			System.out.println("No se encontró el DNI.");
		}
	}

	public static void listarClientes() {
		if (clientes.isEmpty()) {
			System.out.println("No hay clientes.");
			return;
		}

		double suma = 0, max = -1, min = 999999999;
		for (Cliente c : clientes.values()) {
			System.out.println(
					c.getDni() + " - " + c.getNombre() + " (" + c.getEdad() + " años) - Saldo: " + c.getSaldo());
			suma += c.getSaldo();
			if (c.getSaldo() > max)
				max = c.getSaldo();
			if (c.getSaldo() < min)
				min = c.getSaldo();
		}

		System.out.println("\nResumen: Max: " + max + " | Min: " + min + " | Promedio: " + (suma / clientes.size()));
	}

	public static void guardarClientes() {
		try (BufferedWriter bw = new BufferedWriter(new FileWriter(archivo))) {
			for (Cliente c : clientes.values()) {
				bw.write(c.getDni() + ";" + c.getNombre() + ";" + c.getFechaNacimiento() + ";" + c.getSaldo());
				bw.newLine();
			}
			System.out.println("Datos guardados.");
		} catch (IOException e) {
			System.out.println("Error al guardar.");
		}
	}
}