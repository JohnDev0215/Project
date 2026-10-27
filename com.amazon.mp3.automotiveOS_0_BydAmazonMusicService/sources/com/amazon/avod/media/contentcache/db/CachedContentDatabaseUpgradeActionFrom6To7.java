package com.amazon.avod.media.contentcache.db;

import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import com.amazon.avod.db.util.DBSchemaUtils;
import com.amazon.avod.upgrade.UpgradeAction;
import com.amazon.avod.util.DLog;

/* JADX INFO: loaded from: classes2.dex */
class CachedContentDatabaseUpgradeActionFrom6To7 implements UpgradeAction<SQLiteDatabase> {
    CachedContentDatabaseUpgradeActionFrom6To7() {
    }

    @Override // com.amazon.avod.upgrade.UpgradeAction
    public void applyUpgrade(SQLiteDatabase sQLiteDatabase) {
        try {
            String strAddColumnStatement = DBSchemaUtils.addColumnStatement("cached_content_table", "download_time_seconds", "TEXT");
            DLog.logf("Executing SQL statement: %s", strAddColumnStatement);
            sQLiteDatabase.execSQL(strAddColumnStatement);
        } catch (SQLException e) {
            DLog.exceptionf(e, "Adding new v7 column: %s", "download_time_seconds");
        }
    }

    public String toString() {
        return getClass().getSimpleName() + ": adding column download_time_seconds";
    }
}
