package ejercicio04;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class AlmacenarTexto {
	public static void main(String[] args) {
		
		try (Scanner sc = new Scanner(System.in);
			FileWriter out = new FileWriter("src/ejercicio04/TextoAlmacenado.txt", true)) {

			String texto = "";
			System.out.println("------------ BLOC --- DE --- NOTAS ------------");
			System.out.println("Introduce textos (escribe 'fin' para salir):");
			System.out.println("-----------------------------------------------");

			while (true) {
				texto = sc.nextLine();

				if (texto.equalsIgnoreCase("fin")) {
					break;
				}

				out.write(texto + "\n");
			}

			System.out.println("\nProceso terminado. Texto guardado.");

		} catch (IOException e) {
			System.out.println("\nError al manejar el archivo: " + e.getMessage());
		}
	}
}