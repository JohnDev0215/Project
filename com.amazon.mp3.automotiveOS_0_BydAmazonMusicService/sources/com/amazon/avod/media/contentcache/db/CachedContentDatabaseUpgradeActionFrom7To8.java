package com.amazon.avod.media.contentcache.db;

import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import com.amazon.avod.db.util.DBSchemaUtils;
import com.amazon.avod.upgrade.UpgradeAction;
import com.amazon.avod.util.DLog;
import okhttp3.HttpUrl;

/* JADX INFO: loaded from: classes2.dex */
class CachedContentDatabaseUpgradeActionFrom7To8 implements UpgradeAction<SQLiteDatabase> {
    CachedContentDatabaseUpgradeActionFrom7To8() {
    }

    @Override // com.amazon.avod.upgrade.UpgradeAction
    public void applyUpgrade(SQLiteDatabase sQLiteDatabase) {
        try {
            String strAddColumnStatement = DBSchemaUtils.addColumnStatement("cached_content_table", "audio_track_ids", "STRING", HttpUrl.PATH_SEGMENT_ENCODE_SET_URI);
            DLog.logf("Executing SQL statement: %s", strAddColumnStatement);
            sQLiteDatabase.execSQL(strAddColumnStatement);
        } catch (SQLException e) {
            DLog.exceptionf(e, "Adding new v8 column: %s", "audio_track_ids");
        }
    }

    public String toString() {
        return getClass().getSimpleName() + ": adding column audio_track_ids";
    }
}
