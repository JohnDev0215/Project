package com.byd.card.db;

import android.content.Context;
import android.util.Log;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;
import com.android.systemui.SystemUIApplication;

/* JADX INFO: loaded from: classes.dex */
public abstract class CardDatabase extends RoomDatabase {
    public static final String DB_NAME = "card.db";
    private static final Migration MIGRATION_3_4 = new Migration(3, 4) { // from class: com.byd.card.db.CardDatabase.1
        @Override // androidx.room.migration.Migration
        public void migrate(SupportSQLiteDatabase database) {
            Log.w(CardDatabase.TAG, "migrate start!");
            database.execSQL("CREATE TABLE IF NOT EXISTS `shortcut_info` (`shortcutId` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `identify` TEXT, `title` TEXT, `origin` TEXT, `pinyin` TEXT, `icon` BLOB, `intent` TEXT, `pGearFlag` INTEGER NOT NULL, `recent` INTEGER NOT NULL, `recentTime` INTEGER NOT NULL, `count` INTEGER NOT NULL, `reserve1` TEXT, `reserve2` TEXT, `reserve3` TEXT)");
            database.execSQL("CREATE UNIQUE INDEX IF NOT EXISTS `index_shortcut_info_identify_origin` ON `shortcut_info` (`identify`, `origin`)");
            Log.w(CardDatabase.TAG, "migrate success!");
        }
    };
    private static final String TAG = "CardDatabase";
    private static volatile CardDatabase instance;

    public abstract AppInfoDao getAppInfoDao();

    public abstract FolderDao getFolderDao();

    public abstract SearchHistoryDao getSearchHistoryDao();

    public abstract ShortcutInfoDao getShortcutInfoDao();

    public abstract ApplicationDao getWorkSpaceItemDao();

    public static CardDatabase getInstance() {
        if (instance == null) {
            synchronized (CardDatabase.class) {
                if (instance == null) {
                    instance = create(SystemUIApplication.getInstance().getApplicationContext());
                }
            }
        }
        return instance;
    }

    private static CardDatabase create(final Context context) {
        return (CardDatabase) Room.databaseBuilder(context, CardDatabase.class, DB_NAME).addMigrations(MIGRATION_3_4).fallbackToDestructiveMigration().fallbackToDestructiveMigrationOnDowngrade().build();
    }
}
