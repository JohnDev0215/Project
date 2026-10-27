package com.bumptech.glide.load.i.y;

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
import com.bumptech.glide.load.data.d;
import com.bumptech.glide.load.i.n;
import com.bumptech.glide.load.i.o;
import com.bumptech.glide.load.i.r;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;

/* JADX INFO: compiled from: QMediaStoreUriLoader.java */
/* JADX INFO: loaded from: classes.dex */
public final class e<DataT> implements n<Uri, DataT> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final Context f3269a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final n<File, DataT> f3270b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final n<Uri, DataT> f3271c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final Class<DataT> f3272d;

    /* JADX INFO: compiled from: QMediaStoreUriLoader.java */
    private static abstract class a<DataT> implements o<Uri, DataT> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final Context f3273a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final Class<DataT> f3274b;

        a(Context context, Class<DataT> cls) {
            this.f3273a = context;
            this.f3274b = cls;
        }

        @Override // com.bumptech.glide.load.i.o
        public final n<Uri, DataT> b(r rVar) {
            return new e(this.f3273a, rVar.d(File.class, this.f3274b), rVar.d(Uri.class, this.f3274b), this.f3274b);
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
    private static final class d<DataT> implements com.bumptech.glide.load.data.d<DataT> {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private static final String[] f3275b = {"_data"};

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private final Context f3276c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private final n<File, DataT> f3277d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        private final n<Uri, DataT> f3278e;
        private final Uri f;
        private final int g;
        private final int h;
        private final com.bumptech.glide.load.e i;
        private final Class<DataT> j;
        private volatile boolean k;
        private volatile com.bumptech.glide.load.data.d<DataT> l;

        d(Context context, n<File, DataT> nVar, n<Uri, DataT> nVar2, Uri uri, int i, int i2, com.bumptech.glide.load.e eVar, Class<DataT> cls) {
            this.f3276c = context.getApplicationContext();
            this.f3277d = nVar;
            this.f3278e = nVar2;
            this.f = uri;
            this.g = i;
            this.h = i2;
            this.i = eVar;
            this.j = cls;
        }

        private n.a<DataT> d() throws FileNotFoundException {
            if (Environment.isExternalStorageLegacy()) {
                return this.f3277d.a(h(this.f), this.g, this.h, this.i);
            }
            return this.f3278e.a(g() ? MediaStore.setRequireOriginal(this.f) : this.f, this.g, this.h, this.i);
        }

        private com.bumptech.glide.load.data.d<DataT> f() throws FileNotFoundException {
            n.a<DataT> aVarD = d();
            if (aVarD != null) {
                return aVarD.f3223c;
            }
            return null;
        }

        private boolean g() {
            return this.f3276c.checkSelfPermission("android.permission.ACCESS_MEDIA_LOCATION") == 0;
        }

        private File h(Uri uri) throws FileNotFoundException {
            Cursor cursor = null;
            try {
                Cursor cursorQuery = this.f3276c.getContentResolver().query(uri, f3275b, null, null, null);
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
        public DataSource c() {
            return DataSource.LOCAL;
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
        public void e(Priority priority, d.a<? super DataT> aVar) {
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
                    dVarF.e(priority, aVar);
                }
            } catch (FileNotFoundException e2) {
                aVar.d(e2);
            }
        }
    }

    e(Context context, n<File, DataT> nVar, n<Uri, DataT> nVar2, Class<DataT> cls) {
        this.f3269a = context.getApplicationContext();
        this.f3270b = nVar;
        this.f3271c = nVar2;
        this.f3272d = cls;
    }

    @Override // com.bumptech.glide.load.i.n
    /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
    public n.a<DataT> a(Uri uri, int i, int i2, com.bumptech.glide.load.e eVar) {
        return new n.a<>(new com.bumptech.glide.n.b(uri), new d(this.f3269a, this.f3270b, this.f3271c, uri, i, i2, eVar, this.f3272d));
    }

    @Override // com.bumptech.glide.load.i.n
    /* JADX INFO: renamed from: d, reason: merged with bridge method [inline-methods] */
    public boolean b(Uri uri) {
        return Build.VERSION.SDK_INT >= 29 && com.bumptech.glide.load.data.o.b.b(uri);
    }
}
