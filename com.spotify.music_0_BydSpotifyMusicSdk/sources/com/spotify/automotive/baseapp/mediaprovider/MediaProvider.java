package com.spotify.automotive.baseapp.mediaprovider;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Looper;
import android.os.ParcelFileDescriptor;
import java.io.File;
import p.s50;
import p.v03;
import p.v4;

/* JADX INFO: loaded from: classes.dex */
public final class MediaProvider extends s50 {
    public v4 c;

    @Override // android.content.ContentProvider
    public final int delete(Uri uri, String str, String[] strArr) {
        v03.j(uri, "uri");
        throw new UnsupportedOperationException();
    }

    @Override // android.content.ContentProvider
    public final String[] getStreamTypes(Uri uri, String str) {
        v03.j(uri, "uri");
        v03.j(str, "mimeTypeFilter");
        v4 v4Var = this.c;
        if (v4Var != null) {
            return (String[]) v4Var.l;
        }
        v03.Q("mediaProviderDelegate");
        throw null;
    }

    @Override // android.content.ContentProvider
    public final String getType(Uri uri) {
        v03.j(uri, "uri");
        return null;
    }

    @Override // android.content.ContentProvider
    public final Uri insert(Uri uri, ContentValues contentValues) {
        v03.j(uri, "uri");
        throw new UnsupportedOperationException();
    }

    @Override // p.s50, android.content.ContentProvider
    public final boolean onCreate() {
        super.onCreate();
        return true;
    }

    @Override // android.content.ContentProvider
    public final synchronized ParcelFileDescriptor openFile(Uri uri, String str) {
        v03.j(uri, "uri");
        v03.j(str, "mode");
        Context context = getContext();
        if (context == null) {
            return super.openFile(uri, str);
        }
        if (Looper.getMainLooper().getThread() == Thread.currentThread()) {
            throw new IllegalStateException("Image loading should not happen on the main thread.".toString());
        }
        v4 v4Var = this.c;
        if (v4Var == null) {
            v03.Q("mediaProviderDelegate");
            throw null;
        }
        File cacheDir = context.getCacheDir();
        v03.i(cacheDir, "context.cacheDir");
        return v4Var.K(uri, cacheDir);
    }

    @Override // android.content.ContentProvider
    public final Cursor query(Uri uri, String[] strArr, String str, String[] strArr2, String str2) {
        v03.j(uri, "uri");
        throw new UnsupportedOperationException();
    }

    @Override // android.content.ContentProvider
    public final int update(Uri uri, ContentValues contentValues, String str, String[] strArr) {
        v03.j(uri, "uri");
        throw new UnsupportedOperationException();
    }
}
