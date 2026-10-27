package g.a.d.b.a0.b;

import g.a.d.b.d;
import g.a.d.b.e;
import g.a.d.b.f;
import g.a.d.b.h;
import g.a.d.d.g;
import java.math.BigInteger;

/* JADX INFO: loaded from: classes.dex */
public class a extends d.c {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public static final BigInteger f5282i = new BigInteger(1, g.a.f.j.d.a("FFFFFFFEFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF00000000FFFFFFFFFFFFFFFF"));
    public d j;

    /* JADX INFO: renamed from: g.a.d.b.a0.b.a$a, reason: collision with other inner class name */
    public class C0094a implements f {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ int f5283a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ int[] f5284b;

        public C0094a(int i2, int[] iArr) {
            this.f5283a = i2;
            this.f5284b = iArr;
        }

        @Override // g.a.d.b.f
        public h a(int i2) {
            int[] iArrH = g.h();
            int[] iArrH2 = g.h();
            int i3 = 0;
            for (int i4 = 0; i4 < this.f5283a; i4++) {
                int i5 = ((i4 ^ i2) - 1) >> 31;
                for (int i6 = 0; i6 < 8; i6++) {
                    int i7 = iArrH[i6];
                    int[] iArr = this.f5284b;
                    iArrH[i6] = i7 ^ (iArr[i3 + i6] & i5);
                    iArrH2[i6] = iArrH2[i6] ^ (iArr[(i3 + 8) + i6] & i5);
                }
                i3 += 16;
            }
            return a.this.i(new c(iArrH), new c(iArrH2), false);
        }

        @Override // g.a.d.b.f
        public int b() {
            return this.f5283a;
        }
    }

    public a() {
        super(f5282i);
        this.j = new d(this, null, null);
        this.f5477b = n(new BigInteger(1, g.a.f.j.d.a("FFFFFFFEFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF00000000FFFFFFFFFFFFFFFC")));
        this.f5478c = n(new BigInteger(1, g.a.f.j.d.a("28E9FA9E9D9F5E344D5A9E4BCF6509A7F39789F515AB8F92DDBCBD414D940E93")));
        this.f5479d = new BigInteger(1, g.a.f.j.d.a("FFFFFFFEFFFFFFFFFFFFFFFFFFFFFFFF7203DF6B21C6052B53BBF40939D54123"));
        this.f5480e = BigInteger.valueOf(1L);
        this.f5481f = 2;
    }

    @Override // g.a.d.b.d
    public boolean E(int i2) {
        return i2 == 2;
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
            g.f(((c) hVar.n()).f5289h, 0, iArr, i4);
            int i6 = i4 + 8;
            g.f(((c) hVar.o()).f5289h, 0, iArr, i6);
            i4 = i6 + 8;
        }
        return new C0094a(i3, iArr);
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
        return f5282i.bitLength();
    }

    @Override // g.a.d.b.d
    public h v() {
        return this.j;
    }
}
