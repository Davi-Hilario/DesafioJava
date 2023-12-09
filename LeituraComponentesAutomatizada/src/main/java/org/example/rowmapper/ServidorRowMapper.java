package org.example.rowmapper;

import org.example.business.Servidor;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ServidorRowMapper implements RowMapper<Servidor> {
    @Override
    public Servidor mapRow(ResultSet resultSet, int i) throws SQLException {
        return new Servidor(
                resultSet.getInt("id_servidor"),
                resultSet.getString("nome_servidor"),
                resultSet.getString("ipv6_servidor"),
                resultSet.getString("mac_address"),
                resultSet.getString("so_servidor")
        );
    }
}
