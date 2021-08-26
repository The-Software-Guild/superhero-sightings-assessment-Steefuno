/*
 * @author Steven Nguyen
 * @email: steven.686295@gmail.com
 * @date: 
 */

package com.mthree.superherosightings.daos.dbmappers;

import com.mthree.superherosightings.models.Affiliation;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

/**
 * 
 * @author Steven
 */
public class AffiliationMapper implements RowMapper {
    @Override
    public Affiliation mapRow(ResultSet resultSet, int index) throws SQLException {
        return new Affiliation(
            resultSet.getInt(1),
            resultSet.getInt(2),
            resultSet.getInt(3)
        );
    }
}
