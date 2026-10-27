package com.amazon.avod.userdownload.internal.database;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.amazon.avod.upgrade.SequentialUpgradeManager;
import com.amazon.avod.userdownload.internal.database.upgrade.DownloadsDBUpgradeActionFrom28To29;
import com.amazon.avod.userdownload.internal.database.upgrade.PlaybackDownloadsDBUpgradeActionFrom29To30;
import com.amazon.avod.util.DLog;
import com.google.common.base.Joiner;

/* JADX INFO: loaded from: classes2.dex */
public class PlaybackDownloadsDatabaseUpgradeManager extends SequentialUpgradeManager<SQLiteDatabase> {
    public PlaybackDownloadsDatabaseUpgradeManager() {
        register(28, new DownloadsDBUpgradeActionFrom28To29());
        register(29, new PlaybackDownloadsDBUpgradeActionFrom29To30());
    }

    @Override // com.amazon.avod.upgrade.SequentialUpgradeManager, com.amazon.avod.upgrade.UpgradeManager
    public void upgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        DLog.logf("Playback DWNLD_SDK DB Columns prior to upgrading the DownloadsDatabase: %s", getColumnNames(sQLiteDatabase));
        super.upgrade(sQLiteDatabase, i, i2);
    }

    private String getColumnNames(SQLiteDatabase sQLiteDatabase) {
        Cursor cursorRawQuery = sQLiteDatabase.rawQuery("SELECT * FROM playback_download", null);
        String strJoin = Joiner.on(',').join(cursorRawQuery.getColumnNames());
        cursorRawQuery.close();
        return strJoin;
    }
}
