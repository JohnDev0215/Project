package n0;

import o0.AbstractC2007c;
import o0.AbstractC2012h;
import o0.AbstractC2014j;
import o0.C2008d;
import o0.C2011g;
import u.C2476p;

/* JADX INFO: renamed from: n0.t, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1891t {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final long f19445b = AbstractC1861K.d(4278190080L);
    public static final long c;
    public static final long d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final long f19446e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final long f19447f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static final long f19448g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public static final long f19449h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public static final long f19450i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public static final long f19451j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public static final long f19452k;
    public static final /* synthetic */ int l = 0;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final long f19453a;

    static {
        AbstractC1861K.d(4282664004L);
        c = AbstractC1861K.d(4287137928L);
        d = AbstractC1861K.d(4291611852L);
        f19446e = AbstractC1861K.d(4294967295L);
        f19447f = AbstractC1861K.d(4294901760L);
        f19448g = AbstractC1861K.d(4278255360L);
        f19449h = AbstractC1861K.d(4278190335L);
        AbstractC1861K.d(4294967040L);
        AbstractC1861K.d(4278255615L);
        f19450i = AbstractC1861K.d(4294902015L);
        f19451j = AbstractC1861K.c(0);
        f19452k = AbstractC1861K.b(0.0f, 0.0f, 0.0f, 0.0f, C2008d.f20194s);
    }

    public /* synthetic */ C1891t(long j3) {
        this.f19453a = j3;
    }

    public static final long a(long j3, AbstractC2007c abstractC2007c) {
        C2011g c2011gE;
        AbstractC2007c abstractC2007cF = f(j3);
        int i7 = abstractC2007cF.c;
        int i10 = abstractC2007c.c;
        if ((i7 | i10) < 0) {
            c2011gE = AbstractC2014j.e(abstractC2007cF, abstractC2007c, 0);
        } else {
            C2476p c2476p = AbstractC2012h.f20202a;
            int i11 = i7 | (i10 << 6);
            Object objF = c2476p.f(i11);
            if (objF == null) {
                objF = AbstractC2014j.e(abstractC2007cF, abstractC2007c, 0);
                c2476p.i(i11, objF);
            }
            c2011gE = (C2011g) objF;
        }
        return c2011gE.a(j3);
    }

    public static long b(float f10, long j3) {
        return AbstractC1861K.b(h(j3), g(j3), e(j3), f10, f(j3));
    }

    public static final boolean c(long j3, long j10) {
        return j3 == j10;
    }

    public static final float d(long j3) {
        float fA;
        float f10;
        if ((63 & j3) == 0) {
            fA = (float) n7.z.A((j3 >>> 56) & 255);
            f10 = 255.0f;
        } else {
            fA = (float) n7.z.A((j3 >>> 6) & 1023);
            f10 = 1023.0f;
        }
        return fA / f10;
    }

    public static final float e(long j3) {
        int i7;
        int i10;
        int i11;
        if ((63 & j3) == 0) {
            return ((float) n7.z.A((j3 >>> 32) & 255)) / 255.0f;
        }
        short s6 = (short) ((j3 >>> 16) & 65535);
        int i12 = Short.MIN_VALUE & s6;
        int i13 = ((65535 & s6) >>> 10) & 31;
        int i14 = s6 & 1023;
        if (i13 != 0) {
            int i15 = i14 << 13;
            if (i13 == 31) {
                i7 = 255;
                if (i15 != 0) {
                    i15 |= 4194304;
                }
            } else {
                i7 = i13 + 112;
            }
            int i16 = i7;
            i10 = i15;
            i11 = i16;
        } else {
            if (i14 != 0) {
                float fIntBitsToFloat = Float.intBitsToFloat(i14 + 1056964608) - AbstractC1896y.f19457a;
                return i12 == 0 ? fIntBitsToFloat : -fIntBitsToFloat;
            }
            i11 = 0;
            i10 = 0;
        }
        return Float.intBitsToFloat((i11 << 23) | (i12 << 16) | i10);
    }

    public static final AbstractC2007c f(long j3) {
        float[] fArr = C2008d.f20180a;
        return C2008d.f20196u[(int) (j3 & 63)];
    }

    public static final float g(long j3) {
        int i7;
        int i10;
        int i11;
        if ((63 & j3) == 0) {
            return ((float) n7.z.A((j3 >>> 40) & 255)) / 255.0f;
        }
        short s6 = (short) ((j3 >>> 32) & 65535);
        int i12 = Short.MIN_VALUE & s6;
        int i13 = ((65535 & s6) >>> 10) & 31;
        int i14 = s6 & 1023;
        if (i13 != 0) {
            int i15 = i14 << 13;
            if (i13 == 31) {
                i7 = 255;
                if (i15 != 0) {
                    i15 |= 4194304;
                }
            } else {
                i7 = i13 + 112;
            }
            int i16 = i7;
            i10 = i15;
            i11 = i16;
        } else {
            if (i14 != 0) {
                float fIntBitsToFloat = Float.intBitsToFloat(i14 + 1056964608) - AbstractC1896y.f19457a;
                return i12 == 0 ? fIntBitsToFloat : -fIntBitsToFloat;
            }
            i11 = 0;
            i10 = 0;
        }
        return Float.intBitsToFloat((i11 << 23) | (i12 << 16) | i10);
    }

    public static final float h(long j3) {
        int i7;
        int i10;
        int i11;
        if ((63 & j3) == 0) {
            return ((float) n7.z.A((j3 >>> 48) & 255)) / 255.0f;
        }
        short s6 = (short) ((j3 >>> 48) & 65535);
        int i12 = Short.MIN_VALUE & s6;
        int i13 = ((65535 & s6) >>> 10) & 31;
        int i14 = s6 & 1023;
        if (i13 != 0) {
            int i15 = i14 << 13;
            if (i13 == 31) {
                i7 = 255;
                if (i15 != 0) {
                    i15 |= 4194304;
                }
            } else {
                i7 = i13 + 112;
            }
            int i16 = i7;
            i10 = i15;
            i11 = i16;
        } else {
            if (i14 != 0) {
                float fIntBitsToFloat = Float.intBitsToFloat(i14 + 1056964608) - AbstractC1896y.f19457a;
                return i12 == 0 ? fIntBitsToFloat : -fIntBitsToFloat;
            }
            i11 = 0;
            i10 = 0;
        }
        return Float.intBitsToFloat((i11 << 23) | (i12 << 16) | i10);
    }

    public static String i(long j3) {
        StringBuilder sb = new StringBuilder("Color(");
        sb.append(h(j3));
        sb.append(", ");
        sb.append(g(j3));
        sb.append(", ");
        sb.append(e(j3));
        sb.append(", ");
        sb.append(d(j3));
        sb.append(", ");
        return android.hardware.bydauto.ac.tsimpl.a.k(sb, f(j3).f20178a, ')');
    }

    public final boolean equals(Object obj) {
        if (obj instanceof C1891t) {
            return this.f19453a == ((C1891t) obj).f19453a;
        }
        return false;
    }

    public final int hashCode() {
        return Long.hashCode(this.f19453a);
    }

    public final String toString() {
        return i(this.f19453a);
    }
}
