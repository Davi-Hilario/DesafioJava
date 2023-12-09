package org.example.business;

public class Servidor {

    private Integer idServidor;
    private String nomeServidor;
    private String ipv6;
    private String macAddress;
    private String so;

    public Servidor(Integer idServidor, String nomeServidor, String ipv6, String macAddress, String so) {
        this.idServidor = idServidor;
        this.nomeServidor = nomeServidor;
        this.ipv6 = ipv6;
        this.macAddress = macAddress;
        this.so = so;
    }

    public Integer getIdServidor() {
        return idServidor;
    }

    public void setIdServidor(Integer idServidor) {
        this.idServidor = idServidor;
    }

    public String getNomeServidor() {
        return nomeServidor;
    }

    public void setNomeServidor(String nomeServidor) {
        this.nomeServidor = nomeServidor;
    }

    public String getIpv6() {
        return ipv6;
    }

    public void setIpv6(String ipv6) {
        this.ipv6 = ipv6;
    }

    public String getMacAddress() {
        return macAddress;
    }

    public void setMacAddress(String macAddress) {
        this.macAddress = macAddress;
    }

    public String getSo() {
        return so;
    }

    public void setSo(String so) {
        this.so = so;
    }

    @Override
    public String toString() {
        StringBuffer string = new StringBuffer();
        string.append("Nome do Servidor: ").append(nomeServidor).append('\n');
        string.append("Sistema Operacional: ").append(so).append('\n');
        string.append("Mac-Address: ").append(macAddress).append('\n');
        string.append("Ipv6: ").append(ipv6).append('\n');
        return string.toString();
    }
}
