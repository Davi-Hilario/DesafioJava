package org.example.looca.cpu;

import com.github.britooo.looca.api.core.Looca;
import com.github.britooo.looca.api.group.processador.Processador;
import org.example.interfaces.Monitoravel;

public abstract class Cpu implements Monitoravel {
    protected final Processador lucas = new Looca().getProcessador();
}
