package es.dam.aula.repositorios;

import es.dam.aula.Curso;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepositorioCurso extends JpaRepository<Curso,String> {

}
