package com.example.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.dao.CorreoDao;
import com.example.entities.Correo;
import com.example.entities.Empleado;

@Service
public class CorreoServiceImpl implements CorreoService {

    @Autowired
    private CorreoDao correoDao;

    @Override
    public List<Correo> findAll() {
        return correoDao.findAll();
    }

    @Override
    @Transactional
    public Correo findById(int idCorreo) {
        return correoDao.findById(idCorreo).get();
    }

    @Override
    @Transactional
    public void save(Correo correo) {
        correoDao.save(correo);
    }

    @Override
    @Transactional
    public void deleteBy(int idCorreo) {
        correoDao.deleteById(idCorreo);
    }

    @Override
    @Transactional
    public void deleteByEmpledo(Empleado empleado) {
        correoDao.deleteByEmpleado(empleado);
    }

    @Override
    @Transactional
    public List<Correo> findByEmpleado(Empleado empleado) {
        return correoDao.findByEmpleado(empleado);
    }

    
}
