package com.example.angel.jpa;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@NamedQuery(name="Competidor.findAll", query="SELECT c FROM Competidor c")
public class Competidor implements Serializable {
	private static final long serialVersionUID = 1L;

	private byte activo;

	private String contraseña;
	@Id
	@Column(name="email")
	private String email;
	private String nombre;
	private String rol;

	//bi-directional many-to-one association to Equipo
	@ManyToOne
	@JoinColumn(name="id_equipo")
	private Equipo equipo;

	public Competidor() {

	}

	public byte getActivo() {
		return this.activo;
	}

	public void setActivo(byte activo) {
		this.activo = activo;
	}

	public String getContraseña() {
		return this.contraseña;
	}

	public void setContraseña(String contraseña) {
		this.contraseña = contraseña;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getRol() {
		return this.rol;
	}

	public void setRol(String rol) {
		this.rol = rol;
	}

	public Equipo getEquipo() {
		return this.equipo;
	}

	public void setEquipo(Equipo equipo) {
		this.equipo = equipo;
	}

}