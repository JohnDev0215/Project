package g.a.d.b.a0.c;

import g.a.d.b.d;
import java.math.BigInteger;

/* JADX INFO: loaded from: classes.dex */
public class g extends d.c {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public static final BigInteger f5333i = new BigInteger(1, g.a.f.j.d.a("FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF7FFFFFFF"));
    public j j;

    public class a implements g.a.d.b.f {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ int f5334a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ int[] f5335b;

        public a(int i2, int[] iArr) {
            this.f5334a = i2;
            this.f5335b = iArr;
        }

        @Override // g.a.d.b.f
        public g.a.d.b.h a(int i2) {
            int[] iArrD = g.a.d.d.d.d();
            int[] iArrD2 = g.a.d.d.d.d();
            int i3 = 0;
            for (int i4 = 0; i4 < this.f5334a; i4++) {
                int i5 = ((i4 ^ i2) - 1) >> 31;
                for (int i6 = 0; i6 < 5; i6++) {
                    int i7 = iArrD[i6];
                    int[] iArr = this.f5335b;
                    iArrD[i6] = i7 ^ (iArr[i3 + i6] & i5);
                    iArrD2[i6] = iArrD2[i6] ^ (iArr[(i3 + 5) + i6] & i5);
                }
                i3 += 10;
            }
            return g.this.i(new i(iArrD), new i(iArrD2), false);
        }

        @Override // g.a.d.b.f
        public int b() {
            return this.f5334a;
        }
    }

    public g() {
        super(f5333i);
        this.j = new j(this, null, null);
        this.f5477b = n(new BigInteger(1, g.a.f.j.d.a("FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF7FFFFFFC")));
        this.f5478c = n(new BigInteger(1, g.a.f.j.d.a("1C97BEFC54BD7A8B65ACF89F81D4D4ADC565FA45")));
        this.f5479d = new BigInteger(1, g.a.f.j.d.a("0100000000000000000001F4C8F927AED3CA752257"));
        this.f5480e = BigInteger.valueOf(1L);
        this.f5481f = 2;
    }

    @Override // g.a.d.b.d
    public boolean E(int i2) {
        return i2 == 2;
    }

    @Override // g.a.d.b.d
    public g.a.d.b.d c() {
        return new g();
    }

    @Override // g.a.d.b.d
    public g.a.d.b.f e(g.a.d.b.h[] hVarArr, int i2, int i3) {
        int[] iArr = new int[i3 * 5 * 2];
        int i4 = 0;
        for (int i5 = 0; i5 < i3; i5++) {
            g.a.d.b.h hVar = hVarArr[i2 + i5];
            g.a.d.d.d.c(((i) hVar.n()).f5348h, 0, iArr, i4);
            int i6 = i4 + 5;
            g.a.d.d.d.c(((i) hVar.o()).f5348h, 0, iArr, i6);
            i4 = i6 + 5;
        }
        return new a(i3, iArr);
    }

    @Override // g.a.d.b.d
    public g.a.d.b.h i(g.a.d.b.e eVar, g.a.d.b.e eVar2, boolean z) {
        return new j(this, eVar, eVar2, z);
    }

    @Override // g.a.d.b.d
    public g.a.d.b.h j(g.a.d.b.e eVar, g.a.d.b.e eVar2, g.a.d.b.e[] eVarArr, boolean z) {
        return new j(this, eVar, eVar2, eVarArr, z);
    }

    @Override // g.a.d.b.d
    public g.a.d.b.e n(BigInteger bigInteger) {
        return new i(bigInteger);
    }

    @Override // g.a.d.b.d
    public int u() {
        return f5333i.bitLength();
    }

    @Override // g.a.d.b.d
    public g.a.d.b.h v() {
        return this.j;
    }
}
