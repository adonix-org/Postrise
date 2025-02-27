/*
 * Copyright (C) 2025 Ty Busby
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.adonix.postrise.security;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public abstract class PostgresRoleDAO {

    private PostgresRoleDAO() {
    }

    /**
     * PostgreSQL specific SQL to {@code SET ROLE} on the {@link Connection}
     */
    private static final String SQL_SET_ROLE = "SELECT set_config('ROLE', ?, false)";

    /**
     * Be aware: if {@code roleName} is {@code NULL}, the result is the same as
     * executing {@code RESET ROLE}.
     * 
     * @param connection
     * @param roleName
     * @throws SQLException
     */
    public static final void setRole(final Connection connection, final String roleName) throws SQLException {
        try (final PreparedStatement stmt = connection.prepareStatement(SQL_SET_ROLE)) {
            stmt.setString(1, roleName);
            stmt.execute();
        }
    }

    /**
     * PostgreSQL specific SQL to {@code RESET ROLE} on the {@link Connection}
     */
    private static final String SQL_RESET_ROLE = "RESET ROLE";

    public static final void resetRole(final Connection connection) throws SQLException {
        try (final Statement stmt = connection.createStatement()) {
            stmt.execute(SQL_RESET_ROLE);
        }
    }

    /**
     * PostgreSQL specific query to SELECT privileges for a ROLE from the
     * pg_roles TABLE.
     */
    private static final String SQL_SELECT_ROLE_PRIVILEGES = String.join(" ",
            "SELECT",
            "pg_roles.rolname,",
            "pg_roles.rolsuper,",
            "pg_roles.rolcanlogin,",
            "pg_roles.rolinherit,",
            "pg_roles.rolcreaterole,",
            "pg_roles.rolcreatedb,",
            "pg_roles.rolreplication,",
            "pg_roles.rolconnlimit",
            "FROM pg_roles",
            "WHERE pg_roles.rolname = ? LIMIT 1");

    /**
     * @param connection - an open {@link Connection } to a PostgreSQL database.
     * @param roleName   - the name of the ROLE to SELECT from the pg_roles TABLE.
     * @return a populated {@link PostgresRole} from the current database.
     * @throws SQLException
     * @see <a href=
     *      "https://www.postgresql.org/docs/current/view-pg-roles.html">pg_roles</a>
     */
    public static final PostgresRole getRole(final Connection connection, final String roleName)
            throws SQLException {
        try (final PreparedStatement stmt = connection.prepareStatement(SQL_SELECT_ROLE_PRIVILEGES)) {
            stmt.setString(1, roleName);
            try (final ResultSet rs = stmt.executeQuery()) {
                if (!rs.next()) {
                    throw new RoleSecurityException("role \"" + roleName + "\" does not exist");
                }
                return new PostgresRole()
                        .setRoleName(rs.getString(1))
                        .setSuperUser(rs.getBoolean(2))
                        .setLoginRole(rs.getBoolean(3))
                        .setInherit(rs.getBoolean(4))
                        .setCreateRole(rs.getBoolean(5))
                        .setCreateDbRole(rs.getBoolean(6))
                        .setReplicationRole(rs.getBoolean(7))
                        .setConnectionLimit(rs.getInt(8));
            }
        }
    }
}
