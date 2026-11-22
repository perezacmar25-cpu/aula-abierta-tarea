package es.dam.aula.repositorios;

import es.dam.aula.Curso;
import es.dam.aula.ResumenCurso;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface RepositorioCurso extends JpaRepository<Curso,String> {

    List<Curso> findByNivelOrderByIdAsc(String nivel);

    List<Curso>findByTituloContainingIgnoreCaseOrderByIdAsc(String texto);

    List<Curso> findByNivelOrNivelAndHorasIsGreaterThanEqualOrderById(String preferente, String alternativo,int minimo);

    @Query("SELECT NEW es.dam.aula.ResumenCurso(c.id, c.titulo, c.horas) FROM Curso c WHERE c.id in :ids ORDER BY c.id ")
    List<ResumenCurso>resumen(@Param("ids")List<String>ids);

    @Query("SELECT c FROM Curso c LEFT JOIN FETCH c.area LEFT JOIN FETCH c.lecciones WHERE c.id = :id")
    Optional<Curso> detalleJoin(@Param("id") String id);

    @EntityGraph(attributePaths = {"area","lecciones"})
    Optional<Curso> findDetalleById(String id);


    @EntityGraph(attributePaths = {"area","lecciones"})
    @Query("SELECT c FROM Curso c WHERE c.id = :id")
    Optional<Curso> detalleDeclarado(@Param("id") String id);








}
