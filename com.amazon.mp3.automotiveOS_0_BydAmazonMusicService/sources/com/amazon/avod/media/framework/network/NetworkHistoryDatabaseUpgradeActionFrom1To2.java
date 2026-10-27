package com.amazon.avod.media.framework.network;

import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import com.amazon.avod.db.util.DBSchemaUtils;
import com.amazon.avod.upgrade.UpgradeAction;
import com.amazon.avod.util.DLog;

/* JADX INFO: loaded from: classes2.dex */
class NetworkHistoryDatabaseUpgradeActionFrom1To2 implements UpgradeAction<SQLiteDatabase> {
    NetworkHistoryDatabaseUpgradeActionFrom1To2() {
    }

    @Override // com.amazon.avod.upgrade.UpgradeAction
    public void applyUpgrade(SQLiteDatabase sQLiteDatabase) {
        try {
            DLog.logf("ADDING TABLE COLUMN FOR heuristics_state");
            String strAddColumnStatement = DBSchemaUtils.addColumnStatement("access_point_table", "heuristics_state", "BLOB", "NULL");
            DLog.logf("Executing SQL statement: %s", strAddColumnStatement);
            sQLiteDatabase.execSQL(strAddColumnStatement);
        } catch (SQLException e) {
            DLog.exceptionf(e, "Adding 'heuristics_state' column", new Object[0]);
        }
    }

    public String toString() {
        return getClass().getSimpleName() + ": adding heuristics_state column";
    }
}
