package com.example.angel.servicios;

import com.example.angel.jpa.Evento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.ApplicationScope;

import java.util.List;
import java.util.Optional;


@Service
@ApplicationScope
public class EventoService {

    @Autowired
    private EventoRepositorio eventos;

    public EventoService(EventoRepositorio eventos) {
        this.eventos = eventos;
    }

    public List<Evento> listaEventos() {
        return eventos.findAll();
    }

    public Optional<Evento> buscarEvento(Integer id) {
        return eventos.findById(id);
    }

    public void guardarEvento(Evento evento) {
        eventos.save(evento);
    }

    public void eliminarEventoById(Integer id) {
        eventos.deleteById(id);
    }


    public void eliminarEvento(Integer id) {
        eventos.deleteById(id);
    }

    public void actualizarEvento(Evento evento) {
        eventos.save(evento);
    }
}
