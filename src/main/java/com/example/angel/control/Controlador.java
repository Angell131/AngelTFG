package com.example.angel.control;
import com.example.angel.jpa.Competidor;
import com.example.angel.jpa.Evento;
import com.example.angel.servicios.AsistenciaService;
import com.example.angel.servicios.CompetidorService;
import com.example.angel.servicios.EquipoService;
import com.example.angel.servicios.EventoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
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
        if(auth==null)
            mv.addObject("compe", "No se ha iniciado sesión");
        else
            mv.addObject("compe", auth.getName());
        mv.setViewName("index");
        String texto = "angel";
        String encriptado = encoder.encode(texto);
        System.out.println("Contraseña original: "+texto);
        System.out.println("Contraseña encriptado: "+encriptado);
        return mv;
    }
    //login para iniciar sesión
    @RequestMapping("login")
    public ModelAndView peticionSesion(Authentication auth) {
        ModelAndView mv = new ModelAndView();
        if(auth==null)
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
            Optional<Competidor> compeOpcional = competidores.buscarCompetidor(Integer.valueOf(auth.getName()));

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
            List<Evento> listaEventos = eventos.listaEventos();
            mv.addObject("listaEventos", listaEventos);
        }

        mv.addObject("competidor", compe);

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
            List<Competidor> listaCompetidores = competidores.listaCompetidores();
            mv.addObject("listaCompetidores", listaCompetidores);
        }

        mv.addObject("competidor", compe);

        mv.setViewName("competidores");
        return mv;
    }



    /*@RequestMapping("/compe/perfil")
    public ModelAndView peticionPerfil(Authentication auth) {
        ModelAndView mv = new ModelAndView();
        if(auth==null)
            mv.addObject("compe", "No se ha iniciado sesión");
        else
            mv.addObject("compe", auth.getName());
        Optional<Usuario> uOpcional = usuarios.buscarUsuario(auth.getName());
        Usuario u = uOpcional.get();
        mv.addObject("usuario", u);
        mv.setViewName("perfil");
        return mv;
    }

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

    @RequestMapping("/compe/tareas/listado")
    public ModelAndView peticionListadoTareas(Authentication auth) {
        ModelAndView mv = new ModelAndView();
        if(auth==null)
            mv.addObject("compe", "No se ha iniciado sesión");
        else
            mv.addObject("compe", auth.getName());
        List<Usuario> listaUsuarios = usuarios.listaUsuarios();
        mv.addObject("listaUsuarios", listaUsuarios);

        mv.setViewName("listadotareas");
        return mv;
    }



    @RequestMapping("/admin")
    public ModelAndView peticionAdministrador(Authentication auth) {
        ModelAndView mv = new ModelAndView();
        if(auth==null)
            mv.addObject("compe", "No se ha iniciado sesión");
        else
            mv.addObject("compe", auth.getName());

        List<Usuario> listaUsuarios = usuarios.listaUsuarios();
         mv.addObject("listaUsuarios", listaUsuarios);

        mv.setViewName("admin");
        return mv;
    }

    @RequestMapping("/admin/dashboard")
    public ModelAndView peticionDashboard(Authentication auth) {
        ModelAndView mv = new ModelAndView();
        if(auth==null)
            mv.addObject("compe", "No se ha iniciado sesión");
        else
            mv.addObject("compe", auth.getName());
        mv.setViewName("dashboard");
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


    @RequestMapping("/admin/usuario/mostar")
    public ModelAndView peticionUsuariosMostrar(Authentication auth) {
        ModelAndView mv = new ModelAndView();
        if(auth==null)
            mv.addObject("compe", "No se ha iniciado sesión");
        else
            mv.addObject("compe", auth.getName());
        mv.setViewName("mostrarusuarios");
        return mv;
    }

    @RequestMapping("/admin/usuario/editar")
    public ModelAndView peticioUsuariosEditar(Authentication auth, HttpServletRequest request) {
        String nif = request.getParameter("nif");
        Optional<Usuario> usuarioOpt = usuarios.buscarUsuario(nif);
        Usuario compe = usuarioOpt.get();
        ModelAndView mv = new ModelAndView();if(auth==null)
            mv.addObject("compe", "No se ha iniciado sesión");
        else
            mv.addObject("compe", auth.getName());
        mv.addObject("usuario", compe);
        mv.setViewName("editarusuarios");
        return mv;
    }

    @RequestMapping("/actualizar")
    public String peticionActualizar(Usuario u, Authentication auth) {
        usuarios.guardarUsuario(u);
        return "redirect:/admin";
    }
    @RequestMapping("/actualizarTarea")
    public String peticionActualizarTarea(Tarea t, Authentication auth) {
        tareas.guardarTarea(t);
        return "redirect:/compe/tareas/listado";
    }

    @RequestMapping("/eliminar/{nif}")
    public String peticionEliminar(@PathVariable("nif") String nif, Authentication auth) {
        Usuario u = usuarios.buscarUsuario(nif).get();

        if (!u.getRoles().isEmpty()){
            List<Rol> lr = u.getRoles();
            lr.forEach(rol -> roles.eliminarRolById(rol.getId()));
        }
        if (!u.getTareas().isEmpty()){
            List<Tarea> lt = u.getTareas();
            lt.forEach(tarea -> tareas.eliminarTareaById(tarea.getId()));
        }
        usuarios.eliminarUsuarioById(u.getNif());

        return "redirect:/admin";

    }*/
}
