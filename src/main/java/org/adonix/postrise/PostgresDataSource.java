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

package org.adonix.postrise;

import static org.adonix.postrise.security.RoleSecurityProvider.POSTGRES_DEFAULT_ROLE_SECURITY;

import org.adonix.postrise.security.RoleSecurityListener;

/**
 * PostgreSQL specific implementation of the PostriseDataSource.
 */
public abstract class PostgresDataSource extends PostriseDataSource {

    private static final String JDBC_POSTGRES_PREFIX = "jdbc:postgresql://";

    /**
     * Subclass instances are created by
     * {@link PostgresServer#createDataSource(String) createDataSource(String)}
     * 
     * @param server       - the parent of this data source.
     * @param databaseName - name of the PostgreSQL database (case-sensitive).
     */
    protected PostgresDataSource(final Server server, final String databaseName) {
        super(server, databaseName);
        addDataSourceProperty("tcpKeepAlive", "true");
    }

    @Override
    final String getJdbcUrl(final Server server) {
        return JDBC_POSTGRES_PREFIX + server.getHostName() + ":" + server.getPort() + "/" + getDatabaseName();
    }

    @Override
    protected RoleSecurityListener getDefaultRoleSecurity() {
        return POSTGRES_DEFAULT_ROLE_SECURITY;
    }
}
