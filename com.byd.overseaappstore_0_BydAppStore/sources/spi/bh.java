package spi;

import androidx.annotation.NonNull;
import androidx.core.util.Pools;
import java.util.Objects;
import spi.es;
import spi.ta;

/* JADX INFO: loaded from: classes.dex */
public final class bh<Z> implements wm<Z>, ta.d {
    public static final Pools.Pool<bh<?>> e = (ta.c) ta.a(20, new a());
    public final es.b a = new es.b();
    public wm<Z> b;
    public boolean c;
    public boolean d;

    public class a implements ta.b<bh<?>> {
        @Override // spi.ta.b
        public final bh<?> a() {
            return new bh<>();
        }
    }

    @NonNull
    public static <Z> bh<Z> d(wm<Z> wmVar) {
        bh<Z> bhVar = (bh) e.acquire();
        Objects.requireNonNull(bhVar, "Argument must not be null");
        bhVar.d = false;
        bhVar.c = true;
        bhVar.b = wmVar;
        return bhVar;
    }

    @Override // spi.wm
    public final int a() {
        return this.b.a();
    }

    @Override // spi.ta.d
    @NonNull
    public final es b() {
        return this.a;
    }

    @Override // spi.wm
    @NonNull
    public final Class<Z> c() {
        return this.b.c();
    }

    public final synchronized void e() {
        this.a.b();
        if (!this.c) {
            throw new IllegalStateException("Already unlocked");
        }
        this.c = false;
        if (this.d) {
            recycle();
        }
    }

    @Override // spi.wm
    @NonNull
    public final Z get() {
        return this.b.get();
    }

    @Override // spi.wm
    public final synchronized void recycle() {
        this.a.b();
        this.d = true;
        if (!this.c) {
            this.b.recycle();
            this.b = null;
            e.release(this);
        }
    }
}
