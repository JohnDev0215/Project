package com.amazon.avod.media.events.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import com.amazon.avod.db.ADatabaseInstance;
import com.amazon.avod.upgrade.SequentialUpgradeManager;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class MediaEventsDatabaseNoOpUpgradeManager extends SequentialUpgradeManager<SQLiteDatabase> {
    public MediaEventsDatabaseNoOpUpgradeManager(@Nonnull Context context) {
    }

    public static class MediaEventsDatabaseV1 extends ADatabaseInstance {

        private static final class SingletonHolder {
            static final MediaEventsDatabaseV1 INSTANCE = new MediaEventsDatabaseV1();

            private SingletonHolder() {
            }
        }

        public static MediaEventsDatabaseV1 getInstance() {
            return SingletonHolder.INSTANCE;
        }

        private MediaEventsDatabaseV1() {
            super("mediaEvents", 1, ADatabaseInstance.Scope.GLOBAL);
            addTable(MediaEventsDatabase.MEDIA_EVENT_TABLE);
            addTable(MediaEventsDatabase.MEDIA_REPORT_TABLE);
        }
    }
}
