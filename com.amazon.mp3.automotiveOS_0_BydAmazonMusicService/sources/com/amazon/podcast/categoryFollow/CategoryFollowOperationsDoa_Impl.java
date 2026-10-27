package com.amazon.podcast.categoryFollow;

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
public final class CategoryFollowOperationsDoa_Impl implements CategoryFollowOperationsDoa {
    private final RoomDatabase __db;
    private final EntityInsertionAdapter<CategoryFollowOperations> __insertionAdapterOfCategoryFollowOperations;
    private final SharedSQLiteStatement __preparedStmtOfDeleteAll;

    public CategoryFollowOperationsDoa_Impl(RoomDatabase __db) {
        this.__db = __db;
        this.__insertionAdapterOfCategoryFollowOperations = new EntityInsertionAdapter<CategoryFollowOperations>(__db) { // from class: com.amazon.podcast.categoryFollow.CategoryFollowOperationsDoa_Impl.1
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "INSERT OR REPLACE INTO `category_follow_operations` (`operationId`,`elementId`,`title`,`followed`,`updatedTime`,`isProcessed`) VALUES (?,?,?,?,?,?)";
            }

            @Override // androidx.room.EntityInsertionAdapter
            public void bind(SupportSQLiteStatement supportSQLiteStatement, CategoryFollowOperations categoryFollowOperations) {
                if (categoryFollowOperations.getOperationId() == null) {
                    supportSQLiteStatement.bindNull(1);
                } else {
                    supportSQLiteStatement.bindString(1, categoryFollowOperations.getOperationId());
                }
                if (categoryFollowOperations.getElementId() == null) {
                    supportSQLiteStatement.bindNull(2);
                } else {
                    supportSQLiteStatement.bindString(2, categoryFollowOperations.getElementId());
                }
                if (categoryFollowOperations.getTitle() == null) {
                    supportSQLiteStatement.bindNull(3);
                } else {
                    supportSQLiteStatement.bindString(3, categoryFollowOperations.getTitle());
                }
                supportSQLiteStatement.bindLong(4, categoryFollowOperations.isFollowed() ? 1L : 0L);
                supportSQLiteStatement.bindLong(5, categoryFollowOperations.getUpdatedTime());
                supportSQLiteStatement.bindLong(6, categoryFollowOperations.isProcessed() ? 1L : 0L);
            }
        };
        this.__preparedStmtOfDeleteAll = new SharedSQLiteStatement(__db) { // from class: com.amazon.podcast.categoryFollow.CategoryFollowOperationsDoa_Impl.2
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "DELETE FROM category_follow_operations";
            }
        };
    }

    @Override // com.amazon.podcast.categoryFollow.CategoryFollowOperationsDoa
    public void insert(final CategoryFollowOperations operation) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__insertionAdapterOfCategoryFollowOperations.insert(operation);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // com.amazon.podcast.categoryFollow.CategoryFollowOperationsDoa
    public List<CategoryFollowOperations> getUnProcessed() {
        RoomSQLiteQuery roomSQLiteQueryAcquire = RoomSQLiteQuery.acquire("SELECT * FROM category_follow_operations WHERE isProcessed == 0", 0);
        this.__db.assertNotSuspendingTransaction();
        Cursor cursorQuery = DBUtil.query(this.__db, roomSQLiteQueryAcquire, false, null);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(cursorQuery, "operationId");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "elementId");
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "title");
            int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "followed");
            int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "updatedTime");
            int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "isProcessed");
            ArrayList arrayList = new ArrayList(cursorQuery.getCount());
            while (cursorQuery.moveToNext()) {
                arrayList.add(new CategoryFollowOperations(cursorQuery.isNull(columnIndexOrThrow) ? null : cursorQuery.getString(columnIndexOrThrow), cursorQuery.isNull(columnIndexOrThrow2) ? null : cursorQuery.getString(columnIndexOrThrow2), cursorQuery.isNull(columnIndexOrThrow3) ? null : cursorQuery.getString(columnIndexOrThrow3), cursorQuery.getInt(columnIndexOrThrow4) != 0, cursorQuery.getLong(columnIndexOrThrow5), cursorQuery.getInt(columnIndexOrThrow6) != 0));
            }
            return arrayList;
        } finally {
            cursorQuery.close();
            roomSQLiteQueryAcquire.release();
        }
    }

    @Override // com.amazon.podcast.categoryFollow.CategoryFollowOperationsDoa
    public void deleteIds(final List<String> ids) {
        this.__db.assertNotSuspendingTransaction();
        StringBuilder sbNewStringBuilder = StringUtil.newStringBuilder();
        sbNewStringBuilder.append("DELETE FROM category_follow_operations WHERE operationId IN (");
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

    @Override // com.amazon.podcast.categoryFollow.CategoryFollowOperationsDoa
    public void updateToProcessing(final List<String> ids) {
        this.__db.assertNotSuspendingTransaction();
        StringBuilder sbNewStringBuilder = StringUtil.newStringBuilder();
        sbNewStringBuilder.append("UPDATE category_follow_operations SET isProcessed = 1 WHERE operationId IN (");
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
