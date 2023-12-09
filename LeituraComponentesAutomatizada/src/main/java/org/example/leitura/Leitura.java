package org.example.leitura;

import com.slack.api.methods.SlackApiException;
import org.example.alertas.Alertas;
import org.example.business.ComponenteMedida;
import org.example.business.Servidor;
import org.example.business.ViewComponenteServidor;
import org.example.dao.*;
import org.example.enumerators.ComponentesMonitorados;
import org.example.utils.InformacoesMaquina;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Leitura {

    private final Scanner inputNumber = new Scanner(System.in);
    String macAddress = InformacoesMaquina.getMacAddressServidor();

    public void cadastrarServidor() {

        ServidorDAO servidorDAO = new ServidorDAO();
        System.out.println("Servidor não identificado, iniciando cadastro automático...");


        try {
            servidorDAO.inserirServidor(
                    InformacoesMaquina.getNomeServidor(),
                    InformacoesMaquina.getIpv6Servidor(),
                    InformacoesMaquina.getMacAddressServidor(),
                    InformacoesMaquina.getSoServidor()
            );
            System.out.println("Servidor cadastrado!");

        } catch (Exception e) {
            System.out.println("Erro ao cadastrar servidor: " + e.fillInStackTrace());
        }

        inserirComponentes(servidorDAO.consultarServidor(InformacoesMaquina.getMacAddressServidor()));

    }

    public void inserirComponentes(Servidor server) {

        List<ComponentesMonitorados> componentesMonitorados = new ArrayList<>(List.of(ComponentesMonitorados.values()));
        List<ComponentesMonitorados> componentesSelecionados = new ArrayList<>();
        Integer idServer = server.getIdServidor();

        System.out.println("\nSelecione os componentes que deseja monitorar:");

        int i;
        int resposta;

        do {
            for (i = 0; i < componentesMonitorados.size(); i++) {
                System.out.printf("%d - %s%n", i + 1, componentesMonitorados.get(i).getNome());
            }
            i++;

            System.out.printf("%d - Finalizar%n", i);
            resposta = inputNumber.nextInt();

            if (resposta == i) {
                System.out.println("Finalizando cadastro dos componentes!");
            } else if (resposta <= 0 || resposta > i) {
                System.out.println("Valor inválido digitado!");
            } else {
                componentesSelecionados.add(componentesMonitorados.get(resposta - 1));
                componentesMonitorados.remove(componentesMonitorados.get(resposta - 1));
            }

            System.out.println("\n");

        } while ((resposta <= 0 || resposta > i) || resposta != i);

        ComponenteServidorDAO componenteServidorDAO = new ComponenteServidorDAO();
        ComponenteMedidaDAO componenteMedidaDAO = new ComponenteMedidaDAO();

        try {
            componentesSelecionados.forEach(item -> {
                componenteServidorDAO.inserirComponenteServidor
                        (
                                idServer,
                                componenteMedidaDAO.coletarComponenteMedida(item.name()).getIdComponenteMedida()
                        );
            });
            System.out.println("Componentes cadastrados com sucesso!");

        } catch (Exception e) {
            System.out.println("Erro ao cadastrar componentes: " + e.fillInStackTrace());
        }
    }

    public void identificarMaquina() {
        ServidorDAO servidorDAO = new ServidorDAO();
        Leitura leitura = new Leitura();
        Servidor server = servidorDAO.consultarServidor(macAddress);

        if (server == null) {
            leitura.cadastrarServidor();
            server = servidorDAO.consultarServidor(macAddress);
        }

        System.out.println("Servidor localizado!\n" + server);
        System.out.println("Identificando componentes...");

        List<ComponentesMonitorados> executaveis = obterExecutaveis();
        System.out.println("Componentes encontrados!\nIniciando leitura...\n");

        iniciarLeitura(executaveis);

    }

    public List<ComponentesMonitorados> obterExecutaveis(){

        ViewComponenteServidorDAO viewComponenteServidorDAO = new ViewComponenteServidorDAO();
        List<ComponentesMonitorados> componentesMonitorados = new ArrayList<>(List.of(ComponentesMonitorados.values()));
        List<ViewComponenteServidor> listaComponentesServidor = viewComponenteServidorDAO.consultarComponenteServidor(macAddress);
        List<ComponentesMonitorados> monitoraveis = new ArrayList<>();

        for (ComponentesMonitorados comp : componentesMonitorados) {
            for (ViewComponenteServidor compServidor : listaComponentesServidor) {
                if (comp.name().equalsIgnoreCase(compServidor.getTipo())) {
                    monitoraveis.add(comp);
                    break;
                }
            }
        }

        return monitoraveis;
    }

    public void iniciarLeitura(List<ComponentesMonitorados> monitoraveis){
        LocalDateTime momento = LocalDateTime.now();
        Alertas alertas = new Alertas();

        Servidor server = new ServidorDAO().consultarServidor(this.macAddress);

        System.out.println(momento);
        monitoraveis.forEach(item -> {
            double valor = (double) Math.round(item.getMetodo().executar());
            System.out.println(item.getNome() + " -> " + valor);

            inserirRegistro(valor, item, momento);

            try {
                alertas.verificarMetricas(valor, item, server);
            } catch (SlackApiException | IOException e) {
                throw new RuntimeException(e);
            }

        });
        System.out.println("\n");

    }

    public void inserirRegistro(Double valor, ComponentesMonitorados monitoravel, LocalDateTime momento) {
        RegistroDAO registroDAO = new RegistroDAO();
        ViewComponenteServidorDAO viewComponenteServidorDAO = new ViewComponenteServidorDAO();

        Integer idComponenteServidor = viewComponenteServidorDAO.coletarIdComponenteServidor(
                monitoravel.name(), macAddress
        ).getIdComponenteServidor();

        registroDAO.inserirRegistro(idComponenteServidor, valor, momento);
    }

}
