package com.google.zxing.pdf417.decoder.j;

import com.google.zxing.ChecksumException;

/* JADX INFO: compiled from: ErrorCorrection.java */
/* JADX INFO: loaded from: classes.dex */
public final class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final b f4473a = b.f4474a;

    private int[] b(c cVar) throws ChecksumException {
        int iD = cVar.d();
        int[] iArr = new int[iD];
        int i = 0;
        for (int i2 = 1; i2 < this.f4473a.e() && i < iD; i2++) {
            if (cVar.b(i2) == 0) {
                iArr[i] = this.f4473a.g(i2);
                i++;
            }
        }
        if (i == iD) {
            return iArr;
        }
        throw ChecksumException.getChecksumInstance();
    }

    private int[] c(c cVar, c cVar2, int[] iArr) {
        int iD = cVar2.d();
        int[] iArr2 = new int[iD];
        for (int i = 1; i <= iD; i++) {
            iArr2[iD - i] = this.f4473a.i(i, cVar2.c(i));
        }
        c cVar3 = new c(this.f4473a, iArr2);
        int length = iArr.length;
        int[] iArr3 = new int[length];
        for (int i2 = 0; i2 < length; i2++) {
            int iG = this.f4473a.g(iArr[i2]);
            iArr3[i2] = this.f4473a.i(this.f4473a.j(0, cVar.b(iG)), this.f4473a.g(cVar3.b(iG)));
        }
        return iArr3;
    }

    private c[] d(c cVar, c cVar2, int i) throws ChecksumException {
        if (cVar.d() < cVar2.d()) {
            cVar2 = cVar;
            cVar = cVar2;
        }
        c cVarF = this.f4473a.f();
        c cVarD = this.f4473a.d();
        while (true) {
            c cVar3 = cVar2;
            cVar2 = cVar;
            cVar = cVar3;
            c cVar4 = cVarD;
            c cVar5 = cVarF;
            cVarF = cVar4;
            if (cVar.d() < i / 2) {
                int iC = cVarF.c(0);
                if (iC == 0) {
                    throw ChecksumException.getChecksumInstance();
                }
                int iG = this.f4473a.g(iC);
                return new c[]{cVarF.f(iG), cVar.f(iG)};
            }
            if (cVar.e()) {
                throw ChecksumException.getChecksumInstance();
            }
            c cVarF2 = this.f4473a.f();
            int iG2 = this.f4473a.g(cVar.c(cVar.d()));
            while (cVar2.d() >= cVar.d() && !cVar2.e()) {
                int iD = cVar2.d() - cVar.d();
                int i2 = this.f4473a.i(cVar2.c(cVar2.d()), iG2);
                cVarF2 = cVarF2.a(this.f4473a.b(iD, i2));
                cVar2 = cVar2.j(cVar.h(iD, i2));
            }
            cVarD = cVarF2.g(cVarF).j(cVar5).i();
        }
    }

    public int a(int[] iArr, int i, int[] iArr2) throws ChecksumException {
        c cVar = new c(this.f4473a, iArr);
        int[] iArr3 = new int[i];
        boolean z = false;
        for (int i2 = i; i2 > 0; i2--) {
            int iB = cVar.b(this.f4473a.c(i2));
            iArr3[i - i2] = iB;
            if (iB != 0) {
                z = true;
            }
        }
        if (!z) {
            return 0;
        }
        c cVarD = this.f4473a.d();
        if (iArr2 != null) {
            for (int i3 : iArr2) {
                int iC = this.f4473a.c((iArr.length - 1) - i3);
                b bVar = this.f4473a;
                cVarD = cVarD.g(new c(bVar, new int[]{bVar.j(0, iC), 1}));
            }
        }
        c[] cVarArrD = d(this.f4473a.b(i, 1), new c(this.f4473a, iArr3), i);
        c cVar2 = cVarArrD[0];
        c cVar3 = cVarArrD[1];
        int[] iArrB = b(cVar2);
        int[] iArrC = c(cVar3, cVar2, iArrB);
        for (int i4 = 0; i4 < iArrB.length; i4++) {
            int length = (iArr.length - 1) - this.f4473a.h(iArrB[i4]);
            if (length < 0) {
                throw ChecksumException.getChecksumInstance();
            }
            iArr[length] = this.f4473a.j(iArr[length], iArrC[i4]);
        }
        return iArrB.length;
    }
}
