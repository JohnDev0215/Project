package com.byd.auto_photo;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.net.Uri;
import com.byd.util.w;

/* JADX INFO: loaded from: classes.dex */
public class DynaConfigContentProvider extends ContentProvider {
    @Override // android.content.ContentProvider
    public int delete(Uri uri, String str, String[] strArr) {
        return 0;
    }

    @Override // android.content.ContentProvider
    public String getType(Uri uri) {
        return null;
    }

    @Override // android.content.ContentProvider
    public Uri insert(Uri uri, ContentValues contentValues) {
        return null;
    }

    @Override // android.content.ContentProvider
    public boolean onCreate() {
        return true;
    }

    @Override // android.content.ContentProvider
    public Cursor query(Uri uri, String[] strArr, String str, String[] strArr2, String str2) {
        boolean z = !com.byd.util.a.c(getContext().getApplicationContext());
        w.c("DynaConfigContentProvider photo -> showInAppList : " + z);
        MatrixCursor matrixCursor = new MatrixCursor(new String[]{"ShowInAppList", "AppStopEnable", "AppForceStopEnable", "AppClearDateEnable", "AppClearCacheEnable"});
        Boolean bool = Boolean.TRUE;
        matrixCursor.addRow(new Object[]{Boolean.valueOf(z), Boolean.FALSE, bool, bool, bool});
        return matrixCursor;
    }

    @Override // android.content.ContentProvider
    public int update(Uri uri, ContentValues contentValues, String str, String[] strArr) {
        return 0;
    }
}
