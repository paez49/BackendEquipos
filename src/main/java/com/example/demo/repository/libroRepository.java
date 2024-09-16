package com.example.demo.repository;

import com.example.demo.domain.libro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface libroRepository extends JpaRepository <libro, Long> {
}
