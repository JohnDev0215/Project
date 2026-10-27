package x;

import h2.AbstractC1347d;
import n0.AbstractC1861K;

/* JADX INFO: renamed from: x.w, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C2656w implements InterfaceC2612A {
    public final float l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final float f23649m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final float f23650n;
    public final float o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final float f23651p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final float f23652q;

    public C2656w(float f10, float f11, float f12, float f13) {
        int iJ;
        this.l = f10;
        this.f23649m = f11;
        this.f23650n = f12;
        this.o = f13;
        if (Float.isNaN(f10) || Float.isNaN(f11) || Float.isNaN(f12) || Float.isNaN(f13)) {
            throw new IllegalArgumentException("Parameters to CubicBezierEasing cannot be NaN. Actual parameters are: " + f10 + ", " + f11 + ", " + f12 + ", " + f13 + '.');
        }
        float[] fArr = new float[5];
        float f14 = (f11 - 0.0f) * 3.0f;
        float f15 = (f13 - f11) * 3.0f;
        float f16 = (1.0f - f13) * 3.0f;
        double d = f14;
        double d10 = f15;
        double d11 = f16;
        double d12 = d10 * 2.0d;
        double d13 = (d - d12) + d11;
        if (d13 == 0.0d) {
            iJ = d10 == d11 ? 0 : AbstractC1861K.J((float) ((d12 - d11) / (d12 - (d11 * 2.0d))), fArr, 0);
        } else {
            double d14 = -Math.sqrt((d10 * d10) - (d11 * d));
            double d15 = (-d) + d10;
            int iJ2 = AbstractC1861K.J((float) ((-(d14 + d15)) / d13), fArr, 0);
            iJ = AbstractC1861K.J((float) ((d14 - d15) / d13), fArr, iJ2) + iJ2;
            if (iJ > 1) {
                float f17 = fArr[0];
                float f18 = fArr[1];
                if (f17 > f18) {
                    fArr[0] = f18;
                    fArr[1] = f17;
                } else if (f17 == f18) {
                    iJ--;
                }
            }
        }
        float f19 = (f15 - f14) * 2.0f;
        int iJ3 = AbstractC1861K.J((-f19) / (((f16 - f15) * 2.0f) - f19), fArr, iJ) + iJ;
        float fMin = Math.min(0.0f, 1.0f);
        float fMax = Math.max(0.0f, 1.0f);
        for (int i7 = 0; i7 < iJ3; i7++) {
            float f20 = fArr[i7];
            float f21 = (((((((((f11 - f13) * 3.0f) + 1.0f) - 0.0f) * f20) + (((f13 - (f11 * 2.0f)) + 0.0f) * 3.0f)) * f20) + f14) * f20) + 0.0f;
            fMin = Math.min(fMin, f21);
            fMax = Math.max(fMax, f21);
        }
        long jFloatToRawIntBits = (((long) Float.floatToRawIntBits(fMin)) << 32) | (((long) Float.floatToRawIntBits(fMax)) & 4294967295L);
        this.f23651p = Float.intBitsToFloat((int) (jFloatToRawIntBits >> 32));
        this.f23652q = Float.intBitsToFloat((int) (jFloatToRawIntBits & 4294967295L));
    }

    /* JADX WARN: Removed duplicated region for block: B:101:0x01c6  */
    /* JADX WARN: Removed duplicated region for block: B:109:0x01db  */
    /* JADX WARN: Removed duplicated region for block: B:110:0x01de  */
    /* JADX WARN: Removed duplicated region for block: B:124:0x0215 A[PHI: r3
  0x0215: PHI (r3v5 float) = (r3v4 float), (r3v3 float) binds: [B:128:0x0220, B:123:0x0213] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:17:0x0073  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0075  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x0082  */
    /* JADX WARN: Removed duplicated region for block: B:30:0x00a5  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x00be  */
    /* JADX WARN: Removed duplicated region for block: B:62:0x013f  */
    /* JADX WARN: Removed duplicated region for block: B:71:0x0156  */
    /* JADX WARN: Removed duplicated region for block: B:76:0x0171  */
    /* JADX WARN: Removed duplicated region for block: B:85:0x0188  */
    @Override // x.InterfaceC2612A
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final float a(float r26) {
        /*
            Method dump skipped, instruction units count: 642
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: x.C2656w.a(float):float");
    }

    public final boolean equals(Object obj) {
        if (obj instanceof C2656w) {
            C2656w c2656w = (C2656w) obj;
            if (this.l == c2656w.l && this.f23649m == c2656w.f23649m && this.f23650n == c2656w.f23650n && this.o == c2656w.o) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        return Float.hashCode(this.o) + AbstractC1347d.b(AbstractC1347d.b(Float.hashCode(this.l) * 31, 31, this.f23649m), 31, this.f23650n);
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("CubicBezierEasing(a=");
        sb.append(this.l);
        sb.append(", b=");
        sb.append(this.f23649m);
        sb.append(", c=");
        sb.append(this.f23650n);
        sb.append(", d=");
        return AbstractC1347d.h(sb, this.o, ')');
    }
}
