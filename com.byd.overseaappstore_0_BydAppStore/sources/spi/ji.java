package spi;

import android.content.Context;
import android.net.Uri;
import androidx.annotation.NonNull;
import java.io.InputStream;
import spi.ti;
import spi.zs;

/* JADX INFO: loaded from: classes.dex */
public final class ji implements ti<Uri, InputStream> {
    public final Context a;

    public static class a implements ui<Uri, InputStream> {
        public final Context a;

        public a(Context context) {
            this.a = context;
        }

        @Override // spi.ui
        public final void a() {
        }

        @Override // spi.ui
        @NonNull
        public final ti<Uri, InputStream> c(aj ajVar) {
            return new ji(this.a);
        }
    }

    public ji(Context context) {
        this.a = context.getApplicationContext();
    }

    @Override // spi.ti
    public final boolean a(@NonNull Uri uri) {
        Uri uri2 = uri;
        return ki.a(uri2) && !uri2.getPathSegments().contains("video");
    }

    @Override // spi.ti
    public final ti.a<InputStream> b(@NonNull Uri uri, int i, int i2, @NonNull lk lkVar) {
        Uri uri2 = uri;
        if (!(i != Integer.MIN_VALUE && i2 != Integer.MIN_VALUE && i <= 512 && i2 <= 384)) {
            return null;
        }
        wj wjVar = new wj(uri2);
        Context context = this.a;
        return new ti.a<>(wjVar, zs.c(context, uri2, new zs.a(context.getContentResolver())));
    }
}
