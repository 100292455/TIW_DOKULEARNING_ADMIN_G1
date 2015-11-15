package es.uc3m.tiw.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import static javax.persistence.GenerationType.AUTO;

@Entity
public class Cupon {

	@Id
 	@GeneratedValue(strategy = AUTO)
	private int ID_cupon;
	private String fecha_vto_cupon;
	@OneToOne
	private Usuario profesor;
	private int descuento;
	private int TIPO_cupon;
	@OneToOne
	private Curso curso;
	
	
	
	public Cupon() {
		super();
	}
	public Cupon(int id_cupon, String fecha_fin, Usuario profesor,
			int descuento, int tipo_cupon, Curso curso) {
		super();
		this.ID_cupon = id_cupon;
		this.fecha_vto_cupon = fecha_fin;
		this.profesor = profesor;
		this.descuento = descuento;
		this.TIPO_cupon = tipo_cupon;
		this.curso = curso;
	}
	public int getId_cupon() {
		return ID_cupon;
	}
	public void setId_cupon(int id_cupon) {
		this.ID_cupon = id_cupon;
	}
	public String getFecha_fin() {
		return fecha_vto_cupon;
	}
	public void setFecha_fin(String fecha_fin) {
		this.fecha_vto_cupon = fecha_fin;
	}
	public Usuario getProfesor() {
		return profesor;
	}
	public void setProfesor(Usuario profesor) {
		this.profesor = profesor;
	}
	public int getDescuento() {
		return descuento;
	}
	public void setDescuento(int descuento) {
		this.descuento = descuento;
	}
	public int getTipo_cupon() {
		return TIPO_cupon;
	}
	public void setTipo_cupon(int tipo_cupon) {
		this.TIPO_cupon = tipo_cupon;
	}
	public Curso getCurso() {
		return curso;
	}
	public void setCurso(Curso curso) {
		this.curso = curso;
	}
	
	
	
}
