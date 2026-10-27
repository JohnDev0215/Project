package k6;

import android.graphics.Shader;
import h2.AbstractC1347d;
import java.util.List;
import n0.AbstractC1861K;
import n0.AbstractC1864N;

/* JADX INFO: loaded from: classes.dex */
public final class p extends AbstractC1864N {
    public final List c;
    public final List d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final int f18287e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final float f18288f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final float f18289g;

    public p(List list, List list2, float f10, boolean z6) {
        float f11;
        A7.m.f(list, "colors");
        this.c = list;
        this.d = list2;
        this.f18287e = 0;
        if (z6) {
            float f12 = 360;
            f11 = (((90 - f10) % f12) + f12) % f12;
        } else {
            float f13 = 360;
            f11 = ((f10 % f13) + f13) % f13;
        }
        this.f18288f = f11;
        this.f18289g = (float) Math.toRadians(f11);
    }

    @Override // n0.AbstractC1864N
    public final Shader b(long j3) {
        double d = 2;
        float fSqrt = (float) Math.sqrt(((float) Math.pow(m0.f.d(j3), d)) + ((float) Math.pow(m0.f.b(j3), d)));
        float fAcos = (float) Math.acos(m0.f.d(j3) / fSqrt);
        float f10 = this.f18288f;
        float f11 = this.f18289g;
        float fAbs = Math.abs(((float) Math.cos(((f10 <= 90.0f || f10 >= 180.0f) && (f10 <= 270.0f || f10 >= 360.0f)) ? f11 - fAcos : (3.1415927f - f11) - fAcos)) * fSqrt) / 2;
        double d10 = f11;
        float fCos = ((float) Math.cos(d10)) * fAbs;
        float fSin = fAbs * ((float) Math.sin(d10));
        return AbstractC1861K.g(m0.c.h(h6.j.k(j3), h6.e.a(-fCos, fSin)), m0.c.h(h6.j.k(j3), h6.e.a(fCos, -fSin)), this.c, this.d, this.f18287e);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof p)) {
            return false;
        }
        p pVar = (p) obj;
        return A7.m.a(this.c, pVar.c) && A7.m.a(this.d, pVar.d) && this.f18288f == pVar.f18288f && AbstractC1861K.u(this.f18287e, pVar.f18287e);
    }

    public final int hashCode() {
        int iHashCode = this.c.hashCode() * 31;
        List list = this.d;
        return Integer.hashCode(this.f18287e) + AbstractC1347d.b((iHashCode + (list != null ? list.hashCode() : 0)) * 31, 31, this.f18288f);
    }

    public final String toString() {
        return "LinearGradient(colors=" + this.c + ", stops=" + this.d + ", angle=" + this.f18288f + ", tileMode=" + AbstractC1861K.H(this.f18287e) + ")";
    }
}
