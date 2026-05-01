package ejercicio13;

import java.io.*;
import java.util.Scanner;

public class ComparadorArchivos {

	static Scanner sc = new Scanner(System.in);
	static String archivo1 = "";
	static String archivo2 = "";

	public static void main(String[] args) {
		pedirArchivos();
		compararArchivos();
	}

	public static void pedirArchivos() {
		System.out.print("Introduce la ruta del primer archivo: "); // src/ejercicio13/
		archivo1 = sc.nextLine();
		System.out.print("Introduce la ruta del segundo archivo: "); // src/ejercicio13/
		archivo2 = sc.nextLine();
	}

	public static void compararArchivos() {
		try (BufferedReader br1 = new BufferedReader(new FileReader(archivo1));
				BufferedReader br2 = new BufferedReader(new FileReader(archivo2))) {

			String linea1 = br1.readLine();
			String linea2 = br2.readLine();
			int numeroLinea = 1;
			boolean sonIguales = true;

			while (linea1 != null || linea2 != null) {
				if (linea1 == null || linea2 == null) {
					System.out.println("Los archivos son distintos.");
					System.out.println("Diferencia encontrada en la línea " + numeroLinea + ", carácter 1.");
					sonIguales = false;
					break;
				}

				if (!linea1.equals(linea2)) {
					System.out.println("Los archivos son distintos.");
					int caracterDiferente = buscarCaracterDiferente(linea1, linea2);
					System.out.println("Diferencia encontrada en la línea " + numeroLinea + ", carácter "
							+ caracterDiferente + ".");
					sonIguales = false;
					break;
				}

				linea1 = br1.readLine();
				linea2 = br2.readLine();
				numeroLinea++;
			}

			if (sonIguales) {
				System.out.println("Los archivos son exactamente iguales.");
			}

		} catch (FileNotFoundException e) {
			System.out.println("Error: No se ha encontrado uno de los archivos.");
		} catch (IOException e) {
			System.out.println("Error al leer los archivos.");
		}
	}

	public static int buscarCaracterDiferente(String l1, String l2) {
		int longitudMinima = Math.min(l1.length(), l2.length());

		for (int i = 0; i < longitudMinima; i++) {
			if (l1.charAt(i) != l2.charAt(i)) {
				return i + 1;
			}
		}

		return longitudMinima + 1;
	}
}