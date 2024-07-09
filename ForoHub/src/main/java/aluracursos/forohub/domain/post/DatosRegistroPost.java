package aluracursos.forohub.domain.post;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


public record DatosRegistroPost(
        @NotBlank
        String titulo,
        @NotBlank
        String contenido,
        @NotNull
        Long autorId,
        @NotNull
        Tema tema
) {
}
