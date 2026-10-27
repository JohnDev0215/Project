package com.google.zxing.common.reedsolomon;

/* JADX INFO: compiled from: ReedSolomonDecoder.java */
/* JADX INFO: loaded from: classes.dex */
public final class c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final a f4244a;

    public c(a aVar) {
        this.f4244a = aVar;
    }

    private int[] b(b bVar) throws ReedSolomonException {
        int iF = bVar.f();
        int i = 0;
        if (iF == 1) {
            return new int[]{bVar.d(1)};
        }
        int[] iArr = new int[iF];
        for (int i2 = 1; i2 < this.f4244a.f() && i < iF; i2++) {
            if (bVar.c(i2) == 0) {
                iArr[i] = this.f4244a.h(i2);
                i++;
            }
        }
        if (i == iF) {
            return iArr;
        }
        throw new ReedSolomonException("Error locator degree does not match number of roots");
    }

    private int[] c(b bVar, int[] iArr) {
        int length = iArr.length;
        int[] iArr2 = new int[length];
        for (int i = 0; i < length; i++) {
            int iH = this.f4244a.h(iArr[i]);
            int iJ = 1;
            for (int i2 = 0; i2 < length; i2++) {
                if (i != i2) {
                    int iJ2 = this.f4244a.j(iArr[i2], iH);
                    iJ = this.f4244a.j(iJ, (iJ2 & 1) == 0 ? iJ2 | 1 : iJ2 & (-2));
                }
            }
            iArr2[i] = this.f4244a.j(bVar.c(iH), this.f4244a.h(iJ));
            if (this.f4244a.d() != 0) {
                iArr2[i] = this.f4244a.j(iArr2[i], iH);
            }
        }
        return iArr2;
    }

    private b[] d(b bVar, b bVar2, int i) throws ReedSolomonException {
        if (bVar.f() < bVar2.f()) {
            bVar2 = bVar;
            bVar = bVar2;
        }
        b bVarG = this.f4244a.g();
        b bVarE = this.f4244a.e();
        do {
            b bVar3 = bVar2;
            bVar2 = bVar;
            bVar = bVar3;
            b bVar4 = bVarE;
            b bVar5 = bVarG;
            bVarG = bVar4;
            if (bVar.f() < i / 2) {
                int iD = bVarG.d(0);
                if (iD == 0) {
                    throw new ReedSolomonException("sigmaTilde(0) was zero");
                }
                int iH = this.f4244a.h(iD);
                return new b[]{bVarG.h(iH), bVar.h(iH)};
            }
            if (bVar.g()) {
                throw new ReedSolomonException("r_{i-1} was zero");
            }
            b bVarG2 = this.f4244a.g();
            int iH2 = this.f4244a.h(bVar.d(bVar.f()));
            while (bVar2.f() >= bVar.f() && !bVar2.g()) {
                int iF = bVar2.f() - bVar.f();
                int iJ = this.f4244a.j(bVar2.d(bVar2.f()), iH2);
                bVarG2 = bVarG2.a(this.f4244a.b(iF, iJ));
                bVar2 = bVar2.a(bVar.j(iF, iJ));
            }
            bVarE = bVarG2.i(bVarG).a(bVar5);
        } while (bVar2.f() < bVar.f());
        throw new IllegalStateException("Division algorithm failed to reduce polynomial?");
    }

    public void a(int[] iArr, int i) throws ReedSolomonException {
        b bVar = new b(this.f4244a, iArr);
        int[] iArr2 = new int[i];
        boolean z = true;
        for (int i2 = 0; i2 < i; i2++) {
            a aVar = this.f4244a;
            int iC = bVar.c(aVar.c(aVar.d() + i2));
            iArr2[(i - 1) - i2] = iC;
            if (iC != 0) {
                z = false;
            }
        }
        if (z) {
            return;
        }
        b[] bVarArrD = d(this.f4244a.b(i, 1), new b(this.f4244a, iArr2), i);
        b bVar2 = bVarArrD[0];
        b bVar3 = bVarArrD[1];
        int[] iArrB = b(bVar2);
        int[] iArrC = c(bVar3, iArrB);
        for (int i3 = 0; i3 < iArrB.length; i3++) {
            int length = (iArr.length - 1) - this.f4244a.i(iArrB[i3]);
            if (length < 0) {
                throw new ReedSolomonException("Bad error location");
            }
            iArr[length] = a.a(iArr[length], iArrC[i3]);
        }
    }
}
