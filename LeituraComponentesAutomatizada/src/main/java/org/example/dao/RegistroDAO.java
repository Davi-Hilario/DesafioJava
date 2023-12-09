package org.example.dao;

import org.example.database.Conexao;
import org.springframework.jdbc.core.JdbcTemplate;

import java.time.LocalDateTime;

public class RegistroDAO {

    private static final JdbcTemplate conexao = new Conexao().getConexaoDoBanco();

    public void inserirRegistro(Integer fkComponenteServidor, Double valorRegistro, LocalDateTime momentoRegistro) {
        String query = "INSERT INTO Desafio_Java.Registro VALUES (NULL, ?, ?, ?);";
        conexao.update(query, fkComponenteServidor, valorRegistro, momentoRegistro);
    }
}
