package com.example.controllers;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.entities.Correo;
import com.example.entities.Departamento;
import com.example.entities.Empleado;
import com.example.entities.Telefono;
import com.example.services.CorreoService;
import com.example.services.DepartamentoService;
import com.example.services.EmpleadoService;
import com.example.services.TelefonoService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping("/")
public class MainController {

    @Autowired
    private EmpleadoService empleadoService;

    @Autowired
    private DepartamentoService departamentoService;

    @Autowired
    private CorreoService correoService;

    @Autowired
    private TelefonoService telefonoService;

    private static final Logger LOG = Logger.getLogger("MainController");

    @GetMapping(value="/empleados")
    public ModelAndView empleados() {

        List<Empleado> empleados = empleadoService.findAll();

        ModelAndView mav = new ModelAndView("views/empleados");

        mav.addObject("empleados", empleados);
        
        return mav;
    }
    
    //Nuevo empleado
    @GetMapping(value="/empleados/nuevo")
    public ModelAndView nuevo() {

        List<Departamento> departamentos = departamentoService.findAll();

        Empleado empleado = new Empleado();

        ModelAndView mav = new ModelAndView("views/formNuevoEmpleado");

        mav.addObject("empleado", empleado);
        mav.addObject("departamentos", departamentos);
        
        return mav;
    }

    @PostMapping("altaModificacionEmpleado")
    public String altaEmpleado(@ModelAttribute Empleado empleado, @RequestParam("numTelefono") String telefonosRecibidos,
        @RequestParam(name = "listcorreos") String correosRecibidos) {
        
        LOG.info("Recibiendo telefonos: " + telefonosRecibidos);
        LOG.info("Recibiendo correos: " + correosRecibidos);

        empleadoService.save(empleado);

        List<String> listaNumTelefonos = null;

        if(telefonosRecibidos != null) {
            String[] arrayTelefonos = telefonosRecibidos.split(";"); 
            listaNumTelefonos = Arrays.asList(arrayTelefonos);

        } 
        if(listaNumTelefonos != null){
            telefonoService.deleteByEmpledo(empleado);
            listaNumTelefonos.stream().forEach(n ->{
                Telefono telefonoObject = Telefono
                .builder()
                .numero(n)
                .empleado(empleado)
                .build();   

            telefonoService.save(telefonoObject);
            });
        }

        List<String> listaCorreos = null;

        if(correosRecibidos != null) {
            String[] arrayCorreos = correosRecibidos.split(";"); 
            listaCorreos = Arrays.asList(arrayCorreos);
        }
        if(listaCorreos != null){
            correoService.deleteByEmpledo(empleado);
            listaCorreos.stream().forEach(e -> { 
                Correo correoObject = Correo
                    .builder()
                    .email(e)
                    .empleado(empleado)
                    .build();

            correoService.save(correoObject);
            });                
        }
        return "redirect:/empleados";
    }
    @GetMapping("/empleados/editar/{id}")
    public String formActualizarEmpleado(@PathVariable(name = "id") int idEmpleado, Model model){
          
        Empleado empleado = empleadoService.findById(idEmpleado);
  
        List<Telefono> todosTelefonos = telefonoService.findAll();
  
        List<Telefono> telefonosDelEmpleado = todosTelefonos.stream()
            .filter(t -> t.getEmpleado().getId() == idEmpleado)
            .collect(Collectors.toList());
          
        String NumTelefonos = telefonosDelEmpleado.stream()
            .map(t -> t.getNumero())
            .collect(Collectors.joining(";"));
        
        List<Correo> todosCorreos = correoService.findAll();
  
        List<Correo> correosDelEmpleado = todosCorreos.stream()
            .filter(t -> t.getEmpleado().getId() == idEmpleado)
            .collect(Collectors.toList());
              
        String listcorreos = correosDelEmpleado.stream()
            .map(t -> t.getEmail())
            .collect(Collectors.joining(";"));        
          
        List<Departamento> departamentos = departamentoService.findAll();            
                  
        model.addAttribute("empleado", empleado);
        model.addAttribute("telefonos", NumTelefonos);
        model.addAttribute("correos", listcorreos);
        model.addAttribute("departamentos", departamentos);
  
        return "views/formNuevoEmpleado";
    }

    @GetMapping("/empleados/eliminar/{id}")
    public String borrarEmpleado(@PathVariable(name = "id") int idEmpleado){
        
        empleadoService.delete(empleadoService.findById(idEmpleado));

        return "redirect:/empleados";
    }

    @GetMapping(value="/detalle/{id}")
    public String empleadoDetails(@PathVariable(name = "id") int id, Model model){

        Empleado empleado = empleadoService.findById(id);

        List<Telefono> telefonos = telefonoService.findByEmpleado(empleado); 
        List<Correo> correos = correoService.findByEmpleado(empleado);

        List<String> numTelefonos = telefonos.stream()
            .map(t -> t.getNumero())
            .toList();
        
        List<String> listCorreos = correos.stream()
            .map(c -> c.getEmail())
            .toList();
        
        model.addAttribute("empleado", empleado);
        model.addAttribute("telefonos", numTelefonos);
        model.addAttribute("correos", listCorreos);

        return "views/empleadoDetalles";
    }


    
}
