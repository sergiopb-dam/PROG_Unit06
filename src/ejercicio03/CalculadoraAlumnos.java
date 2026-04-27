package ejercicio03;

import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.Locale;

public class CalculadoraAlumnos {
	public static void main(String[] args) {
		double sumaEdad = 0;
		double sumaAltura = 0;
		int contador = 0;

		try (Scanner sc = new Scanner(new FileReader("Alumnos.txt"))) {
			sc.useLocale(Locale.US);

			while (sc.hasNext()) {
				String nombre = sc.next();
				int edad = Integer.parseInt(sc.next());
				double altura = Double.parseDouble(sc.next());

				System.out.println("Alumno: " + nombre);

				sumaEdad += edad;
				sumaAltura += altura;
				contador++;
			}

			if (contador > 0) {
				double mediaEdad = sumaEdad / contador;
				double mediaAltura = sumaAltura / contador;
				System.out.println("--------------------");
				System.out.println("Media Edad: " + mediaEdad);
				System.out.println("Media Altura: " + mediaAltura);
			} else {
				System.out.println("El archivo está vacío.");
			}
		} catch (IOException e) {
			System.out.println("Error: " + e.getMessage());
		}
	}
}