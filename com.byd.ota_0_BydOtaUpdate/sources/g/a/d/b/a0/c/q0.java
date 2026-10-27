package g.a.d.b.a0.c;

import g.a.d.b.d;
import java.math.BigInteger;

/* JADX INFO: loaded from: classes.dex */
public class q0 extends d.c {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public static final BigInteger f5404i = new BigInteger(1, g.a.f.j.d.a("01FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF"));
    public t0 j;

    public class a implements g.a.d.b.f {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ int f5405a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ int[] f5406b;

        public a(int i2, int[] iArr) {
            this.f5405a = i2;
            this.f5406b = iArr;
        }

        @Override // g.a.d.b.f
        public g.a.d.b.h a(int i2) {
            int[] iArrJ = g.a.d.d.m.j(17);
            int[] iArrJ2 = g.a.d.d.m.j(17);
            int i3 = 0;
            for (int i4 = 0; i4 < this.f5405a; i4++) {
                int i5 = ((i4 ^ i2) - 1) >> 31;
                for (int i6 = 0; i6 < 17; i6++) {
                    int i7 = iArrJ[i6];
                    int[] iArr = this.f5406b;
                    iArrJ[i6] = i7 ^ (iArr[i3 + i6] & i5);
                    iArrJ2[i6] = iArrJ2[i6] ^ (iArr[(i3 + 17) + i6] & i5);
                }
                i3 += 34;
            }
            return q0.this.i(new s0(iArrJ), new s0(iArrJ2), false);
        }

        @Override // g.a.d.b.f
        public int b() {
            return this.f5405a;
        }
    }

    public q0() {
        super(f5404i);
        this.j = new t0(this, null, null);
        this.f5477b = n(new BigInteger(1, g.a.f.j.d.a("01FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFC")));
        this.f5478c = n(new BigInteger(1, g.a.f.j.d.a("0051953EB9618E1C9A1F929A21A0B68540EEA2DA725B99B315F3B8B489918EF109E156193951EC7E937B1652C0BD3BB1BF073573DF883D2C34F1EF451FD46B503F00")));
        this.f5479d = new BigInteger(1, g.a.f.j.d.a("01FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFA51868783BF2F966B7FCC0148F709A5D03BB5C9B8899C47AEBB6FB71E91386409"));
        this.f5480e = BigInteger.valueOf(1L);
        this.f5481f = 2;
    }

    @Override // g.a.d.b.d
    public boolean E(int i2) {
        return i2 == 2;
    }

    @Override // g.a.d.b.d
    public g.a.d.b.d c() {
        return new q0();
    }

    @Override // g.a.d.b.d
    public g.a.d.b.f e(g.a.d.b.h[] hVarArr, int i2, int i3) {
        int[] iArr = new int[i3 * 17 * 2];
        int i4 = 0;
        for (int i5 = 0; i5 < i3; i5++) {
            g.a.d.b.h hVar = hVarArr[i2 + i5];
            g.a.d.d.m.h(17, ((s0) hVar.n()).f5419h, 0, iArr, i4);
            int i6 = i4 + 17;
            g.a.d.d.m.h(17, ((s0) hVar.o()).f5419h, 0, iArr, i6);
            i4 = i6 + 17;
        }
        return new a(i3, iArr);
    }

    @Override // g.a.d.b.d
    public g.a.d.b.h i(g.a.d.b.e eVar, g.a.d.b.e eVar2, boolean z) {
        return new t0(this, eVar, eVar2, z);
    }

    @Override // g.a.d.b.d
    public g.a.d.b.h j(g.a.d.b.e eVar, g.a.d.b.e eVar2, g.a.d.b.e[] eVarArr, boolean z) {
        return new t0(this, eVar, eVar2, eVarArr, z);
    }

    @Override // g.a.d.b.d
    public g.a.d.b.e n(BigInteger bigInteger) {
        return new s0(bigInteger);
    }

    @Override // g.a.d.b.d
    public int u() {
        return f5404i.bitLength();
    }

    @Override // g.a.d.b.d
    public g.a.d.b.h v() {
        return this.j;
    }
}
