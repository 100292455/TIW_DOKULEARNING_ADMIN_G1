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
public class Admin_AdministradorServlet extends HttpServlet {
	private static final String ENTRADA_JSP = "/Admin_Administrador.jsp";
	private static final String VALIDARCURSOS_JSP = "/Admin_ValidarCursos.jsp";
	private static final String GSETIONARCUPONES_JSP = "/GestionCupones.jsp";
	private static final long serialVersionUID = 1L;
	private Curso curso;
	private ArrayList<Curso> cursos;
	private int new_IDCurso = 0;
	@Override
	public void init() throws ServletException {
	
		cursos = new ArrayList<Curso>();
		Curso ingles = new Curso(new_IDCurso, "ingles", "curso ingles", 1, 10, 50, 50, 0, 1, 0);
		new_IDCurso++;
		Curso frances = new Curso(new_IDCurso, "frances", "curso frances", 1, 10, 50, 50, 0, 1, 0);
		new_IDCurso++;
		Curso italiano = new Curso(new_IDCurso, "italiano", "curso italiano", 0, 20, 80, 80, 0, 2, 0);
		new_IDCurso++;
		Curso matematicas = new Curso(new_IDCurso, "matematicas", "curso matematicas", 2, 10, 50, 50, 0, 2, 0);
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
		this.getServletContext().getRequestDispatcher(VALIDARCURSOS_JSP).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String filtro = request.getParameter("filtro");
		String pagina = "";
		pagina = ENTRADA_JSP;
		
		if(filtro.equals("IrMenu")){
			this.getServletContext().getRequestDispatcher(pagina).forward(request, response);
		}
		
		if(filtro.equals("CursosPendientes")){
			pagina = VALIDARCURSOS_JSP;
			HttpSession sesion = request.getSession();	
			ServletContext context = sesion.getServletContext();
			/* Recuperar de DB -> CURSOS WHERE TIPO_estado = 1 */
			context.removeAttribute("cursos");
			context.setAttribute("cursos", cursos);
				
			this.getServletContext().getRequestDispatcher(pagina).forward(request, response);	
		}
		if(filtro.equals("GestionCupones")){
			pagina = GSETIONARCUPONES_JSP;
			HttpSession sesion = request.getSession();	
			ServletContext context = sesion.getServletContext();
			/* Recuperar de DB -> todos los CURSOS */
			context.removeAttribute("cursos");
			context.setAttribute("cursos", cursos);
				
			this.getServletContext().getRequestDispatcher(pagina).forward(request, response);	
		}
		else {
			this.getServletContext().getRequestDispatcher(pagina).forward(request, response);
		}
	}
	
}