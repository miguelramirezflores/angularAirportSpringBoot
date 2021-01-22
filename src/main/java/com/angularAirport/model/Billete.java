package com.angularAirport.model;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;


/**
 * The persistent class for the billete database table.
 * 
 */
@Entity
@Table(name = "billete")
@NamedQuery(name="Billete.findAll", query="SELECT b FROM Billete b")
public class Billete implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "id")
	private int id;

	//bi-directional many-to-one association to Usuario
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="usuario_idusuario")
	private Usuario usuario;

	//bi-directional many-to-one association to Vuelo
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="vuelo_idvuelo")
	private Vuelo vuelo;

	public Billete() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Vuelo getVuelo() {
		return this.vuelo;
	}

	public void setVuelo(Vuelo vuelo) {
		this.vuelo = vuelo;
	}

}