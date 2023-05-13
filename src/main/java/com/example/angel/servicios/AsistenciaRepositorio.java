package com.example.angel.servicios;

import com.example.angel.jpa.Asistencia;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AsistenciaRepositorio extends JpaRepository<Asistencia, Integer> {

    List<Asistencia> findByIdAsistencia(int idAsistencia);


}
