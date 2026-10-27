package com.amazon.avod.userdownload.internal.database.upgrade;

import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import com.amazon.avod.db.util.DBSchemaUtils;
import com.amazon.avod.upgrade.UpgradeAction;
import com.amazon.avod.util.DLog;
import com.amazon.avod.util.Throwables2;

/* JADX INFO: loaded from: classes2.dex */
public class PlaybackDownloadsDBUpgradeActionFrom29To30 implements UpgradeAction<SQLiteDatabase> {
    @Override // com.amazon.avod.upgrade.UpgradeAction
    public void applyUpgrade(SQLiteDatabase sQLiteDatabase) {
        try {
            String strAddColumnStatement = DBSchemaUtils.addColumnStatement("playback_download", "cuepoint_playlist", "TEXT");
            DLog.logf("ServerInsertedPlaybackSession Playback DWNLD DB Executing SQL statement: %s", strAddColumnStatement);
            sQLiteDatabase.execSQL(strAddColumnStatement);
        } catch (SQLException e) {
            DLog.exceptionf(e, "ServerInsertedPlaybackSession DWNLD DB Adding new column: %s", "cuepoint_playlist");
            Throwables2.propagateIfWeakMode("DWNLD Upgrade", e);
        }
    }

    public String toString() {
        return getClass().getSimpleName() + ": adding column cuepoint_playlist";
    }
}
