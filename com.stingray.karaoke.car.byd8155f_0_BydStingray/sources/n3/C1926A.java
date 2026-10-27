package n3;

import java.util.Iterator;
import java.util.NoSuchElementException;
import u.AbstractC2473m;
import u.C2459G;

/* JADX INFO: renamed from: n3.A, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1926A implements Iterator, B7.a {
    public int l = -1;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public boolean f19787m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ C1927B f19788n;

    public C1926A(C1927B c1927b) {
        this.f19788n = c1927b;
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        return this.l + 1 < this.f19788n.f19790u.f();
    }

    @Override // java.util.Iterator
    public final Object next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        this.f19787m = true;
        C2459G c2459g = this.f19788n.f19790u;
        int i7 = this.l + 1;
        this.l = i7;
        return (y) c2459g.g(i7);
    }

    @Override // java.util.Iterator
    public final void remove() {
        if (!this.f19787m) {
            throw new IllegalStateException("You must call next() before you can remove an element");
        }
        C2459G c2459g = this.f19788n.f19790u;
        ((y) c2459g.g(this.l)).f19914m = null;
        int i7 = this.l;
        Object[] objArr = c2459g.f22589n;
        Object obj = objArr[i7];
        Object obj2 = AbstractC2473m.c;
        if (obj != obj2) {
            objArr[i7] = obj2;
            c2459g.l = true;
        }
        this.l = i7 - 1;
        this.f19787m = false;
    }
}
