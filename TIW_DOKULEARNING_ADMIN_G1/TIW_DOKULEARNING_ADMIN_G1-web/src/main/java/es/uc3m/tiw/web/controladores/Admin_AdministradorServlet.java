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

import es.uc3m.tiw.model.Curso;
import es.uc3m.tiw.model.Cupon;
import es.uc3m.tiw.model.Promocion;
import es.uc3m.tiw.model.Usuario;
import es.uc3m.tiw.model.dao.CursoDAO;
import es.uc3m.tiw.model.dao.CursoDAOImpl;
import es.uc3m.tiw.model.dao.CuponDAOImpl;
import es.uc3m.tiw.model.dao.CuponDAO;
import es.uc3m.tiw.model.dao.MatriculaDAO;
import es.uc3m.tiw.model.dao.MatriculaDAOImpl;
import es.uc3m.tiw.model.dao.PromocionDAOImpl;
import es.uc3m.tiw.model.dao.PromocionDAO;

@WebServlet("/Administracion")
/* Clase que funciona como controlador de la herramienta de administracion */
public class Admin_AdministradorServlet extends HttpServlet {
	private static final String ENTRADA_JSP = "/index.jsp";
	private static final String VALIDAR_CURSOS_JSP = "/Admin_ValidarCursos.jsp";
	private static final String ADMIN_DESTACADOS_JSP = "/Admin_CursosDestacados.jsp";
	private static final String ADMIN_PROMOCIONES_JSP = "/GestionPromociones.jsp";
	private static final long serialVersionUID = 1L;
	@PersistenceContext(unitName = "demoTIW")
	private EntityManager em;
	@Resource
	private UserTransaction ut;
	private ServletConfig config2;
	private PromocionDAO promDao;
	private CuponDAO cupDao;
	private CursoDAO curDao;
	@Override
	public void init(ServletConfig config) throws ServletException {
		config2 = config;
		cupDao = new CuponDAOImpl(em, ut);
		curDao = new CursoDAOImpl(em, ut);
		promDao = new PromocionDAOImpl(em, ut);

	}
	
	public void destroy() {
		cupDao = null;
		curDao = null;
		promDao = null;
	}
       


	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.getServletContext().getRequestDispatcher(VALIDAR_CURSOS_JSP).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String filtro = request.getParameter("filtro");
		String pagina = "";
		pagina = ENTRADA_JSP;
		
		/* En funcion de lo introducida en "filtro" redirigimos a una pagina jsp u otra */
		
		if(filtro.equals("IrMenu")){
			this.getServletContext().getRequestDispatcher(pagina).forward(request, response);
		}
		
		if(filtro.equals("AdministrarCursosPendientes")){
			pagina = VALIDAR_CURSOS_JSP;
			HttpSession sesion = request.getSession();	
			ServletContext context = sesion.getServletContext();
			/* Recuperar de DB -> CURSOS WHERE TIPO_estado = 0 */
			Collection<Curso> cursosSinValidar = curDao.recuperarCursosPorDEstado(0);
			sesion.setAttribute("cursosValidar", cursosSinValidar);
				
			this.getServletContext().getRequestDispatcher(pagina).forward(request, response);	
		}
		
		if(filtro.equals("AdministrarPromociones")){
			pagina = ADMIN_PROMOCIONES_JSP;
			HttpSession sesion = request.getSession();	
			ServletContext context = sesion.getServletContext();
			/* Recuperar de DB -> todos los CURSOS */
			Collection<Curso> cursos = curDao.buscarTodosLosCursos();
			sesion.setAttribute("cursos", cursos);
				
			this.getServletContext().getRequestDispatcher(pagina).forward(request, response);	
		}
		if(filtro.equals("AdministrarCursosDestacados")){
			pagina = ADMIN_DESTACADOS_JSP;
			HttpSession sesion = request.getSession();	
			ServletContext context = sesion.getServletContext();
			/* Recuperar de DB -> CURSOS WHERE TIPO_destacado = 0 */
			Collection<Curso> cursosDestacados = curDao.recuperarCursosPorDestacado(0);
			sesion.setAttribute("cursosDestacados", cursosDestacados);
				
			this.getServletContext().getRequestDispatcher(pagina).forward(request, response);	
		}
	}
	
}