package com.amplifyframework.datastore.storage.sqlite;

import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import androidx.annotation.NonNull;
import com.amplifyframework.AmplifyException;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.DataStoreException;
import com.amplifyframework.logging.Logger;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

/* JADX INFO: loaded from: classes5.dex */
final class SQLCommandProcessor {
    private static final Logger LOG = Amplify.Logging.forNamespace("amplify:aws-datastore");
    private final SQLiteDatabase sqliteDatabase;

    SQLCommandProcessor(@NonNull SQLiteDatabase sQLiteDatabase) {
        this.sqliteDatabase = sQLiteDatabase;
    }

    Cursor rawQuery(SqlCommand sqlCommand) throws DataStoreException {
        try {
            long jCurrentTimeMillis = System.currentTimeMillis();
            Cursor cursorRawQuery = this.sqliteDatabase.rawQuery(sqlCommand.sqlStatement(), sqlCommand.getBindingsAsArray());
            LOG.verbose("SQLCommandProcessor rawQuery in " + (System.currentTimeMillis() - jCurrentTimeMillis) + " ms inTransaction: " + this.sqliteDatabase.inTransaction() + " SQL: " + sqlCommand.sqlStatement());
            return cursorRawQuery;
        } catch (SQLException e) {
            throw dataStoreException(e, sqlCommand.sqlStatement());
        }
    }

    boolean executeExists(SqlCommand sqlCommand) throws DataStoreException {
        SQLiteStatement sQLiteStatementCompileStatement = this.sqliteDatabase.compileStatement(sqlCommand.sqlStatement());
        try {
            long jCurrentTimeMillis = System.currentTimeMillis();
            bindValuesToStatement(sQLiteStatementCompileStatement, sqlCommand.getBindings());
            boolean z = sQLiteStatementCompileStatement.simpleQueryForLong() > 0;
            LOG.verbose("SQLCommandProcessor executeExists in " + (System.currentTimeMillis() - jCurrentTimeMillis) + " ms inTransaction: " + this.sqliteDatabase.inTransaction() + " SQL: " + sqlCommand.sqlStatement());
            return z;
        } catch (SQLException e) {
            throw dataStoreException(e, sqlCommand.sqlStatement());
        }
    }

    void execute(SqlCommand sqlCommand) throws DataStoreException {
        SQLiteStatement sQLiteStatementCompileStatement = this.sqliteDatabase.compileStatement(sqlCommand.sqlStatement());
        try {
            long jCurrentTimeMillis = System.currentTimeMillis();
            bindValuesToStatement(sQLiteStatementCompileStatement, sqlCommand.getBindings());
            sQLiteStatementCompileStatement.execute();
            LOG.verbose("SQLCommandProcessor execute in " + (System.currentTimeMillis() - jCurrentTimeMillis) + " ms inTransaction: " + this.sqliteDatabase.inTransaction() + " SQL: " + sqlCommand.sqlStatement());
        } catch (SQLException e) {
            throw dataStoreException(e, sqlCommand.sqlStatement());
        }
    }

    private DataStoreException dataStoreException(SQLException sQLException, String str) {
        return new DataStoreException("Invalid SQL statement: " + str, sQLException, AmplifyException.REPORT_BUG_TO_AWS_SUGGESTION);
    }

    private void bindValuesToStatement(SQLiteStatement sQLiteStatement, List<Object> list) throws DataStoreException {
        sQLiteStatement.clearBindings();
        Objects.requireNonNull(list);
        Iterator<Object> it = list.iterator();
        int i = 1;
        while (it.hasNext()) {
            bindValueToStatement(sQLiteStatement, i, it.next());
            i++;
        }
    }

    private void bindValueToStatement(SQLiteStatement sQLiteStatement, int i, Object obj) throws DataStoreException {
        LOG.verbose("SQLCommandProcessor.bindValueToStatement(..., value = " + obj);
        if (obj == null) {
            sQLiteStatement.bindNull(i);
            return;
        }
        if (obj instanceof String) {
            sQLiteStatement.bindString(i, (String) obj);
            return;
        }
        if (obj instanceof Long) {
            sQLiteStatement.bindLong(i, ((Long) obj).longValue());
            return;
        }
        if (obj instanceof Integer) {
            sQLiteStatement.bindLong(i, ((Integer) obj).intValue());
            return;
        }
        if (obj instanceof Float) {
            sQLiteStatement.bindDouble(i, ((Float) obj).floatValue());
            return;
        }
        if (obj instanceof Double) {
            sQLiteStatement.bindDouble(i, ((Double) obj).doubleValue());
            return;
        }
        throw new DataStoreException("Failed to bind " + obj + " to SQL statement. " + obj.getClass().getSimpleName() + " is an unsupported type.", AmplifyException.REPORT_BUG_TO_AWS_SUGGESTION);
    }
}
