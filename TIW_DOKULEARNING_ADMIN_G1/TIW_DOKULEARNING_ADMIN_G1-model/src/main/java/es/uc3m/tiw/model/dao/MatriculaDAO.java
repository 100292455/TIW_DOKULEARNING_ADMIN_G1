package es.uc3m.tiw.model.dao;

import java.util.Collection;

import es.uc3m.tiw.model.Curso;
import es.uc3m.tiw.model.Matricula;
import es.uc3m.tiw.model.Usuario;

public interface MatriculaDAO {

	public abstract Matricula guardarMatricula(Matricula nuevoMatricula)
			throws Exception;

	public abstract Matricula modificarMatricula(Matricula matricula) throws Exception;

	public abstract void borrarMatricula(Matricula matricula) throws Exception;

	public abstract Matricula recuperarMatriculaPorPK(Long pk);

	public abstract Matricula recuperarMatriculaPorAlumnoYCurso(Usuario alumno, Curso curso);

	public abstract Collection<Matricula> buscarTodosLosMatriculas();

}