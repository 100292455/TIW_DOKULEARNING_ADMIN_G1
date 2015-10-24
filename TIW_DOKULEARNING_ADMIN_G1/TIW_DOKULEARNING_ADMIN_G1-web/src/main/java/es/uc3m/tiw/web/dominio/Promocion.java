package es.uc3m.tiw.web.dominio;

import java.util.Date;

public class Promocion {
	
	public Promocion() {
		super();
	}
	String nombrePromo;
	int id_promo;
	String fecha_fin;
	int descuento;
	int  tipo_promo;
	

	public Promocion(String nombrePromo, int id_promo, String fecha_fin,
			int descuento, int tipo_promo) {
		super();
		this.nombrePromo = nombrePromo;
		this.id_promo = id_promo;
		this.fecha_fin = fecha_fin;
		this.descuento = descuento;
		this.tipo_promo = tipo_promo;
	}
	
	public String getNombrePromo() {
		return nombrePromo;
	}
	public void setNombrePromo(String nombrePromo) {
		this.nombrePromo = nombrePromo;
	}
	public int getId_promo() {
		return id_promo;
	}
	public void setId_promo(int id_promo) {
		this.id_promo = id_promo;
	}
	public String getFecha_fin() {
		return fecha_fin;
	}
	public void setFecha_fin(String fecha_fin) {
		this.fecha_fin = fecha_fin;
	}
	public int getDescuento() {
		return descuento;
	}
	public void setDescuento(int descuento) {
		this.descuento = descuento;
	}
	public int getTipo_promo() {
		return tipo_promo;
	}
	public void setTipo_promo(int tipo_promo) {
		this.tipo_promo = tipo_promo;
	}
	
	
	
	
	
	
}
