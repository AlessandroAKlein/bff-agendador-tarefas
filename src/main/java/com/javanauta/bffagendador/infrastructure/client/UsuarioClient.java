package com.javanauta.bffagendador.infrastructure.client;


import com.javanauta.bffagendador.business.dto.EnderecoDTO;
import com.javanauta.bffagendador.business.dto.TelefoneDTO;
import com.javanauta.bffagendador.business.dto.UsuarioDTO;
import com.javanauta.bffagendador.business.dto.ViaCepDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "usuario", url = "${usuario.url}")
public interface UsuarioClient {

    @GetMapping
    UsuarioDTO listarTodosUsuarios(@RequestParam("email") String email,
                                   @RequestHeader("Authorization") String token);


    @PostMapping
    UsuarioDTO salvaUsuario(@RequestBody UsuarioDTO usuarioDTO);


    @PostMapping("/login")
    String login(@RequestBody UsuarioDTO usuarioDTO);


    @DeleteMapping("/{email}")
    void deletarUsuarioPorEmail(@PathVariable String email,
                                @RequestHeader("Authorization") String token);


    @PutMapping
    UsuarioDTO editarUsuario(@RequestBody UsuarioDTO usuarioDTO,
                             @RequestHeader("Authorization") String token);

    @PutMapping("/endereco")
    EnderecoDTO editarEndereco(@RequestBody EnderecoDTO enderecoDTO,
                               @RequestParam("Id") Long id,
                               @RequestHeader("Authorization") String token);

    @PutMapping("/telefone")
    TelefoneDTO telefoneDTOeditarTelefone(@RequestBody TelefoneDTO telefoneDTO,
                              @RequestParam("Id") Long id,
                              @RequestHeader("Authorization") String token);

    @PostMapping("/endereco")
    EnderecoDTO adicionarEndereco(@RequestBody EnderecoDTO enderecoDTO,
                                  @RequestHeader("Authorization") String token);

    @PostMapping("/telefone")
    TelefoneDTO adicionarTelefone(@RequestBody TelefoneDTO telefoneDTO,
                                  @RequestHeader("Authorization") String token);

    @GetMapping("/endereco/{cep}")
    ViaCepDTO buscarDadosCep(@PathVariable ("cep") String cep);

}
