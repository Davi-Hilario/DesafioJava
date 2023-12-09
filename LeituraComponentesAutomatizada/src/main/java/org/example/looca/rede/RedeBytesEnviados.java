package org.example.looca.rede;

import com.github.britooo.looca.api.group.rede.RedeInterface;

public class RedeBytesEnviados extends Rede {
    @Override
    public Double executar() {
        Long sentBytes = (long) 0;

        for(RedeInterface i : lucas.getGrupoDeInterfaces().getInterfaces()) {
            if (!i.getEnderecoIpv4().isEmpty()) {
                sentBytes = i.getBytesEnviados();
                break;
            }
        }

        return sentBytes.doubleValue();
    }
}
