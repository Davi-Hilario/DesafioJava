package org.example.dao;

import org.example.business.Servidor;
import org.example.database.Conexao;
import org.example.rowmapper.ServidorRowMapper;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class ServidorDAO {

    private static final JdbcTemplate conexao = new Conexao().getConexaoDoBanco();

    public Servidor consultarServidor(String macAddress) {
        String query = "SELECT * FROM Desafio_Java.Servidor WHERE mac_address = ?;";
        try {
            return conexao.queryForObject(query, new ServidorRowMapper(), macAddress);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public void inserirServidor(String nomeServer, String ipv6Server, String macAddress, String soServer)
    {
        String query = "INSERT INTO Desafio_Java.Servidor VALUES (NULL,?,?,?,?);";
        conexao.update(query, nomeServer, ipv6Server, macAddress, soServer);
    }
}
