package com.angularAirport.model;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Date;
import java.util.List;


/**
 * The persistent class for the usuario database table.
 * 
 */
@Entity
@Table(name = "usuario")
@NamedQuery(name="Usuario.findAll", query="SELECT u FROM Usuario u")
public class Usuario implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "id")
	private int id;
	
	@Column(name = "email")
	private String email;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "fechaEliminacion")
	private Date fechaEliminacion;

	@Temporal(TemporalType.DATE)
	@Column(name = "fechaNav")
	private Date fechaNav;

	@Lob
	@Column(name = "imagen")
	private byte[] imagen;

	@Column(name = "nombre")
	private String nombre;
	@Column(name = "password")
	private String password;
	@Column(name = "usuario")
	private String usuario;

	//bi-directional many-to-one association to Billete
	@JsonIgnore
	@OneToMany(mappedBy="usuario")
	private List<Billete> billetes;

	//bi-directional many-to-one association to Nacionalidad
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="idnacionalidad")
	private Nacionalidad nacionalidad;

	//bi-directional many-to-one association to TipoSexo
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="idtipoSexo")
	private TipoSexo tiposexo;

	public Usuario() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getFechaEliminacion() {
		return this.fechaEliminacion;
	}

	public void setFechaEliminacion(Date fechaEliminacion) {
		this.fechaEliminacion = fechaEliminacion;
	}

	public Date getFechaNav() {
		return this.fechaNav;
	}

	public void setFechaNav(Date fechaNav) {
		this.fechaNav = fechaNav;
	}

	public byte[] getImagen() {
		return this.imagen;
	}

	public void setImagen(byte[] imagen) {
		this.imagen = imagen;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsuario() {
		return this.usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public List<Billete> getBilletes() {
		return this.billetes;
	}

	public void setBilletes(List<Billete> billetes) {
		this.billetes = billetes;
	}

	public Billete addBillete(Billete billete) {
		getBilletes().add(billete);
		billete.setUsuario(this);

		return billete;
	}

	public Billete removeBillete(Billete billete) {
		getBilletes().remove(billete);
		billete.setUsuario(null);

		return billete;
	}

	public Nacionalidad getNacionalidad() {
		return this.nacionalidad;
	}

	public void setNacionalidad(Nacionalidad nacionalidad) {
		this.nacionalidad = nacionalidad;
	}

	public TipoSexo getTiposexo() {
		return this.tiposexo;
	}

	public void setTiposexo(TipoSexo tiposexo) {
		this.tiposexo = tiposexo;
	}

}