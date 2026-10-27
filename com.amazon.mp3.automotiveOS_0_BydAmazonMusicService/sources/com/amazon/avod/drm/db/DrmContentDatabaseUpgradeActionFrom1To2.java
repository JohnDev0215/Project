package com.amazon.avod.drm.db;

import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import com.amazon.avod.db.util.DBSchemaUtils;
import com.amazon.avod.upgrade.UpgradeAction;
import com.amazon.avod.util.DLog;
import com.amazon.identity.auth.device.utils.AccountConstants;

/* JADX INFO: loaded from: classes2.dex */
@Deprecated
class DrmContentDatabaseUpgradeActionFrom1To2 implements UpgradeAction<SQLiteDatabase> {
    DrmContentDatabaseUpgradeActionFrom1To2() {
    }

    @Override // com.amazon.avod.upgrade.UpgradeAction
    public void applyUpgrade(SQLiteDatabase sQLiteDatabase) {
        try {
            addColumn(sQLiteDatabase, AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE, "TEXT");
            addColumn(sQLiteDatabase, "expiry", "INTEGER");
            addColumn(sQLiteDatabase, "view_hours", "INTEGER");
        } catch (SQLException e) {
            DLog.exceptionf(e, "Adding new V2 columns: 'type', 'expiry' and 'view_hours'", new Object[0]);
        }
    }

    private void addColumn(SQLiteDatabase sQLiteDatabase, String str, String str2) {
        String strAddColumnStatement = DBSchemaUtils.addColumnStatement("drm_table", str, str2, "0");
        DLog.logf("Executing SQL statement: %s", strAddColumnStatement);
        sQLiteDatabase.execSQL(strAddColumnStatement);
    }

    public String toString() {
        return getClass().getSimpleName() + ": adding 'type', 'expiry' and 'view_hours' columns";
    }
}
