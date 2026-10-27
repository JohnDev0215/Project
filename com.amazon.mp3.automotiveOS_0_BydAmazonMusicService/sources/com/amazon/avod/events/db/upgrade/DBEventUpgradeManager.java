package com.amazon.avod.events.db.upgrade;

import android.database.sqlite.SQLiteDatabase;
import com.amazon.avod.upgrade.SequentialUpgradeManager;

/* JADX INFO: loaded from: classes2.dex */
public class DBEventUpgradeManager extends SequentialUpgradeManager<SQLiteDatabase> {
    public DBEventUpgradeManager() {
        register(5, new DBEventUpgradeActionFrom5To6());
        register(6, new DBEventUpgradeActionFrom6To7());
    }
}
