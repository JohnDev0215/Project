package g.a.d.b.a0.c;

import g.a.d.b.d;
import java.math.BigInteger;

/* JADX INFO: loaded from: classes.dex */
public class o extends d.c {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public static final BigInteger f5389i = new BigInteger(1, g.a.f.j.d.a("FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFEFFFFEE37"));
    public r j;

    public class a implements g.a.d.b.f {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ int f5390a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ int[] f5391b;

        public a(int i2, int[] iArr) {
            this.f5390a = i2;
            this.f5391b = iArr;
        }

        @Override // g.a.d.b.f
        public g.a.d.b.h a(int i2) {
            int[] iArrG = g.a.d.d.e.g();
            int[] iArrG2 = g.a.d.d.e.g();
            int i3 = 0;
            for (int i4 = 0; i4 < this.f5390a; i4++) {
                int i5 = ((i4 ^ i2) - 1) >> 31;
                for (int i6 = 0; i6 < 6; i6++) {
                    int i7 = iArrG[i6];
                    int[] iArr = this.f5391b;
                    iArrG[i6] = i7 ^ (iArr[i3 + i6] & i5);
                    iArrG2[i6] = iArrG2[i6] ^ (iArr[(i3 + 6) + i6] & i5);
                }
                i3 += 12;
            }
            return o.this.i(new q(iArrG), new q(iArrG2), false);
        }

        @Override // g.a.d.b.f
        public int b() {
            return this.f5390a;
        }
    }

    public o() {
        super(f5389i);
        this.j = new r(this, null, null);
        this.f5477b = n(g.a.d.b.c.f5470a);
        this.f5478c = n(BigInteger.valueOf(3L));
        this.f5479d = new BigInteger(1, g.a.f.j.d.a("FFFFFFFFFFFFFFFFFFFFFFFE26F2FC170F69466A74DEFD8D"));
        this.f5480e = BigInteger.valueOf(1L);
        this.f5481f = 2;
    }

    @Override // g.a.d.b.d
    public boolean E(int i2) {
        return i2 == 2;
    }

    @Override // g.a.d.b.d
    public g.a.d.b.d c() {
        return new o();
    }

    @Override // g.a.d.b.d
    public g.a.d.b.f e(g.a.d.b.h[] hVarArr, int i2, int i3) {
        int[] iArr = new int[i3 * 6 * 2];
        int i4 = 0;
        for (int i5 = 0; i5 < i3; i5++) {
            g.a.d.b.h hVar = hVarArr[i2 + i5];
            g.a.d.d.e.e(((q) hVar.n()).f5403h, 0, iArr, i4);
            int i6 = i4 + 6;
            g.a.d.d.e.e(((q) hVar.o()).f5403h, 0, iArr, i6);
            i4 = i6 + 6;
        }
        return new a(i3, iArr);
    }

    @Override // g.a.d.b.d
    public g.a.d.b.h i(g.a.d.b.e eVar, g.a.d.b.e eVar2, boolean z) {
        return new r(this, eVar, eVar2, z);
    }

    @Override // g.a.d.b.d
    public g.a.d.b.h j(g.a.d.b.e eVar, g.a.d.b.e eVar2, g.a.d.b.e[] eVarArr, boolean z) {
        return new r(this, eVar, eVar2, eVarArr, z);
    }

    @Override // g.a.d.b.d
    public g.a.d.b.e n(BigInteger bigInteger) {
        return new q(bigInteger);
    }

    @Override // g.a.d.b.d
    public int u() {
        return f5389i.bitLength();
    }

    @Override // g.a.d.b.d
    public g.a.d.b.h v() {
        return this.j;
    }
}
