package ejercicio15;

import java.time.LocalDate;
import java.time.Period;

public class Cliente {
	private String dni;
	private String nombre;
	private LocalDate fechaNacimiento;
	private double saldo;

	public Cliente(String dni, String nombre, LocalDate fechaNacimiento, double saldo) {
		this.dni = dni;
		this.nombre = nombre;
		this.fechaNacimiento = fechaNacimiento;
		this.saldo = saldo;
	}

	public String getDni() {
		return dni;
	}

	public String getNombre() {
		return nombre;
	}

	public LocalDate getFechaNacimiento() {
		return fechaNacimiento;
	}

	public double getSaldo() {
		return saldo;
	}

	public int getEdad() {
		return Period.between(this.fechaNacimiento, LocalDate.now()).getYears();
	}
}