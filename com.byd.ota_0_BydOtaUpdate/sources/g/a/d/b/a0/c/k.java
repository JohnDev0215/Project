package g.a.d.b.a0.c;

import g.a.d.b.d;
import java.math.BigInteger;

/* JADX INFO: loaded from: classes.dex */
public class k extends d.c {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public static final BigInteger f5361i = new BigInteger(1, g.a.f.j.d.a("FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFEFFFFAC73"));
    public n j;

    public class a implements g.a.d.b.f {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ int f5362a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ int[] f5363b;

        public a(int i2, int[] iArr) {
            this.f5362a = i2;
            this.f5363b = iArr;
        }

        @Override // g.a.d.b.f
        public g.a.d.b.h a(int i2) {
            int[] iArrD = g.a.d.d.d.d();
            int[] iArrD2 = g.a.d.d.d.d();
            int i3 = 0;
            for (int i4 = 0; i4 < this.f5362a; i4++) {
                int i5 = ((i4 ^ i2) - 1) >> 31;
                for (int i6 = 0; i6 < 5; i6++) {
                    int i7 = iArrD[i6];
                    int[] iArr = this.f5363b;
                    iArrD[i6] = i7 ^ (iArr[i3 + i6] & i5);
                    iArrD2[i6] = iArrD2[i6] ^ (iArr[(i3 + 5) + i6] & i5);
                }
                i3 += 10;
            }
            return k.this.i(new m(iArrD), new m(iArrD2), false);
        }

        @Override // g.a.d.b.f
        public int b() {
            return this.f5362a;
        }
    }

    public k() {
        super(f5361i);
        this.j = new n(this, null, null);
        this.f5477b = n(new BigInteger(1, g.a.f.j.d.a("FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFEFFFFAC70")));
        this.f5478c = n(new BigInteger(1, g.a.f.j.d.a("B4E134D3FB59EB8BAB57274904664D5AF50388BA")));
        this.f5479d = new BigInteger(1, g.a.f.j.d.a("0100000000000000000000351EE786A818F3A1A16B"));
        this.f5480e = BigInteger.valueOf(1L);
        this.f5481f = 2;
    }

    @Override // g.a.d.b.d
    public boolean E(int i2) {
        return i2 == 2;
    }

    @Override // g.a.d.b.d
    public g.a.d.b.d c() {
        return new k();
    }

    @Override // g.a.d.b.d
    public g.a.d.b.f e(g.a.d.b.h[] hVarArr, int i2, int i3) {
        int[] iArr = new int[i3 * 5 * 2];
        int i4 = 0;
        for (int i5 = 0; i5 < i3; i5++) {
            g.a.d.b.h hVar = hVarArr[i2 + i5];
            g.a.d.d.d.c(((m) hVar.n()).f5375h, 0, iArr, i4);
            int i6 = i4 + 5;
            g.a.d.d.d.c(((m) hVar.o()).f5375h, 0, iArr, i6);
            i4 = i6 + 5;
        }
        return new a(i3, iArr);
    }

    @Override // g.a.d.b.d
    public g.a.d.b.h i(g.a.d.b.e eVar, g.a.d.b.e eVar2, boolean z) {
        return new n(this, eVar, eVar2, z);
    }

    @Override // g.a.d.b.d
    public g.a.d.b.h j(g.a.d.b.e eVar, g.a.d.b.e eVar2, g.a.d.b.e[] eVarArr, boolean z) {
        return new n(this, eVar, eVar2, eVarArr, z);
    }

    @Override // g.a.d.b.d
    public g.a.d.b.e n(BigInteger bigInteger) {
        return new m(bigInteger);
    }

    @Override // g.a.d.b.d
    public int u() {
        return f5361i.bitLength();
    }

    @Override // g.a.d.b.d
    public g.a.d.b.h v() {
        return this.j;
    }
}
