/*
 * @author Steven Nguyen
 * @email: steven.686295@gmail.com
 * @date: 
 */

package com.mthree.superherosightings.daos.dbmappers;

import com.mthree.superherosightings.models.IdAndName;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

/**
 * 
 * @author Steven
 */
public class IdAndNameMapper implements RowMapper {
    @Override
    public IdAndName mapRow(ResultSet resultSet, int index) throws SQLException {
        return new IdAndName(
            resultSet.getInt(1),
            resultSet.getString(2)
        );
    }
}
