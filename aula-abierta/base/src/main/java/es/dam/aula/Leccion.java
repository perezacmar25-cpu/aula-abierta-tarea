package es.dam.aula;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Leccion {

    @Id
    private String id;
    private String titulo;
}