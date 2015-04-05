package controlador;

import java.sql.Connection;

import modelo.Cliente;
import modelo.Tarifa;
import modelo.Ticket;
import modelo.Usuario;
import persistencia.PoolConnection;

public class Controlador {

	private Tarifa tarifa;
	private Cliente cliente;
	private Ticket ticket;
	private Usuario usuarioActual;

	private static Controlador instancia;

	public static void main(String []args)
	{

	}

	public static Controlador getInstancia()
	{
		if(instancia==null)
		{
			instancia=new Controlador();
		}
		return instancia;
	}

	public boolean validarContrasenaActual(int dni , String psw){

		Usuario u = buscarUsuario(dni) ;

		if (u!=null){
			if(psw.equals(u.getPassword())){
				return true;
			}else
				return false;
		}else
			return false;
	}

	public boolean validarLogin(String userName, String password){

		String pswEnc = Encriptacion.Encriptar(password);

		Usuario u = this.buscarUsuario(userName, pswEnc) ;

		if (u!=null){

			if(pswEnc.equals(u.getPassword())){
				usuarioActual = u.getView();
				return true;
			}
			else
				return false;
		}
		return false;
	}




	public Usuario buscarUsuario(String userName, String password){

		Usuario u = null ;

		if (!usuarios.isEmpty()){
			for (Usuario usuarioTemp : this.usuarios) {
				if (usuarioTemp.getUserName().equals(userName)){
					u = usuarioTemp;
					break;
				}
			}
		}
		if (u == null){

			u = new Usuario().buscarUsuario(userName);
			this.usuarios.add(u);

		}

		if (u!=null){
			return u;
		}else
			return null;
	}


	public Usuario buscarUsuario(int dni){

		Usuario u = null ;

		if (!usuarios.isEmpty()){
			for (Usuario usuarioTemp : this.usuarios) {
				if (usuarioTemp.getDni() == dni)
				{
					u = usuarioTemp;
					break;
				}
			}
		}

		if (u == null){
			u = new Usuario().buscarUsuario(dni);
			this.usuarios.add(u);
		}

		return u;
	}

	public Usuario getUsuarioActual() {
		return usuarioActual;
	}


	public boolean probarConexion(){

		Connection con = PoolConnection.getPoolConnection().getConnection();
		if (con == null){
			return false;
		}else
			return true;
	}


}

