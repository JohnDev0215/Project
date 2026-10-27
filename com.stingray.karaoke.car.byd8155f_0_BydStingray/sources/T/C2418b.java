package t;

import java.util.Iterator;

/* JADX INFO: renamed from: t.b, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C2418b extends AbstractC2421e implements Iterator {
    public C2419c l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public C2419c f22417m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ int f22418n;

    public C2418b(C2419c c2419c, C2419c c2419c2, int i7) {
        this.f22418n = i7;
        this.l = c2419c2;
        this.f22417m = c2419c;
    }

    @Override // t.AbstractC2421e
    public final void a(C2419c c2419c) {
        C2419c c2419c2;
        C2419c c2419cB = null;
        if (this.l == c2419c && c2419c == this.f22417m) {
            this.f22417m = null;
            this.l = null;
        }
        C2419c c2419c3 = this.l;
        if (c2419c3 == c2419c) {
            switch (this.f22418n) {
                case 0:
                    c2419c2 = c2419c3.o;
                    break;
                default:
                    c2419c2 = c2419c3.f22420n;
                    break;
            }
            this.l = c2419c2;
        }
        C2419c c2419c4 = this.f22417m;
        if (c2419c4 == c2419c) {
            C2419c c2419c5 = this.l;
            if (c2419c4 != c2419c5 && c2419c5 != null) {
                c2419cB = b(c2419c4);
            }
            this.f22417m = c2419cB;
        }
    }

    public final C2419c b(C2419c c2419c) {
        switch (this.f22418n) {
            case 0:
                return c2419c.f22420n;
            default:
                return c2419c.o;
        }
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        return this.f22417m != null;
    }

    @Override // java.util.Iterator
    public final Object next() {
        C2419c c2419c = this.f22417m;
        C2419c c2419c2 = this.l;
        this.f22417m = (c2419c == c2419c2 || c2419c2 == null) ? null : b(c2419c);
        return c2419c;
    }
}
