package g.a.d.b.a0.a;

import g.a.d.b.d;
import g.a.d.b.e;
import g.a.d.b.f;
import g.a.d.b.h;
import g.a.d.d.g;
import java.math.BigInteger;

/* JADX INFO: loaded from: classes.dex */
public class a extends d.c {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public static final BigInteger f5273i = g.J(b.f5277a);
    public d j;

    /* JADX INFO: renamed from: g.a.d.b.a0.a.a$a, reason: collision with other inner class name */
    public class C0093a implements f {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ int f5274a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ int[] f5275b;

        public C0093a(int i2, int[] iArr) {
            this.f5274a = i2;
            this.f5275b = iArr;
        }

        @Override // g.a.d.b.f
        public h a(int i2) {
            int[] iArrH = g.h();
            int[] iArrH2 = g.h();
            int i3 = 0;
            for (int i4 = 0; i4 < this.f5274a; i4++) {
                int i5 = ((i4 ^ i2) - 1) >> 31;
                for (int i6 = 0; i6 < 8; i6++) {
                    int i7 = iArrH[i6];
                    int[] iArr = this.f5275b;
                    iArrH[i6] = i7 ^ (iArr[i3 + i6] & i5);
                    iArrH2[i6] = iArrH2[i6] ^ (iArr[(i3 + 8) + i6] & i5);
                }
                i3 += 16;
            }
            return a.this.i(new c(iArrH), new c(iArrH2), false);
        }

        @Override // g.a.d.b.f
        public int b() {
            return this.f5274a;
        }
    }

    public a() {
        super(f5273i);
        this.j = new d(this, null, null);
        this.f5477b = n(new BigInteger(1, g.a.f.j.d.a("2AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA984914A144")));
        this.f5478c = n(new BigInteger(1, g.a.f.j.d.a("7B425ED097B425ED097B425ED097B425ED097B425ED097B4260B5E9C7710C864")));
        this.f5479d = new BigInteger(1, g.a.f.j.d.a("1000000000000000000000000000000014DEF9DEA2F79CD65812631A5CF5D3ED"));
        this.f5480e = BigInteger.valueOf(8L);
        this.f5481f = 4;
    }

    @Override // g.a.d.b.d
    public boolean E(int i2) {
        return i2 == 4;
    }

    @Override // g.a.d.b.d
    public g.a.d.b.d c() {
        return new a();
    }

    @Override // g.a.d.b.d
    public f e(h[] hVarArr, int i2, int i3) {
        int[] iArr = new int[i3 * 8 * 2];
        int i4 = 0;
        for (int i5 = 0; i5 < i3; i5++) {
            h hVar = hVarArr[i2 + i5];
            g.f(((c) hVar.n()).f5281i, 0, iArr, i4);
            int i6 = i4 + 8;
            g.f(((c) hVar.o()).f5281i, 0, iArr, i6);
            i4 = i6 + 8;
        }
        return new C0093a(i3, iArr);
    }

    @Override // g.a.d.b.d
    public h i(e eVar, e eVar2, boolean z) {
        return new d(this, eVar, eVar2, z);
    }

    @Override // g.a.d.b.d
    public h j(e eVar, e eVar2, e[] eVarArr, boolean z) {
        return new d(this, eVar, eVar2, eVarArr, z);
    }

    @Override // g.a.d.b.d
    public e n(BigInteger bigInteger) {
        return new c(bigInteger);
    }

    @Override // g.a.d.b.d
    public int u() {
        return f5273i.bitLength();
    }

    @Override // g.a.d.b.d
    public h v() {
        return this.j;
    }
}
