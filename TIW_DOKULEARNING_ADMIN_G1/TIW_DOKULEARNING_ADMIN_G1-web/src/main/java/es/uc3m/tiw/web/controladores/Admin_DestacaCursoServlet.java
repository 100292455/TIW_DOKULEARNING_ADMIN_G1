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

@WebServlet("/DestacaCurso")
public class Admin_DestacaCursoServlet extends HttpServlet {
	private static final String DESTACACURSO_JSP = "/Admin_CursosDestacados.jsp";
	private static final long serialVersionUID = 1L;
	@Override
	public void init() throws ServletException {
		
	}
       


	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String pagina = "";
		pagina = DESTACACURSO_JSP;
		
		HttpSession sesion = request.getSession();	
		ServletContext context = sesion.getServletContext();
		String idCursoStr = request.getParameter("IdCurso");
		int idCurso = Integer.parseInt(idCursoStr);
		ArrayList<Curso> cursos = (ArrayList<Curso>) context.getAttribute("cursosDestacados");
		context.removeAttribute("cursosDestacados");
		
		for (Curso curso : cursos) {
			if (curso.getID_curso() == idCurso) {
				/* UPDATE CURSOS WHERE ID_CURSO = idCurso , SET TIPO_destacado = 1 */
				curso.setTIPO_destacado(1);
				cursos.remove(curso);
				break;
			}
		}
		/* DEVOLVER LOS CURSOS CON TIPO_estado = 1  */

		context.setAttribute("cursosDestacados", cursos);
		
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