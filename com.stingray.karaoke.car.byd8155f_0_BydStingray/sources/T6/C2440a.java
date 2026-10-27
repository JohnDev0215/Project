package t6;

import x.AbstractC2641j;

/* JADX INFO: renamed from: t6.a, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C2440a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final int f22473a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final int f22474b;
    public final int c;

    public C2440a(int i7, int i10, int i11) {
        this.f22473a = i7;
        this.f22474b = i10;
        this.c = i11;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof C2440a)) {
            return false;
        }
        C2440a c2440a = (C2440a) obj;
        return this.f22473a == c2440a.f22473a && this.f22474b == c2440a.f22474b && this.c == c2440a.c;
    }

    public final int hashCode() {
        return Integer.hashCode(this.c) + AbstractC2641j.b(this.f22474b, Integer.hashCode(this.f22473a) * 31, 31);
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("KmlColor(red=");
        sb.append(this.f22473a);
        sb.append(", green=");
        sb.append(this.f22474b);
        sb.append(", blue=");
        return M5.b.h(sb, this.c, ")");
    }
}
