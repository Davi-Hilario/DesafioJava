package org.example.enumerators;

import org.example.interfaces.Monitoravel;
import org.example.looca.cpu.CpuFrequencia;
import org.example.looca.cpu.CpuUso;
import org.example.looca.disco.DiscoUso;
import org.example.looca.memoria.MemoriaUso;
import org.example.looca.rede.RedeBytesEnviados;
import org.example.looca.rede.RedeBytesRecebidos;

public enum ComponentesMonitorados {

    USO_PORCENTAGEM_CPU("Uso da CPU (%)", new CpuUso()),
    FREQUENCIA_CPU("Frequência da CPU (Htz)", new CpuFrequencia()),
    USO_MEMORIA_PORCENTAGEM("Uso da Memória (%)", new MemoriaUso()),
    USO_DISCO_PORCENTAGEM( "Uso do Disco (%)", new DiscoUso()),
    BYTES_ENVIADOS_REDE( "Bytes Enviados", new RedeBytesEnviados()),
    BYTES_RECEBIDOS_REDE( "Bytes Recebidos", new RedeBytesRecebidos());

    private final String nome;
    private final Monitoravel metodo;

    ComponentesMonitorados(String nome, Monitoravel metodo) {
        this.nome = nome;
        this.metodo = metodo;
    }

    public String getNome() {
        return nome;
    }

    public Monitoravel getMetodo() {
        return metodo;
    }
}
