package spi;

import android.net.Uri;
import androidx.annotation.NonNull;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import spi.ti;

/* JADX INFO: loaded from: classes.dex */
public final class pu<Data> implements ti<Uri, Data> {
    public static final Set<String> b = Collections.unmodifiableSet(new HashSet(Arrays.asList("http", "https")));
    public final ti<sd, Data> a;

    public static class a implements ui<Uri, InputStream> {
        @Override // spi.ui
        public final void a() {
        }

        @Override // spi.ui
        @NonNull
        public final ti<Uri, InputStream> c(aj ajVar) {
            return new pu(ajVar.c(sd.class, InputStream.class));
        }
    }

    public pu(ti<sd, Data> tiVar) {
        this.a = tiVar;
    }

    @Override // spi.ti
    public final boolean a(@NonNull Uri uri) {
        return b.contains(uri.getScheme());
    }

    @Override // spi.ti
    public final ti.a b(@NonNull Uri uri, int i, int i2, @NonNull lk lkVar) {
        return this.a.b(new sd(uri.toString()), i, i2, lkVar);
    }
}
