package es.dam.aula;

import es.dam.aula.interfaces.Entorno;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
public class Etapa4Controller {

    private Entorno entorno;

    @GetMapping("/entorno")
    public Modo entorno() {
        return new Modo(entorno.modo());
    }
}
