package com.android.providers.contacts;

import android.content.ContentValues;
import android.content.Context;
import com.android.providers.contacts.SearchIndexManager;
import com.android.providers.contacts.aggregation.AbstractContactAggregator;

/* JADX INFO: loaded from: classes.dex */
public class DataRowHandlerForNote extends DataRowHandler {
    @Override // com.android.providers.contacts.DataRowHandler
    public boolean hasSearchableData() {
        return true;
    }

    public DataRowHandlerForNote(Context context, ContactsDatabaseHelper contactsDatabaseHelper, AbstractContactAggregator abstractContactAggregator) {
        super(context, contactsDatabaseHelper, abstractContactAggregator, "vnd.android.cursor.item/note");
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
