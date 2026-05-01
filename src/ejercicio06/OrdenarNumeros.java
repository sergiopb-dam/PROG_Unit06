package ejercicio06;

import java.io.*;
import java.util.*;

public class OrdenarNumeros {
	public static void main(String[] args) {
		List<Integer> numeros = new ArrayList<>();

		try (Scanner sc = new Scanner(new FileReader("src/ejercicio06/Entrada.txt"))) {
			while (sc.hasNextInt()) {
				numeros.add(sc.nextInt());
			}
		} catch (IOException e) {
			System.out.println("Error al leer: " + e.getMessage());
		}

		Collections.sort(numeros);

		try (BufferedWriter out = new BufferedWriter(new FileWriter("src/ejercicio06/Salida.txt"))) {
			for (Integer n : numeros) {
				out.write(String.valueOf(n));
				out.newLine();
			}
		} catch (IOException e) {
			System.out.println("Error al escribir: " + e.getMessage());
		}
	}
}