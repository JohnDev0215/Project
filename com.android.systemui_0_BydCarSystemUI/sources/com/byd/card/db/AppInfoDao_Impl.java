package com.byd.card.db;

import android.database.Cursor;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.android.systemui.Constants;
import com.byd.card.bean.AppInfo;
import com.byd.card.bean.ShortcutInfo;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class AppInfoDao_Impl implements AppInfoDao {
    private final RoomDatabase __db;
    private final EntityDeletionOrUpdateAdapter<AppInfo> __deletionAdapterOfAppInfo;
    private final EntityInsertionAdapter<AppInfo> __insertionAdapterOfAppInfo;
    private final SharedSQLiteStatement __preparedStmtOfUpdates;
    private final EntityDeletionOrUpdateAdapter<AppInfo> __updateAdapterOfAppInfo;

    public AppInfoDao_Impl(RoomDatabase __db) {
        this.__db = __db;
        this.__insertionAdapterOfAppInfo = new EntityInsertionAdapter<AppInfo>(__db) { // from class: com.byd.card.db.AppInfoDao_Impl.1
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "INSERT OR REPLACE INTO `appinfo` (`appId`,`name`,`pinyin`,`packageName`,`className`,`activity`,`versionName`,`versionCode`,`recent`,`recentTime`,`icons`,`count`,`resizeMode`) VALUES (nullif(?, 0),?,?,?,?,?,?,?,?,?,?,?,?)";
            }

            @Override // androidx.room.EntityInsertionAdapter
            public void bind(SupportSQLiteStatement stmt, AppInfo value) {
                stmt.bindLong(1, value.appId);
                if (value.name == null) {
                    stmt.bindNull(2);
                } else {
                    stmt.bindString(2, value.name);
                }
                if (value.pinyin == null) {
                    stmt.bindNull(3);
                } else {
                    stmt.bindString(3, value.pinyin);
                }
                if (value.packageName == null) {
                    stmt.bindNull(4);
                } else {
                    stmt.bindString(4, value.packageName);
                }
                if (value.className == null) {
                    stmt.bindNull(5);
                } else {
                    stmt.bindString(5, value.className);
                }
                if (value.activity == null) {
                    stmt.bindNull(6);
                } else {
                    stmt.bindString(6, value.activity);
                }
                if (value.versionName == null) {
                    stmt.bindNull(7);
                } else {
                    stmt.bindString(7, value.versionName);
                }
                stmt.bindLong(8, value.versionCode);
                stmt.bindLong(9, value.recent);
                stmt.bindLong(10, value.recentTime);
                if (value.icons == null) {
                    stmt.bindNull(11);
                } else {
                    stmt.bindBlob(11, value.icons);
                }
                stmt.bindLong(12, value.count);
                stmt.bindLong(13, value.resizeMode);
            }
        };
        this.__deletionAdapterOfAppInfo = new EntityDeletionOrUpdateAdapter<AppInfo>(__db) { // from class: com.byd.card.db.AppInfoDao_Impl.2
            @Override // androidx.room.EntityDeletionOrUpdateAdapter, androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "DELETE FROM `appinfo` WHERE `appId` = ?";
            }

            @Override // androidx.room.EntityDeletionOrUpdateAdapter
            public void bind(SupportSQLiteStatement stmt, AppInfo value) {
                stmt.bindLong(1, value.appId);
            }
        };
        this.__updateAdapterOfAppInfo = new EntityDeletionOrUpdateAdapter<AppInfo>(__db) { // from class: com.byd.card.db.AppInfoDao_Impl.3
            @Override // androidx.room.EntityDeletionOrUpdateAdapter, androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "UPDATE OR ABORT `appinfo` SET `appId` = ?,`name` = ?,`pinyin` = ?,`packageName` = ?,`className` = ?,`activity` = ?,`versionName` = ?,`versionCode` = ?,`recent` = ?,`recentTime` = ?,`icons` = ?,`count` = ?,`resizeMode` = ? WHERE `appId` = ?";
            }

            @Override // androidx.room.EntityDeletionOrUpdateAdapter
            public void bind(SupportSQLiteStatement stmt, AppInfo value) {
                stmt.bindLong(1, value.appId);
                if (value.name == null) {
                    stmt.bindNull(2);
                } else {
                    stmt.bindString(2, value.name);
                }
                if (value.pinyin == null) {
                    stmt.bindNull(3);
                } else {
                    stmt.bindString(3, value.pinyin);
                }
                if (value.packageName == null) {
                    stmt.bindNull(4);
                } else {
                    stmt.bindString(4, value.packageName);
                }
                if (value.className == null) {
                    stmt.bindNull(5);
                } else {
                    stmt.bindString(5, value.className);
                }
                if (value.activity == null) {
                    stmt.bindNull(6);
                } else {
                    stmt.bindString(6, value.activity);
                }
                if (value.versionName == null) {
                    stmt.bindNull(7);
                } else {
                    stmt.bindString(7, value.versionName);
                }
                stmt.bindLong(8, value.versionCode);
                stmt.bindLong(9, value.recent);
                stmt.bindLong(10, value.recentTime);
                if (value.icons == null) {
                    stmt.bindNull(11);
                } else {
                    stmt.bindBlob(11, value.icons);
                }
                stmt.bindLong(12, value.count);
                stmt.bindLong(13, value.resizeMode);
                stmt.bindLong(14, value.appId);
            }
        };
        this.__preparedStmtOfUpdates = new SharedSQLiteStatement(__db) { // from class: com.byd.card.db.AppInfoDao_Impl.4
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "update appinfo set recent=1, recentTime=? WHERE appId=?";
            }
        };
    }

    @Override // com.byd.card.db.AppInfoDao
    public void insert(final AppInfo... appInfos) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__insertionAdapterOfAppInfo.insert(appInfos);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // com.byd.card.db.AppInfoDao
    public void insertList(final List<AppInfo> appInfos) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__insertionAdapterOfAppInfo.insert(appInfos);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // com.byd.card.db.AppInfoDao
    public void delete(final AppInfo... appInfos) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__deletionAdapterOfAppInfo.handleMultiple(appInfos);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // com.byd.card.db.AppInfoDao
    public void deleteList(final List<AppInfo> appInfos) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__deletionAdapterOfAppInfo.handleMultiple(appInfos);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // com.byd.card.db.AppInfoDao
    public void update(final AppInfo... appInfos) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__updateAdapterOfAppInfo.handleMultiple(appInfos);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // com.byd.card.db.AppInfoDao
    public void updateList(final List<AppInfo> appInfos) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__updateAdapterOfAppInfo.handleMultiple(appInfos);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // com.byd.card.db.AppInfoDao
    public void updates(final long time, final int id) {
        this.__db.assertNotSuspendingTransaction();
        SupportSQLiteStatement supportSQLiteStatementAcquire = this.__preparedStmtOfUpdates.acquire();
        supportSQLiteStatementAcquire.bindLong(1, time);
        supportSQLiteStatementAcquire.bindLong(2, id);
        this.__db.beginTransaction();
        try {
            supportSQLiteStatementAcquire.executeUpdateDelete();
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
            this.__preparedStmtOfUpdates.release(supportSQLiteStatementAcquire);
        }
    }

    @Override // com.byd.card.db.AppInfoDao
    public List<AppInfo> getListForExact(final String inputText) throws Throwable {
        RoomSQLiteQuery roomSQLiteQuery;
        int columnIndexOrThrow;
        int columnIndexOrThrow2;
        int columnIndexOrThrow3;
        int columnIndexOrThrow4;
        int columnIndexOrThrow5;
        int columnIndexOrThrow6;
        int columnIndexOrThrow7;
        int columnIndexOrThrow8;
        int columnIndexOrThrow9;
        int columnIndexOrThrow10;
        int columnIndexOrThrow11;
        int columnIndexOrThrow12;
        int columnIndexOrThrow13;
        RoomSQLiteQuery roomSQLiteQueryAcquire = RoomSQLiteQuery.acquire("SELECT * FROM appinfo WHERE name=? or pinyin=? order by count desc", 2);
        if (inputText == null) {
            roomSQLiteQueryAcquire.bindNull(1);
        } else {
            roomSQLiteQueryAcquire.bindString(1, inputText);
        }
        if (inputText == null) {
            roomSQLiteQueryAcquire.bindNull(2);
        } else {
            roomSQLiteQueryAcquire.bindString(2, inputText);
        }
        this.__db.assertNotSuspendingTransaction();
        Cursor cursorQuery = DBUtil.query(this.__db, roomSQLiteQueryAcquire, false, null);
        try {
            columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(cursorQuery, "appId");
            columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "name");
            columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(cursorQuery, ShortcutInfo.Column.COLUMN_PINYIN);
            columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "packageName");
            columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "className");
            columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(cursorQuery, Constants.PackageInfo.ACTIVITY);
            columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "versionName");
            columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "versionCode");
            columnIndexOrThrow9 = CursorUtil.getColumnIndexOrThrow(cursorQuery, ShortcutInfo.Column.COLUMN_RECENT);
            columnIndexOrThrow10 = CursorUtil.getColumnIndexOrThrow(cursorQuery, ShortcutInfo.Column.COLUMN_RECENT_TIME);
            columnIndexOrThrow11 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "icons");
            columnIndexOrThrow12 = CursorUtil.getColumnIndexOrThrow(cursorQuery, ShortcutInfo.Column.COLUMN_COUNT);
            columnIndexOrThrow13 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "resizeMode");
            roomSQLiteQuery = roomSQLiteQueryAcquire;
        } catch (Throwable th) {
            th = th;
            roomSQLiteQuery = roomSQLiteQueryAcquire;
        }
        try {
            ArrayList arrayList = new ArrayList(cursorQuery.getCount());
            while (cursorQuery.moveToNext()) {
                int i = columnIndexOrThrow4;
                int i2 = columnIndexOrThrow5;
                AppInfo appInfo = new AppInfo(cursorQuery.getString(columnIndexOrThrow4), cursorQuery.getString(columnIndexOrThrow5));
                appInfo.appId = cursorQuery.getInt(columnIndexOrThrow);
                appInfo.name = cursorQuery.getString(columnIndexOrThrow2);
                appInfo.pinyin = cursorQuery.getString(columnIndexOrThrow3);
                appInfo.activity = cursorQuery.getString(columnIndexOrThrow6);
                appInfo.versionName = cursorQuery.getString(columnIndexOrThrow7);
                appInfo.versionCode = cursorQuery.getInt(columnIndexOrThrow8);
                appInfo.recent = cursorQuery.getInt(columnIndexOrThrow9);
                int i3 = columnIndexOrThrow2;
                int i4 = columnIndexOrThrow3;
                appInfo.recentTime = cursorQuery.getLong(columnIndexOrThrow10);
                appInfo.icons = cursorQuery.getBlob(columnIndexOrThrow11);
                appInfo.count = cursorQuery.getInt(columnIndexOrThrow12);
                appInfo.resizeMode = cursorQuery.getInt(columnIndexOrThrow13);
                arrayList.add(appInfo);
                columnIndexOrThrow2 = i3;
                columnIndexOrThrow3 = i4;
                columnIndexOrThrow5 = i2;
                columnIndexOrThrow4 = i;
            }
            cursorQuery.close();
            roomSQLiteQuery.release();
            return arrayList;
        } catch (Throwable th2) {
            th = th2;
            cursorQuery.close();
            roomSQLiteQuery.release();
            throw th;
        }
    }

    @Override // com.byd.card.db.AppInfoDao
    public List<AppInfo> getListForStart(final String inputText) throws Throwable {
        RoomSQLiteQuery roomSQLiteQuery;
        int columnIndexOrThrow;
        int columnIndexOrThrow2;
        int columnIndexOrThrow3;
        int columnIndexOrThrow4;
        int columnIndexOrThrow5;
        int columnIndexOrThrow6;
        int columnIndexOrThrow7;
        int columnIndexOrThrow8;
        int columnIndexOrThrow9;
        int columnIndexOrThrow10;
        int columnIndexOrThrow11;
        int columnIndexOrThrow12;
        int columnIndexOrThrow13;
        RoomSQLiteQuery roomSQLiteQueryAcquire = RoomSQLiteQuery.acquire("SELECT * FROM appinfo WHERE name LIKE ? ||'%' or pinyin LIKE ? ||'%' order by count desc", 2);
        if (inputText == null) {
            roomSQLiteQueryAcquire.bindNull(1);
        } else {
            roomSQLiteQueryAcquire.bindString(1, inputText);
        }
        if (inputText == null) {
            roomSQLiteQueryAcquire.bindNull(2);
        } else {
            roomSQLiteQueryAcquire.bindString(2, inputText);
        }
        this.__db.assertNotSuspendingTransaction();
        Cursor cursorQuery = DBUtil.query(this.__db, roomSQLiteQueryAcquire, false, null);
        try {
            columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(cursorQuery, "appId");
            columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "name");
            columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(cursorQuery, ShortcutInfo.Column.COLUMN_PINYIN);
            columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "packageName");
            columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "className");
            columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(cursorQuery, Constants.PackageInfo.ACTIVITY);
            columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "versionName");
            columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "versionCode");
            columnIndexOrThrow9 = CursorUtil.getColumnIndexOrThrow(cursorQuery, ShortcutInfo.Column.COLUMN_RECENT);
            columnIndexOrThrow10 = CursorUtil.getColumnIndexOrThrow(cursorQuery, ShortcutInfo.Column.COLUMN_RECENT_TIME);
            columnIndexOrThrow11 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "icons");
            columnIndexOrThrow12 = CursorUtil.getColumnIndexOrThrow(cursorQuery, ShortcutInfo.Column.COLUMN_COUNT);
            columnIndexOrThrow13 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "resizeMode");
            roomSQLiteQuery = roomSQLiteQueryAcquire;
        } catch (Throwable th) {
            th = th;
            roomSQLiteQuery = roomSQLiteQueryAcquire;
        }
        try {
            ArrayList arrayList = new ArrayList(cursorQuery.getCount());
            while (cursorQuery.moveToNext()) {
                int i = columnIndexOrThrow4;
                int i2 = columnIndexOrThrow5;
                AppInfo appInfo = new AppInfo(cursorQuery.getString(columnIndexOrThrow4), cursorQuery.getString(columnIndexOrThrow5));
                appInfo.appId = cursorQuery.getInt(columnIndexOrThrow);
                appInfo.name = cursorQuery.getString(columnIndexOrThrow2);
                appInfo.pinyin = cursorQuery.getString(columnIndexOrThrow3);
                appInfo.activity = cursorQuery.getString(columnIndexOrThrow6);
                appInfo.versionName = cursorQuery.getString(columnIndexOrThrow7);
                appInfo.versionCode = cursorQuery.getInt(columnIndexOrThrow8);
                appInfo.recent = cursorQuery.getInt(columnIndexOrThrow9);
                int i3 = columnIndexOrThrow2;
                int i4 = columnIndexOrThrow3;
                appInfo.recentTime = cursorQuery.getLong(columnIndexOrThrow10);
                appInfo.icons = cursorQuery.getBlob(columnIndexOrThrow11);
                appInfo.count = cursorQuery.getInt(columnIndexOrThrow12);
                appInfo.resizeMode = cursorQuery.getInt(columnIndexOrThrow13);
                arrayList.add(appInfo);
                columnIndexOrThrow2 = i3;
                columnIndexOrThrow3 = i4;
                columnIndexOrThrow5 = i2;
                columnIndexOrThrow4 = i;
            }
            cursorQuery.close();
            roomSQLiteQuery.release();
            return arrayList;
        } catch (Throwable th2) {
            th = th2;
            cursorQuery.close();
            roomSQLiteQuery.release();
            throw th;
        }
    }

    @Override // com.byd.card.db.AppInfoDao
    public List<AppInfo> getListForSearch(final String inputText) throws Throwable {
        RoomSQLiteQuery roomSQLiteQuery;
        int columnIndexOrThrow;
        int columnIndexOrThrow2;
        int columnIndexOrThrow3;
        int columnIndexOrThrow4;
        int columnIndexOrThrow5;
        int columnIndexOrThrow6;
        int columnIndexOrThrow7;
        int columnIndexOrThrow8;
        int columnIndexOrThrow9;
        int columnIndexOrThrow10;
        int columnIndexOrThrow11;
        int columnIndexOrThrow12;
        int columnIndexOrThrow13;
        RoomSQLiteQuery roomSQLiteQueryAcquire = RoomSQLiteQuery.acquire("SELECT * FROM appinfo WHERE name LIKE '%'|| ? ||'%' or pinyin LIKE '%'|| ? ||'%' order by count desc", 2);
        if (inputText == null) {
            roomSQLiteQueryAcquire.bindNull(1);
        } else {
            roomSQLiteQueryAcquire.bindString(1, inputText);
        }
        if (inputText == null) {
            roomSQLiteQueryAcquire.bindNull(2);
        } else {
            roomSQLiteQueryAcquire.bindString(2, inputText);
        }
        this.__db.assertNotSuspendingTransaction();
        Cursor cursorQuery = DBUtil.query(this.__db, roomSQLiteQueryAcquire, false, null);
        try {
            columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(cursorQuery, "appId");
            columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "name");
            columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(cursorQuery, ShortcutInfo.Column.COLUMN_PINYIN);
            columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "packageName");
            columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "className");
            columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(cursorQuery, Constants.PackageInfo.ACTIVITY);
            columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "versionName");
            columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "versionCode");
            columnIndexOrThrow9 = CursorUtil.getColumnIndexOrThrow(cursorQuery, ShortcutInfo.Column.COLUMN_RECENT);
            columnIndexOrThrow10 = CursorUtil.getColumnIndexOrThrow(cursorQuery, ShortcutInfo.Column.COLUMN_RECENT_TIME);
            columnIndexOrThrow11 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "icons");
            columnIndexOrThrow12 = CursorUtil.getColumnIndexOrThrow(cursorQuery, ShortcutInfo.Column.COLUMN_COUNT);
            columnIndexOrThrow13 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "resizeMode");
            roomSQLiteQuery = roomSQLiteQueryAcquire;
        } catch (Throwable th) {
            th = th;
            roomSQLiteQuery = roomSQLiteQueryAcquire;
        }
        try {
            ArrayList arrayList = new ArrayList(cursorQuery.getCount());
            while (cursorQuery.moveToNext()) {
                int i = columnIndexOrThrow4;
                int i2 = columnIndexOrThrow5;
                AppInfo appInfo = new AppInfo(cursorQuery.getString(columnIndexOrThrow4), cursorQuery.getString(columnIndexOrThrow5));
                appInfo.appId = cursorQuery.getInt(columnIndexOrThrow);
                appInfo.name = cursorQuery.getString(columnIndexOrThrow2);
                appInfo.pinyin = cursorQuery.getString(columnIndexOrThrow3);
                appInfo.activity = cursorQuery.getString(columnIndexOrThrow6);
                appInfo.versionName = cursorQuery.getString(columnIndexOrThrow7);
                appInfo.versionCode = cursorQuery.getInt(columnIndexOrThrow8);
                appInfo.recent = cursorQuery.getInt(columnIndexOrThrow9);
                int i3 = columnIndexOrThrow2;
                int i4 = columnIndexOrThrow3;
                appInfo.recentTime = cursorQuery.getLong(columnIndexOrThrow10);
                appInfo.icons = cursorQuery.getBlob(columnIndexOrThrow11);
                appInfo.count = cursorQuery.getInt(columnIndexOrThrow12);
                appInfo.resizeMode = cursorQuery.getInt(columnIndexOrThrow13);
                arrayList.add(appInfo);
                columnIndexOrThrow2 = i3;
                columnIndexOrThrow3 = i4;
                columnIndexOrThrow5 = i2;
                columnIndexOrThrow4 = i;
            }
            cursorQuery.close();
            roomSQLiteQuery.release();
            return arrayList;
        } catch (Throwable th2) {
            th = th2;
            cursorQuery.close();
            roomSQLiteQuery.release();
            throw th;
        }
    }

    @Override // com.byd.card.db.AppInfoDao
    public List<AppInfo> getRecentList(final int recent) throws Throwable {
        RoomSQLiteQuery roomSQLiteQuery;
        int columnIndexOrThrow;
        int columnIndexOrThrow2;
        int columnIndexOrThrow3;
        int columnIndexOrThrow4;
        int columnIndexOrThrow5;
        int columnIndexOrThrow6;
        int columnIndexOrThrow7;
        int columnIndexOrThrow8;
        int columnIndexOrThrow9;
        int columnIndexOrThrow10;
        int columnIndexOrThrow11;
        int columnIndexOrThrow12;
        int columnIndexOrThrow13;
        RoomSQLiteQuery roomSQLiteQueryAcquire = RoomSQLiteQuery.acquire("SELECT * FROM appinfo WHERE recent=? order by recentTime desc limit 6", 1);
        roomSQLiteQueryAcquire.bindLong(1, recent);
        this.__db.assertNotSuspendingTransaction();
        Cursor cursorQuery = DBUtil.query(this.__db, roomSQLiteQueryAcquire, false, null);
        try {
            columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(cursorQuery, "appId");
            columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "name");
            columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(cursorQuery, ShortcutInfo.Column.COLUMN_PINYIN);
            columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "packageName");
            columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "className");
            columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(cursorQuery, Constants.PackageInfo.ACTIVITY);
            columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "versionName");
            columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "versionCode");
            columnIndexOrThrow9 = CursorUtil.getColumnIndexOrThrow(cursorQuery, ShortcutInfo.Column.COLUMN_RECENT);
            columnIndexOrThrow10 = CursorUtil.getColumnIndexOrThrow(cursorQuery, ShortcutInfo.Column.COLUMN_RECENT_TIME);
            columnIndexOrThrow11 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "icons");
            columnIndexOrThrow12 = CursorUtil.getColumnIndexOrThrow(cursorQuery, ShortcutInfo.Column.COLUMN_COUNT);
            columnIndexOrThrow13 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "resizeMode");
            roomSQLiteQuery = roomSQLiteQueryAcquire;
        } catch (Throwable th) {
            th = th;
            roomSQLiteQuery = roomSQLiteQueryAcquire;
        }
        try {
            ArrayList arrayList = new ArrayList(cursorQuery.getCount());
            while (cursorQuery.moveToNext()) {
                int i = columnIndexOrThrow4;
                int i2 = columnIndexOrThrow5;
                AppInfo appInfo = new AppInfo(cursorQuery.getString(columnIndexOrThrow4), cursorQuery.getString(columnIndexOrThrow5));
                appInfo.appId = cursorQuery.getInt(columnIndexOrThrow);
                appInfo.name = cursorQuery.getString(columnIndexOrThrow2);
                appInfo.pinyin = cursorQuery.getString(columnIndexOrThrow3);
                appInfo.activity = cursorQuery.getString(columnIndexOrThrow6);
                appInfo.versionName = cursorQuery.getString(columnIndexOrThrow7);
                appInfo.versionCode = cursorQuery.getInt(columnIndexOrThrow8);
                appInfo.recent = cursorQuery.getInt(columnIndexOrThrow9);
                int i3 = columnIndexOrThrow2;
                appInfo.recentTime = cursorQuery.getLong(columnIndexOrThrow10);
                appInfo.icons = cursorQuery.getBlob(columnIndexOrThrow11);
                appInfo.count = cursorQuery.getInt(columnIndexOrThrow12);
                appInfo.resizeMode = cursorQuery.getInt(columnIndexOrThrow13);
                arrayList.add(appInfo);
                columnIndexOrThrow2 = i3;
                columnIndexOrThrow5 = i2;
                columnIndexOrThrow4 = i;
            }
            cursorQuery.close();
            roomSQLiteQuery.release();
            return arrayList;
        } catch (Throwable th2) {
            th = th2;
            cursorQuery.close();
            roomSQLiteQuery.release();
            throw th;
        }
    }

    @Override // com.byd.card.db.AppInfoDao
    public AppInfo getAppInfoByPackage(final String packageName) {
        AppInfo appInfo;
        RoomSQLiteQuery roomSQLiteQueryAcquire = RoomSQLiteQuery.acquire("SELECT * FROM appinfo WHERE packageName=?", 1);
        if (packageName == null) {
            roomSQLiteQueryAcquire.bindNull(1);
        } else {
            roomSQLiteQueryAcquire.bindString(1, packageName);
        }
        this.__db.assertNotSuspendingTransaction();
        Cursor cursorQuery = DBUtil.query(this.__db, roomSQLiteQueryAcquire, false, null);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(cursorQuery, "appId");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "name");
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(cursorQuery, ShortcutInfo.Column.COLUMN_PINYIN);
            int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "packageName");
            int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "className");
            int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(cursorQuery, Constants.PackageInfo.ACTIVITY);
            int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "versionName");
            int columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "versionCode");
            int columnIndexOrThrow9 = CursorUtil.getColumnIndexOrThrow(cursorQuery, ShortcutInfo.Column.COLUMN_RECENT);
            int columnIndexOrThrow10 = CursorUtil.getColumnIndexOrThrow(cursorQuery, ShortcutInfo.Column.COLUMN_RECENT_TIME);
            int columnIndexOrThrow11 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "icons");
            int columnIndexOrThrow12 = CursorUtil.getColumnIndexOrThrow(cursorQuery, ShortcutInfo.Column.COLUMN_COUNT);
            int columnIndexOrThrow13 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "resizeMode");
            if (cursorQuery.moveToFirst()) {
                AppInfo appInfo2 = new AppInfo(cursorQuery.getString(columnIndexOrThrow4), cursorQuery.getString(columnIndexOrThrow5));
                appInfo2.appId = cursorQuery.getInt(columnIndexOrThrow);
                appInfo2.name = cursorQuery.getString(columnIndexOrThrow2);
                appInfo2.pinyin = cursorQuery.getString(columnIndexOrThrow3);
                appInfo2.activity = cursorQuery.getString(columnIndexOrThrow6);
                appInfo2.versionName = cursorQuery.getString(columnIndexOrThrow7);
                appInfo2.versionCode = cursorQuery.getInt(columnIndexOrThrow8);
                appInfo2.recent = cursorQuery.getInt(columnIndexOrThrow9);
                appInfo2.recentTime = cursorQuery.getLong(columnIndexOrThrow10);
                appInfo2.icons = cursorQuery.getBlob(columnIndexOrThrow11);
                appInfo2.count = cursorQuery.getInt(columnIndexOrThrow12);
                appInfo2.resizeMode = cursorQuery.getInt(columnIndexOrThrow13);
                appInfo = appInfo2;
            } else {
                appInfo = null;
            }
            return appInfo;
        } finally {
            cursorQuery.close();
            roomSQLiteQueryAcquire.release();
        }
    }

    @Override // com.byd.card.db.AppInfoDao
    public List<AppInfo> getAllApp() throws Throwable {
        RoomSQLiteQuery roomSQLiteQuery;
        int columnIndexOrThrow;
        int columnIndexOrThrow2;
        int columnIndexOrThrow3;
        int columnIndexOrThrow4;
        int columnIndexOrThrow5;
        int columnIndexOrThrow6;
        int columnIndexOrThrow7;
        int columnIndexOrThrow8;
        int columnIndexOrThrow9;
        int columnIndexOrThrow10;
        int columnIndexOrThrow11;
        int columnIndexOrThrow12;
        int columnIndexOrThrow13;
        RoomSQLiteQuery roomSQLiteQueryAcquire = RoomSQLiteQuery.acquire("SELECT * FROM appinfo", 0);
        this.__db.assertNotSuspendingTransaction();
        Cursor cursorQuery = DBUtil.query(this.__db, roomSQLiteQueryAcquire, false, null);
        try {
            columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(cursorQuery, "appId");
            columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "name");
            columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(cursorQuery, ShortcutInfo.Column.COLUMN_PINYIN);
            columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "packageName");
            columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "className");
            columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(cursorQuery, Constants.PackageInfo.ACTIVITY);
            columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "versionName");
            columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "versionCode");
            columnIndexOrThrow9 = CursorUtil.getColumnIndexOrThrow(cursorQuery, ShortcutInfo.Column.COLUMN_RECENT);
            columnIndexOrThrow10 = CursorUtil.getColumnIndexOrThrow(cursorQuery, ShortcutInfo.Column.COLUMN_RECENT_TIME);
            columnIndexOrThrow11 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "icons");
            columnIndexOrThrow12 = CursorUtil.getColumnIndexOrThrow(cursorQuery, ShortcutInfo.Column.COLUMN_COUNT);
            columnIndexOrThrow13 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "resizeMode");
            roomSQLiteQuery = roomSQLiteQueryAcquire;
        } catch (Throwable th) {
            th = th;
            roomSQLiteQuery = roomSQLiteQueryAcquire;
        }
        try {
            ArrayList arrayList = new ArrayList(cursorQuery.getCount());
            while (cursorQuery.moveToNext()) {
                int i = columnIndexOrThrow4;
                int i2 = columnIndexOrThrow5;
                AppInfo appInfo = new AppInfo(cursorQuery.getString(columnIndexOrThrow4), cursorQuery.getString(columnIndexOrThrow5));
                appInfo.appId = cursorQuery.getInt(columnIndexOrThrow);
                appInfo.name = cursorQuery.getString(columnIndexOrThrow2);
                appInfo.pinyin = cursorQuery.getString(columnIndexOrThrow3);
                appInfo.activity = cursorQuery.getString(columnIndexOrThrow6);
                appInfo.versionName = cursorQuery.getString(columnIndexOrThrow7);
                appInfo.versionCode = cursorQuery.getInt(columnIndexOrThrow8);
                appInfo.recent = cursorQuery.getInt(columnIndexOrThrow9);
                int i3 = columnIndexOrThrow2;
                appInfo.recentTime = cursorQuery.getLong(columnIndexOrThrow10);
                appInfo.icons = cursorQuery.getBlob(columnIndexOrThrow11);
                appInfo.count = cursorQuery.getInt(columnIndexOrThrow12);
                appInfo.resizeMode = cursorQuery.getInt(columnIndexOrThrow13);
                arrayList.add(appInfo);
                columnIndexOrThrow2 = i3;
                columnIndexOrThrow4 = i;
                columnIndexOrThrow5 = i2;
            }
            cursorQuery.close();
            roomSQLiteQuery.release();
            return arrayList;
        } catch (Throwable th2) {
            th = th2;
            cursorQuery.close();
            roomSQLiteQuery.release();
            throw th;
        }
    }

    @Override // com.byd.card.db.AppInfoDao
    public List<AppInfo> getAllAppExceptPkg() throws Throwable {
        RoomSQLiteQuery roomSQLiteQuery;
        int columnIndexOrThrow;
        int columnIndexOrThrow2;
        int columnIndexOrThrow3;
        int columnIndexOrThrow4;
        int columnIndexOrThrow5;
        int columnIndexOrThrow6;
        int columnIndexOrThrow7;
        int columnIndexOrThrow8;
        int columnIndexOrThrow9;
        int columnIndexOrThrow10;
        int columnIndexOrThrow11;
        int columnIndexOrThrow12;
        int columnIndexOrThrow13;
        RoomSQLiteQuery roomSQLiteQueryAcquire = RoomSQLiteQuery.acquire("SELECT * FROM appinfo WHERE packageName not in ('com.byd.bluetoothcall','com.neusoft.na.navigation','com.byd.localmusic','com.byd.mycar','com.ts.carplay.app','com.ts.androidauto.app','com.byd.automap','com.byd.mycar.ts','com.huawei.maps.auto.app','net.zmap.znav.byd8155','com.tmap.auto.byd')", 0);
        this.__db.assertNotSuspendingTransaction();
        Cursor cursorQuery = DBUtil.query(this.__db, roomSQLiteQueryAcquire, false, null);
        try {
            columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(cursorQuery, "appId");
            columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "name");
            columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(cursorQuery, ShortcutInfo.Column.COLUMN_PINYIN);
            columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "packageName");
            columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "className");
            columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(cursorQuery, Constants.PackageInfo.ACTIVITY);
            columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "versionName");
            columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "versionCode");
            columnIndexOrThrow9 = CursorUtil.getColumnIndexOrThrow(cursorQuery, ShortcutInfo.Column.COLUMN_RECENT);
            columnIndexOrThrow10 = CursorUtil.getColumnIndexOrThrow(cursorQuery, ShortcutInfo.Column.COLUMN_RECENT_TIME);
            columnIndexOrThrow11 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "icons");
            columnIndexOrThrow12 = CursorUtil.getColumnIndexOrThrow(cursorQuery, ShortcutInfo.Column.COLUMN_COUNT);
            columnIndexOrThrow13 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "resizeMode");
            roomSQLiteQuery = roomSQLiteQueryAcquire;
        } catch (Throwable th) {
            th = th;
            roomSQLiteQuery = roomSQLiteQueryAcquire;
        }
        try {
            ArrayList arrayList = new ArrayList(cursorQuery.getCount());
            while (cursorQuery.moveToNext()) {
                int i = columnIndexOrThrow4;
                int i2 = columnIndexOrThrow5;
                AppInfo appInfo = new AppInfo(cursorQuery.getString(columnIndexOrThrow4), cursorQuery.getString(columnIndexOrThrow5));
                appInfo.appId = cursorQuery.getInt(columnIndexOrThrow);
                appInfo.name = cursorQuery.getString(columnIndexOrThrow2);
                appInfo.pinyin = cursorQuery.getString(columnIndexOrThrow3);
                appInfo.activity = cursorQuery.getString(columnIndexOrThrow6);
                appInfo.versionName = cursorQuery.getString(columnIndexOrThrow7);
                appInfo.versionCode = cursorQuery.getInt(columnIndexOrThrow8);
                appInfo.recent = cursorQuery.getInt(columnIndexOrThrow9);
                int i3 = columnIndexOrThrow2;
                appInfo.recentTime = cursorQuery.getLong(columnIndexOrThrow10);
                appInfo.icons = cursorQuery.getBlob(columnIndexOrThrow11);
                appInfo.count = cursorQuery.getInt(columnIndexOrThrow12);
                appInfo.resizeMode = cursorQuery.getInt(columnIndexOrThrow13);
                arrayList.add(appInfo);
                columnIndexOrThrow2 = i3;
                columnIndexOrThrow4 = i;
                columnIndexOrThrow5 = i2;
            }
            cursorQuery.close();
            roomSQLiteQuery.release();
            return arrayList;
        } catch (Throwable th2) {
            th = th2;
            cursorQuery.close();
            roomSQLiteQuery.release();
            throw th;
        }
    }
}
