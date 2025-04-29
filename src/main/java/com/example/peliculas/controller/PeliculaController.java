package com.example.peliculas.controller;

import com.example.peliculas.model.Pelicula;
import com.example.peliculas.repository.PeliculaRepository;
import com.example.peliculas.service.PeliculaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/peliculas")
public class PeliculaController {

    @Autowired
    private PeliculaService peliculaService;
    @Autowired
    private PeliculaRepository peliculaRepository;

    // Obtener todas las películas
    @GetMapping
    public List<Pelicula> obtenerTodasLasPeliculas() {
        return peliculaService.obtenerTodasLasPeliculas();
    }

    // Obtener una película por su ID
    @GetMapping("/{id}")
    public ResponseEntity<Pelicula> obtenerPeliculaPorId(@PathVariable Long id) {
        Optional<Pelicula> pelicula = peliculaService.obtenerPeliculaPorId(id);
        return pelicula.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Crear una nueva película
    @PostMapping
    public ResponseEntity<Pelicula> crearPelicula(@RequestBody Pelicula pelicula) {
        Pelicula nuevaPelicula = peliculaService.crearPelicula(pelicula);
        return new ResponseEntity<>(nuevaPelicula, HttpStatus.CREATED);
    }

    // Actualizar una película existente
    @PutMapping("/{id}")
    public ResponseEntity<Pelicula> actualizarPelicula(@PathVariable Long id, @RequestBody Pelicula pelicula) {
        Optional<Pelicula> peliculaExistente = peliculaService.obtenerPeliculaPorId(id);
        if (peliculaExistente.isPresent()) {
            pelicula.setId(id);
            Pelicula peliculaActualizada = peliculaService.crearPelicula(pelicula);
            return ResponseEntity.ok(peliculaActualizada);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Eliminar una película
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarPelicula(@PathVariable Long id) {
        if (peliculaService.obtenerPeliculaPorId(id).isPresent()) {
            peliculaService.eliminarPelicula(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Listar películas por género
    @GetMapping("/genero/{idGenero}")
    public List<Pelicula> obtenerPeliculasPorGenero(@PathVariable Long idGenero) {
        return peliculaRepository.findByGeneroId(idGenero);
    }
}