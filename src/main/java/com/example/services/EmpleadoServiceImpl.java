package com.example.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.example.dao.EmpleadoDao;
import com.example.entities.Empleado;

public class EmpleadoServiceImpl implements EmpleadoService{
    
    @Autowired
    public EmpleadoDao empleadoDao;

    @Override
    public List<Empleado> findAll() {
        return empleadoDao.findAll();
    }

    @Override
    public Empleado findById(int idEmpleado) {
        return empleadoDao.findById(idEmpleado).get();
    }

    @Override
    @Transactional
    public void save(Empleado empleado) {
        empleadoDao.save(empleado);
    }

    @Override
    @Transactional
    public void deleteBy(int idEmpleado) {
        empleadoDao.deleteById(idEmpleado);
    }

    @Override
    @Transactional
    public void delete(Empleado empleado) {
        empleadoDao.delete(empleado);
    }



}
