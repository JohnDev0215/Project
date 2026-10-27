package com.byd.card.db;

import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.RoomDatabase;
import androidx.room.RoomMasterTable;
import androidx.room.RoomOpenHelper;
import androidx.room.util.DBUtil;
import androidx.room.util.TableInfo;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
import com.android.systemui.Constants;
import com.byd.card.bean.ShortcutInfo;
import com.byd.mediacenter.api.media.MediaControl;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

/* JADX INFO: loaded from: classes.dex */
public final class CardDatabase_Impl extends CardDatabase {
    private volatile AppInfoDao _appInfoDao;
    private volatile ApplicationDao _applicationDao;
    private volatile FolderDao _folderDao;
    private volatile SearchHistoryDao _searchHistoryDao;
    private volatile ShortcutInfoDao _shortcutInfoDao;

    @Override // androidx.room.RoomDatabase
    protected SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration configuration) {
        return configuration.sqliteOpenHelperFactory.create(SupportSQLiteOpenHelper.Configuration.builder(configuration.context).name(configuration.name).callback(new RoomOpenHelper(configuration, new RoomOpenHelper.Delegate(4) { // from class: com.byd.card.db.CardDatabase_Impl.1
            @Override // androidx.room.RoomOpenHelper.Delegate
            public void onPostMigrate(SupportSQLiteDatabase _db) {
            }

            @Override // androidx.room.RoomOpenHelper.Delegate
            public void createAllTables(SupportSQLiteDatabase _db) {
                _db.execSQL("CREATE TABLE IF NOT EXISTS `appinfo` (`appId` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `name` TEXT, `pinyin` TEXT, `packageName` TEXT, `className` TEXT, `activity` TEXT, `versionName` TEXT, `versionCode` INTEGER NOT NULL, `recent` INTEGER NOT NULL, `recentTime` INTEGER NOT NULL, `icons` BLOB, `count` INTEGER NOT NULL, `resizeMode` INTEGER NOT NULL)");
                _db.execSQL("CREATE UNIQUE INDEX IF NOT EXISTS `index_appinfo_packageName` ON `appinfo` (`packageName`)");
                _db.execSQL("CREATE TABLE IF NOT EXISTS `search_history` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `text` TEXT, `time` INTEGER NOT NULL)");
                _db.execSQL("CREATE TABLE IF NOT EXISTS `folder` (`folder_id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `title` TEXT)");
                _db.execSQL("CREATE TABLE IF NOT EXISTS `application` (`title` TEXT, `pinyin` TEXT, `type` INTEGER NOT NULL, `packageName` TEXT, `activity` TEXT, `versionName` TEXT, `versionCode` INTEGER NOT NULL, `iconBytes` BLOB, `position` INTEGER NOT NULL, `folderPosition` INTEGER NOT NULL, `folderId` INTEGER NOT NULL, `x` INTEGER NOT NULL, `y` INTEGER NOT NULL, `id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL)");
                _db.execSQL("CREATE UNIQUE INDEX IF NOT EXISTS `index_application_packageName` ON `application` (`packageName`)");
                _db.execSQL("CREATE TABLE IF NOT EXISTS `shortcut_info` (`shortcutId` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `identify` TEXT, `title` TEXT, `origin` TEXT, `pinyin` TEXT, `icon` BLOB, `intent` TEXT, `pGearFlag` INTEGER NOT NULL, `recent` INTEGER NOT NULL, `recentTime` INTEGER NOT NULL, `count` INTEGER NOT NULL, `reserve1` TEXT, `reserve2` TEXT, `reserve3` TEXT)");
                _db.execSQL("CREATE UNIQUE INDEX IF NOT EXISTS `index_shortcut_info_identify_origin` ON `shortcut_info` (`identify`, `origin`)");
                _db.execSQL(RoomMasterTable.CREATE_QUERY);
                _db.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, '9c3dca6c4c6cabde5dfee05f6e29fb53')");
            }

            @Override // androidx.room.RoomOpenHelper.Delegate
            public void dropAllTables(SupportSQLiteDatabase _db) {
                _db.execSQL("DROP TABLE IF EXISTS `appinfo`");
                _db.execSQL("DROP TABLE IF EXISTS `search_history`");
                _db.execSQL("DROP TABLE IF EXISTS `folder`");
                _db.execSQL("DROP TABLE IF EXISTS `application`");
                _db.execSQL("DROP TABLE IF EXISTS `shortcut_info`");
                if (CardDatabase_Impl.this.mCallbacks != null) {
                    int size = CardDatabase_Impl.this.mCallbacks.size();
                    for (int i = 0; i < size; i++) {
                        ((RoomDatabase.Callback) CardDatabase_Impl.this.mCallbacks.get(i)).onDestructiveMigration(_db);
                    }
                }
            }

            @Override // androidx.room.RoomOpenHelper.Delegate
            protected void onCreate(SupportSQLiteDatabase _db) {
                if (CardDatabase_Impl.this.mCallbacks != null) {
                    int size = CardDatabase_Impl.this.mCallbacks.size();
                    for (int i = 0; i < size; i++) {
                        ((RoomDatabase.Callback) CardDatabase_Impl.this.mCallbacks.get(i)).onCreate(_db);
                    }
                }
            }

            @Override // androidx.room.RoomOpenHelper.Delegate
            public void onOpen(SupportSQLiteDatabase _db) {
                CardDatabase_Impl.this.mDatabase = _db;
                CardDatabase_Impl.this.internalInitInvalidationTracker(_db);
                if (CardDatabase_Impl.this.mCallbacks != null) {
                    int size = CardDatabase_Impl.this.mCallbacks.size();
                    for (int i = 0; i < size; i++) {
                        ((RoomDatabase.Callback) CardDatabase_Impl.this.mCallbacks.get(i)).onOpen(_db);
                    }
                }
            }

            @Override // androidx.room.RoomOpenHelper.Delegate
            public void onPreMigrate(SupportSQLiteDatabase _db) {
                DBUtil.dropFtsSyncTriggers(_db);
            }

            @Override // androidx.room.RoomOpenHelper.Delegate
            protected RoomOpenHelper.ValidationResult onValidateSchema(SupportSQLiteDatabase _db) {
                HashMap map = new HashMap(13);
                map.put("appId", new TableInfo.Column("appId", "INTEGER", true, 1, null, 1));
                map.put("name", new TableInfo.Column("name", "TEXT", false, 0, null, 1));
                map.put(ShortcutInfo.Column.COLUMN_PINYIN, new TableInfo.Column(ShortcutInfo.Column.COLUMN_PINYIN, "TEXT", false, 0, null, 1));
                map.put("packageName", new TableInfo.Column("packageName", "TEXT", false, 0, null, 1));
                map.put("className", new TableInfo.Column("className", "TEXT", false, 0, null, 1));
                map.put(Constants.PackageInfo.ACTIVITY, new TableInfo.Column(Constants.PackageInfo.ACTIVITY, "TEXT", false, 0, null, 1));
                map.put("versionName", new TableInfo.Column("versionName", "TEXT", false, 0, null, 1));
                map.put("versionCode", new TableInfo.Column("versionCode", "INTEGER", true, 0, null, 1));
                map.put(ShortcutInfo.Column.COLUMN_RECENT, new TableInfo.Column(ShortcutInfo.Column.COLUMN_RECENT, "INTEGER", true, 0, null, 1));
                map.put(ShortcutInfo.Column.COLUMN_RECENT_TIME, new TableInfo.Column(ShortcutInfo.Column.COLUMN_RECENT_TIME, "INTEGER", true, 0, null, 1));
                map.put("icons", new TableInfo.Column("icons", "BLOB", false, 0, null, 1));
                map.put(ShortcutInfo.Column.COLUMN_COUNT, new TableInfo.Column(ShortcutInfo.Column.COLUMN_COUNT, "INTEGER", true, 0, null, 1));
                map.put("resizeMode", new TableInfo.Column("resizeMode", "INTEGER", true, 0, null, 1));
                HashSet hashSet = new HashSet(0);
                HashSet hashSet2 = new HashSet(1);
                hashSet2.add(new TableInfo.Index("index_appinfo_packageName", true, Arrays.asList("packageName")));
                TableInfo tableInfo = new TableInfo("appinfo", map, hashSet, hashSet2);
                TableInfo tableInfo2 = TableInfo.read(_db, "appinfo");
                if (!tableInfo.equals(tableInfo2)) {
                    return new RoomOpenHelper.ValidationResult(false, "appinfo(com.byd.card.bean.AppInfo).\n Expected:\n" + tableInfo + "\n Found:\n" + tableInfo2);
                }
                HashMap map2 = new HashMap(3);
                map2.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, 1));
                map2.put("text", new TableInfo.Column("text", "TEXT", false, 0, null, 1));
                map2.put("time", new TableInfo.Column("time", "INTEGER", true, 0, null, 1));
                TableInfo tableInfo3 = new TableInfo("search_history", map2, new HashSet(0), new HashSet(0));
                TableInfo tableInfo4 = TableInfo.read(_db, "search_history");
                if (!tableInfo3.equals(tableInfo4)) {
                    return new RoomOpenHelper.ValidationResult(false, "search_history(com.byd.card.bean.SearchHistory).\n Expected:\n" + tableInfo3 + "\n Found:\n" + tableInfo4);
                }
                HashMap map3 = new HashMap(2);
                map3.put("folder_id", new TableInfo.Column("folder_id", "INTEGER", true, 1, null, 1));
                map3.put(ShortcutInfo.Column.COLUMN_TITLE, new TableInfo.Column(ShortcutInfo.Column.COLUMN_TITLE, "TEXT", false, 0, null, 1));
                TableInfo tableInfo5 = new TableInfo("folder", map3, new HashSet(0), new HashSet(0));
                TableInfo tableInfo6 = TableInfo.read(_db, "folder");
                if (!tableInfo5.equals(tableInfo6)) {
                    return new RoomOpenHelper.ValidationResult(false, "folder(com.byd.card.bean.Folder).\n Expected:\n" + tableInfo5 + "\n Found:\n" + tableInfo6);
                }
                HashMap map4 = new HashMap(14);
                map4.put(ShortcutInfo.Column.COLUMN_TITLE, new TableInfo.Column(ShortcutInfo.Column.COLUMN_TITLE, "TEXT", false, 0, null, 1));
                map4.put(ShortcutInfo.Column.COLUMN_PINYIN, new TableInfo.Column(ShortcutInfo.Column.COLUMN_PINYIN, "TEXT", false, 0, null, 1));
                map4.put("type", new TableInfo.Column("type", "INTEGER", true, 0, null, 1));
                map4.put("packageName", new TableInfo.Column("packageName", "TEXT", false, 0, null, 1));
                map4.put(Constants.PackageInfo.ACTIVITY, new TableInfo.Column(Constants.PackageInfo.ACTIVITY, "TEXT", false, 0, null, 1));
                map4.put("versionName", new TableInfo.Column("versionName", "TEXT", false, 0, null, 1));
                map4.put("versionCode", new TableInfo.Column("versionCode", "INTEGER", true, 0, null, 1));
                map4.put("iconBytes", new TableInfo.Column("iconBytes", "BLOB", false, 0, null, 1));
                map4.put(MediaControl.PARAM_POSITION, new TableInfo.Column(MediaControl.PARAM_POSITION, "INTEGER", true, 0, null, 1));
                map4.put("folderPosition", new TableInfo.Column("folderPosition", "INTEGER", true, 0, null, 1));
                map4.put("folderId", new TableInfo.Column("folderId", "INTEGER", true, 0, null, 1));
                map4.put("x", new TableInfo.Column("x", "INTEGER", true, 0, null, 1));
                map4.put("y", new TableInfo.Column("y", "INTEGER", true, 0, null, 1));
                map4.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, 1));
                HashSet hashSet3 = new HashSet(0);
                HashSet hashSet4 = new HashSet(1);
                hashSet4.add(new TableInfo.Index("index_application_packageName", true, Arrays.asList("packageName")));
                TableInfo tableInfo7 = new TableInfo("application", map4, hashSet3, hashSet4);
                TableInfo tableInfo8 = TableInfo.read(_db, "application");
                if (!tableInfo7.equals(tableInfo8)) {
                    return new RoomOpenHelper.ValidationResult(false, "application(com.byd.card.drag.ApplicationInfo).\n Expected:\n" + tableInfo7 + "\n Found:\n" + tableInfo8);
                }
                HashMap map5 = new HashMap(14);
                map5.put(ShortcutInfo.Column.COLUMN_SHORTCUT_ID, new TableInfo.Column(ShortcutInfo.Column.COLUMN_SHORTCUT_ID, "INTEGER", true, 1, null, 1));
                map5.put(ShortcutInfo.Column.COLUMN_IDENTIFY, new TableInfo.Column(ShortcutInfo.Column.COLUMN_IDENTIFY, "TEXT", false, 0, null, 1));
                map5.put(ShortcutInfo.Column.COLUMN_TITLE, new TableInfo.Column(ShortcutInfo.Column.COLUMN_TITLE, "TEXT", false, 0, null, 1));
                map5.put("origin", new TableInfo.Column("origin", "TEXT", false, 0, null, 1));
                map5.put(ShortcutInfo.Column.COLUMN_PINYIN, new TableInfo.Column(ShortcutInfo.Column.COLUMN_PINYIN, "TEXT", false, 0, null, 1));
                map5.put(ShortcutInfo.Column.COLUMN_ICON, new TableInfo.Column(ShortcutInfo.Column.COLUMN_ICON, "BLOB", false, 0, null, 1));
                map5.put(ShortcutInfo.Column.COLUMN_INTENT, new TableInfo.Column(ShortcutInfo.Column.COLUMN_INTENT, "TEXT", false, 0, null, 1));
                map5.put(ShortcutInfo.Column.COLUMN_P_GEAR_FLAG, new TableInfo.Column(ShortcutInfo.Column.COLUMN_P_GEAR_FLAG, "INTEGER", true, 0, null, 1));
                map5.put(ShortcutInfo.Column.COLUMN_RECENT, new TableInfo.Column(ShortcutInfo.Column.COLUMN_RECENT, "INTEGER", true, 0, null, 1));
                map5.put(ShortcutInfo.Column.COLUMN_RECENT_TIME, new TableInfo.Column(ShortcutInfo.Column.COLUMN_RECENT_TIME, "INTEGER", true, 0, null, 1));
                map5.put(ShortcutInfo.Column.COLUMN_COUNT, new TableInfo.Column(ShortcutInfo.Column.COLUMN_COUNT, "INTEGER", true, 0, null, 1));
                map5.put(ShortcutInfo.Column.COLUMN_RESERVE1, new TableInfo.Column(ShortcutInfo.Column.COLUMN_RESERVE1, "TEXT", false, 0, null, 1));
                map5.put(ShortcutInfo.Column.COLUMN_RESERVE2, new TableInfo.Column(ShortcutInfo.Column.COLUMN_RESERVE2, "TEXT", false, 0, null, 1));
                map5.put(ShortcutInfo.Column.COLUMN_RESERVE3, new TableInfo.Column(ShortcutInfo.Column.COLUMN_RESERVE3, "TEXT", false, 0, null, 1));
                HashSet hashSet5 = new HashSet(0);
                HashSet hashSet6 = new HashSet(1);
                hashSet6.add(new TableInfo.Index("index_shortcut_info_identify_origin", true, Arrays.asList(ShortcutInfo.Column.COLUMN_IDENTIFY, "origin")));
                TableInfo tableInfo9 = new TableInfo("shortcut_info", map5, hashSet5, hashSet6);
                TableInfo tableInfo10 = TableInfo.read(_db, "shortcut_info");
                if (!tableInfo9.equals(tableInfo10)) {
                    return new RoomOpenHelper.ValidationResult(false, "shortcut_info(com.byd.card.bean.ShortcutInfo).\n Expected:\n" + tableInfo9 + "\n Found:\n" + tableInfo10);
                }
                return new RoomOpenHelper.ValidationResult(true, null);
            }
        }, "9c3dca6c4c6cabde5dfee05f6e29fb53", "27c14af9f055808bc7e76e23c9872aa6")).build());
    }

    @Override // androidx.room.RoomDatabase
    protected InvalidationTracker createInvalidationTracker() {
        return new InvalidationTracker(this, new HashMap(0), new HashMap(0), "appinfo", "search_history", "folder", "application", "shortcut_info");
    }

    @Override // androidx.room.RoomDatabase
    public void clearAllTables() {
        super.assertNotMainThread();
        SupportSQLiteDatabase writableDatabase = super.getOpenHelper().getWritableDatabase();
        try {
            super.beginTransaction();
            writableDatabase.execSQL("DELETE FROM `appinfo`");
            writableDatabase.execSQL("DELETE FROM `search_history`");
            writableDatabase.execSQL("DELETE FROM `folder`");
            writableDatabase.execSQL("DELETE FROM `application`");
            writableDatabase.execSQL("DELETE FROM `shortcut_info`");
            super.setTransactionSuccessful();
        } finally {
            super.endTransaction();
            writableDatabase.query("PRAGMA wal_checkpoint(FULL)").close();
            if (!writableDatabase.inTransaction()) {
                writableDatabase.execSQL("VACUUM");
            }
        }
    }

    @Override // com.byd.card.db.CardDatabase
    public AppInfoDao getAppInfoDao() {
        AppInfoDao appInfoDao;
        if (this._appInfoDao != null) {
            return this._appInfoDao;
        }
        synchronized (this) {
            if (this._appInfoDao == null) {
                this._appInfoDao = new AppInfoDao_Impl(this);
            }
            appInfoDao = this._appInfoDao;
        }
        return appInfoDao;
    }

    @Override // com.byd.card.db.CardDatabase
    public SearchHistoryDao getSearchHistoryDao() {
        SearchHistoryDao searchHistoryDao;
        if (this._searchHistoryDao != null) {
            return this._searchHistoryDao;
        }
        synchronized (this) {
            if (this._searchHistoryDao == null) {
                this._searchHistoryDao = new SearchHistoryDao_Impl(this);
            }
            searchHistoryDao = this._searchHistoryDao;
        }
        return searchHistoryDao;
    }

    @Override // com.byd.card.db.CardDatabase
    public FolderDao getFolderDao() {
        FolderDao folderDao;
        if (this._folderDao != null) {
            return this._folderDao;
        }
        synchronized (this) {
            if (this._folderDao == null) {
                this._folderDao = new FolderDao_Impl(this);
            }
            folderDao = this._folderDao;
        }
        return folderDao;
    }

    @Override // com.byd.card.db.CardDatabase
    public ApplicationDao getWorkSpaceItemDao() {
        ApplicationDao applicationDao;
        if (this._applicationDao != null) {
            return this._applicationDao;
        }
        synchronized (this) {
            if (this._applicationDao == null) {
                this._applicationDao = new ApplicationDao_Impl(this);
            }
            applicationDao = this._applicationDao;
        }
        return applicationDao;
    }

    @Override // com.byd.card.db.CardDatabase
    public ShortcutInfoDao getShortcutInfoDao() {
        ShortcutInfoDao shortcutInfoDao;
        if (this._shortcutInfoDao != null) {
            return this._shortcutInfoDao;
        }
        synchronized (this) {
            if (this._shortcutInfoDao == null) {
                this._shortcutInfoDao = new ShortcutInfoDao_Impl(this);
            }
            shortcutInfoDao = this._shortcutInfoDao;
        }
        return shortcutInfoDao;
    }
}
