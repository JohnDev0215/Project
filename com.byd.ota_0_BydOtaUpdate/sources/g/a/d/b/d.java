package g.a.d.b;

import g.a.d.b.e;
import g.a.d.b.h;
import java.math.BigInteger;
import java.util.Hashtable;
import java.util.Random;

/* JADX INFO: loaded from: classes.dex */
public abstract class d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public g.a.d.c.a f5476a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public g.a.d.b.e f5477b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public g.a.d.b.e f5478c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public BigInteger f5479d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public BigInteger f5480e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int f5481f = 0;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public g.a.d.b.b0.a f5482g = null;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public g f5483h = null;

    public class a implements g.a.d.b.f {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ int f5484a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ int f5485b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final /* synthetic */ byte[] f5486c;

        public a(int i2, int i3, byte[] bArr) {
            this.f5484a = i2;
            this.f5485b = i3;
            this.f5486c = bArr;
        }

        @Override // g.a.d.b.f
        public h a(int i2) {
            int i3;
            int i4 = this.f5485b;
            byte[] bArr = new byte[i4];
            byte[] bArr2 = new byte[i4];
            int i5 = 0;
            for (int i6 = 0; i6 < this.f5484a; i6++) {
                int i7 = ((i6 ^ i2) - 1) >> 31;
                int i8 = 0;
                while (true) {
                    i3 = this.f5485b;
                    if (i8 < i3) {
                        byte b2 = bArr[i8];
                        byte[] bArr3 = this.f5486c;
                        bArr[i8] = (byte) (b2 ^ (bArr3[i5 + i8] & i7));
                        bArr2[i8] = (byte) ((bArr3[(i3 + i5) + i8] & i7) ^ bArr2[i8]);
                        i8++;
                    }
                }
                i5 += i3 * 2;
            }
            d dVar = d.this;
            return dVar.i(dVar.n(new BigInteger(1, bArr)), d.this.n(new BigInteger(1, bArr2)), false);
        }

        @Override // g.a.d.b.f
        public int b() {
            return this.f5484a;
        }
    }

    public static abstract class b extends d {

        /* JADX INFO: renamed from: i, reason: collision with root package name */
        public BigInteger[] f5488i;

        public b(int i2, int i3, int i4, int i5) {
            super(G(i2, i3, i4, i5));
            this.f5488i = null;
        }

        public static g.a.d.c.a G(int i2, int i3, int i4, int i5) {
            if (i3 == 0) {
                throw new IllegalArgumentException("k1 must be > 0");
            }
            if (i4 == 0) {
                if (i5 == 0) {
                    return g.a.d.c.b.a(new int[]{0, i3, i2});
                }
                throw new IllegalArgumentException("k3 must be 0 if k2 == 0");
            }
            if (i4 <= i3) {
                throw new IllegalArgumentException("k2 must be > k1");
            }
            if (i5 > i4) {
                return g.a.d.c.b.a(new int[]{0, i3, i4, i5, i2});
            }
            throw new IllegalArgumentException("k3 must be > k2");
        }

        @Override // g.a.d.b.d
        public boolean A(BigInteger bigInteger) {
            return bigInteger != null && bigInteger.signum() >= 0 && bigInteger.bitLength() <= u();
        }

        public synchronized BigInteger[] H() {
            if (this.f5488i == null) {
                this.f5488i = s.f(this);
            }
            return this.f5488i;
        }

        public boolean I() {
            return this.f5479d != null && this.f5480e != null && this.f5478c.h() && (this.f5477b.i() || this.f5477b.h());
        }

        public g.a.d.b.e J(g.a.d.b.e eVar) {
            g.a.d.b.e eVarA;
            if (eVar.i()) {
                return eVar;
            }
            g.a.d.b.e eVarN = n(g.a.d.b.c.f5470a);
            int iU = u();
            Random random = new Random();
            do {
                g.a.d.b.e eVarN2 = n(new BigInteger(iU, random));
                g.a.d.b.e eVarA2 = eVar;
                eVarA = eVarN;
                for (int i2 = 1; i2 < iU; i2++) {
                    g.a.d.b.e eVarO = eVarA2.o();
                    eVarA = eVarA.o().a(eVarO.j(eVarN2));
                    eVarA2 = eVarO.a(eVar);
                }
                if (!eVarA2.i()) {
                    return null;
                }
            } while (eVarA.o().a(eVarA).i());
            return eVarA;
        }

        @Override // g.a.d.b.d
        public h h(BigInteger bigInteger, BigInteger bigInteger2, boolean z) {
            g.a.d.b.e eVarN = n(bigInteger);
            g.a.d.b.e eVarN2 = n(bigInteger2);
            int iR = r();
            if (iR == 5 || iR == 6) {
                if (!eVarN.i()) {
                    eVarN2 = eVarN2.d(eVarN).a(eVarN);
                } else if (!eVarN2.o().equals(p())) {
                    throw new IllegalArgumentException();
                }
            }
            return i(eVarN, eVarN2, z);
        }

        @Override // g.a.d.b.d
        public h l(int i2, BigInteger bigInteger) {
            g.a.d.b.e eVarA;
            g.a.d.b.e eVarN = n(bigInteger);
            if (eVarN.i()) {
                eVarA = p().n();
            } else {
                g.a.d.b.e eVarJ = J(eVarN.o().g().j(p()).a(o()).a(eVarN));
                if (eVarJ != null) {
                    if (eVarJ.s() != (i2 == 1)) {
                        eVarJ = eVarJ.b();
                    }
                    int iR = r();
                    eVarA = (iR == 5 || iR == 6) ? eVarJ.a(eVarN) : eVarJ.j(eVarN);
                } else {
                    eVarA = null;
                }
            }
            if (eVarA != null) {
                return i(eVarN, eVarA, true);
            }
            throw new IllegalArgumentException("Invalid point compression");
        }
    }

    public static abstract class c extends d {
        public c(BigInteger bigInteger) {
            super(g.a.d.c.b.b(bigInteger));
        }

        @Override // g.a.d.b.d
        public boolean A(BigInteger bigInteger) {
            return bigInteger != null && bigInteger.signum() >= 0 && bigInteger.compareTo(t().c()) < 0;
        }

        @Override // g.a.d.b.d
        public h l(int i2, BigInteger bigInteger) {
            g.a.d.b.e eVarN = n(bigInteger);
            g.a.d.b.e eVarN2 = eVarN.o().a(this.f5477b).j(eVarN).a(this.f5478c).n();
            if (eVarN2 == null) {
                throw new IllegalArgumentException("Invalid point compression");
            }
            if (eVarN2.s() != (i2 == 1)) {
                eVarN2 = eVarN2.m();
            }
            return i(eVarN, eVarN2, true);
        }
    }

    /* JADX INFO: renamed from: g.a.d.b.d$d, reason: collision with other inner class name */
    public class C0096d {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public int f5489a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public g.a.d.b.b0.a f5490b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public g f5491c;

        public C0096d(int i2, g.a.d.b.b0.a aVar, g gVar) {
            this.f5489a = i2;
            this.f5490b = aVar;
            this.f5491c = gVar;
        }

        public d a() {
            if (!d.this.E(this.f5489a)) {
                throw new IllegalStateException("unsupported coordinate system");
            }
            d dVarC = d.this.c();
            if (dVarC == d.this) {
                throw new IllegalStateException("implementation returned current curve");
            }
            synchronized (dVarC) {
                dVarC.f5481f = this.f5489a;
                dVarC.f5482g = this.f5490b;
                dVarC.f5483h = this.f5491c;
            }
            return dVarC;
        }

        public C0096d b(g.a.d.b.b0.a aVar) {
            this.f5490b = aVar;
            return this;
        }
    }

    public static class e extends b {
        public int j;
        public int k;
        public int l;
        public int m;
        public h.d n;

        public class a implements g.a.d.b.f {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public final /* synthetic */ int f5493a;

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            public final /* synthetic */ int f5494b;

            /* JADX INFO: renamed from: c, reason: collision with root package name */
            public final /* synthetic */ long[] f5495c;

            /* JADX INFO: renamed from: d, reason: collision with root package name */
            public final /* synthetic */ int[] f5496d;

            public a(int i2, int i3, long[] jArr, int[] iArr) {
                this.f5493a = i2;
                this.f5494b = i3;
                this.f5495c = jArr;
                this.f5496d = iArr;
            }

            @Override // g.a.d.b.f
            public h a(int i2) {
                int i3;
                long[] jArrK = g.a.d.d.m.k(this.f5494b);
                long[] jArrK2 = g.a.d.d.m.k(this.f5494b);
                int i4 = 0;
                for (int i5 = 0; i5 < this.f5493a; i5++) {
                    long j = ((i5 ^ i2) - 1) >> 31;
                    int i6 = 0;
                    while (true) {
                        i3 = this.f5494b;
                        if (i6 < i3) {
                            long j2 = jArrK[i6];
                            long[] jArr = this.f5495c;
                            jArrK[i6] = j2 ^ (jArr[i4 + i6] & j);
                            jArrK2[i6] = jArrK2[i6] ^ (jArr[(i3 + i4) + i6] & j);
                            i6++;
                        }
                    }
                    i4 += i3 * 2;
                }
                return e.this.i(new e.c(e.this.j, this.f5496d, new n(jArrK)), new e.c(e.this.j, this.f5496d, new n(jArrK2)), false);
            }

            @Override // g.a.d.b.f
            public int b() {
                return this.f5493a;
            }
        }

        public e(int i2, int i3, int i4, int i5, g.a.d.b.e eVar, g.a.d.b.e eVar2, BigInteger bigInteger, BigInteger bigInteger2) {
            super(i2, i3, i4, i5);
            this.j = i2;
            this.k = i3;
            this.l = i4;
            this.m = i5;
            this.f5479d = bigInteger;
            this.f5480e = bigInteger2;
            this.n = new h.d(this, null, null, false);
            this.f5477b = eVar;
            this.f5478c = eVar2;
            this.f5481f = 6;
        }

        public e(int i2, int i3, int i4, int i5, BigInteger bigInteger, BigInteger bigInteger2) {
            this(i2, i3, i4, i5, bigInteger, bigInteger2, (BigInteger) null, (BigInteger) null);
        }

        public e(int i2, int i3, int i4, int i5, BigInteger bigInteger, BigInteger bigInteger2, BigInteger bigInteger3, BigInteger bigInteger4) {
            super(i2, i3, i4, i5);
            this.j = i2;
            this.k = i3;
            this.l = i4;
            this.m = i5;
            this.f5479d = bigInteger3;
            this.f5480e = bigInteger4;
            this.n = new h.d(this, null, null, false);
            this.f5477b = n(bigInteger);
            this.f5478c = n(bigInteger2);
            this.f5481f = 6;
        }

        public e(int i2, int i3, BigInteger bigInteger, BigInteger bigInteger2, BigInteger bigInteger3, BigInteger bigInteger4) {
            this(i2, i3, 0, 0, bigInteger, bigInteger2, bigInteger3, bigInteger4);
        }

        @Override // g.a.d.b.d
        public boolean E(int i2) {
            return i2 == 0 || i2 == 1 || i2 == 6;
        }

        public boolean L() {
            return this.l == 0 && this.m == 0;
        }

        @Override // g.a.d.b.d
        public d c() {
            return new e(this.j, this.k, this.l, this.m, this.f5477b, this.f5478c, this.f5479d, this.f5480e);
        }

        @Override // g.a.d.b.d
        public g.a.d.b.f e(h[] hVarArr, int i2, int i3) {
            int i4 = (this.j + 63) >>> 6;
            int[] iArr = L() ? new int[]{this.k} : new int[]{this.k, this.l, this.m};
            long[] jArr = new long[i3 * i4 * 2];
            int i5 = 0;
            for (int i6 = 0; i6 < i3; i6++) {
                h hVar = hVarArr[i2 + i6];
                ((e.c) hVar.n()).j.l(jArr, i5);
                int i7 = i5 + i4;
                ((e.c) hVar.o()).j.l(jArr, i7);
                i5 = i7 + i4;
            }
            return new a(i3, i4, jArr, iArr);
        }

        @Override // g.a.d.b.d
        public g f() {
            return I() ? new x() : super.f();
        }

        @Override // g.a.d.b.d
        public h i(g.a.d.b.e eVar, g.a.d.b.e eVar2, boolean z) {
            return new h.d(this, eVar, eVar2, z);
        }

        @Override // g.a.d.b.d
        public h j(g.a.d.b.e eVar, g.a.d.b.e eVar2, g.a.d.b.e[] eVarArr, boolean z) {
            return new h.d(this, eVar, eVar2, eVarArr, z);
        }

        @Override // g.a.d.b.d
        public g.a.d.b.e n(BigInteger bigInteger) {
            return new e.c(this.j, this.k, this.l, this.m, bigInteger);
        }

        @Override // g.a.d.b.d
        public int u() {
            return this.j;
        }

        @Override // g.a.d.b.d
        public h v() {
            return this.n;
        }
    }

    public static class f extends c {

        /* JADX INFO: renamed from: i, reason: collision with root package name */
        public BigInteger f5498i;
        public BigInteger j;
        public h.e k;

        public f(BigInteger bigInteger, BigInteger bigInteger2, g.a.d.b.e eVar, g.a.d.b.e eVar2, BigInteger bigInteger3, BigInteger bigInteger4) {
            super(bigInteger);
            this.f5498i = bigInteger;
            this.j = bigInteger2;
            this.k = new h.e(this, null, null, false);
            this.f5477b = eVar;
            this.f5478c = eVar2;
            this.f5479d = bigInteger3;
            this.f5480e = bigInteger4;
            this.f5481f = 4;
        }

        public f(BigInteger bigInteger, BigInteger bigInteger2, BigInteger bigInteger3) {
            this(bigInteger, bigInteger2, bigInteger3, null, null);
        }

        public f(BigInteger bigInteger, BigInteger bigInteger2, BigInteger bigInteger3, BigInteger bigInteger4, BigInteger bigInteger5) {
            super(bigInteger);
            this.f5498i = bigInteger;
            this.j = e.d.u(bigInteger);
            this.k = new h.e(this, null, null, false);
            this.f5477b = n(bigInteger2);
            this.f5478c = n(bigInteger3);
            this.f5479d = bigInteger4;
            this.f5480e = bigInteger5;
            this.f5481f = 4;
        }

        @Override // g.a.d.b.d
        public boolean E(int i2) {
            return i2 == 0 || i2 == 1 || i2 == 2 || i2 == 4;
        }

        @Override // g.a.d.b.d
        public d c() {
            return new f(this.f5498i, this.j, this.f5477b, this.f5478c, this.f5479d, this.f5480e);
        }

        @Override // g.a.d.b.d
        public h i(g.a.d.b.e eVar, g.a.d.b.e eVar2, boolean z) {
            return new h.e(this, eVar, eVar2, z);
        }

        @Override // g.a.d.b.d
        public h j(g.a.d.b.e eVar, g.a.d.b.e eVar2, g.a.d.b.e[] eVarArr, boolean z) {
            return new h.e(this, eVar, eVar2, eVarArr, z);
        }

        @Override // g.a.d.b.d
        public g.a.d.b.e n(BigInteger bigInteger) {
            return new e.d(this.f5498i, this.j, bigInteger);
        }

        @Override // g.a.d.b.d
        public int u() {
            return this.f5498i.bitLength();
        }

        @Override // g.a.d.b.d
        public h v() {
            return this.k;
        }

        @Override // g.a.d.b.d
        public h z(h hVar) {
            int iR;
            return (this == hVar.i() || r() != 2 || hVar.u() || !((iR = hVar.i().r()) == 2 || iR == 3 || iR == 4)) ? super.z(hVar) : new h.e(this, n(hVar.f5507c.t()), n(hVar.f5508d.t()), new g.a.d.b.e[]{n(hVar.f5509e[0].t())}, hVar.f5510f);
        }
    }

    public d(g.a.d.c.a aVar) {
        this.f5476a = aVar;
    }

    public abstract boolean A(BigInteger bigInteger);

    public void B(h[] hVarArr) {
        C(hVarArr, 0, hVarArr.length, null);
    }

    public void C(h[] hVarArr, int i2, int i3, g.a.d.b.e eVar) {
        b(hVarArr, i2, i3);
        int iR = r();
        if (iR == 0 || iR == 5) {
            if (eVar != null) {
                throw new IllegalArgumentException("'iso' not valid for affine coordinates");
            }
            return;
        }
        g.a.d.b.e[] eVarArr = new g.a.d.b.e[i3];
        int[] iArr = new int[i3];
        int i4 = 0;
        for (int i5 = 0; i5 < i3; i5++) {
            int i6 = i2 + i5;
            h hVar = hVarArr[i6];
            if (hVar != null && (eVar != null || !hVar.v())) {
                eVarArr[i4] = hVar.s(0);
                iArr[i4] = i6;
                i4++;
            }
        }
        if (i4 == 0) {
            return;
        }
        g.a.d.b.b.o(eVarArr, 0, i4, eVar);
        for (int i7 = 0; i7 < i4; i7++) {
            int i8 = iArr[i7];
            hVarArr[i8] = hVarArr[i8].B(eVarArr[i7]);
        }
    }

    public p D(h hVar, String str, o oVar) {
        Hashtable hashtable;
        p pVarA;
        a(hVar);
        synchronized (hVar) {
            hashtable = hVar.f5511g;
            if (hashtable == null) {
                hashtable = new Hashtable(4);
                hVar.f5511g = hashtable;
            }
        }
        synchronized (hashtable) {
            p pVar = (p) hashtable.get(str);
            pVarA = oVar.a(pVar);
            if (pVarA != pVar) {
                hashtable.put(str, pVarA);
            }
        }
        return pVarA;
    }

    public boolean E(int i2) {
        return i2 == 0;
    }

    public h F(BigInteger bigInteger, BigInteger bigInteger2) {
        h hVarG = g(bigInteger, bigInteger2);
        if (hVarG.w()) {
            return hVarG;
        }
        throw new IllegalArgumentException("Invalid point coordinates");
    }

    public void a(h hVar) {
        if (hVar == null || this != hVar.i()) {
            throw new IllegalArgumentException("'point' must be non-null and on this curve");
        }
    }

    public void b(h[] hVarArr, int i2, int i3) {
        if (hVarArr == null) {
            throw new IllegalArgumentException("'points' cannot be null");
        }
        if (i2 < 0 || i3 < 0 || i2 > hVarArr.length - i3) {
            throw new IllegalArgumentException("invalid range specified for 'points'");
        }
        for (int i4 = 0; i4 < i3; i4++) {
            h hVar = hVarArr[i2 + i4];
            if (hVar != null && this != hVar.i()) {
                throw new IllegalArgumentException("'points' entries must be null or on this curve");
            }
        }
    }

    public abstract d c();

    public synchronized C0096d d() {
        return new C0096d(this.f5481f, this.f5482g, this.f5483h);
    }

    public g.a.d.b.f e(h[] hVarArr, int i2, int i3) {
        int iU = (u() + 7) >>> 3;
        byte[] bArr = new byte[i3 * iU * 2];
        int i4 = 0;
        for (int i5 = 0; i5 < i3; i5++) {
            h hVar = hVarArr[i2 + i5];
            byte[] byteArray = hVar.n().t().toByteArray();
            byte[] byteArray2 = hVar.o().t().toByteArray();
            int i6 = 1;
            int i7 = byteArray.length > iU ? 1 : 0;
            int length = byteArray.length - i7;
            if (byteArray2.length <= iU) {
                i6 = 0;
            }
            int length2 = byteArray2.length - i6;
            int i8 = i4 + iU;
            System.arraycopy(byteArray, i7, bArr, i8 - length, length);
            i4 = i8 + iU;
            System.arraycopy(byteArray2, i6, bArr, i4 - length2, length2);
        }
        return new a(i3, iU, bArr);
    }

    public boolean equals(Object obj) {
        return this == obj || ((obj instanceof d) && m((d) obj));
    }

    public g f() {
        g.a.d.b.b0.a aVar = this.f5482g;
        return aVar instanceof g.a.d.b.b0.b ? new m(this, (g.a.d.b.b0.b) aVar) : new u();
    }

    public h g(BigInteger bigInteger, BigInteger bigInteger2) {
        return h(bigInteger, bigInteger2, false);
    }

    public h h(BigInteger bigInteger, BigInteger bigInteger2, boolean z) {
        return i(n(bigInteger), n(bigInteger2), z);
    }

    public int hashCode() {
        return (t().hashCode() ^ g.a.f.d.a(o().t().hashCode(), 8)) ^ g.a.f.d.a(p().t().hashCode(), 16);
    }

    public abstract h i(g.a.d.b.e eVar, g.a.d.b.e eVar2, boolean z);

    public abstract h j(g.a.d.b.e eVar, g.a.d.b.e eVar2, g.a.d.b.e[] eVarArr, boolean z);

    public h k(byte[] bArr) {
        h hVarV;
        int iU = (u() + 7) / 8;
        byte b2 = bArr[0];
        if (b2 != 0) {
            if (b2 == 2 || b2 == 3) {
                if (bArr.length != iU + 1) {
                    throw new IllegalArgumentException("Incorrect length for compressed encoding");
                }
                hVarV = l(b2 & 1, g.a.f.b.d(bArr, 1, iU));
                if (!hVarV.w()) {
                    throw new IllegalArgumentException("Invalid point");
                }
            } else if (b2 != 4) {
                if (b2 != 6 && b2 != 7) {
                    throw new IllegalArgumentException("Invalid point encoding 0x" + Integer.toString(b2, 16));
                }
                if (bArr.length != (iU * 2) + 1) {
                    throw new IllegalArgumentException("Incorrect length for hybrid encoding");
                }
                BigInteger bigIntegerD = g.a.f.b.d(bArr, 1, iU);
                BigInteger bigIntegerD2 = g.a.f.b.d(bArr, iU + 1, iU);
                if (bigIntegerD2.testBit(0) != (b2 == 7)) {
                    throw new IllegalArgumentException("Inconsistent Y coordinate in hybrid encoding");
                }
                hVarV = F(bigIntegerD, bigIntegerD2);
            } else {
                if (bArr.length != (iU * 2) + 1) {
                    throw new IllegalArgumentException("Incorrect length for uncompressed encoding");
                }
                hVarV = F(g.a.f.b.d(bArr, 1, iU), g.a.f.b.d(bArr, iU + 1, iU));
            }
        } else {
            if (bArr.length != 1) {
                throw new IllegalArgumentException("Incorrect length for infinity encoding");
            }
            hVarV = v();
        }
        if (b2 == 0 || !hVarV.u()) {
            return hVarV;
        }
        throw new IllegalArgumentException("Invalid infinity encoding");
    }

    public abstract h l(int i2, BigInteger bigInteger);

    public boolean m(d dVar) {
        return this == dVar || (dVar != null && t().equals(dVar.t()) && o().t().equals(dVar.o().t()) && p().t().equals(dVar.p().t()));
    }

    public abstract g.a.d.b.e n(BigInteger bigInteger);

    public g.a.d.b.e o() {
        return this.f5477b;
    }

    public g.a.d.b.e p() {
        return this.f5478c;
    }

    public BigInteger q() {
        return this.f5480e;
    }

    public int r() {
        return this.f5481f;
    }

    public g.a.d.b.b0.a s() {
        return this.f5482g;
    }

    public g.a.d.c.a t() {
        return this.f5476a;
    }

    public abstract int u();

    public abstract h v();

    public synchronized g w() {
        if (this.f5483h == null) {
            this.f5483h = f();
        }
        return this.f5483h;
    }

    public BigInteger x() {
        return this.f5479d;
    }

    public p y(h hVar, String str) {
        Hashtable hashtable;
        p pVar;
        a(hVar);
        synchronized (hVar) {
            hashtable = hVar.f5511g;
        }
        if (hashtable == null) {
            return null;
        }
        synchronized (hashtable) {
            pVar = (p) hashtable.get(str);
        }
        return pVar;
    }

    public h z(h hVar) {
        if (this == hVar.i()) {
            return hVar;
        }
        if (hVar.u()) {
            return v();
        }
        h hVarA = hVar.A();
        return h(hVarA.q().t(), hVarA.r().t(), hVarA.f5510f);
    }
}
