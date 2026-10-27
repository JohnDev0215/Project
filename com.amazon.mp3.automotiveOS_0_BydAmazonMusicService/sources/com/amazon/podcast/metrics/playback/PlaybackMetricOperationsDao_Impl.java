package com.amazon.podcast.metrics.playback;

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
public final class PlaybackMetricOperationsDao_Impl implements PlaybackMetricOperationsDao {
    private final RoomDatabase __db;
    private final EntityInsertionAdapter<PlaybackMetricOperations> __insertionAdapterOfPlaybackMetricOperations;
    private final SharedSQLiteStatement __preparedStmtOfDeleteAll;

    public PlaybackMetricOperationsDao_Impl(RoomDatabase __db) {
        this.__db = __db;
        this.__insertionAdapterOfPlaybackMetricOperations = new EntityInsertionAdapter<PlaybackMetricOperations>(__db) { // from class: com.amazon.podcast.metrics.playback.PlaybackMetricOperationsDao_Impl.1
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "INSERT OR REPLACE INTO `playback_metric_operations` (`operationId`,`id`,`mediaId`,`mediaCollectionType`,`playbackSignalType`,`metricsPreset`,`playbackRequestedAtMillisecocnds`,`playbackStartedAtMillisecocnds`,`isMediaDownloaded`,`currentProgressMilliseconds`,`currentPlaybackSpeed`,`playbackStartOffsetMilliseconds`,`initialPlaybackStartDelayMilliseconds`,`rebufferDurationMilliseconds`,`rebufferCount`,`updatedTime`,`ingressSource`,`audioUri`,`errorException`,`mediaTitle`,`mediaCollectionId`,`mediaSubTitle`,`processed`,`playbackInstanceId`,`playbackEngineType`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            }

            @Override // androidx.room.EntityInsertionAdapter
            public void bind(SupportSQLiteStatement supportSQLiteStatement, PlaybackMetricOperations playbackMetricOperations) {
                if (playbackMetricOperations.getOperationId() == null) {
                    supportSQLiteStatement.bindNull(1);
                } else {
                    supportSQLiteStatement.bindString(1, playbackMetricOperations.getOperationId());
                }
                if (playbackMetricOperations.getId() == null) {
                    supportSQLiteStatement.bindNull(2);
                } else {
                    supportSQLiteStatement.bindString(2, playbackMetricOperations.getId());
                }
                if (playbackMetricOperations.getMediaId() == null) {
                    supportSQLiteStatement.bindNull(3);
                } else {
                    supportSQLiteStatement.bindString(3, playbackMetricOperations.getMediaId());
                }
                if (playbackMetricOperations.getMediaCollectionType() == null) {
                    supportSQLiteStatement.bindNull(4);
                } else {
                    supportSQLiteStatement.bindString(4, playbackMetricOperations.getMediaCollectionType());
                }
                if (playbackMetricOperations.getPlaybackSignalType() == null) {
                    supportSQLiteStatement.bindNull(5);
                } else {
                    supportSQLiteStatement.bindString(5, playbackMetricOperations.getPlaybackSignalType());
                }
                if (playbackMetricOperations.getMetricsPreset() == null) {
                    supportSQLiteStatement.bindNull(6);
                } else {
                    supportSQLiteStatement.bindString(6, playbackMetricOperations.getMetricsPreset());
                }
                supportSQLiteStatement.bindLong(7, playbackMetricOperations.getPlaybackRequestedAtMillisecocnds());
                supportSQLiteStatement.bindLong(8, playbackMetricOperations.getPlaybackStartedAtMillisecocnds());
                supportSQLiteStatement.bindLong(9, playbackMetricOperations.isMediaDownloaded() ? 1L : 0L);
                supportSQLiteStatement.bindLong(10, playbackMetricOperations.getCurrentProgressMilliseconds());
                supportSQLiteStatement.bindDouble(11, playbackMetricOperations.getCurrentPlaybackSpeed());
                supportSQLiteStatement.bindLong(12, playbackMetricOperations.getPlaybackStartOffsetMilliseconds());
                supportSQLiteStatement.bindLong(13, playbackMetricOperations.getInitialPlaybackStartDelayMilliseconds());
                supportSQLiteStatement.bindLong(14, playbackMetricOperations.getRebufferDurationMilliseconds());
                supportSQLiteStatement.bindLong(15, playbackMetricOperations.getRebufferCount());
                supportSQLiteStatement.bindLong(16, playbackMetricOperations.getUpdatedTime());
                if (playbackMetricOperations.getIngressSource() == null) {
                    supportSQLiteStatement.bindNull(17);
                } else {
                    supportSQLiteStatement.bindString(17, playbackMetricOperations.getIngressSource());
                }
                if (playbackMetricOperations.getAudioUri() == null) {
                    supportSQLiteStatement.bindNull(18);
                } else {
                    supportSQLiteStatement.bindString(18, playbackMetricOperations.getAudioUri());
                }
                if (playbackMetricOperations.getErrorException() == null) {
                    supportSQLiteStatement.bindNull(19);
                } else {
                    supportSQLiteStatement.bindString(19, playbackMetricOperations.getErrorException());
                }
                if (playbackMetricOperations.getMediaTitle() == null) {
                    supportSQLiteStatement.bindNull(20);
                } else {
                    supportSQLiteStatement.bindString(20, playbackMetricOperations.getMediaTitle());
                }
                if (playbackMetricOperations.getMediaCollectionId() == null) {
                    supportSQLiteStatement.bindNull(21);
                } else {
                    supportSQLiteStatement.bindString(21, playbackMetricOperations.getMediaCollectionId());
                }
                if (playbackMetricOperations.getMediaSubTitle() == null) {
                    supportSQLiteStatement.bindNull(22);
                } else {
                    supportSQLiteStatement.bindString(22, playbackMetricOperations.getMediaSubTitle());
                }
                supportSQLiteStatement.bindLong(23, playbackMetricOperations.isProcessed() ? 1L : 0L);
                if (playbackMetricOperations.getPlaybackInstanceId() == null) {
                    supportSQLiteStatement.bindNull(24);
                } else {
                    supportSQLiteStatement.bindString(24, playbackMetricOperations.getPlaybackInstanceId());
                }
                if (playbackMetricOperations.getPlaybackEngineType() == null) {
                    supportSQLiteStatement.bindNull(25);
                } else {
                    supportSQLiteStatement.bindString(25, playbackMetricOperations.getPlaybackEngineType());
                }
            }
        };
        this.__preparedStmtOfDeleteAll = new SharedSQLiteStatement(__db) { // from class: com.amazon.podcast.metrics.playback.PlaybackMetricOperationsDao_Impl.2
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "DELETE FROM playback_metric_operations";
            }
        };
    }

    @Override // com.amazon.podcast.metrics.playback.PlaybackMetricOperationsDao
    public void insert(final PlaybackMetricOperations playbackMetric) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__insertionAdapterOfPlaybackMetricOperations.insert(playbackMetric);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // com.amazon.podcast.metrics.playback.PlaybackMetricOperationsDao
    public List<PlaybackMetricOperations> getUnProcessed() throws Throwable {
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
        String string;
        int i;
        String string2;
        int i2;
        String string3;
        int i3;
        String string4;
        int i4;
        String string5;
        int i5;
        String string6;
        int i6;
        int i7;
        boolean z;
        String string7;
        int i8;
        String string8;
        RoomSQLiteQuery roomSQLiteQueryAcquire = RoomSQLiteQuery.acquire("SELECT * FROM playback_metric_operations WHERE processed == 0", 0);
        this.__db.assertNotSuspendingTransaction();
        Cursor cursorQuery = DBUtil.query(this.__db, roomSQLiteQueryAcquire, false, null);
        try {
            columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(cursorQuery, "operationId");
            columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "id");
            columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "mediaId");
            columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "mediaCollectionType");
            columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "playbackSignalType");
            columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "metricsPreset");
            columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "playbackRequestedAtMillisecocnds");
            columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "playbackStartedAtMillisecocnds");
            columnIndexOrThrow9 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "isMediaDownloaded");
            columnIndexOrThrow10 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "currentProgressMilliseconds");
            columnIndexOrThrow11 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "currentPlaybackSpeed");
            columnIndexOrThrow12 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "playbackStartOffsetMilliseconds");
            columnIndexOrThrow13 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "initialPlaybackStartDelayMilliseconds");
            columnIndexOrThrow14 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "rebufferDurationMilliseconds");
            roomSQLiteQuery = roomSQLiteQueryAcquire;
        } catch (Throwable th) {
            th = th;
            roomSQLiteQuery = roomSQLiteQueryAcquire;
        }
        try {
            int columnIndexOrThrow15 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "rebufferCount");
            int columnIndexOrThrow16 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "updatedTime");
            int columnIndexOrThrow17 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "ingressSource");
            int columnIndexOrThrow18 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "audioUri");
            int columnIndexOrThrow19 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "errorException");
            int columnIndexOrThrow20 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "mediaTitle");
            int columnIndexOrThrow21 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "mediaCollectionId");
            int columnIndexOrThrow22 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "mediaSubTitle");
            int columnIndexOrThrow23 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "processed");
            int columnIndexOrThrow24 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "playbackInstanceId");
            int columnIndexOrThrow25 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "playbackEngineType");
            int i9 = columnIndexOrThrow14;
            ArrayList arrayList = new ArrayList(cursorQuery.getCount());
            while (cursorQuery.moveToNext()) {
                String string9 = cursorQuery.isNull(columnIndexOrThrow) ? null : cursorQuery.getString(columnIndexOrThrow);
                String string10 = cursorQuery.isNull(columnIndexOrThrow2) ? null : cursorQuery.getString(columnIndexOrThrow2);
                String string11 = cursorQuery.isNull(columnIndexOrThrow3) ? null : cursorQuery.getString(columnIndexOrThrow3);
                String string12 = cursorQuery.isNull(columnIndexOrThrow4) ? null : cursorQuery.getString(columnIndexOrThrow4);
                String string13 = cursorQuery.isNull(columnIndexOrThrow5) ? null : cursorQuery.getString(columnIndexOrThrow5);
                String string14 = cursorQuery.isNull(columnIndexOrThrow6) ? null : cursorQuery.getString(columnIndexOrThrow6);
                long j = cursorQuery.getLong(columnIndexOrThrow7);
                long j2 = cursorQuery.getLong(columnIndexOrThrow8);
                boolean z2 = cursorQuery.getInt(columnIndexOrThrow9) != 0;
                long j3 = cursorQuery.getLong(columnIndexOrThrow10);
                float f = cursorQuery.getFloat(columnIndexOrThrow11);
                long j4 = cursorQuery.getLong(columnIndexOrThrow12);
                long j5 = cursorQuery.getLong(columnIndexOrThrow13);
                int i10 = i9;
                long j6 = cursorQuery.getLong(i10);
                int i11 = columnIndexOrThrow;
                int i12 = columnIndexOrThrow15;
                int i13 = cursorQuery.getInt(i12);
                columnIndexOrThrow15 = i12;
                int i14 = columnIndexOrThrow16;
                long j7 = cursorQuery.getLong(i14);
                columnIndexOrThrow16 = i14;
                int i15 = columnIndexOrThrow17;
                if (cursorQuery.isNull(i15)) {
                    columnIndexOrThrow17 = i15;
                    i = columnIndexOrThrow18;
                    string = null;
                } else {
                    string = cursorQuery.getString(i15);
                    columnIndexOrThrow17 = i15;
                    i = columnIndexOrThrow18;
                }
                if (cursorQuery.isNull(i)) {
                    columnIndexOrThrow18 = i;
                    i2 = columnIndexOrThrow19;
                    string2 = null;
                } else {
                    string2 = cursorQuery.getString(i);
                    columnIndexOrThrow18 = i;
                    i2 = columnIndexOrThrow19;
                }
                if (cursorQuery.isNull(i2)) {
                    columnIndexOrThrow19 = i2;
                    i3 = columnIndexOrThrow20;
                    string3 = null;
                } else {
                    string3 = cursorQuery.getString(i2);
                    columnIndexOrThrow19 = i2;
                    i3 = columnIndexOrThrow20;
                }
                if (cursorQuery.isNull(i3)) {
                    columnIndexOrThrow20 = i3;
                    i4 = columnIndexOrThrow21;
                    string4 = null;
                } else {
                    string4 = cursorQuery.getString(i3);
                    columnIndexOrThrow20 = i3;
                    i4 = columnIndexOrThrow21;
                }
                if (cursorQuery.isNull(i4)) {
                    columnIndexOrThrow21 = i4;
                    i5 = columnIndexOrThrow22;
                    string5 = null;
                } else {
                    string5 = cursorQuery.getString(i4);
                    columnIndexOrThrow21 = i4;
                    i5 = columnIndexOrThrow22;
                }
                if (cursorQuery.isNull(i5)) {
                    columnIndexOrThrow22 = i5;
                    i6 = columnIndexOrThrow23;
                    string6 = null;
                } else {
                    string6 = cursorQuery.getString(i5);
                    columnIndexOrThrow22 = i5;
                    i6 = columnIndexOrThrow23;
                }
                if (cursorQuery.getInt(i6) != 0) {
                    columnIndexOrThrow23 = i6;
                    i7 = columnIndexOrThrow24;
                    z = true;
                } else {
                    columnIndexOrThrow23 = i6;
                    i7 = columnIndexOrThrow24;
                    z = false;
                }
                if (cursorQuery.isNull(i7)) {
                    columnIndexOrThrow24 = i7;
                    i8 = columnIndexOrThrow25;
                    string7 = null;
                } else {
                    string7 = cursorQuery.getString(i7);
                    columnIndexOrThrow24 = i7;
                    i8 = columnIndexOrThrow25;
                }
                if (cursorQuery.isNull(i8)) {
                    columnIndexOrThrow25 = i8;
                    string8 = null;
                } else {
                    string8 = cursorQuery.getString(i8);
                    columnIndexOrThrow25 = i8;
                }
                arrayList.add(new PlaybackMetricOperations(string9, string10, string11, string12, string13, string14, j, j2, z2, j3, f, j4, j5, j6, i13, j7, string, string2, string4, string5, string6, string3, z, string7, string8));
                columnIndexOrThrow = i11;
                i9 = i10;
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

    @Override // com.amazon.podcast.metrics.playback.PlaybackMetricOperationsDao
    public void deleteIds(final List<String> ids) {
        this.__db.assertNotSuspendingTransaction();
        StringBuilder sbNewStringBuilder = StringUtil.newStringBuilder();
        sbNewStringBuilder.append("DELETE FROM playback_metric_operations WHERE operationId IN (");
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

    @Override // com.amazon.podcast.metrics.playback.PlaybackMetricOperationsDao
    public void updateToProcessing(final List<String> ids) {
        this.__db.assertNotSuspendingTransaction();
        StringBuilder sbNewStringBuilder = StringUtil.newStringBuilder();
        sbNewStringBuilder.append("UPDATE playback_metric_operations SET processed = 1 WHERE operationId IN (");
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
