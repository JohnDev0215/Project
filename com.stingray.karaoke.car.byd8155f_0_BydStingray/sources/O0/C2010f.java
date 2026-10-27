package o0;

import java.util.Arrays;
import n0.AbstractC1861K;
import n0.C1891t;

/* JADX INFO: renamed from: o0.f, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C2010f extends C2011g {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final C2021q f20197e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final C2021q f20198f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final float[] f20199g;

    public C2010f(C2021q c2021q, C2021q c2021q2, int i7) {
        float[] fArrH;
        super(c2021q2, c2021q, c2021q2, null);
        this.f20197e = c2021q;
        this.f20198f = c2021q2;
        C2023s c2023s = c2021q2.d;
        C2023s c2023s2 = c2021q.d;
        boolean zD = AbstractC2014j.d(c2023s2, c2023s);
        float[] fArrH2 = c2021q.f20219i;
        float[] fArrG = c2021q2.f20220j;
        if (zD) {
            fArrH = AbstractC2014j.h(fArrG, fArrH2);
        } else {
            float[] fArrA = c2023s2.a();
            C2023s c2023s3 = c2021q2.d;
            float[] fArrA2 = c2023s3.a();
            C2023s c2023s4 = AbstractC2014j.f20204b;
            boolean zD2 = AbstractC2014j.d(c2023s2, c2023s4);
            float[] fArr = AbstractC2014j.f20205e;
            float[] fArr2 = C2005a.f20173b.f20174a;
            if (!zD2) {
                float[] fArrCopyOf = Arrays.copyOf(fArr, 3);
                A7.m.e(fArrCopyOf, "copyOf(this, size)");
                fArrH2 = AbstractC2014j.h(AbstractC2014j.c(fArr2, fArrA, fArrCopyOf), fArrH2);
            }
            if (!AbstractC2014j.d(c2023s3, c2023s4)) {
                float[] fArrCopyOf2 = Arrays.copyOf(fArr, 3);
                A7.m.e(fArrCopyOf2, "copyOf(this, size)");
                fArrG = AbstractC2014j.g(AbstractC2014j.h(AbstractC2014j.c(fArr2, fArrA2, fArrCopyOf2), c2021q2.f20219i));
            }
            fArrH = AbstractC2014j.h(fArrG, i7 == 3 ? AbstractC2014j.i(new float[]{fArrA[0] / fArrA2[0], fArrA[1] / fArrA2[1], fArrA[2] / fArrA2[2]}, fArrH2) : fArrH2);
        }
        this.f20199g = fArrH;
    }

    @Override // o0.C2011g
    public final long a(long j3) {
        float fH = C1891t.h(j3);
        float fG = C1891t.g(j3);
        float fE = C1891t.e(j3);
        float fD = C1891t.d(j3);
        C2017m c2017m = this.f20197e.f20224p;
        float fC = (float) c2017m.c(fH);
        float fC2 = (float) c2017m.c(fG);
        float fC3 = (float) c2017m.c(fE);
        float[] fArr = this.f20199g;
        float f10 = (fArr[6] * fC3) + (fArr[3] * fC2) + (fArr[0] * fC);
        float f11 = (fArr[7] * fC3) + (fArr[4] * fC2) + (fArr[1] * fC);
        float f12 = (fArr[8] * fC3) + (fArr[5] * fC2) + (fArr[2] * fC);
        C2021q c2021q = this.f20198f;
        float fC4 = (float) c2021q.f20222m.c(f10);
        C2017m c2017m2 = c2021q.f20222m;
        return AbstractC1861K.b(fC4, (float) c2017m2.c(f11), (float) c2017m2.c(f12), fD, c2021q);
    }
}
