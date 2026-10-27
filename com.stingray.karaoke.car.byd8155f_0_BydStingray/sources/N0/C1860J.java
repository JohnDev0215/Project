package n0;

import android.graphics.RadialGradient;
import android.graphics.Shader;
import h2.AbstractC1347d;
import java.util.List;

/* JADX INFO: renamed from: n0.J, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1860J extends AbstractC1864N {
    public final List c;
    public final long d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final float f19379e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final int f19380f;

    public C1860J(List list, long j3, float f10, int i7) {
        this.c = list;
        this.d = j3;
        this.f19379e = f10;
        this.f19380f = i7;
    }

    @Override // n0.AbstractC1864N
    public final Shader b(long j3) {
        float fD;
        float fB;
        long j10 = this.d;
        if (h6.e.n(j10)) {
            long jK = h6.j.k(j3);
            fD = m0.c.d(jK);
            fB = m0.c.e(jK);
        } else {
            fD = m0.c.d(j10) == Float.POSITIVE_INFINITY ? m0.f.d(j3) : m0.c.d(j10);
            fB = m0.c.e(j10) == Float.POSITIVE_INFINITY ? m0.f.b(j3) : m0.c.e(j10);
        }
        long jA = h6.e.a(fD, fB);
        float fC = this.f19379e;
        if (fC == Float.POSITIVE_INFINITY) {
            fC = m0.f.c(j3) / 2;
        }
        float f10 = fC;
        List list = this.c;
        AbstractC1861K.I(list, null);
        float fD2 = m0.c.d(jA);
        float fE = m0.c.e(jA);
        int size = list.size();
        int[] iArr = new int[size];
        for (int i7 = 0; i7 < size; i7++) {
            iArr[i7] = AbstractC1861K.E(((C1891t) list.get(i7)).f19453a);
        }
        return new RadialGradient(fD2, fE, f10, iArr, (float[]) null, AbstractC1861K.D(this.f19380f));
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof C1860J)) {
            return false;
        }
        C1860J c1860j = (C1860J) obj;
        return this.c.equals(c1860j.c) && A7.m.a(null, null) && m0.c.b(this.d, c1860j.d) && this.f19379e == c1860j.f19379e && AbstractC1861K.u(this.f19380f, c1860j.f19380f);
    }

    public final int hashCode() {
        return Integer.hashCode(this.f19380f) + AbstractC1347d.b(AbstractC1347d.c(this.c.hashCode() * 961, 31, this.d), 31, this.f19379e);
    }

    public final String toString() {
        String str;
        long j3 = this.d;
        String str2 = "";
        if (h6.e.m(j3)) {
            str = "center=" + ((Object) m0.c.j(j3)) + ", ";
        } else {
            str = "";
        }
        float f10 = this.f19379e;
        if (!Float.isInfinite(f10) && !Float.isNaN(f10)) {
            str2 = "radius=" + f10 + ", ";
        }
        return "RadialGradient(colors=" + this.c + ", stops=null, " + str + str2 + "tileMode=" + ((Object) AbstractC1861K.H(this.f19380f)) + ')';
    }
}
