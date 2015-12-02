package es.uc3m.tiw.model.dao;

import java.util.Collection;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.transaction.UserTransaction;

import es.uc3m.tiw.model.Leccion;
import es.uc3m.tiw.model.Seccion;

public class LeccionDAOImpl implements LeccionDAO {

	private final EntityManager em;
	private final UserTransaction ut;
	
	
	
	public LeccionDAOImpl(EntityManager em, UserTransaction ut) {
		super();
		this.em = em;
		this.ut = ut;
	}



	/* (non-Javadoc)
	 * @see es.uc3m.tiw.daos.UsuarioDAO#guardarUsuario(es.uc3m.tiw.dominios.Usuario)
	 */
	@Override
	public Leccion guardarLeccion(Leccion nuevoLeccion) throws Exception{
		ut.begin();
		em.persist(nuevoLeccion);
		ut.commit();
		return nuevoLeccion;
		
	}
	/* (non-Javadoc)
	 * @see es.uc3m.tiw.daos.UsuarioDAO#modificarUsuario(es.uc3m.tiw.dominios.Usuario)
	 */
	@Override
	public Leccion modificarLeccion(Leccion leccion) throws Exception{
		ut.begin();
		em.merge(leccion);
		ut.commit();
		return leccion;
	}
	/* (non-Javadoc)
	 * @see es.uc3m.tiw.daos.UsuarioDAO#borrarUsuario(es.uc3m.tiw.dominios.Usuario)
	 */
	@Override
	public Leccion borrarLeccion(Leccion leccion) throws Exception{
		ut.begin();
		em.remove(em.merge(leccion));
		ut.commit();
		return leccion;
	}
	/* (non-Javadoc)
	 * @see es.uc3m.tiw.daos.UsuarioDAO#recuperarUsuarioPorPK(java.lang.Integer)
	 */
	@Override
	public Leccion recuperarLeccionPorPK(int pk){
		return em.find(Leccion.class, pk);
	}
	/* (non-Javadoc)
	 * @see es.uc3m.tiw.daos.UsuarioDAO#recuperarUsuarioPorNombre(java.lang.String)
	 */
	@Override
	public Leccion recuperarLeccionPorNombre(String nombre) {
		return em.createQuery("select l from Leccion l where u.nombre="+nombre, Leccion.class).getSingleResult();
	}
	@Override
	public Collection<Leccion> recuperarLeccionesPorSeccion(Seccion seccion)throws NoResultException{
		return em.createQuery("select l from Leccion l where l.seccion='"+seccion, Leccion.class).getResultList();
	}
	
	public Collection<Leccion> buscarTodosLosLecciones(){
		return em.createQuery("select l from Leccion l",Leccion.class).getResultList();
	}
	
}