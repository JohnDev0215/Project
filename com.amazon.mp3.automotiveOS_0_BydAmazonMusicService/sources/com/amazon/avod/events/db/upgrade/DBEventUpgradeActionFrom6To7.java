package com.amazon.avod.events.db.upgrade;

import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import com.amazon.avod.db.util.DBSchemaUtils;
import com.amazon.avod.upgrade.UpgradeAction;
import com.amazon.avod.util.DLog;
import com.google.android.gms.common.Scopes;
import com.google.common.collect.ImmutableMap;
import java.util.Map;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class DBEventUpgradeActionFrom6To7 implements UpgradeAction<SQLiteDatabase> {
    private static final Map<String, String> NEW_COLUMNS = ImmutableMap.of("AccountId", "STRING", "ProfileId", "STRING");

    @Override // com.amazon.avod.upgrade.UpgradeAction
    public void applyUpgrade(SQLiteDatabase sQLiteDatabase) {
        try {
            for (Map.Entry<String, String> entry : NEW_COLUMNS.entrySet()) {
                executeSqlStatement(sQLiteDatabase, DBSchemaUtils.addColumnStatement("events", entry.getKey(), entry.getValue()));
            }
            executeSqlStatement(sQLiteDatabase, DBSchemaUtils.createIndexStatement("events", Scopes.PROFILE, "(type, accountid, profileid, asin, sessionid)"));
        } catch (SQLException e) {
            DLog.exceptionf(e, "Upgrade table", new Object[0]);
        }
    }

    private void executeSqlStatement(SQLiteDatabase sQLiteDatabase, @Nonnull String str) {
        DLog.logf("Executing SQL statement: %s", str);
        sQLiteDatabase.execSQL(str);
    }

    @Nonnull
    public String toString() {
        return getClass().getSimpleName() + ": Adding column \"account_id\" and \"profile_id\"";
    }
}
