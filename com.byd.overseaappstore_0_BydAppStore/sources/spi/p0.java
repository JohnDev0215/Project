package spi;

import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.net.Uri;
import androidx.annotation.NonNull;
import java.io.InputStream;
import spi.ti;

/* JADX INFO: loaded from: classes.dex */
public final class p0<Data> implements ti<Uri, Data> {
    public final AssetManager a;
    public final a<Data> b;

    public interface a<Data> {
        m5<Data> b(AssetManager assetManager, String str);
    }

    public static class b implements ui<Uri, AssetFileDescriptor>, a<AssetFileDescriptor> {
        public final AssetManager a;

        public b(AssetManager assetManager) {
            this.a = assetManager;
        }

        @Override // spi.ui
        public final void a() {
        }

        @Override // spi.p0.a
        public final m5<AssetFileDescriptor> b(AssetManager assetManager, String str) {
            return new tb(assetManager, str);
        }

        @Override // spi.ui
        @NonNull
        public final ti<Uri, AssetFileDescriptor> c(aj ajVar) {
            return new p0(this.a, this);
        }
    }

    public static class c implements ui<Uri, InputStream>, a<InputStream> {
        public final AssetManager a;

        public c(AssetManager assetManager) {
            this.a = assetManager;
        }

        @Override // spi.ui
        public final void a() {
        }

        @Override // spi.p0.a
        public final m5<InputStream> b(AssetManager assetManager, String str) {
            return new gs(assetManager, str);
        }

        @Override // spi.ui
        @NonNull
        public final ti<Uri, InputStream> c(aj ajVar) {
            return new p0(this.a, this);
        }
    }

    public p0(AssetManager assetManager, a<Data> aVar) {
        this.a = assetManager;
        this.b = aVar;
    }

    @Override // spi.ti
    public final boolean a(@NonNull Uri uri) {
        Uri uri2 = uri;
        return "file".equals(uri2.getScheme()) && !uri2.getPathSegments().isEmpty() && "android_asset".equals(uri2.getPathSegments().get(0));
    }

    @Override // spi.ti
    public final ti.a b(@NonNull Uri uri, int i, int i2, @NonNull lk lkVar) {
        Uri uri2 = uri;
        return new ti.a(new wj(uri2), this.b.b(this.a, uri2.toString().substring(22)));
    }
}
