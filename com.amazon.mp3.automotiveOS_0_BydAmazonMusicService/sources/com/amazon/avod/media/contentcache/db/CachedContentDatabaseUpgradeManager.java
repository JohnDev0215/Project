package com.amazon.avod.media.contentcache.db;

import android.database.sqlite.SQLiteDatabase;
import com.amazon.avod.upgrade.SequentialUpgradeManager;

/* JADX INFO: loaded from: classes2.dex */
class CachedContentDatabaseUpgradeManager extends SequentialUpgradeManager<SQLiteDatabase> {
    public CachedContentDatabaseUpgradeManager() {
        register(1, new CachedContentDatabaseUpgradeActionFrom1To2());
        register(2, new CachedContentDatabaseUpgradeActionFrom2To3());
        register(3, new CachedContentDatabaseUpgradeActionFrom3To4());
        register(4, new CachedContentDatabaseUpgradeActionFrom4To5());
        register(5, new CachedContentDatabaseUpgradeActionFrom5To6());
        register(6, new CachedContentDatabaseUpgradeActionFrom6To7());
        register(7, new CachedContentDatabaseUpgradeActionFrom7To8());
        register(8, new CachedContentDatabaseUpgradeActionFrom8To9());
        register(9, new CachedContentDatabaseUpgradeActionFrom9To10());
        register(10, new CachedContentDatabaseUpgradeActionFrom10To11());
        register(11, new CachedContentDatabaseUpgradeActionFrom11To12());
        register(12, new CachedContentDatabaseUpgradeActionFrom12To13());
        register(13, new CachedContentDatabaseUpgradeActionFrom13To14());
        register(14, new CachedContentDatabaseUpgradeActionFrom14To15());
        register(15, new CachedContentDatabaseUpgradeActionFrom15To16());
        register(16, new CachedContentDatabaseUpgradeActionFrom16To17());
        register(17, new CachedContentDatabaseUpgradeActionFrom17To18());
        register(18, new CachedContentDatabaseUpgradeActionFrom18To19());
        register(19, new CachedContentDatabaseUpgradeActionFrom19To20());
        register(20, new CachedContentDatabaseUpgradeActionFrom20To21());
        register(21, new CachedContentDatabaseUpgradeActionFrom21To22());
    }
}
