package org.example.rowmapper;

import org.example.business.ComponenteMedida;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ComponenteMedidaRowMapper implements RowMapper<ComponenteMedida> {
    @Override
    public ComponenteMedida mapRow(ResultSet resultSet, int i) throws SQLException {
        return new ComponenteMedida(
                resultSet.getInt("id_componente_medida"),
                resultSet.getString("nome_componente_medida"),
                resultSet.getString("tipo"),
                resultSet.getInt("valor_alerta_emergencia"),
                resultSet.getInt("valor_alerta_perigo"),
                resultSet.getInt("valor_alerta_prevencao"),
                resultSet.getInt("fk_componente"),
                resultSet.getInt("fk_medida")
        );
    }
}
