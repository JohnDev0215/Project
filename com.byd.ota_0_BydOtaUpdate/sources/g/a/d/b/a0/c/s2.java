package g.a.d.b.a0.c;

import g.a.d.b.d;
import java.math.BigInteger;

/* JADX INFO: loaded from: classes.dex */
public class s2 extends d.b {
    public t2 j;

    public class a implements g.a.d.b.f {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ int f5423a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ long[] f5424b;

        public a(int i2, long[] jArr) {
            this.f5423a = i2;
            this.f5424b = jArr;
        }

        @Override // g.a.d.b.f
        public g.a.d.b.h a(int i2) {
            long[] jArrB = g.a.d.d.l.b();
            long[] jArrB2 = g.a.d.d.l.b();
            int i3 = 0;
            for (int i4 = 0; i4 < this.f5423a; i4++) {
                long j = ((i4 ^ i2) - 1) >> 31;
                for (int i5 = 0; i5 < 9; i5++) {
                    long j2 = jArrB[i5];
                    long[] jArr = this.f5424b;
                    jArrB[i5] = j2 ^ (jArr[i3 + i5] & j);
                    jArrB2[i5] = jArrB2[i5] ^ (jArr[(i3 + 9) + i5] & j);
                }
                i3 += 18;
            }
            return s2.this.i(new r2(jArrB), new r2(jArrB2), false);
        }

        @Override // g.a.d.b.f
        public int b() {
            return this.f5423a;
        }
    }

    public s2() {
        super(571, 2, 5, 10);
        this.j = new t2(this, null, null);
        this.f5477b = n(BigInteger.valueOf(0L));
        this.f5478c = n(BigInteger.valueOf(1L));
        this.f5479d = new BigInteger(1, g.a.f.j.d.a("020000000000000000000000000000000000000000000000000000000000000000000000131850E1F19A63E4B391A8DB917F4138B630D84BE5D639381E91DEB45CFE778F637C1001"));
        this.f5480e = BigInteger.valueOf(4L);
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
        return new s2();
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
    public g.a.d.b.g f() {
        return new g.a.d.b.x();
    }

    @Override // g.a.d.b.d
    public g.a.d.b.h i(g.a.d.b.e eVar, g.a.d.b.e eVar2, boolean z) {
        return new t2(this, eVar, eVar2, z);
    }

    @Override // g.a.d.b.d
    public g.a.d.b.h j(g.a.d.b.e eVar, g.a.d.b.e eVar2, g.a.d.b.e[] eVarArr, boolean z) {
        return new t2(this, eVar, eVar2, eVarArr, z);
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
        return this.j;
    }
}
