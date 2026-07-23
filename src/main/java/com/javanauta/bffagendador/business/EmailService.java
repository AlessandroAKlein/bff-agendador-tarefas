package com.javanauta.bffagendador.business;


import com.javanauta.bffagendador.business.dto.TarefaDTO;
import com.javanauta.bffagendador.infrastructure.client.NotificacaoClient;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailService {

    private final NotificacaoClient client;





    public void enviaEmail(TarefaDTO dto){
        client.enviarEmail(dto);


    }






}
