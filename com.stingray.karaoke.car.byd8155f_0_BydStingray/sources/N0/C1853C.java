package n0;

import android.graphics.Shader;
import h2.AbstractC1347d;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: renamed from: n0.C, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1853C extends AbstractC1864N {
    public final List c;
    public final ArrayList d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final long f19371e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final long f19372f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final int f19373g;

    public C1853C(List list, ArrayList arrayList, long j3, long j10, int i7) {
        this.c = list;
        this.d = arrayList;
        this.f19371e = j3;
        this.f19372f = j10;
        this.f19373g = i7;
    }

    @Override // n0.AbstractC1864N
    public final Shader b(long j3) {
        long j10 = this.f19371e;
        float fD = m0.c.d(j10) == Float.POSITIVE_INFINITY ? m0.f.d(j3) : m0.c.d(j10);
        float fB = m0.c.e(j10) == Float.POSITIVE_INFINITY ? m0.f.b(j3) : m0.c.e(j10);
        long j11 = this.f19372f;
        return AbstractC1861K.g(h6.e.a(fD, fB), h6.e.a(m0.c.d(j11) == Float.POSITIVE_INFINITY ? m0.f.d(j3) : m0.c.d(j11), m0.c.e(j11) == Float.POSITIVE_INFINITY ? m0.f.b(j3) : m0.c.e(j11)), this.c, this.d, this.f19373g);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof C1853C)) {
            return false;
        }
        C1853C c1853c = (C1853C) obj;
        return this.c.equals(c1853c.c) && A7.m.a(this.d, c1853c.d) && m0.c.b(this.f19371e, c1853c.f19371e) && m0.c.b(this.f19372f, c1853c.f19372f) && AbstractC1861K.u(this.f19373g, c1853c.f19373g);
    }

    public final int hashCode() {
        int iHashCode = this.c.hashCode() * 31;
        ArrayList arrayList = this.d;
        return Integer.hashCode(this.f19373g) + AbstractC1347d.c(AbstractC1347d.c((iHashCode + (arrayList != null ? arrayList.hashCode() : 0)) * 31, 31, this.f19371e), 31, this.f19372f);
    }

    public final String toString() {
        String str;
        long j3 = this.f19371e;
        String str2 = "";
        if (h6.e.l(j3)) {
            str = "start=" + ((Object) m0.c.j(j3)) + ", ";
        } else {
            str = "";
        }
        long j10 = this.f19372f;
        if (h6.e.l(j10)) {
            str2 = "end=" + ((Object) m0.c.j(j10)) + ", ";
        }
        return "LinearGradient(colors=" + this.c + ", stops=" + this.d + ", " + str + str2 + "tileMode=" + ((Object) AbstractC1861K.H(this.f19373g)) + ')';
    }
}
