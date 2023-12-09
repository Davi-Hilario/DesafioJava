package org.example.looca.rede;

import com.github.britooo.looca.api.core.Looca;
import org.example.interfaces.Monitoravel;

public abstract class Rede implements Monitoravel {

    protected static final com.github.britooo.looca.api.group.rede.Rede lucas = new Looca().getRede();

}
