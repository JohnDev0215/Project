package org.chromium.chrome.browser.base;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.net.Uri;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import org.chromium.base.BundleUtils;

/* JADX INFO: loaded from: classes.dex */
public class SplitCompatContentProvider extends ContentProvider {
    public a f;
    public final Object e = new Object();
    public final String g = "vp";

    public static abstract class a {
        public SplitCompatContentProvider a;

        public abstract void a();

        public abstract String b(Uri uri);

        public abstract void c();

        public abstract MatrixCursor d();

        public abstract void e();
    }

    public final a a() {
        a aVar;
        synchronized (this.e) {
            if (this.f == null) {
                a aVar2 = (a) BundleUtils.e(SplitCompatApplication.b(getContext()), this.g);
                this.f = aVar2;
                aVar2.a = this;
            }
            aVar = this.f;
        }
        return aVar;
    }

    @Override // android.content.ContentProvider
    public final int delete(Uri uri, String str, String[] strArr) {
        a().a();
        return 0;
    }

    @Override // android.content.ContentProvider
    public final void dump(FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        a().getClass();
    }

    @Override // android.content.ContentProvider
    public final String getType(Uri uri) {
        return a().b(uri);
    }

    @Override // android.content.ContentProvider
    public final Uri insert(Uri uri, ContentValues contentValues) {
        a().c();
        return null;
    }

    @Override // android.content.ContentProvider
    public final boolean onCreate() {
        return true;
    }

    @Override // android.content.ContentProvider
    public final Cursor query(Uri uri, String[] strArr, String str, String[] strArr2, String str2) {
        return a().d();
    }

    @Override // android.content.ContentProvider
    public final int update(Uri uri, ContentValues contentValues, String str, String[] strArr) {
        a().e();
        return 0;
    }
}
