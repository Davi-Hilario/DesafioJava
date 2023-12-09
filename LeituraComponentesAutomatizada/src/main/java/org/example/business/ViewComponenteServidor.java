package org.example.business;

public class ViewComponenteServidor {
    private String servidor;
    private String sistemaOperacional;
    private String macAddress;
    private String tipo;
    private Integer idComponenteServidor;
    private String componente;
    private String medida;

    public ViewComponenteServidor(String servidor, String sistemaOperacional, String macAddress, String tipo, Integer idComponenteServidor, String componente, String medida) {
        this.servidor = servidor;
        this.sistemaOperacional = sistemaOperacional;
        this.macAddress = macAddress;
        this.tipo = tipo;
        this.idComponenteServidor = idComponenteServidor;
        this.componente = componente;
        this.medida = medida;
    }

    public String getServidor() {
        return servidor;
    }

    public void setServidor(String servidor) {
        this.servidor = servidor;
    }

    public String getSistemaOperacional() {
        return sistemaOperacional;
    }

    public void setSistemaOperacional(String sistemaOperacional) {
        this.sistemaOperacional = sistemaOperacional;
    }

    public String getMacAddress() {
        return macAddress;
    }

    public void setMacAddress(String macAddress) {
        this.macAddress = macAddress;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Integer getIdComponenteServidor() {
        return idComponenteServidor;
    }

    public void setIdComponenteServidor(Integer idComponenteServidor) {
        this.idComponenteServidor = idComponenteServidor;
    }

    public String getComponente() {
        return componente;
    }

    public void setComponente(String componente) {
        this.componente = componente;
    }

    public String getMedida() {
        return medida;
    }

    public void setMedida(String medida) {
        this.medida = medida;
    }

    @Override
    public String toString() {
        StringBuffer string = new StringBuffer();
        string.append("Servidor: ").append(servidor).append('\n');
        string.append("Sistema Operacional: ").append(sistemaOperacional).append('\n');
        string.append("Mac-Address: ").append(macAddress).append('\n');
        string.append("Tipo: ").append(tipo).append('\n');
        string.append("Componente: ").append(componente).append('\n');
        string.append("Medida: ").append(medida).append('\n');
        return string.toString();
    }
}
