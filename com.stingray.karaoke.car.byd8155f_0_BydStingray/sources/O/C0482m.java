package O;

import h2.AbstractC1347d;

/* JADX INFO: renamed from: O.m, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0482m {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final C0481l f4972a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final C0481l f4973b;
    public final boolean c;

    public C0482m(C0481l c0481l, C0481l c0481l2, boolean z6) {
        this.f4972a = c0481l;
        this.f4973b = c0481l2;
        this.c = z6;
    }

    public static C0482m a(C0482m c0482m, C0481l c0481l, C0481l c0481l2, boolean z6, int i7) {
        if ((i7 & 1) != 0) {
            c0481l = c0482m.f4972a;
        }
        if ((i7 & 2) != 0) {
            c0481l2 = c0482m.f4973b;
        }
        c0482m.getClass();
        return new C0482m(c0481l, c0481l2, z6);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof C0482m)) {
            return false;
        }
        C0482m c0482m = (C0482m) obj;
        return A7.m.a(this.f4972a, c0482m.f4972a) && A7.m.a(this.f4973b, c0482m.f4973b) && this.c == c0482m.c;
    }

    public final int hashCode() {
        return Boolean.hashCode(this.c) + ((this.f4973b.hashCode() + (this.f4972a.hashCode() * 31)) * 31);
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("Selection(start=");
        sb.append(this.f4972a);
        sb.append(", end=");
        sb.append(this.f4973b);
        sb.append(", handlesCrossed=");
        return AbstractC1347d.i(sb, this.c, ')');
    }
}
