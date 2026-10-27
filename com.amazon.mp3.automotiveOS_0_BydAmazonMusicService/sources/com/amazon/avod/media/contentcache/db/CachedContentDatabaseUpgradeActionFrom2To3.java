package com.amazon.avod.media.contentcache.db;

import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import com.amazon.avod.db.util.DBSchemaUtils;
import com.amazon.avod.upgrade.UpgradeAction;
import com.amazon.avod.util.DLog;

/* JADX INFO: loaded from: classes2.dex */
class CachedContentDatabaseUpgradeActionFrom2To3 implements UpgradeAction<SQLiteDatabase> {
    CachedContentDatabaseUpgradeActionFrom2To3() {
    }

    @Override // com.amazon.avod.upgrade.UpgradeAction
    public void applyUpgrade(SQLiteDatabase sQLiteDatabase) {
        try {
            String strAddColumnStatement = DBSchemaUtils.addColumnStatement("cached_content_table", "retry_attempts", "INTEGER");
            DLog.logf("Executing SQL statement: %s", strAddColumnStatement);
            sQLiteDatabase.execSQL(strAddColumnStatement);
        } catch (SQLException e) {
            DLog.exceptionf(e, "Adding new v3 column: 'retry_attempts'", new Object[0]);
        }
    }

    public String toString() {
        return getClass().getSimpleName() + ": adding 'retry_attempts' columns";
    }
}
