package com.example.angel.control;
import com.example.angel.jpa.Asistencia;
import com.example.angel.jpa.Competidor;
import com.example.angel.jpa.Evento;
import com.example.angel.servicios.AsistenciaService;
import com.example.angel.servicios.CompetidorService;
import com.example.angel.servicios.EquipoService;
import com.example.angel.servicios.EventoService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;


@Controller
public class Controlador {

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    EquipoService equipos;

    @Autowired
    AsistenciaService asistencias;

    @Autowired
    CompetidorService competidores;

    @Autowired
    EventoService eventos;

    //atiende a la petición localhost:8089/
    @RequestMapping("/")
    public ModelAndView peticionRaiz(Authentication auth) {
        ModelAndView mv = new ModelAndView();
        if (auth == null)
            mv.addObject("compe", "No se ha iniciado sesión");
        else
            mv.addObject("compe", auth.getName());
        mv.setViewName("index");
        String texto = "angel";
        String encriptado = encoder.encode(texto);
        System.out.println("Contraseña original: " + texto);
        System.out.println("Contraseña encriptado: " + encriptado);
        return mv;
    }


    //login para iniciar sesión
    @RequestMapping("login")
    public ModelAndView peticionSesion(Authentication auth) {
        ModelAndView mv = new ModelAndView();
        if (auth == null)
            mv.addObject("compe", "No se ha iniciado sesión");
        else
            mv.addObject("compe", auth.getName());
        mv.setViewName("login");
        return mv;
    }

    @RequestMapping("/denegado")
    public ModelAndView peticionDenegado(Authentication auth) {
        ModelAndView mv = new ModelAndView();
        if(auth==null)
            mv.addObject("compe", "No se ha iniciado sesión");
        else
            mv.addObject("compe", auth.getName());
        mv.setViewName("denegado");
        return mv;
    }


    @RequestMapping("/mapas")
    public ModelAndView peticionMapas(Authentication auth) {
        ModelAndView mv = new ModelAndView();
        Competidor compe=null;
        if(auth==null)
            mv.addObject("compe", "No se ha iniciado sesión");
        else {
            mv.addObject("compe", auth.getName());
            Optional<Competidor> compeOpcional = competidores.buscarCompetidor(auth.getName());

            if (compeOpcional.isPresent())
                compe = compeOpcional.get();
        }

        mv.addObject("usuario", compe);

        mv.setViewName("mapas");
        return mv;
    }

    @RequestMapping("/eventos")
    public ModelAndView peticionEventos(Authentication auth) {
        ModelAndView mv = new ModelAndView();
        Competidor compe=null;
        if(auth==null)
            mv.addObject("compe", "No se ha iniciado sesión");
        else {
            mv.addObject("compe", auth.getName());
        }

        mv.addObject("competidor", compe);

        List<Evento> listaEventos = eventos.listaEventos();
        mv.addObject("listaEventos", listaEventos);

        mv.setViewName("eventos");
        return mv;
    }

    @RequestMapping("/competidores")
    public ModelAndView peticionCompetidores(Authentication auth) {
        ModelAndView mv = new ModelAndView();
        Competidor compe=null;
        if(auth==null)
            mv.addObject("compe", "No se ha iniciado sesión");
        else {
            mv.addObject("compe", auth.getName());
        }

        mv.addObject("competidor", compe);

        List<Competidor> listaCompetidores = competidores.listaCompetidores();
        mv.addObject("listaCompetidores", listaCompetidores);

        mv.setViewName("competidores");
        return mv;
    }


    @RequestMapping("/evento/nuevo")
    public ModelAndView peticionNuevaTarea(Authentication auth) {
        ModelAndView mv = new ModelAndView();
        if(auth==null)
            mv.addObject("user", "No se ha iniciado sesión");
        else
            mv.addObject("user", auth.getName());
        Evento e = new Evento();
        mv.addObject("evento", e);
        mv.setViewName("nuevoevento");
        return mv;
    }

    @RequestMapping("/evento/editar")
    public ModelAndView peticioEventoEditar(Authentication auth, HttpServletRequest request) {
        Integer id = Integer.valueOf(request.getParameter("id"));
        Optional<Evento> eventoOptional = eventos.buscarEvento(id);
        Evento eve = eventoOptional.get();
        ModelAndView mv = new ModelAndView();if(auth==null)
            mv.addObject("user", "No se ha iniciado sesión");
        else
            mv.addObject("user", auth.getName());

        mv.addObject("evento", eve);
        mv.setViewName("editarevento");
        return mv;
    }

    @RequestMapping("/actualizar")
    public String peticionActualizar(Evento evento) {
        eventos.actualizarEvento(evento);
        return "redirect:/eventos";
    }

    /*@RequestMapping("/evento/eliminar/{id}")
    public String peticionEventoEliminar(@PathVariable("id") Integer id) {
        Evento ideve = eventos.buscarEvento(id).get();
        if (!ideve.getAsistencias().isEmpty()){
            List<Asistencia> la = ideve.getAsistencias();
            la.forEach(asistencia -> asistencias.eliminarAsistenciaById(asistencia.getIdAsistencia()));
        }
        eventos.eliminarEventoById(ideve.getIdEvento());
        return "redirect:/eventos";
    }*/

    @RequestMapping("/evento/eliminar/{id}")
    public String peticionEventoEliminar(@PathVariable("id") Integer id) {
        Optional<Evento> optionalEvento = eventos.buscarEvento(id);
        if (optionalEvento.isPresent()) {
            Evento ideve = optionalEvento.get();
            if (!ideve.getAsistencias().isEmpty()) {
                List<Asistencia> la = ideve.getAsistencias();
                la.forEach(asistencia -> asistencias.eliminarAsistenciaById(asistencia.getIdAsistencia()));
            }
            eventos.eliminarEventoById(ideve.getIdEvento());
        } else {
            // El evento no se encuentra, puedes devolver un error 404
            System.out.println("El evento no se encuentra, puedes devolver un error 404");
        }
        return "redirect:/eventos";
    }


    @RequestMapping("/actualizarCompetidor")
    public String peticionActualizarTarea(Competidor c, Authentication auth) {
        competidores.guardarCompetidor(c);
        return "redirect:/competidores";
    }



    /*

    @RequestMapping("/compe/tareas/nueva")
    public ModelAndView peticionNuevaTarea(Authentication auth) {
        ModelAndView mv = new ModelAndView();
        if(auth==null)
            mv.addObject("compe", "No se ha iniciado sesión");
        else
            mv.addObject("compe", auth.getName());
        Tarea t = new Tarea();
        mv.addObject("tarea", t);
        mv.setViewName("nuevatarea");
        return mv;
    }

    @RequestMapping("/admin/usuario/nuevo")
    public ModelAndView registro() {
        ModelAndView mv = new ModelAndView();
        Usuario c = new Usuario();
        mv.addObject("usuario", c);
        mv.setViewName("nuevousuario");
        return mv;
    }




    @RequestMapping("/guardar")
    public ModelAndView peticionGuardar(Usuario u, Authentication auth) {
        ModelAndView mv = new ModelAndView();
        System.out.println(u);
        String sincifrar = u.getPw();
        String cifrado = encoder.encode(sincifrar);
        u.setPw(cifrado);
        if(auth==null)
            mv.addObject("compe", "No se ha iniciado sesión");
        else
            mv.addObject("compe", auth.getName());
        Optional<Usuario> usuarioBuscado = usuarios.buscarUsuario(u.getNif());
        if (usuarioBuscado.isPresent()) {
            mv.addObject("sms", "El nif " + u.getNif() + " ya está utilizado");
        } else {
            usuarios.guardarUsuario(u);
            Rol rol = new Rol();
            rol.setUsuario(u);
            rol.setRol("USUARIO");
            roles.guardarRol(rol);
            mv.addObject("sms", "Usuario " + u.getNombre() + " registrado con éxito");
        }
        mv.setViewName("informa");
        return mv;
    }


    */
}
