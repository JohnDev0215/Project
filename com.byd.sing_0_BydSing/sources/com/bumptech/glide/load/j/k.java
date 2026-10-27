package com.bumptech.glide.load.j;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.text.TextUtils;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.i.d;
import com.bumptech.glide.load.j.n;
import java.io.File;
import java.io.FileNotFoundException;

/* JADX INFO: compiled from: MediaStoreFileLoader.java */
/* JADX INFO: loaded from: classes.dex */
public final class k implements n<Uri, File> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final Context f2986a;

    /* JADX INFO: compiled from: MediaStoreFileLoader.java */
    public static final class a implements o<Uri, File> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final Context f2987a;

        public a(Context context) {
            this.f2987a = context;
        }

        @Override // com.bumptech.glide.load.j.o
        public n<Uri, File> b(r rVar) {
            return new k(this.f2987a);
        }
    }

    /* JADX INFO: compiled from: MediaStoreFileLoader.java */
    private static class b implements com.bumptech.glide.load.i.d<File> {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private static final String[] f2988b = {"_data"};

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private final Context f2989c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private final Uri f2990d;

        b(Context context, Uri uri) {
            this.f2989c = context;
            this.f2990d = uri;
        }

        @Override // com.bumptech.glide.load.i.d
        public Class<File> a() {
            return File.class;
        }

        @Override // com.bumptech.glide.load.i.d
        public void b() {
        }

        @Override // com.bumptech.glide.load.i.d
        public void cancel() {
        }

        @Override // com.bumptech.glide.load.i.d
        public DataSource e() {
            return DataSource.LOCAL;
        }

        @Override // com.bumptech.glide.load.i.d
        public void f(Priority priority, d.a<? super File> aVar) {
            Cursor cursorQuery = this.f2989c.getContentResolver().query(this.f2990d, f2988b, null, null, null);
            if (cursorQuery != null) {
                try {
                    string = cursorQuery.moveToFirst() ? cursorQuery.getString(cursorQuery.getColumnIndexOrThrow("_data")) : null;
                } finally {
                    cursorQuery.close();
                }
            }
            if (!TextUtils.isEmpty(string)) {
                aVar.d(new File(string));
                return;
            }
            aVar.c(new FileNotFoundException("Failed to find file path for: " + this.f2990d));
        }
    }

    public k(Context context) {
        this.f2986a = context;
    }

    @Override // com.bumptech.glide.load.j.n
    /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
    public n.a<File> a(Uri uri, int i, int i2, com.bumptech.glide.load.e eVar) {
        return new n.a<>(new com.bumptech.glide.l.b(uri), new b(this.f2986a, uri));
    }

    @Override // com.bumptech.glide.load.j.n
    /* JADX INFO: renamed from: d, reason: merged with bridge method [inline-methods] */
    public boolean b(Uri uri) {
        return com.bumptech.glide.load.i.p.b.b(uri);
    }
}
