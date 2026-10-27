package B2;

import h2.AbstractC1348e;
import java.util.Collections;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class A {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final List f492a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final int f493b;
    public final int c;
    public final int d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final int f494e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final int f495f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final int f496g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final int f497h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final float f498i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final int f499j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public final String f500k;
    public final B8.t l;

    public A(List list, int i7, int i10, int i11, int i12, int i13, int i14, int i15, float f10, int i16, String str, B8.t tVar) {
        this.f492a = list;
        this.f493b = i7;
        this.c = i10;
        this.d = i11;
        this.f494e = i12;
        this.f495f = i13;
        this.f496g = i14;
        this.f497h = i15;
        this.f498i = f10;
        this.f499j = i16;
        this.f500k = str;
        this.l = tVar;
    }

    public static A a(h2.t tVar, boolean z6, B8.t tVar2) {
        int i7;
        L8.p pVarF;
        int i10;
        int i11;
        int i12;
        int i13;
        int i14 = 4;
        try {
            if (z6) {
                tVar.I(4);
            } else {
                tVar.I(21);
            }
            int iV = tVar.v() & 3;
            int iV2 = tVar.v();
            int iC = tVar.c();
            int i15 = 0;
            int i16 = 0;
            for (int i17 = 0; i17 < iV2; i17++) {
                tVar.I(1);
                int iB = tVar.B();
                for (int i18 = 0; i18 < iB; i18++) {
                    int iB2 = tVar.B();
                    i16 += iB2 + 4;
                    tVar.I(iB2);
                }
            }
            tVar.H(iC);
            byte[] bArr = new byte[i16];
            B8.t tVar3 = tVar2;
            int i19 = -1;
            int i20 = -1;
            int i21 = -1;
            int i22 = -1;
            int i23 = -1;
            int i24 = -1;
            int i25 = -1;
            float f10 = 1.0f;
            String strA = null;
            int i26 = 0;
            int i27 = 0;
            while (i26 < iV2) {
                int iV3 = tVar.v() & 63;
                int iB3 = tVar.B();
                int i28 = i15;
                B8.t tVarH = tVar3;
                while (i28 < iB3) {
                    int iB4 = tVar.B();
                    int i29 = iV2;
                    System.arraycopy(i2.n.f15713a, i15, bArr, i27, i14);
                    int i30 = i27 + 4;
                    System.arraycopy(tVar.f15241a, tVar.c(), bArr, i30, iB4);
                    if (iV3 == 32 && i28 == 0) {
                        tVarH = i2.n.h(i30, i30 + iB4, bArr);
                        i7 = iB3;
                    } else if (iV3 == 33 && i28 == 0) {
                        i2.j jVarG = i2.n.g(bArr, i30, i30 + iB4, tVarH);
                        int i31 = jVarG.f15687b + 8;
                        int i32 = jVarG.c + 8;
                        int i33 = jVarG.f15691h;
                        int i34 = jVarG.f15692i;
                        i21 = i33;
                        int i35 = jVarG.f15693j;
                        float f11 = jVarG.f15689f;
                        int i36 = jVarG.f15690g;
                        i2.h hVar = jVarG.f15686a;
                        if (hVar != null) {
                            i10 = i36;
                            i11 = i34;
                            i7 = iB3;
                            i12 = i31;
                            i13 = i32;
                            strA = AbstractC1348e.a(hVar.f15679a, hVar.f15680b, hVar.c, hVar.d, hVar.f15681e, hVar.f15682f);
                        } else {
                            i10 = i36;
                            i11 = i34;
                            i7 = iB3;
                            i12 = i31;
                            i13 = i32;
                        }
                        i19 = i12;
                        i20 = i13;
                        i15 = 0;
                        i23 = i35;
                        i22 = i11;
                        i25 = i10;
                        f10 = f11;
                    } else {
                        i7 = iB3;
                        if (iV3 != 39 || i28 != 0 || (pVarF = i2.n.f(i30, i30 + iB4, bArr)) == null || tVarH == null) {
                            i15 = 0;
                        } else {
                            i15 = 0;
                            i24 = pVarF.l == ((i2.g) ((d5.I) tVarH.f808n).get(0)).f15678b ? 4 : 5;
                        }
                    }
                    i27 = i30 + iB4;
                    tVar.I(iB4);
                    i28++;
                    iB3 = i7;
                    iV2 = i29;
                    i14 = 4;
                }
                i26++;
                tVar3 = tVarH;
                i14 = 4;
            }
            return new A(i16 == 0 ? Collections.emptyList() : Collections.singletonList(bArr), iV + 1, i19, i20, i21, i22, i23, i24, f10, i25, strA, tVar3);
        } catch (ArrayIndexOutOfBoundsException e10) {
            throw e2.M.a(e10, "Error parsing".concat(z6 ? "L-HEVC config" : "HEVC config"));
        }
    }
}
