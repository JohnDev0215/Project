package a1;

import a.AbstractC0904a;
import b1.AbstractC0989b;
import b1.InterfaceC0988a;

/* JADX INFO: loaded from: classes.dex */
public interface b {
    default float K(long j3) {
        if (!n.a(m.b(j3), 4294967296L)) {
            throw new IllegalStateException("Only Sp can convert to Px");
        }
        float[] fArr = AbstractC0989b.f12198a;
        if (p() < 1.03f) {
            return p() * m.c(j3);
        }
        InterfaceC0988a interfaceC0988aA = AbstractC0989b.a(p());
        if (interfaceC0988aA != null) {
            return interfaceC0988aA.b(m.c(j3));
        }
        return p() * m.c(j3);
    }

    default int O(float f10) {
        float fY = y(f10);
        if (Float.isInfinite(fY)) {
            return Integer.MAX_VALUE;
        }
        return Math.round(fY);
    }

    default long Y(long j3) {
        if (j3 != 9205357640488583168L) {
            return h6.j.f(y(g.b(j3)), y(g.a(j3)));
        }
        return 9205357640488583168L;
    }

    default float b0(long j3) {
        if (n.a(m.b(j3), 4294967296L)) {
            return y(K(j3));
        }
        throw new IllegalStateException("Only Sp can convert to Px");
    }

    float getDensity();

    default long j0(float f10) {
        return w(r0(f10));
    }

    float p();

    default float p0(int i7) {
        return i7 / getDensity();
    }

    default float r0(float f10) {
        return f10 / getDensity();
    }

    default long w(float f10) {
        float[] fArr = AbstractC0989b.f12198a;
        if (!(p() >= 1.03f)) {
            return AbstractC0904a.O(f10 / p(), 4294967296L);
        }
        InterfaceC0988a interfaceC0988aA = AbstractC0989b.a(p());
        return AbstractC0904a.O(interfaceC0988aA != null ? interfaceC0988aA.a(f10) : f10 / p(), 4294967296L);
    }

    default long x(long j3) {
        if (j3 != 9205357640488583168L) {
            return l9.l.k(r0(m0.f.d(j3)), r0(m0.f.b(j3)));
        }
        return 9205357640488583168L;
    }

    default float y(float f10) {
        return getDensity() * f10;
    }
}
