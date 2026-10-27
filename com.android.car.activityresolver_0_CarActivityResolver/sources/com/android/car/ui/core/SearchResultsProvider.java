package com.android.car.ui.core;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.net.Uri;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class SearchResultsProvider extends ContentProvider {
    public static final String CONTENT = "content://";
    public static final String ITEM_ID = "primaryId";
    public static final String PRIMARY_IMAGE_BLOB = "primary_image";
    public static final String SEARCH_RESULTS_PROVIDER = ".SearchResultsProvider";
    public static final String SEARCH_RESULTS_TABLE_NAME = "search_results";
    public static final String SECONDARY_IMAGE_BLOB = "secondary_image";
    public static final String SECONDARY_IMAGE_ID = "secondary";
    public static final String SUBTITLE = "subtitle";
    public static final String TITLE = "title";
    private Uri mContentUri;
    private List<ContentValues> mSearchResults = new ArrayList();

    @Override // android.content.ContentProvider
    public boolean onCreate() {
        String url = CONTENT + getProviderName() + "/" + SEARCH_RESULTS_TABLE_NAME;
        this.mContentUri = Uri.parse(url);
        return true;
    }

    private String getProviderName() {
        return getContext().getPackageName() + SEARCH_RESULTS_PROVIDER;
    }

    @Override // android.content.ContentProvider
    public Uri insert(Uri uri, ContentValues values) {
        this.mSearchResults.add(values);
        Uri contentUri = ContentUris.withAppendedId(this.mContentUri, this.mSearchResults.size() - 1);
        getContext().getContentResolver().notifyChange(contentUri, null);
        return contentUri;
    }

    @Override // android.content.ContentProvider
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        MatrixCursor cursor = new MatrixCursor(new String[]{ITEM_ID, SECONDARY_IMAGE_ID, PRIMARY_IMAGE_BLOB, SECONDARY_IMAGE_BLOB, TITLE, SUBTITLE});
        for (ContentValues values : this.mSearchResults) {
            cursor.addRow(new Object[]{values.get(ITEM_ID), values.get(SECONDARY_IMAGE_ID), values.get(PRIMARY_IMAGE_BLOB), values.get(SECONDARY_IMAGE_BLOB), values.get(TITLE), values.get(SUBTITLE)});
        }
        return cursor;
    }

    @Override // android.content.ContentProvider
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        this.mSearchResults.clear();
        return 0;
    }

    @Override // android.content.ContentProvider
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        return 0;
    }

    @Override // android.content.ContentProvider
    public String getType(Uri uri) {
        return null;
    }
}
