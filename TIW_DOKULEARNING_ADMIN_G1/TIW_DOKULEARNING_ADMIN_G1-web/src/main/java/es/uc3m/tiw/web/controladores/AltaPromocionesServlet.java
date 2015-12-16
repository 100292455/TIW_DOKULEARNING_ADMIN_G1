package es.uc3m.tiw.web.controladores;

import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;

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
		config2.getServletContext().getRequestDispatcher(GESTION_CURSOS_JSP).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//String nombrePromo = request.getParameter("nombrePromo");
		HttpSession sesion = request.getSession();
		Usuario usuario = (Usuario) sesion.getAttribute("usuario");
		//Usuario usuario = new Usuario ( "Miguel", "Solera", 1, "miguel@uc3m.es", "descripcion", "intereses", "565543324", "VISA", 0, "1234");
		String nombrePromo = "Promo prueba";
		String precio1 = request.getParameter("precio");
		String tipo_promocion1 = request.getParameter("tipo_promocion");
		String fecha_fin = request.getParameter("datepicker");
		/*****/
		
		String mensaje ="";
		String pagina = "";
		pagina = GESTION_CURSOS_JSP;
		//Promocion p = new Promocion();
		String m = comprobarPromocion(nombrePromo, precio1, tipo_promocion1, fecha_fin);
		if(promDao.buscarTodosLosPromociones().size()!=0){
			mensaje = "Error al crear promocion. Ya existe una promocion.";
			sesion.setAttribute("mensajePromociones", mensaje);
		}
		//Si la promocion a crear presenta todos los campos y no hay otra en el sistema
		else if (m==null || m.equals("")){
			int precio2 = Integer.parseInt(precio1);
			int tipo_promocion2 = Integer.parseInt(tipo_promocion1);
			//Creamos nueva promocion
			Promocion p = new Promocion(nombrePromo, fecha_fin, precio2, tipo_promocion2, usuario);
			pagina = ENTRADA_JSP;
			boolean promocionExito=true;
			
			//Mensajes de error y no guardamos promocion
			for (Curso c : curDao.buscarTodosLosCursos()) {
				
				int precioInicial = c.getPrecio_inicial();
				int descuento = p.getDescuento();
				int tipoDescuento = p.getTipo_promo();
				
				
				if (tipoDescuento==0) {
					if(descuento>0.3*precioInicial){
						m="Error al crear promocion. El valor de la promocion es mayor que los beneficios que el profesor obtendria en el curso "+c.getDES_titulo();
						sesion.setAttribute("mensajePromociones", m);
						promocionExito=false;
					}
					
				}
				
				//Tipo promocion %
				else{
					if((descuento*0.01)*precioInicial>0.7*precioInicial){
						m="Error al crear la promocion. El valor de la promocion es mayor que los beneficios que el profesor obtendria en el curso "+c.getDES_titulo();
						sesion.setAttribute("mensajePromociones", m);
						promocionExito=false;
					}
					
				}
				
			}
			
			//si no hay problemas con la promocion (es  mayor que el beneficio) se crea y modifican los cursos.
			if (promocionExito) {
				sesion.setAttribute("mensajePromociones", "");
				for (Curso c : curDao.buscarTodosLosCursos()) {
					
					int precioInicial = c.getPrecio_inicial();
					int descuento = p.getDescuento();
					int tipoDescuento = p.getTipo_promo();
					
					
					if (tipoDescuento==0) {
							c.setPrecio_final(precioInicial-descuento);
							c.setFechaFinDescuento(p.getFecha_fin());
							try {
								p=promDao.guardarPromocion(p);
							} catch (Exception e) {
								// TODO Auto-generated catch block
							e.printStackTrace();
							}
						
					}
					
					//Tipo promocion %
					else{
							int descuentoTotal = (int) (precioInicial-((descuento*0.01)*precioInicial));
							c.setPrecio_final(descuentoTotal);
							c.setFechaFinDescuento(p.getFecha_fin());
							try {
								p=promDao.guardarPromocion(p);
							} catch (Exception e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						
					}
					
					try {
						c=curDao.modificarCurso(c);
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
					
				}
			}
			
			
			Collection<Promocion> promociones = promDao.buscarTodosLosPromociones();
			sesion.setAttribute("promociones", promociones);
			
			/*ACTUALIZAMOS LAS VARIABLES DE SESION*/
			/* Recuperar de DB -> CURSOS WHERE TIPO_estado = 0 */
			Collection<Curso> cursosSinValidar = curDao.recuperarCursosPorDEstado(0);
			sesion.setAttribute("cursosValidar", cursosSinValidar);
			/* Recuperar de DB -> CURSOS WHERE TIPO_destacado = 0 */
			Collection<Curso> cursosDestacados = curDao.recuperarCursosPorDestacado(0);
			sesion.setAttribute("cursosDestacados", cursosDestacados);
			
		}else{
			
			mensaje = m;
			sesion.setAttribute("mensajePromociones", mensaje);
		}
			
			config2.getServletContext().getRequestDispatcher(pagina).forward(request, response);
			
		
	}

	private Promocion crearPromocion(String nombrePromo, int precio, int tipo_promocion, String fecha_fin, Usuario profesor) {
		Promocion p = new Promocion();
		
		p.setNombrePromo(nombrePromo);
		p.setDescuento(precio);
		p.setTipo_promo(tipo_promocion);
		p.setFecha_fin(fecha_fin);
		p.setProfesor(profesor);
		return p;
	}



	private String comprobarPromocion(String nombrePromo, String precio, String tipo_promocion, String fecha_fin) {
		String m = "";
		
		if (nombrePromo.equals("") || precio.equals("") || tipo_promocion.equals("") || fecha_fin.equals("")) {
			m ="Fallo al crear nueva promocion. ";
		}
		
		return m;
	}

}