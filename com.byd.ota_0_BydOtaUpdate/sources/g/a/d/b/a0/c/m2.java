package g.a.d.b.a0.c;

import g.a.d.b.d;
import java.math.BigInteger;

/* JADX INFO: loaded from: classes.dex */
public class m2 extends d.b {
    public n2 j;

    public class a implements g.a.d.b.f {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ int f5383a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ long[] f5384b;

        public a(int i2, long[] jArr) {
            this.f5383a = i2;
            this.f5384b = jArr;
        }

        @Override // g.a.d.b.f
        public g.a.d.b.h a(int i2) {
            long[] jArrB = g.a.d.d.j.b();
            long[] jArrB2 = g.a.d.d.j.b();
            int i3 = 0;
            for (int i4 = 0; i4 < this.f5383a; i4++) {
                long j = ((i4 ^ i2) - 1) >> 31;
                for (int i5 = 0; i5 < 7; i5++) {
                    long j2 = jArrB[i5];
                    long[] jArr = this.f5384b;
                    jArrB[i5] = j2 ^ (jArr[i3 + i5] & j);
                    jArrB2[i5] = jArrB2[i5] ^ (jArr[(i3 + 7) + i5] & j);
                }
                i3 += 14;
            }
            return m2.this.i(new l2(jArrB), new l2(jArrB2), false);
        }

        @Override // g.a.d.b.f
        public int b() {
            return this.f5383a;
        }
    }

    public m2() {
        super(409, 87, 0, 0);
        this.j = new n2(this, null, null);
        this.f5477b = n(BigInteger.valueOf(0L));
        this.f5478c = n(BigInteger.valueOf(1L));
        this.f5479d = new BigInteger(1, g.a.f.j.d.a("7FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFE5F83B2D4EA20400EC4557D5ED3E3E7CA5B4B5C83B8E01E5FCF"));
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
        return new m2();
    }

    @Override // g.a.d.b.d
    public g.a.d.b.f e(g.a.d.b.h[] hVarArr, int i2, int i3) {
        long[] jArr = new long[i3 * 7 * 2];
        int i4 = 0;
        for (int i5 = 0; i5 < i3; i5++) {
            g.a.d.b.h hVar = hVarArr[i2 + i5];
            g.a.d.d.j.a(((l2) hVar.n()).f5373g, 0, jArr, i4);
            int i6 = i4 + 7;
            g.a.d.d.j.a(((l2) hVar.o()).f5373g, 0, jArr, i6);
            i4 = i6 + 7;
        }
        return new a(i3, jArr);
    }

    @Override // g.a.d.b.d
    public g.a.d.b.g f() {
        return new g.a.d.b.x();
    }

    @Override // g.a.d.b.d
    public g.a.d.b.h i(g.a.d.b.e eVar, g.a.d.b.e eVar2, boolean z) {
        return new n2(this, eVar, eVar2, z);
    }

    @Override // g.a.d.b.d
    public g.a.d.b.h j(g.a.d.b.e eVar, g.a.d.b.e eVar2, g.a.d.b.e[] eVarArr, boolean z) {
        return new n2(this, eVar, eVar2, eVarArr, z);
    }

    @Override // g.a.d.b.d
    public g.a.d.b.e n(BigInteger bigInteger) {
        return new l2(bigInteger);
    }

    @Override // g.a.d.b.d
    public int u() {
        return 409;
    }

    @Override // g.a.d.b.d
    public g.a.d.b.h v() {
        return this.j;
    }
}
