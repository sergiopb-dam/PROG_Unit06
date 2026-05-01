package ejercicio09;

import java.io.*;

public class ContadorFichero {

	static String archivo = "src/ejercicio09/carta.txt";

	public static void main(String[] args) {
		procesarFichero();
	}

	public static void procesarFichero() {
		int lineas = 0;
		int palabras = 0;
		int caracteres = 0;

		try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
			String linea = br.readLine();

			while (linea != null) {
				lineas++;
				caracteres += linea.length();

				if (!linea.isEmpty()) {
					String[] partes = linea.split(" ");
					palabras += partes.length;
				}

				linea = br.readLine();
			}

			mostrarResultados(lineas, palabras, caracteres);

		} catch (FileNotFoundException e) {
			System.out.println("No se ha encontrado el archivo carta.txt");
		} catch (IOException e) {
			System.out.println("Error al leer el archivo.");
		}
	}

	public static void mostrarResultados(int lineas, int palabras, int caracteres) {
		System.out.println("=== ESTADÍSTICAS DEL FICHERO ===");
		System.out.println("Líneas: " + lineas);
		System.out.println("Palabras: " + palabras);
		System.out.println("Caracteres: " + caracteres);
	}
}