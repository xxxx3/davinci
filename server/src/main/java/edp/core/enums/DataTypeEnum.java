/*
 * <<
 * Davinci
 * ==
 * Copyright (C) 2016 - 2018 EDP
 * ==
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *       http://www.apache.org/licenses/LICENSE-2.0
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 * >>
 */

package edp.core.enums;

import edp.core.exception.SourceException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public enum DataTypeEnum {

    MYSQL("mysql", "mysql", "com.mysql.jdbc.Driver", "`"),

    ORACLE("oracle", "oracle", "oracle.jdbc.driver.OracleDriver", "\""),

    SQLSERVER("sqlserver", "sqlserver", "com.microsoft.sqlserver.jdbc.SQLServerDriver", "\""),

    H2("h2", "h2", "org.h2.Driver", null),

    PHOENIX("phoenix", "hbase phoenix", "org.apache.phoenix.jdbc.PhoenixDriver", null),

    MONGODB("mongodb", "mongodb", "mongodb.jdbc.MongoDriver", null),

    ELASTICSEARCH("sql4es", "elasticSearch", "nl.anchormen.sql4es.jdbc.ESDriver", null),

    PRESTO("presto", "presto", "com.facebook.presto.jdbc.PrestoDriver", null),

    MOONBOX("moonbox", "moonbox", "moonbox.jdbc.MbDriver", null),

    CASSANDRA("cassandra", "cassandra", "com.github.adejanovski.cassandra.jdbc.CassandraDriver", null),

    CLICKHOUSE("clickhouse", "clickhouse", "ru.yandex.clickhouse.ClickHouseDriver", null),

    KYLIN("kylin", "kylin", "org.apache.kylin.jdbc.Driver", null),

    VERTICA("vertica", "vertica", "com.vertica.jdbc.Driver", null),

    HANA("sap", "sap hana", "com.sap.db.jdbc.Driver", null),

    IMPALA("impala", "impala", "com.cloudera.impala.jdbc41.Driver", null);


    private String feature;
    private String desc;
    private String driver;
    private String kewordChar;

    DataTypeEnum(String feature, String desc, String driver, String kewordChar) {
        this.feature = feature;
        this.desc = desc;
        this.driver = driver;
        this.kewordChar = kewordChar;
    }

    public static DataTypeEnum urlOf(String jdbcUrl) throws SourceException {
        String url = jdbcUrl.toLowerCase();
        for (DataTypeEnum dataTypeEnum : values()) {
            if (url.indexOf(dataTypeEnum.feature) > -1) {
                try {
                    Class<?> aClass = Class.forName(dataTypeEnum.getDriver());
                    if (null == aClass) {
                        throw new SourceException("Unable to get driver instance for jdbcUrl: " + jdbcUrl);
                    }
                } catch (ClassNotFoundException e) {
                    throw new SourceException("Unable to get driver instance: " + jdbcUrl);
                }
                return dataTypeEnum;
            }
        }
        return null;
    }


    public String getFeature() {
        return feature;
    }

    public String getDesc() {
        return desc;
    }

    public String getDriver() {
        return driver;
    }

    public String getKewordChar() {
        return kewordChar;
    }
}
