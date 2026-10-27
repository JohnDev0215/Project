package j9;

import java.io.EOFException;
import java.util.Arrays;
import n7.AbstractC1996k;
import r9.C2319i;
import r9.C2322l;

/* JADX INFO: loaded from: classes.dex */
public final class c {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final C2319i f17510b;
    public boolean d;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public int f17514h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public int f17515i;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final boolean f17509a = true;
    public int c = Integer.MAX_VALUE;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f17511e = 4096;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public b[] f17512f = new b[8];

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public int f17513g = 7;

    public c(C2319i c2319i) {
        this.f17510b = c2319i;
    }

    public final void a(int i7) {
        int i10;
        if (i7 > 0) {
            int length = this.f17512f.length - 1;
            int i11 = 0;
            while (true) {
                i10 = this.f17513g;
                if (length < i10 || i7 <= 0) {
                    break;
                }
                b bVar = this.f17512f[length];
                A7.m.c(bVar);
                i7 -= bVar.c;
                int i12 = this.f17515i;
                b bVar2 = this.f17512f[length];
                A7.m.c(bVar2);
                this.f17515i = i12 - bVar2.c;
                this.f17514h--;
                i11++;
                length--;
            }
            b[] bVarArr = this.f17512f;
            int i13 = i10 + 1;
            System.arraycopy(bVarArr, i13, bVarArr, i13 + i11, this.f17514h);
            b[] bVarArr2 = this.f17512f;
            int i14 = this.f17513g + 1;
            Arrays.fill(bVarArr2, i14, i14 + i11, (Object) null);
            this.f17513g += i11;
        }
    }

    public final void b(b bVar) {
        int i7 = this.f17511e;
        int i10 = bVar.c;
        if (i10 > i7) {
            b[] bVarArr = this.f17512f;
            AbstractC1996k.J(bVarArr, null, 0, bVarArr.length);
            this.f17513g = this.f17512f.length - 1;
            this.f17514h = 0;
            this.f17515i = 0;
            return;
        }
        a((this.f17515i + i10) - i7);
        int i11 = this.f17514h + 1;
        b[] bVarArr2 = this.f17512f;
        if (i11 > bVarArr2.length) {
            b[] bVarArr3 = new b[bVarArr2.length * 2];
            System.arraycopy(bVarArr2, 0, bVarArr3, bVarArr2.length, bVarArr2.length);
            this.f17513g = this.f17512f.length - 1;
            this.f17512f = bVarArr3;
        }
        int i12 = this.f17513g;
        this.f17513g = i12 - 1;
        this.f17512f[i12] = bVar;
        this.f17514h++;
        this.f17515i += i10;
    }

    public final void c(C2322l c2322l) throws EOFException {
        A7.m.f(c2322l, "data");
        C2319i c2319i = this.f17510b;
        if (this.f17509a) {
            int[] iArr = w.f17595a;
            int iD = c2322l.d();
            long j3 = 0;
            for (int i7 = 0; i7 < iD; i7++) {
                byte bI = c2322l.i(i7);
                byte[] bArr = d9.b.f13645a;
                j3 += (long) w.f17596b[bI & 255];
            }
            if (((int) ((j3 + ((long) 7)) >> 3)) < c2322l.d()) {
                C2319i c2319i2 = new C2319i();
                int[] iArr2 = w.f17595a;
                int iD2 = c2322l.d();
                long j10 = 0;
                int i10 = 0;
                for (int i11 = 0; i11 < iD2; i11++) {
                    byte bI2 = c2322l.i(i11);
                    byte[] bArr2 = d9.b.f13645a;
                    int i12 = bI2 & 255;
                    int i13 = w.f17595a[i12];
                    byte b10 = w.f17596b[i12];
                    j10 = (j10 << b10) | ((long) i13);
                    i10 += b10;
                    while (i10 >= 8) {
                        i10 -= 8;
                        c2319i2.X((int) (j10 >> i10));
                    }
                }
                if (i10 > 0) {
                    c2319i2.X((int) ((255 >>> i10) | (j10 << (8 - i10))));
                }
                C2322l c2322lF = c2319i2.F(c2319i2.f21932m);
                e(c2322lF.d(), 127, 128);
                c2319i.U(c2322lF);
                return;
            }
        }
        e(c2322l.d(), 127, 0);
        c2319i.U(c2322l);
    }

    /* JADX WARN: Removed duplicated region for block: B:22:0x0069  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void d(java.util.ArrayList r14) throws java.io.EOFException {
        /*
            Method dump skipped, instruction units count: 254
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: j9.c.d(java.util.ArrayList):void");
    }

    public final void e(int i7, int i10, int i11) {
        C2319i c2319i = this.f17510b;
        if (i7 < i10) {
            c2319i.X(i7 | i11);
            return;
        }
        c2319i.X(i11 | i10);
        int i12 = i7 - i10;
        while (i12 >= 128) {
            c2319i.X(128 | (i12 & 127));
            i12 >>>= 7;
        }
        c2319i.X(i12);
    }
}
