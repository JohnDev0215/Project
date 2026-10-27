package n0;

import java.util.Arrays;

/* JADX INFO: renamed from: n0.D, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1854D {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final float[] f19374a;

    public static float[] a() {
        return new float[]{1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f};
    }

    public static final long b(long j3, float[] fArr) {
        float fD = m0.c.d(j3);
        float fE = m0.c.e(j3);
        float f10 = 1 / (((fArr[7] * fE) + (fArr[3] * fD)) + fArr[15]);
        if (Float.isInfinite(f10) || Float.isNaN(f10)) {
            f10 = 0.0f;
        }
        return h6.e.a(((fArr[4] * fE) + (fArr[0] * fD) + fArr[12]) * f10, ((fArr[5] * fE) + (fArr[1] * fD) + fArr[13]) * f10);
    }

    public static final void c(float[] fArr, m0.b bVar) {
        long jB = b(h6.e.a(bVar.f18878a, bVar.f18879b), fArr);
        long jB2 = b(h6.e.a(bVar.f18878a, bVar.d), fArr);
        long jB3 = b(h6.e.a(bVar.c, bVar.f18879b), fArr);
        long jB4 = b(h6.e.a(bVar.c, bVar.d), fArr);
        bVar.f18878a = Math.min(Math.min(m0.c.d(jB), m0.c.d(jB2)), Math.min(m0.c.d(jB3), m0.c.d(jB4)));
        bVar.f18879b = Math.min(Math.min(m0.c.e(jB), m0.c.e(jB2)), Math.min(m0.c.e(jB3), m0.c.e(jB4)));
        bVar.c = Math.max(Math.max(m0.c.d(jB), m0.c.d(jB2)), Math.max(m0.c.d(jB3), m0.c.d(jB4)));
        bVar.d = Math.max(Math.max(m0.c.e(jB), m0.c.e(jB2)), Math.max(m0.c.e(jB3), m0.c.e(jB4)));
    }

    public static final void d(float[] fArr) {
        int i7 = 0;
        while (i7 < 4) {
            int i10 = 0;
            while (i10 < 4) {
                fArr[(i10 * 4) + i7] = i7 == i10 ? 1.0f : 0.0f;
                i10++;
            }
            i7++;
        }
    }

    public static final void e(float[] fArr, float f10) {
        double d = (((double) f10) * 3.141592653589793d) / 180.0d;
        float fCos = (float) Math.cos(d);
        float fSin = (float) Math.sin(d);
        float f11 = fArr[0];
        float f12 = fArr[4];
        float f13 = (fSin * f12) + (fCos * f11);
        float f14 = -fSin;
        float f15 = (f12 * fCos) + (f11 * f14);
        float f16 = fArr[1];
        float f17 = fArr[5];
        float f18 = (fSin * f17) + (fCos * f16);
        float f19 = (f17 * fCos) + (f16 * f14);
        float f20 = fArr[2];
        float f21 = fArr[6];
        float f22 = (fSin * f21) + (fCos * f20);
        float f23 = (f21 * fCos) + (f20 * f14);
        float f24 = fArr[3];
        float f25 = fArr[7];
        float f26 = (fSin * f25) + (fCos * f24);
        fArr[0] = f13;
        fArr[1] = f18;
        fArr[2] = f22;
        fArr[3] = f26;
        fArr[4] = f15;
        fArr[5] = f19;
        fArr[6] = f23;
        fArr[7] = (fCos * f25) + (f14 * f24);
    }

    public static final void f(float f10, float f11, float f12, float[] fArr) {
        fArr[0] = fArr[0] * f10;
        fArr[1] = fArr[1] * f10;
        fArr[2] = fArr[2] * f10;
        fArr[3] = fArr[3] * f10;
        fArr[4] = fArr[4] * f11;
        fArr[5] = fArr[5] * f11;
        fArr[6] = fArr[6] * f11;
        fArr[7] = fArr[7] * f11;
        fArr[8] = fArr[8] * f12;
        fArr[9] = fArr[9] * f12;
        fArr[10] = fArr[10] * f12;
        fArr[11] = fArr[11] * f12;
    }

    public static final void g(float[] fArr, float[] fArr2) {
        float fL = AbstractC1861K.l(0, 0, fArr, fArr2);
        float fL2 = AbstractC1861K.l(0, 1, fArr, fArr2);
        float fL3 = AbstractC1861K.l(0, 2, fArr, fArr2);
        float fL4 = AbstractC1861K.l(0, 3, fArr, fArr2);
        float fL5 = AbstractC1861K.l(1, 0, fArr, fArr2);
        float fL6 = AbstractC1861K.l(1, 1, fArr, fArr2);
        float fL7 = AbstractC1861K.l(1, 2, fArr, fArr2);
        float fL8 = AbstractC1861K.l(1, 3, fArr, fArr2);
        float fL9 = AbstractC1861K.l(2, 0, fArr, fArr2);
        float fL10 = AbstractC1861K.l(2, 1, fArr, fArr2);
        float fL11 = AbstractC1861K.l(2, 2, fArr, fArr2);
        float fL12 = AbstractC1861K.l(2, 3, fArr, fArr2);
        float fL13 = AbstractC1861K.l(3, 0, fArr, fArr2);
        float fL14 = AbstractC1861K.l(3, 1, fArr, fArr2);
        float fL15 = AbstractC1861K.l(3, 2, fArr, fArr2);
        float fL16 = AbstractC1861K.l(3, 3, fArr, fArr2);
        fArr[0] = fL;
        fArr[1] = fL2;
        fArr[2] = fL3;
        fArr[3] = fL4;
        fArr[4] = fL5;
        fArr[5] = fL6;
        fArr[6] = fL7;
        fArr[7] = fL8;
        fArr[8] = fL9;
        fArr[9] = fL10;
        fArr[10] = fL11;
        fArr[11] = fL12;
        fArr[12] = fL13;
        fArr[13] = fL14;
        fArr[14] = fL15;
        fArr[15] = fL16;
    }

    public static final void h(float f10, float f11, float f12, float[] fArr) {
        float f13 = (fArr[8] * f12) + (fArr[4] * f11) + (fArr[0] * f10) + fArr[12];
        float f14 = (fArr[9] * f12) + (fArr[5] * f11) + (fArr[1] * f10) + fArr[13];
        float f15 = (fArr[10] * f12) + (fArr[6] * f11) + (fArr[2] * f10) + fArr[14];
        float f16 = (fArr[11] * f12) + (fArr[7] * f11) + (fArr[3] * f10) + fArr[15];
        fArr[12] = f13;
        fArr[13] = f14;
        fArr[14] = f15;
        fArr[15] = f16;
    }

    public final boolean equals(Object obj) {
        if (obj instanceof C1854D) {
            return A7.m.a(this.f19374a, ((C1854D) obj).f19374a);
        }
        return false;
    }

    public final int hashCode() {
        return Arrays.hashCode(this.f19374a);
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("\n            |");
        float[] fArr = this.f19374a;
        sb.append(fArr[0]);
        sb.append(' ');
        sb.append(fArr[1]);
        sb.append(' ');
        sb.append(fArr[2]);
        sb.append(' ');
        sb.append(fArr[3]);
        sb.append("|\n            |");
        sb.append(fArr[4]);
        sb.append(' ');
        sb.append(fArr[5]);
        sb.append(' ');
        sb.append(fArr[6]);
        sb.append(' ');
        sb.append(fArr[7]);
        sb.append("|\n            |");
        sb.append(fArr[8]);
        sb.append(' ');
        sb.append(fArr[9]);
        sb.append(' ');
        sb.append(fArr[10]);
        sb.append(' ');
        sb.append(fArr[11]);
        sb.append("|\n            |");
        sb.append(fArr[12]);
        sb.append(' ');
        sb.append(fArr[13]);
        sb.append(' ');
        sb.append(fArr[14]);
        sb.append(' ');
        sb.append(fArr[15]);
        sb.append("|\n        ");
        return Q8.i.q0(sb.toString());
    }
}
