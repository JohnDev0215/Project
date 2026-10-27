package g.a.e.a;

import g.a.a.c1;
import g.a.a.n;
import g.a.a.q;
import g.a.a.r;
import g.a.a.y0;

/* JADX INFO: loaded from: classes.dex */
public class f extends g.a.a.l {
    public byte[] E3;
    public byte[] F3;
    public g.a.e.b.c.a[] G3;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public g.a.a.j f5578c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public g.a.a.m f5579d;
    public byte[][] s;
    public byte[] x;
    public byte[][] y;

    public f(r rVar) {
        int i2 = 0;
        if (rVar.q(0) instanceof g.a.a.j) {
            this.f5578c = g.a.a.j.n(rVar.q(0));
        } else {
            this.f5579d = g.a.a.m.s(rVar.q(0));
        }
        r rVar2 = (r) rVar.q(1);
        this.s = new byte[rVar2.size()][];
        for (int i3 = 0; i3 < rVar2.size(); i3++) {
            this.s[i3] = ((n) rVar2.q(i3)).p();
        }
        this.x = ((n) ((r) rVar.q(2)).q(0)).p();
        r rVar3 = (r) rVar.q(3);
        this.y = new byte[rVar3.size()][];
        for (int i4 = 0; i4 < rVar3.size(); i4++) {
            this.y[i4] = ((n) rVar3.q(i4)).p();
        }
        this.E3 = ((n) ((r) rVar.q(4)).q(0)).p();
        this.F3 = ((n) ((r) rVar.q(5)).q(0)).p();
        r rVar4 = (r) rVar.q(6);
        byte[][][][] bArr = new byte[rVar4.size()][][][];
        byte[][][][] bArr2 = new byte[rVar4.size()][][][];
        byte[][][] bArr3 = new byte[rVar4.size()][][];
        byte[][] bArr4 = new byte[rVar4.size()][];
        int i5 = 0;
        while (i5 < rVar4.size()) {
            r rVar5 = (r) rVar4.q(i5);
            r rVar6 = (r) rVar5.q(i2);
            bArr[i5] = new byte[rVar6.size()][][];
            for (int i6 = i2; i6 < rVar6.size(); i6++) {
                r rVar7 = (r) rVar6.q(i6);
                bArr[i5][i6] = new byte[rVar7.size()][];
                for (int i7 = 0; i7 < rVar7.size(); i7++) {
                    bArr[i5][i6][i7] = ((n) rVar7.q(i7)).p();
                }
            }
            r rVar8 = (r) rVar5.q(1);
            bArr2[i5] = new byte[rVar8.size()][][];
            for (int i8 = 0; i8 < rVar8.size(); i8++) {
                r rVar9 = (r) rVar8.q(i8);
                bArr2[i5][i8] = new byte[rVar9.size()][];
                for (int i9 = 0; i9 < rVar9.size(); i9++) {
                    bArr2[i5][i8][i9] = ((n) rVar9.q(i9)).p();
                }
            }
            r rVar10 = (r) rVar5.q(2);
            bArr3[i5] = new byte[rVar10.size()][];
            for (int i10 = 0; i10 < rVar10.size(); i10++) {
                bArr3[i5][i10] = ((n) rVar10.q(i10)).p();
            }
            bArr4[i5] = ((n) rVar5.q(3)).p();
            i5++;
            i2 = 0;
        }
        int length = this.F3.length - 1;
        this.G3 = new g.a.e.b.c.a[length];
        int i11 = 0;
        while (i11 < length) {
            byte[] bArr5 = this.F3;
            int i12 = i11 + 1;
            this.G3[i11] = new g.a.e.b.c.a(bArr5[i11], bArr5[i12], g.a.e.b.c.f.a.f(bArr[i11]), g.a.e.b.c.f.a.f(bArr2[i11]), g.a.e.b.c.f.a.d(bArr3[i11]), g.a.e.b.c.f.a.b(bArr4[i11]));
            i11 = i12;
        }
    }

    public f(short[][] sArr, short[] sArr2, short[][] sArr3, short[] sArr4, int[] iArr, g.a.e.b.c.a[] aVarArr) {
        this.f5578c = new g.a.a.j(1L);
        this.s = g.a.e.b.c.f.a.c(sArr);
        this.x = g.a.e.b.c.f.a.a(sArr2);
        this.y = g.a.e.b.c.f.a.c(sArr3);
        this.E3 = g.a.e.b.c.f.a.a(sArr4);
        this.F3 = g.a.e.b.c.f.a.h(iArr);
        this.G3 = aVarArr;
    }

    public static f i(Object obj) {
        if (obj instanceof f) {
            return (f) obj;
        }
        if (obj != null) {
            return new f(r.n(obj));
        }
        return null;
    }

    @Override // g.a.a.l, g.a.a.e
    public q c() {
        g.a.a.f fVar = new g.a.a.f();
        g.a.a.e eVar = this.f5578c;
        if (eVar == null) {
            eVar = this.f5579d;
        }
        fVar.a(eVar);
        g.a.a.f fVar2 = new g.a.a.f();
        int i2 = 0;
        while (true) {
            byte[][] bArr = this.s;
            if (i2 >= bArr.length) {
                break;
            }
            fVar2.a(new y0(bArr[i2]));
            i2++;
        }
        fVar.a(new c1(fVar2));
        g.a.a.f fVar3 = new g.a.a.f();
        fVar3.a(new y0(this.x));
        fVar.a(new c1(fVar3));
        g.a.a.f fVar4 = new g.a.a.f();
        int i3 = 0;
        while (true) {
            byte[][] bArr2 = this.y;
            if (i3 >= bArr2.length) {
                break;
            }
            fVar4.a(new y0(bArr2[i3]));
            i3++;
        }
        fVar.a(new c1(fVar4));
        g.a.a.f fVar5 = new g.a.a.f();
        fVar5.a(new y0(this.E3));
        fVar.a(new c1(fVar5));
        g.a.a.f fVar6 = new g.a.a.f();
        fVar6.a(new y0(this.F3));
        fVar.a(new c1(fVar6));
        g.a.a.f fVar7 = new g.a.a.f();
        for (int i4 = 0; i4 < this.G3.length; i4++) {
            g.a.a.f fVar8 = new g.a.a.f();
            byte[][][] bArrE = g.a.e.b.c.f.a.e(this.G3[i4].a());
            g.a.a.f fVar9 = new g.a.a.f();
            for (int i5 = 0; i5 < bArrE.length; i5++) {
                g.a.a.f fVar10 = new g.a.a.f();
                for (int i6 = 0; i6 < bArrE[i5].length; i6++) {
                    fVar10.a(new y0(bArrE[i5][i6]));
                }
                fVar9.a(new c1(fVar10));
            }
            fVar8.a(new c1(fVar9));
            byte[][][] bArrE2 = g.a.e.b.c.f.a.e(this.G3[i4].b());
            g.a.a.f fVar11 = new g.a.a.f();
            for (int i7 = 0; i7 < bArrE2.length; i7++) {
                g.a.a.f fVar12 = new g.a.a.f();
                for (int i8 = 0; i8 < bArrE2[i7].length; i8++) {
                    fVar12.a(new y0(bArrE2[i7][i8]));
                }
                fVar11.a(new c1(fVar12));
            }
            fVar8.a(new c1(fVar11));
            byte[][] bArrC = g.a.e.b.c.f.a.c(this.G3[i4].d());
            g.a.a.f fVar13 = new g.a.a.f();
            for (byte[] bArr3 : bArrC) {
                fVar13.a(new y0(bArr3));
            }
            fVar8.a(new c1(fVar13));
            fVar8.a(new y0(g.a.e.b.c.f.a.a(this.G3[i4].c())));
            fVar7.a(new c1(fVar8));
        }
        fVar.a(new c1(fVar7));
        return new c1(fVar);
    }

    public short[] g() {
        return g.a.e.b.c.f.a.b(this.x);
    }

    public short[] h() {
        return g.a.e.b.c.f.a.b(this.E3);
    }

    public short[][] j() {
        return g.a.e.b.c.f.a.d(this.s);
    }

    public short[][] k() {
        return g.a.e.b.c.f.a.d(this.y);
    }

    public g.a.e.b.c.a[] l() {
        return this.G3;
    }

    public int[] m() {
        return g.a.e.b.c.f.a.g(this.F3);
    }
}
