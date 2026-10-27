package d5;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

/* JADX INFO: loaded from: classes.dex */
public final class r implements Iterator {
    public int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public int f13449m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public int f13450n;
    public final /* synthetic */ C1098u o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ int f13451p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final /* synthetic */ C1098u f13452q;

    public r(C1098u c1098u, int i7) {
        this.f13451p = i7;
        this.f13452q = c1098u;
        this.o = c1098u;
        this.l = c1098u.f13459p;
        this.f13449m = c1098u.isEmpty() ? -1 : 0;
        this.f13450n = -1;
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        return this.f13449m >= 0;
    }

    @Override // java.util.Iterator
    public final Object next() {
        Object c1097t;
        C1098u c1098u = this.o;
        if (c1098u.f13459p != this.l) {
            throw new ConcurrentModificationException();
        }
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        int i7 = this.f13449m;
        this.f13450n = i7;
        switch (this.f13451p) {
            case 0:
                c1097t = this.f13452q.j()[i7];
                break;
            case 1:
                c1097t = new C1097t(this.f13452q, i7);
                break;
            default:
                c1097t = this.f13452q.k()[i7];
                break;
        }
        int i10 = this.f13449m + 1;
        if (i10 >= c1098u.f13460q) {
            i10 = -1;
        }
        this.f13449m = i10;
        return c1097t;
    }

    @Override // java.util.Iterator
    public final void remove() {
        C1098u c1098u = this.o;
        int i7 = c1098u.f13459p;
        int i10 = this.l;
        if (i7 != i10) {
            throw new ConcurrentModificationException();
        }
        int i11 = this.f13450n;
        if (i11 < 0) {
            throw new IllegalStateException("no calls to next() since the last call to remove()");
        }
        this.l = i10 + 32;
        c1098u.remove(c1098u.j()[i11]);
        this.f13449m--;
        this.f13450n = -1;
    }
}
