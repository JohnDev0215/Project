package androidx.startup;

import A9.k;
import android.content.ComponentName;
import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Trace;
import io.sentry.android.core.performance.e;
import u3.C2523a;

/* JADX INFO: loaded from: classes.dex */
public class InitializationProvider extends ContentProvider {
    @Override // android.content.ContentProvider
    public final int delete(Uri uri, String str, String[] strArr) {
        throw new IllegalStateException("Not allowed.");
    }

    @Override // android.content.ContentProvider
    public final String getType(Uri uri) {
        throw new IllegalStateException("Not allowed.");
    }

    @Override // android.content.ContentProvider
    public final Uri insert(Uri uri, ContentValues contentValues) {
        throw new IllegalStateException("Not allowed.");
    }

    @Override // android.content.ContentProvider
    public final boolean onCreate() {
        e.d(this);
        Context context = getContext();
        if (context == null) {
            k kVar = new k("Context cannot be null");
            e.e(this);
            throw kVar;
        }
        if (context.getApplicationContext() != null) {
            C2523a c2523aC = C2523a.c(context);
            Context context2 = c2523aC.c;
            try {
                try {
                    h6.e.f("Startup");
                    c2523aC.a(context2.getPackageManager().getProviderInfo(new ComponentName(context2.getPackageName(), InitializationProvider.class.getName()), 128).metaData);
                } catch (PackageManager.NameNotFoundException e10) {
                    throw new k(e10);
                }
            } finally {
                Trace.endSection();
            }
        }
        e.e(this);
        return true;
    }

    @Override // android.content.ContentProvider
    public final Cursor query(Uri uri, String[] strArr, String str, String[] strArr2, String str2) {
        throw new IllegalStateException("Not allowed.");
    }

    @Override // android.content.ContentProvider
    public final int update(Uri uri, ContentValues contentValues, String str, String[] strArr) {
        throw new IllegalStateException("Not allowed.");
    }
}
