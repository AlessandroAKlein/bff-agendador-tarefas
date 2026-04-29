package com.javanauta.bffagendador.infrastructure;


import com.javanauta.bffagendador.business.dto.EnderecoDTO;
import com.javanauta.bffagendador.business.dto.TarefaDTO;
import com.javanauta.bffagendador.business.dto.TelefoneDTO;
import com.javanauta.bffagendador.business.dto.UsuarioDTO;
import com.javanauta.bffagendador.infrastructure.enums.StatusNotificacaoEnum;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@FeignClient(name = "agendador-tarefas", url = "${agendador-tarefas.url}")
public interface TarefasClient {

    @PostMapping
    TarefaDTO salvaTarefa(@RequestBody TarefaDTO tarefaDTO,
                          @RequestHeader("Authorization") String token);


    @GetMapping("/eventos")
    List<TarefaDTO> buscaListadeTarefasPorPeriodo(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataInicial,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataFinal,
            @RequestHeader("Authorization") String token);


    @GetMapping("/user")
    List<TarefaDTO> buscarPorEmail(
            @RequestHeader("Authorization") String token);

    @DeleteMapping
    void deletarPorId(@RequestParam("id") String id,
                      @RequestHeader("Authorization") String token);

    @PatchMapping
    TarefaDTO alteraStatusNotificacao(@RequestParam("status") StatusNotificacaoEnum status,
                                      @RequestParam("id") String id,
                                      @RequestHeader("Authorization") String token);


    @PutMapping
    TarefaDTO editarTarefa(@RequestBody TarefaDTO dto,
                           @RequestParam("id") String id,
                           @RequestHeader("Authorization") String token);





}
