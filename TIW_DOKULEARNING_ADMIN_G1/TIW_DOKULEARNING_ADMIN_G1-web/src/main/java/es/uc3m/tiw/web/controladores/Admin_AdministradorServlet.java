package es.uc3m.tiw.web.controladores;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import es.uc3m.tiw.web.dominio.Curso;

@WebServlet("/Administracion")
/* Clase que funciona como controlador de la herramienta de administracion */
public class Admin_AdministradorServlet extends HttpServlet {
	private static final String ENTRADA_JSP = "/Admin_Administrador.jsp";
	private static final String VALIDAR_CURSOS_JSP = "/Admin_ValidarCursos.jsp";
	private static final String ADMIN_DESTACADOS_JSP = "/Admin_CursosDestacados.jsp";
	private static final String ADMIN_PROMOCIONES_JSP = "/GestionPromociones.jsp";
	private static final long serialVersionUID = 1L;
	private ArrayList<Curso> cursos;
	private int new_IDCurso = 0;
	@Override
	public void init() throws ServletException {
	/* Creamos unos cursos de prueba, estos cursos se rescataran de la BBDD cuando la haya */
		cursos = new ArrayList<Curso>();
		Curso ingles = new Curso(new_IDCurso, "ingles", "curso ingles", 1, 10, 50, 50, 1, 1, 0);
		new_IDCurso++;
		Curso frances = new Curso(new_IDCurso, "frances", "curso frances", 1, 10, 50, 50, 1, 1, 0);
		new_IDCurso++;
		Curso italiano = new Curso(new_IDCurso, "italiano", "curso italiano", 0, 20, 80, 80, 0, 2, 0);
		new_IDCurso++;
		Curso matematicas = new Curso(new_IDCurso, "matematicas", "curso matematicas", 2, 10, 50, 50, 0, 2, 1);
		new_IDCurso++;
		cursos.add(ingles);
		cursos.add(frances);
		cursos.add(italiano);
		cursos.add(matematicas);
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
			ArrayList<Curso> cursosValidar = obtenerCursosPendValidar(cursos);
			context.setAttribute("cursosValidar", cursosValidar);
				
			this.getServletContext().getRequestDispatcher(pagina).forward(request, response);	
		}
		if(filtro.equals("AdministrarPromociones")){
			pagina = ADMIN_PROMOCIONES_JSP;
			HttpSession sesion = request.getSession();	
			ServletContext context = sesion.getServletContext();
			/* Recuperar de DB -> todos los CURSOS */
			context.removeAttribute("cursos");
			context.setAttribute("cursos", cursos);
				
			this.getServletContext().getRequestDispatcher(pagina).forward(request, response);	
		}
		if(filtro.equals("AdministrarCursosDestacados")){
			pagina = ADMIN_DESTACADOS_JSP;
			HttpSession sesion = request.getSession();	
			ServletContext context = sesion.getServletContext();
			/* Recuperar de DB -> CURSOS WHERE TIPO_destacado = 0 */
			ArrayList<Curso> cursosDestacados = obtenerCursosDestacados(cursos);
			context.removeAttribute("cursos");
			context.setAttribute("cursosDestacados", cursosDestacados);
				
			this.getServletContext().getRequestDispatcher(pagina).forward(request, response);	
		}
		else {
			this.getServletContext().getRequestDispatcher(pagina).forward(request, response);
		}
	}

	/* metodo que recupera los cursos pendientes de validar del arrayList que simula la BBDD */
	private ArrayList<Curso> obtenerCursosPendValidar(ArrayList<Curso> cursos) {

		ArrayList<Curso> cursosPendValidar = new ArrayList<Curso>();
		
		for (Curso curso : cursos) {
			if (curso.getTIPO_estado() == 0) {
				cursosPendValidar.add(curso);
			}
		}
		
		return cursosPendValidar;
	}
	
	/* Metodo que recupera los cursos que no estan destacados en la BBDD, se simula a traves del arrayList */
	private ArrayList<Curso> obtenerCursosDestacados(ArrayList<Curso> cursos) {

		ArrayList<Curso> cursosDestacados = new ArrayList<Curso>();
		
		for (Curso curso : cursos) {
			if (curso.getTIPO_destacado() == 0) {
				cursosDestacados.add(curso);
			}
		}
		
		return cursosDestacados;
	}
	
}