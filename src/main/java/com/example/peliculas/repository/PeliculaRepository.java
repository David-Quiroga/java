package com.example.peliculas.repository;

import com.example.peliculas.model.Pelicula;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

//import org.springframework.stereotype.Repository;

//import java.util.List;

//@Repository
public interface PeliculaRepository extends JpaRepository<Pelicula, Long> {

    List<Pelicula> findByGeneroId(Long idGenero);

    // List<Pelicula> findByGenero(String genero);

    // List<Pelicula> findByCalificacionGreaterThanEqual(double calificacion);
}

// package com.ejemplo.peliculas.repository;

// import com.ejemplo.peliculas.model.Pelicula;
// import org.springframework.data.jpa.repository.JpaRepository;

// public interface PeliculaRepository extends JpaRepository<Pelicula, Long> {
// }
