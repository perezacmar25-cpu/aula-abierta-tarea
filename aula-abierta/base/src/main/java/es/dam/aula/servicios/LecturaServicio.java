package es.dam.aula.servicios;


import es.dam.aula.Area;
import es.dam.aula.Curso;
import es.dam.aula.Vistas;
import es.dam.aula.repositorios.RepositorioArea;
import es.dam.aula.repositorios.RepositorioCurso;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class LecturaServicio {

    private final RepositorioCurso cursoRepository;
    private final RepositorioArea areaRepository;

    private Vistas.Detalle mostrarDetallesCurso(Curso curso){
        return new Vistas.Detalle(curso.getId(),curso.getArea().getId(),curso.getLecciones().stream()
                .map(leccion -> leccion.getId())
                .sorted()
                .toList());
    }

    public Vistas.Detalle detalle(String id, String plan) {
        Optional<Curso> curso;

        switch (plan) {
            case "join":
                curso = cursoRepository.detalleJoin(id);
                break;
            case "derivado":
                curso = cursoRepository.findDetalleById(id);
                break;
            case "declarado":
                curso = cursoRepository.detalleDeclarado(id);
                break;
            default:
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        return curso.map(this::mostrarDetallesCurso)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    private Vistas.Programa mostrarProgramaArea(Area area) {
        List<Vistas.Detalle> cursos = area.getCursos().stream()
                .sorted((a, b) -> a.getId().compareTo(b.getId()))
                .map(this::mostrarDetallesCurso)
                .toList();

        return new Vistas.Programa(area.getId(), cursos);
    }

    public Vistas.Programa programaPorId(String id){
        return areaRepository.findById(id)
                .map(this::mostrarProgramaArea)
                .orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND));

    }

    public List<Vistas.Programa> programasPorNombre(String nombre){
        return areaRepository.findByNombre(nombre)
                .stream()
                .map(this::mostrarProgramaArea)
                .toList();

    }
}
