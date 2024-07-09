package aluracursos.forohub.domain.post;

import java.time.LocalDateTime;

public record DatosRespuestaPost(Long id, String titulo, Tema tema,String contenido, String autor, LocalDateTime fechaCreacion) {

    public DatosRespuestaPost(Post post) {
        this(post.getId(), post.getTitulo(),post.getTema(), post.getContenido(), post.getAutor().getLogin(),post.getFechaCreacion());
    }
}
