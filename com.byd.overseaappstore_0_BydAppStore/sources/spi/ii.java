package spi;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.DataSource;
import java.io.File;
import java.io.FileNotFoundException;
import spi.m5;
import spi.ti;

/* JADX INFO: loaded from: classes.dex */
public final class ii implements ti<Uri, File> {
    public final Context a;

    public static final class a implements ui<Uri, File> {
        public final Context a;

        public a(Context context) {
            this.a = context;
        }

        @Override // spi.ui
        public final void a() {
        }

        @Override // spi.ui
        @NonNull
        public final ti<Uri, File> c(aj ajVar) {
            return new ii(this.a);
        }
    }

    public static class b implements m5<File> {
        public static final String[] c = {"_data"};
        public final Context a;
        public final Uri b;

        public b(Context context, Uri uri) {
            this.a = context;
            this.b = uri;
        }

        @Override // spi.m5
        @NonNull
        public final Class<File> a() {
            return File.class;
        }

        @Override // spi.m5
        public final void b() {
        }

        @Override // spi.m5
        public final void cancel() {
        }

        @Override // spi.m5
        public final void d(@NonNull Priority priority, @NonNull m5.a<? super File> aVar) {
            Cursor cursorQuery = this.a.getContentResolver().query(this.b, c, null, null, null);
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
            StringBuilder sbK = e.k("Failed to find file path for: ");
            sbK.append(this.b);
            aVar.c(new FileNotFoundException(sbK.toString()));
        }

        @Override // spi.m5
        @NonNull
        public final DataSource e() {
            return DataSource.LOCAL;
        }
    }

    public ii(Context context) {
        this.a = context;
    }

    @Override // spi.ti
    public final boolean a(@NonNull Uri uri) {
        return ki.a(uri);
    }

    @Override // spi.ti
    public final ti.a<File> b(@NonNull Uri uri, int i, int i2, @NonNull lk lkVar) {
        Uri uri2 = uri;
        return new ti.a<>(new wj(uri2), new b(this.a, uri2));
    }
}
