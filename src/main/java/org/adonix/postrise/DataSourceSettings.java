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

import org.adonix.postrise.security.RoleSecuritySettings;

/**
 * Defines the configuration settings required to establish a connection to a
 * PostgreSQL database. Implementations of this interface provide the necessary
 * parameters for configuring the data source.
 */
public interface DataSourceSettings extends ConnectionPoolSettings, ConnectionSettingsWrite, RoleSecuritySettings {
}
