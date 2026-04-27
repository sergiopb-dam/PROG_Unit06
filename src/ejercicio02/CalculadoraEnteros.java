package ejercicio02;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class CalculadoraEnteros {
	public static void main(String[] args) {
		try {
			double suma = 0;
			int contador = 0;

			try (Scanner sc = new Scanner(new FileReader("Enteros.txt"))) {
				while (sc.hasNextInt()) {
					int num = sc.nextInt();
					suma += num;
					contador++;
				}
			}

			if (contador > 0) {
				double media = suma / contador;
				System.out.println("Suma total: " + (int) suma);
				System.out.println("Media aritmética: " + media);
			} else {
				System.out.println("El archivo está vacío.");
			}
		} catch (FileNotFoundException e) {
			System.out.println("Error: " + e.getMessage());
		}
	}
}