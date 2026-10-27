package spi;

import androidx.annotation.NonNull;
import java.io.InputStream;
import java.net.URL;
import spi.ti;

/* JADX INFO: loaded from: classes.dex */
public final class ou implements ti<URL, InputStream> {
    public final ti<sd, InputStream> a;

    public static class a implements ui<URL, InputStream> {
        @Override // spi.ui
        public final void a() {
        }

        @Override // spi.ui
        @NonNull
        public final ti<URL, InputStream> c(aj ajVar) {
            return new ou(ajVar.c(sd.class, InputStream.class));
        }
    }

    public ou(ti<sd, InputStream> tiVar) {
        this.a = tiVar;
    }

    @Override // spi.ti
    public final /* bridge */ /* synthetic */ boolean a(@NonNull URL url) {
        return true;
    }

    @Override // spi.ti
    public final ti.a<InputStream> b(@NonNull URL url, int i, int i2, @NonNull lk lkVar) {
        return this.a.b(new sd(url), i, i2, lkVar);
    }
}
