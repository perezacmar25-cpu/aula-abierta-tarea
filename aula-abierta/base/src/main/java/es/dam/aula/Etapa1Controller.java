package es.dam.aula;

import es.dam.aula.servicios.CursoServicio;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

@RestController
@RequiredArgsConstructor
public class Etapa1Controller {

    private final CursoServicio cursoServicio;

    @PostMapping("/cursos")
    @ResponseStatus(HttpStatus.CREATED)
    public Object alta(@RequestBody Vistas.Alta alta) {

        return cursoServicio.crearCurso(alta);
    }

    @GetMapping("/cursos/{id}")
    public Object detalleBasico(@PathVariable String id) {
        return cursoServicio.buscar(id);
    }

    @DeleteMapping("/cursos/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void retirar(@PathVariable String id) {

        cursoServicio.retirar(id);
    }
}
