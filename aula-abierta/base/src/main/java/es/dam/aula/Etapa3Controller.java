package es.dam.aula;

import es.dam.aula.servicios.LecturaServicio;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

@RestController
@RequiredArgsConstructor
public class Etapa3Controller {

    private final LecturaServicio lecturaServicio;

    @GetMapping("/lectura/curso/{id}")
    public Object cursoCompleto(@PathVariable String id, @RequestParam(defaultValue = "join") String plan) {
        return lecturaServicio.detalle(id,plan);
    }

    @GetMapping("/lectura/area/{id}")
    public Object programa(@PathVariable String id) {

        return lecturaServicio.programaPorId(id);
    }

    @GetMapping("/lectura/programas")
    public Object programas(@RequestParam String nombre) {
        return lecturaServicio.programasPorNombre(nombre);
    }


}
