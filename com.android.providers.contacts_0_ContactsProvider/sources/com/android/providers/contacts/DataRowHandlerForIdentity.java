package com.android.providers.contacts;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.android.providers.contacts.aggregation.AbstractContactAggregator;

/* JADX INFO: loaded from: classes.dex */
public class DataRowHandlerForIdentity extends DataRowHandler {
    public DataRowHandlerForIdentity(Context context, ContactsDatabaseHelper contactsDatabaseHelper, AbstractContactAggregator abstractContactAggregator) {
        super(context, contactsDatabaseHelper, abstractContactAggregator, "vnd.android.cursor.item/identity");
    }

    @Override // com.android.providers.contacts.DataRowHandler
    public long insert(SQLiteDatabase sQLiteDatabase, TransactionContext transactionContext, long j, ContentValues contentValues) {
        long jInsert = super.insert(sQLiteDatabase, transactionContext, j, contentValues);
        if (contentValues.containsKey("data1") || contentValues.containsKey("data2")) {
            triggerAggregation(transactionContext, j);
        }
        return jInsert;
    }

    @Override // com.android.providers.contacts.DataRowHandler
    public boolean update(SQLiteDatabase sQLiteDatabase, TransactionContext transactionContext, ContentValues contentValues, Cursor cursor, boolean z, boolean z2) {
        super.update(sQLiteDatabase, transactionContext, contentValues, cursor, z, z2);
        long j = cursor.getLong(1);
        if (contentValues.containsKey("data1") || contentValues.containsKey("data2")) {
            triggerAggregation(transactionContext, j);
        }
        return true;
    }

    @Override // com.android.providers.contacts.DataRowHandler
    public int delete(SQLiteDatabase sQLiteDatabase, TransactionContext transactionContext, Cursor cursor) {
        int iDelete = super.delete(sQLiteDatabase, transactionContext, cursor);
        triggerAggregation(transactionContext, cursor.getLong(1));
        return iDelete;
    }
}
