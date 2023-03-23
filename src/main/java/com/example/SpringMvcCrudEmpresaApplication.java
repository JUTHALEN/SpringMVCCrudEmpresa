package com.example;

import java.time.LocalDate;
import java.time.Month;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.entities.Correo;
import com.example.entities.Departamento;
import com.example.entities.Empleado;
import com.example.entities.Telefono;
import com.example.entities.Empleado.Genero;
import com.example.services.CorreoService;
import com.example.services.DepartamentoService;
import com.example.services.EmpleadoService;
import com.example.services.TelefonoService;

@SpringBootApplication
public class SpringMvcCrudEmpresaApplication implements CommandLineRunner{

	@Autowired
	private CorreoService correoService;
	
	@Autowired
	private DepartamentoService departamentoService;

	@Autowired
	private EmpleadoService empleadoService;

	@Autowired
	private TelefonoService telefonoService;

	
	public static void main(String[] args) {
		SpringApplication.run(SpringMvcCrudEmpresaApplication.class, args);

		//Aquí podemos meter los datos de usuarios
	}

	@Override
	public void run(String... args) throws Exception {
		
		departamentoService.save(Departamento.builder()
		.id(1)
		.nombre("Informática")
		.build());

		departamentoService.save(Departamento.builder()
		.id(2)
		.nombre("RRHH")
		.build());

		departamentoService.save(Departamento.builder()
		.id(3)
		.nombre("Contabilidad")
		.build());

		departamentoService.save(Departamento.builder()
		.id(4)
		.nombre("Comercial")
		.build());
		
		empleadoService.save(Empleado.builder()
		.id(1)
		.nombre("Judith")
		.apellidos("Alende Martínez")
		.fechaAlta(LocalDate.of(2023 , Month.JANUARY, 23))
		.genero(Genero.MUJER)
		.departamento(departamentoService.findById(1))
		.build());

		empleadoService.save(Empleado.builder()
		.id(2)
		.nombre("Celia")
		.apellidos("Cava Ruiz")
		.fechaAlta(LocalDate.of(2023 , Month.JANUARY, 23))
		.genero(Genero.MUJER)
		.departamento(departamentoService.findById(2))
		.build());

		correoService.save(Correo.builder()
		.id(1)
		.email("judith.alende@hotmail.com")
		.empleado(empleadoService.findById(1))
		.build());

		correoService.save(Correo.builder()
		.id(2)
		.email("alende@gmail.com")
		.empleado(empleadoService.findById(1))
		.build());

		telefonoService.save(Telefono.builder()
		.id(1)
		.numero("603410362")
		.empleado(empleadoService.findById(1))
		.build());

		telefonoService.save(Telefono.builder()
		.id(2)
		.numero("978750337")
		.empleado(empleadoService.findById(1))
		.build());

		telefonoService.save(Telefono.builder()
		.id(3)
		.numero("678123456")
		.empleado(empleadoService.findById(2))
		.build());
	
	}

}
