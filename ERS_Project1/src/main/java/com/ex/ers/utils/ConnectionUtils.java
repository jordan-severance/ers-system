package com.ex.ers.utils;

import java.sql.Connection;
import java.sql.SQLException;

public abstract class ConnectionUtils {
    protected String url;
    protected String username;
    protected String password;
    protected String schema;

    public abstract Connection getConnection() throws SQLException;

    public String getDefaultSchema() {
        return this.schema;
    }

}
