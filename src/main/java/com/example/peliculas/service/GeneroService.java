package com.example.peliculas.service;

import com.example.peliculas.model.Genero;
import com.example.peliculas.repository.GeneroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GeneroService {
    @Autowired
    private GeneroRepository generoRepository;

    public List<Genero> obtenerTodosLosGeneros() {
        return generoRepository.findAll();
    }

    public Optional<Genero> obtenerGeneroPorId(Long id) {
        return generoRepository.findById(id);
    }

    public Genero crearGenero(Genero genero) {
        return generoRepository.save(genero);
    }

    public void eliminarGenero(Long id) {
        generoRepository.deleteById(id);
    }
}
