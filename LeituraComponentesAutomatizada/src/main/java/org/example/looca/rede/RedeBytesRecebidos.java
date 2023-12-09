package org.example.looca.rede;

import com.github.britooo.looca.api.group.rede.RedeInterface;

public class RedeBytesRecebidos extends Rede{
    @Override
    public Double executar() {
        Long receivedBytes = (long) 0;

        for(RedeInterface i : lucas.getGrupoDeInterfaces().getInterfaces()) {
            if (!i.getEnderecoIpv4().isEmpty()) {
                receivedBytes = i.getBytesRecebidos();
                break;
            }
        }

        return receivedBytes.doubleValue();
    }
}
