package com.amazon.podcast.biteBookmark;

import android.database.Cursor;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.room.util.StringUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public final class BiteBookmarkOperationsDoa_Impl implements BiteBookmarkOperationsDoa {
    private final RoomDatabase __db;
    private final EntityInsertionAdapter<BiteBookmarkOperations> __insertionAdapterOfBiteBookmarkOperations;
    private final SharedSQLiteStatement __preparedStmtOfDeleteAll;

    public BiteBookmarkOperationsDoa_Impl(RoomDatabase __db) {
        this.__db = __db;
        this.__insertionAdapterOfBiteBookmarkOperations = new EntityInsertionAdapter<BiteBookmarkOperations>(__db) { // from class: com.amazon.podcast.biteBookmark.BiteBookmarkOperationsDoa_Impl.1
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "INSERT OR REPLACE INTO `bite_bookmark_operations` (`operationId`,`id`,`podcastId`,`episodeId`,`totalDurationMilliseconds`,`progressMilliseconds`,`updatedTime`,`isProcessed`) VALUES (?,?,?,?,?,?,?,?)";
            }

            @Override // androidx.room.EntityInsertionAdapter
            public void bind(SupportSQLiteStatement supportSQLiteStatement, BiteBookmarkOperations biteBookmarkOperations) {
                if (biteBookmarkOperations.getOperationId() == null) {
                    supportSQLiteStatement.bindNull(1);
                } else {
                    supportSQLiteStatement.bindString(1, biteBookmarkOperations.getOperationId());
                }
                if (biteBookmarkOperations.getId() == null) {
                    supportSQLiteStatement.bindNull(2);
                } else {
                    supportSQLiteStatement.bindString(2, biteBookmarkOperations.getId());
                }
                if (biteBookmarkOperations.getPodcastId() == null) {
                    supportSQLiteStatement.bindNull(3);
                } else {
                    supportSQLiteStatement.bindString(3, biteBookmarkOperations.getPodcastId());
                }
                if (biteBookmarkOperations.getEpisodeId() == null) {
                    supportSQLiteStatement.bindNull(4);
                } else {
                    supportSQLiteStatement.bindString(4, biteBookmarkOperations.getEpisodeId());
                }
                supportSQLiteStatement.bindLong(5, biteBookmarkOperations.getTotalDurationMilliseconds());
                supportSQLiteStatement.bindLong(6, biteBookmarkOperations.getProgressMilliseconds());
                supportSQLiteStatement.bindLong(7, biteBookmarkOperations.getUpdatedTime());
                supportSQLiteStatement.bindLong(8, biteBookmarkOperations.isProcessed() ? 1L : 0L);
            }
        };
        this.__preparedStmtOfDeleteAll = new SharedSQLiteStatement(__db) { // from class: com.amazon.podcast.biteBookmark.BiteBookmarkOperationsDoa_Impl.2
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "DELETE FROM bite_bookmark_operations";
            }
        };
    }

    @Override // com.amazon.podcast.biteBookmark.BiteBookmarkOperationsDoa
    public void insert(final BiteBookmarkOperations bookmark) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__insertionAdapterOfBiteBookmarkOperations.insert(bookmark);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // com.amazon.podcast.biteBookmark.BiteBookmarkOperationsDoa
    public List<BiteBookmarkOperations> getUnProcessed() {
        RoomSQLiteQuery roomSQLiteQueryAcquire = RoomSQLiteQuery.acquire("SELECT * FROM bite_bookmark_operations WHERE isProcessed == 0", 0);
        this.__db.assertNotSuspendingTransaction();
        Cursor cursorQuery = DBUtil.query(this.__db, roomSQLiteQueryAcquire, false, null);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(cursorQuery, "operationId");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "id");
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "podcastId");
            int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "episodeId");
            int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "totalDurationMilliseconds");
            int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "progressMilliseconds");
            int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "updatedTime");
            int columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "isProcessed");
            ArrayList arrayList = new ArrayList(cursorQuery.getCount());
            while (cursorQuery.moveToNext()) {
                arrayList.add(new BiteBookmarkOperations(cursorQuery.isNull(columnIndexOrThrow) ? null : cursorQuery.getString(columnIndexOrThrow), cursorQuery.isNull(columnIndexOrThrow2) ? null : cursorQuery.getString(columnIndexOrThrow2), cursorQuery.isNull(columnIndexOrThrow3) ? null : cursorQuery.getString(columnIndexOrThrow3), cursorQuery.isNull(columnIndexOrThrow4) ? null : cursorQuery.getString(columnIndexOrThrow4), cursorQuery.getLong(columnIndexOrThrow5), cursorQuery.getLong(columnIndexOrThrow6), cursorQuery.getLong(columnIndexOrThrow7), cursorQuery.getInt(columnIndexOrThrow8) != 0));
            }
            return arrayList;
        } finally {
            cursorQuery.close();
            roomSQLiteQueryAcquire.release();
        }
    }

    @Override // com.amazon.podcast.biteBookmark.BiteBookmarkOperationsDoa
    public void deleteIds(final List<String> ids) {
        this.__db.assertNotSuspendingTransaction();
        StringBuilder sbNewStringBuilder = StringUtil.newStringBuilder();
        sbNewStringBuilder.append("DELETE FROM bite_bookmark_operations WHERE operationId IN (");
        StringUtil.appendPlaceholders(sbNewStringBuilder, ids.size());
        sbNewStringBuilder.append(")");
        SupportSQLiteStatement supportSQLiteStatementCompileStatement = this.__db.compileStatement(sbNewStringBuilder.toString());
        int i = 1;
        for (String str : ids) {
            if (str == null) {
                supportSQLiteStatementCompileStatement.bindNull(i);
            } else {
                supportSQLiteStatementCompileStatement.bindString(i, str);
            }
            i++;
        }
        this.__db.beginTransaction();
        try {
            supportSQLiteStatementCompileStatement.executeUpdateDelete();
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // com.amazon.podcast.biteBookmark.BiteBookmarkOperationsDoa
    public void updateToProcessing(final List<String> ids) {
        this.__db.assertNotSuspendingTransaction();
        StringBuilder sbNewStringBuilder = StringUtil.newStringBuilder();
        sbNewStringBuilder.append("UPDATE bite_bookmark_operations SET isProcessed = 1 WHERE operationId IN (");
        StringUtil.appendPlaceholders(sbNewStringBuilder, ids.size());
        sbNewStringBuilder.append(")");
        SupportSQLiteStatement supportSQLiteStatementCompileStatement = this.__db.compileStatement(sbNewStringBuilder.toString());
        int i = 1;
        for (String str : ids) {
            if (str == null) {
                supportSQLiteStatementCompileStatement.bindNull(i);
            } else {
                supportSQLiteStatementCompileStatement.bindString(i, str);
            }
            i++;
        }
        this.__db.beginTransaction();
        try {
            supportSQLiteStatementCompileStatement.executeUpdateDelete();
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    public static List<Class<?>> getRequiredConverters() {
        return Collections.emptyList();
    }
}
