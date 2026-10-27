package com.amazon.podcast.metrics.ui;

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
import com.amazon.music.find.utils.PageUriUtils;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public final class UiMetricOperationsDao_Impl implements UiMetricOperationsDao {
    private final RoomDatabase __db;
    private final EntityInsertionAdapter<UiMetricOperations> __insertionAdapterOfUiMetricOperations;
    private final SharedSQLiteStatement __preparedStmtOfDeleteAll;

    public UiMetricOperationsDao_Impl(RoomDatabase __db) {
        this.__db = __db;
        this.__insertionAdapterOfUiMetricOperations = new EntityInsertionAdapter<UiMetricOperations>(__db) { // from class: com.amazon.podcast.metrics.ui.UiMetricOperationsDao_Impl.1
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "INSERT OR REPLACE INTO `ui_metric_operations` (`operationId`,`id`,`signalType`,`metricsPreset`,`pageType`,`contentSrc`,`containerType`,`entityType`,`entityIdType`,`entityId`,`detailPageItemId`,`detailPageItemIdType`,`refMarker`,`actionType`,`interactionType`,`contentName`,`containerIndex`,`entityPos`,`firstViewableIndex`,`lastViewableIndex`,`updatedTime`,`isProcessed`,`numItemsAccessible`,`containerInstId`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            }

            @Override // androidx.room.EntityInsertionAdapter
            public void bind(SupportSQLiteStatement supportSQLiteStatement, UiMetricOperations uiMetricOperations) {
                if (uiMetricOperations.getOperationId() == null) {
                    supportSQLiteStatement.bindNull(1);
                } else {
                    supportSQLiteStatement.bindString(1, uiMetricOperations.getOperationId());
                }
                if (uiMetricOperations.getId() == null) {
                    supportSQLiteStatement.bindNull(2);
                } else {
                    supportSQLiteStatement.bindString(2, uiMetricOperations.getId());
                }
                if (uiMetricOperations.getSignalType() == null) {
                    supportSQLiteStatement.bindNull(3);
                } else {
                    supportSQLiteStatement.bindString(3, uiMetricOperations.getSignalType());
                }
                if (uiMetricOperations.getMetricsPreset() == null) {
                    supportSQLiteStatement.bindNull(4);
                } else {
                    supportSQLiteStatement.bindString(4, uiMetricOperations.getMetricsPreset());
                }
                if (uiMetricOperations.getPageType() == null) {
                    supportSQLiteStatement.bindNull(5);
                } else {
                    supportSQLiteStatement.bindString(5, uiMetricOperations.getPageType());
                }
                if (uiMetricOperations.getContentSrc() == null) {
                    supportSQLiteStatement.bindNull(6);
                } else {
                    supportSQLiteStatement.bindString(6, uiMetricOperations.getContentSrc());
                }
                if (uiMetricOperations.getContainerType() == null) {
                    supportSQLiteStatement.bindNull(7);
                } else {
                    supportSQLiteStatement.bindString(7, uiMetricOperations.getContainerType());
                }
                if (uiMetricOperations.getEntityType() == null) {
                    supportSQLiteStatement.bindNull(8);
                } else {
                    supportSQLiteStatement.bindString(8, uiMetricOperations.getEntityType());
                }
                if (uiMetricOperations.getEntityIdType() == null) {
                    supportSQLiteStatement.bindNull(9);
                } else {
                    supportSQLiteStatement.bindString(9, uiMetricOperations.getEntityIdType());
                }
                if (uiMetricOperations.getEntityId() == null) {
                    supportSQLiteStatement.bindNull(10);
                } else {
                    supportSQLiteStatement.bindString(10, uiMetricOperations.getEntityId());
                }
                if (uiMetricOperations.getDetailPageItemId() == null) {
                    supportSQLiteStatement.bindNull(11);
                } else {
                    supportSQLiteStatement.bindString(11, uiMetricOperations.getDetailPageItemId());
                }
                if (uiMetricOperations.getDetailPageItemIdType() == null) {
                    supportSQLiteStatement.bindNull(12);
                } else {
                    supportSQLiteStatement.bindString(12, uiMetricOperations.getDetailPageItemIdType());
                }
                if (uiMetricOperations.getRefMarker() == null) {
                    supportSQLiteStatement.bindNull(13);
                } else {
                    supportSQLiteStatement.bindString(13, uiMetricOperations.getRefMarker());
                }
                if (uiMetricOperations.getActionType() == null) {
                    supportSQLiteStatement.bindNull(14);
                } else {
                    supportSQLiteStatement.bindString(14, uiMetricOperations.getActionType());
                }
                if (uiMetricOperations.getInteractionType() == null) {
                    supportSQLiteStatement.bindNull(15);
                } else {
                    supportSQLiteStatement.bindString(15, uiMetricOperations.getInteractionType());
                }
                if (uiMetricOperations.getContentName() == null) {
                    supportSQLiteStatement.bindNull(16);
                } else {
                    supportSQLiteStatement.bindString(16, uiMetricOperations.getContentName());
                }
                supportSQLiteStatement.bindLong(17, uiMetricOperations.getContainerIndex());
                supportSQLiteStatement.bindLong(18, uiMetricOperations.getEntityPos());
                supportSQLiteStatement.bindLong(19, uiMetricOperations.getFirstViewableIndex());
                supportSQLiteStatement.bindLong(20, uiMetricOperations.getLastViewableIndex());
                supportSQLiteStatement.bindLong(21, uiMetricOperations.getUpdatedTime());
                supportSQLiteStatement.bindLong(22, uiMetricOperations.isProcessed() ? 1L : 0L);
                supportSQLiteStatement.bindLong(23, uiMetricOperations.getNumItemsAccessible());
                if (uiMetricOperations.getContainerInstId() == null) {
                    supportSQLiteStatement.bindNull(24);
                } else {
                    supportSQLiteStatement.bindString(24, uiMetricOperations.getContainerInstId());
                }
            }
        };
        this.__preparedStmtOfDeleteAll = new SharedSQLiteStatement(__db) { // from class: com.amazon.podcast.metrics.ui.UiMetricOperationsDao_Impl.2
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "DELETE FROM ui_metric_operations";
            }
        };
    }

    @Override // com.amazon.podcast.metrics.ui.UiMetricOperationsDao
    public void insert(final UiMetricOperations uiMetric) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__insertionAdapterOfUiMetricOperations.insert(uiMetric);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // com.amazon.podcast.metrics.ui.UiMetricOperationsDao
    public List<UiMetricOperations> getUnProcessed() throws Throwable {
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
        int i2;
        boolean z;
        String string2;
        RoomSQLiteQuery roomSQLiteQueryAcquire = RoomSQLiteQuery.acquire("SELECT * FROM ui_metric_operations WHERE isProcessed == 0", 0);
        this.__db.assertNotSuspendingTransaction();
        Cursor cursorQuery = DBUtil.query(this.__db, roomSQLiteQueryAcquire, false, null);
        try {
            columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(cursorQuery, "operationId");
            columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "id");
            columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "signalType");
            columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "metricsPreset");
            columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "pageType");
            columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(cursorQuery, ContextMappingConstants.CONTENT_SRC);
            columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "containerType");
            columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(cursorQuery, PageUriUtils.ENTITY_TYPE_QUERY_PARAM);
            columnIndexOrThrow9 = CursorUtil.getColumnIndexOrThrow(cursorQuery, ContextMappingConstants.ENTITY_ID_TYPE);
            columnIndexOrThrow10 = CursorUtil.getColumnIndexOrThrow(cursorQuery, ContextMappingConstants.ENTITY_ID);
            columnIndexOrThrow11 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "detailPageItemId");
            columnIndexOrThrow12 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "detailPageItemIdType");
            columnIndexOrThrow13 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "refMarker");
            columnIndexOrThrow14 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "actionType");
            roomSQLiteQuery = roomSQLiteQueryAcquire;
        } catch (Throwable th) {
            th = th;
            roomSQLiteQuery = roomSQLiteQueryAcquire;
        }
        try {
            int columnIndexOrThrow15 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "interactionType");
            int columnIndexOrThrow16 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "contentName");
            int columnIndexOrThrow17 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "containerIndex");
            int columnIndexOrThrow18 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "entityPos");
            int columnIndexOrThrow19 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "firstViewableIndex");
            int columnIndexOrThrow20 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "lastViewableIndex");
            int columnIndexOrThrow21 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "updatedTime");
            int columnIndexOrThrow22 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "isProcessed");
            int columnIndexOrThrow23 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "numItemsAccessible");
            int columnIndexOrThrow24 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "containerInstId");
            int i3 = columnIndexOrThrow14;
            ArrayList arrayList = new ArrayList(cursorQuery.getCount());
            while (cursorQuery.moveToNext()) {
                String string3 = cursorQuery.isNull(columnIndexOrThrow) ? null : cursorQuery.getString(columnIndexOrThrow);
                String string4 = cursorQuery.isNull(columnIndexOrThrow2) ? null : cursorQuery.getString(columnIndexOrThrow2);
                String string5 = cursorQuery.isNull(columnIndexOrThrow3) ? null : cursorQuery.getString(columnIndexOrThrow3);
                String string6 = cursorQuery.isNull(columnIndexOrThrow4) ? null : cursorQuery.getString(columnIndexOrThrow4);
                String string7 = cursorQuery.isNull(columnIndexOrThrow5) ? null : cursorQuery.getString(columnIndexOrThrow5);
                String string8 = cursorQuery.isNull(columnIndexOrThrow6) ? null : cursorQuery.getString(columnIndexOrThrow6);
                String string9 = cursorQuery.isNull(columnIndexOrThrow7) ? null : cursorQuery.getString(columnIndexOrThrow7);
                String string10 = cursorQuery.isNull(columnIndexOrThrow8) ? null : cursorQuery.getString(columnIndexOrThrow8);
                String string11 = cursorQuery.isNull(columnIndexOrThrow9) ? null : cursorQuery.getString(columnIndexOrThrow9);
                String string12 = cursorQuery.isNull(columnIndexOrThrow10) ? null : cursorQuery.getString(columnIndexOrThrow10);
                String string13 = cursorQuery.isNull(columnIndexOrThrow11) ? null : cursorQuery.getString(columnIndexOrThrow11);
                String string14 = cursorQuery.isNull(columnIndexOrThrow12) ? null : cursorQuery.getString(columnIndexOrThrow12);
                if (cursorQuery.isNull(columnIndexOrThrow13)) {
                    i = i3;
                    string = null;
                } else {
                    string = cursorQuery.getString(columnIndexOrThrow13);
                    i = i3;
                }
                String string15 = cursorQuery.isNull(i) ? null : cursorQuery.getString(i);
                int i4 = columnIndexOrThrow15;
                int i5 = columnIndexOrThrow;
                String string16 = cursorQuery.isNull(i4) ? null : cursorQuery.getString(i4);
                int i6 = columnIndexOrThrow16;
                String string17 = cursorQuery.isNull(i6) ? null : cursorQuery.getString(i6);
                int i7 = columnIndexOrThrow17;
                int i8 = cursorQuery.getInt(i7);
                int i9 = columnIndexOrThrow18;
                int i10 = cursorQuery.getInt(i9);
                columnIndexOrThrow18 = i9;
                int i11 = columnIndexOrThrow19;
                int i12 = cursorQuery.getInt(i11);
                columnIndexOrThrow19 = i11;
                int i13 = columnIndexOrThrow20;
                int i14 = cursorQuery.getInt(i13);
                columnIndexOrThrow20 = i13;
                int i15 = columnIndexOrThrow21;
                long j = cursorQuery.getLong(i15);
                columnIndexOrThrow21 = i15;
                int i16 = columnIndexOrThrow22;
                if (cursorQuery.getInt(i16) != 0) {
                    z = true;
                    columnIndexOrThrow22 = i16;
                    i2 = columnIndexOrThrow23;
                } else {
                    columnIndexOrThrow22 = i16;
                    i2 = columnIndexOrThrow23;
                    z = false;
                }
                int i17 = cursorQuery.getInt(i2);
                columnIndexOrThrow23 = i2;
                int i18 = columnIndexOrThrow24;
                if (cursorQuery.isNull(i18)) {
                    columnIndexOrThrow24 = i18;
                    string2 = null;
                } else {
                    string2 = cursorQuery.getString(i18);
                    columnIndexOrThrow24 = i18;
                }
                arrayList.add(new UiMetricOperations(string3, string4, string5, string6, string7, string8, string9, string10, string11, string12, string13, string14, string, string15, string16, string17, i8, i10, i12, i14, j, z, i17, string2));
                columnIndexOrThrow = i5;
                columnIndexOrThrow15 = i4;
                columnIndexOrThrow16 = i6;
                columnIndexOrThrow17 = i7;
                i3 = i;
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

    @Override // com.amazon.podcast.metrics.ui.UiMetricOperationsDao
    public void deleteIds(final List<String> ids) {
        this.__db.assertNotSuspendingTransaction();
        StringBuilder sbNewStringBuilder = StringUtil.newStringBuilder();
        sbNewStringBuilder.append("DELETE FROM ui_metric_operations WHERE operationId IN (");
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

    @Override // com.amazon.podcast.metrics.ui.UiMetricOperationsDao
    public void updateToProcessing(final List<String> ids) {
        this.__db.assertNotSuspendingTransaction();
        StringBuilder sbNewStringBuilder = StringUtil.newStringBuilder();
        sbNewStringBuilder.append("UPDATE ui_metric_operations SET isProcessed = 1 WHERE operationId IN (");
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
