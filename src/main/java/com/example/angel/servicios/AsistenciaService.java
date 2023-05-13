package com.example.angel.servicios;

import com.example.angel.jpa.Asistencia;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.ApplicationScope;

import java.util.List;
import java.util.Optional;

@Service
@ApplicationScope
public class AsistenciaService {

    @Autowired
    private AsistenciaRepositorio asistencias;

    public AsistenciaService(AsistenciaRepositorio asistencias) {
        this.asistencias = asistencias;
    }

    public List<Asistencia> listaAsistencias() {
        return asistencias.findAll();
    }

    public Optional<Asistencia> buscarAsistencia(Integer id) {
        return asistencias.findById(id);
    }

    public void guardarAsistencia(Asistencia asistencia) {
        asistencias.save(asistencia);
    }

    public void eliminarAsistenciaById(Integer id) {
        asistencias.deleteById(id);
    }


}
