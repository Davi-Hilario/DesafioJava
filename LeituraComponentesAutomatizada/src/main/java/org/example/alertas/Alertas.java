package org.example.alertas;

import com.slack.api.Slack;
import com.slack.api.methods.MethodsClient;
import com.slack.api.methods.SlackApiException;
import com.slack.api.methods.request.chat.ChatPostMessageRequest;
import com.slack.api.methods.response.chat.ChatPostMessageResponse;
import io.github.cdimascio.dotenv.Dotenv;
import org.example.business.ComponenteMedida;
import org.example.business.Servidor;
import org.example.dao.ComponenteMedidaDAO;
import org.example.enumerators.ComponentesMonitorados;

import java.io.IOException;

public class Alertas {

    private final Slack slack = Slack.getInstance();

    public void verificarMetricas(Double valor, ComponentesMonitorados item, Servidor server) throws SlackApiException, IOException {

        ComponenteMedidaDAO componenteMedidaDAO = new ComponenteMedidaDAO();
        ComponenteMedida componenteMedida = componenteMedidaDAO.coletarComponenteMedida(item.name());

        Integer metricaEmergencia = componenteMedida.getValorAlertaEmergencia();
        Integer metricaPerigo = componenteMedida.getValorAlertaPerigo();
        Integer metricaPrevencao = componenteMedida.getValorAlertaPrevencao();

        String nivelAlerta = "Alerta de ";
        boolean alerta = false;

        if (metricaEmergencia != 0 && metricaPerigo != 0 && metricaPrevencao != 0) {
            if (valor >= metricaEmergencia) {
                nivelAlerta += "Emergência ";
                alerta = true;
            } else if (valor >= metricaPerigo) {
                nivelAlerta += "Perigo ";
                alerta = true;
            } else if (valor >= metricaPrevencao) {
                nivelAlerta += "Prevenção ";
                alerta = true;
            }
        }

        nivelAlerta += "no Servidor %s: O %s ultrapassou %f".formatted(server.getNomeServidor(), item.getNome(), valor);

        if (alerta) {
            System.out.println(nivelAlerta);
            enviarMensagemSlack(nivelAlerta);
        }
    }

    public void enviarMensagemSlack(String alerta) throws SlackApiException, IOException {

        Dotenv dotenv = Dotenv.load();
        String token = dotenv.get("TOKEN_BOT_SLACK");
        MethodsClient methods = slack.methods(token);

        ChatPostMessageRequest request = ChatPostMessageRequest.builder()
                .channel("C069AMS9TFF")
                .text(":warning:  " + alerta)
                .build();

        ChatPostMessageResponse response = methods.chatPostMessage(request);

        if (response.isOk()) {
            System.out.println("\nMensagem enviada para o Slack!\n");
        }

    }
}
