package es.dam.aula.servicios;


import es.dam.aula.Area;
import es.dam.aula.Curso;
import es.dam.aula.ResumenCurso;
import es.dam.aula.Vistas;
import es.dam.aula.repositorios.RepositorioArea;
import es.dam.aula.repositorios.RepositorioCurso;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.server.ResponseStatusException;

@Service
public class CursoServicio {

    private RepositorioCurso cursoRepository;
    private RepositorioArea areaRepositorio;

    @Transactional
    public ResumenCurso crearCurso(Vistas.Alta alta) {
        Area area = areaRepositorio.findById(alta.areaId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        Curso curso = new Curso();
        curso.setId(alta.id());
        curso.setTitulo(alta.titulo());
        curso.setNivel(alta.nivel());
        curso.setHoras(alta.horas());
        area.addCurso(curso);
        cursoRepository.save(curso);
        return new ResumenCurso(curso.getId(), curso.getTitulo(), curso.getHoras());
    }

    @Transactional
    public ResumenCurso buscar(String id) {
        Curso curso = cursoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return new ResumenCurso(curso.getId(), curso.getTitulo(), curso.getHoras());
    }

    @Transactional
    public void retirar(String id){
        Curso curso = cursoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Curso no encontrado"));
                cursoRepository.delete(curso);
    }


}
