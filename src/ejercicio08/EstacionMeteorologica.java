package ejercicio08;

import java.io.*;
import java.util.Scanner;
import java.time.LocalDate;

public class EstacionMeteorologica {

	static Scanner sc = new Scanner(System.in);
	static String archivo = "src/ejercicio08/Temperaturas.txt";

	public static void main(String[] args) {
		int opcion = 0;

		do {
			mostrarMenu();
			opcion = sc.nextInt();
			sc.nextLine();

			switch (opcion) {
			case 1:
				registrarTemperatura();
				break;
			case 2:
				mostrarHistorial();
				break;
			case 3:
				System.out.println("Apagando la estación...");
				break;
			default:
				System.out.println("Opción incorrecta. Introduce 1, 2 o 3.");
			}
		} while (opcion != 3);

		sc.close();
	}

	public static void mostrarMenu() {
		System.out.println("\n--- ESTACIÓN METEOROLÓGICA ---");
		System.out.println("1. Registra nueva temperatura.");
		System.out.println("2. Mostrar historial de registros.");
		System.out.println("3. Salir.");
		System.out.print("Elige una opción: ");
	}

	public static void registrarTemperatura() {
		System.out.print("Introduce la temperatura máxima de hoy: ");
		int max = sc.nextInt();
		System.out.print("Introduce la temperatura mínima de hoy: ");
		int min = sc.nextInt();

		String fecha = LocalDate.now().toString();

		try (BufferedWriter bw = new BufferedWriter(new FileWriter(archivo, true))) {
			bw.write(fecha + "," + max + "," + min);
			bw.newLine();
			System.out.println("Registro guardado con éxito.");
		} catch (IOException e) {
			System.out.println("Error al guardar en el archivo.");
		}
	}

	public static void mostrarHistorial() {
		System.out.println("\n--- HISTORIAL DE REGISTROS ---");
		System.out.println("Fecha,Temperatura máxima,Temperatura mínima");

		int maxAbsoluta = -999;
		int minAbsoluta = 999;
		boolean hayDatos = false;

		try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
			String linea = br.readLine();

			while (linea != null) {
				System.out.println(linea);

				String[] partes = linea.split(",");
				if (partes.length == 3) {
					int tempMaxLinea = Integer.parseInt(partes[1]);
					int tempMinLinea = Integer.parseInt(partes[2]);

					if (tempMaxLinea > maxAbsoluta) {
						maxAbsoluta = tempMaxLinea;
					}

					if (tempMinLinea < minAbsoluta) {
						minAbsoluta = tempMinLinea;
					}
					hayDatos = true;
				}
				linea = br.readLine();
			}

			if (hayDatos) {
				System.out.println("\n>>> RESUMEN HISTÓRICO <<<");
				System.out.println("Máxima histórica registrada: " + maxAbsoluta);
				System.out.println("Mínima histórica registrada: " + minAbsoluta);
			} else {
				System.out.println("El archivo está vacío, no hay registros.");
			}

		} catch (FileNotFoundException e) {
			System.out.println("Aún no hay ningún archivo creado. Registra algo primero!");
		} catch (IOException e) {
			System.out.println("Error al leer el archivo.");
		}
	}
}
