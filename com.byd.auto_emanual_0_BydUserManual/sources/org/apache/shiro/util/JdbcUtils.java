package org.apache.shiro.util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: loaded from: classes.dex */
public class JdbcUtils {
    private static final Logger log = LoggerFactory.getLogger(JdbcUtils.class);

    private JdbcUtils() {
    }

    public static void closeConnection(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                if (log.isDebugEnabled()) {
                    log.debug("Could not close JDBC Connection", e);
                }
            } catch (Throwable th) {
                if (log.isDebugEnabled()) {
                    log.debug("Unexpected exception on closing JDBC Connection", th);
                }
            }
        }
    }

    public static void closeStatement(Statement statement) {
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                if (log.isDebugEnabled()) {
                    log.debug("Could not close JDBC Statement", e);
                }
            } catch (Throwable th) {
                if (log.isDebugEnabled()) {
                    log.debug("Unexpected exception on closing JDBC Statement", th);
                }
            }
        }
    }

    public static void closeResultSet(ResultSet resultSet) {
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                if (log.isDebugEnabled()) {
                    log.debug("Could not close JDBC ResultSet", e);
                }
            } catch (Throwable th) {
                if (log.isDebugEnabled()) {
                    log.debug("Unexpected exception on closing JDBC ResultSet", th);
                }
            }
        }
    }
}
