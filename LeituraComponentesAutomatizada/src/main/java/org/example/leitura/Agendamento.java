package org.example.leitura;

import java.util.Timer;
import java.util.TimerTask;

public class Agendamento implements Runnable{
    @Override
    public void run() {
        TimerTask tarefaRegistros = new TimerTask() {
            @Override
            public void run() {
                Leitura app = new Leitura();
                app.iniciarLeitura(app.obterExecutaveis());
            }
        };

        Timer timerRegistros = new Timer();
        timerRegistros.schedule(tarefaRegistros, 0, 5000);
    }
}
