package u;

import java.util.Iterator;
import java.util.NoSuchElementException;

/* JADX INFO: renamed from: u.a, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C2461a implements Iterator, B7.a {
    public int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public int f22591m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public boolean f22592n;
    public final /* synthetic */ int o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ Object f22593p;

    public C2461a(int i7) {
        this.l = i7;
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        return this.f22591m < this.l;
    }

    @Override // java.util.Iterator
    public final Object next() {
        Object objG;
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        int i7 = this.f22591m;
        switch (this.o) {
            case 0:
                objG = ((C2465e) this.f22593p).g(i7);
                break;
            case 1:
                objG = ((C2465e) this.f22593p).k(i7);
                break;
            default:
                objG = ((C2466f) this.f22593p).f22598m[i7];
                break;
        }
        this.f22591m++;
        this.f22592n = true;
        return objG;
    }

    @Override // java.util.Iterator
    public final void remove() {
        if (!this.f22592n) {
            throw new IllegalStateException("Call next() before removing an element.");
        }
        int i7 = this.f22591m - 1;
        this.f22591m = i7;
        switch (this.o) {
            case 0:
                ((C2465e) this.f22593p).i(i7);
                break;
            case 1:
                ((C2465e) this.f22593p).i(i7);
                break;
            default:
                ((C2466f) this.f22593p).d(i7);
                break;
        }
        this.l--;
        this.f22592n = false;
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public C2461a(C2466f c2466f) {
        this(c2466f.f22599n);
        this.o = 2;
        this.f22593p = c2466f;
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public C2461a(C2465e c2465e, int i7) {
        this(c2465e.f22587n);
        this.o = i7;
        switch (i7) {
            case 1:
                this.f22593p = c2465e;
                this(c2465e.f22587n);
                break;
            default:
                this.f22593p = c2465e;
                break;
        }
    }
}
