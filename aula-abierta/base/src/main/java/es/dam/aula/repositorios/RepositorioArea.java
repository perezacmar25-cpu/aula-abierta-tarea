package es.dam.aula.repositorios;

import es.dam.aula.Area;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface RepositorioArea extends JpaRepository<Area,String> {

    @EntityGraph("Area.programa")
    Optional<Area>findById(String id);

    @EntityGraph("Area.programa")
    List<Area>findByNombre(String nombre);

}
