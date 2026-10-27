package com.amazon.avod.userdownload.internal.database.upgrade;

import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import com.amazon.avod.db.util.DBSchemaUtils;
import com.amazon.avod.upgrade.UpgradeAction;
import com.amazon.avod.util.DLog;
import com.amazon.avod.util.Throwables2;
import com.google.common.base.Preconditions;
import java.util.Locale;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class DownloadsDBUpgradeActionFrom28To29 implements UpgradeAction<SQLiteDatabase> {
    @Override // com.amazon.avod.upgrade.UpgradeAction
    public void applyUpgrade(@Nonnull SQLiteDatabase sQLiteDatabase) {
        Preconditions.checkNotNull(sQLiteDatabase, "instance");
        try {
            String strAddColumnStatement = DBSchemaUtils.addColumnStatement("playback_download", "actual_runtime_ms", "INTEGER");
            DLog.logf("DWNLD DB Executing SQL statement: %s", strAddColumnStatement);
            sQLiteDatabase.execSQL(strAddColumnStatement);
        } catch (SQLException e) {
            DLog.exceptionf(e, "DWNLD DB Failed to add new column: %s", "actual_runtime_ms");
            Throwables2.propagateIfWeakMode("DWNLD Upgrade", e);
        }
    }

    public String toString() {
        return String.format(Locale.US, "%s; adding columns %s", getClass().getSimpleName(), "actual_runtime_ms");
    }
}
