package com.bumptech.glide.load.o;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.text.TextUtils;
import com.bumptech.glide.load.data.d;
import com.bumptech.glide.load.o.n;
import java.io.File;
import java.io.FileNotFoundException;

/* JADX INFO: loaded from: classes.dex */
public final class k implements n<Uri, File> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final Context f2240a;

    public static final class a implements o<Uri, File> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final Context f2241a;

        public a(Context context) {
            this.f2241a = context;
        }

        @Override // com.bumptech.glide.load.o.o
        public n<Uri, File> b(r rVar) {
            return new k(this.f2241a);
        }
    }

    private static class b implements com.bumptech.glide.load.data.d<File> {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private static final String[] f2242b = {"_data"};

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private final Context f2243c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private final Uri f2244d;

        b(Context context, Uri uri) {
            this.f2243c = context;
            this.f2244d = uri;
        }

        @Override // com.bumptech.glide.load.data.d
        public Class<File> a() {
            return File.class;
        }

        @Override // com.bumptech.glide.load.data.d
        public void b() {
        }

        @Override // com.bumptech.glide.load.data.d
        public com.bumptech.glide.load.a c() {
            return com.bumptech.glide.load.a.LOCAL;
        }

        @Override // com.bumptech.glide.load.data.d
        public void cancel() {
        }

        @Override // com.bumptech.glide.load.data.d
        public void e(com.bumptech.glide.g gVar, d.a<? super File> aVar) {
            Cursor cursorQuery = this.f2243c.getContentResolver().query(this.f2244d, f2242b, null, null, null);
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
            aVar.d(new FileNotFoundException("Failed to find file path for: " + this.f2244d));
        }
    }

    public k(Context context) {
        this.f2240a = context;
    }

    @Override // com.bumptech.glide.load.o.n
    /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
    public n.a<File> a(Uri uri, int i, int i2, com.bumptech.glide.load.i iVar) {
        return new n.a<>(new com.bumptech.glide.s.b(uri), new b(this.f2240a, uri));
    }

    @Override // com.bumptech.glide.load.o.n
    /* JADX INFO: renamed from: d, reason: merged with bridge method [inline-methods] */
    public boolean b(Uri uri) {
        return com.bumptech.glide.load.data.o.b.b(uri);
    }
}
