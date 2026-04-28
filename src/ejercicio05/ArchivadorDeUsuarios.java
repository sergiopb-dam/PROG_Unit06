package ejercicio05;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class ArchivadorDeUsuarios {
	public static void main(String[] args) {
		try (Scanner sc = new Scanner(System.in)) {
			System.out.print("Introduce tu nombre: ");
			String nombre = sc.nextLine();

			System.out.print("Introduce tu edad: ");
			int edad = Integer.parseInt(sc.nextLine());

			try (FileWriter out = new FileWriter("src/ejercicio05/datos.txt", true)) {
				out.write(nombre + " | Edad: " + edad + "\n");
				System.out.println("Usuario creado con éxito.");
			}
		} catch (IOException | NumberFormatException e) {
			System.out.println("Error: " + e.getMessage());
		}
	}
}