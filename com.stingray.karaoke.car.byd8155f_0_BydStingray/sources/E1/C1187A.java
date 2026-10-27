package e1;

import T.C0773y;
import h2.AbstractC1347d;

/* JADX INFO: renamed from: e1.A, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1187A {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final int f13759a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final boolean f13760b;
    public final boolean c;
    public final boolean d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final boolean f13761e;

    public C1187A(int i7, boolean z6) {
        this((i7 & 1) != 0 ? false : z6, true, true, 1, true, true);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof C1187A)) {
            return false;
        }
        C1187A c1187a = (C1187A) obj;
        return this.f13759a == c1187a.f13759a && this.f13760b == c1187a.f13760b && this.c == c1187a.c && this.d == c1187a.d && this.f13761e == c1187a.f13761e;
    }

    public final int hashCode() {
        return Boolean.hashCode(false) + AbstractC1347d.d(AbstractC1347d.d(AbstractC1347d.d(AbstractC1347d.d(this.f13759a * 31, 31, this.f13760b), 31, this.c), 31, this.d), 31, this.f13761e);
    }

    public C1187A(boolean z6, boolean z10, boolean z11, int i7, boolean z12, boolean z13) {
        C0773y c0773y = m.f13798a;
        int i10 = !z6 ? 262152 : 262144;
        i10 = i7 == 2 ? i10 | 8192 : i10;
        i10 = z13 ? i10 : i10 | 512;
        boolean z14 = i7 == 1;
        this.f13759a = i10;
        this.f13760b = z14;
        this.c = z10;
        this.d = z11;
        this.f13761e = z12;
    }
}
