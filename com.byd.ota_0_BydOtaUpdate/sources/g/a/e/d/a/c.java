package g.a.e.d.a;

import java.lang.reflect.Array;

/* JADX INFO: loaded from: classes.dex */
public final class c {
    public static a a(b bVar, i iVar) {
        int iD = bVar.d();
        int i2 = 1 << iD;
        int iG = iVar.g();
        int i3 = 0;
        int[][] iArr = (int[][]) Array.newInstance((Class<?>) int.class, iG, i2);
        int[][] iArr2 = (int[][]) Array.newInstance((Class<?>) int.class, iG, i2);
        for (int i4 = 0; i4 < i2; i4++) {
            iArr2[0][i4] = bVar.f(iVar.e(i4));
        }
        for (int i5 = 1; i5 < iG; i5++) {
            for (int i6 = 0; i6 < i2; i6++) {
                iArr2[i5][i6] = bVar.h(iArr2[i5 - 1][i6], i6);
            }
        }
        int i7 = 0;
        while (i7 < iG) {
            int i8 = i3;
            while (i8 < i2) {
                for (int i9 = i3; i9 <= i7; i9++) {
                    iArr[i7][i8] = bVar.a(iArr[i7][i8], bVar.h(iArr2[i9][i8], iVar.f((iG + i9) - i7)));
                }
                i8++;
                i3 = 0;
            }
            i7++;
            i3 = 0;
        }
        int[][] iArr3 = (int[][]) Array.newInstance((Class<?>) int.class, iG * iD, (i2 + 31) >>> 5);
        for (int i10 = 0; i10 < i2; i10++) {
            int i11 = i10 >>> 5;
            int i12 = 1 << (i10 & 31);
            for (int i13 = 0; i13 < iG; i13++) {
                int i14 = iArr[i13][i10];
                for (int i15 = 0; i15 < iD; i15++) {
                    if (((i14 >>> i15) & 1) != 0) {
                        int[] iArr4 = iArr3[(((i13 + 1) * iD) - i15) - 1];
                        iArr4[i11] = iArr4[i11] ^ i12;
                    }
                }
            }
        }
        return new a(i2, iArr3);
    }
}
