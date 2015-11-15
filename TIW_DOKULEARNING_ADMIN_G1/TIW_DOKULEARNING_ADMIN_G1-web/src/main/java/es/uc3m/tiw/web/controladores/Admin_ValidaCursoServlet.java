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


@WebServlet("/ValidaCurso")
public class Admin_ValidaCursoServlet extends HttpServlet {
	private static final String VALIDACURSO_JSP = "/Admin_ValidarCursos.jsp";
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
	/* Cada vez que el ususario clique en el boton VALIDAR, validamos el curso sore el que ha clicado y devolvemos todos los cursos sin validar que aun queden en la BBDD */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String pagina = "";
		pagina = VALIDACURSO_JSP;
		
		HttpSession sesion = request.getSession();	
		ServletContext context = sesion.getServletContext();
		String idCursoStr = request.getParameter("IdCurso");
		int idCurso = Integer.parseInt(idCursoStr);
		Collection<Curso> cursos = (Collection<Curso>) sesion.getAttribute("cursosValidar");
		
		Curso c = curDao.recuperarCursoPorPK(idCurso);
		c.setTIPO_estado(2);
		try {
			c=curDao.modificarCurso(c);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		/* DEVOLVER LOS CURSOS CON TIPO_estado = 1  */
		Collection<Curso> cursosEstado1 = curDao.recuperarCursosPorDEstado(1);
		sesion.setAttribute("cursosValidar", cursosEstado1);
		
		this.getServletContext().getRequestDispatcher(pagina).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		/*String pagina = "";
		pagina = VALIDACURSO_JSP;
		
		HttpSession sesion = request.getSession();	
		ServletContext context = sesion.getServletContext();
		String idCursoStr = request.getParameter("IdCurso");
		int idCurso = Integer.parseInt(idCursoStr);
		ArrayList<Curso> cursos = (ArrayList<Curso>) context.getAttribute("cursos");
		context.removeAttribute("cursos");
		
		Curso curso = cursos.get(idCurso);
		/* UPDATE CURSOS WHERE ID_CURSO = idCurso , SET TIPO_estado = 2 */
		/*curso.setTIPO_estado(2);
		cursos.remove(curso);
		/* DEVOLVER LOS CURSOS CON TIPO_estado = 1  */

		/*context.setAttribute("cursos", cursos);
		
		this.getServletContext().getRequestDispatcher(pagina).forward(request, response);*/
		
	}

}