package es.uc3m.tiw.web.filtros;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Este filtro intercepta cualquier peticion que vaya a la pagina listado.jsp y comprueba que el usuario se haya autenticado con un token en sesion, en caso contrario le devuelve a la pagina de login.jsp
 * @author David Palomar
 */
//@WebFilter( urlPatterns = { "/index.jsp" })
public class LoginFilter implements Filter {



	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

		HttpSession sesion =  ((HttpServletRequest)request).getSession(); //accedemos a la sesion del servidor usando un casting al objeto HttpServletRequest ya que nos pasan solamente ServletRequest
		
		if (sesion.getAttribute("acceso")!=null && sesion.getAttribute("acceso").equals("ok")) { //buscamos el token de autenticacion
			
			chain.doFilter(request, response); 
		}else{
			request.getRequestDispatcher("/Admin_Login.jsp").forward(request, response);
		}

	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}

}
