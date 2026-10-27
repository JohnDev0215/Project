package O;

import x.AbstractC2641j;

/* JADX INFO: renamed from: O.l, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0481l {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Y0.h f4970a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final int f4971b;
    public final long c;

    public C0481l(Y0.h hVar, int i7, long j3) {
        this.f4970a = hVar;
        this.f4971b = i7;
        this.c = j3;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof C0481l)) {
            return false;
        }
        C0481l c0481l = (C0481l) obj;
        return this.f4970a == c0481l.f4970a && this.f4971b == c0481l.f4971b && this.c == c0481l.c;
    }

    public final int hashCode() {
        return Long.hashCode(this.c) + AbstractC2641j.b(this.f4971b, this.f4970a.hashCode() * 31, 31);
    }

    public final String toString() {
        return "AnchorInfo(direction=" + this.f4970a + ", offset=" + this.f4971b + ", selectableId=" + this.c + ')';
    }
}
