package com.example.peliculas.service;

import com.example.peliculas.model.Pelicula;
import com.example.peliculas.repository.PeliculaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PeliculaService {

    @Autowired
    private PeliculaRepository peliculaRepository;

    public List<Pelicula> obtenerTodasLasPeliculas() {
        return peliculaRepository.findAll();
    }

    public Optional<Pelicula> obtenerPeliculaPorId(Long id) {
        return peliculaRepository.findById(id);
    }

    public Pelicula crearPelicula(Pelicula pelicula) {
        return peliculaRepository.save(pelicula);
    }

    public void eliminarPelicula(Long id) {
        peliculaRepository.deleteById(id);
    }
}