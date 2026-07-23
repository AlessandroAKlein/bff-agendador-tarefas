package com.javanauta.bffagendador.business;

import com.javanauta.bffagendador.business.dto.EnderecoDTO;
import com.javanauta.bffagendador.business.dto.TelefoneDTO;
import com.javanauta.bffagendador.business.dto.UsuarioDTO;
import com.javanauta.bffagendador.business.dto.ViaCepDTO;
import com.javanauta.bffagendador.infrastructure.client.UsuarioClient;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioClient usuarioClient;


    public UsuarioDTO salvaUsuario(UsuarioDTO usuarioDTO) {
       return usuarioClient.salvaUsuario(usuarioDTO);
    }

    public String loginUsuario (UsuarioDTO dto){
        return usuarioClient.login(dto);
    }


    public UsuarioDTO buscarUserPorEmail(String email, String token) {
        return usuarioClient.listarTodosUsuarios(email,token);
    }


    public void deletarUsuarioPorEmail(String email,String token) {
        usuarioClient.deletarUsuarioPorEmail(email, token);
    }

    public UsuarioDTO atualizarDadosUsuario(String token, UsuarioDTO usuarioDTO) {
        return usuarioClient.editarUsuario(usuarioDTO,token);

    }

    public EnderecoDTO atualizaEndereco(Long idEndereco, EnderecoDTO enderecoDTO, String token) {
        return usuarioClient.editarEndereco(enderecoDTO,idEndereco, token);

    }

    public TelefoneDTO atualizarTelefone(Long idTelefone, TelefoneDTO telefoneDTO, String token) {
      return usuarioClient.telefoneDTOeditarTelefone(telefoneDTO,idTelefone,token);

    }


    public EnderecoDTO adicionarEndereco( EnderecoDTO enderecoDTO, String token) {
        return usuarioClient.adicionarEndereco(enderecoDTO,token);
    }

    public TelefoneDTO adicionarTelefone (TelefoneDTO dto, String token){
       return usuarioClient.adicionarTelefone(dto,token);
    }

    public ViaCepDTO buscarEnderecoPorCep (String cep){
        return usuarioClient.buscarDadosCep(cep);
    }






}
