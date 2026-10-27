package g.a.d.b.a0.c;

import g.a.d.b.d;
import java.math.BigInteger;

/* JADX INFO: loaded from: classes.dex */
public class e1 extends d.b {
    public f1 j;

    public class a implements g.a.d.b.f {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ int f5325a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ long[] f5326b;

        public a(int i2, long[] jArr) {
            this.f5325a = i2;
            this.f5326b = jArr;
        }

        @Override // g.a.d.b.f
        public g.a.d.b.h a(int i2) {
            long[] jArrH = g.a.d.d.e.h();
            long[] jArrH2 = g.a.d.d.e.h();
            int i3 = 0;
            for (int i4 = 0; i4 < this.f5325a; i4++) {
                long j = ((i4 ^ i2) - 1) >> 31;
                for (int i5 = 0; i5 < 3; i5++) {
                    long j2 = jArrH[i5];
                    long[] jArr = this.f5326b;
                    jArrH[i5] = j2 ^ (jArr[i3 + i5] & j);
                    jArrH2[i5] = jArrH2[i5] ^ (jArr[(i3 + 3) + i5] & j);
                }
                i3 += 6;
            }
            return e1.this.i(new b1(jArrH), new b1(jArrH2), false);
        }

        @Override // g.a.d.b.f
        public int b() {
            return this.f5325a;
        }
    }

    public e1() {
        super(131, 2, 3, 8);
        this.j = new f1(this, null, null);
        this.f5477b = n(new BigInteger(1, g.a.f.j.d.a("03E5A88919D7CAFCBF415F07C2176573B2")));
        this.f5478c = n(new BigInteger(1, g.a.f.j.d.a("04B8266A46C55657AC734CE38F018F2192")));
        this.f5479d = new BigInteger(1, g.a.f.j.d.a("0400000000000000016954A233049BA98F"));
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
        return new e1();
    }

    @Override // g.a.d.b.d
    public g.a.d.b.f e(g.a.d.b.h[] hVarArr, int i2, int i3) {
        long[] jArr = new long[i3 * 3 * 2];
        int i4 = 0;
        for (int i5 = 0; i5 < i3; i5++) {
            g.a.d.b.h hVar = hVarArr[i2 + i5];
            g.a.d.d.e.f(((b1) hVar.n()).f5305g, 0, jArr, i4);
            int i6 = i4 + 3;
            g.a.d.d.e.f(((b1) hVar.o()).f5305g, 0, jArr, i6);
            i4 = i6 + 3;
        }
        return new a(i3, jArr);
    }

    @Override // g.a.d.b.d
    public g.a.d.b.h i(g.a.d.b.e eVar, g.a.d.b.e eVar2, boolean z) {
        return new f1(this, eVar, eVar2, z);
    }

    @Override // g.a.d.b.d
    public g.a.d.b.h j(g.a.d.b.e eVar, g.a.d.b.e eVar2, g.a.d.b.e[] eVarArr, boolean z) {
        return new f1(this, eVar, eVar2, eVarArr, z);
    }

    @Override // g.a.d.b.d
    public g.a.d.b.e n(BigInteger bigInteger) {
        return new b1(bigInteger);
    }

    @Override // g.a.d.b.d
    public int u() {
        return 131;
    }

    @Override // g.a.d.b.d
    public g.a.d.b.h v() {
        return this.j;
    }
}
