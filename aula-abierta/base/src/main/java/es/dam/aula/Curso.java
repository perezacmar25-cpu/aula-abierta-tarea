package es.dam.aula;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.SoftDelete;
import java.util.HashSet;
import java.util.Set;

@Entity
@SoftDelete(columnName = "retirado")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Curso {

    @Id
    private String id;
    private String titulo;
    private String nivel;
    private int horas;

    //Las consultas de los cursos no necesitan el área, por lo tanto lo dejamos en lazy para no cargar datos innecesarios.
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "area_id")
    @ToString.Exclude
    private Area area;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "curso_id")
    @ToString.Exclude
    private Set<Leccion> lecciones = new HashSet<>();
}