package es.dam.aula.repositorios;

import es.dam.aula.Curso;
import es.dam.aula.ResumenCurso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RepositorioCurso extends JpaRepository<Curso,String> {

    List<Curso> findByNivelOrderByIdAsc(String nivel);

    List<Curso>findByTituloContainingIgnoreCaseOrderByIdAsc(String texto);

    List<Curso> findByNivelOrNivelAndHorasIsGreatherThanEqualOrderById(String preferente, String alternativo,int minimo);

    @Query("SELECT NEW es.dam.aula.ResumenCurso(c.id, c.titulo, c.horas)FROM Curso c WHERE c.id in :ids ORDER BY c.id ")
    List<ResumenCurso>resumen(@Param("ids")List<String>ids);

}
