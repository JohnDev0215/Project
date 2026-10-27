package g.a.d.b.a0.c;

import g.a.d.b.d;
import java.math.BigInteger;

/* JADX INFO: loaded from: classes.dex */
public class m0 extends d.c {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public static final BigInteger f5376i = new BigInteger(1, g.a.f.j.d.a("FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFEFFFFFFFF0000000000000000FFFFFFFF"));
    public p0 j;

    public class a implements g.a.d.b.f {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ int f5377a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ int[] f5378b;

        public a(int i2, int[] iArr) {
            this.f5377a = i2;
            this.f5378b = iArr;
        }

        @Override // g.a.d.b.f
        public g.a.d.b.h a(int i2) {
            int[] iArrJ = g.a.d.d.m.j(12);
            int[] iArrJ2 = g.a.d.d.m.j(12);
            int i3 = 0;
            for (int i4 = 0; i4 < this.f5377a; i4++) {
                int i5 = ((i4 ^ i2) - 1) >> 31;
                for (int i6 = 0; i6 < 12; i6++) {
                    int i7 = iArrJ[i6];
                    int[] iArr = this.f5378b;
                    iArrJ[i6] = i7 ^ (iArr[i3 + i6] & i5);
                    iArrJ2[i6] = iArrJ2[i6] ^ (iArr[(i3 + 12) + i6] & i5);
                }
                i3 += 24;
            }
            return m0.this.i(new o0(iArrJ), new o0(iArrJ2), false);
        }

        @Override // g.a.d.b.f
        public int b() {
            return this.f5377a;
        }
    }

    public m0() {
        super(f5376i);
        this.j = new p0(this, null, null);
        this.f5477b = n(new BigInteger(1, g.a.f.j.d.a("FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFEFFFFFFFF0000000000000000FFFFFFFC")));
        this.f5478c = n(new BigInteger(1, g.a.f.j.d.a("B3312FA7E23EE7E4988E056BE3F82D19181D9C6EFE8141120314088F5013875AC656398D8A2ED19D2A85C8EDD3EC2AEF")));
        this.f5479d = new BigInteger(1, g.a.f.j.d.a("FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFC7634D81F4372DDF581A0DB248B0A77AECEC196ACCC52973"));
        this.f5480e = BigInteger.valueOf(1L);
        this.f5481f = 2;
    }

    @Override // g.a.d.b.d
    public boolean E(int i2) {
        return i2 == 2;
    }

    @Override // g.a.d.b.d
    public g.a.d.b.d c() {
        return new m0();
    }

    @Override // g.a.d.b.d
    public g.a.d.b.f e(g.a.d.b.h[] hVarArr, int i2, int i3) {
        int[] iArr = new int[i3 * 12 * 2];
        int i4 = 0;
        for (int i5 = 0; i5 < i3; i5++) {
            g.a.d.b.h hVar = hVarArr[i2 + i5];
            g.a.d.d.m.h(12, ((o0) hVar.n()).f5394h, 0, iArr, i4);
            int i6 = i4 + 12;
            g.a.d.d.m.h(12, ((o0) hVar.o()).f5394h, 0, iArr, i6);
            i4 = i6 + 12;
        }
        return new a(i3, iArr);
    }

    @Override // g.a.d.b.d
    public g.a.d.b.h i(g.a.d.b.e eVar, g.a.d.b.e eVar2, boolean z) {
        return new p0(this, eVar, eVar2, z);
    }

    @Override // g.a.d.b.d
    public g.a.d.b.h j(g.a.d.b.e eVar, g.a.d.b.e eVar2, g.a.d.b.e[] eVarArr, boolean z) {
        return new p0(this, eVar, eVar2, eVarArr, z);
    }

    @Override // g.a.d.b.d
    public g.a.d.b.e n(BigInteger bigInteger) {
        return new o0(bigInteger);
    }

    @Override // g.a.d.b.d
    public int u() {
        return f5376i.bitLength();
    }

    @Override // g.a.d.b.d
    public g.a.d.b.h v() {
        return this.j;
    }
}
