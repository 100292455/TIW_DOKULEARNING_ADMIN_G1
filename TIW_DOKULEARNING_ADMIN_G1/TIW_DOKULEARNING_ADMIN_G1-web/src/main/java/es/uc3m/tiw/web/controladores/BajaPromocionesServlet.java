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
import es.uc3m.tiw.model.Promocion;
import es.uc3m.tiw.model.dao.CursoDAO;
import es.uc3m.tiw.model.dao.CursoDAOImpl;
import es.uc3m.tiw.model.dao.PromocionDAO;
import es.uc3m.tiw.model.dao.PromocionDAOImpl;


@WebServlet("/BajaPromociones")
public class BajaPromocionesServlet extends HttpServlet {
	private static final String ENTRADA_JSP = "/GestionPromociones.jsp";
	private static final long serialVersionUID = 1L;
	@PersistenceContext(unitName = "demoTIW")
	private EntityManager em;
	@Resource
	private UserTransaction ut;
	private ServletConfig config2;
	private CursoDAO curDao;
	private PromocionDAO promDao;
	ServletContext context;
	@Override
	public void init(ServletConfig config) throws ServletException {
		config2 = config;
		curDao = new CursoDAOImpl(em, ut);
		promDao = new PromocionDAOImpl(em, ut);
	}

	public void destroy() {
		curDao = null;
		promDao = null;
	}



	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String idPromocionStr = request.getParameter("IdPromocion");

		HttpSession sesion = request.getSession();

		int idPromocion = Integer.parseInt(idPromocionStr);



		Promocion promocionBorrar=promDao.recuperarPromocionPorPK(idPromocion);
		try {
			promDao.borrarPromocion(promocionBorrar);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Collection<Curso> cursos = curDao.buscarTodosLosCursos();
		
		for (Curso curso : cursos) {
			curso.setFechaFinDescuento("");
			curso.setPrecio_final(curso.getPrecio_inicial());
			try {
				curDao.modificarCurso(curso);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
		Collection<Promocion> promociones = promDao.buscarTodosLosPromociones();
		sesion.setAttribute("promociones", promociones);

		config2.getServletContext().getRequestDispatcher(ENTRADA_JSP).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		config2.getServletContext().getRequestDispatcher(ENTRADA_JSP).forward(request, response);	

	}
}
