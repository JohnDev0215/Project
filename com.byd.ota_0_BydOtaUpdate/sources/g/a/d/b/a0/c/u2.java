package g.a.d.b.a0.c;

import g.a.d.b.d;
import java.math.BigInteger;

/* JADX INFO: loaded from: classes.dex */
public class u2 extends d.b {
    public static final r2 j;
    public static final r2 k;
    public v2 l;

    public class a implements g.a.d.b.f {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ int f5431a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ long[] f5432b;

        public a(int i2, long[] jArr) {
            this.f5431a = i2;
            this.f5432b = jArr;
        }

        @Override // g.a.d.b.f
        public g.a.d.b.h a(int i2) {
            long[] jArrB = g.a.d.d.l.b();
            long[] jArrB2 = g.a.d.d.l.b();
            int i3 = 0;
            for (int i4 = 0; i4 < this.f5431a; i4++) {
                long j = ((i4 ^ i2) - 1) >> 31;
                for (int i5 = 0; i5 < 9; i5++) {
                    long j2 = jArrB[i5];
                    long[] jArr = this.f5432b;
                    jArrB[i5] = j2 ^ (jArr[i3 + i5] & j);
                    jArrB2[i5] = jArrB2[i5] ^ (jArr[(i3 + 9) + i5] & j);
                }
                i3 += 18;
            }
            return u2.this.i(new r2(jArrB), new r2(jArrB2), false);
        }

        @Override // g.a.d.b.f
        public int b() {
            return this.f5431a;
        }
    }

    static {
        r2 r2Var = new r2(new BigInteger(1, g.a.f.j.d.a("02F40E7E2221F295DE297117B7F3D62F5C6A97FFCB8CEFF1CD6BA8CE4A9A18AD84FFABBD8EFA59332BE7AD6756A66E294AFD185A78FF12AA520E4DE739BACA0C7FFEFF7F2955727A")));
        j = r2Var;
        k = (r2) r2Var.n();
    }

    public u2() {
        super(571, 2, 5, 10);
        this.l = new v2(this, null, null);
        this.f5477b = n(BigInteger.valueOf(1L));
        this.f5478c = j;
        this.f5479d = new BigInteger(1, g.a.f.j.d.a("03FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFE661CE18FF55987308059B186823851EC7DD9CA1161DE93D5174D66E8382E9BB2FE84E47"));
        this.f5480e = BigInteger.valueOf(2L);
        this.f5481f = 6;
    }

    @Override // g.a.d.b.d
    public boolean E(int i2) {
        return i2 == 6;
    }

    @Override // g.a.d.b.d.b
    public boolean I() {
        return false;
    }

    @Override // g.a.d.b.d
    public g.a.d.b.d c() {
        return new u2();
    }

    @Override // g.a.d.b.d
    public g.a.d.b.f e(g.a.d.b.h[] hVarArr, int i2, int i3) {
        long[] jArr = new long[i3 * 9 * 2];
        int i4 = 0;
        for (int i5 = 0; i5 < i3; i5++) {
            g.a.d.b.h hVar = hVarArr[i2 + i5];
            g.a.d.d.l.a(((r2) hVar.n()).f5413g, 0, jArr, i4);
            int i6 = i4 + 9;
            g.a.d.d.l.a(((r2) hVar.o()).f5413g, 0, jArr, i6);
            i4 = i6 + 9;
        }
        return new a(i3, jArr);
    }

    @Override // g.a.d.b.d
    public g.a.d.b.h i(g.a.d.b.e eVar, g.a.d.b.e eVar2, boolean z) {
        return new v2(this, eVar, eVar2, z);
    }

    @Override // g.a.d.b.d
    public g.a.d.b.h j(g.a.d.b.e eVar, g.a.d.b.e eVar2, g.a.d.b.e[] eVarArr, boolean z) {
        return new v2(this, eVar, eVar2, eVarArr, z);
    }

    @Override // g.a.d.b.d
    public g.a.d.b.e n(BigInteger bigInteger) {
        return new r2(bigInteger);
    }

    @Override // g.a.d.b.d
    public int u() {
        return 571;
    }

    @Override // g.a.d.b.d
    public g.a.d.b.h v() {
        return this.l;
    }
}
