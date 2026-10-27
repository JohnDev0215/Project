package com.amazon.avod.drm.db;

import android.database.sqlite.SQLiteDatabase;
import com.amazon.avod.upgrade.SequentialUpgradeManager;

/* JADX INFO: loaded from: classes2.dex */
@Deprecated
class DrmContentDatabaseUpgradeManager extends SequentialUpgradeManager<SQLiteDatabase> {
    public DrmContentDatabaseUpgradeManager() {
        register(1, new DrmContentDatabaseUpgradeActionFrom1To2());
        register(2, new DrmContentDatabaseUpgradeActionFrom2To3());
    }
}
