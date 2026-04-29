package com.javanauta.bffagendador.business;


import com.javanauta.bffagendador.business.dto.TarefaDTO;
import com.javanauta.bffagendador.infrastructure.NotificacaoClient;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.stereotype.Service;


import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;

@Service
@RequiredArgsConstructor
public class EmailService {

    private final NotificacaoClient client;





    public void enviaEmail(TarefaDTO dto){
        client.enviarEmail(dto);


    }






}
