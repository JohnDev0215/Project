package com.amazon.avod.drm.db;

import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import com.amazon.avod.db.util.DBSchemaUtils;
import com.amazon.avod.identity.User;
import com.amazon.avod.media.framework.MediaSystemSharedDependencies;
import com.amazon.avod.upgrade.UpgradeAction;
import com.amazon.avod.util.DLog;
import com.google.common.base.Optional;
import java.util.Locale;

/* JADX INFO: loaded from: classes2.dex */
@Deprecated
class DrmContentDatabaseUpgradeActionFrom2To3 implements UpgradeAction<SQLiteDatabase> {
    DrmContentDatabaseUpgradeActionFrom2To3() {
    }

    @Override // com.amazon.avod.upgrade.UpgradeAction
    public void applyUpgrade(SQLiteDatabase sQLiteDatabase) {
        Optional<User> devicePrimaryUser = MediaSystemSharedDependencies.getInstance().getIdentityShim().getDevicePrimaryUser();
        String str = String.format(Locale.US, "INSERT INTO %s (asin,owner_id) SELECT asin, '%s' FROM %s ", "drm_owner_table", devicePrimaryUser.isPresent() ? devicePrimaryUser.get().getAccountId() : null, "drm_table");
        try {
            try {
                DLog.logf("upgrading DRM database from 2 -> 3");
                sQLiteDatabase.beginTransaction();
                sQLiteDatabase.execSQL(DBSchemaUtils.createTableStatement("drm_owner_table", DrmOwnerTable.COLUMNS));
                sQLiteDatabase.execSQL(str);
                sQLiteDatabase.setTransactionSuccessful();
                DLog.logf("upgrading DRM database from 2 -> 3: succeded");
                if (!sQLiteDatabase.inTransaction()) {
                    return;
                }
            } catch (SQLException e) {
                DLog.exceptionf(e, "Adding new table: drm_owner_table", new Object[0]);
                if (!sQLiteDatabase.inTransaction()) {
                    return;
                }
            }
            sQLiteDatabase.endTransaction();
        } catch (Throwable th) {
            if (sQLiteDatabase.inTransaction()) {
                sQLiteDatabase.endTransaction();
            }
            throw th;
        }
    }

    public String toString() {
        return getClass().getSimpleName() + ": adding table drm_owner_table";
    }
}
