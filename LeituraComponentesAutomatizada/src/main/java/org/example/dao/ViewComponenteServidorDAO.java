package org.example.dao;

import org.example.business.ViewComponenteServidor;
import org.example.database.Conexao;
import org.example.rowmapper.ViewComponenteServidorRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class ViewComponenteServidorDAO {

    private static final JdbcTemplate conexao = new Conexao().getConexaoDoBanco();
    public List<ViewComponenteServidor> consultarComponenteServidor(String macAddress) {
        String query = "SELECT * FROM Desafio_Java.View_Componentes_Servidores WHERE `Mac-Address` = ?;";
        return conexao.query(query, new ViewComponenteServidorRowMapper(), macAddress);
    }

    public ViewComponenteServidor coletarIdComponenteServidor(String tipo, String macAddress) {
        String query = "SELECT * FROM Desafio_Java.View_Componentes_Servidores WHERE `Tipo` = ? AND `Mac-Address` = ?;";
        return conexao.queryForObject(query, new ViewComponenteServidorRowMapper(), tipo, macAddress);
    }

}
