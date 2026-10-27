package com.android.car.ui.core;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.net.Uri;
import androidx.annotation.NonNull;
import androidx.media.MediaBrowserServiceCompat;
import com.alibaba.android.arouter.utils.Consts;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class SearchResultsProvider extends ContentProvider {
    public final List<ContentValues> a = new ArrayList();

    @NonNull
    public static Uri a(@NonNull Context context) {
        StringBuilder sb = new StringBuilder();
        sb.append("content://" + context.getPackageName() + Consts.DOT + "SearchResultsProvider");
        sb.append("/");
        sb.append(MediaBrowserServiceCompat.KEY_SEARCH_RESULTS);
        return Uri.parse(sb.toString());
    }

    /* JADX WARN: Type inference failed for: r0v1, types: [java.util.ArrayList, java.util.List<android.content.ContentValues>] */
    @Override // android.content.ContentProvider
    public final int delete(Uri uri, String str, String[] strArr) {
        this.a.clear();
        return 0;
    }

    @Override // android.content.ContentProvider
    public final String getType(Uri uri) {
        return null;
    }

    /* JADX WARN: Type inference failed for: r3v1, types: [java.util.ArrayList, java.util.List<android.content.ContentValues>] */
    /* JADX WARN: Type inference failed for: r4v1, types: [java.util.ArrayList, java.util.List<android.content.ContentValues>] */
    @Override // android.content.ContentProvider
    public final Uri insert(Uri uri, ContentValues contentValues) {
        this.a.add(contentValues);
        Uri uriWithAppendedId = ContentUris.withAppendedId(a(getContext()), this.a.size() - 1);
        getContext().getContentResolver().notifyChange(uriWithAppendedId, null);
        return uriWithAppendedId;
    }

    @Override // android.content.ContentProvider
    public final boolean onCreate() {
        return true;
    }

    /* JADX WARN: Type inference failed for: r6v1, types: [java.util.ArrayList, java.util.List<android.content.ContentValues>] */
    @Override // android.content.ContentProvider
    public final Cursor query(Uri uri, String[] strArr, String str, String[] strArr2, String str2) {
        MatrixCursor matrixCursor = new MatrixCursor(new String[]{"primaryId", "secondary", "primary_image", "secondary_image", "title", "subtitle"});
        for (ContentValues contentValues : this.a) {
            matrixCursor.addRow(new Object[]{contentValues.get("primaryId"), contentValues.get("secondary"), contentValues.get("primary_image"), contentValues.get("secondary_image"), contentValues.get("title"), contentValues.get("subtitle")});
        }
        return matrixCursor;
    }

    @Override // android.content.ContentProvider
    public final int update(Uri uri, ContentValues contentValues, String str, String[] strArr) {
        return 0;
    }
}
