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

/**
 * A PostgreSQL {@link Server} implementation.
 */
public class PostgresServer extends PostriseServer {

    /**
     * The default PostgreSQL host name.
     */
    public static final String POSTGRES_DEFAULT_HOSTNAME = "localhost";

    /**
     * The default PostgreSQL port
     */
    public static final Integer POSTGRES_DEFAULT_PORT = 5432;

    /**
     * Construct a new {@link PostgresServer} instance.
     */
    public PostgresServer() {
        super();
    }

    @Override
    public String getHostName() {
        return POSTGRES_DEFAULT_HOSTNAME;
    }

    @Override
    public Integer getPort() {
        return POSTGRES_DEFAULT_PORT;
    }

    @Override
    protected PostgresDataSource createDataSource(final String databaseName) {
        return new PostgresDataSourceDefault(this, databaseName);
    }
}
