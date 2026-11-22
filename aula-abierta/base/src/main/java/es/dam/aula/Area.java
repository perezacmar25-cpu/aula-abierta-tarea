package es.dam.aula;

import jakarta.persistence.*;
import lombok.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Area {

    @Id
    private String id;
    private String nombre;

    @OneToMany(mappedBy = "area")
    @ToString.Exclude
    private Set<Curso> cursos = new HashSet<>();

    public void addCurso(Curso curso) {
        cursos.add(curso);
        curso.setArea(this);
    }

    public void removeCurso(Curso curso) {
        cursos.remove(curso);
        curso.setArea(null);
    }
}