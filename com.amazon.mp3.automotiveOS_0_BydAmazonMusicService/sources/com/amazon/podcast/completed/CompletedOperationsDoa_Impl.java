package com.amazon.podcast.completed;

import android.database.Cursor;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.room.util.StringUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.amazon.music.curate.skyfire.bootstrap.ContextMappingConstants;
import com.google.android.gms.cast.MediaTrack;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public final class CompletedOperationsDoa_Impl implements CompletedOperationsDoa {
    private final RoomDatabase __db;
    private final EntityInsertionAdapter<CompletedOperations> __insertionAdapterOfCompletedOperations;
    private final SharedSQLiteStatement __preparedStmtOfDeleteAll;

    public CompletedOperationsDoa_Impl(RoomDatabase __db) {
        this.__db = __db;
        this.__insertionAdapterOfCompletedOperations = new EntityInsertionAdapter<CompletedOperations>(__db) { // from class: com.amazon.podcast.completed.CompletedOperationsDoa_Impl.1
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "INSERT OR REPLACE INTO `completed_operations` (`operationId`,`id`,`podcastId`,`podcastTitle`,`title`,`image`,`podcastImage`,`publishDate`,`description`,`authors`,`seasonNumber`,`completed`,`updatedTime`,`isProcessed`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            }

            @Override // androidx.room.EntityInsertionAdapter
            public void bind(SupportSQLiteStatement supportSQLiteStatement, CompletedOperations completedOperations) {
                if (completedOperations.getOperationId() == null) {
                    supportSQLiteStatement.bindNull(1);
                } else {
                    supportSQLiteStatement.bindString(1, completedOperations.getOperationId());
                }
                if (completedOperations.getId() == null) {
                    supportSQLiteStatement.bindNull(2);
                } else {
                    supportSQLiteStatement.bindString(2, completedOperations.getId());
                }
                if (completedOperations.getPodcastId() == null) {
                    supportSQLiteStatement.bindNull(3);
                } else {
                    supportSQLiteStatement.bindString(3, completedOperations.getPodcastId());
                }
                if (completedOperations.getPodcastTitle() == null) {
                    supportSQLiteStatement.bindNull(4);
                } else {
                    supportSQLiteStatement.bindString(4, completedOperations.getPodcastTitle());
                }
                if (completedOperations.getTitle() == null) {
                    supportSQLiteStatement.bindNull(5);
                } else {
                    supportSQLiteStatement.bindString(5, completedOperations.getTitle());
                }
                if (completedOperations.getImage() == null) {
                    supportSQLiteStatement.bindNull(6);
                } else {
                    supportSQLiteStatement.bindString(6, completedOperations.getImage());
                }
                if (completedOperations.getPodcastImage() == null) {
                    supportSQLiteStatement.bindNull(7);
                } else {
                    supportSQLiteStatement.bindString(7, completedOperations.getPodcastImage());
                }
                if (completedOperations.getPublishDate() == null) {
                    supportSQLiteStatement.bindNull(8);
                } else {
                    supportSQLiteStatement.bindString(8, completedOperations.getPublishDate());
                }
                if (completedOperations.getDescription() == null) {
                    supportSQLiteStatement.bindNull(9);
                } else {
                    supportSQLiteStatement.bindString(9, completedOperations.getDescription());
                }
                if (completedOperations.getAuthors() == null) {
                    supportSQLiteStatement.bindNull(10);
                } else {
                    supportSQLiteStatement.bindString(10, completedOperations.getAuthors());
                }
                if (completedOperations.getSeasonNumber() == null) {
                    supportSQLiteStatement.bindNull(11);
                } else {
                    supportSQLiteStatement.bindString(11, completedOperations.getSeasonNumber());
                }
                supportSQLiteStatement.bindLong(12, completedOperations.isCompleted() ? 1L : 0L);
                supportSQLiteStatement.bindLong(13, completedOperations.getUpdatedTime());
                supportSQLiteStatement.bindLong(14, completedOperations.isProcessed() ? 1L : 0L);
            }
        };
        this.__preparedStmtOfDeleteAll = new SharedSQLiteStatement(__db) { // from class: com.amazon.podcast.completed.CompletedOperationsDoa_Impl.2
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "DELETE FROM completed_operations";
            }
        };
    }

    @Override // com.amazon.podcast.completed.CompletedOperationsDoa
    public void insert(final CompletedOperations completed) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__insertionAdapterOfCompletedOperations.insert(completed);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // com.amazon.podcast.completed.CompletedOperationsDoa
    public List<CompletedOperations> getUnProcessed() throws Throwable {
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
        int i;
        boolean z;
        RoomSQLiteQuery roomSQLiteQueryAcquire = RoomSQLiteQuery.acquire("SELECT * FROM completed_operations WHERE isProcessed == 0", 0);
        this.__db.assertNotSuspendingTransaction();
        Cursor cursorQuery = DBUtil.query(this.__db, roomSQLiteQueryAcquire, false, null);
        try {
            columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(cursorQuery, "operationId");
            columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "id");
            columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "podcastId");
            columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "podcastTitle");
            columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "title");
            columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(cursorQuery, ContextMappingConstants.IMAGE);
            columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "podcastImage");
            columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "publishDate");
            columnIndexOrThrow9 = CursorUtil.getColumnIndexOrThrow(cursorQuery, MediaTrack.ROLE_DESCRIPTION);
            columnIndexOrThrow10 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "authors");
            columnIndexOrThrow11 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "seasonNumber");
            columnIndexOrThrow12 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "completed");
            columnIndexOrThrow13 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "updatedTime");
            roomSQLiteQuery = roomSQLiteQueryAcquire;
        } catch (Throwable th) {
            th = th;
            roomSQLiteQuery = roomSQLiteQueryAcquire;
        }
        try {
            int columnIndexOrThrow14 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "isProcessed");
            ArrayList arrayList = new ArrayList(cursorQuery.getCount());
            while (cursorQuery.moveToNext()) {
                String string = cursorQuery.isNull(columnIndexOrThrow) ? null : cursorQuery.getString(columnIndexOrThrow);
                String string2 = cursorQuery.isNull(columnIndexOrThrow2) ? null : cursorQuery.getString(columnIndexOrThrow2);
                String string3 = cursorQuery.isNull(columnIndexOrThrow3) ? null : cursorQuery.getString(columnIndexOrThrow3);
                String string4 = cursorQuery.isNull(columnIndexOrThrow4) ? null : cursorQuery.getString(columnIndexOrThrow4);
                String string5 = cursorQuery.isNull(columnIndexOrThrow5) ? null : cursorQuery.getString(columnIndexOrThrow5);
                String string6 = cursorQuery.isNull(columnIndexOrThrow6) ? null : cursorQuery.getString(columnIndexOrThrow6);
                String string7 = cursorQuery.isNull(columnIndexOrThrow7) ? null : cursorQuery.getString(columnIndexOrThrow7);
                String string8 = cursorQuery.isNull(columnIndexOrThrow8) ? null : cursorQuery.getString(columnIndexOrThrow8);
                String string9 = cursorQuery.isNull(columnIndexOrThrow9) ? null : cursorQuery.getString(columnIndexOrThrow9);
                String string10 = cursorQuery.isNull(columnIndexOrThrow10) ? null : cursorQuery.getString(columnIndexOrThrow10);
                String string11 = cursorQuery.isNull(columnIndexOrThrow11) ? null : cursorQuery.getString(columnIndexOrThrow11);
                boolean z2 = cursorQuery.getInt(columnIndexOrThrow12) != 0;
                long j = cursorQuery.getLong(columnIndexOrThrow13);
                int i2 = columnIndexOrThrow14;
                if (cursorQuery.getInt(i2) != 0) {
                    i = columnIndexOrThrow;
                    z = true;
                } else {
                    i = columnIndexOrThrow;
                    z = false;
                }
                arrayList.add(new CompletedOperations(string, string2, string3, string4, string5, string6, string7, string8, string9, string10, string11, z2, j, z));
                columnIndexOrThrow = i;
                columnIndexOrThrow14 = i2;
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

    @Override // com.amazon.podcast.completed.CompletedOperationsDoa
    public void deleteIds(final List<String> ids) {
        this.__db.assertNotSuspendingTransaction();
        StringBuilder sbNewStringBuilder = StringUtil.newStringBuilder();
        sbNewStringBuilder.append("DELETE FROM completed_operations WHERE operationId IN (");
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

    @Override // com.amazon.podcast.completed.CompletedOperationsDoa
    public void updateToProcessing(final List<String> ids) {
        this.__db.assertNotSuspendingTransaction();
        StringBuilder sbNewStringBuilder = StringUtil.newStringBuilder();
        sbNewStringBuilder.append("UPDATE completed_operations SET isProcessed = 1 WHERE operationId IN (");
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
