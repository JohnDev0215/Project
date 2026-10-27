package spi;

import android.content.ContentResolver;
import android.content.res.AssetFileDescriptor;
import android.net.Uri;
import android.os.ParcelFileDescriptor;
import androidx.annotation.NonNull;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import spi.ti;

/* JADX INFO: loaded from: classes.dex */
public final class nu<Data> implements ti<Uri, Data> {
    public static final Set<String> b = Collections.unmodifiableSet(new HashSet(Arrays.asList("file", "android.resource", "content")));
    public final c<Data> a;

    public static final class a implements ui<Uri, AssetFileDescriptor>, c<AssetFileDescriptor> {
        public final ContentResolver a;

        public a(ContentResolver contentResolver) {
            this.a = contentResolver;
        }

        @Override // spi.ui
        public final void a() {
        }

        @Override // spi.nu.c
        public final m5<AssetFileDescriptor> b(Uri uri) {
            return new n0(this.a, uri);
        }

        @Override // spi.ui
        public final ti<Uri, AssetFileDescriptor> c(aj ajVar) {
            return new nu(this);
        }
    }

    public static class b implements ui<Uri, ParcelFileDescriptor>, c<ParcelFileDescriptor> {
        public final ContentResolver a;

        public b(ContentResolver contentResolver) {
            this.a = contentResolver;
        }

        @Override // spi.ui
        public final void a() {
        }

        @Override // spi.nu.c
        public final m5<ParcelFileDescriptor> b(Uri uri) {
            return new ub(this.a, uri);
        }

        @Override // spi.ui
        @NonNull
        public final ti<Uri, ParcelFileDescriptor> c(aj ajVar) {
            return new nu(this);
        }
    }

    public interface c<Data> {
        m5<Data> b(Uri uri);
    }

    public static class d implements ui<Uri, InputStream>, c<InputStream> {
        public final ContentResolver a;

        public d(ContentResolver contentResolver) {
            this.a = contentResolver;
        }

        @Override // spi.ui
        public final void a() {
        }

        @Override // spi.nu.c
        public final m5<InputStream> b(Uri uri) {
            return new js(this.a, uri);
        }

        @Override // spi.ui
        @NonNull
        public final ti<Uri, InputStream> c(aj ajVar) {
            return new nu(this);
        }
    }

    public nu(c<Data> cVar) {
        this.a = cVar;
    }

    @Override // spi.ti
    public final boolean a(@NonNull Uri uri) {
        return b.contains(uri.getScheme());
    }

    @Override // spi.ti
    public final ti.a b(@NonNull Uri uri, int i, int i2, @NonNull lk lkVar) {
        Uri uri2 = uri;
        return new ti.a(new wj(uri2), this.a.b(uri2));
    }
}
