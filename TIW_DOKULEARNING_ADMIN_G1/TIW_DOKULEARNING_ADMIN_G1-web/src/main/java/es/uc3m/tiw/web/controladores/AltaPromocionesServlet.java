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

@WebServlet("/AltaPromociones")
public class AltaPromocionesServlet extends HttpServlet {
	private static final String ENTRADA_JSP = "/GestionPromociones.jsp";
	private static final String GESTION_CURSOS_JSP = "/GestionPromociones.jsp";
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
		this.getServletContext().getRequestDispatcher(GESTION_CURSOS_JSP).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String nombrePromo = request.getParameter("nombrePromo");
		String precio1 = request.getParameter("precio");
		String tipo_promocion1 = request.getParameter("tipo_promocion");
		String fecha_fin = request.getParameter("fecha_fin");
		/*****/
		
		String mensaje ="";
		String pagina = "";
		pagina = GESTION_CURSOS_JSP;
		HttpSession sesion = request.getSession();	
		ServletContext context = sesion.getServletContext();
		Promocion p = new Promocion();
		String m = comprobarPromocion(nombrePromo, precio1, tipo_promocion1, fecha_fin);
		if (m .equals(null) || m .equals("")){
			int precio2 = Integer.parseInt(precio1);
			int tipo_promocion2 = Integer.parseInt(tipo_promocion1);
			p = crearPromocion(nombrePromo, precio2, tipo_promocion2, fecha_fin);
			pagina = ENTRADA_JSP;
			try {
				p=promDao.guardarPromocion(p);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Collection<Promocion> promociones = promDao.buscarTodosLosPromociones();
			sesion.setAttribute("promociones", promociones);
			sesion.setAttribute("promocion", p);
			
		}else{
			
			mensaje = m;
			request.setAttribute("mensaje", mensaje);
			sesion.setAttribute("promocion", p);
		}
			
			this.getServletContext().getRequestDispatcher(pagina).forward(request, response);
			
		
	}

	private Promocion crearPromocion(String nombrePromo, int precio, int tipo_promocion, String fecha_fin) {
		Promocion p = new Promocion();
		
		p.setNombrePromo(nombrePromo);
		p.setDescuento(precio);
		p.setTipo_promo(tipo_promocion);
		p.setFecha_fin(fecha_fin);
		return p;
	}



	private String comprobarPromocion(String nombrePromo, String precio, String tipo_promocion, String fecha_fin) {
		String m = "";
		
		if (nombrePromo.equals("") || precio.equals("") || tipo_promocion.equals("") || fecha_fin.equals("")) {
			m ="Fallo al crear nuevo cupon. ";
		}
		
		return m;
	}

}