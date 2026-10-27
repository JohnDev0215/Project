package o0;

import n0.C1891t;

/* JADX INFO: renamed from: o0.g, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public class C2011g {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final AbstractC2007c f20200a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final AbstractC2007c f20201b;
    public final AbstractC2007c c;
    public final float[] d;

    public C2011g(AbstractC2007c abstractC2007c, AbstractC2007c abstractC2007c2, AbstractC2007c abstractC2007c3, float[] fArr) {
        this.f20200a = abstractC2007c;
        this.f20201b = abstractC2007c2;
        this.c = abstractC2007c3;
        this.d = fArr;
    }

    public long a(long j3) {
        float fH = C1891t.h(j3);
        float fG = C1891t.g(j3);
        float fE = C1891t.e(j3);
        float fD = C1891t.d(j3);
        AbstractC2007c abstractC2007c = this.f20201b;
        long jD = abstractC2007c.d(fH, fG, fE);
        float fIntBitsToFloat = Float.intBitsToFloat((int) (jD >> 32));
        float fIntBitsToFloat2 = Float.intBitsToFloat((int) (jD & 4294967295L));
        float fE2 = abstractC2007c.e(fH, fG, fE);
        float[] fArr = this.d;
        if (fArr != null) {
            fIntBitsToFloat *= fArr[0];
            fIntBitsToFloat2 *= fArr[1];
            fE2 *= fArr[2];
        }
        float f10 = fIntBitsToFloat;
        float f11 = fIntBitsToFloat2;
        return this.c.f(f10, f11, fE2, fD, this.f20200a);
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public C2011g(AbstractC2007c abstractC2007c, AbstractC2007c abstractC2007c2, int i7) {
        long j3 = abstractC2007c.f20179b;
        long j10 = AbstractC2006b.f20175a;
        AbstractC2007c abstractC2007cA = AbstractC2006b.a(j3, j10) ? AbstractC2014j.a(abstractC2007c) : abstractC2007c;
        AbstractC2007c abstractC2007cA2 = AbstractC2006b.a(abstractC2007c2.f20179b, j10) ? AbstractC2014j.a(abstractC2007c2) : abstractC2007c2;
        float[] fArr = null;
        if (i7 == 3) {
            boolean zA = AbstractC2006b.a(abstractC2007c.f20179b, j10);
            boolean zA2 = AbstractC2006b.a(abstractC2007c2.f20179b, j10);
            if ((!zA || !zA2) && (zA || zA2)) {
                abstractC2007c = zA ? abstractC2007c : abstractC2007c2;
                float[] fArrA = AbstractC2014j.f20205e;
                C2023s c2023s = ((C2021q) abstractC2007c).d;
                float[] fArrA2 = zA ? c2023s.a() : fArrA;
                fArrA = zA2 ? c2023s.a() : fArrA;
                fArr = new float[]{fArrA2[0] / fArrA[0], fArrA2[1] / fArrA[1], fArrA2[2] / fArrA[2]};
            }
        }
        this(abstractC2007c2, abstractC2007cA, abstractC2007cA2, fArr);
    }
}
