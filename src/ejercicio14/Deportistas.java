package ejercicio14;

import java.io.*;

public class Deportistas {

	static String archivo = "src/ejercicio14/deportistas.txt";
	static int maxEdad = -1;
	static double maxPeso = -1.0;
	static double maxEstatura = -1.0;
	static String nombreMaxEdad = "";
	static String nombreMaxPeso = "";
	static String nombreMaxEstatura = "";

	public static void main(String[] args) {
		procesarArchivo();
		mostrarResultados();
	}

	public static void procesarArchivo() {
		try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
			String linea = br.readLine();
			linea = br.readLine();

			while (linea != null) {
				evaluarDeportista(linea);
				linea = br.readLine();
			}
		} catch (FileNotFoundException e) {
			System.out.println("No se ha encontrado el archivo deportistas.txt");
		} catch (IOException e) {
			System.out.println("Error al leer el archivo.");
		}
	}

	public static void evaluarDeportista(String linea) {
		if (linea.trim().isEmpty()) {
			return;
		}

		String[] partes = linea.trim().split("\\s+");
		int longitud = partes.length;

		if (longitud >= 4) {
			double estatura = Double.parseDouble(partes[longitud - 1].replace(",", "."));
			double peso = Double.parseDouble(partes[longitud - 2].replace(",", "."));
			int edad = Integer.parseInt(partes[longitud - 3]);

			String nombre = "";
			for (int i = 0; i < longitud - 3; i++) {
				nombre += partes[i] + " ";
			}
			nombre = nombre.trim();

			if (edad > maxEdad) {
				maxEdad = edad;
				nombreMaxEdad = nombre;
			}

			if (peso > maxPeso) {
				maxPeso = peso;
				nombreMaxPeso = nombre;
			}

			if (estatura > maxEstatura) {
				maxEstatura = estatura;
				nombreMaxEstatura = nombre;
			}
		}
	}

	public static void mostrarResultados() {
		if (maxEdad != -1) {
			System.out.println("Deportista de mayor edad: " + nombreMaxEdad);
			System.out.println("Deportista de mayor peso: " + nombreMaxPeso);
			System.out.println("Deportista de mayor estatura: " + nombreMaxEstatura);
		} else {
			System.out.println("No hay datos suficientes para mostrar.");
		}
	}
}