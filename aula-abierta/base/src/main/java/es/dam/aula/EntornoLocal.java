package es.dam.aula;

import es.dam.aula.interfaces.Entorno;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("local")
public class EntornoLocal implements Entorno {
    @Override
    public String modo() {
        return "LOCAL";
    }
}
