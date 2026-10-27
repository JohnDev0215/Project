package com.amazon.avod.media.contentcache.db;

import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import com.amazon.avod.db.util.DBSchemaUtils;
import com.amazon.avod.upgrade.UpgradeAction;
import com.amazon.avod.util.DLog;
import com.google.common.collect.ImmutableList;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class CachedContentDatabaseUpgradeActionFrom14To15 implements UpgradeAction<SQLiteDatabase> {
    private static final List<String> NEW_COLUMNS = ImmutableList.of("content_type", "failover_map");

    @Override // com.amazon.avod.upgrade.UpgradeAction
    public void applyUpgrade(SQLiteDatabase sQLiteDatabase) {
        for (String str : NEW_COLUMNS) {
            try {
                String strAddColumnStatement = DBSchemaUtils.addColumnStatement("cached_content_table", str, "TEXT");
                DLog.logf("Executing SQL statement: %s", strAddColumnStatement);
                sQLiteDatabase.execSQL(strAddColumnStatement);
            } catch (SQLException e) {
                DLog.exceptionf(e, "Adding new v15 column: %s", str);
            }
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(": adding columns:");
        List<String> list = NEW_COLUMNS;
        sb.append(list.get(0));
        sb.append(" AND ");
        sb.append(list.get(1));
        return sb.toString();
    }
}
