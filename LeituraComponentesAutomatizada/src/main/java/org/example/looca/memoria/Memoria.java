package org.example.looca.memoria;

import com.github.britooo.looca.api.core.Looca;
import org.example.interfaces.Monitoravel;

public abstract class Memoria implements Monitoravel {

    protected final com.github.britooo.looca.api.group.memoria.Memoria lucas = new Looca().getMemoria();

}
