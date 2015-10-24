package es.uc3m.tiw.web.dominio;

import java.util.Date;

public class Curso {

	int ID_curso;
	String DES_titulo;
	String DES_descripcion;
	int TIPO_dificultad;
	int horas;
	int precio_inicial;
	int precio_final;
	int TIPO_destacado;
	int COD_profesor;
	int TIPO_estado;
	Date FechaFinDescuento;
	public Curso() {
		// TODO Auto-generated constructor stub
	}
	
	public Curso(int iD_curso, 
			String dES_titulo, 
			String dES_descripcion, 
			int tIPO_dificultad, 
			int horas, 
			int precio_inicial, 
			int precio_final,	
			int tIPO_destacado,	
			int cOD_profesor, 
			int tIPO_estado) {
		super();
		this.ID_curso = iD_curso;
		this.DES_titulo = dES_titulo;
		this.DES_descripcion = dES_descripcion;
		this.TIPO_dificultad = tIPO_dificultad;
		this.horas = horas;
		this.precio_inicial = precio_inicial;
		this.precio_final = precio_final;
		this.TIPO_destacado = tIPO_destacado;
		this.COD_profesor = cOD_profesor;
		this.TIPO_estado = tIPO_estado;
	}
	
	public Curso(int iD_curso, 
			String dES_titulo, 
			String dES_descripcion, 
			int tIPO_dificultad, 
			int horas, 
			int precio_inicial, 
			int precio_final,	
			int tIPO_destacado,	
			int cOD_profesor, 
			int tIPO_estado,
			Date FechaFinDescuento) {
		super();
		this.ID_curso = iD_curso;
		this.DES_titulo = dES_titulo;
		this.DES_descripcion = dES_descripcion;
		this.TIPO_dificultad = tIPO_dificultad;
		this.horas = horas;
		this.precio_inicial = precio_inicial;
		this.precio_final = precio_final;
		this.TIPO_destacado = tIPO_destacado;
		this.COD_profesor = cOD_profesor;
		this.TIPO_estado = tIPO_estado;
		this.FechaFinDescuento = FechaFinDescuento;
	}
	public Curso(String titulo, String descripcion, int dificultad, int horas, int precio_inicial) {
		super();
		this.DES_titulo = titulo;
		this.DES_descripcion = descripcion;
		this.TIPO_dificultad = dificultad;
		this.horas = horas;
		this.precio_inicial = precio_inicial;
	}
	public int getID_curso() {
		return ID_curso;
	}
	public void setID_curso(int iD_curso) {
		ID_curso = iD_curso;
	}
	public String getDES_titulo() {
		return DES_titulo;
	}
	public void setDES_titulo(String dES_titulo) {
		DES_titulo = dES_titulo;
	}
	public String getDES_descripcion() {
		return DES_descripcion;
	}
	public void setDES_descripcion(String dES_descripcion) {
		DES_descripcion = dES_descripcion;
	}
	public int getTIPO_dificultad() {
		return TIPO_dificultad;
	}
	public void setTIPO_dificultad(int tIPO_dificultad) {
		TIPO_dificultad = tIPO_dificultad;
	}
	public int getHoras() {
		return horas;
	}
	public void setHoras(int horas) {
		this.horas = horas;
	}
	public int getPrecio_inicial() {
		return precio_inicial;
	}
	public void setPrecio_inicial(int precio_inicial) {
		this.precio_inicial = precio_inicial;
	}
	public int getPrecio_final() {
		return precio_final;
	}
	public void setPrecio_final(int precio_final) {
		this.precio_final = precio_final;
	}
	public int getTIPO_destacado() {
		return TIPO_destacado;
	}
	public void setTIPO_destacado(int tIPO_destacado) {
		TIPO_destacado = tIPO_destacado;
	}
	public int getCOD_profesor() {
		return COD_profesor;
	}
	public void setCOD_profesor(int cOD_profesor) {
		COD_profesor = cOD_profesor;
	}
	public int getTIPO_estado() {
		return TIPO_estado;
	}
	public void setTIPO_estado(int tIPO_estado) {
		TIPO_estado = tIPO_estado;
	}
	public Date getFechaFinDescuento() {
		return FechaFinDescuento;
	}
	public void setFechaFinDescuento(Date fechaFinDescuento) {
		FechaFinDescuento = fechaFinDescuento;
	}
	
	
}