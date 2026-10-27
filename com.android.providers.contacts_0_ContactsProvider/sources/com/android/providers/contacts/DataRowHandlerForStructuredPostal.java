package com.android.providers.contacts;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;
import com.android.providers.contacts.PostalSplitter;
import com.android.providers.contacts.SearchIndexManager;
import com.android.providers.contacts.aggregation.AbstractContactAggregator;

/* JADX INFO: loaded from: classes.dex */
public class DataRowHandlerForStructuredPostal extends DataRowHandler {
    private final String[] STRUCTURED_FIELDS;
    private final PostalSplitter mSplitter;

    @Override // com.android.providers.contacts.DataRowHandler
    public boolean hasSearchableData() {
        return true;
    }

    public DataRowHandlerForStructuredPostal(Context context, ContactsDatabaseHelper contactsDatabaseHelper, AbstractContactAggregator abstractContactAggregator, PostalSplitter postalSplitter) {
        super(context, contactsDatabaseHelper, abstractContactAggregator, "vnd.android.cursor.item/postal-address_v2");
        this.STRUCTURED_FIELDS = new String[]{"data4", "data5", "data6", "data7", "data8", "data9", "data10"};
        this.mSplitter = postalSplitter;
    }

    @Override // com.android.providers.contacts.DataRowHandler
    public long insert(SQLiteDatabase sQLiteDatabase, TransactionContext transactionContext, long j, ContentValues contentValues) {
        fixStructuredPostalComponents(contentValues, contentValues);
        return super.insert(sQLiteDatabase, transactionContext, j, contentValues);
    }

    @Override // com.android.providers.contacts.DataRowHandler
    public boolean update(SQLiteDatabase sQLiteDatabase, TransactionContext transactionContext, ContentValues contentValues, Cursor cursor, boolean z, boolean z2) {
        ContentValues augmentedValues = getAugmentedValues(sQLiteDatabase, cursor.getLong(0), contentValues);
        if (augmentedValues == null) {
            return false;
        }
        fixStructuredPostalComponents(augmentedValues, contentValues);
        super.update(sQLiteDatabase, transactionContext, contentValues, cursor, z, z2);
        return true;
    }

    private void fixStructuredPostalComponents(ContentValues contentValues, ContentValues contentValues2) {
        String asString = contentValues2.getAsString("data1");
        boolean z = !TextUtils.isEmpty(asString);
        boolean z2 = !areAllEmpty(contentValues2, this.STRUCTURED_FIELDS);
        PostalSplitter.Postal postal = new PostalSplitter.Postal();
        if (z && !z2) {
            this.mSplitter.split(postal, asString);
            postal.toValues(contentValues2);
        } else {
            if (z) {
                return;
            }
            if (z2 || areAnySpecified(contentValues2, this.STRUCTURED_FIELDS)) {
                postal.fromValues(contentValues);
                contentValues2.put("data1", this.mSplitter.join(postal));
            }
        }
    }

    @Override // com.android.providers.contacts.DataRowHandler
    public boolean containsSearchableColumns(ContentValues contentValues) {
        return contentValues.containsKey("data1");
    }

    @Override // com.android.providers.contacts.DataRowHandler
    public void appendSearchableData(SearchIndexManager.IndexBuilder indexBuilder) {
        indexBuilder.appendContentFromColumn("data1");
    }
}
