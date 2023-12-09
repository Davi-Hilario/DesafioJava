package org.example.dao;

import org.example.business.ComponenteMedida;
import org.example.database.Conexao;
import org.example.rowmapper.ComponenteMedidaRowMapper;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

public class ComponenteMedidaDAO {
    private static final JdbcTemplate conexao = new Conexao().getConexaoDoBanco();

    public ComponenteMedida coletarComponenteMedida(String tipo) {
        String query = "SELECT * FROM Desafio_Java.Componente_Medida WHERE tipo = ?;";
        try {
            return conexao.queryForObject(query, new ComponenteMedidaRowMapper(), tipo);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

}
