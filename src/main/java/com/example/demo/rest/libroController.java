package com.example.demo.rest;

import com.example.demo.domain.libro;
import com.example.demo.services.libroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/libros")
public class libroController {

    @Autowired
    private libroService libroService;

    @PostMapping("/anadir")
    public void crearlibro(@RequestBody libro libro){
        libroService.ingresarLibro(libro);
    }

    @GetMapping("/lista")
    public List<libro> obtenerlibros(){
        return libroService.obtenerLibros();
    }
}
