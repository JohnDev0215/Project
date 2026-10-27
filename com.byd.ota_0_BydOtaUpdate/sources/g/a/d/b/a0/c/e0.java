package g.a.d.b.a0.c;

import g.a.d.b.d;
import java.math.BigInteger;

/* JADX INFO: loaded from: classes.dex */
public class e0 extends d.c {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public static final BigInteger f5321i = new BigInteger(1, g.a.f.j.d.a("FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFEFFFFFC2F"));
    public h0 j;

    public class a implements g.a.d.b.f {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ int f5322a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ int[] f5323b;

        public a(int i2, int[] iArr) {
            this.f5322a = i2;
            this.f5323b = iArr;
        }

        @Override // g.a.d.b.f
        public g.a.d.b.h a(int i2) {
            int[] iArrH = g.a.d.d.g.h();
            int[] iArrH2 = g.a.d.d.g.h();
            int i3 = 0;
            for (int i4 = 0; i4 < this.f5322a; i4++) {
                int i5 = ((i4 ^ i2) - 1) >> 31;
                for (int i6 = 0; i6 < 8; i6++) {
                    int i7 = iArrH[i6];
                    int[] iArr = this.f5323b;
                    iArrH[i6] = i7 ^ (iArr[i3 + i6] & i5);
                    iArrH2[i6] = iArrH2[i6] ^ (iArr[(i3 + 8) + i6] & i5);
                }
                i3 += 16;
            }
            return e0.this.i(new g0(iArrH), new g0(iArrH2), false);
        }

        @Override // g.a.d.b.f
        public int b() {
            return this.f5322a;
        }
    }

    public e0() {
        super(f5321i);
        this.j = new h0(this, null, null);
        this.f5477b = n(g.a.d.b.c.f5470a);
        this.f5478c = n(BigInteger.valueOf(7L));
        this.f5479d = new BigInteger(1, g.a.f.j.d.a("FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFEBAAEDCE6AF48A03BBFD25E8CD0364141"));
        this.f5480e = BigInteger.valueOf(1L);
        this.f5481f = 2;
    }

    @Override // g.a.d.b.d
    public boolean E(int i2) {
        return i2 == 2;
    }

    @Override // g.a.d.b.d
    public g.a.d.b.d c() {
        return new e0();
    }

    @Override // g.a.d.b.d
    public g.a.d.b.f e(g.a.d.b.h[] hVarArr, int i2, int i3) {
        int[] iArr = new int[i3 * 8 * 2];
        int i4 = 0;
        for (int i5 = 0; i5 < i3; i5++) {
            g.a.d.b.h hVar = hVarArr[i2 + i5];
            g.a.d.d.g.f(((g0) hVar.n()).f5338h, 0, iArr, i4);
            int i6 = i4 + 8;
            g.a.d.d.g.f(((g0) hVar.o()).f5338h, 0, iArr, i6);
            i4 = i6 + 8;
        }
        return new a(i3, iArr);
    }

    @Override // g.a.d.b.d
    public g.a.d.b.h i(g.a.d.b.e eVar, g.a.d.b.e eVar2, boolean z) {
        return new h0(this, eVar, eVar2, z);
    }

    @Override // g.a.d.b.d
    public g.a.d.b.h j(g.a.d.b.e eVar, g.a.d.b.e eVar2, g.a.d.b.e[] eVarArr, boolean z) {
        return new h0(this, eVar, eVar2, eVarArr, z);
    }

    @Override // g.a.d.b.d
    public g.a.d.b.e n(BigInteger bigInteger) {
        return new g0(bigInteger);
    }

    @Override // g.a.d.b.d
    public int u() {
        return f5321i.bitLength();
    }

    @Override // g.a.d.b.d
    public g.a.d.b.h v() {
        return this.j;
    }
}
