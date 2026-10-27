package com.amazon.avod.media.framework.network;

import android.database.sqlite.SQLiteDatabase;
import com.amazon.avod.upgrade.SequentialUpgradeManager;

/* JADX INFO: loaded from: classes2.dex */
class NetworkHistoryDatabaseUpgradeManager extends SequentialUpgradeManager<SQLiteDatabase> {
    public NetworkHistoryDatabaseUpgradeManager() {
        register(1, new NetworkHistoryDatabaseUpgradeActionFrom1To2());
        register(2, new NetworkHistoryDatabaseUpgradeActionFrom2To3());
    }
}
