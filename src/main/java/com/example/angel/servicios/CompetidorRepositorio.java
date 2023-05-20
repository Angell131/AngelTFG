package com.example.angel.servicios;

import com.example.angel.jpa.Competidor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CompetidorRepositorio extends JpaRepository<Competidor, String> {

    List<Competidor> findByNombre(String nombre);
}
