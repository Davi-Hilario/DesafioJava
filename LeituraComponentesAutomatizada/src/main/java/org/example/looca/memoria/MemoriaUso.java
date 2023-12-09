package org.example.looca.memoria;

public class MemoriaUso extends Memoria {
    @Override
    public Double executar() {
        return (((double) super.lucas.getEmUso() / (double) super.lucas.getTotal()) * 100);
    }
}
