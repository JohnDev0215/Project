package g.a.d.b.a0.c;

import g.a.d.b.d;
import java.math.BigInteger;

/* JADX INFO: loaded from: classes.dex */
public class i2 extends d.b {
    public j2 j;

    public class a implements g.a.d.b.f {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ int f5356a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ long[] f5357b;

        public a(int i2, long[] jArr) {
            this.f5356a = i2;
            this.f5357b = jArr;
        }

        @Override // g.a.d.b.f
        public g.a.d.b.h a(int i2) {
            long[] jArrB = g.a.d.d.h.b();
            long[] jArrB2 = g.a.d.d.h.b();
            int i3 = 0;
            for (int i4 = 0; i4 < this.f5356a; i4++) {
                long j = ((i4 ^ i2) - 1) >> 31;
                for (int i5 = 0; i5 < 5; i5++) {
                    long j2 = jArrB[i5];
                    long[] jArr = this.f5357b;
                    jArrB[i5] = j2 ^ (jArr[i3 + i5] & j);
                    jArrB2[i5] = jArrB2[i5] ^ (jArr[(i3 + 5) + i5] & j);
                }
                i3 += 10;
            }
            return i2.this.i(new f2(jArrB), new f2(jArrB2), false);
        }

        @Override // g.a.d.b.f
        public int b() {
            return this.f5356a;
        }
    }

    public i2() {
        super(283, 5, 7, 12);
        this.j = new j2(this, null, null);
        this.f5477b = n(BigInteger.valueOf(1L));
        this.f5478c = n(new BigInteger(1, g.a.f.j.d.a("027B680AC8B8596DA5A4AF8A19A0303FCA97FD7645309FA2A581485AF6263E313B79A2F5")));
        this.f5479d = new BigInteger(1, g.a.f.j.d.a("03FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFEF90399660FC938A90165B042A7CEFADB307"));
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
        return new i2();
    }

    @Override // g.a.d.b.d
    public g.a.d.b.f e(g.a.d.b.h[] hVarArr, int i2, int i3) {
        long[] jArr = new long[i3 * 5 * 2];
        int i4 = 0;
        for (int i5 = 0; i5 < i3; i5++) {
            g.a.d.b.h hVar = hVarArr[i2 + i5];
            g.a.d.d.h.a(((f2) hVar.n()).f5332g, 0, jArr, i4);
            int i6 = i4 + 5;
            g.a.d.d.h.a(((f2) hVar.o()).f5332g, 0, jArr, i6);
            i4 = i6 + 5;
        }
        return new a(i3, jArr);
    }

    @Override // g.a.d.b.d
    public g.a.d.b.h i(g.a.d.b.e eVar, g.a.d.b.e eVar2, boolean z) {
        return new j2(this, eVar, eVar2, z);
    }

    @Override // g.a.d.b.d
    public g.a.d.b.h j(g.a.d.b.e eVar, g.a.d.b.e eVar2, g.a.d.b.e[] eVarArr, boolean z) {
        return new j2(this, eVar, eVar2, eVarArr, z);
    }

    @Override // g.a.d.b.d
    public g.a.d.b.e n(BigInteger bigInteger) {
        return new f2(bigInteger);
    }

    @Override // g.a.d.b.d
    public int u() {
        return 283;
    }

    @Override // g.a.d.b.d
    public g.a.d.b.h v() {
        return this.j;
    }
}
