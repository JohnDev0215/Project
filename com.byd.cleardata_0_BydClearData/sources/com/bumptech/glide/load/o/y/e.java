package com.bumptech.glide.load.o.y;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.ParcelFileDescriptor;
import android.provider.MediaStore;
import android.text.TextUtils;
import com.bumptech.glide.g;
import com.bumptech.glide.load.data.d;
import com.bumptech.glide.load.i;
import com.bumptech.glide.load.o.n;
import com.bumptech.glide.load.o.o;
import com.bumptech.glide.load.o.r;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;

/* JADX INFO: loaded from: classes.dex */
public final class e<DataT> implements n<Uri, DataT> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final Context f2296a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final n<File, DataT> f2297b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final n<Uri, DataT> f2298c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final Class<DataT> f2299d;

    private static abstract class a<DataT> implements o<Uri, DataT> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final Context f2300a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final Class<DataT> f2301b;

        a(Context context, Class<DataT> cls) {
            this.f2300a = context;
            this.f2301b = cls;
        }

        @Override // com.bumptech.glide.load.o.o
        public final n<Uri, DataT> b(r rVar) {
            return new e(this.f2300a, rVar.d(File.class, this.f2301b), rVar.d(Uri.class, this.f2301b), this.f2301b);
        }
    }

    public static final class b extends a<ParcelFileDescriptor> {
        public b(Context context) {
            super(context, ParcelFileDescriptor.class);
        }
    }

    public static final class c extends a<InputStream> {
        public c(Context context) {
            super(context, InputStream.class);
        }
    }

    private static final class d<DataT> implements com.bumptech.glide.load.data.d<DataT> {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private static final String[] f2302b = {"_data"};

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private final Context f2303c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private final n<File, DataT> f2304d;
        private final n<Uri, DataT> e;
        private final Uri f;
        private final int g;
        private final int h;
        private final i i;
        private final Class<DataT> j;
        private volatile boolean k;
        private volatile com.bumptech.glide.load.data.d<DataT> l;

        d(Context context, n<File, DataT> nVar, n<Uri, DataT> nVar2, Uri uri, int i, int i2, i iVar, Class<DataT> cls) {
            this.f2303c = context.getApplicationContext();
            this.f2304d = nVar;
            this.e = nVar2;
            this.f = uri;
            this.g = i;
            this.h = i2;
            this.i = iVar;
            this.j = cls;
        }

        private n.a<DataT> d() {
            if (Environment.isExternalStorageLegacy()) {
                return this.f2304d.a(h(this.f), this.g, this.h, this.i);
            }
            return this.e.a(g() ? MediaStore.setRequireOriginal(this.f) : this.f, this.g, this.h, this.i);
        }

        private com.bumptech.glide.load.data.d<DataT> f() {
            n.a<DataT> aVarD = d();
            if (aVarD != null) {
                return aVarD.f2252c;
            }
            return null;
        }

        private boolean g() {
            return this.f2303c.checkSelfPermission("android.permission.ACCESS_MEDIA_LOCATION") == 0;
        }

        private File h(Uri uri) {
            Cursor cursor = null;
            try {
                Cursor cursorQuery = this.f2303c.getContentResolver().query(uri, f2302b, null, null, null);
                if (cursorQuery == null || !cursorQuery.moveToFirst()) {
                    throw new FileNotFoundException("Failed to media store entry for: " + uri);
                }
                String string = cursorQuery.getString(cursorQuery.getColumnIndexOrThrow("_data"));
                if (!TextUtils.isEmpty(string)) {
                    File file = new File(string);
                    cursorQuery.close();
                    return file;
                }
                throw new FileNotFoundException("File path was empty in media store for: " + uri);
            } catch (Throwable th) {
                if (0 != 0) {
                    cursor.close();
                }
                throw th;
            }
        }

        @Override // com.bumptech.glide.load.data.d
        public Class<DataT> a() {
            return this.j;
        }

        @Override // com.bumptech.glide.load.data.d
        public void b() {
            com.bumptech.glide.load.data.d<DataT> dVar = this.l;
            if (dVar != null) {
                dVar.b();
            }
        }

        @Override // com.bumptech.glide.load.data.d
        public com.bumptech.glide.load.a c() {
            return com.bumptech.glide.load.a.LOCAL;
        }

        @Override // com.bumptech.glide.load.data.d
        public void cancel() {
            this.k = true;
            com.bumptech.glide.load.data.d<DataT> dVar = this.l;
            if (dVar != null) {
                dVar.cancel();
            }
        }

        @Override // com.bumptech.glide.load.data.d
        public void e(g gVar, d.a<? super DataT> aVar) {
            try {
                com.bumptech.glide.load.data.d<DataT> dVarF = f();
                if (dVarF == null) {
                    aVar.d(new IllegalArgumentException("Failed to build fetcher for: " + this.f));
                    return;
                }
                this.l = dVarF;
                if (this.k) {
                    cancel();
                } else {
                    dVarF.e(gVar, aVar);
                }
            } catch (FileNotFoundException e) {
                aVar.d(e);
            }
        }
    }

    e(Context context, n<File, DataT> nVar, n<Uri, DataT> nVar2, Class<DataT> cls) {
        this.f2296a = context.getApplicationContext();
        this.f2297b = nVar;
        this.f2298c = nVar2;
        this.f2299d = cls;
    }

    @Override // com.bumptech.glide.load.o.n
    /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
    public n.a<DataT> a(Uri uri, int i, int i2, i iVar) {
        return new n.a<>(new com.bumptech.glide.s.b(uri), new d(this.f2296a, this.f2297b, this.f2298c, uri, i, i2, iVar, this.f2299d));
    }

    @Override // com.bumptech.glide.load.o.n
    /* JADX INFO: renamed from: d, reason: merged with bridge method [inline-methods] */
    public boolean b(Uri uri) {
        return Build.VERSION.SDK_INT >= 29 && com.bumptech.glide.load.data.o.b.b(uri);
    }
}
