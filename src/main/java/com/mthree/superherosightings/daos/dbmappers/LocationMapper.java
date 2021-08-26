/*
 * @author Steven Nguyen
 * @email: steven.686295@gmail.com
 * @date: 
 */

package com.mthree.superherosightings.daos.dbmappers;

import com.mthree.superherosightings.models.Location;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

/**
 * 
 * @author Steven
 */
public class LocationMapper implements RowMapper {
    @Override
    public Location mapRow(ResultSet resultSet, int index) throws SQLException {
        return new Location(
            resultSet.getInt(1),
            resultSet.getString(2),
            resultSet.getString(3),
            resultSet.getString(4),
            resultSet.getDouble(5),
            resultSet.getDouble(6)
        );
    }
}
