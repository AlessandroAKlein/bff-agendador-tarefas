package com.javanauta.bffagendador.controller;


import com.javanauta.bffagendador.business.UsuarioService;
import com.javanauta.bffagendador.business.dto.EnderecoDTO;
import com.javanauta.bffagendador.business.dto.TelefoneDTO;
import com.javanauta.bffagendador.business.dto.UsuarioDTO;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuario")
@RequiredArgsConstructor
@Tag(name =" Usuário", description = "Cadastro e login e usuários")
public class UsuarioController {


    private final UsuarioService usuarioService;



    @PostMapping
    public ResponseEntity<UsuarioDTO> salvaUsuario(@RequestBody UsuarioDTO usuarioDTO) {
        return ResponseEntity.ok(usuarioService.salvaUsuario(usuarioDTO));
    }

    @PostMapping("/login")
    public String login(@RequestBody UsuarioDTO usuarioDTO) {
        return usuarioService.loginUsuario(usuarioDTO);

    }

     ///por email
    @GetMapping
    public ResponseEntity<UsuarioDTO> listarTodosUsuarios(@RequestParam("email") String email,
                                                          @RequestHeader("Authorization") String token) {
        return ResponseEntity.ok(usuarioService.buscarUserPorEmail(email, token));
    }



    @DeleteMapping("/{email}")
    public ResponseEntity<Void> deletarUsuarioPorEmail(@PathVariable String email,
                                                       @RequestHeader("Authorization") String token) {
        usuarioService.deletarUsuarioPorEmail(email, token);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    public ResponseEntity<UsuarioDTO> editarUsuario (@RequestBody UsuarioDTO usuarioDTO,
                                                     @RequestHeader("Authorization") String token){
        return ResponseEntity.ok(usuarioService.atualizarDadosUsuario(token,usuarioDTO));
    }

    @PutMapping("/endereco")
    public ResponseEntity<EnderecoDTO> editarEndereco (@RequestBody EnderecoDTO enderecoDTO,
                                                       @RequestParam("Id") Long id,
                                                        @RequestHeader("Authorization") String token){
        return ResponseEntity.ok(usuarioService.atualizaEndereco(id,enderecoDTO, token));
    }

    @PutMapping("/telefone")
    public ResponseEntity<TelefoneDTO> editarTelefone(@RequestBody TelefoneDTO telefoneDTO,
                                                      @RequestParam("Id") Long id,
                                                      @RequestHeader("Authorization") String token){
        return ResponseEntity.ok(usuarioService.atualizarTelefone(id, telefoneDTO, token));
    }

    @PostMapping("/endereco")
    public ResponseEntity<EnderecoDTO> adicionarEndereco(@RequestBody EnderecoDTO enderecoDTO,
                                                         @RequestHeader("Authorization") String token){
        return ResponseEntity.ok(usuarioService.adicionarEndereco(enderecoDTO,token));
    }

    @PostMapping("/telefone")
    public ResponseEntity<TelefoneDTO> adicionarTelefone(@RequestBody TelefoneDTO telefoneDTO,
                                                         @RequestHeader("Authorization") String token){
        return ResponseEntity.ok(usuarioService.adicionarTelefone(telefoneDTO,token));
    }






}
