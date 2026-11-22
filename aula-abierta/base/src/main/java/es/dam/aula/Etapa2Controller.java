package es.dam.aula;

import es.dam.aula.servicios.CursoServicio;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;
import java.util.List;

@RestController
public class Etapa2Controller {

    private CursoServicio cursoServicio;

    @GetMapping("/catalogo/nivel")
    public Object porNivel(@RequestParam String nivel) {
        return cursoServicio.buscarPorNivel(nivel);
    }

    @GetMapping("/catalogo/titulo")
    public Object porTitulo(@RequestParam String texto) {
        return cursoServicio.buscarPorTexto(texto);
    }

    @GetMapping("/catalogo/prioridad")
    public Object prioridad(@RequestParam String preferente, @RequestParam String alternativo, @RequestParam int minimo) {
        return cursoServicio.buscarPorNivelYHoras(preferente,alternativo,minimo);
    }

    @GetMapping("/catalogo/resumen")
    public Object resumen(@RequestParam List<String> ids) {
        return cursoServicio.resumenCursos(ids);
    }
}
