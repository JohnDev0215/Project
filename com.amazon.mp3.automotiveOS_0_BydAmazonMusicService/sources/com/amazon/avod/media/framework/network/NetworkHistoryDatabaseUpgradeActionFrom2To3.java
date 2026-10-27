package com.amazon.avod.media.framework.network;

import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import com.amazon.avod.db.util.DBSchemaUtils;
import com.amazon.avod.upgrade.UpgradeAction;
import com.amazon.avod.util.DLog;

/* JADX INFO: loaded from: classes2.dex */
class NetworkHistoryDatabaseUpgradeActionFrom2To3 implements UpgradeAction<SQLiteDatabase> {
    NetworkHistoryDatabaseUpgradeActionFrom2To3() {
    }

    @Override // com.amazon.avod.upgrade.UpgradeAction
    public void applyUpgrade(SQLiteDatabase sQLiteDatabase) {
        try {
            DLog.logf("ADDING TABLE COLUMN FOR heuristic_bandwidth");
            String strAddColumnStatement = DBSchemaUtils.addColumnStatement("access_point_table", "heuristic_bandwidth", "INTEGER", "0");
            DLog.logf("Executing SQL statement: %s", strAddColumnStatement);
            sQLiteDatabase.execSQL(strAddColumnStatement);
            DLog.logf("ADDING TABLE COLUMN FOR heuristic_bandwidth_stdev");
            String strAddColumnStatement2 = DBSchemaUtils.addColumnStatement("access_point_table", "heuristic_bandwidth_stdev", "INTEGER", "0");
            DLog.logf("Executing SQL statement: %s", strAddColumnStatement2);
            sQLiteDatabase.execSQL(strAddColumnStatement2);
        } catch (SQLException e) {
            DLog.exceptionf(e, "Upgrade database from 2 to 3 failed", new Object[0]);
        }
    }

    public String toString() {
        return getClass().getSimpleName();
    }
}
