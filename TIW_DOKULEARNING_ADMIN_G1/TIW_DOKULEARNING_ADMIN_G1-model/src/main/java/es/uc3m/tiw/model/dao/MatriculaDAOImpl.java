package es.uc3m.tiw.model.dao;

import java.util.Collection;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.transaction.UserTransaction;

import es.uc3m.tiw.model.Curso;
import es.uc3m.tiw.model.Matricula;
import es.uc3m.tiw.model.Usuario;

public class MatriculaDAOImpl implements MatriculaDAO {

	private final EntityManager em;
	private final UserTransaction ut;
	
	
	
	public MatriculaDAOImpl(EntityManager em, UserTransaction ut) {
		super();
		this.em = em;
		this.ut = ut;
	}



	/* (non-Javadoc)
	 * @see es.uc3m.tiw.daos.UsuarioDAO#guardarUsuario(es.uc3m.tiw.dominios.Usuario)
	 */
	@Override
	public Matricula guardarMatricula(Matricula nuevoMatricula) throws Exception{
		ut.begin();
		em.persist(nuevoMatricula);
		ut.commit();
		return nuevoMatricula;
		
	}
	/* (non-Javadoc)
	 * @see es.uc3m.tiw.daos.UsuarioDAO#modificarUsuario(es.uc3m.tiw.dominios.Usuario)
	 */
	@Override
	public Matricula modificarMatricula(Matricula matricula) throws Exception{
		ut.begin();
		em.merge(matricula);
		ut.commit();
		return matricula;
	}
	/* (non-Javadoc)
	 * @see es.uc3m.tiw.daos.UsuarioDAO#borrarUsuario(es.uc3m.tiw.dominios.Usuario)
	 */
	@Override
	public void borrarMatricula(Matricula matricula) throws Exception{
		ut.begin();
		em.remove(em.merge(matricula));
		ut.commit();
	}
	/* (non-Javadoc)
	 * @see es.uc3m.tiw.daos.UsuarioDAO#recuperarUsuarioPorPK(java.lang.Integer)
	 */
	@Override
	public Matricula recuperarMatriculaPorPK(Long pk){
		return em.find(Matricula.class, pk);
	}
	/* (non-Javadoc)
	 * @see es.uc3m.tiw.daos.UsuarioDAO#recuperarUsuarioPorNombre(java.lang.String)
	 */
	@Override
	public Matricula recuperarMatriculaPorAlumnoYCurso(Usuario alumno, Curso curso) {
		return em.createQuery("select m from Matricula m where m.alumno="+alumno+"' and m.curso='"+curso+"'",Matricula.class).getSingleResult();
	}
	
	public Collection<Matricula> buscarTodosLosMatriculas(){
		return em.createQuery("select m from Matricula m",Matricula.class).getResultList();
	}
	
}