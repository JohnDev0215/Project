package com.amazon.music.find.data.database;

import android.database.Cursor;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.amazon.music.find.data.database.entities.SearchHistory;
import com.amazon.music.skyfire.models.TetheredMessageKey;
import com.google.android.gms.actions.SearchIntents;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* JADX INFO: loaded from: classes4.dex */
public final class SearchQueryDao_Impl implements SearchQueryDao {
    private final RoomDatabase __db;
    private final EntityInsertionAdapter<SearchHistory> __insertionAdapterOfSearchQueryData;
    private final SharedSQLiteStatement __preparedStmtOfDeleteAll;
    private final SharedSQLiteStatement __preparedStmtOfDeleteExpired;
    private final SharedSQLiteStatement __preparedStmtOfSyncDefaultProfileHistory;

    public SearchQueryDao_Impl(RoomDatabase __db) {
        this.__db = __db;
        this.__insertionAdapterOfSearchQueryData = new EntityInsertionAdapter<SearchHistory>(__db) { // from class: com.amazon.music.find.data.database.SearchQueryDao_Impl.1
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "INSERT OR REPLACE INTO `search_query` (`query`,`timestamp`,`profile_id`) VALUES (?,?,?)";
            }

            @Override // androidx.room.EntityInsertionAdapter
            public void bind(SupportSQLiteStatement stmt, SearchHistory value) {
                if (value.getQuery() == null) {
                    stmt.bindNull(1);
                } else {
                    stmt.bindString(1, value.getQuery());
                }
                stmt.bindLong(2, value.getTimestamp());
                if (value.getPid() == null) {
                    stmt.bindNull(3);
                } else {
                    stmt.bindString(3, value.getPid());
                }
            }
        };
        this.__preparedStmtOfDeleteAll = new SharedSQLiteStatement(__db) { // from class: com.amazon.music.find.data.database.SearchQueryDao_Impl.2
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "delete from search_query where profile_id = ?";
            }
        };
        this.__preparedStmtOfDeleteExpired = new SharedSQLiteStatement(__db) { // from class: com.amazon.music.find.data.database.SearchQueryDao_Impl.3
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "delete from search_query where timestamp < ?";
            }
        };
        this.__preparedStmtOfSyncDefaultProfileHistory = new SharedSQLiteStatement(__db) { // from class: com.amazon.music.find.data.database.SearchQueryDao_Impl.4
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "update search_query set profile_id = ? where profile_id = 'undefined'";
            }
        };
    }

    @Override // com.amazon.music.find.data.database.SearchQueryDao
    public void put(final SearchHistory searchQueryData) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__insertionAdapterOfSearchQueryData.insert(searchQueryData);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // com.amazon.music.find.data.database.SearchQueryDao
    public void deleteAll(final String profileId) {
        this.__db.assertNotSuspendingTransaction();
        SupportSQLiteStatement supportSQLiteStatementAcquire = this.__preparedStmtOfDeleteAll.acquire();
        if (profileId == null) {
            supportSQLiteStatementAcquire.bindNull(1);
        } else {
            supportSQLiteStatementAcquire.bindString(1, profileId);
        }
        this.__db.beginTransaction();
        try {
            supportSQLiteStatementAcquire.executeUpdateDelete();
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
            this.__preparedStmtOfDeleteAll.release(supportSQLiteStatementAcquire);
        }
    }

    @Override // com.amazon.music.find.data.database.SearchQueryDao
    public void deleteExpired(final long timestamp) {
        this.__db.assertNotSuspendingTransaction();
        SupportSQLiteStatement supportSQLiteStatementAcquire = this.__preparedStmtOfDeleteExpired.acquire();
        supportSQLiteStatementAcquire.bindLong(1, timestamp);
        this.__db.beginTransaction();
        try {
            supportSQLiteStatementAcquire.executeUpdateDelete();
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
            this.__preparedStmtOfDeleteExpired.release(supportSQLiteStatementAcquire);
        }
    }

    @Override // com.amazon.music.find.data.database.SearchQueryDao
    public void syncDefaultProfileHistory(final String defaultProfileId) {
        this.__db.assertNotSuspendingTransaction();
        SupportSQLiteStatement supportSQLiteStatementAcquire = this.__preparedStmtOfSyncDefaultProfileHistory.acquire();
        if (defaultProfileId == null) {
            supportSQLiteStatementAcquire.bindNull(1);
        } else {
            supportSQLiteStatementAcquire.bindString(1, defaultProfileId);
        }
        this.__db.beginTransaction();
        try {
            supportSQLiteStatementAcquire.executeUpdateDelete();
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
            this.__preparedStmtOfSyncDefaultProfileHistory.release(supportSQLiteStatementAcquire);
        }
    }

    @Override // com.amazon.music.find.data.database.SearchQueryDao
    public List<SearchHistory> getRecentSearchHistory(final String profileId, final int limit) {
        RoomSQLiteQuery roomSQLiteQueryAcquire = RoomSQLiteQuery.acquire("select * from search_query where profile_id = ? order by timestamp desc limit ?", 2);
        if (profileId == null) {
            roomSQLiteQueryAcquire.bindNull(1);
        } else {
            roomSQLiteQueryAcquire.bindString(1, profileId);
        }
        roomSQLiteQueryAcquire.bindLong(2, limit);
        this.__db.assertNotSuspendingTransaction();
        Cursor cursorQuery = DBUtil.query(this.__db, roomSQLiteQueryAcquire, false, null);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(cursorQuery, SearchIntents.EXTRA_QUERY);
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(cursorQuery, TetheredMessageKey.timestamp);
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "profile_id");
            ArrayList arrayList = new ArrayList(cursorQuery.getCount());
            while (cursorQuery.moveToNext()) {
                arrayList.add(new SearchHistory(cursorQuery.isNull(columnIndexOrThrow) ? null : cursorQuery.getString(columnIndexOrThrow), cursorQuery.getLong(columnIndexOrThrow2), cursorQuery.isNull(columnIndexOrThrow3) ? null : cursorQuery.getString(columnIndexOrThrow3)));
            }
            return arrayList;
        } finally {
            cursorQuery.close();
            roomSQLiteQueryAcquire.release();
        }
    }

    @Override // com.amazon.music.find.data.database.SearchQueryDao
    public List<SearchHistory> getAll(final String profileId) {
        RoomSQLiteQuery roomSQLiteQueryAcquire = RoomSQLiteQuery.acquire("select * from search_query where profile_id = ?", 1);
        if (profileId == null) {
            roomSQLiteQueryAcquire.bindNull(1);
        } else {
            roomSQLiteQueryAcquire.bindString(1, profileId);
        }
        this.__db.assertNotSuspendingTransaction();
        Cursor cursorQuery = DBUtil.query(this.__db, roomSQLiteQueryAcquire, false, null);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(cursorQuery, SearchIntents.EXTRA_QUERY);
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(cursorQuery, TetheredMessageKey.timestamp);
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "profile_id");
            ArrayList arrayList = new ArrayList(cursorQuery.getCount());
            while (cursorQuery.moveToNext()) {
                arrayList.add(new SearchHistory(cursorQuery.isNull(columnIndexOrThrow) ? null : cursorQuery.getString(columnIndexOrThrow), cursorQuery.getLong(columnIndexOrThrow2), cursorQuery.isNull(columnIndexOrThrow3) ? null : cursorQuery.getString(columnIndexOrThrow3)));
            }
            return arrayList;
        } finally {
            cursorQuery.close();
            roomSQLiteQueryAcquire.release();
        }
    }

    public static List<Class<?>> getRequiredConverters() {
        return Collections.emptyList();
    }
}
