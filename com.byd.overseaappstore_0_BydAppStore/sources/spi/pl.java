package spi;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.ParcelFileDescriptor;
import android.provider.MediaStore;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.DataSource;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import spi.m5;
import spi.ti;

/* JADX INFO: loaded from: classes.dex */
@RequiresApi(29)
public final class pl<DataT> implements ti<Uri, DataT> {
    public final Context a;
    public final ti<File, DataT> b;
    public final ti<Uri, DataT> c;
    public final Class<DataT> d;

    public static abstract class a<DataT> implements ui<Uri, DataT> {
        public final Context a;
        public final Class<DataT> b;

        public a(Context context, Class<DataT> cls) {
            this.a = context;
            this.b = cls;
        }

        @Override // spi.ui
        public final void a() {
        }

        @Override // spi.ui
        @NonNull
        public final ti<Uri, DataT> c(@NonNull aj ajVar) {
            return new pl(this.a, ajVar.c(File.class, this.b), ajVar.c(Uri.class, this.b), this.b);
        }
    }

    @RequiresApi(29)
    public static final class b extends a<ParcelFileDescriptor> {
        public b(Context context) {
            super(context, ParcelFileDescriptor.class);
        }
    }

    @RequiresApi(29)
    public static final class c extends a<InputStream> {
        public c(Context context) {
            super(context, InputStream.class);
        }
    }

    public static final class d<DataT> implements m5<DataT> {
        public static final String[] k = {"_data"};
        public final Context a;
        public final ti<File, DataT> b;
        public final ti<Uri, DataT> c;
        public final Uri d;
        public final int e;
        public final int f;
        public final lk g;
        public final Class<DataT> h;
        public volatile boolean i;

        @Nullable
        public volatile m5<DataT> j;

        public d(Context context, ti<File, DataT> tiVar, ti<Uri, DataT> tiVar2, Uri uri, int i, int i2, lk lkVar, Class<DataT> cls) {
            this.a = context.getApplicationContext();
            this.b = tiVar;
            this.c = tiVar2;
            this.d = uri;
            this.e = i;
            this.f = i2;
            this.g = lkVar;
            this.h = cls;
        }

        @Override // spi.m5
        @NonNull
        public final Class<DataT> a() {
            return this.h;
        }

        @Override // spi.m5
        public final void b() {
            m5<DataT> m5Var = this.j;
            if (m5Var != null) {
                m5Var.b();
            }
        }

        @Nullable
        public final m5<DataT> c() throws Throwable {
            ti.a<DataT> aVarB;
            Cursor cursor = null;
            if (Environment.isExternalStorageLegacy()) {
                ti<File, DataT> tiVar = this.b;
                Uri uri = this.d;
                try {
                    Cursor cursorQuery = this.a.getContentResolver().query(uri, k, null, null, null);
                    if (cursorQuery != null) {
                        try {
                            if (cursorQuery.moveToFirst()) {
                                String string = cursorQuery.getString(cursorQuery.getColumnIndexOrThrow("_data"));
                                if (TextUtils.isEmpty(string)) {
                                    throw new FileNotFoundException("File path was empty in media store for: " + uri);
                                }
                                File file = new File(string);
                                cursorQuery.close();
                                aVarB = tiVar.b(file, this.e, this.f, this.g);
                            }
                        } catch (Throwable th) {
                            th = th;
                            cursor = cursorQuery;
                            if (cursor != null) {
                                cursor.close();
                            }
                            throw th;
                        }
                    }
                    throw new FileNotFoundException("Failed to media store entry for: " + uri);
                } catch (Throwable th2) {
                    th = th2;
                }
            } else {
                aVarB = this.c.b(this.a.checkSelfPermission("android.permission.ACCESS_MEDIA_LOCATION") == 0 ? MediaStore.setRequireOriginal(this.d) : this.d, this.e, this.f, this.g);
            }
            if (aVarB != null) {
                return aVarB.c;
            }
            return null;
        }

        @Override // spi.m5
        public final void cancel() {
            this.i = true;
            m5<DataT> m5Var = this.j;
            if (m5Var != null) {
                m5Var.cancel();
            }
        }

        @Override // spi.m5
        public final void d(@NonNull Priority priority, @NonNull m5.a<? super DataT> aVar) throws Throwable {
            try {
                m5<DataT> m5VarC = c();
                if (m5VarC == null) {
                    aVar.c(new IllegalArgumentException("Failed to build fetcher for: " + this.d));
                    return;
                }
                this.j = m5VarC;
                if (this.i) {
                    cancel();
                } else {
                    m5VarC.d(priority, aVar);
                }
            } catch (FileNotFoundException e) {
                aVar.c(e);
            }
        }

        @Override // spi.m5
        @NonNull
        public final DataSource e() {
            return DataSource.LOCAL;
        }
    }

    public pl(Context context, ti<File, DataT> tiVar, ti<Uri, DataT> tiVar2, Class<DataT> cls) {
        this.a = context.getApplicationContext();
        this.b = tiVar;
        this.c = tiVar2;
        this.d = cls;
    }

    @Override // spi.ti
    public final boolean a(@NonNull Uri uri) {
        return Build.VERSION.SDK_INT >= 29 && ki.a(uri);
    }

    @Override // spi.ti
    public final ti.a b(@NonNull Uri uri, int i, int i2, @NonNull lk lkVar) {
        Uri uri2 = uri;
        return new ti.a(new wj(uri2), new d(this.a, this.b, this.c, uri2, i, i2, lkVar, this.d));
    }
}
