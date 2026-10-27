package com.bumptech.glide.load.i;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.text.TextUtils;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.data.d;
import com.bumptech.glide.load.i.n;
import java.io.File;
import java.io.FileNotFoundException;

/* JADX INFO: compiled from: MediaStoreFileLoader.java */
/* JADX INFO: loaded from: classes.dex */
public final class k implements n<Uri, File> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final Context f3210a;

    /* JADX INFO: compiled from: MediaStoreFileLoader.java */
    public static final class a implements o<Uri, File> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final Context f3211a;

        public a(Context context) {
            this.f3211a = context;
        }

        @Override // com.bumptech.glide.load.i.o
        public n<Uri, File> b(r rVar) {
            return new k(this.f3211a);
        }
    }

    /* JADX INFO: compiled from: MediaStoreFileLoader.java */
    private static class b implements com.bumptech.glide.load.data.d<File> {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private static final String[] f3212b = {"_data"};

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private final Context f3213c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private final Uri f3214d;

        b(Context context, Uri uri) {
            this.f3213c = context;
            this.f3214d = uri;
        }

        @Override // com.bumptech.glide.load.data.d
        public Class<File> a() {
            return File.class;
        }

        @Override // com.bumptech.glide.load.data.d
        public void b() {
        }

        @Override // com.bumptech.glide.load.data.d
        public DataSource c() {
            return DataSource.LOCAL;
        }

        @Override // com.bumptech.glide.load.data.d
        public void cancel() {
        }

        @Override // com.bumptech.glide.load.data.d
        public void e(Priority priority, d.a<? super File> aVar) {
            Cursor cursorQuery = this.f3213c.getContentResolver().query(this.f3214d, f3212b, null, null, null);
            if (cursorQuery != null) {
                try {
                    string = cursorQuery.moveToFirst() ? cursorQuery.getString(cursorQuery.getColumnIndexOrThrow("_data")) : null;
                } finally {
                    cursorQuery.close();
                }
            }
            if (!TextUtils.isEmpty(string)) {
                aVar.f(new File(string));
                return;
            }
            aVar.d(new FileNotFoundException("Failed to find file path for: " + this.f3214d));
        }
    }

    public k(Context context) {
        this.f3210a = context;
    }

    @Override // com.bumptech.glide.load.i.n
    /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
    public n.a<File> a(Uri uri, int i, int i2, com.bumptech.glide.load.e eVar) {
        return new n.a<>(new com.bumptech.glide.n.b(uri), new b(this.f3210a, uri));
    }

    @Override // com.bumptech.glide.load.i.n
    /* JADX INFO: renamed from: d, reason: merged with bridge method [inline-methods] */
    public boolean b(Uri uri) {
        return com.bumptech.glide.load.data.o.b.b(uri);
    }
}
