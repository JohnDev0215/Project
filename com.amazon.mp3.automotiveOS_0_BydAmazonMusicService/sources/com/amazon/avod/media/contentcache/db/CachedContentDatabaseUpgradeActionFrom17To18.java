package com.amazon.avod.media.contentcache.db;

import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import com.amazon.avod.db.util.DBSchemaUtils;
import com.amazon.avod.upgrade.UpgradeAction;
import com.amazon.avod.util.DLog;

/* JADX INFO: loaded from: classes2.dex */
public class CachedContentDatabaseUpgradeActionFrom17To18 implements UpgradeAction<SQLiteDatabase> {
    @Override // com.amazon.avod.upgrade.UpgradeAction
    public void applyUpgrade(SQLiteDatabase sQLiteDatabase) {
        try {
            String strAddColumnStatement = DBSchemaUtils.addColumnStatement("cached_content_table", "playback_settings", "TEXT");
            DLog.logf("Executing SQL statement: %s", strAddColumnStatement);
            sQLiteDatabase.execSQL(strAddColumnStatement);
        } catch (SQLException e) {
            DLog.exceptionf(e, "Adding new v18 column: %s", "playback_settings");
        }
    }

    public String toString() {
        return getClass().getSimpleName() + ": adding column playback_settings";
    }
}
