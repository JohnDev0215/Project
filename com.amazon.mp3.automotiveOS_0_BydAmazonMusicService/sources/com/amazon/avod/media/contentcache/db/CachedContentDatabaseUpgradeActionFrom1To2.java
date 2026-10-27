package com.amazon.avod.media.contentcache.db;

import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import com.amazon.avod.db.util.DBSchemaUtils;
import com.amazon.avod.upgrade.UpgradeAction;
import com.amazon.avod.util.DLog;
import com.google.common.base.Joiner;
import com.google.common.collect.ImmutableMap;
import java.util.Locale;
import java.util.Map;

/* JADX INFO: loaded from: classes2.dex */
class CachedContentDatabaseUpgradeActionFrom1To2 implements UpgradeAction<SQLiteDatabase> {
    private static final Map<String, String> NEW_COLUMNS = new ImmutableMap.Builder().put("is_trailer", "TEXT").put("target_duration", "INTEGER").build();

    CachedContentDatabaseUpgradeActionFrom1To2() {
    }

    @Override // com.amazon.avod.upgrade.UpgradeAction
    public void applyUpgrade(SQLiteDatabase sQLiteDatabase) {
        try {
            for (Map.Entry<String, String> entry : NEW_COLUMNS.entrySet()) {
                String strAddColumnStatement = DBSchemaUtils.addColumnStatement("cached_content_table", entry.getKey(), entry.getValue());
                DLog.logf("Executing SQL statement: %s", strAddColumnStatement);
                sQLiteDatabase.execSQL(strAddColumnStatement);
            }
        } catch (SQLException e) {
            DLog.exceptionf(e, toString(), new Object[0]);
        }
    }

    public String toString() {
        return String.format(Locale.US, "%s : Adding columns %s", getClass().getSimpleName(), Joiner.on(",").join(NEW_COLUMNS.keySet()));
    }
}
