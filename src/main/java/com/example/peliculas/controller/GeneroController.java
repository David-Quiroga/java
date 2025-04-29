package com.example.peliculas.controller;

import com.example.peliculas.model.Genero;
import com.example.peliculas.service.GeneroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/generos")
public class GeneroController {

    @Autowired
    private GeneroService generoService;

    // Obtener todos los géneros
    @GetMapping
    public List<Genero> obtenerTodosLosGeneros() {
        return generoService.obtenerTodosLosGeneros();
    }

    // Obtener un género por su ID
    @GetMapping("/{id}")
    public ResponseEntity<Genero> obtenerGeneroPorId(@PathVariable Long id) {
        Optional<Genero> genero = generoService.obtenerGeneroPorId(id);
        return genero.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Crear un nuevo género
    @PostMapping
    public ResponseEntity<Genero> crearGenero(@RequestBody Genero genero) {
        Genero nuevoGenero = generoService.crearGenero(genero);
        return new ResponseEntity<>(nuevoGenero, HttpStatus.CREATED);
    }

    // Actualizar un género existente
    @PutMapping("/{id}")
    public ResponseEntity<Genero> actualizarGenero(@PathVariable Long id, @RequestBody Genero genero) {
        Optional<Genero> generoExistente = generoService.obtenerGeneroPorId(id);
        if (generoExistente.isPresent()) {
            genero.setId(id);
            Genero generoActualizado = generoService.crearGenero(genero);
            return ResponseEntity.ok(generoActualizado);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Eliminar un género
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarGenero(@PathVariable Long id) {
        if (generoService.obtenerGeneroPorId(id).isPresent()) {
            generoService.eliminarGenero(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}