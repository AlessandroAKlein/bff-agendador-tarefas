package com.javanauta.bffagendador.business;


import com.javanauta.bffagendador.business.dto.TarefaDTO;
import com.javanauta.bffagendador.business.dto.UsuarioDTO;
import com.javanauta.bffagendador.infrastructure.enums.StatusNotificacaoEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CronService {

    private final TarefaService tarefaService;
    private final EmailService emailService;
    private final UsuarioService usuarioService;

    @Value("${usuario.email}")
    private String email;
    @Value("${usuario.senha}")
    private String senha;


    @Scheduled(cron = "${cron.horario}")
    public void buscaTarefasProximaHora(){
        String token = login(converterParaRequestDTO());
        LocalDateTime horaFutura = LocalDateTime.now().plusHours(1);
        LocalDateTime horaFuturMaisCinco = LocalDateTime.now().plusHours(1).plusMinutes(5);
        List<TarefaDTO> listaTarefas = tarefaService.buscaTarefasAgendadasPorPeriodo(horaFutura, horaFuturMaisCinco, token);
        listaTarefas.forEach(tarefa -> {
            emailService.enviaEmail(tarefa);
            tarefaService.alteraStatus(StatusNotificacaoEnum.NOTIFICADO, tarefa.getId(), token);
        });


    }

    public String login (UsuarioDTO dto){
       return usuarioService.loginUsuario(dto);
    }

    public UsuarioDTO converterParaRequestDTO(){
        return UsuarioDTO.builder()
                .email(email)
                .senha(senha)
                .build();
    }

}
