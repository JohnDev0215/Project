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
import com.byd.card.bean.ShortcutInfo;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class ShortcutInfoDao_Impl implements ShortcutInfoDao {
    private final RoomDatabase __db;
    private final EntityDeletionOrUpdateAdapter<ShortcutInfo> __deletionAdapterOfShortcutInfo;
    private final EntityInsertionAdapter<ShortcutInfo> __insertionAdapterOfShortcutInfo;
    private final SharedSQLiteStatement __preparedStmtOfDeleteShortcutByOrigin;
    private final EntityDeletionOrUpdateAdapter<ShortcutInfo> __updateAdapterOfShortcutInfo;

    public ShortcutInfoDao_Impl(RoomDatabase __db) {
        this.__db = __db;
        this.__insertionAdapterOfShortcutInfo = new EntityInsertionAdapter<ShortcutInfo>(__db) { // from class: com.byd.card.db.ShortcutInfoDao_Impl.1
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "INSERT OR REPLACE INTO `shortcut_info` (`shortcutId`,`identify`,`title`,`origin`,`pinyin`,`icon`,`intent`,`pGearFlag`,`recent`,`recentTime`,`count`,`reserve1`,`reserve2`,`reserve3`) VALUES (nullif(?, 0),?,?,?,?,?,?,?,?,?,?,?,?,?)";
            }

            @Override // androidx.room.EntityInsertionAdapter
            public void bind(SupportSQLiteStatement stmt, ShortcutInfo value) {
                stmt.bindLong(1, value.shortcutId);
                if (value.identify == null) {
                    stmt.bindNull(2);
                } else {
                    stmt.bindString(2, value.identify);
                }
                if (value.title == null) {
                    stmt.bindNull(3);
                } else {
                    stmt.bindString(3, value.title);
                }
                if (value.origin == null) {
                    stmt.bindNull(4);
                } else {
                    stmt.bindString(4, value.origin);
                }
                if (value.pinyin == null) {
                    stmt.bindNull(5);
                } else {
                    stmt.bindString(5, value.pinyin);
                }
                if (value.icon == null) {
                    stmt.bindNull(6);
                } else {
                    stmt.bindBlob(6, value.icon);
                }
                if (value.intent == null) {
                    stmt.bindNull(7);
                } else {
                    stmt.bindString(7, value.intent);
                }
                stmt.bindLong(8, value.pGearFlag);
                stmt.bindLong(9, value.recent);
                stmt.bindLong(10, value.recentTime);
                stmt.bindLong(11, value.count);
                if (value.reserve1 == null) {
                    stmt.bindNull(12);
                } else {
                    stmt.bindString(12, value.reserve1);
                }
                if (value.reserve2 == null) {
                    stmt.bindNull(13);
                } else {
                    stmt.bindString(13, value.reserve2);
                }
                if (value.reserve3 == null) {
                    stmt.bindNull(14);
                } else {
                    stmt.bindString(14, value.reserve3);
                }
            }
        };
        this.__deletionAdapterOfShortcutInfo = new EntityDeletionOrUpdateAdapter<ShortcutInfo>(__db) { // from class: com.byd.card.db.ShortcutInfoDao_Impl.2
            @Override // androidx.room.EntityDeletionOrUpdateAdapter, androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "DELETE FROM `shortcut_info` WHERE `shortcutId` = ?";
            }

            @Override // androidx.room.EntityDeletionOrUpdateAdapter
            public void bind(SupportSQLiteStatement stmt, ShortcutInfo value) {
                stmt.bindLong(1, value.shortcutId);
            }
        };
        this.__updateAdapterOfShortcutInfo = new EntityDeletionOrUpdateAdapter<ShortcutInfo>(__db) { // from class: com.byd.card.db.ShortcutInfoDao_Impl.3
            @Override // androidx.room.EntityDeletionOrUpdateAdapter, androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "UPDATE OR ABORT `shortcut_info` SET `shortcutId` = ?,`identify` = ?,`title` = ?,`origin` = ?,`pinyin` = ?,`icon` = ?,`intent` = ?,`pGearFlag` = ?,`recent` = ?,`recentTime` = ?,`count` = ?,`reserve1` = ?,`reserve2` = ?,`reserve3` = ? WHERE `shortcutId` = ?";
            }

            @Override // androidx.room.EntityDeletionOrUpdateAdapter
            public void bind(SupportSQLiteStatement stmt, ShortcutInfo value) {
                stmt.bindLong(1, value.shortcutId);
                if (value.identify == null) {
                    stmt.bindNull(2);
                } else {
                    stmt.bindString(2, value.identify);
                }
                if (value.title == null) {
                    stmt.bindNull(3);
                } else {
                    stmt.bindString(3, value.title);
                }
                if (value.origin == null) {
                    stmt.bindNull(4);
                } else {
                    stmt.bindString(4, value.origin);
                }
                if (value.pinyin == null) {
                    stmt.bindNull(5);
                } else {
                    stmt.bindString(5, value.pinyin);
                }
                if (value.icon == null) {
                    stmt.bindNull(6);
                } else {
                    stmt.bindBlob(6, value.icon);
                }
                if (value.intent == null) {
                    stmt.bindNull(7);
                } else {
                    stmt.bindString(7, value.intent);
                }
                stmt.bindLong(8, value.pGearFlag);
                stmt.bindLong(9, value.recent);
                stmt.bindLong(10, value.recentTime);
                stmt.bindLong(11, value.count);
                if (value.reserve1 == null) {
                    stmt.bindNull(12);
                } else {
                    stmt.bindString(12, value.reserve1);
                }
                if (value.reserve2 == null) {
                    stmt.bindNull(13);
                } else {
                    stmt.bindString(13, value.reserve2);
                }
                if (value.reserve3 == null) {
                    stmt.bindNull(14);
                } else {
                    stmt.bindString(14, value.reserve3);
                }
                stmt.bindLong(15, value.shortcutId);
            }
        };
        this.__preparedStmtOfDeleteShortcutByOrigin = new SharedSQLiteStatement(__db) { // from class: com.byd.card.db.ShortcutInfoDao_Impl.4
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "DELETE FROM shortcut_info WHERE origin=?";
            }
        };
    }

    @Override // com.byd.card.db.ShortcutInfoDao
    public void insert(final ShortcutInfo... shortcutInfos) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__insertionAdapterOfShortcutInfo.insert(shortcutInfos);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // com.byd.card.db.ShortcutInfoDao
    public void insertList(final List<ShortcutInfo> shortcutInfos) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__insertionAdapterOfShortcutInfo.insert(shortcutInfos);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // com.byd.card.db.ShortcutInfoDao
    public void delete(final ShortcutInfo... shortcutInfos) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__deletionAdapterOfShortcutInfo.handleMultiple(shortcutInfos);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // com.byd.card.db.ShortcutInfoDao
    public void deleteList(final List<ShortcutInfo> shortcutInfos) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__deletionAdapterOfShortcutInfo.handleMultiple(shortcutInfos);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // com.byd.card.db.ShortcutInfoDao
    public void update(final ShortcutInfo... shortcutInfos) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__updateAdapterOfShortcutInfo.handleMultiple(shortcutInfos);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // com.byd.card.db.ShortcutInfoDao
    public void updateList(final List<ShortcutInfo> shortcutInfos) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__updateAdapterOfShortcutInfo.handleMultiple(shortcutInfos);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // com.byd.card.db.ShortcutInfoDao
    public void deleteShortcutByOrigin(final String origin) {
        this.__db.assertNotSuspendingTransaction();
        SupportSQLiteStatement supportSQLiteStatementAcquire = this.__preparedStmtOfDeleteShortcutByOrigin.acquire();
        if (origin == null) {
            supportSQLiteStatementAcquire.bindNull(1);
        } else {
            supportSQLiteStatementAcquire.bindString(1, origin);
        }
        this.__db.beginTransaction();
        try {
            supportSQLiteStatementAcquire.executeUpdateDelete();
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
            this.__preparedStmtOfDeleteShortcutByOrigin.release(supportSQLiteStatementAcquire);
        }
    }

    @Override // com.byd.card.db.ShortcutInfoDao
    public List<ShortcutInfo> getListForExact(final String inputText) throws Throwable {
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
        int columnIndexOrThrow14;
        RoomSQLiteQuery roomSQLiteQueryAcquire = RoomSQLiteQuery.acquire("SELECT * FROM shortcut_info WHERE title=? or pinyin=? order by count desc", 2);
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
            columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(cursorQuery, ShortcutInfo.Column.COLUMN_SHORTCUT_ID);
            columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(cursorQuery, ShortcutInfo.Column.COLUMN_IDENTIFY);
            columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(cursorQuery, ShortcutInfo.Column.COLUMN_TITLE);
            columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "origin");
            columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(cursorQuery, ShortcutInfo.Column.COLUMN_PINYIN);
            columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(cursorQuery, ShortcutInfo.Column.COLUMN_ICON);
            columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(cursorQuery, ShortcutInfo.Column.COLUMN_INTENT);
            columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(cursorQuery, ShortcutInfo.Column.COLUMN_P_GEAR_FLAG);
            columnIndexOrThrow9 = CursorUtil.getColumnIndexOrThrow(cursorQuery, ShortcutInfo.Column.COLUMN_RECENT);
            columnIndexOrThrow10 = CursorUtil.getColumnIndexOrThrow(cursorQuery, ShortcutInfo.Column.COLUMN_RECENT_TIME);
            columnIndexOrThrow11 = CursorUtil.getColumnIndexOrThrow(cursorQuery, ShortcutInfo.Column.COLUMN_COUNT);
            columnIndexOrThrow12 = CursorUtil.getColumnIndexOrThrow(cursorQuery, ShortcutInfo.Column.COLUMN_RESERVE1);
            columnIndexOrThrow13 = CursorUtil.getColumnIndexOrThrow(cursorQuery, ShortcutInfo.Column.COLUMN_RESERVE2);
            columnIndexOrThrow14 = CursorUtil.getColumnIndexOrThrow(cursorQuery, ShortcutInfo.Column.COLUMN_RESERVE3);
            roomSQLiteQuery = roomSQLiteQueryAcquire;
        } catch (Throwable th) {
            th = th;
            roomSQLiteQuery = roomSQLiteQueryAcquire;
        }
        try {
            int i = columnIndexOrThrow11;
            ArrayList arrayList = new ArrayList(cursorQuery.getCount());
            while (cursorQuery.moveToNext()) {
                ShortcutInfo shortcutInfo = new ShortcutInfo(cursorQuery.getString(columnIndexOrThrow2), cursorQuery.getString(columnIndexOrThrow3), cursorQuery.getString(columnIndexOrThrow4), cursorQuery.getBlob(columnIndexOrThrow6), cursorQuery.getString(columnIndexOrThrow7), cursorQuery.getInt(columnIndexOrThrow8), cursorQuery.getString(columnIndexOrThrow12), cursorQuery.getString(columnIndexOrThrow13), cursorQuery.getString(columnIndexOrThrow14));
                int i2 = columnIndexOrThrow14;
                shortcutInfo.shortcutId = cursorQuery.getInt(columnIndexOrThrow);
                shortcutInfo.pinyin = cursorQuery.getString(columnIndexOrThrow5);
                shortcutInfo.recent = cursorQuery.getInt(columnIndexOrThrow9);
                int i3 = columnIndexOrThrow;
                shortcutInfo.recentTime = cursorQuery.getLong(columnIndexOrThrow10);
                int i4 = i;
                shortcutInfo.count = cursorQuery.getInt(i4);
                arrayList.add(shortcutInfo);
                i = i4;
                columnIndexOrThrow14 = i2;
                columnIndexOrThrow = i3;
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

    @Override // com.byd.card.db.ShortcutInfoDao
    public List<ShortcutInfo> getListForStart(final String inputText) throws Throwable {
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
        int columnIndexOrThrow14;
        RoomSQLiteQuery roomSQLiteQueryAcquire = RoomSQLiteQuery.acquire("SELECT * FROM shortcut_info WHERE title LIKE ? ||'%' or pinyin LIKE ? ||'%' order by count desc", 2);
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
            columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(cursorQuery, ShortcutInfo.Column.COLUMN_SHORTCUT_ID);
            columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(cursorQuery, ShortcutInfo.Column.COLUMN_IDENTIFY);
            columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(cursorQuery, ShortcutInfo.Column.COLUMN_TITLE);
            columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "origin");
            columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(cursorQuery, ShortcutInfo.Column.COLUMN_PINYIN);
            columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(cursorQuery, ShortcutInfo.Column.COLUMN_ICON);
            columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(cursorQuery, ShortcutInfo.Column.COLUMN_INTENT);
            columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(cursorQuery, ShortcutInfo.Column.COLUMN_P_GEAR_FLAG);
            columnIndexOrThrow9 = CursorUtil.getColumnIndexOrThrow(cursorQuery, ShortcutInfo.Column.COLUMN_RECENT);
            columnIndexOrThrow10 = CursorUtil.getColumnIndexOrThrow(cursorQuery, ShortcutInfo.Column.COLUMN_RECENT_TIME);
            columnIndexOrThrow11 = CursorUtil.getColumnIndexOrThrow(cursorQuery, ShortcutInfo.Column.COLUMN_COUNT);
            columnIndexOrThrow12 = CursorUtil.getColumnIndexOrThrow(cursorQuery, ShortcutInfo.Column.COLUMN_RESERVE1);
            columnIndexOrThrow13 = CursorUtil.getColumnIndexOrThrow(cursorQuery, ShortcutInfo.Column.COLUMN_RESERVE2);
            columnIndexOrThrow14 = CursorUtil.getColumnIndexOrThrow(cursorQuery, ShortcutInfo.Column.COLUMN_RESERVE3);
            roomSQLiteQuery = roomSQLiteQueryAcquire;
        } catch (Throwable th) {
            th = th;
            roomSQLiteQuery = roomSQLiteQueryAcquire;
        }
        try {
            int i = columnIndexOrThrow11;
            ArrayList arrayList = new ArrayList(cursorQuery.getCount());
            while (cursorQuery.moveToNext()) {
                ShortcutInfo shortcutInfo = new ShortcutInfo(cursorQuery.getString(columnIndexOrThrow2), cursorQuery.getString(columnIndexOrThrow3), cursorQuery.getString(columnIndexOrThrow4), cursorQuery.getBlob(columnIndexOrThrow6), cursorQuery.getString(columnIndexOrThrow7), cursorQuery.getInt(columnIndexOrThrow8), cursorQuery.getString(columnIndexOrThrow12), cursorQuery.getString(columnIndexOrThrow13), cursorQuery.getString(columnIndexOrThrow14));
                int i2 = columnIndexOrThrow14;
                shortcutInfo.shortcutId = cursorQuery.getInt(columnIndexOrThrow);
                shortcutInfo.pinyin = cursorQuery.getString(columnIndexOrThrow5);
                shortcutInfo.recent = cursorQuery.getInt(columnIndexOrThrow9);
                int i3 = columnIndexOrThrow;
                shortcutInfo.recentTime = cursorQuery.getLong(columnIndexOrThrow10);
                int i4 = i;
                shortcutInfo.count = cursorQuery.getInt(i4);
                arrayList.add(shortcutInfo);
                i = i4;
                columnIndexOrThrow14 = i2;
                columnIndexOrThrow = i3;
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

    @Override // com.byd.card.db.ShortcutInfoDao
    public List<ShortcutInfo> getListForSearch(final String inputText) throws Throwable {
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
        int columnIndexOrThrow14;
        RoomSQLiteQuery roomSQLiteQueryAcquire = RoomSQLiteQuery.acquire("SELECT * FROM shortcut_info WHERE title LIKE '%'|| ? ||'%' or pinyin LIKE '%'|| ? ||'%' order by count desc", 2);
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
            columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(cursorQuery, ShortcutInfo.Column.COLUMN_SHORTCUT_ID);
            columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(cursorQuery, ShortcutInfo.Column.COLUMN_IDENTIFY);
            columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(cursorQuery, ShortcutInfo.Column.COLUMN_TITLE);
            columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "origin");
            columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(cursorQuery, ShortcutInfo.Column.COLUMN_PINYIN);
            columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(cursorQuery, ShortcutInfo.Column.COLUMN_ICON);
            columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(cursorQuery, ShortcutInfo.Column.COLUMN_INTENT);
            columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(cursorQuery, ShortcutInfo.Column.COLUMN_P_GEAR_FLAG);
            columnIndexOrThrow9 = CursorUtil.getColumnIndexOrThrow(cursorQuery, ShortcutInfo.Column.COLUMN_RECENT);
            columnIndexOrThrow10 = CursorUtil.getColumnIndexOrThrow(cursorQuery, ShortcutInfo.Column.COLUMN_RECENT_TIME);
            columnIndexOrThrow11 = CursorUtil.getColumnIndexOrThrow(cursorQuery, ShortcutInfo.Column.COLUMN_COUNT);
            columnIndexOrThrow12 = CursorUtil.getColumnIndexOrThrow(cursorQuery, ShortcutInfo.Column.COLUMN_RESERVE1);
            columnIndexOrThrow13 = CursorUtil.getColumnIndexOrThrow(cursorQuery, ShortcutInfo.Column.COLUMN_RESERVE2);
            columnIndexOrThrow14 = CursorUtil.getColumnIndexOrThrow(cursorQuery, ShortcutInfo.Column.COLUMN_RESERVE3);
            roomSQLiteQuery = roomSQLiteQueryAcquire;
        } catch (Throwable th) {
            th = th;
            roomSQLiteQuery = roomSQLiteQueryAcquire;
        }
        try {
            int i = columnIndexOrThrow11;
            ArrayList arrayList = new ArrayList(cursorQuery.getCount());
            while (cursorQuery.moveToNext()) {
                ShortcutInfo shortcutInfo = new ShortcutInfo(cursorQuery.getString(columnIndexOrThrow2), cursorQuery.getString(columnIndexOrThrow3), cursorQuery.getString(columnIndexOrThrow4), cursorQuery.getBlob(columnIndexOrThrow6), cursorQuery.getString(columnIndexOrThrow7), cursorQuery.getInt(columnIndexOrThrow8), cursorQuery.getString(columnIndexOrThrow12), cursorQuery.getString(columnIndexOrThrow13), cursorQuery.getString(columnIndexOrThrow14));
                int i2 = columnIndexOrThrow14;
                shortcutInfo.shortcutId = cursorQuery.getInt(columnIndexOrThrow);
                shortcutInfo.pinyin = cursorQuery.getString(columnIndexOrThrow5);
                shortcutInfo.recent = cursorQuery.getInt(columnIndexOrThrow9);
                int i3 = columnIndexOrThrow;
                shortcutInfo.recentTime = cursorQuery.getLong(columnIndexOrThrow10);
                int i4 = i;
                shortcutInfo.count = cursorQuery.getInt(i4);
                arrayList.add(shortcutInfo);
                i = i4;
                columnIndexOrThrow14 = i2;
                columnIndexOrThrow = i3;
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

    @Override // com.byd.card.db.ShortcutInfoDao
    public List<ShortcutInfo> getAllShortcut() throws Throwable {
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
        int columnIndexOrThrow14;
        RoomSQLiteQuery roomSQLiteQueryAcquire = RoomSQLiteQuery.acquire("SELECT * FROM shortcut_info", 0);
        this.__db.assertNotSuspendingTransaction();
        Cursor cursorQuery = DBUtil.query(this.__db, roomSQLiteQueryAcquire, false, null);
        try {
            columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(cursorQuery, ShortcutInfo.Column.COLUMN_SHORTCUT_ID);
            columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(cursorQuery, ShortcutInfo.Column.COLUMN_IDENTIFY);
            columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(cursorQuery, ShortcutInfo.Column.COLUMN_TITLE);
            columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "origin");
            columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(cursorQuery, ShortcutInfo.Column.COLUMN_PINYIN);
            columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(cursorQuery, ShortcutInfo.Column.COLUMN_ICON);
            columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(cursorQuery, ShortcutInfo.Column.COLUMN_INTENT);
            columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(cursorQuery, ShortcutInfo.Column.COLUMN_P_GEAR_FLAG);
            columnIndexOrThrow9 = CursorUtil.getColumnIndexOrThrow(cursorQuery, ShortcutInfo.Column.COLUMN_RECENT);
            columnIndexOrThrow10 = CursorUtil.getColumnIndexOrThrow(cursorQuery, ShortcutInfo.Column.COLUMN_RECENT_TIME);
            columnIndexOrThrow11 = CursorUtil.getColumnIndexOrThrow(cursorQuery, ShortcutInfo.Column.COLUMN_COUNT);
            columnIndexOrThrow12 = CursorUtil.getColumnIndexOrThrow(cursorQuery, ShortcutInfo.Column.COLUMN_RESERVE1);
            columnIndexOrThrow13 = CursorUtil.getColumnIndexOrThrow(cursorQuery, ShortcutInfo.Column.COLUMN_RESERVE2);
            columnIndexOrThrow14 = CursorUtil.getColumnIndexOrThrow(cursorQuery, ShortcutInfo.Column.COLUMN_RESERVE3);
            roomSQLiteQuery = roomSQLiteQueryAcquire;
        } catch (Throwable th) {
            th = th;
            roomSQLiteQuery = roomSQLiteQueryAcquire;
        }
        try {
            int i = columnIndexOrThrow11;
            ArrayList arrayList = new ArrayList(cursorQuery.getCount());
            while (cursorQuery.moveToNext()) {
                ShortcutInfo shortcutInfo = new ShortcutInfo(cursorQuery.getString(columnIndexOrThrow2), cursorQuery.getString(columnIndexOrThrow3), cursorQuery.getString(columnIndexOrThrow4), cursorQuery.getBlob(columnIndexOrThrow6), cursorQuery.getString(columnIndexOrThrow7), cursorQuery.getInt(columnIndexOrThrow8), cursorQuery.getString(columnIndexOrThrow12), cursorQuery.getString(columnIndexOrThrow13), cursorQuery.getString(columnIndexOrThrow14));
                int i2 = columnIndexOrThrow14;
                shortcutInfo.shortcutId = cursorQuery.getInt(columnIndexOrThrow);
                shortcutInfo.pinyin = cursorQuery.getString(columnIndexOrThrow5);
                shortcutInfo.recent = cursorQuery.getInt(columnIndexOrThrow9);
                int i3 = columnIndexOrThrow;
                shortcutInfo.recentTime = cursorQuery.getLong(columnIndexOrThrow10);
                int i4 = i;
                shortcutInfo.count = cursorQuery.getInt(i4);
                arrayList.add(shortcutInfo);
                i = i4;
                columnIndexOrThrow14 = i2;
                columnIndexOrThrow = i3;
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

    @Override // com.byd.card.db.ShortcutInfoDao
    public ShortcutInfo getShortcutByIdentify(final String identify, final String origin) {
        ShortcutInfo shortcutInfo;
        RoomSQLiteQuery roomSQLiteQueryAcquire = RoomSQLiteQuery.acquire("SELECT * FROM shortcut_info WHERE identify=? and origin=?", 2);
        if (identify == null) {
            roomSQLiteQueryAcquire.bindNull(1);
        } else {
            roomSQLiteQueryAcquire.bindString(1, identify);
        }
        if (origin == null) {
            roomSQLiteQueryAcquire.bindNull(2);
        } else {
            roomSQLiteQueryAcquire.bindString(2, origin);
        }
        this.__db.assertNotSuspendingTransaction();
        Cursor cursorQuery = DBUtil.query(this.__db, roomSQLiteQueryAcquire, false, null);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(cursorQuery, ShortcutInfo.Column.COLUMN_SHORTCUT_ID);
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(cursorQuery, ShortcutInfo.Column.COLUMN_IDENTIFY);
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(cursorQuery, ShortcutInfo.Column.COLUMN_TITLE);
            int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "origin");
            int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(cursorQuery, ShortcutInfo.Column.COLUMN_PINYIN);
            int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(cursorQuery, ShortcutInfo.Column.COLUMN_ICON);
            int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(cursorQuery, ShortcutInfo.Column.COLUMN_INTENT);
            int columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(cursorQuery, ShortcutInfo.Column.COLUMN_P_GEAR_FLAG);
            int columnIndexOrThrow9 = CursorUtil.getColumnIndexOrThrow(cursorQuery, ShortcutInfo.Column.COLUMN_RECENT);
            int columnIndexOrThrow10 = CursorUtil.getColumnIndexOrThrow(cursorQuery, ShortcutInfo.Column.COLUMN_RECENT_TIME);
            int columnIndexOrThrow11 = CursorUtil.getColumnIndexOrThrow(cursorQuery, ShortcutInfo.Column.COLUMN_COUNT);
            int columnIndexOrThrow12 = CursorUtil.getColumnIndexOrThrow(cursorQuery, ShortcutInfo.Column.COLUMN_RESERVE1);
            int columnIndexOrThrow13 = CursorUtil.getColumnIndexOrThrow(cursorQuery, ShortcutInfo.Column.COLUMN_RESERVE2);
            int columnIndexOrThrow14 = CursorUtil.getColumnIndexOrThrow(cursorQuery, ShortcutInfo.Column.COLUMN_RESERVE3);
            if (cursorQuery.moveToFirst()) {
                shortcutInfo = new ShortcutInfo(cursorQuery.getString(columnIndexOrThrow2), cursorQuery.getString(columnIndexOrThrow3), cursorQuery.getString(columnIndexOrThrow4), cursorQuery.getBlob(columnIndexOrThrow6), cursorQuery.getString(columnIndexOrThrow7), cursorQuery.getInt(columnIndexOrThrow8), cursorQuery.getString(columnIndexOrThrow12), cursorQuery.getString(columnIndexOrThrow13), cursorQuery.getString(columnIndexOrThrow14));
                shortcutInfo.shortcutId = cursorQuery.getInt(columnIndexOrThrow);
                shortcutInfo.pinyin = cursorQuery.getString(columnIndexOrThrow5);
                shortcutInfo.recent = cursorQuery.getInt(columnIndexOrThrow9);
                shortcutInfo.recentTime = cursorQuery.getLong(columnIndexOrThrow10);
                shortcutInfo.count = cursorQuery.getInt(columnIndexOrThrow11);
            } else {
                shortcutInfo = null;
            }
            return shortcutInfo;
        } finally {
            cursorQuery.close();
            roomSQLiteQueryAcquire.release();
        }
    }

    @Override // com.byd.card.db.ShortcutInfoDao
    public List<ShortcutInfo> getShortcutByPGearFlag(final int pGearFlag) throws Throwable {
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
        int columnIndexOrThrow14;
        RoomSQLiteQuery roomSQLiteQueryAcquire = RoomSQLiteQuery.acquire("SELECT * FROM shortcut_info WHERE pGearFlag=?", 1);
        roomSQLiteQueryAcquire.bindLong(1, pGearFlag);
        this.__db.assertNotSuspendingTransaction();
        Cursor cursorQuery = DBUtil.query(this.__db, roomSQLiteQueryAcquire, false, null);
        try {
            columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(cursorQuery, ShortcutInfo.Column.COLUMN_SHORTCUT_ID);
            columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(cursorQuery, ShortcutInfo.Column.COLUMN_IDENTIFY);
            columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(cursorQuery, ShortcutInfo.Column.COLUMN_TITLE);
            columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "origin");
            columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(cursorQuery, ShortcutInfo.Column.COLUMN_PINYIN);
            columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(cursorQuery, ShortcutInfo.Column.COLUMN_ICON);
            columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(cursorQuery, ShortcutInfo.Column.COLUMN_INTENT);
            columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(cursorQuery, ShortcutInfo.Column.COLUMN_P_GEAR_FLAG);
            columnIndexOrThrow9 = CursorUtil.getColumnIndexOrThrow(cursorQuery, ShortcutInfo.Column.COLUMN_RECENT);
            columnIndexOrThrow10 = CursorUtil.getColumnIndexOrThrow(cursorQuery, ShortcutInfo.Column.COLUMN_RECENT_TIME);
            columnIndexOrThrow11 = CursorUtil.getColumnIndexOrThrow(cursorQuery, ShortcutInfo.Column.COLUMN_COUNT);
            columnIndexOrThrow12 = CursorUtil.getColumnIndexOrThrow(cursorQuery, ShortcutInfo.Column.COLUMN_RESERVE1);
            columnIndexOrThrow13 = CursorUtil.getColumnIndexOrThrow(cursorQuery, ShortcutInfo.Column.COLUMN_RESERVE2);
            columnIndexOrThrow14 = CursorUtil.getColumnIndexOrThrow(cursorQuery, ShortcutInfo.Column.COLUMN_RESERVE3);
            roomSQLiteQuery = roomSQLiteQueryAcquire;
        } catch (Throwable th) {
            th = th;
            roomSQLiteQuery = roomSQLiteQueryAcquire;
        }
        try {
            int i = columnIndexOrThrow11;
            ArrayList arrayList = new ArrayList(cursorQuery.getCount());
            while (cursorQuery.moveToNext()) {
                ShortcutInfo shortcutInfo = new ShortcutInfo(cursorQuery.getString(columnIndexOrThrow2), cursorQuery.getString(columnIndexOrThrow3), cursorQuery.getString(columnIndexOrThrow4), cursorQuery.getBlob(columnIndexOrThrow6), cursorQuery.getString(columnIndexOrThrow7), cursorQuery.getInt(columnIndexOrThrow8), cursorQuery.getString(columnIndexOrThrow12), cursorQuery.getString(columnIndexOrThrow13), cursorQuery.getString(columnIndexOrThrow14));
                int i2 = columnIndexOrThrow14;
                shortcutInfo.shortcutId = cursorQuery.getInt(columnIndexOrThrow);
                shortcutInfo.pinyin = cursorQuery.getString(columnIndexOrThrow5);
                shortcutInfo.recent = cursorQuery.getInt(columnIndexOrThrow9);
                int i3 = columnIndexOrThrow;
                shortcutInfo.recentTime = cursorQuery.getLong(columnIndexOrThrow10);
                int i4 = i;
                shortcutInfo.count = cursorQuery.getInt(i4);
                arrayList.add(shortcutInfo);
                i = i4;
                columnIndexOrThrow14 = i2;
                columnIndexOrThrow = i3;
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

    @Override // com.byd.card.db.ShortcutInfoDao
    public List<ShortcutInfo> getRecentList(final int recent) throws Throwable {
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
        int columnIndexOrThrow14;
        RoomSQLiteQuery roomSQLiteQueryAcquire = RoomSQLiteQuery.acquire("SELECT * FROM shortcut_info WHERE recent=? order by recentTime desc limit 6", 1);
        roomSQLiteQueryAcquire.bindLong(1, recent);
        this.__db.assertNotSuspendingTransaction();
        Cursor cursorQuery = DBUtil.query(this.__db, roomSQLiteQueryAcquire, false, null);
        try {
            columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(cursorQuery, ShortcutInfo.Column.COLUMN_SHORTCUT_ID);
            columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(cursorQuery, ShortcutInfo.Column.COLUMN_IDENTIFY);
            columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(cursorQuery, ShortcutInfo.Column.COLUMN_TITLE);
            columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "origin");
            columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(cursorQuery, ShortcutInfo.Column.COLUMN_PINYIN);
            columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(cursorQuery, ShortcutInfo.Column.COLUMN_ICON);
            columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(cursorQuery, ShortcutInfo.Column.COLUMN_INTENT);
            columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(cursorQuery, ShortcutInfo.Column.COLUMN_P_GEAR_FLAG);
            columnIndexOrThrow9 = CursorUtil.getColumnIndexOrThrow(cursorQuery, ShortcutInfo.Column.COLUMN_RECENT);
            columnIndexOrThrow10 = CursorUtil.getColumnIndexOrThrow(cursorQuery, ShortcutInfo.Column.COLUMN_RECENT_TIME);
            columnIndexOrThrow11 = CursorUtil.getColumnIndexOrThrow(cursorQuery, ShortcutInfo.Column.COLUMN_COUNT);
            columnIndexOrThrow12 = CursorUtil.getColumnIndexOrThrow(cursorQuery, ShortcutInfo.Column.COLUMN_RESERVE1);
            columnIndexOrThrow13 = CursorUtil.getColumnIndexOrThrow(cursorQuery, ShortcutInfo.Column.COLUMN_RESERVE2);
            columnIndexOrThrow14 = CursorUtil.getColumnIndexOrThrow(cursorQuery, ShortcutInfo.Column.COLUMN_RESERVE3);
            roomSQLiteQuery = roomSQLiteQueryAcquire;
        } catch (Throwable th) {
            th = th;
            roomSQLiteQuery = roomSQLiteQueryAcquire;
        }
        try {
            int i = columnIndexOrThrow11;
            ArrayList arrayList = new ArrayList(cursorQuery.getCount());
            while (cursorQuery.moveToNext()) {
                ShortcutInfo shortcutInfo = new ShortcutInfo(cursorQuery.getString(columnIndexOrThrow2), cursorQuery.getString(columnIndexOrThrow3), cursorQuery.getString(columnIndexOrThrow4), cursorQuery.getBlob(columnIndexOrThrow6), cursorQuery.getString(columnIndexOrThrow7), cursorQuery.getInt(columnIndexOrThrow8), cursorQuery.getString(columnIndexOrThrow12), cursorQuery.getString(columnIndexOrThrow13), cursorQuery.getString(columnIndexOrThrow14));
                int i2 = columnIndexOrThrow14;
                shortcutInfo.shortcutId = cursorQuery.getInt(columnIndexOrThrow);
                shortcutInfo.pinyin = cursorQuery.getString(columnIndexOrThrow5);
                shortcutInfo.recent = cursorQuery.getInt(columnIndexOrThrow9);
                int i3 = columnIndexOrThrow;
                shortcutInfo.recentTime = cursorQuery.getLong(columnIndexOrThrow10);
                int i4 = i;
                shortcutInfo.count = cursorQuery.getInt(i4);
                arrayList.add(shortcutInfo);
                i = i4;
                columnIndexOrThrow14 = i2;
                columnIndexOrThrow = i3;
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
