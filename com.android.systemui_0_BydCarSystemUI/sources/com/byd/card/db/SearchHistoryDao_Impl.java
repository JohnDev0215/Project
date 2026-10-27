package com.byd.card.db;

import android.database.Cursor;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.byd.card.bean.SearchHistory;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class SearchHistoryDao_Impl implements SearchHistoryDao {
    private final RoomDatabase __db;
    private final EntityDeletionOrUpdateAdapter<SearchHistory> __deletionAdapterOfSearchHistory;
    private final EntityInsertionAdapter<SearchHistory> __insertionAdapterOfSearchHistory;
    private final EntityDeletionOrUpdateAdapter<SearchHistory> __updateAdapterOfSearchHistory;

    public SearchHistoryDao_Impl(RoomDatabase __db) {
        this.__db = __db;
        this.__insertionAdapterOfSearchHistory = new EntityInsertionAdapter<SearchHistory>(__db) { // from class: com.byd.card.db.SearchHistoryDao_Impl.1
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "INSERT OR ABORT INTO `search_history` (`id`,`text`,`time`) VALUES (nullif(?, 0),?,?)";
            }

            @Override // androidx.room.EntityInsertionAdapter
            public void bind(SupportSQLiteStatement stmt, SearchHistory value) {
                stmt.bindLong(1, value.id);
                if (value.text == null) {
                    stmt.bindNull(2);
                } else {
                    stmt.bindString(2, value.text);
                }
                stmt.bindLong(3, value.time);
            }
        };
        this.__deletionAdapterOfSearchHistory = new EntityDeletionOrUpdateAdapter<SearchHistory>(__db) { // from class: com.byd.card.db.SearchHistoryDao_Impl.2
            @Override // androidx.room.EntityDeletionOrUpdateAdapter, androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "DELETE FROM `search_history` WHERE `id` = ?";
            }

            @Override // androidx.room.EntityDeletionOrUpdateAdapter
            public void bind(SupportSQLiteStatement stmt, SearchHistory value) {
                stmt.bindLong(1, value.id);
            }
        };
        this.__updateAdapterOfSearchHistory = new EntityDeletionOrUpdateAdapter<SearchHistory>(__db) { // from class: com.byd.card.db.SearchHistoryDao_Impl.3
            @Override // androidx.room.EntityDeletionOrUpdateAdapter, androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "UPDATE OR REPLACE `search_history` SET `id` = ?,`text` = ?,`time` = ? WHERE `id` = ?";
            }

            @Override // androidx.room.EntityDeletionOrUpdateAdapter
            public void bind(SupportSQLiteStatement stmt, SearchHistory value) {
                stmt.bindLong(1, value.id);
                if (value.text == null) {
                    stmt.bindNull(2);
                } else {
                    stmt.bindString(2, value.text);
                }
                stmt.bindLong(3, value.time);
                stmt.bindLong(4, value.id);
            }
        };
    }

    @Override // com.byd.card.db.SearchHistoryDao
    public void insert(final SearchHistory... histories) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__insertionAdapterOfSearchHistory.insert(histories);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // com.byd.card.db.SearchHistoryDao
    public void delete(final SearchHistory... histories) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__deletionAdapterOfSearchHistory.handleMultiple(histories);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // com.byd.card.db.SearchHistoryDao
    public void deleteAll(final List<SearchHistory> histories) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__deletionAdapterOfSearchHistory.handleMultiple(histories);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // com.byd.card.db.SearchHistoryDao
    public void update(final SearchHistory... histories) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__updateAdapterOfSearchHistory.handleMultiple(histories);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // com.byd.card.db.SearchHistoryDao
    public List<SearchHistory> getAllHistory() {
        RoomSQLiteQuery roomSQLiteQueryAcquire = RoomSQLiteQuery.acquire("SELECT * FROM search_history", 0);
        this.__db.assertNotSuspendingTransaction();
        Cursor cursorQuery = DBUtil.query(this.__db, roomSQLiteQueryAcquire, false, null);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(cursorQuery, "id");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "text");
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "time");
            ArrayList arrayList = new ArrayList(cursorQuery.getCount());
            while (cursorQuery.moveToNext()) {
                SearchHistory searchHistory = new SearchHistory(cursorQuery.getString(columnIndexOrThrow2), cursorQuery.getLong(columnIndexOrThrow3));
                searchHistory.id = cursorQuery.getInt(columnIndexOrThrow);
                arrayList.add(searchHistory);
            }
            return arrayList;
        } finally {
            cursorQuery.close();
            roomSQLiteQueryAcquire.release();
        }
    }

    @Override // com.byd.card.db.SearchHistoryDao
    public List<SearchHistory> getHistory() {
        RoomSQLiteQuery roomSQLiteQueryAcquire = RoomSQLiteQuery.acquire("SELECT * FROM search_history order by time desc limit 8", 0);
        this.__db.assertNotSuspendingTransaction();
        Cursor cursorQuery = DBUtil.query(this.__db, roomSQLiteQueryAcquire, false, null);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(cursorQuery, "id");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "text");
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "time");
            ArrayList arrayList = new ArrayList(cursorQuery.getCount());
            while (cursorQuery.moveToNext()) {
                SearchHistory searchHistory = new SearchHistory(cursorQuery.getString(columnIndexOrThrow2), cursorQuery.getLong(columnIndexOrThrow3));
                searchHistory.id = cursorQuery.getInt(columnIndexOrThrow);
                arrayList.add(searchHistory);
            }
            return arrayList;
        } finally {
            cursorQuery.close();
            roomSQLiteQueryAcquire.release();
        }
    }

    @Override // com.byd.card.db.SearchHistoryDao
    public SearchHistory getHistoryByName(final String name) {
        RoomSQLiteQuery roomSQLiteQueryAcquire = RoomSQLiteQuery.acquire("SELECT * FROM search_history WHERE text=?", 1);
        if (name == null) {
            roomSQLiteQueryAcquire.bindNull(1);
        } else {
            roomSQLiteQueryAcquire.bindString(1, name);
        }
        this.__db.assertNotSuspendingTransaction();
        SearchHistory searchHistory = null;
        Cursor cursorQuery = DBUtil.query(this.__db, roomSQLiteQueryAcquire, false, null);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(cursorQuery, "id");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "text");
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "time");
            if (cursorQuery.moveToFirst()) {
                SearchHistory searchHistory2 = new SearchHistory(cursorQuery.getString(columnIndexOrThrow2), cursorQuery.getLong(columnIndexOrThrow3));
                searchHistory2.id = cursorQuery.getInt(columnIndexOrThrow);
                searchHistory = searchHistory2;
            }
            return searchHistory;
        } finally {
            cursorQuery.close();
            roomSQLiteQueryAcquire.release();
        }
    }
}
