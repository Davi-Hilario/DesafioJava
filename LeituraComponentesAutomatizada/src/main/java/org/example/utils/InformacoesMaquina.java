package org.example.utils;

import com.github.britooo.looca.api.core.Looca;
import com.github.britooo.looca.api.group.rede.RedeInterface;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;

public abstract class InformacoesMaquina {
    private static final Looca looca = new Looca();
    public static String getNomeServidor() {

        StringBuilder output = new StringBuilder();
        Process p;
        try {

            String[] command = {"hostname"};
            p = Runtime.getRuntime().exec(command);
            p.waitFor();

            BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String line;

            while ((line = reader.readLine()) != null) {
                output.append(line).append("\n");
            }
        } catch (Exception e) {
            System.out.println("Erro ao executar comando: " + e.fillInStackTrace());
        }

        return output.toString();
    }

    public static String getMacAddressServidor() {

        String macAddress = "00:00:00:00:00:00";
        List<RedeInterface> interfacesRede = looca.getRede().getGrupoDeInterfaces().getInterfaces();

        for (RedeInterface redeInterface : interfacesRede) {
            if(redeInterface.getNome().startsWith("e")) {
                macAddress = redeInterface.getEnderecoMac();
                break;
            }
        }

        return macAddress;
    }

    public static String getIpv6Servidor() {

        String ipv6 = "0000:0:0:0:000:0000:0000:0000";
        List<RedeInterface> interfacesRede = looca.getRede().getGrupoDeInterfaces().getInterfaces();

        for (RedeInterface redeInterface : interfacesRede) {
            if(redeInterface.getEnderecoMac().equalsIgnoreCase(getMacAddressServidor())) {
                if (!redeInterface.getEnderecoIpv6().isEmpty()){
                    ipv6 = redeInterface.getEnderecoIpv6().get(0);
                    break;
                }
            }
        }

        return ipv6;
    }

    public static String getSoServidor() {
        return looca.getSistema().getSistemaOperacional();
    }

}
