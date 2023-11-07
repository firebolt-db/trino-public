/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.trino.plugin.firebolt;

import io.trino.plugin.jdbc.BaseJdbcConnectorSmokeTest;
import io.trino.testing.QueryRunner;
import io.trino.testing.TestingConnectorBehavior;

public class TestFireboltConnectorSmokeTest
        extends BaseJdbcConnectorSmokeTest
{
    @Override
    protected boolean hasBehavior(TestingConnectorBehavior connectorBehavior)
    {
        return switch (connectorBehavior) {
            case SUPPORTS_ADD_COLUMN,
                    SUPPORTS_COMMENT_ON_COLUMN,
                    SUPPORTS_CREATE_SCHEMA,
                    SUPPORTS_DELETE,
                    SUPPORTS_MERGE,
                    SUPPORTS_RENAME_COLUMN,
                    SUPPORTS_RENAME_TABLE,
                    SUPPORTS_UPDATE,
                    SUPPORTS_RENAME_TABLE_ACROSS_SCHEMAS -> false;
            default -> super.hasBehavior(connectorBehavior);
        };
    }

    @Override
    public void testCreateSchema() {
        // Firebolt does not support creating schemas
    }
    @Override
    protected QueryRunner createQueryRunner()
            throws Exception
    {
        return FireboltQueryRunner.createQueryRunner();
    }
}
