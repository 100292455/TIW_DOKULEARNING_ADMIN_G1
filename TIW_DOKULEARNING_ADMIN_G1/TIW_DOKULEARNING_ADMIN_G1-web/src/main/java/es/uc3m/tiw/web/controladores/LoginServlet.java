package es.uc3m.tiw.web.controladores;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import es.uc3m.tiw.web.dominio.Usuario;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private static final String ENTRADA_JSP = "/Admin_Administrador.jsp";
    private static final String LOGIN_JSP = "/Admin_Login.jsp";
    
    private Usuario usuario;
    private ArrayList<Usuario> usuarios;
    
    public void init() throws ServletException {
    	usuario = new Usuario (2, "Miguel", "Solera", 1, "miguel@uc3m.es", "565543324", "VISA", 0, "1234");
    	usuarios = new ArrayList<Usuario>();
    	usuarios.add(usuario);
    }
    	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			// TODO Auto-generated method stub
			String salir = request.getParameter("accion");
				if (salir != null && !salir.equals("")){
					request.getSession().invalidate();
				}
			this.getServletContext().getRequestDispatcher(LOGIN_JSP).forward(request, response);
			
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String user = request.getParameter("usuariologin");
		String password = request.getParameter("passwordlogin");
		String pagina = "";
		String mensaje = "";
		pagina = LOGIN_JSP;
		
		HttpSession sesion = request.getSession();
		Usuario u = comprobarUsuario(user, password);
		
		if (u != null){
			pagina = ENTRADA_JSP;
			sesion.setAttribute("usuarios", usuarios);
			sesion.setAttribute("usuario", u);
			sesion.setAttribute("acceso", "ok");
		}
		
		else {
			mensaje = "Usuario o password incorrectos";
			request.setAttribute("mensaje", mensaje);
		}
		
		this.getServletContext().getRequestDispatcher(pagina).forward(request, response);
		
	}
	private Usuario comprobarUsuario(String user, String password) {
		// TODO Auto-generated method stub
		Usuario u = null;
		for (Usuario usuario : usuarios){
			if (user.equals(usuario.getEmail()) && password.equals(usuario.getClave())){
				u = usuario;
				break;
			}
		}
		return u;
	}

}
