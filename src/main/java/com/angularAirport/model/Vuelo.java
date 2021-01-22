package com.angularAirport.model;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Date;
import java.util.List;


/**
 * The persistent class for the vuelo database table.
 * 
 */
@Entity
@Table(name = "vuelo")
@NamedQuery(name="Vuelo.findAll", query="SELECT v FROM Vuelo v")
public class Vuelo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "id")
	private int id;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "fecha")
	private Date fecha;
	
	@Column(name = "precio")
	private int precio;

	//bi-directional many-to-one association to Billete
	@JsonIgnore
	@OneToMany(mappedBy="vuelo")
	private List<Billete> billetes;

	//bi-directional many-to-one association to Nacionalidad
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="origen")
	private Nacionalidad nacionalidad1;

	//bi-directional many-to-one association to Nacionalidad
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="destino")
	private Nacionalidad nacionalidad2;

	public Vuelo() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getFecha() {
		return this.fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public int getPrecio() {
		return this.precio;
	}

	public void setPrecio(int precio) {
		this.precio = precio;
	}

	public List<Billete> getBilletes() {
		return this.billetes;
	}

	public void setBilletes(List<Billete> billetes) {
		this.billetes = billetes;
	}

	public Billete addBillete(Billete billete) {
		getBilletes().add(billete);
		billete.setVuelo(this);

		return billete;
	}

	public Billete removeBillete(Billete billete) {
		getBilletes().remove(billete);
		billete.setVuelo(null);

		return billete;
	}

	public Nacionalidad getNacionalidad1() {
		return this.nacionalidad1;
	}

	public void setNacionalidad1(Nacionalidad nacionalidad1) {
		this.nacionalidad1 = nacionalidad1;
	}

	public Nacionalidad getNacionalidad2() {
		return this.nacionalidad2;
	}

	public void setNacionalidad2(Nacionalidad nacionalidad2) {
		this.nacionalidad2 = nacionalidad2;
	}

}