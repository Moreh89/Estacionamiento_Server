package modelo;

import java.sql.Time;

public class Ticket {
	String codigo;
	String montoCobrado;
	
	//Time puede cambiarse si no es compatible con hibernate
	Time horarioIngreso;
	Time horarioEgreso;
	
	Vehiculo vehiculo;
	Cliente cliente;
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public String getMontoCobrado() {
		return montoCobrado;
	}
	public void setMontoCobrado(String montoCobrado) {
		this.montoCobrado = montoCobrado;
	}
	public Time getHorarioIngreso() {
		return horarioIngreso;
	}
	public void setHorarioIngreso(Time horarioIngreso) {
		this.horarioIngreso = horarioIngreso;
	}
	public Time getHorarioEgreso() {
		return horarioEgreso;
	}
	public void setHorarioEgreso(Time horarioEgreso) {
		this.horarioEgreso = horarioEgreso;
	}
	public Vehiculo getVehiculo() {
		return vehiculo;
	}
	public void setVehiculo(Vehiculo vehiculo) {
		this.vehiculo = vehiculo;
	}
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
	
}
