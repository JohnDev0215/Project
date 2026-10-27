package io.sentry.android.core;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;

/* JADX INFO: loaded from: classes.dex */
public abstract class G extends ContentProvider {
    public final e7.f l = new e7.f(14, (byte) 0);

    @Override // android.content.ContentProvider
    public final int delete(Uri uri, String str, String[] strArr) {
        ((B) this.l.f14480m).getClass();
        return 0;
    }

    @Override // android.content.ContentProvider
    public final Uri insert(Uri uri, ContentValues contentValues) {
        ((B) this.l.f14480m).getClass();
        return null;
    }

    @Override // android.content.ContentProvider
    public final Cursor query(Uri uri, String[] strArr, String str, String[] strArr2, String str2) {
        ((B) this.l.f14480m).getClass();
        return null;
    }

    @Override // android.content.ContentProvider
    public final int update(Uri uri, ContentValues contentValues, String str, String[] strArr) {
        ((B) this.l.f14480m).getClass();
        return 0;
    }
}
