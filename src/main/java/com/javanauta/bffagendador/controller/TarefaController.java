package com.javanauta.bffagendador.controller;



import com.javanauta.bffagendador.business.TarefaService;
import com.javanauta.bffagendador.business.dto.TarefaDTO;
import com.javanauta.bffagendador.infrastructure.enums.StatusNotificacaoEnum;
import com.javanauta.bffagendador.infrastructure.security.SecurityConfig;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/tarefa")
@RequiredArgsConstructor
@Tag(name = "Tarefas", description = "Cadastra tarefas de usuários")
@SecurityRequirement(name = SecurityConfig.SECURITY_SCHEME)
public class TarefaController {

    private final TarefaService tarefaService;


    @PostMapping
    @Operation(summary = "Salva Tarefas de usuários", description = "Cria uma nova tarefa")
    @ApiResponse(responseCode = "200", description = "tarefa salva com sucesso")
    @ApiResponse(responseCode = "500", description = "Erro servidor")
    public ResponseEntity<TarefaDTO> salvaTarefa(@RequestBody TarefaDTO tarefaDTO,
                                                 @RequestHeader(name ="Authorization", required = false) String token) {
        return ResponseEntity.ok(tarefaService.gravarTarefa(token, tarefaDTO));
    }

    @GetMapping("/eventos")
    @Operation(summary = "Busca tarefas por periodo", description = "Busca tarefa por periodos ")
    @ApiResponse(responseCode = "200", description = "Tarefas encontradas com sucesso")
    @ApiResponse(responseCode = "500", description = "Erro servidor")
    public ResponseEntity<List<TarefaDTO>> buscaListadeTarefasPorPeriodo(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataInicial,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataFinal,
            @RequestHeader(name ="Authorization", required = false) String token

    ) {
        return ResponseEntity.ok(tarefaService.buscaTarefasAgendadasPorPeriodo(dataInicial, dataFinal, token));
    }

    @GetMapping("/user")
    @Operation(summary = "Busca lista de tarefas por determinado email usuário", description = "Busca tarefas por email")
    @ApiResponse(responseCode = "200", description = "tarefa encontrada com sucesso")
    @ApiResponse(responseCode = "500", description = "Erro servidor")
    @ApiResponse(responseCode = "403", description = "email não encotrado")
    @ApiResponse(responseCode = "401", description = "User não autorizado")
    public ResponseEntity<List<TarefaDTO>> buscarPorEmail(
            @RequestHeader(name ="Authorization", required = false) String token) {
        return ResponseEntity.ok(tarefaService.buscarporUser(token));
    }

    @DeleteMapping
    @Operation(summary = "deleta tarefas por id", description = "deleta tarefa por id")
    @ApiResponse(responseCode = "200", description = "tarefa deletada com sucesso")
    @ApiResponse(responseCode = "500", description = "Erro servidor")
    @ApiResponse(responseCode = "403", description = "Tarefa id não encontrada")
    @ApiResponse(responseCode = "401", description = "User não autorizado")
    public ResponseEntity<Void> deletarPorId(@RequestParam("id") String id,
                                             @RequestHeader(name ="Authorization", required = false) String token) {
        tarefaService.excluirPorId(id, token);
        return ResponseEntity.ok().build();
    }

    @PatchMapping
    @Operation(summary = "altera o status de notificação da tarefa", description = "altera staus de notificação de determinada tarefa")
    @ApiResponse(responseCode = "200", description = "tarefas alteradas sucesso")
    @ApiResponse(responseCode = "500", description = "Erro servidor")
    @ApiResponse(responseCode = "403", description = "Tarefa id não encontrada")
    @ApiResponse(responseCode = "401", description = "User não autorizado")
    public ResponseEntity<TarefaDTO> alteraStatusNotificacao(@RequestParam("status") StatusNotificacaoEnum status,
                                                             @RequestParam("id") String id,
                                                             @RequestHeader(name ="Authorization", required = false) String token) {
        return ResponseEntity.ok(tarefaService.alteraStatus(status, id, token));
    }

    @PutMapping
    @Operation(summary = "altera dados da tarefa", description = "altera dados de determinada tarefa, por id")
    @ApiResponse(responseCode = "200", description = "dados da tarefa alterada com sucesso")
    @ApiResponse(responseCode = "500", description = "Erro servidor")
    @ApiResponse(responseCode = "403", description = "Tarefa id não encontrada")
    @ApiResponse(responseCode = "401", description = "User não autorizado")
    public ResponseEntity<TarefaDTO> editarTarefa(@RequestBody TarefaDTO dto,
                                                  @RequestParam("id") String id,
                                                  @RequestHeader(name ="Authorization", required = false) String token){
        return ResponseEntity.ok(tarefaService.updateTarefas(dto,id, token));
    }



}
