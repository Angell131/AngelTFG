package com.example.angel.servicios;

import com.example.angel.jpa.Evento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EventoRepositorio extends JpaRepository<Evento, Integer> {

    List<Evento> findByNombre(String nombre);
}
