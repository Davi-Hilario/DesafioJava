package org.example.dao;

import org.example.business.ViewComponenteServidor;
import org.example.database.Conexao;
import org.springframework.jdbc.core.JdbcTemplate;

public class ComponenteServidorDAO {

    private static final JdbcTemplate conexao = new Conexao().getConexaoDoBanco();

    public void inserirComponenteServidor(Integer fkServidor, Integer fkComponenteMedida) {
        String query = "INSERT INTO Desafio_Java.Componente_Servidor VALUES (NULL, ?, ?);";
        conexao.update(query, fkServidor, fkComponenteMedida);
    }
}
