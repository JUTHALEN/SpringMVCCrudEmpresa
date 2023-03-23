package com.example.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.example.dao.DepartamentoDao;
import com.example.entities.Departamento;

public class DepartamentoServiceImpl implements DepartamentoService {

    @Autowired
    public DepartamentoDao departamentoDao;

    @Override
    public List<Departamento> findAll() {
        return departamentoDao.findAll();
    }

    @Override
    public Departamento findById(int idDepartamento) {
        return departamentoDao.findById(idDepartamento).get();
    }

    @Override
    @Transactional
    public void save(Departamento departamento) {
        departamentoDao.save(departamento);
    }

    @Override
    @Transactional
    public void deleteBy(int idDepartamento) {
        departamentoDao.deleteById(idDepartamento);
    }
    
}
