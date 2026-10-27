package e8;

import A7.k;
import A7.m;
import F8.A;
import java.util.Set;
import x.AbstractC2641j;

/* JADX INFO: renamed from: e8.a, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1236a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final int f14483a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final int f14484b;
    public final boolean c;
    public final boolean d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final Set f14485e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final A f14486f;

    public C1236a(int i7, int i10, boolean z6, boolean z10, Set set, A a10) {
        k.p(i7, "howThisTypeIsUsed");
        k.p(i10, "flexibility");
        this.f14483a = i7;
        this.f14484b = i10;
        this.c = z6;
        this.d = z10;
        this.f14485e = set;
        this.f14486f = a10;
    }

    public static C1236a a(C1236a c1236a, int i7, boolean z6, Set set, A a10, int i10) {
        int i11 = c1236a.f14483a;
        if ((i10 & 2) != 0) {
            i7 = c1236a.f14484b;
        }
        int i12 = i7;
        if ((i10 & 4) != 0) {
            z6 = c1236a.c;
        }
        boolean z10 = z6;
        boolean z11 = c1236a.d;
        if ((i10 & 16) != 0) {
            set = c1236a.f14485e;
        }
        Set set2 = set;
        if ((i10 & 32) != 0) {
            a10 = c1236a.f14486f;
        }
        c1236a.getClass();
        k.p(i11, "howThisTypeIsUsed");
        k.p(i12, "flexibility");
        return new C1236a(i11, i12, z10, z11, set2, a10);
    }

    public final C1236a b(int i7) {
        k.p(i7, "flexibility");
        return a(this, i7, false, null, null, 61);
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof C1236a)) {
            return false;
        }
        C1236a c1236a = (C1236a) obj;
        if (m.a(c1236a.f14486f, this.f14486f)) {
            return c1236a.f14483a == this.f14483a && c1236a.f14484b == this.f14484b && c1236a.c == this.c && c1236a.d == this.d;
        }
        return false;
    }

    public final int hashCode() {
        A a10 = this.f14486f;
        int iHashCode = a10 != null ? a10.hashCode() : 0;
        int iC = AbstractC2641j.c(this.f14483a) + (iHashCode * 31) + iHashCode;
        int iC2 = AbstractC2641j.c(this.f14484b) + (iC * 31) + iC;
        int i7 = (iC2 * 31) + (this.c ? 1 : 0) + iC2;
        return (i7 * 31) + (this.d ? 1 : 0) + i7;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("JavaTypeAttributes(howThisTypeIsUsed=");
        int i7 = this.f14483a;
        sb.append(i7 != 1 ? i7 != 2 ? "null" : "COMMON" : "SUPERTYPE");
        sb.append(", flexibility=");
        int i10 = this.f14484b;
        sb.append(i10 != 1 ? i10 != 2 ? i10 != 3 ? "null" : "FLEXIBLE_LOWER_BOUND" : "FLEXIBLE_UPPER_BOUND" : "INFLEXIBLE");
        sb.append(", isRaw=");
        sb.append(this.c);
        sb.append(", isForAnnotationParameter=");
        sb.append(this.d);
        sb.append(", visitedTypeParameters=");
        sb.append(this.f14485e);
        sb.append(", defaultType=");
        sb.append(this.f14486f);
        sb.append(')');
        return sb.toString();
    }

    public /* synthetic */ C1236a(int i7, boolean z6, boolean z10, Set set, int i10) {
        this(i7, 1, (i10 & 4) != 0 ? false : z6, (i10 & 8) != 0 ? false : z10, (i10 & 16) != 0 ? null : set, null);
    }
}
