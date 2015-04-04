package controlador;

import modelo.Cliente;
import modelo.Tarifa;

public class Controlador {

	private Tarifa tarifa;
	private Cliente cliente;

	private static Controlador controlador;
	
	public static Controlador getInstancia()
	{
		if(controlador==null)
		{
			controlador=new Controlador();
		}
		return controlador;
	}
	
	
	public static void main(String []args)
	{

	}

	
	
	private double cobrar(int cantidadMinutos)
	{
		double montoCobrar=0;        
		
		if(cliente.equals("porHora"))
		{
			montoCobrar=cobroPorHora(cantidadMinutos, tarifa);
		}
		else if(cliente.equals("porEstadia"))
		{
			montoCobrar=cobroPorEstadia(cantidadMinutos, tarifa);
		}
		return montoCobrar;
	}

	private static double cobroPorHora(double cantidadMinutos, Tarifa tarifa)
	{
		double montoCobrar=0;
		if(cantidadMinutos<60)
		{
			montoCobrar=tarifa.getCostoMinimo();
		}
		else
		{
			//Calculo Multiplo comun maximo -> puede que haya function.
			double countHs=(int)(cantidadMinutos/60);
			montoCobrar=montoCobrar+tarifa.getCostoHora()*countHs;
			cantidadMinutos=cantidadMinutos-countHs*60;
			//con el Math.ceil redondeo al techo.
			double countFracciones=(int)Math.ceil((cantidadMinutos)/tarifa.getTiempoFraccion());
			montoCobrar=montoCobrar+tarifa.getCostoFraccion()*countFracciones;
		}
		return montoCobrar;
	}

	private static double cobroPorEstadia(double cantidadMinutos, Tarifa tarifa)
	{
		double montoCobrar=0;

		if(cantidadMinutos<=tarifa.getTiempoMediaEstadia_min())
		{
			montoCobrar=tarifa.getCostoMediaEstadia();
		}
		else if(cantidadMinutos<=tarifa.getTiempoEstadia_min())
		{
			montoCobrar=tarifa.getCostoEstadia();
		}
		else
		{
			double cantidadHsEstadia=(int)(cantidadMinutos/tarifa.getTiempoEstadia_min());
			montoCobrar=montoCobrar+tarifa.getCostoEstadia()*cantidadHsEstadia;
			cantidadMinutos=cantidadMinutos-cantidadHsEstadia*tarifa.getTiempoEstadia_min();			
			double cantidadHsMediaEstadia=(int)(cantidadMinutos/tarifa.getTiempoMediaEstadia_min());
			if(cantidadHsMediaEstadia>=0)
			{
				montoCobrar=montoCobrar+tarifa.getCostoMediaEstadia()*cantidadHsMediaEstadia;
				cantidadMinutos=cantidadMinutos-cantidadHsMediaEstadia*tarifa.getTiempoMediaEstadia_min();
			}			
			//con el Math.ceil redondeo al techo.
			double countFracciones=(int)Math.ceil((cantidadMinutos)/tarifa.getTiempoFraccion());
			montoCobrar=montoCobrar+tarifa.getCostoFraccion()*countFracciones;
		}


		return montoCobrar;
	}
}

