/*
 * @author Steven Nguyen
 * @email: steven.686295@gmail.com
 * @date: 
 */

package com.mthree.superherosightings.daos.dbmappers;

import com.mthree.superherosightings.models.Power;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

/**
 * 
 * @author Steven
 */
public class PowerMapper implements RowMapper {
    @Override
    public Power mapRow(ResultSet resultSet, int index) throws SQLException {
        return new Power(
            resultSet.getInt(1),
            resultSet.getString(2)
        );
    }
}
