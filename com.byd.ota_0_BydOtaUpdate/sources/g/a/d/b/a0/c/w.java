package g.a.d.b.a0.c;

import g.a.d.b.d;
import java.math.BigInteger;

/* JADX INFO: loaded from: classes.dex */
public class w extends d.c {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public static final BigInteger f5436i = new BigInteger(1, g.a.f.j.d.a("FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFEFFFFE56D"));
    public z j;

    public class a implements g.a.d.b.f {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ int f5437a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ int[] f5438b;

        public a(int i2, int[] iArr) {
            this.f5437a = i2;
            this.f5438b = iArr;
        }

        @Override // g.a.d.b.f
        public g.a.d.b.h a(int i2) {
            int[] iArrE = g.a.d.d.f.e();
            int[] iArrE2 = g.a.d.d.f.e();
            int i3 = 0;
            for (int i4 = 0; i4 < this.f5437a; i4++) {
                int i5 = ((i4 ^ i2) - 1) >> 31;
                for (int i6 = 0; i6 < 7; i6++) {
                    int i7 = iArrE[i6];
                    int[] iArr = this.f5438b;
                    iArrE[i6] = i7 ^ (iArr[i3 + i6] & i5);
                    iArrE2[i6] = iArrE2[i6] ^ (iArr[(i3 + 7) + i6] & i5);
                }
                i3 += 14;
            }
            return w.this.i(new y(iArrE), new y(iArrE2), false);
        }

        @Override // g.a.d.b.f
        public int b() {
            return this.f5437a;
        }
    }

    public w() {
        super(f5436i);
        this.j = new z(this, null, null);
        this.f5477b = n(g.a.d.b.c.f5470a);
        this.f5478c = n(BigInteger.valueOf(5L));
        this.f5479d = new BigInteger(1, g.a.f.j.d.a("010000000000000000000000000001DCE8D2EC6184CAF0A971769FB1F7"));
        this.f5480e = BigInteger.valueOf(1L);
        this.f5481f = 2;
    }

    @Override // g.a.d.b.d
    public boolean E(int i2) {
        return i2 == 2;
    }

    @Override // g.a.d.b.d
    public g.a.d.b.d c() {
        return new w();
    }

    @Override // g.a.d.b.d
    public g.a.d.b.f e(g.a.d.b.h[] hVarArr, int i2, int i3) {
        int[] iArr = new int[i3 * 7 * 2];
        int i4 = 0;
        for (int i5 = 0; i5 < i3; i5++) {
            g.a.d.b.h hVar = hVarArr[i2 + i5];
            g.a.d.d.f.c(((y) hVar.n()).f5451i, 0, iArr, i4);
            int i6 = i4 + 7;
            g.a.d.d.f.c(((y) hVar.o()).f5451i, 0, iArr, i6);
            i4 = i6 + 7;
        }
        return new a(i3, iArr);
    }

    @Override // g.a.d.b.d
    public g.a.d.b.h i(g.a.d.b.e eVar, g.a.d.b.e eVar2, boolean z) {
        return new z(this, eVar, eVar2, z);
    }

    @Override // g.a.d.b.d
    public g.a.d.b.h j(g.a.d.b.e eVar, g.a.d.b.e eVar2, g.a.d.b.e[] eVarArr, boolean z) {
        return new z(this, eVar, eVar2, eVarArr, z);
    }

    @Override // g.a.d.b.d
    public g.a.d.b.e n(BigInteger bigInteger) {
        return new y(bigInteger);
    }

    @Override // g.a.d.b.d
    public int u() {
        return f5436i.bitLength();
    }

    @Override // g.a.d.b.d
    public g.a.d.b.h v() {
        return this.j;
    }
}
