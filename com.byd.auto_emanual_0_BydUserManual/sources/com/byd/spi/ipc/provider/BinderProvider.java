package com.byd.spi.ipc.provider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.IBinder;
import androidx.core.app.NotificationCompat;
import com.byd.foundation.utils.LogUtils;
import com.byd.spi.ipc.cursor.BinderCursor;
import spi.a;
import spi.c;

/* JADX INFO: loaded from: classes.dex */
public class BinderProvider extends ContentProvider {
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
        try {
            LogUtils.i(a.e, "spi remote get service:" + strArr2[0]);
            return new BinderCursor(new String[]{NotificationCompat.CATEGORY_SERVICE}, (IBinder) c.a().a(getContext(), Class.forName(strArr2[0])));
        } catch (Throwable th) {
            LogUtils.e(a.e, "spi remote get service failed:", th);
            return null;
        }
    }

    @Override // android.content.ContentProvider
    public int update(Uri uri, ContentValues contentValues, String str, String[] strArr) {
        return 0;
    }
}
