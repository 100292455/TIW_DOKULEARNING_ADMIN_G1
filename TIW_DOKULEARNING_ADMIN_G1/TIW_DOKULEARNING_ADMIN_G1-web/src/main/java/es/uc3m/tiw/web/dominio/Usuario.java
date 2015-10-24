

package es.uc3m.tiw.web.dominio;

public class Usuario {
    int ID_usuario;
	public String nombre;
	public String apellido;
	public int sexo;
	public String email;
	public String telefono;
	public String medio_cobro;
	public int tipo_usuario;
	public String clave;
	
	public int getID_usuario() {
		return ID_usuario;
	}

	public void setID_usuario(int iD_usuario) {
		ID_usuario = iD_usuario;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public int getSexo() {
		return sexo;
	}

	public void setSexo(int sexo) {
		this.sexo = sexo;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getMedio_cobro() {
		return medio_cobro;
	}

	public void setMedio_cobro(String medio_cobro) {
		this.medio_cobro = medio_cobro;
	}

	public int getTipo_usuario() {
		return tipo_usuario;
	}

	public void setTipo_usuario(int tipo_usuario) {
		this.tipo_usuario = tipo_usuario;
	}

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	public Usuario(int iD_usuario, String nombre, String apellido, int sexo,
			String email, String telefono, String medio_cobro,
			int tipo_usuario, String clave) {
		super();
		ID_usuario = iD_usuario;
		this.nombre = nombre;
		this.apellido = apellido;
		this.sexo = sexo;
		this.email = email;
		this.telefono = telefono;
		this.medio_cobro = medio_cobro;
		this.tipo_usuario = tipo_usuario;
		this.clave = clave;
	}

	public Usuario() {
		// TODO Auto-generated constructor stub
	}
	
}