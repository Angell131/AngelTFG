package com.example.angel.jpa;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.List;

@Entity
@NamedQuery(name="Equipo.findAll", query="SELECT e FROM Equipo e")
public class Equipo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_equipo")
	private int idEquipo;

	private double latitud;

	private double longitud;

	private String nombre;

	private String sede;

	//bi-directional many-to-one association to Asistencia
	@OneToMany(mappedBy="equipo")
	private List<Asistencia> asistencias;

	//bi-directional many-to-one association to Competidor
	@OneToMany(mappedBy="equipo")
	private List<Competidor> competidors;

	public Equipo() {
	}

	public int getIdEquipo() {
		return this.idEquipo;
	}

	public void setIdEquipo(int idEquipo) {
		this.idEquipo = idEquipo;
	}

	public double getLatitud() {
		return this.latitud;
	}

	public void setLatitud(double latitud) {
		this.latitud = latitud;
	}

	public double getLongitud() {
		return this.longitud;
	}

	public void setLongitud(double longitud) {
		this.longitud = longitud;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getSede() {
		return this.sede;
	}

	public void setSede(String sede) {
		this.sede = sede;
	}

	public List<Asistencia> getAsistencias() {
		return this.asistencias;
	}

	public void setAsistencias(List<Asistencia> asistencias) {
		this.asistencias = asistencias;
	}

	public Asistencia addAsistencia(Asistencia asistencia) {
		getAsistencias().add(asistencia);
		asistencia.setEquipo(this);

		return asistencia;
	}

	public Asistencia removeAsistencia(Asistencia asistencia) {
		getAsistencias().remove(asistencia);
		asistencia.setEquipo(null);

		return asistencia;
	}

	public List<Competidor> getCompetidors() {
		return this.competidors;
	}

	public void setCompetidors(List<Competidor> competidors) {
		this.competidors = competidors;
	}

	public Competidor addCompetidor(Competidor competidor) {
		getCompetidors().add(competidor);
		competidor.setEquipo(this);

		return competidor;
	}

	public Competidor removeCompetidor(Competidor competidor) {
		getCompetidors().remove(competidor);
		competidor.setEquipo(null);

		return competidor;
	}

}