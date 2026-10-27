package g.a.d.b.a0.c;

import g.a.d.b.d;
import java.math.BigInteger;

/* JADX INFO: loaded from: classes.dex */
public class y0 extends d.b {
    public z0 j;

    public class a implements g.a.d.b.f {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ int f5452a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ long[] f5453b;

        public a(int i2, long[] jArr) {
            this.f5452a = i2;
            this.f5453b = jArr;
        }

        @Override // g.a.d.b.f
        public g.a.d.b.h a(int i2) {
            long[] jArrF = g.a.d.d.c.f();
            long[] jArrF2 = g.a.d.d.c.f();
            int i3 = 0;
            for (int i4 = 0; i4 < this.f5452a; i4++) {
                long j = ((i4 ^ i2) - 1) >> 31;
                for (int i5 = 0; i5 < 2; i5++) {
                    long j2 = jArrF[i5];
                    long[] jArr = this.f5453b;
                    jArrF[i5] = j2 ^ (jArr[i3 + i5] & j);
                    jArrF2[i5] = jArrF2[i5] ^ (jArr[(i3 + 2) + i5] & j);
                }
                i3 += 4;
            }
            return y0.this.i(new v0(jArrF), new v0(jArrF2), false);
        }

        @Override // g.a.d.b.f
        public int b() {
            return this.f5452a;
        }
    }

    public y0() {
        super(113, 9, 0, 0);
        this.j = new z0(this, null, null);
        this.f5477b = n(new BigInteger(1, g.a.f.j.d.a("00689918DBEC7E5A0DD6DFC0AA55C7")));
        this.f5478c = n(new BigInteger(1, g.a.f.j.d.a("0095E9A9EC9B297BD4BF36E059184F")));
        this.f5479d = new BigInteger(1, g.a.f.j.d.a("010000000000000108789B2496AF93"));
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
        return new y0();
    }

    @Override // g.a.d.b.d
    public g.a.d.b.f e(g.a.d.b.h[] hVarArr, int i2, int i3) {
        long[] jArr = new long[i3 * 2 * 2];
        int i4 = 0;
        for (int i5 = 0; i5 < i3; i5++) {
            g.a.d.b.h hVar = hVarArr[i2 + i5];
            g.a.d.d.c.d(((v0) hVar.n()).f5434g, 0, jArr, i4);
            int i6 = i4 + 2;
            g.a.d.d.c.d(((v0) hVar.o()).f5434g, 0, jArr, i6);
            i4 = i6 + 2;
        }
        return new a(i3, jArr);
    }

    @Override // g.a.d.b.d
    public g.a.d.b.h i(g.a.d.b.e eVar, g.a.d.b.e eVar2, boolean z) {
        return new z0(this, eVar, eVar2, z);
    }

    @Override // g.a.d.b.d
    public g.a.d.b.h j(g.a.d.b.e eVar, g.a.d.b.e eVar2, g.a.d.b.e[] eVarArr, boolean z) {
        return new z0(this, eVar, eVar2, eVarArr, z);
    }

    @Override // g.a.d.b.d
    public g.a.d.b.e n(BigInteger bigInteger) {
        return new v0(bigInteger);
    }

    @Override // g.a.d.b.d
    public int u() {
        return 113;
    }

    @Override // g.a.d.b.d
    public g.a.d.b.h v() {
        return this.j;
    }
}
