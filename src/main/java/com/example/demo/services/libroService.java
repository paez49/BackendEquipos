package com.example.demo.services;

import com.example.demo.domain.libro;
import com.example.demo.repository.libroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class libroService {
    @Autowired
    libroRepository libroRepository;

    public List<libro> obtenerLibros(){
        return libroRepository.findAll();
    }
    public void ingresarLibro(libro libro){
        libroRepository.save(libro);
    }
}
