/*
 * @author Steven Nguyen
 * @email: steven.686295@gmail.com
 * @date: 
 */

package com.mthree.superherosightings.daos.dbmappers;

import com.mthree.superherosightings.models.Organization;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

/**
 * 
 * @author Steven
 */
public class OrganizationMapper implements RowMapper {
    @Override
    public Organization mapRow(ResultSet resultSet, int index) throws SQLException {
        return new Organization(
            resultSet.getInt(1),
            resultSet.getString(2),
            resultSet.getString(3),
            resultSet.getInt(4)
        );
    }
}
