package es.uc3m.tiw.web.controladores;

import java.io.IOException;
import java.util.Collection;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.transaction.UserTransaction;

import es.uc3m.tiw.model.Promocion;
import es.uc3m.tiw.model.Usuario;
import es.uc3m.tiw.model.dao.UsuarioDAO;
import es.uc3m.tiw.model.dao.UsuarioDAOImpl;
import es.uc3m.tiw.model.dao.PromocionDAO;
import es.uc3m.tiw.model.dao.PromocionDAOImpl;


/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private static final String ENTRADA_JSP = "/index.jsp";
    private static final String LOGIN_JSP = "/Admin_Login.jsp";
    @PersistenceContext(unitName = "demoTIW")
	private EntityManager em;
	@Resource
	private UserTransaction ut;
	private ServletConfig config2;
	private UsuarioDAO usDao;
	private PromocionDAO promDao;
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		config2 = config;
		usDao = new UsuarioDAOImpl(em, ut);
		promDao = new PromocionDAOImpl(em, ut);
		/*Usuario usuario = new Usuario ( "Miguel", "Solera", 1, "miguel@uc3m.es", "descripcion", "intereses", "565543324", "VISA", 0, "1234");
		try {
			usuario = usDao.guardarUsuario(usuario);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
	}
	
	public void destroy() {
		usDao = null;
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
			config2.getServletContext().getRequestDispatcher(LOGIN_JSP).forward(request, response);
			
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
		ServletContext context = sesion.getServletContext();
		Usuario u = usDao.buscarPorEmailYpassword(user, password);
		
		if (u != null){
			pagina = ENTRADA_JSP;
			Collection<Usuario> usuarios = usDao.buscarTodosLosUsuarios();
			sesion.setAttribute("usuarios", usuarios);
			sesion.setAttribute("usuario", u);
			sesion.setAttribute("acceso", "ok");
		}
		
		else {
			mensaje = "Usuario o password incorrectos";
			request.setAttribute("mensaje", mensaje);
		}
		
		Collection<Promocion> promociones = promDao.buscarTodosLosPromociones();
		sesion.setAttribute("promociones", promociones);
		config2.getServletContext().getRequestDispatcher(pagina).forward(request, response);
		
	}

}
