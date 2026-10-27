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
import com.byd.card.bean.ShortcutInfo;
import com.byd.card.drag.ApplicationInfo;
import com.byd.mediacenter.api.media.MediaControl;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class ApplicationDao_Impl implements ApplicationDao {
    private final RoomDatabase __db;
    private final EntityDeletionOrUpdateAdapter<ApplicationInfo> __deletionAdapterOfApplicationInfo;
    private final EntityInsertionAdapter<ApplicationInfo> __insertionAdapterOfApplicationInfo;
    private final SharedSQLiteStatement __preparedStmtOfDeleteShortcutByOrigin;
    private final EntityDeletionOrUpdateAdapter<ApplicationInfo> __updateAdapterOfApplicationInfo;

    public ApplicationDao_Impl(RoomDatabase __db) {
        this.__db = __db;
        this.__insertionAdapterOfApplicationInfo = new EntityInsertionAdapter<ApplicationInfo>(__db) { // from class: com.byd.card.db.ApplicationDao_Impl.1
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "INSERT OR REPLACE INTO `application` (`title`,`pinyin`,`type`,`packageName`,`activity`,`versionName`,`versionCode`,`iconBytes`,`position`,`folderPosition`,`folderId`,`x`,`y`,`id`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,nullif(?, 0))";
            }

            @Override // androidx.room.EntityInsertionAdapter
            public void bind(SupportSQLiteStatement stmt, ApplicationInfo value) {
                if (value.title == null) {
                    stmt.bindNull(1);
                } else {
                    stmt.bindString(1, value.title);
                }
                if (value.pinyin == null) {
                    stmt.bindNull(2);
                } else {
                    stmt.bindString(2, value.pinyin);
                }
                stmt.bindLong(3, value.type);
                if (value.mPackageName == null) {
                    stmt.bindNull(4);
                } else {
                    stmt.bindString(4, value.mPackageName);
                }
                if (value.activity == null) {
                    stmt.bindNull(5);
                } else {
                    stmt.bindString(5, value.activity);
                }
                if (value.versionName == null) {
                    stmt.bindNull(6);
                } else {
                    stmt.bindString(6, value.versionName);
                }
                stmt.bindLong(7, value.versionCode);
                if (value.iconBytes == null) {
                    stmt.bindNull(8);
                } else {
                    stmt.bindBlob(8, value.iconBytes);
                }
                stmt.bindLong(9, value.position);
                stmt.bindLong(10, value.folderPosition);
                stmt.bindLong(11, value.folderId);
                stmt.bindLong(12, value.x);
                stmt.bindLong(13, value.y);
                stmt.bindLong(14, value.getId());
            }
        };
        this.__deletionAdapterOfApplicationInfo = new EntityDeletionOrUpdateAdapter<ApplicationInfo>(__db) { // from class: com.byd.card.db.ApplicationDao_Impl.2
            @Override // androidx.room.EntityDeletionOrUpdateAdapter, androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "DELETE FROM `application` WHERE `id` = ?";
            }

            @Override // androidx.room.EntityDeletionOrUpdateAdapter
            public void bind(SupportSQLiteStatement stmt, ApplicationInfo value) {
                stmt.bindLong(1, value.getId());
            }
        };
        this.__updateAdapterOfApplicationInfo = new EntityDeletionOrUpdateAdapter<ApplicationInfo>(__db) { // from class: com.byd.card.db.ApplicationDao_Impl.3
            @Override // androidx.room.EntityDeletionOrUpdateAdapter, androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "UPDATE OR ABORT `application` SET `title` = ?,`pinyin` = ?,`type` = ?,`packageName` = ?,`activity` = ?,`versionName` = ?,`versionCode` = ?,`iconBytes` = ?,`position` = ?,`folderPosition` = ?,`folderId` = ?,`x` = ?,`y` = ?,`id` = ? WHERE `id` = ?";
            }

            @Override // androidx.room.EntityDeletionOrUpdateAdapter
            public void bind(SupportSQLiteStatement stmt, ApplicationInfo value) {
                if (value.title == null) {
                    stmt.bindNull(1);
                } else {
                    stmt.bindString(1, value.title);
                }
                if (value.pinyin == null) {
                    stmt.bindNull(2);
                } else {
                    stmt.bindString(2, value.pinyin);
                }
                stmt.bindLong(3, value.type);
                if (value.mPackageName == null) {
                    stmt.bindNull(4);
                } else {
                    stmt.bindString(4, value.mPackageName);
                }
                if (value.activity == null) {
                    stmt.bindNull(5);
                } else {
                    stmt.bindString(5, value.activity);
                }
                if (value.versionName == null) {
                    stmt.bindNull(6);
                } else {
                    stmt.bindString(6, value.versionName);
                }
                stmt.bindLong(7, value.versionCode);
                if (value.iconBytes == null) {
                    stmt.bindNull(8);
                } else {
                    stmt.bindBlob(8, value.iconBytes);
                }
                stmt.bindLong(9, value.position);
                stmt.bindLong(10, value.folderPosition);
                stmt.bindLong(11, value.folderId);
                stmt.bindLong(12, value.x);
                stmt.bindLong(13, value.y);
                stmt.bindLong(14, value.getId());
                stmt.bindLong(15, value.getId());
            }
        };
        this.__preparedStmtOfDeleteShortcutByOrigin = new SharedSQLiteStatement(__db) { // from class: com.byd.card.db.ApplicationDao_Impl.4
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "DELETE FROM application WHERE type=3 and packageName LIKE ? ||'%'";
            }
        };
    }

    @Override // com.byd.card.db.ApplicationDao
    public void insert(final ApplicationInfo... applicationInfos) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__insertionAdapterOfApplicationInfo.insert(applicationInfos);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // com.byd.card.db.ApplicationDao
    public void insertList(final List<ApplicationInfo> applicationInfos) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__insertionAdapterOfApplicationInfo.insert(applicationInfos);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // com.byd.card.db.ApplicationDao
    public void delete(final ApplicationInfo... applicationInfos) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__deletionAdapterOfApplicationInfo.handleMultiple(applicationInfos);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // com.byd.card.db.ApplicationDao
    public void deleteList(final List<ApplicationInfo> applicationInfos) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__deletionAdapterOfApplicationInfo.handleMultiple(applicationInfos);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // com.byd.card.db.ApplicationDao
    public void update(final ApplicationInfo... applicationInfos) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__updateAdapterOfApplicationInfo.handleMultiple(applicationInfos);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // com.byd.card.db.ApplicationDao
    public void updateList(final List<ApplicationInfo> applicationInfos) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__updateAdapterOfApplicationInfo.handleMultiple(applicationInfos);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // com.byd.card.db.ApplicationDao
    public int deleteShortcutByOrigin(final String packageName) {
        this.__db.assertNotSuspendingTransaction();
        SupportSQLiteStatement supportSQLiteStatementAcquire = this.__preparedStmtOfDeleteShortcutByOrigin.acquire();
        if (packageName == null) {
            supportSQLiteStatementAcquire.bindNull(1);
        } else {
            supportSQLiteStatementAcquire.bindString(1, packageName);
        }
        this.__db.beginTransaction();
        try {
            int iExecuteUpdateDelete = supportSQLiteStatementAcquire.executeUpdateDelete();
            this.__db.setTransactionSuccessful();
            return iExecuteUpdateDelete;
        } finally {
            this.__db.endTransaction();
            this.__preparedStmtOfDeleteShortcutByOrigin.release(supportSQLiteStatementAcquire);
        }
    }

    @Override // com.byd.card.db.ApplicationDao
    public List<ApplicationInfo> getAllApps() throws Throwable {
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
        RoomSQLiteQuery roomSQLiteQueryAcquire = RoomSQLiteQuery.acquire("SELECT * FROM application order by position asc", 0);
        this.__db.assertNotSuspendingTransaction();
        Cursor cursorQuery = DBUtil.query(this.__db, roomSQLiteQueryAcquire, false, null);
        try {
            columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(cursorQuery, ShortcutInfo.Column.COLUMN_TITLE);
            columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(cursorQuery, ShortcutInfo.Column.COLUMN_PINYIN);
            columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "type");
            columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "packageName");
            columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(cursorQuery, Constants.PackageInfo.ACTIVITY);
            columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "versionName");
            columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "versionCode");
            columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "iconBytes");
            columnIndexOrThrow9 = CursorUtil.getColumnIndexOrThrow(cursorQuery, MediaControl.PARAM_POSITION);
            columnIndexOrThrow10 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "folderPosition");
            columnIndexOrThrow11 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "folderId");
            columnIndexOrThrow12 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "x");
            columnIndexOrThrow13 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "y");
            roomSQLiteQuery = roomSQLiteQueryAcquire;
        } catch (Throwable th) {
            th = th;
            roomSQLiteQuery = roomSQLiteQueryAcquire;
        }
        try {
            int columnIndexOrThrow14 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "id");
            ArrayList arrayList = new ArrayList(cursorQuery.getCount());
            while (cursorQuery.moveToNext()) {
                ApplicationInfo applicationInfo = new ApplicationInfo();
                ArrayList arrayList2 = arrayList;
                applicationInfo.title = cursorQuery.getString(columnIndexOrThrow);
                applicationInfo.pinyin = cursorQuery.getString(columnIndexOrThrow2);
                applicationInfo.type = cursorQuery.getInt(columnIndexOrThrow3);
                applicationInfo.mPackageName = cursorQuery.getString(columnIndexOrThrow4);
                applicationInfo.activity = cursorQuery.getString(columnIndexOrThrow5);
                applicationInfo.versionName = cursorQuery.getString(columnIndexOrThrow6);
                applicationInfo.versionCode = cursorQuery.getInt(columnIndexOrThrow7);
                applicationInfo.iconBytes = cursorQuery.getBlob(columnIndexOrThrow8);
                applicationInfo.position = cursorQuery.getInt(columnIndexOrThrow9);
                applicationInfo.folderPosition = cursorQuery.getInt(columnIndexOrThrow10);
                applicationInfo.folderId = cursorQuery.getInt(columnIndexOrThrow11);
                applicationInfo.x = cursorQuery.getInt(columnIndexOrThrow12);
                applicationInfo.y = cursorQuery.getInt(columnIndexOrThrow13);
                int i = columnIndexOrThrow14;
                int i2 = columnIndexOrThrow;
                applicationInfo.setId(cursorQuery.getInt(i));
                arrayList2.add(applicationInfo);
                arrayList = arrayList2;
                columnIndexOrThrow = i2;
                columnIndexOrThrow14 = i;
            }
            ArrayList arrayList3 = arrayList;
            cursorQuery.close();
            roomSQLiteQuery.release();
            return arrayList3;
        } catch (Throwable th2) {
            th = th2;
            cursorQuery.close();
            roomSQLiteQuery.release();
            throw th;
        }
    }

    @Override // com.byd.card.db.ApplicationDao
    public List<ApplicationInfo> getShortcutList() throws Throwable {
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
        RoomSQLiteQuery roomSQLiteQueryAcquire = RoomSQLiteQuery.acquire("SELECT * FROM application where type=3 order by position asc", 0);
        this.__db.assertNotSuspendingTransaction();
        Cursor cursorQuery = DBUtil.query(this.__db, roomSQLiteQueryAcquire, false, null);
        try {
            columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(cursorQuery, ShortcutInfo.Column.COLUMN_TITLE);
            columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(cursorQuery, ShortcutInfo.Column.COLUMN_PINYIN);
            columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "type");
            columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "packageName");
            columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(cursorQuery, Constants.PackageInfo.ACTIVITY);
            columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "versionName");
            columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "versionCode");
            columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "iconBytes");
            columnIndexOrThrow9 = CursorUtil.getColumnIndexOrThrow(cursorQuery, MediaControl.PARAM_POSITION);
            columnIndexOrThrow10 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "folderPosition");
            columnIndexOrThrow11 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "folderId");
            columnIndexOrThrow12 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "x");
            columnIndexOrThrow13 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "y");
            roomSQLiteQuery = roomSQLiteQueryAcquire;
        } catch (Throwable th) {
            th = th;
            roomSQLiteQuery = roomSQLiteQueryAcquire;
        }
        try {
            int columnIndexOrThrow14 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "id");
            ArrayList arrayList = new ArrayList(cursorQuery.getCount());
            while (cursorQuery.moveToNext()) {
                ApplicationInfo applicationInfo = new ApplicationInfo();
                ArrayList arrayList2 = arrayList;
                applicationInfo.title = cursorQuery.getString(columnIndexOrThrow);
                applicationInfo.pinyin = cursorQuery.getString(columnIndexOrThrow2);
                applicationInfo.type = cursorQuery.getInt(columnIndexOrThrow3);
                applicationInfo.mPackageName = cursorQuery.getString(columnIndexOrThrow4);
                applicationInfo.activity = cursorQuery.getString(columnIndexOrThrow5);
                applicationInfo.versionName = cursorQuery.getString(columnIndexOrThrow6);
                applicationInfo.versionCode = cursorQuery.getInt(columnIndexOrThrow7);
                applicationInfo.iconBytes = cursorQuery.getBlob(columnIndexOrThrow8);
                applicationInfo.position = cursorQuery.getInt(columnIndexOrThrow9);
                applicationInfo.folderPosition = cursorQuery.getInt(columnIndexOrThrow10);
                applicationInfo.folderId = cursorQuery.getInt(columnIndexOrThrow11);
                applicationInfo.x = cursorQuery.getInt(columnIndexOrThrow12);
                applicationInfo.y = cursorQuery.getInt(columnIndexOrThrow13);
                int i = columnIndexOrThrow14;
                int i2 = columnIndexOrThrow;
                applicationInfo.setId(cursorQuery.getInt(i));
                arrayList2.add(applicationInfo);
                arrayList = arrayList2;
                columnIndexOrThrow = i2;
                columnIndexOrThrow14 = i;
            }
            ArrayList arrayList3 = arrayList;
            cursorQuery.close();
            roomSQLiteQuery.release();
            return arrayList3;
        } catch (Throwable th2) {
            th = th2;
            cursorQuery.close();
            roomSQLiteQuery.release();
            throw th;
        }
    }

    @Override // com.byd.card.db.ApplicationDao
    public List<ApplicationInfo> getExceptFolderAllApp() throws Throwable {
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
        RoomSQLiteQuery roomSQLiteQueryAcquire = RoomSQLiteQuery.acquire("SELECT * FROM application where folderId=0", 0);
        this.__db.assertNotSuspendingTransaction();
        Cursor cursorQuery = DBUtil.query(this.__db, roomSQLiteQueryAcquire, false, null);
        try {
            columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(cursorQuery, ShortcutInfo.Column.COLUMN_TITLE);
            columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(cursorQuery, ShortcutInfo.Column.COLUMN_PINYIN);
            columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "type");
            columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "packageName");
            columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(cursorQuery, Constants.PackageInfo.ACTIVITY);
            columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "versionName");
            columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "versionCode");
            columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "iconBytes");
            columnIndexOrThrow9 = CursorUtil.getColumnIndexOrThrow(cursorQuery, MediaControl.PARAM_POSITION);
            columnIndexOrThrow10 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "folderPosition");
            columnIndexOrThrow11 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "folderId");
            columnIndexOrThrow12 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "x");
            columnIndexOrThrow13 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "y");
            roomSQLiteQuery = roomSQLiteQueryAcquire;
        } catch (Throwable th) {
            th = th;
            roomSQLiteQuery = roomSQLiteQueryAcquire;
        }
        try {
            int columnIndexOrThrow14 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "id");
            ArrayList arrayList = new ArrayList(cursorQuery.getCount());
            while (cursorQuery.moveToNext()) {
                ApplicationInfo applicationInfo = new ApplicationInfo();
                ArrayList arrayList2 = arrayList;
                applicationInfo.title = cursorQuery.getString(columnIndexOrThrow);
                applicationInfo.pinyin = cursorQuery.getString(columnIndexOrThrow2);
                applicationInfo.type = cursorQuery.getInt(columnIndexOrThrow3);
                applicationInfo.mPackageName = cursorQuery.getString(columnIndexOrThrow4);
                applicationInfo.activity = cursorQuery.getString(columnIndexOrThrow5);
                applicationInfo.versionName = cursorQuery.getString(columnIndexOrThrow6);
                applicationInfo.versionCode = cursorQuery.getInt(columnIndexOrThrow7);
                applicationInfo.iconBytes = cursorQuery.getBlob(columnIndexOrThrow8);
                applicationInfo.position = cursorQuery.getInt(columnIndexOrThrow9);
                applicationInfo.folderPosition = cursorQuery.getInt(columnIndexOrThrow10);
                applicationInfo.folderId = cursorQuery.getInt(columnIndexOrThrow11);
                applicationInfo.x = cursorQuery.getInt(columnIndexOrThrow12);
                applicationInfo.y = cursorQuery.getInt(columnIndexOrThrow13);
                int i = columnIndexOrThrow14;
                int i2 = columnIndexOrThrow;
                applicationInfo.setId(cursorQuery.getInt(i));
                arrayList2.add(applicationInfo);
                arrayList = arrayList2;
                columnIndexOrThrow = i2;
                columnIndexOrThrow14 = i;
            }
            ArrayList arrayList3 = arrayList;
            cursorQuery.close();
            roomSQLiteQuery.release();
            return arrayList3;
        } catch (Throwable th2) {
            th = th2;
            cursorQuery.close();
            roomSQLiteQuery.release();
            throw th;
        }
    }

    @Override // com.byd.card.db.ApplicationDao
    public List<ApplicationInfo> getFolderListByName(final String name) throws Throwable {
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
        RoomSQLiteQuery roomSQLiteQueryAcquire = RoomSQLiteQuery.acquire("SELECT * FROM application WHERE title=?", 1);
        if (name == null) {
            roomSQLiteQueryAcquire.bindNull(1);
        } else {
            roomSQLiteQueryAcquire.bindString(1, name);
        }
        this.__db.assertNotSuspendingTransaction();
        Cursor cursorQuery = DBUtil.query(this.__db, roomSQLiteQueryAcquire, false, null);
        try {
            columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(cursorQuery, ShortcutInfo.Column.COLUMN_TITLE);
            columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(cursorQuery, ShortcutInfo.Column.COLUMN_PINYIN);
            columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "type");
            columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "packageName");
            columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(cursorQuery, Constants.PackageInfo.ACTIVITY);
            columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "versionName");
            columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "versionCode");
            columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "iconBytes");
            columnIndexOrThrow9 = CursorUtil.getColumnIndexOrThrow(cursorQuery, MediaControl.PARAM_POSITION);
            columnIndexOrThrow10 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "folderPosition");
            columnIndexOrThrow11 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "folderId");
            columnIndexOrThrow12 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "x");
            columnIndexOrThrow13 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "y");
            roomSQLiteQuery = roomSQLiteQueryAcquire;
        } catch (Throwable th) {
            th = th;
            roomSQLiteQuery = roomSQLiteQueryAcquire;
        }
        try {
            int columnIndexOrThrow14 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "id");
            ArrayList arrayList = new ArrayList(cursorQuery.getCount());
            while (cursorQuery.moveToNext()) {
                ApplicationInfo applicationInfo = new ApplicationInfo();
                ArrayList arrayList2 = arrayList;
                applicationInfo.title = cursorQuery.getString(columnIndexOrThrow);
                applicationInfo.pinyin = cursorQuery.getString(columnIndexOrThrow2);
                applicationInfo.type = cursorQuery.getInt(columnIndexOrThrow3);
                applicationInfo.mPackageName = cursorQuery.getString(columnIndexOrThrow4);
                applicationInfo.activity = cursorQuery.getString(columnIndexOrThrow5);
                applicationInfo.versionName = cursorQuery.getString(columnIndexOrThrow6);
                applicationInfo.versionCode = cursorQuery.getInt(columnIndexOrThrow7);
                applicationInfo.iconBytes = cursorQuery.getBlob(columnIndexOrThrow8);
                applicationInfo.position = cursorQuery.getInt(columnIndexOrThrow9);
                applicationInfo.folderPosition = cursorQuery.getInt(columnIndexOrThrow10);
                applicationInfo.folderId = cursorQuery.getInt(columnIndexOrThrow11);
                applicationInfo.x = cursorQuery.getInt(columnIndexOrThrow12);
                applicationInfo.y = cursorQuery.getInt(columnIndexOrThrow13);
                int i = columnIndexOrThrow14;
                int i2 = columnIndexOrThrow;
                applicationInfo.setId(cursorQuery.getInt(i));
                arrayList2.add(applicationInfo);
                columnIndexOrThrow = i2;
                columnIndexOrThrow14 = i;
                arrayList = arrayList2;
            }
            ArrayList arrayList3 = arrayList;
            cursorQuery.close();
            roomSQLiteQuery.release();
            return arrayList3;
        } catch (Throwable th2) {
            th = th2;
            cursorQuery.close();
            roomSQLiteQuery.release();
            throw th;
        }
    }

    @Override // com.byd.card.db.ApplicationDao
    public ApplicationInfo getAppByPackageName(final String packageName) throws Throwable {
        RoomSQLiteQuery roomSQLiteQuery;
        ApplicationInfo applicationInfo;
        RoomSQLiteQuery roomSQLiteQueryAcquire = RoomSQLiteQuery.acquire("SELECT * FROM application WHERE packageName=?", 1);
        if (packageName == null) {
            roomSQLiteQueryAcquire.bindNull(1);
        } else {
            roomSQLiteQueryAcquire.bindString(1, packageName);
        }
        this.__db.assertNotSuspendingTransaction();
        Cursor cursorQuery = DBUtil.query(this.__db, roomSQLiteQueryAcquire, false, null);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(cursorQuery, ShortcutInfo.Column.COLUMN_TITLE);
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(cursorQuery, ShortcutInfo.Column.COLUMN_PINYIN);
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "type");
            int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "packageName");
            int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(cursorQuery, Constants.PackageInfo.ACTIVITY);
            int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "versionName");
            int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "versionCode");
            int columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "iconBytes");
            int columnIndexOrThrow9 = CursorUtil.getColumnIndexOrThrow(cursorQuery, MediaControl.PARAM_POSITION);
            int columnIndexOrThrow10 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "folderPosition");
            int columnIndexOrThrow11 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "folderId");
            int columnIndexOrThrow12 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "x");
            int columnIndexOrThrow13 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "y");
            int columnIndexOrThrow14 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "id");
            if (cursorQuery.moveToFirst()) {
                roomSQLiteQuery = roomSQLiteQueryAcquire;
                try {
                    ApplicationInfo applicationInfo2 = new ApplicationInfo();
                    applicationInfo2.title = cursorQuery.getString(columnIndexOrThrow);
                    applicationInfo2.pinyin = cursorQuery.getString(columnIndexOrThrow2);
                    applicationInfo2.type = cursorQuery.getInt(columnIndexOrThrow3);
                    applicationInfo2.mPackageName = cursorQuery.getString(columnIndexOrThrow4);
                    applicationInfo2.activity = cursorQuery.getString(columnIndexOrThrow5);
                    applicationInfo2.versionName = cursorQuery.getString(columnIndexOrThrow6);
                    applicationInfo2.versionCode = cursorQuery.getInt(columnIndexOrThrow7);
                    applicationInfo2.iconBytes = cursorQuery.getBlob(columnIndexOrThrow8);
                    applicationInfo2.position = cursorQuery.getInt(columnIndexOrThrow9);
                    applicationInfo2.folderPosition = cursorQuery.getInt(columnIndexOrThrow10);
                    applicationInfo2.folderId = cursorQuery.getInt(columnIndexOrThrow11);
                    applicationInfo2.x = cursorQuery.getInt(columnIndexOrThrow12);
                    applicationInfo2.y = cursorQuery.getInt(columnIndexOrThrow13);
                    applicationInfo2.setId(cursorQuery.getInt(columnIndexOrThrow14));
                    applicationInfo = applicationInfo2;
                } catch (Throwable th) {
                    th = th;
                    cursorQuery.close();
                    roomSQLiteQuery.release();
                    throw th;
                }
            } else {
                roomSQLiteQuery = roomSQLiteQueryAcquire;
                applicationInfo = null;
            }
            cursorQuery.close();
            roomSQLiteQuery.release();
            return applicationInfo;
        } catch (Throwable th2) {
            th = th2;
            roomSQLiteQuery = roomSQLiteQueryAcquire;
        }
    }

    @Override // com.byd.card.db.ApplicationDao
    public ApplicationInfo getAppByPosition(final int position) throws Throwable {
        RoomSQLiteQuery roomSQLiteQuery;
        ApplicationInfo applicationInfo;
        RoomSQLiteQuery roomSQLiteQueryAcquire = RoomSQLiteQuery.acquire("SELECT * FROM application WHERE position=?", 1);
        roomSQLiteQueryAcquire.bindLong(1, position);
        this.__db.assertNotSuspendingTransaction();
        Cursor cursorQuery = DBUtil.query(this.__db, roomSQLiteQueryAcquire, false, null);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(cursorQuery, ShortcutInfo.Column.COLUMN_TITLE);
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(cursorQuery, ShortcutInfo.Column.COLUMN_PINYIN);
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "type");
            int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "packageName");
            int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(cursorQuery, Constants.PackageInfo.ACTIVITY);
            int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "versionName");
            int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "versionCode");
            int columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "iconBytes");
            int columnIndexOrThrow9 = CursorUtil.getColumnIndexOrThrow(cursorQuery, MediaControl.PARAM_POSITION);
            int columnIndexOrThrow10 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "folderPosition");
            int columnIndexOrThrow11 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "folderId");
            int columnIndexOrThrow12 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "x");
            int columnIndexOrThrow13 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "y");
            int columnIndexOrThrow14 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "id");
            if (cursorQuery.moveToFirst()) {
                roomSQLiteQuery = roomSQLiteQueryAcquire;
                try {
                    ApplicationInfo applicationInfo2 = new ApplicationInfo();
                    applicationInfo2.title = cursorQuery.getString(columnIndexOrThrow);
                    applicationInfo2.pinyin = cursorQuery.getString(columnIndexOrThrow2);
                    applicationInfo2.type = cursorQuery.getInt(columnIndexOrThrow3);
                    applicationInfo2.mPackageName = cursorQuery.getString(columnIndexOrThrow4);
                    applicationInfo2.activity = cursorQuery.getString(columnIndexOrThrow5);
                    applicationInfo2.versionName = cursorQuery.getString(columnIndexOrThrow6);
                    applicationInfo2.versionCode = cursorQuery.getInt(columnIndexOrThrow7);
                    applicationInfo2.iconBytes = cursorQuery.getBlob(columnIndexOrThrow8);
                    applicationInfo2.position = cursorQuery.getInt(columnIndexOrThrow9);
                    applicationInfo2.folderPosition = cursorQuery.getInt(columnIndexOrThrow10);
                    applicationInfo2.folderId = cursorQuery.getInt(columnIndexOrThrow11);
                    applicationInfo2.x = cursorQuery.getInt(columnIndexOrThrow12);
                    applicationInfo2.y = cursorQuery.getInt(columnIndexOrThrow13);
                    applicationInfo2.setId(cursorQuery.getInt(columnIndexOrThrow14));
                    applicationInfo = applicationInfo2;
                } catch (Throwable th) {
                    th = th;
                    cursorQuery.close();
                    roomSQLiteQuery.release();
                    throw th;
                }
            } else {
                roomSQLiteQuery = roomSQLiteQueryAcquire;
                applicationInfo = null;
            }
            cursorQuery.close();
            roomSQLiteQuery.release();
            return applicationInfo;
        } catch (Throwable th2) {
            th = th2;
            roomSQLiteQuery = roomSQLiteQueryAcquire;
        }
    }

    @Override // com.byd.card.db.ApplicationDao
    public ApplicationInfo getAppByActivity(final String activity) throws Throwable {
        RoomSQLiteQuery roomSQLiteQuery;
        ApplicationInfo applicationInfo;
        RoomSQLiteQuery roomSQLiteQueryAcquire = RoomSQLiteQuery.acquire("SELECT * FROM application WHERE activity=?", 1);
        if (activity == null) {
            roomSQLiteQueryAcquire.bindNull(1);
        } else {
            roomSQLiteQueryAcquire.bindString(1, activity);
        }
        this.__db.assertNotSuspendingTransaction();
        Cursor cursorQuery = DBUtil.query(this.__db, roomSQLiteQueryAcquire, false, null);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(cursorQuery, ShortcutInfo.Column.COLUMN_TITLE);
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(cursorQuery, ShortcutInfo.Column.COLUMN_PINYIN);
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "type");
            int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "packageName");
            int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(cursorQuery, Constants.PackageInfo.ACTIVITY);
            int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "versionName");
            int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "versionCode");
            int columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "iconBytes");
            int columnIndexOrThrow9 = CursorUtil.getColumnIndexOrThrow(cursorQuery, MediaControl.PARAM_POSITION);
            int columnIndexOrThrow10 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "folderPosition");
            int columnIndexOrThrow11 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "folderId");
            int columnIndexOrThrow12 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "x");
            int columnIndexOrThrow13 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "y");
            int columnIndexOrThrow14 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "id");
            if (cursorQuery.moveToFirst()) {
                roomSQLiteQuery = roomSQLiteQueryAcquire;
                try {
                    ApplicationInfo applicationInfo2 = new ApplicationInfo();
                    applicationInfo2.title = cursorQuery.getString(columnIndexOrThrow);
                    applicationInfo2.pinyin = cursorQuery.getString(columnIndexOrThrow2);
                    applicationInfo2.type = cursorQuery.getInt(columnIndexOrThrow3);
                    applicationInfo2.mPackageName = cursorQuery.getString(columnIndexOrThrow4);
                    applicationInfo2.activity = cursorQuery.getString(columnIndexOrThrow5);
                    applicationInfo2.versionName = cursorQuery.getString(columnIndexOrThrow6);
                    applicationInfo2.versionCode = cursorQuery.getInt(columnIndexOrThrow7);
                    applicationInfo2.iconBytes = cursorQuery.getBlob(columnIndexOrThrow8);
                    applicationInfo2.position = cursorQuery.getInt(columnIndexOrThrow9);
                    applicationInfo2.folderPosition = cursorQuery.getInt(columnIndexOrThrow10);
                    applicationInfo2.folderId = cursorQuery.getInt(columnIndexOrThrow11);
                    applicationInfo2.x = cursorQuery.getInt(columnIndexOrThrow12);
                    applicationInfo2.y = cursorQuery.getInt(columnIndexOrThrow13);
                    applicationInfo2.setId(cursorQuery.getInt(columnIndexOrThrow14));
                    applicationInfo = applicationInfo2;
                } catch (Throwable th) {
                    th = th;
                    cursorQuery.close();
                    roomSQLiteQuery.release();
                    throw th;
                }
            } else {
                roomSQLiteQuery = roomSQLiteQueryAcquire;
                applicationInfo = null;
            }
            cursorQuery.close();
            roomSQLiteQuery.release();
            return applicationInfo;
        } catch (Throwable th2) {
            th = th2;
            roomSQLiteQuery = roomSQLiteQueryAcquire;
        }
    }

    @Override // com.byd.card.db.ApplicationDao
    public ApplicationInfo getAppByTitle(final String title) throws Throwable {
        RoomSQLiteQuery roomSQLiteQuery;
        ApplicationInfo applicationInfo;
        RoomSQLiteQuery roomSQLiteQueryAcquire = RoomSQLiteQuery.acquire("SELECT * FROM application WHERE title=?", 1);
        if (title == null) {
            roomSQLiteQueryAcquire.bindNull(1);
        } else {
            roomSQLiteQueryAcquire.bindString(1, title);
        }
        this.__db.assertNotSuspendingTransaction();
        Cursor cursorQuery = DBUtil.query(this.__db, roomSQLiteQueryAcquire, false, null);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(cursorQuery, ShortcutInfo.Column.COLUMN_TITLE);
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(cursorQuery, ShortcutInfo.Column.COLUMN_PINYIN);
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "type");
            int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "packageName");
            int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(cursorQuery, Constants.PackageInfo.ACTIVITY);
            int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "versionName");
            int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "versionCode");
            int columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "iconBytes");
            int columnIndexOrThrow9 = CursorUtil.getColumnIndexOrThrow(cursorQuery, MediaControl.PARAM_POSITION);
            int columnIndexOrThrow10 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "folderPosition");
            int columnIndexOrThrow11 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "folderId");
            int columnIndexOrThrow12 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "x");
            int columnIndexOrThrow13 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "y");
            int columnIndexOrThrow14 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "id");
            if (cursorQuery.moveToFirst()) {
                roomSQLiteQuery = roomSQLiteQueryAcquire;
                try {
                    ApplicationInfo applicationInfo2 = new ApplicationInfo();
                    applicationInfo2.title = cursorQuery.getString(columnIndexOrThrow);
                    applicationInfo2.pinyin = cursorQuery.getString(columnIndexOrThrow2);
                    applicationInfo2.type = cursorQuery.getInt(columnIndexOrThrow3);
                    applicationInfo2.mPackageName = cursorQuery.getString(columnIndexOrThrow4);
                    applicationInfo2.activity = cursorQuery.getString(columnIndexOrThrow5);
                    applicationInfo2.versionName = cursorQuery.getString(columnIndexOrThrow6);
                    applicationInfo2.versionCode = cursorQuery.getInt(columnIndexOrThrow7);
                    applicationInfo2.iconBytes = cursorQuery.getBlob(columnIndexOrThrow8);
                    applicationInfo2.position = cursorQuery.getInt(columnIndexOrThrow9);
                    applicationInfo2.folderPosition = cursorQuery.getInt(columnIndexOrThrow10);
                    applicationInfo2.folderId = cursorQuery.getInt(columnIndexOrThrow11);
                    applicationInfo2.x = cursorQuery.getInt(columnIndexOrThrow12);
                    applicationInfo2.y = cursorQuery.getInt(columnIndexOrThrow13);
                    applicationInfo2.setId(cursorQuery.getInt(columnIndexOrThrow14));
                    applicationInfo = applicationInfo2;
                } catch (Throwable th) {
                    th = th;
                    cursorQuery.close();
                    roomSQLiteQuery.release();
                    throw th;
                }
            } else {
                roomSQLiteQuery = roomSQLiteQueryAcquire;
                applicationInfo = null;
            }
            cursorQuery.close();
            roomSQLiteQuery.release();
            return applicationInfo;
        } catch (Throwable th2) {
            th = th2;
            roomSQLiteQuery = roomSQLiteQueryAcquire;
        }
    }

    @Override // com.byd.card.db.ApplicationDao
    public List<ApplicationInfo> getListByFolderId(final long folderId) throws Throwable {
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
        RoomSQLiteQuery roomSQLiteQueryAcquire = RoomSQLiteQuery.acquire("SELECT * FROM application WHERE folderId=? order by folderPosition asc", 1);
        roomSQLiteQueryAcquire.bindLong(1, folderId);
        this.__db.assertNotSuspendingTransaction();
        Cursor cursorQuery = DBUtil.query(this.__db, roomSQLiteQueryAcquire, false, null);
        try {
            columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(cursorQuery, ShortcutInfo.Column.COLUMN_TITLE);
            columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(cursorQuery, ShortcutInfo.Column.COLUMN_PINYIN);
            columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "type");
            columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "packageName");
            columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(cursorQuery, Constants.PackageInfo.ACTIVITY);
            columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "versionName");
            columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "versionCode");
            columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "iconBytes");
            columnIndexOrThrow9 = CursorUtil.getColumnIndexOrThrow(cursorQuery, MediaControl.PARAM_POSITION);
            columnIndexOrThrow10 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "folderPosition");
            columnIndexOrThrow11 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "folderId");
            columnIndexOrThrow12 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "x");
            columnIndexOrThrow13 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "y");
            roomSQLiteQuery = roomSQLiteQueryAcquire;
        } catch (Throwable th) {
            th = th;
            roomSQLiteQuery = roomSQLiteQueryAcquire;
        }
        try {
            int columnIndexOrThrow14 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "id");
            ArrayList arrayList = new ArrayList(cursorQuery.getCount());
            while (cursorQuery.moveToNext()) {
                ApplicationInfo applicationInfo = new ApplicationInfo();
                ArrayList arrayList2 = arrayList;
                applicationInfo.title = cursorQuery.getString(columnIndexOrThrow);
                applicationInfo.pinyin = cursorQuery.getString(columnIndexOrThrow2);
                applicationInfo.type = cursorQuery.getInt(columnIndexOrThrow3);
                applicationInfo.mPackageName = cursorQuery.getString(columnIndexOrThrow4);
                applicationInfo.activity = cursorQuery.getString(columnIndexOrThrow5);
                applicationInfo.versionName = cursorQuery.getString(columnIndexOrThrow6);
                applicationInfo.versionCode = cursorQuery.getInt(columnIndexOrThrow7);
                applicationInfo.iconBytes = cursorQuery.getBlob(columnIndexOrThrow8);
                applicationInfo.position = cursorQuery.getInt(columnIndexOrThrow9);
                applicationInfo.folderPosition = cursorQuery.getInt(columnIndexOrThrow10);
                applicationInfo.folderId = cursorQuery.getInt(columnIndexOrThrow11);
                applicationInfo.x = cursorQuery.getInt(columnIndexOrThrow12);
                applicationInfo.y = cursorQuery.getInt(columnIndexOrThrow13);
                int i = columnIndexOrThrow14;
                int i2 = columnIndexOrThrow;
                applicationInfo.setId(cursorQuery.getInt(i));
                arrayList2.add(applicationInfo);
                columnIndexOrThrow = i2;
                columnIndexOrThrow14 = i;
                arrayList = arrayList2;
            }
            ArrayList arrayList3 = arrayList;
            cursorQuery.close();
            roomSQLiteQuery.release();
            return arrayList3;
        } catch (Throwable th2) {
            th = th2;
            cursorQuery.close();
            roomSQLiteQuery.release();
            throw th;
        }
    }

    @Override // com.byd.card.db.ApplicationDao
    public int getMaxPosition() {
        RoomSQLiteQuery roomSQLiteQueryAcquire = RoomSQLiteQuery.acquire("SELECT max(position) AS Maximum FROM application", 0);
        this.__db.assertNotSuspendingTransaction();
        Cursor cursorQuery = DBUtil.query(this.__db, roomSQLiteQueryAcquire, false, null);
        try {
            return cursorQuery.moveToFirst() ? cursorQuery.getInt(0) : 0;
        } finally {
            cursorQuery.close();
            roomSQLiteQueryAcquire.release();
        }
    }
}
