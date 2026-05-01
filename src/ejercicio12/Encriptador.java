package ejercicio12;

import java.io.*;
import java.util.Scanner;

public class Encriptador {

	static Scanner sc = new Scanner(System.in);
	static String archivoCodec = "src/ejercicio12/codec.txt";
	static String archivoOriginal = "";
	static String archivoCifrado = "";
	static String alfabetoNormal = "";
	static String alfabetoCodificado = "";

	public static void main(String[] args) {
		if (cargarCodec()) {
			pedirNombresArchivos();
			codificarFichero();
		}
	}

	public static boolean cargarCodec() {
		try (BufferedReader br = new BufferedReader(new FileReader(archivoCodec))) {
			String linea1 = br.readLine();
			String linea2 = br.readLine();

			if (linea1 != null && linea2 != null) {
				alfabetoNormal = linea1.replace("Alfabeto:", "").replace(" ", "").toLowerCase();
				alfabetoCodificado = linea2.replace("Cifrado:", "").replace(" ", "").toLowerCase();
				return true;
			}
			System.out.println("El formato de codec.txt no es correcto.");
			return false;

		} catch (FileNotFoundException e) {
			System.out.println("No se ha encontrado codec.txt");
			return false;
		} catch (IOException e) {
			System.out.println("Error al leer codec.txt");
			return false;
		}
	}

	public static void pedirNombresArchivos() {
		System.out.print("Introduce el archivo a cifrar: "); // src/ejercicio12/mensaje.txt
		archivoOriginal = sc.nextLine();
		System.out.print("Introduce el nombre del archivo destino: "); // src/ejercicio12/secreto.txt
		archivoCifrado = sc.nextLine();
	}

	public static void codificarFichero() {
		try (BufferedReader br = new BufferedReader(new FileReader(archivoOriginal));
				BufferedWriter bw = new BufferedWriter(new FileWriter(archivoCifrado))) {

			String linea = br.readLine();

			while (linea != null) {
				bw.write(encriptarLinea(linea));
				bw.newLine();
				linea = br.readLine();
			}

			System.out.println("Archivo cifrado con éxito.");

		} catch (FileNotFoundException e) {
			System.out.println("No se ha encontrado el archivo original.");
		} catch (IOException e) {
			System.out.println("Error de lectura/escritura en los archivos.");
		}
	}

	public static String encriptarLinea(String linea) {
		String resultado = "";
		String lineaMinusculas = linea.toLowerCase();

		for (int i = 0; i < lineaMinusculas.length(); i++) {
			char caracterActual = lineaMinusculas.charAt(i);
			int posicion = alfabetoNormal.indexOf(caracterActual);

			if (posicion != -1) {
				resultado += alfabetoCodificado.charAt(posicion);
			} else {
				resultado += linea.charAt(i);
			}
		}

		return resultado;
	}
}