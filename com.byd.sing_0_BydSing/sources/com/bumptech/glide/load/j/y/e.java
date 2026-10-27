package com.bumptech.glide.load.j.y;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.ParcelFileDescriptor;
import android.provider.MediaStore;
import android.text.TextUtils;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.i.d;
import com.bumptech.glide.load.j.n;
import com.bumptech.glide.load.j.o;
import com.bumptech.glide.load.j.r;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;

/* JADX INFO: compiled from: QMediaStoreUriLoader.java */
/* JADX INFO: loaded from: classes.dex */
public final class e<DataT> implements n<Uri, DataT> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final Context f3042a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final n<File, DataT> f3043b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final n<Uri, DataT> f3044c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final Class<DataT> f3045d;

    /* JADX INFO: compiled from: QMediaStoreUriLoader.java */
    private static abstract class a<DataT> implements o<Uri, DataT> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final Context f3046a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final Class<DataT> f3047b;

        a(Context context, Class<DataT> cls) {
            this.f3046a = context;
            this.f3047b = cls;
        }

        @Override // com.bumptech.glide.load.j.o
        public final n<Uri, DataT> b(r rVar) {
            return new e(this.f3046a, rVar.d(File.class, this.f3047b), rVar.d(Uri.class, this.f3047b), this.f3047b);
        }
    }

    /* JADX INFO: compiled from: QMediaStoreUriLoader.java */
    public static final class b extends a<ParcelFileDescriptor> {
        public b(Context context) {
            super(context, ParcelFileDescriptor.class);
        }
    }

    /* JADX INFO: compiled from: QMediaStoreUriLoader.java */
    public static final class c extends a<InputStream> {
        public c(Context context) {
            super(context, InputStream.class);
        }
    }

    /* JADX INFO: compiled from: QMediaStoreUriLoader.java */
    private static final class d<DataT> implements com.bumptech.glide.load.i.d<DataT> {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private static final String[] f3048b = {"_data"};

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private final Context f3049c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private final n<File, DataT> f3050d;
        private final n<Uri, DataT> e;
        private final Uri f;
        private final int g;
        private final int h;
        private final com.bumptech.glide.load.e i;
        private final Class<DataT> j;
        private volatile boolean k;
        private volatile com.bumptech.glide.load.i.d<DataT> l;

        d(Context context, n<File, DataT> nVar, n<Uri, DataT> nVar2, Uri uri, int i, int i2, com.bumptech.glide.load.e eVar, Class<DataT> cls) {
            this.f3049c = context.getApplicationContext();
            this.f3050d = nVar;
            this.e = nVar2;
            this.f = uri;
            this.g = i;
            this.h = i2;
            this.i = eVar;
            this.j = cls;
        }

        private n.a<DataT> c() throws FileNotFoundException {
            if (Environment.isExternalStorageLegacy()) {
                return this.f3050d.a(h(this.f), this.g, this.h, this.i);
            }
            return this.e.a(g() ? MediaStore.setRequireOriginal(this.f) : this.f, this.g, this.h, this.i);
        }

        private com.bumptech.glide.load.i.d<DataT> d() throws FileNotFoundException {
            n.a<DataT> aVarC = c();
            if (aVarC != null) {
                return aVarC.f2998c;
            }
            return null;
        }

        private boolean g() {
            return this.f3049c.checkSelfPermission("android.permission.ACCESS_MEDIA_LOCATION") == 0;
        }

        private File h(Uri uri) throws FileNotFoundException {
            Cursor cursor = null;
            try {
                Cursor cursorQuery = this.f3049c.getContentResolver().query(uri, f3048b, null, null, null);
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

        @Override // com.bumptech.glide.load.i.d
        public Class<DataT> a() {
            return this.j;
        }

        @Override // com.bumptech.glide.load.i.d
        public void b() {
            com.bumptech.glide.load.i.d<DataT> dVar = this.l;
            if (dVar != null) {
                dVar.b();
            }
        }

        @Override // com.bumptech.glide.load.i.d
        public void cancel() {
            this.k = true;
            com.bumptech.glide.load.i.d<DataT> dVar = this.l;
            if (dVar != null) {
                dVar.cancel();
            }
        }

        @Override // com.bumptech.glide.load.i.d
        public DataSource e() {
            return DataSource.LOCAL;
        }

        @Override // com.bumptech.glide.load.i.d
        public void f(Priority priority, d.a<? super DataT> aVar) {
            try {
                com.bumptech.glide.load.i.d<DataT> dVarD = d();
                if (dVarD == null) {
                    aVar.c(new IllegalArgumentException("Failed to build fetcher for: " + this.f));
                    return;
                }
                this.l = dVarD;
                if (this.k) {
                    cancel();
                } else {
                    dVarD.f(priority, aVar);
                }
            } catch (FileNotFoundException e) {
                aVar.c(e);
            }
        }
    }

    e(Context context, n<File, DataT> nVar, n<Uri, DataT> nVar2, Class<DataT> cls) {
        this.f3042a = context.getApplicationContext();
        this.f3043b = nVar;
        this.f3044c = nVar2;
        this.f3045d = cls;
    }

    @Override // com.bumptech.glide.load.j.n
    /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
    public n.a<DataT> a(Uri uri, int i, int i2, com.bumptech.glide.load.e eVar) {
        return new n.a<>(new com.bumptech.glide.l.b(uri), new d(this.f3042a, this.f3043b, this.f3044c, uri, i, i2, eVar, this.f3045d));
    }

    @Override // com.bumptech.glide.load.j.n
    /* JADX INFO: renamed from: d, reason: merged with bridge method [inline-methods] */
    public boolean b(Uri uri) {
        return Build.VERSION.SDK_INT >= 29 && com.bumptech.glide.load.i.p.b.b(uri);
    }
}
