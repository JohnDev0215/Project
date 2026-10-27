package com.amazon.podcast.downloads;

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
public final class DownloadOperationsDao_Impl implements DownloadOperationsDao {
    private final RoomDatabase __db;
    private final EntityInsertionAdapter<DownloadOperations> __insertionAdapterOfDownloadOperations;
    private final SharedSQLiteStatement __preparedStmtOfDeleteAll;

    public DownloadOperationsDao_Impl(RoomDatabase __db) {
        this.__db = __db;
        this.__insertionAdapterOfDownloadOperations = new EntityInsertionAdapter<DownloadOperations>(__db) { // from class: com.amazon.podcast.downloads.DownloadOperationsDao_Impl.1
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "INSERT OR REPLACE INTO `download_operations` (`operationId`,`id`,`podcastId`,`podcastTitle`,`title`,`image`,`podcastImage`,`publishDate`,`description`,`authors`,`seasonNumber`,`durationMilliseconds`,`explicit`,`state`,`localAudioUrl`,`updatedTime`,`isProcessed`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            }

            @Override // androidx.room.EntityInsertionAdapter
            public void bind(SupportSQLiteStatement supportSQLiteStatement, DownloadOperations downloadOperations) {
                if (downloadOperations.getOperationId() == null) {
                    supportSQLiteStatement.bindNull(1);
                } else {
                    supportSQLiteStatement.bindString(1, downloadOperations.getOperationId());
                }
                if (downloadOperations.getId() == null) {
                    supportSQLiteStatement.bindNull(2);
                } else {
                    supportSQLiteStatement.bindString(2, downloadOperations.getId());
                }
                if (downloadOperations.getPodcastId() == null) {
                    supportSQLiteStatement.bindNull(3);
                } else {
                    supportSQLiteStatement.bindString(3, downloadOperations.getPodcastId());
                }
                if (downloadOperations.getPodcastTitle() == null) {
                    supportSQLiteStatement.bindNull(4);
                } else {
                    supportSQLiteStatement.bindString(4, downloadOperations.getPodcastTitle());
                }
                if (downloadOperations.getTitle() == null) {
                    supportSQLiteStatement.bindNull(5);
                } else {
                    supportSQLiteStatement.bindString(5, downloadOperations.getTitle());
                }
                if (downloadOperations.getImage() == null) {
                    supportSQLiteStatement.bindNull(6);
                } else {
                    supportSQLiteStatement.bindString(6, downloadOperations.getImage());
                }
                if (downloadOperations.getPodcastImage() == null) {
                    supportSQLiteStatement.bindNull(7);
                } else {
                    supportSQLiteStatement.bindString(7, downloadOperations.getPodcastImage());
                }
                if (downloadOperations.getPublishDate() == null) {
                    supportSQLiteStatement.bindNull(8);
                } else {
                    supportSQLiteStatement.bindString(8, downloadOperations.getPublishDate());
                }
                if (downloadOperations.getDescription() == null) {
                    supportSQLiteStatement.bindNull(9);
                } else {
                    supportSQLiteStatement.bindString(9, downloadOperations.getDescription());
                }
                if (downloadOperations.getAuthors() == null) {
                    supportSQLiteStatement.bindNull(10);
                } else {
                    supportSQLiteStatement.bindString(10, downloadOperations.getAuthors());
                }
                if (downloadOperations.getSeasonNumber() == null) {
                    supportSQLiteStatement.bindNull(11);
                } else {
                    supportSQLiteStatement.bindString(11, downloadOperations.getSeasonNumber());
                }
                supportSQLiteStatement.bindLong(12, downloadOperations.getDurationMilliseconds());
                supportSQLiteStatement.bindLong(13, downloadOperations.isExplicit() ? 1L : 0L);
                if (downloadOperations.getState() == null) {
                    supportSQLiteStatement.bindNull(14);
                } else {
                    supportSQLiteStatement.bindString(14, downloadOperations.getState());
                }
                if (downloadOperations.getLocalAudioUri() == null) {
                    supportSQLiteStatement.bindNull(15);
                } else {
                    supportSQLiteStatement.bindString(15, downloadOperations.getLocalAudioUri());
                }
                supportSQLiteStatement.bindLong(16, downloadOperations.getUpdatedTime());
                supportSQLiteStatement.bindLong(17, downloadOperations.isProcessed() ? 1L : 0L);
            }
        };
        this.__preparedStmtOfDeleteAll = new SharedSQLiteStatement(__db) { // from class: com.amazon.podcast.downloads.DownloadOperationsDao_Impl.2
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "DELETE FROM download_operations";
            }
        };
    }

    @Override // com.amazon.podcast.downloads.DownloadOperationsDao
    public List<DownloadOperations> getUnProcessed() throws Throwable {
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
        int i;
        boolean z;
        boolean z2;
        RoomSQLiteQuery roomSQLiteQueryAcquire = RoomSQLiteQuery.acquire("SELECT * FROM download_operations WHERE isProcessed == 0", 0);
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
            columnIndexOrThrow12 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "durationMilliseconds");
            columnIndexOrThrow13 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "explicit");
            columnIndexOrThrow14 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "state");
            roomSQLiteQuery = roomSQLiteQueryAcquire;
        } catch (Throwable th) {
            th = th;
            roomSQLiteQuery = roomSQLiteQueryAcquire;
        }
        try {
            int columnIndexOrThrow15 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "localAudioUrl");
            int columnIndexOrThrow16 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "updatedTime");
            int columnIndexOrThrow17 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "isProcessed");
            int i2 = columnIndexOrThrow14;
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
                long j = cursorQuery.getLong(columnIndexOrThrow12);
                if (cursorQuery.getInt(columnIndexOrThrow13) != 0) {
                    i = i2;
                    z = true;
                } else {
                    i = i2;
                    z = false;
                }
                String string12 = cursorQuery.isNull(i) ? null : cursorQuery.getString(i);
                int i3 = columnIndexOrThrow15;
                int i4 = columnIndexOrThrow;
                String string13 = cursorQuery.isNull(i3) ? null : cursorQuery.getString(i3);
                int i5 = columnIndexOrThrow16;
                long j2 = cursorQuery.getLong(i5);
                int i6 = columnIndexOrThrow17;
                if (cursorQuery.getInt(i6) != 0) {
                    columnIndexOrThrow17 = i6;
                    z2 = true;
                } else {
                    columnIndexOrThrow17 = i6;
                    z2 = false;
                }
                arrayList.add(new DownloadOperations(string, string2, string3, string4, string5, string6, string7, string8, string9, string10, string11, j, z, string12, string13, j2, z2));
                columnIndexOrThrow = i4;
                columnIndexOrThrow15 = i3;
                columnIndexOrThrow16 = i5;
                i2 = i;
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

    @Override // com.amazon.podcast.downloads.DownloadOperationsDao
    public void deleteIds(final List<String> ids) {
        this.__db.assertNotSuspendingTransaction();
        StringBuilder sbNewStringBuilder = StringUtil.newStringBuilder();
        sbNewStringBuilder.append("DELETE FROM download_operations WHERE operationId IN (");
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

    @Override // com.amazon.podcast.downloads.DownloadOperationsDao
    public void updateToProcessing(final List<String> ids) {
        this.__db.assertNotSuspendingTransaction();
        StringBuilder sbNewStringBuilder = StringUtil.newStringBuilder();
        sbNewStringBuilder.append("UPDATE download_operations SET isProcessed = 1 WHERE operationId IN (");
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
