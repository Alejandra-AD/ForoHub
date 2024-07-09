package aluracursos.forohub.controller;

import aluracursos.forohub.domain.post.*;
import aluracursos.forohub.domain.usuarios.Usuario;
import aluracursos.forohub.domain.usuarios.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/posts")
public class PostController {

    @Autowired
    private PostRepository postRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;

    @PostMapping
    public ResponseEntity<DatosRespuestaPost> crearPost(@RequestBody @Valid DatosRegistroPost datosRegistroPost,
                                                        UriComponentsBuilder uriComponentsBuilder) {
        Usuario autor = usuarioRepository.findById(datosRegistroPost.autorId())
                .orElseThrow(() -> new EntityNotFoundException("No se encontró el usuario con ID: " + datosRegistroPost.autorId()));

        Post post = new Post(datosRegistroPost, autor);
        postRepository.save(post);

        DatosRespuestaPost datosRespuestaPost = new DatosRespuestaPost(post.getId(), post.getTitulo(), post.getTema(),post.getContenido(),
                post.getAutor().getLogin(), post.getFechaCreacion());

        URI url = uriComponentsBuilder.path("/posts/{id}").buildAndExpand(post.getId()).toUri();
        return ResponseEntity.created(url).body(datosRespuestaPost);
    }

    @GetMapping
    public ResponseEntity<Page<DatosListarPost>> listarPosts(@PageableDefault(size = 10) Pageable paginacion) {
        return ResponseEntity.ok(postRepository.findAll(paginacion).map(DatosListarPost::new));
    }

    @PutMapping("/{id}")
    public ResponseEntity<DatosRespuestaPost> actualizarPost(@PathVariable Long id, @RequestBody @Valid DatosActualizarPost datosActualizarPost) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("No se encontró el post con ID: " + id));
        post.actualizarDatos(datosActualizarPost.titulo(), datosActualizarPost.contenido(), datosActualizarPost.tema());
        return ResponseEntity.ok(new DatosRespuestaPost(post.getId(), post.getTitulo(),post.getTema(), post.getContenido(),
                post.getAutor().getLogin(), post.getFechaCreacion()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarPost(@PathVariable Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("No se encontró el post con ID: " + id));

        postRepository.delete(post);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DatosRespuestaPost> obtenerPostPorId(@PathVariable Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("No se encontró el post con ID: " + id));

        return ResponseEntity.ok(new DatosRespuestaPost(post.getId(), post.getTitulo(), post.getTema(),post.getContenido(),
                post.getAutor().getLogin(), post.getFechaCreacion()));
    }
}
