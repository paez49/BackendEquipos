package com.example.demo;

import com.example.demo.domain.libro;
import com.example.demo.repository.libroRepository;
import com.example.demo.services.libroService;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@SpringBootTest
public class libroServiceTest {

  @Autowired
  private libroRepository libroRepository;

  @Autowired
  private libroRepository libroService;

  @Captor
  private ArgumentCaptor<libro> libroCaptor;

  public void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  public void testCrearLibro() {
    int canti = (int) libroRepository.count();
    libro libro = new libro();
    libro.setReferencia("Libor213");
    libro.setAutor("Dummy autor");
    libro.setPrecio(10000L);
    libro.setUbicacion("Panamericana");

    libroRepository.save(libro);

    int cantiNueva = (int) libroRepository.count();
    assertTrue(cantiNueva == canti+1);
  }
  @Test
  public void testListaLibros(){
    libro libro = new libro();
    libro.setReferencia("Libor213");
    libro.setAutor("Dummy autor");
    libro.setPrecio(10000L);
    libro.setUbicacion("Panamericana");

    libroRepository.save(libro);
    List<libro> libroList = libroService.findAll();

    assertTrue(libroList.contains(libro));
  }
}