package com.amazon.music.inappmessaging.internal.cache.dao;

import android.database.Cursor;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.amazon.music.inappmessaging.external.model.Trigger;
import com.amazon.music.inappmessaging.internal.cache.converters.DynamicMessageConverter;
import com.amazon.music.inappmessaging.internal.cache.converters.TriggerConverter;
import com.amazon.music.inappmessaging.internal.cache.entity.DynamicMessagesPerTrigger;
import java.util.Collections;
import java.util.List;

/* JADX INFO: loaded from: classes4.dex */
public final class DynamicMessagingDao_Impl implements DynamicMessagingDao {
    private final RoomDatabase __db;
    private final EntityInsertionAdapter<DynamicMessagesPerTrigger> __insertionAdapterOfDynamicMessagesPerTrigger;
    private final SharedSQLiteStatement __preparedStmtOfDeleteAllEntries;

    public DynamicMessagingDao_Impl(RoomDatabase __db) {
        this.__db = __db;
        this.__insertionAdapterOfDynamicMessagesPerTrigger = new EntityInsertionAdapter<DynamicMessagesPerTrigger>(__db) { // from class: com.amazon.music.inappmessaging.internal.cache.dao.DynamicMessagingDao_Impl.1
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "INSERT OR REPLACE INTO `dynamicMessages` (`trigger`,`dynamicMessages`,`epochTimeToLive`) VALUES (?,?,?)";
            }

            @Override // androidx.room.EntityInsertionAdapter
            public void bind(SupportSQLiteStatement stmt, DynamicMessagesPerTrigger value) {
                String strFromTrigger = TriggerConverter.fromTrigger(value.getTrigger());
                if (strFromTrigger == null) {
                    stmt.bindNull(1);
                } else {
                    stmt.bindString(1, strFromTrigger);
                }
                String strFromDynamicMessages = DynamicMessageConverter.fromDynamicMessages(value.getDynamicMessages());
                if (strFromDynamicMessages == null) {
                    stmt.bindNull(2);
                } else {
                    stmt.bindString(2, strFromDynamicMessages);
                }
                stmt.bindLong(3, value.getEpochTimeToLive());
            }
        };
        this.__preparedStmtOfDeleteAllEntries = new SharedSQLiteStatement(__db) { // from class: com.amazon.music.inappmessaging.internal.cache.dao.DynamicMessagingDao_Impl.2
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "DELETE FROM dynamicMessages";
            }
        };
    }

    @Override // com.amazon.music.inappmessaging.internal.cache.dao.DynamicMessagingDao
    public void addMessages(final List<DynamicMessagesPerTrigger> dynamicMessagesPerTriggers) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__insertionAdapterOfDynamicMessagesPerTrigger.insert(dynamicMessagesPerTriggers);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // com.amazon.music.inappmessaging.internal.cache.dao.DynamicMessagingDao
    public void deleteAllEntries() {
        this.__db.assertNotSuspendingTransaction();
        SupportSQLiteStatement supportSQLiteStatementAcquire = this.__preparedStmtOfDeleteAllEntries.acquire();
        this.__db.beginTransaction();
        try {
            supportSQLiteStatementAcquire.executeUpdateDelete();
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
            this.__preparedStmtOfDeleteAllEntries.release(supportSQLiteStatementAcquire);
        }
    }

    @Override // com.amazon.music.inappmessaging.internal.cache.dao.DynamicMessagingDao
    public DynamicMessagesPerTrigger getDynamicMessages(Trigger trigger) {
        RoomSQLiteQuery roomSQLiteQueryAcquire = RoomSQLiteQuery.acquire("SELECT * FROM dynamicMessages WHERE `trigger` = ?", 1);
        String strFromTrigger = TriggerConverter.fromTrigger(trigger);
        if (strFromTrigger == null) {
            roomSQLiteQueryAcquire.bindNull(1);
        } else {
            roomSQLiteQueryAcquire.bindString(1, strFromTrigger);
        }
        this.__db.assertNotSuspendingTransaction();
        DynamicMessagesPerTrigger dynamicMessagesPerTrigger = null;
        String string = null;
        Cursor cursorQuery = DBUtil.query(this.__db, roomSQLiteQueryAcquire, false, null);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(cursorQuery, "trigger");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "dynamicMessages");
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "epochTimeToLive");
            if (cursorQuery.moveToFirst()) {
                Trigger triggerFromString = TriggerConverter.fromString(cursorQuery.isNull(columnIndexOrThrow) ? null : cursorQuery.getString(columnIndexOrThrow));
                if (!cursorQuery.isNull(columnIndexOrThrow2)) {
                    string = cursorQuery.getString(columnIndexOrThrow2);
                }
                dynamicMessagesPerTrigger = new DynamicMessagesPerTrigger(triggerFromString, DynamicMessageConverter.fromString(string), cursorQuery.getLong(columnIndexOrThrow3));
            }
            return dynamicMessagesPerTrigger;
        } finally {
            cursorQuery.close();
            roomSQLiteQueryAcquire.release();
        }
    }

    public static List<Class<?>> getRequiredConverters() {
        return Collections.emptyList();
    }
}
