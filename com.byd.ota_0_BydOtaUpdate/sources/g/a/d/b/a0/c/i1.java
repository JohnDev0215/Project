package g.a.d.b.a0.c;

import g.a.d.b.d;
import java.math.BigInteger;

/* JADX INFO: loaded from: classes.dex */
public class i1 extends d.b {
    public j1 j;

    public class a implements g.a.d.b.f {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ int f5353a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ long[] f5354b;

        public a(int i2, long[] jArr) {
            this.f5353a = i2;
            this.f5354b = jArr;
        }

        @Override // g.a.d.b.f
        public g.a.d.b.h a(int i2) {
            long[] jArrH = g.a.d.d.e.h();
            long[] jArrH2 = g.a.d.d.e.h();
            int i3 = 0;
            for (int i4 = 0; i4 < this.f5353a; i4++) {
                long j = ((i4 ^ i2) - 1) >> 31;
                for (int i5 = 0; i5 < 3; i5++) {
                    long j2 = jArrH[i5];
                    long[] jArr = this.f5354b;
                    jArrH[i5] = j2 ^ (jArr[i3 + i5] & j);
                    jArrH2[i5] = jArrH2[i5] ^ (jArr[(i3 + 3) + i5] & j);
                }
                i3 += 6;
            }
            return i1.this.i(new h1(jArrH), new h1(jArrH2), false);
        }

        @Override // g.a.d.b.f
        public int b() {
            return this.f5353a;
        }
    }

    public i1() {
        super(163, 3, 6, 7);
        this.j = new j1(this, null, null);
        g.a.d.b.e eVarN = n(BigInteger.valueOf(1L));
        this.f5477b = eVarN;
        this.f5478c = eVarN;
        this.f5479d = new BigInteger(1, g.a.f.j.d.a("04000000000000000000020108A2E0CC0D99F8A5EF"));
        this.f5480e = BigInteger.valueOf(2L);
        this.f5481f = 6;
    }

    @Override // g.a.d.b.d
    public boolean E(int i2) {
        return i2 == 6;
    }

    @Override // g.a.d.b.d.b
    public boolean I() {
        return true;
    }

    @Override // g.a.d.b.d
    public g.a.d.b.d c() {
        return new i1();
    }

    @Override // g.a.d.b.d
    public g.a.d.b.f e(g.a.d.b.h[] hVarArr, int i2, int i3) {
        long[] jArr = new long[i3 * 3 * 2];
        int i4 = 0;
        for (int i5 = 0; i5 < i3; i5++) {
            g.a.d.b.h hVar = hVarArr[i2 + i5];
            g.a.d.d.e.f(((h1) hVar.n()).f5346g, 0, jArr, i4);
            int i6 = i4 + 3;
            g.a.d.d.e.f(((h1) hVar.o()).f5346g, 0, jArr, i6);
            i4 = i6 + 3;
        }
        return new a(i3, jArr);
    }

    @Override // g.a.d.b.d
    public g.a.d.b.g f() {
        return new g.a.d.b.x();
    }

    @Override // g.a.d.b.d
    public g.a.d.b.h i(g.a.d.b.e eVar, g.a.d.b.e eVar2, boolean z) {
        return new j1(this, eVar, eVar2, z);
    }

    @Override // g.a.d.b.d
    public g.a.d.b.h j(g.a.d.b.e eVar, g.a.d.b.e eVar2, g.a.d.b.e[] eVarArr, boolean z) {
        return new j1(this, eVar, eVar2, eVarArr, z);
    }

    @Override // g.a.d.b.d
    public g.a.d.b.e n(BigInteger bigInteger) {
        return new h1(bigInteger);
    }

    @Override // g.a.d.b.d
    public int u() {
        return 163;
    }

    @Override // g.a.d.b.d
    public g.a.d.b.h v() {
        return this.j;
    }
}
