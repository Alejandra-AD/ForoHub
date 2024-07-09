package aluracursos.forohub.domain.post;
import jakarta.validation.constraints.NotNull;

public record DatosActualizarPost(
        @NotNull Long id,
        String titulo,
        String contenido,
        Tema tema
) {
}
