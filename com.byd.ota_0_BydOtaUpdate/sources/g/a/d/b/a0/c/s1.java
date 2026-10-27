package g.a.d.b.a0.c;

import g.a.d.b.d;
import java.math.BigInteger;

/* JADX INFO: loaded from: classes.dex */
public class s1 extends d.b {
    public t1 j;

    public class a implements g.a.d.b.f {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ int f5420a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ long[] f5421b;

        public a(int i2, long[] jArr) {
            this.f5420a = i2;
            this.f5421b = jArr;
        }

        @Override // g.a.d.b.f
        public g.a.d.b.h a(int i2) {
            long[] jArrI = g.a.d.d.g.i();
            long[] jArrI2 = g.a.d.d.g.i();
            int i3 = 0;
            for (int i4 = 0; i4 < this.f5420a; i4++) {
                long j = ((i4 ^ i2) - 1) >> 31;
                for (int i5 = 0; i5 < 4; i5++) {
                    long j2 = jArrI[i5];
                    long[] jArr = this.f5421b;
                    jArrI[i5] = j2 ^ (jArr[i3 + i5] & j);
                    jArrI2[i5] = jArrI2[i5] ^ (jArr[(i3 + 4) + i5] & j);
                }
                i3 += 8;
            }
            return s1.this.i(new p1(jArrI), new p1(jArrI2), false);
        }

        @Override // g.a.d.b.f
        public int b() {
            return this.f5420a;
        }
    }

    public s1() {
        super(193, 15, 0, 0);
        this.j = new t1(this, null, null);
        this.f5477b = n(new BigInteger(1, g.a.f.j.d.a("0163F35A5137C2CE3EA6ED8667190B0BC43ECD69977702709B")));
        this.f5478c = n(new BigInteger(1, g.a.f.j.d.a("00C9BB9E8927D4D64C377E2AB2856A5B16E3EFB7F61D4316AE")));
        this.f5479d = new BigInteger(1, g.a.f.j.d.a("010000000000000000000000015AAB561B005413CCD4EE99D5"));
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
        return new s1();
    }

    @Override // g.a.d.b.d
    public g.a.d.b.f e(g.a.d.b.h[] hVarArr, int i2, int i3) {
        long[] jArr = new long[i3 * 4 * 2];
        int i4 = 0;
        for (int i5 = 0; i5 < i3; i5++) {
            g.a.d.b.h hVar = hVarArr[i2 + i5];
            g.a.d.d.g.g(((p1) hVar.n()).f5401g, 0, jArr, i4);
            int i6 = i4 + 4;
            g.a.d.d.g.g(((p1) hVar.o()).f5401g, 0, jArr, i6);
            i4 = i6 + 4;
        }
        return new a(i3, jArr);
    }

    @Override // g.a.d.b.d
    public g.a.d.b.h i(g.a.d.b.e eVar, g.a.d.b.e eVar2, boolean z) {
        return new t1(this, eVar, eVar2, z);
    }

    @Override // g.a.d.b.d
    public g.a.d.b.h j(g.a.d.b.e eVar, g.a.d.b.e eVar2, g.a.d.b.e[] eVarArr, boolean z) {
        return new t1(this, eVar, eVar2, eVarArr, z);
    }

    @Override // g.a.d.b.d
    public g.a.d.b.e n(BigInteger bigInteger) {
        return new p1(bigInteger);
    }

    @Override // g.a.d.b.d
    public int u() {
        return 193;
    }

    @Override // g.a.d.b.d
    public g.a.d.b.h v() {
        return this.j;
    }
}
