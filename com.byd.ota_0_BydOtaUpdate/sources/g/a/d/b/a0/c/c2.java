package g.a.d.b.a0.c;

import g.a.d.b.d;
import java.math.BigInteger;

/* JADX INFO: loaded from: classes.dex */
public class c2 extends d.b {
    public d2 j;

    public class a implements g.a.d.b.f {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ int f5314a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ long[] f5315b;

        public a(int i2, long[] jArr) {
            this.f5314a = i2;
            this.f5315b = jArr;
        }

        @Override // g.a.d.b.f
        public g.a.d.b.h a(int i2) {
            long[] jArrI = g.a.d.d.g.i();
            long[] jArrI2 = g.a.d.d.g.i();
            int i3 = 0;
            for (int i4 = 0; i4 < this.f5314a; i4++) {
                long j = ((i4 ^ i2) - 1) >> 31;
                for (int i5 = 0; i5 < 4; i5++) {
                    long j2 = jArrI[i5];
                    long[] jArr = this.f5315b;
                    jArrI[i5] = j2 ^ (jArr[i3 + i5] & j);
                    jArrI2[i5] = jArrI2[i5] ^ (jArr[(i3 + 4) + i5] & j);
                }
                i3 += 8;
            }
            return c2.this.i(new b2(jArrI), new b2(jArrI2), false);
        }

        @Override // g.a.d.b.f
        public int b() {
            return this.f5314a;
        }
    }

    public c2() {
        super(239, 158, 0, 0);
        this.j = new d2(this, null, null);
        this.f5477b = n(BigInteger.valueOf(0L));
        this.f5478c = n(BigInteger.valueOf(1L));
        this.f5479d = new BigInteger(1, g.a.f.j.d.a("2000000000000000000000000000005A79FEC67CB6E91F1C1DA800E478A5"));
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
        return new c2();
    }

    @Override // g.a.d.b.d
    public g.a.d.b.f e(g.a.d.b.h[] hVarArr, int i2, int i3) {
        long[] jArr = new long[i3 * 4 * 2];
        int i4 = 0;
        for (int i5 = 0; i5 < i3; i5++) {
            g.a.d.b.h hVar = hVarArr[i2 + i5];
            g.a.d.d.g.g(((b2) hVar.n()).f5306g, 0, jArr, i4);
            int i6 = i4 + 4;
            g.a.d.d.g.g(((b2) hVar.o()).f5306g, 0, jArr, i6);
            i4 = i6 + 4;
        }
        return new a(i3, jArr);
    }

    @Override // g.a.d.b.d
    public g.a.d.b.g f() {
        return new g.a.d.b.x();
    }

    @Override // g.a.d.b.d
    public g.a.d.b.h i(g.a.d.b.e eVar, g.a.d.b.e eVar2, boolean z) {
        return new d2(this, eVar, eVar2, z);
    }

    @Override // g.a.d.b.d
    public g.a.d.b.h j(g.a.d.b.e eVar, g.a.d.b.e eVar2, g.a.d.b.e[] eVarArr, boolean z) {
        return new d2(this, eVar, eVar2, eVarArr, z);
    }

    @Override // g.a.d.b.d
    public g.a.d.b.e n(BigInteger bigInteger) {
        return new b2(bigInteger);
    }

    @Override // g.a.d.b.d
    public int u() {
        return 239;
    }

    @Override // g.a.d.b.d
    public g.a.d.b.h v() {
        return this.j;
    }
}
