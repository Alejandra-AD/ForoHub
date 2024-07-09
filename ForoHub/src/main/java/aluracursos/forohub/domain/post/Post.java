package aluracursos.forohub.domain.post;

import aluracursos.forohub.domain.usuarios.Usuario;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Table(name = "posts")
@Entity(name = "Post")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;
    private String contenido;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id")
    private Usuario autor;

    @Enumerated(EnumType.STRING)
    private Tema tema;

    private LocalDateTime fechaCreacion;
    private Boolean activo;

    public Post(String titulo, String contenido, Usuario autor, Tema tema) {
        this.titulo = titulo;
        this.contenido = contenido;
        this.autor = autor;
        this.tema = tema;
        this.fechaCreacion = LocalDateTime.now();
        this.activo = true;
    }

    public Post(DatosRegistroPost datosRegistroPost, Usuario autor) {
        this.titulo = datosRegistroPost.titulo();
        this.contenido = datosRegistroPost.contenido();
        this.autor = autor;
        this.tema = datosRegistroPost.tema();
        this.fechaCreacion = LocalDateTime.now();
        this.activo = true;
    }

    public void actualizarDatos(String titulo, String contenido, Tema tema) {
        this.titulo = titulo;
        this.contenido = contenido;
        this.tema = tema;
    }

    public void desactivarPost() {
        this.activo = false;
    }
}
