package com.amazon.avod.events.db.upgrade;

import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import com.amazon.avod.db.util.DBSchemaUtils;
import com.amazon.avod.upgrade.UpgradeAction;
import com.amazon.avod.util.DLog;
import com.google.common.collect.ImmutableMap;
import java.util.Map;

/* JADX INFO: loaded from: classes2.dex */
public class DBEventUpgradeActionFrom5To6 implements UpgradeAction<SQLiteDatabase> {
    private static final Map<String, String> NEW_COLUMNS = ImmutableMap.of("SessionId", "STRING");

    @Override // com.amazon.avod.upgrade.UpgradeAction
    public void applyUpgrade(SQLiteDatabase sQLiteDatabase) {
        try {
            for (Map.Entry<String, String> entry : NEW_COLUMNS.entrySet()) {
                String strAddColumnStatement = DBSchemaUtils.addColumnStatement("events", entry.getKey(), entry.getValue());
                DLog.logf("Executing SQL statement: %s", strAddColumnStatement);
                sQLiteDatabase.execSQL(strAddColumnStatement);
            }
        } catch (SQLException e) {
            DLog.exceptionf(e, "Adding table", new Object[0]);
        }
    }

    public String toString() {
        return getClass().getSimpleName() + ": Adding column \"session_id\"";
    }
}
