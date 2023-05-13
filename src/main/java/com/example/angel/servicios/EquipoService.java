package com.example.angel.servicios;


import com.example.angel.jpa.Equipo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.ApplicationScope;

import java.util.List;
import java.util.Optional;

@Service
@ApplicationScope
public class EquipoService {

    @Autowired
    private EquipoRepositorio equipos;

    public EquipoService(EquipoRepositorio equipos) {
        this.equipos = equipos;
    }

    public List<Equipo> listaEquipos() {
        return equipos.findAll();
    }

    public Optional<Equipo> buscarEquipo(Integer id) {
        return equipos.findById(id);
    }

    public void guardarEquipo(Equipo equipo) {
        equipos.save(equipo);
    }

    public void eliminarEquipoById(Integer id) {
        equipos.deleteById(id);
    }


}
