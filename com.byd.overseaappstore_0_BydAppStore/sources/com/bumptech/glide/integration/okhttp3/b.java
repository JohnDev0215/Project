package com.bumptech.glide.integration.okhttp3;

import androidx.annotation.NonNull;
import java.io.InputStream;
import spi.aj;
import spi.c3;
import spi.dk;
import spi.ek;
import spi.lk;
import spi.sd;
import spi.ti;
import spi.ui;

/* JADX INFO: loaded from: classes.dex */
public final class b implements ti<sd, InputStream> {
    public final c3.a a;

    public static class a implements ui<sd, InputStream> {
        public static volatile dk b;
        public final c3.a a;

        /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
        public a() {
            this(b);
            if (b == null) {
                synchronized (a.class) {
                    if (b == null) {
                        b = new dk();
                    }
                }
            }
        }

        public a(@NonNull c3.a aVar) {
            this.a = aVar;
        }

        @Override // spi.ui
        public final void a() {
        }

        @Override // spi.ui
        @NonNull
        public final ti<sd, InputStream> c(aj ajVar) {
            return new b(this.a);
        }
    }

    public b(@NonNull c3.a aVar) {
        this.a = aVar;
    }

    @Override // spi.ti
    public final /* bridge */ /* synthetic */ boolean a(@NonNull sd sdVar) {
        return true;
    }

    @Override // spi.ti
    public final ti.a<InputStream> b(@NonNull sd sdVar, int i, int i2, @NonNull lk lkVar) {
        sd sdVar2 = sdVar;
        return new ti.a<>(sdVar2, new ek(this.a, sdVar2));
    }
}
