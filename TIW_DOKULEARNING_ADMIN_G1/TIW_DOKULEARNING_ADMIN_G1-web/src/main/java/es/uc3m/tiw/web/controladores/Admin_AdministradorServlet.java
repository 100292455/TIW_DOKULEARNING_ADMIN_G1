package es.uc3m.tiw.web.controladores;

import java.io.IOException;
import java.util.Collection;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.transaction.UserTransaction;

import es.uc3m.tiw.model.Curso;
import es.uc3m.tiw.model.Pedido;
import es.uc3m.tiw.model.Matricula;
import es.uc3m.tiw.model.dao.CursoDAO;
import es.uc3m.tiw.model.dao.CursoDAOImpl;
import es.uc3m.tiw.model.dao.MatriculaDAOImpl;
import es.uc3m.tiw.model.dao.PromocionDAOImpl;
import es.uc3m.tiw.model.dao.PromocionDAO;
import es.uc3m.tiw.model.dao.PedidoDAOImpl;
import es.uc3m.tiw.model.dao.PedidoDAO;
import es.uc3m.tiw.web.rest.AlumnoWSBanco;

@WebServlet("/Administracion")
/* Clase que funciona como controlador de la herramienta de administracion */
public class Admin_AdministradorServlet extends HttpServlet {
	private static final String ENTRADA_JSP = "/index.jsp";
	private static final String VALIDAR_CURSOS_JSP = "/Admin_ValidarCursos.jsp";
	private static final String ADMIN_CONCILIACION_JSP = "/Conciliacion.jsp";
	private static final String ADMIN_DESTACADOS_JSP = "/Admin_CursosDestacados.jsp";
	private static final String ADMIN_PROMOCIONES_JSP = "/GestionPromociones.jsp";
	private static final long serialVersionUID = 1L;
	private AlumnoWSBanco ws;
	@PersistenceContext(unitName = "demoTIW")
	private EntityManager em;
	@Resource
	private UserTransaction ut;
	private ServletConfig config2;
	private PromocionDAO promDao;
	private CursoDAO curDao;
	private PedidoDAO pedDao;
	private MatriculaDAOImpl matDao;
	@Override
	public void init(ServletConfig config) throws ServletException {
		config2 = config;
		curDao = new CursoDAOImpl(em, ut);
		promDao = new PromocionDAOImpl(em, ut);
		pedDao = new PedidoDAOImpl(em, ut);
		matDao = new MatriculaDAOImpl(em, ut);
		ws = new AlumnoWSBanco();

	}

	public void destroy() {
		curDao = null;
		promDao = null;
		pedDao = null;
	}



	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String filtro = request.getParameter("filtro");
		Double beneficioPortalTotal=0.0;
		Double beneficioProfe;
		Double beneficioPortal;
		
		if(filtro.equals("Conciliacion")) {
			String pagina = ADMIN_CONCILIACION_JSP;
			HttpSession sesion = request.getSession();	
			/* Recuperar de DB -> PEDIDOS WHERE CONCILIACION = 0 */
			Collection<Pedido> pedidosSinConciliar=pedDao.recuperarPedidosSinConciliar();
			for (Pedido pedido : pedidosSinConciliar) {
				String cod_pedido = pedido.getCOD_pago();
				/*Llamar a web service pasandole el codPago*/
				Double precioConciliado =  ws.ConciliarWSBanco(cod_pedido);
				/*Recibo del banco el precio conciliado (99% del precio original del curso)*/
				pedido.setESTADO_conciliado(1);
				pedido.setImporteCobrado(precioConciliado);

				try {
					pedDao.modificarPedido(pedido);
				} catch (Exception e) {
					e.printStackTrace();
				}

				/*Procedo a conciliar (repartir beneficio)*/

				//Comprobamos si hay promociones/cupones para este curso
				
				if (pedido.getCurso().getPrecio_inicial()==pedido.getCurso().getPrecio_final()) {
					beneficioProfe = pedido.getImporteCobrado()*0.7;
					beneficioPortal = pedido.getImporteCobrado()*0.3;
				}
				//hay promociones
				else if (promDao.buscarTodosLosPromociones().size()!=0) {
					beneficioProfe = pedido.getCurso().getPrecio_inicial()*0.7;
					beneficioPortal = precioConciliado*0.3;
				}
				//hay cupones
				else {
					beneficioProfe = precioConciliado*0.7;
					beneficioPortal = pedido.getCurso().getPrecio_inicial()*0.3;
				}

				//Metemos los beneficios en Matricula
				for (Matricula matricula : matDao.buscarTodosLosMatriculas()) {
					//Cuando encuentra el curso en la tabla matricula
					if (pedido.getCurso().getID_curso()==matricula.getCurso().getID_curso()) {
						matricula.setBeneficioPortal(beneficioPortal);
						matricula.setBeneficioProfe(beneficioProfe);
						try {
							matDao.modificarMatricula(matricula);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				}
				beneficioPortalTotal=beneficioPortalTotal+beneficioPortal;
			}
			Collection<Matricula> matriculasBeneficio=matDao.buscarTodosLosMatriculas();
			sesion.setAttribute("matriculasBeneficio", matriculasBeneficio);
			sesion.setAttribute("beneficioPortalTotal", beneficioPortalTotal);
			config2.getServletContext().getRequestDispatcher(pagina).forward(request, response);

		}
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
			config2.getServletContext().getRequestDispatcher(pagina).forward(request, response);
		}

		if(filtro.equals("AdministrarCursosPendientes")){
			pagina = VALIDAR_CURSOS_JSP;
			HttpSession sesion = request.getSession();	
			/* Recuperar de DB -> CURSOS WHERE TIPO_estado = 0 */
			Collection<Curso> cursosSinValidar = curDao.recuperarCursosPorDEstado(0);
			sesion.setAttribute("cursosValidar", cursosSinValidar);

			config2.getServletContext().getRequestDispatcher(pagina).forward(request, response);	
		}

		if(filtro.equals("AdministrarPromociones")){
			pagina = ADMIN_PROMOCIONES_JSP;
			HttpSession sesion = request.getSession();	
			/* Recuperar de DB -> todos los CURSOS */
			Collection<Curso> cursos = curDao.buscarTodosLosCursos();
			sesion.setAttribute("cursos", cursos);

			config2.getServletContext().getRequestDispatcher(pagina).forward(request, response);	
		}
		if(filtro.equals("AdministrarCursosDestacados")){
			pagina = ADMIN_DESTACADOS_JSP;
			HttpSession sesion = request.getSession();	
			/* Recuperar de DB -> CURSOS WHERE TIPO_destacado = 0 */
			Collection<Curso> cursosDestacados = curDao.recuperarCursosPorDestacado(0);
			sesion.setAttribute("cursosDestacados", cursosDestacados);

			config2.getServletContext().getRequestDispatcher(pagina).forward(request, response);	
		}

	}

}