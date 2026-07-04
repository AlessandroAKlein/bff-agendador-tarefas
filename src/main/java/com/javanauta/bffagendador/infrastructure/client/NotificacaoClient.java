package com.javanauta.bffagendador.infrastructure.client;


import com.javanauta.bffagendador.business.dto.TarefaDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "notificacao", url = "${notificacao.url}")
public interface NotificacaoClient {

    @PostMapping
    void enviarEmail(@RequestBody TarefaDTO dto);





}
