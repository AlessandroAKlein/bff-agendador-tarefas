package com.javanauta.bffagendador.business;



import com.javanauta.bffagendador.business.dto.TarefaDTO;
import com.javanauta.bffagendador.infrastructure.TarefasClient;
import com.javanauta.bffagendador.infrastructure.enums.StatusNotificacaoEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TarefaService {

  private final TarefasClient client;


    public TarefaDTO gravarTarefa(String token, TarefaDTO dto) {
        return client.salvaTarefa(dto,token);
    }

    public List<TarefaDTO> buscaTarefasAgendadasPorPeriodo(LocalDateTime dataInicial, LocalDateTime dataFinal, String token) {
        return client.buscaListadeTarefasPorPeriodo(dataInicial,dataFinal,token);
    }

    public List<TarefaDTO> buscarporUser(String token) {
        return client.buscarPorEmail(token);
    }

    public void excluirPorId(String id, String token) {
        client.deletarPorId(id,token);
    }

    public TarefaDTO alteraStatus(StatusNotificacaoEnum status, String id, String token) {
        return client.alteraStatusNotificacao(status,id,token);

    }

    public TarefaDTO updateTarefas(TarefaDTO dto, String id, String token){
        return client.editarTarefa(dto,id,token);


    }






}
