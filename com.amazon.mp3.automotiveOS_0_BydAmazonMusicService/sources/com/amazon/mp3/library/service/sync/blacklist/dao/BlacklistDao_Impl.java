package com.amazon.mp3.library.service.sync.blacklist.dao;

import android.database.Cursor;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.amazon.mp3.library.service.sync.blacklist.entity.LocalUri;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public final class BlacklistDao_Impl implements BlacklistDao {
    private final RoomDatabase __db;
    private final EntityInsertionAdapter<LocalUri> __insertionAdapterOfLocalUri;
    private final SharedSQLiteStatement __preparedStmtOfDeleteAll;

    public BlacklistDao_Impl(RoomDatabase __db) {
        this.__db = __db;
        this.__insertionAdapterOfLocalUri = new EntityInsertionAdapter<LocalUri>(__db) { // from class: com.amazon.mp3.library.service.sync.blacklist.dao.BlacklistDao_Impl.1
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "INSERT OR REPLACE INTO `local_uris` (`uid`,`local_uri`) VALUES (nullif(?, 0),?)";
            }

            @Override // androidx.room.EntityInsertionAdapter
            public void bind(SupportSQLiteStatement stmt, LocalUri value) {
                stmt.bindLong(1, value.getUid());
                if (value.getLocalUri() == null) {
                    stmt.bindNull(2);
                } else {
                    stmt.bindString(2, value.getLocalUri());
                }
            }
        };
        this.__preparedStmtOfDeleteAll = new SharedSQLiteStatement(__db) { // from class: com.amazon.mp3.library.service.sync.blacklist.dao.BlacklistDao_Impl.2
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "DELETE FROM local_uris";
            }
        };
    }

    @Override // com.amazon.mp3.library.service.sync.blacklist.dao.BlacklistDao
    public void insert(final LocalUri localUri) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__insertionAdapterOfLocalUri.insert(localUri);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // com.amazon.mp3.library.service.sync.blacklist.dao.BlacklistDao
    public List<LocalUri> getAllLocalUris() {
        RoomSQLiteQuery roomSQLiteQueryAcquire = RoomSQLiteQuery.acquire("SELECT * FROM local_uris", 0);
        this.__db.assertNotSuspendingTransaction();
        Cursor cursorQuery = DBUtil.query(this.__db, roomSQLiteQueryAcquire, false, null);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(cursorQuery, "uid");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "local_uri");
            ArrayList arrayList = new ArrayList(cursorQuery.getCount());
            while (cursorQuery.moveToNext()) {
                LocalUri localUri = new LocalUri(cursorQuery.isNull(columnIndexOrThrow2) ? null : cursorQuery.getString(columnIndexOrThrow2));
                localUri.setUid(cursorQuery.getLong(columnIndexOrThrow));
                arrayList.add(localUri);
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
