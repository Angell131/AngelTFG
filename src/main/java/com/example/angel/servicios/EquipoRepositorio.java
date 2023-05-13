package com.example.angel.servicios;

import com.example.angel.jpa.Equipo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EquipoRepositorio extends JpaRepository<Equipo, Integer> {

    List<Equipo> findByNombre(String nombre);

}
