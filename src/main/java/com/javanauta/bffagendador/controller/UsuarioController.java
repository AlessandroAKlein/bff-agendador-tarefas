package com.javanauta.bffagendador.controller;


import com.javanauta.bffagendador.business.UsuarioService;
import com.javanauta.bffagendador.business.dto.EnderecoDTO;
import com.javanauta.bffagendador.business.dto.TelefoneDTO;
import com.javanauta.bffagendador.business.dto.UsuarioDTO;
import com.javanauta.bffagendador.infrastructure.security.SecurityConfig;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Description;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuario")
@RequiredArgsConstructor
@Tag(name =" Usuário", description = "Cadastro e login e usuários")
@SecurityRequirement(name = SecurityConfig.SECURITY_SCHEME)
public class UsuarioController {


    private final UsuarioService usuarioService;



    @PostMapping
    @Operation(summary = "Salvar usuários", description = "Cria um novo usuário")
    @ApiResponse(responseCode = "200", description = "Usuário salvo com sucesso")
    @ApiResponse(responseCode = "500", description = "Erro servidor")
    @ApiResponse(responseCode = "409", description = "Usuário já cadastrado")
    public ResponseEntity<UsuarioDTO> salvaUsuario(@RequestBody UsuarioDTO usuarioDTO) {
        return ResponseEntity.ok(usuarioService.salvaUsuario(usuarioDTO));
    }

    @PostMapping("/login")
    @Operation(summary = "Login", description = "faz login no sistema")
    @ApiResponse(responseCode = "200", description = "logado com sucesso")
    @ApiResponse(responseCode = "401", description = "Credenciais invalidas")
    @ApiResponse(responseCode = "500", description = "Erro servidor")
    public String login(@RequestBody UsuarioDTO usuarioDTO) {
        return usuarioService.loginUsuario(usuarioDTO);

    }


    @GetMapping
    @Operation(summary = "Busca dados do usuário por Email", description = "Busca dados do usuário por email")
    @ApiResponse(responseCode = "200", description = "Usuário encontrado com sucesso")
    @ApiResponse(responseCode = "403", description = "Usuário não encontrado/cadastrado")
    @ApiResponse(responseCode = "500", description = "Erro servidor")
    @ApiResponse(responseCode = "401", description = "Credenciais invalidas")
    public ResponseEntity<UsuarioDTO> listarTodosUsuarios(@RequestParam("email") String email,
                                                          @RequestHeader(name ="Authorization", required = false) String token) {
        return ResponseEntity.ok(usuarioService.buscarUserPorEmail(email, token));
    }



    @DeleteMapping("/{email}")
    @Operation(summary = "Deletar usuários", description = "deleta usuário pelo email")
    @ApiResponse(responseCode = "200", description = "usuário deletado com sucesso")
    @ApiResponse(responseCode = "403", description = "usuário não encontrado")
    @ApiResponse(responseCode = "500", description = "Erro servidor")
    @ApiResponse(responseCode = "401", description = "Credenciais invalidas")
    public ResponseEntity<Void> deletarUsuarioPorEmail(@PathVariable String email,
                                                       @RequestHeader(name ="Authorization", required = false) String token) {
        usuarioService.deletarUsuarioPorEmail(email, token);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    @Operation(summary = "Edita dados dos usuários", description = "atualiza os dados do usuário")
    @ApiResponse(responseCode = "200", description = "usuário editado com sucesso")
    @ApiResponse(responseCode = "404", description = "usuário não encontrado")
    @ApiResponse(responseCode = "500", description = "Erro servidor")
    @ApiResponse(responseCode = "401", description = "Credenciais invalidas")
    public ResponseEntity<UsuarioDTO> editarUsuario (@RequestBody UsuarioDTO usuarioDTO,
                                                     @RequestHeader(name ="Authorization", required = false) String token){
        return ResponseEntity.ok(usuarioService.atualizarDadosUsuario(token,usuarioDTO));
    }

    @PutMapping("/endereco")
    @Operation(summary = "Edita endereço dos usuários", description = "atualiza os dados do endereço do usuário")
    @ApiResponse(responseCode = "200", description = "dados de endereço editado com sucesso")
    @ApiResponse(responseCode = "403", description = "usuário não encontrado")
    @ApiResponse(responseCode = "500", description = "Erro servidor")
    @ApiResponse(responseCode = "401", description = "Credenciais invalidas")
    public ResponseEntity<EnderecoDTO> editarEndereco (@RequestBody EnderecoDTO enderecoDTO,
                                                       @RequestParam("Id") Long id,
                                                        @RequestHeader(name ="Authorization", required = false) String token){
        return ResponseEntity.ok(usuarioService.atualizaEndereco(id,enderecoDTO, token));
    }

    @PutMapping("/telefone")
    @Operation(summary = "Edita telefone dos usuários", description = "atualiza os dados do telefone do usuário")
    @ApiResponse(responseCode = "200", description = "dados de telefone editado com sucesso")
    @ApiResponse(responseCode = "403", description = "usuário não encontrado")
    @ApiResponse(responseCode = "500", description = "Erro servidor")
    @ApiResponse(responseCode = "401", description = "Credenciais invalidas")
    public ResponseEntity<TelefoneDTO> editarTelefone(@RequestBody TelefoneDTO telefoneDTO,
                                                      @RequestParam("Id") Long id,
                                                      @RequestHeader(name ="Authorization", required = false) String token){
        return ResponseEntity.ok(usuarioService.atualizarTelefone(id, telefoneDTO, token));
    }

    @PostMapping("/endereco")
    @Operation(summary = "Adiciona endereço", description = "adiciona um endereço ao usuário")
    @ApiResponse(responseCode = "200", description = "endereço inserido com sucesso")
    @ApiResponse(responseCode = "403", description = "Usuário não encontrado")
    @ApiResponse(responseCode = "500", description = "Erro servidor")
    @ApiResponse(responseCode = "401", description = "Credenciais invalidas")
    public ResponseEntity<EnderecoDTO> adicionarEndereco(@RequestBody EnderecoDTO enderecoDTO,
                                                         @RequestHeader(name ="Authorization", required = false) String token){
        return ResponseEntity.ok(usuarioService.adicionarEndereco(enderecoDTO,token));
    }

    @PostMapping("/telefone")
    @Operation(summary = "Adiciona Telefone", description = "adiciona um telefone ao usuário")
    @ApiResponse(responseCode = "200", description = "telefone inserido com sucesso")
    @ApiResponse(responseCode = "403", description = "Usuário não encontrado")
    @ApiResponse(responseCode = "500", description = "Erro servidor")
    @ApiResponse(responseCode = "401", description = "Credenciais invalidas")
    public ResponseEntity<TelefoneDTO> adicionarTelefone(@RequestBody TelefoneDTO telefoneDTO,
                                                         @RequestHeader(name ="Authorization", required = false) String token){
        return ResponseEntity.ok(usuarioService.adicionarTelefone(telefoneDTO,token));
    }






}
