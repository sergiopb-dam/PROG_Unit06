package ejercicio01;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class CalculadoraNumReales {
	public static void main(String[] args) {
		try {
			double suma = 0;
			int contador = 0;
			try (Scanner sc = new Scanner(new FileReader("src/ejercicio01/NumerosReales.txt"))) {
				while (sc.hasNextDouble()) {
					double num = sc.nextDouble();
					suma += num;
					contador++;
				}
			}
			if (contador > 0) {
				double media = suma / contador;
				System.out.println("Suma total: " + suma);
				System.out.println("Media aritmética: " + media);
			} else {
				System.out.println("El archivo está vacío.");
			}
		} catch (FileNotFoundException e) {
			// TODO Bloque catch generado automáticamente
			System.out.println("Error: " + e.getMessage());
		}
	}
}
