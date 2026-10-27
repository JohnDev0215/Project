package o0;

/* JADX INFO: renamed from: o0.j, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC2014j {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final C2023s f20203a = new C2023s(0.31006f, 0.31616f);

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final C2023s f20204b = new C2023s(0.34567f, 0.3585f);
    public static final C2023s c = new C2023s(0.32168f, 0.33767f);
    public static final C2023s d = new C2023s(0.31271f, 0.32902f);

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final float[] f20205e = {0.964212f, 1.0f, 0.825188f};

    public static AbstractC2007c a(AbstractC2007c abstractC2007c) {
        C2023s c2023s = f20204b;
        C2005a c2005a = C2005a.f20173b;
        if (!AbstractC2006b.a(abstractC2007c.f20179b, AbstractC2006b.f20175a)) {
            return abstractC2007c;
        }
        C2021q c2021q = (C2021q) abstractC2007c;
        C2023s c2023s2 = c2021q.d;
        if (d(c2023s2, c2023s)) {
            return abstractC2007c;
        }
        return new C2021q(c2021q.f20178a, c2021q.f20218h, c2023s, h(c(c2005a.f20174a, c2023s2.a(), c2023s.a()), c2021q.f20219i), c2021q.f20221k, c2021q.f20223n, c2021q.f20215e, c2021q.f20216f, c2021q.f20217g, -1);
    }

    public static float b(float[] fArr) {
        float f10 = fArr[0];
        float f11 = fArr[1];
        float f12 = fArr[2];
        float f13 = fArr[3];
        float f14 = fArr[4];
        float f15 = fArr[5];
        float f16 = (((((f12 * f15) + ((f11 * f14) + (f10 * f13))) - (f13 * f14)) - (f11 * f12)) - (f10 * f15)) * 0.5f;
        return f16 < 0.0f ? -f16 : f16;
    }

    public static final float[] c(float[] fArr, float[] fArr2, float[] fArr3) {
        j(fArr, fArr2);
        j(fArr, fArr3);
        return h(g(fArr), i(new float[]{fArr3[0] / fArr2[0], fArr3[1] / fArr2[1], fArr3[2] / fArr2[2]}, fArr));
    }

    public static final boolean d(C2023s c2023s, C2023s c2023s2) {
        if (c2023s == c2023s2) {
            return true;
        }
        return Math.abs(c2023s.f20231a - c2023s2.f20231a) < 0.001f && Math.abs(c2023s.f20232b - c2023s2.f20232b) < 0.001f;
    }

    public static final C2011g e(AbstractC2007c abstractC2007c, AbstractC2007c abstractC2007c2, int i7) {
        if (abstractC2007c == abstractC2007c2) {
            return new C2009e(abstractC2007c, abstractC2007c, 1);
        }
        long j3 = AbstractC2006b.f20175a;
        return (AbstractC2006b.a(abstractC2007c.f20179b, j3) && AbstractC2006b.a(abstractC2007c2.f20179b, j3)) ? new C2010f((C2021q) abstractC2007c, (C2021q) abstractC2007c2, i7) : new C2011g(abstractC2007c, abstractC2007c2, i7);
    }

    public static float f(float f10, float f11, float f12, float f13) {
        return (f10 * f13) - (f11 * f12);
    }

    public static final float[] g(float[] fArr) {
        float f10 = fArr[0];
        float f11 = fArr[3];
        float f12 = fArr[6];
        float f13 = fArr[1];
        float f14 = fArr[4];
        float f15 = fArr[7];
        float f16 = fArr[2];
        float f17 = fArr[5];
        float f18 = fArr[8];
        float f19 = (f14 * f18) - (f15 * f17);
        float f20 = (f15 * f16) - (f13 * f18);
        float f21 = (f13 * f17) - (f14 * f16);
        float f22 = (f12 * f21) + (f11 * f20) + (f10 * f19);
        float[] fArr2 = new float[fArr.length];
        fArr2[0] = f19 / f22;
        fArr2[1] = f20 / f22;
        fArr2[2] = f21 / f22;
        fArr2[3] = ((f12 * f17) - (f11 * f18)) / f22;
        fArr2[4] = ((f18 * f10) - (f12 * f16)) / f22;
        fArr2[5] = ((f16 * f11) - (f17 * f10)) / f22;
        fArr2[6] = ((f11 * f15) - (f12 * f14)) / f22;
        fArr2[7] = ((f12 * f13) - (f15 * f10)) / f22;
        fArr2[8] = ((f10 * f14) - (f11 * f13)) / f22;
        return fArr2;
    }

    public static final float[] h(float[] fArr, float[] fArr2) {
        float f10 = fArr[0];
        float f11 = fArr2[0];
        float f12 = fArr[3];
        float f13 = fArr2[1];
        float f14 = fArr[6];
        float f15 = fArr2[2];
        float f16 = (f14 * f15) + (f12 * f13) + (f10 * f11);
        float f17 = fArr[1];
        float f18 = fArr[4];
        float f19 = fArr[7];
        float f20 = (f19 * f15) + (f18 * f13) + (f17 * f11);
        float f21 = fArr[2];
        float f22 = fArr[5];
        float f23 = fArr[8];
        float f24 = (f15 * f23) + (f13 * f22) + (f11 * f21);
        float f25 = fArr2[3];
        float f26 = fArr2[4];
        float f27 = fArr2[5];
        float f28 = (f14 * f27) + (f12 * f26) + (f10 * f25);
        float f29 = (f19 * f27) + (f18 * f26) + (f17 * f25);
        float f30 = (f27 * f23) + (f26 * f22) + (f25 * f21);
        float f31 = fArr2[6];
        float f32 = fArr2[7];
        float f33 = (f12 * f32) + (f10 * f31);
        float f34 = fArr2[8];
        return new float[]{f16, f20, f24, f28, f29, f30, (f14 * f34) + f33, (f19 * f34) + (f18 * f32) + (f17 * f31), (f23 * f34) + (f22 * f32) + (f21 * f31)};
    }

    public static final float[] i(float[] fArr, float[] fArr2) {
        float f10 = fArr[0];
        float f11 = fArr2[0] * f10;
        float f12 = fArr[1];
        float f13 = fArr2[1] * f12;
        float f14 = fArr[2];
        return new float[]{f11, f13, fArr2[2] * f14, fArr2[3] * f10, fArr2[4] * f12, fArr2[5] * f14, f10 * fArr2[6], f12 * fArr2[7], f14 * fArr2[8]};
    }

    public static final void j(float[] fArr, float[] fArr2) {
        float f10 = fArr2[0];
        float f11 = fArr2[1];
        float f12 = fArr2[2];
        fArr2[0] = (fArr[6] * f12) + (fArr[3] * f11) + (fArr[0] * f10);
        fArr2[1] = (fArr[7] * f12) + (fArr[4] * f11) + (fArr[1] * f10);
        fArr2[2] = (fArr[8] * f12) + (fArr[5] * f11) + (fArr[2] * f10);
    }
}
