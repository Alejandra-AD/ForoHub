package aluracursos.forohub.domain.post;

public record DatosListarPost(String titulo,Tema tema, String contenido, String autor) {

    public DatosListarPost(Post post) {
        this(post.getTitulo(),post.getTema(), post.getContenido(), post.getAutor().getLogin());
    }
}
