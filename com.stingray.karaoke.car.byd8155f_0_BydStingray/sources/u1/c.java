package U1;

import io.sentry.hints.i;
import java.nio.ByteBuffer;
import java.util.ConcurrentModificationException;
import o7.C2053e;

/* JADX INFO: loaded from: classes.dex */
public abstract class c {
    public int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public int f8960m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public int f8961n;
    public Object o;

    public c() {
        if (i.f16551m == null) {
            i.f16551m = new i(10);
        }
    }

    public int b(int i7) {
        if (i7 < this.f8961n) {
            return ((ByteBuffer) this.o).getShort(this.f8960m + i7);
        }
        return 0;
    }

    public void c() {
        if (((C2053e) this.o).f20554s != this.f8961n) {
            throw new ConcurrentModificationException();
        }
    }

    public void d() {
        while (true) {
            int i7 = this.l;
            C2053e c2053e = (C2053e) this.o;
            if (i7 >= c2053e.f20552q || c2053e.f20550n[i7] >= 0) {
                return;
            } else {
                this.l = i7 + 1;
            }
        }
    }

    public boolean hasNext() {
        return this.l < ((C2053e) this.o).f20552q;
    }

    public void remove() {
        c();
        if (this.f8960m == -1) {
            throw new IllegalStateException("Call next() before removing element from the iterator.");
        }
        C2053e c2053e = (C2053e) this.o;
        c2053e.c();
        c2053e.o(this.f8960m);
        this.f8960m = -1;
        this.f8961n = c2053e.f20554s;
    }
}
