package com.javanauta.bffagendador.infrastructure;


import com.javanauta.bffagendador.business.dto.TarefaDTO;
import com.javanauta.bffagendador.infrastructure.enums.StatusNotificacaoEnum;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@FeignClient(name = "notificação", url = "${notificacao.url}")
public interface NotificacaoClient {

    @PostMapping
    void enviarEmail(@RequestBody TarefaDTO dto);





}
