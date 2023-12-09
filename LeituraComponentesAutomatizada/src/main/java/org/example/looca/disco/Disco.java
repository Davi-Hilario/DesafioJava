package org.example.looca.disco;

import com.github.britooo.looca.api.core.Looca;
import com.github.britooo.looca.api.group.discos.DiscoGrupo;
import org.example.interfaces.Monitoravel;

public abstract class Disco  implements Monitoravel {

    protected final DiscoGrupo lucas = new Looca().getGrupoDeDiscos();

}
