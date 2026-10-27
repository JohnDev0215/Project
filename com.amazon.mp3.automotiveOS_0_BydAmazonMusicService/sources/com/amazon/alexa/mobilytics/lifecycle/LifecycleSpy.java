package com.amazon.alexa.mobilytics.lifecycle;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import com.amazon.alexa.mobilytics.util.Log;

/* JADX INFO: loaded from: classes2.dex */
public class LifecycleSpy extends ContentProvider {
    private static final String TAG = Log.tag(LifecycleSpy.class);

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
    public Cursor query(Uri uri, String[] strArr, String str, String[] strArr2, String str2) {
        return null;
    }

    @Override // android.content.ContentProvider
    public int update(Uri uri, ContentValues contentValues, String str, String[] strArr) {
        return 0;
    }

    @Override // android.content.ContentProvider
    public boolean onCreate() {
        Log.d(TAG, "Initializing lifecycle dispatcher.");
        if (getContext() == null) {
            return true;
        }
        LifecycleDispatcher.initialize(getContext());
        return true;
    }
}
