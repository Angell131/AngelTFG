package com.example.angel.servicios;

import com.example.angel.jpa.Competidor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.ApplicationScope;

import java.util.List;
import java.util.Optional;

@Service
@ApplicationScope
public class CompetidorService {

    @Autowired
    private CompetidorRepositorio competidores;

    public CompetidorService(CompetidorRepositorio competidores) {
        this.competidores = competidores;
    }

    public List<Competidor> listaCompetidores() {
        return competidores.findAll();
    }


    public Optional<Competidor> buscarCompetidor(Integer name) {
        return competidores.findById(name);
    }

    public void guardarCompetidor(Competidor c) {
        competidores.save(c);
    }
}
