package u;

import n7.AbstractC1996k;

/* JADX INFO: renamed from: u.g, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC2467g {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final float[] f22600a;

    static {
        long[] jArr = AbstractC2456D.f22584a;
        int iF = AbstractC2456D.f(0);
        int iMax = iF > 0 ? Math.max(7, AbstractC2456D.e(iF)) : 0;
        if (iMax != 0) {
            jArr = new long[((iMax + 15) & (-8)) >> 3];
            AbstractC1996k.K(jArr);
        }
        int i7 = iMax >> 3;
        long j3 = 255 << ((iMax & 7) << 3);
        jArr[i7] = (jArr[i7] & (~j3)) | j3;
        float[] fArr = new float[iMax];
        f22600a = new float[0];
    }
}
