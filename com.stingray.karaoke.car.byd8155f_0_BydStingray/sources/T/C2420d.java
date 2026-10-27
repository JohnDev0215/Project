package t;

import java.util.Iterator;

/* JADX INFO: renamed from: t.d, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C2420d extends AbstractC2421e implements Iterator {
    public C2419c l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public boolean f22421m = true;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ C2422f f22422n;

    public C2420d(C2422f c2422f) {
        this.f22422n = c2422f;
    }

    @Override // t.AbstractC2421e
    public final void a(C2419c c2419c) {
        C2419c c2419c2 = this.l;
        if (c2419c == c2419c2) {
            C2419c c2419c3 = c2419c2.o;
            this.l = c2419c3;
            this.f22421m = c2419c3 == null;
        }
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        if (this.f22421m) {
            return this.f22422n.l != null;
        }
        C2419c c2419c = this.l;
        return (c2419c == null || c2419c.f22420n == null) ? false : true;
    }

    @Override // java.util.Iterator
    public final Object next() {
        if (this.f22421m) {
            this.f22421m = false;
            this.l = this.f22422n.l;
        } else {
            C2419c c2419c = this.l;
            this.l = c2419c != null ? c2419c.f22420n : null;
        }
        return this.l;
    }
}
