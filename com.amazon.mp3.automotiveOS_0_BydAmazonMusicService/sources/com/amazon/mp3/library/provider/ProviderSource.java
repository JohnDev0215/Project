package com.amazon.mp3.library.provider;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.net.Uri;

/* JADX INFO: loaded from: classes3.dex */
public interface ProviderSource {
    int bulkInsert(Uri uri, ContentValues[] values);

    int delete(Uri uri, String selection, String[] selectionArgs) throws SQLException;

    Uri insert(Uri uri, ContentValues values);

    void onRegistered(Context context);

    Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder);

    int update(Uri uri, ContentValues values, String selection, String[] selectionArgs);
}
