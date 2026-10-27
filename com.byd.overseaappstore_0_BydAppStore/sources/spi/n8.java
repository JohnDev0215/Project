package spi;

import androidx.annotation.NonNull;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public final class n8<Z> implements wm<Z> {
    public final boolean a;
    public final boolean b;
    public final wm<Z> c;
    public final a d;
    public final ig e;
    public int f;
    public boolean g;

    public interface a {
        void a(ig igVar, n8<?> n8Var);
    }

    public n8(wm<Z> wmVar, boolean z, boolean z2, ig igVar, a aVar) {
        Objects.requireNonNull(wmVar, "Argument must not be null");
        this.c = wmVar;
        this.a = z;
        this.b = z2;
        this.e = igVar;
        Objects.requireNonNull(aVar, "Argument must not be null");
        this.d = aVar;
    }

    @Override // spi.wm
    public final int a() {
        return this.c.a();
    }

    public final synchronized void b() {
        if (this.g) {
            throw new IllegalStateException("Cannot acquire a recycled resource");
        }
        this.f++;
    }

    @Override // spi.wm
    @NonNull
    public final Class<Z> c() {
        return this.c.c();
    }

    public final void d() {
        boolean z;
        synchronized (this) {
            int i = this.f;
            if (i <= 0) {
                throw new IllegalStateException("Cannot release a recycled or not yet acquired resource");
            }
            z = true;
            int i2 = i - 1;
            this.f = i2;
            if (i2 != 0) {
                z = false;
            }
        }
        if (z) {
            this.d.a(this.e, this);
        }
    }

    @Override // spi.wm
    @NonNull
    public final Z get() {
        return this.c.get();
    }

    @Override // spi.wm
    public final synchronized void recycle() {
        if (this.f > 0) {
            throw new IllegalStateException("Cannot recycle a resource while it is still acquired");
        }
        if (this.g) {
            throw new IllegalStateException("Cannot recycle a resource that has already been recycled");
        }
        this.g = true;
        if (this.b) {
            this.c.recycle();
        }
    }

    public final synchronized String toString() {
        return "EngineResource{isMemoryCacheable=" + this.a + ", listener=" + this.d + ", key=" + this.e + ", acquired=" + this.f + ", isRecycled=" + this.g + ", resource=" + this.c + '}';
    }
}
