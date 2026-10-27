package spi;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.io.InputStream;
import java.util.Objects;
import java.util.Queue;
import spi.si;
import spi.ti;

/* JADX INFO: loaded from: classes.dex */
public final class ef implements ti<sd, InputStream> {
    public static final jk<Integer> b = jk.c("com.bumptech.glide.load.model.stream.HttpGlideUrlLoader.Timeout", 2500);

    @Nullable
    public final si<sd, sd> a;

    public static class a implements ui<sd, InputStream> {
        public final si<sd, sd> a = new si<>(500);

        @Override // spi.ui
        public final void a() {
        }

        @Override // spi.ui
        @NonNull
        public final ti<sd, InputStream> c(aj ajVar) {
            return new ef(this.a);
        }
    }

    public ef() {
        this(null);
    }

    public ef(@Nullable si<sd, sd> siVar) {
        this.a = siVar;
    }

    @Override // spi.ti
    public final /* bridge */ /* synthetic */ boolean a(@NonNull sd sdVar) {
        return true;
    }

    /* JADX WARN: Type inference incomplete: some casts might be missing */
    @Override // spi.ti
    public final ti.a<InputStream> b(@NonNull sd sdVar, int i, int i2, @NonNull lk lkVar) {
        sd sdVar2 = sdVar;
        si<sd, sd> siVar = this.a;
        if (siVar != null) {
            si.b<sd> bVarA = si.b.a(sdVar2);
            sd sdVarF = siVar.a.f(bVarA);
            Queue<si.b<?>> queue = si.b.d;
            synchronized (queue) {
                queue.offer(bVarA);
            }
            sd sdVar3 = sdVarF;
            if (sdVar3 == null) {
                si<sd, sd> siVar2 = this.a;
                Objects.requireNonNull(siVar2);
                siVar2.a.i(si.b.a(sdVar2), sdVar2);
            } else {
                sdVar2 = sdVar3;
            }
        }
        return new ti.a<>(sdVar2, new Cif(sdVar2, ((Integer) lkVar.c(b)).intValue()));
    }
}
