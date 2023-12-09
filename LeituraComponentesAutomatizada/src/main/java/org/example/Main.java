package org.example;


import org.example.leitura.Agendamento;
import org.example.leitura.Leitura;

public class Main {
    public static void main(String[] args) throws InterruptedException {

        Leitura app = new Leitura();
        app.identificarMaquina();

        Thread.sleep(5000);

        Agendamento agendamento = new Agendamento();
        agendamento.run();

    }
}