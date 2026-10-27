package T;

import java.util.ConcurrentModificationException;
import java.util.Iterator;

/* JADX INFO: loaded from: classes.dex */
public final class J implements Iterator, B7.a {
    public final /* synthetic */ int l = 0;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final z0 f8191m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final int f8192n;
    public int o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public int f8193p;

    public J(z0 z0Var, int i7, int i10) {
        this.f8191m = z0Var;
        this.f8192n = i10;
        this.o = i7;
        this.f8193p = z0Var.f8404r;
        if (z0Var.f8403q) {
            throw new ConcurrentModificationException();
        }
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        switch (this.l) {
            case 0:
                return this.o < this.f8192n;
            default:
                throw null;
        }
    }

    @Override // java.util.Iterator
    public final Object next() {
        switch (this.l) {
            case 0:
                z0 z0Var = this.f8191m;
                int i7 = z0Var.f8404r;
                int i10 = this.f8193p;
                if (i7 != i10) {
                    throw new ConcurrentModificationException();
                }
                int i11 = this.o;
                this.o = C0735d.l(z0Var.l, i11) + i11;
                return new A0(z0Var, i11, i10);
            default:
                throw null;
        }
    }

    @Override // java.util.Iterator
    public final void remove() {
        switch (this.l) {
            case 0:
                throw new UnsupportedOperationException("Operation is not supported for read-only collection");
            default:
                throw new UnsupportedOperationException("Operation is not supported for read-only collection");
        }
    }

    public J(z0 z0Var, int i7, K k10, C0735d c0735d) {
        this.f8191m = z0Var;
        this.f8192n = i7;
        this.o = z0Var.f8404r;
    }
}
