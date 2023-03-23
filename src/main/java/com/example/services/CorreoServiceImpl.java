package com.example.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.example.dao.CorreoDao;
import com.example.entities.Correo;

public class CorreoServiceImpl implements CorreoService {

    @Autowired
    public CorreoDao correoDao;

    @Override
    public List<Correo> findAll() {
        return correoDao.findAll();
    }

    @Override
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
    
    
}
