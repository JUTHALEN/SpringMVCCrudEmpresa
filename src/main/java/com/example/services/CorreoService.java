package com.example.services;

import java.util.List;

import com.example.entities.Correo;
import com.example.entities.Empleado;

public interface CorreoService {
    
    public List<Correo> findAll();
    public Correo findById(int idCorreo);
    public void save(Correo correo);
    public void deleteBy(int idCorreo);
    public void deleteByEmpledo(Empleado empleado);
    public List<Correo> findByEmpleado(Empleado empleado);
    
}
