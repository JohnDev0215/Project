package com.amazon.avod.media.contentcache.db;

import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import com.amazon.avod.db.util.DBSchemaUtils;
import com.amazon.avod.upgrade.UpgradeAction;
import com.amazon.avod.util.DLog;

/* JADX INFO: loaded from: classes2.dex */
class CachedContentDatabaseUpgradeActionFrom8To9 implements UpgradeAction<SQLiteDatabase> {
    CachedContentDatabaseUpgradeActionFrom8To9() {
    }

    @Override // com.amazon.avod.upgrade.UpgradeAction
    public void applyUpgrade(SQLiteDatabase sQLiteDatabase) {
        try {
            String strAddColumnStatement = DBSchemaUtils.addColumnStatement("cached_content_table", "all_sources", "STRING");
            DLog.logf("Executing SQL statement: %s", strAddColumnStatement);
            sQLiteDatabase.execSQL(strAddColumnStatement);
        } catch (SQLException e) {
            DLog.exceptionf(e, "Adding new v9 column: %s", "all_sources");
        }
    }

    public String toString() {
        return getClass().getSimpleName() + ": adding column all_sources";
    }
}
