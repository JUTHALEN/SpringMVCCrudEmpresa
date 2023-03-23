package com.example.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.example.dao.TelefonoDao;
import com.example.entities.Telefono;

public class TelefonoServiceImpl implements TelefonoService {

    @Autowired
    public TelefonoDao telefonoDao;

    @Override
    public List<Telefono> findAll() {
        return telefonoDao.findAll();
    }

    @Override
    public Telefono findById(int idTelefono) {
        return telefonoDao.findById(idTelefono).get();
    }

    @Override
    @Transactional
    public void save(Telefono telefono) {
        telefonoDao.save(telefono);
    }

    @Override
    @Transactional
    public void deleteBy(int idTelefono) {
        telefonoDao.deleteById(idTelefono);
    }

    
}
