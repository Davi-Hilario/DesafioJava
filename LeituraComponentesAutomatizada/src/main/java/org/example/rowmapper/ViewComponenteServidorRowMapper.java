package org.example.rowmapper;

import org.example.business.ViewComponenteServidor;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ViewComponenteServidorRowMapper implements RowMapper<ViewComponenteServidor> {
    @Override
    public ViewComponenteServidor mapRow(ResultSet resultSet, int i) throws SQLException {
        return new ViewComponenteServidor(
                resultSet.getString("Servidor"),
                resultSet.getString("SO"),
                resultSet.getString("Mac-Address"),
                resultSet.getString("Tipo"),
                resultSet.getInt("IdComponenteServidor"),
                resultSet.getString("Componente"),
                resultSet.getString("Servidor")
        );
    }
}
