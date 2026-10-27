package g.a.d.b;

import java.math.BigInteger;
import java.util.Random;

/* JADX INFO: loaded from: classes.dex */
public abstract class e implements g.a.d.b.c {

    public static abstract class a extends e {
        public int u() {
            int iF = f();
            e eVarA = this;
            e eVarO = eVarA;
            for (int i2 = 1; i2 < iF; i2++) {
                eVarO = eVarO.o();
                eVarA = eVarA.a(eVarO);
            }
            if (eVarA.i()) {
                return 0;
            }
            if (eVarA.h()) {
                return 1;
            }
            throw new IllegalStateException("Internal error in trace calculation");
        }
    }

    public static abstract class b extends e {
    }

    public static class c extends a {

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        public int f5499g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        public int f5500h;

        /* JADX INFO: renamed from: i, reason: collision with root package name */
        public int[] f5501i;
        public n j;

        public c(int i2, int i3, int i4, int i5, BigInteger bigInteger) {
            if (bigInteger == null || bigInteger.signum() < 0 || bigInteger.bitLength() > i2) {
                throw new IllegalArgumentException("x value invalid in F2m field element");
            }
            if (i4 == 0 && i5 == 0) {
                this.f5499g = 2;
                this.f5501i = new int[]{i3};
            } else {
                if (i4 >= i5) {
                    throw new IllegalArgumentException("k2 must be smaller than k3");
                }
                if (i4 <= 0) {
                    throw new IllegalArgumentException("k2 must be larger than 0");
                }
                this.f5499g = 3;
                this.f5501i = new int[]{i3, i4, i5};
            }
            this.f5500h = i2;
            this.j = new n(bigInteger);
        }

        public c(int i2, int[] iArr, n nVar) {
            this.f5500h = i2;
            this.f5499g = iArr.length == 1 ? 2 : 3;
            this.f5501i = iArr;
            this.j = nVar;
        }

        public static void v(e eVar, e eVar2) {
            if (!(eVar instanceof c) || !(eVar2 instanceof c)) {
                throw new IllegalArgumentException("Field elements are not both instances of ECFieldElement.F2m");
            }
            c cVar = (c) eVar;
            c cVar2 = (c) eVar2;
            if (cVar.f5499g != cVar2.f5499g) {
                throw new IllegalArgumentException("One of the F2m field elements has incorrect representation");
            }
            if (cVar.f5500h != cVar2.f5500h || !g.a.f.a.c(cVar.f5501i, cVar2.f5501i)) {
                throw new IllegalArgumentException("Field elements are not elements of the same field F2m");
            }
        }

        @Override // g.a.d.b.e
        public e a(e eVar) {
            n nVar = (n) this.j.clone();
            nVar.h(((c) eVar).j, 0);
            return new c(this.f5500h, this.f5501i, nVar);
        }

        @Override // g.a.d.b.e
        public e b() {
            return new c(this.f5500h, this.f5501i, this.j.f());
        }

        @Override // g.a.d.b.e
        public int c() {
            return this.j.m();
        }

        @Override // g.a.d.b.e
        public e d(e eVar) {
            return j(eVar.g());
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof c)) {
                return false;
            }
            c cVar = (c) obj;
            return this.f5500h == cVar.f5500h && this.f5499g == cVar.f5499g && g.a.f.a.c(this.f5501i, cVar.f5501i) && this.j.equals(cVar.j);
        }

        @Override // g.a.d.b.e
        public int f() {
            return this.f5500h;
        }

        @Override // g.a.d.b.e
        public e g() {
            int i2 = this.f5500h;
            int[] iArr = this.f5501i;
            return new c(i2, iArr, this.j.y(i2, iArr));
        }

        @Override // g.a.d.b.e
        public boolean h() {
            return this.j.w();
        }

        public int hashCode() {
            return (this.j.hashCode() ^ this.f5500h) ^ g.a.f.a.B(this.f5501i);
        }

        @Override // g.a.d.b.e
        public boolean i() {
            return this.j.x();
        }

        @Override // g.a.d.b.e
        public e j(e eVar) {
            int i2 = this.f5500h;
            int[] iArr = this.f5501i;
            return new c(i2, iArr, this.j.z(((c) eVar).j, i2, iArr));
        }

        @Override // g.a.d.b.e
        public e k(e eVar, e eVar2, e eVar3) {
            return l(eVar, eVar2, eVar3);
        }

        @Override // g.a.d.b.e
        public e l(e eVar, e eVar2, e eVar3) {
            n nVar = this.j;
            n nVar2 = ((c) eVar).j;
            n nVar3 = ((c) eVar2).j;
            n nVar4 = ((c) eVar3).j;
            n nVarC = nVar.C(nVar2, this.f5500h, this.f5501i);
            n nVarC2 = nVar3.C(nVar4, this.f5500h, this.f5501i);
            if (nVarC == nVar || nVarC == nVar2) {
                nVarC = (n) nVarC.clone();
            }
            nVarC.h(nVarC2, 0);
            nVarC.E(this.f5500h, this.f5501i);
            return new c(this.f5500h, this.f5501i, nVarC);
        }

        @Override // g.a.d.b.e
        public e m() {
            return this;
        }

        @Override // g.a.d.b.e
        public e n() {
            return (this.j.x() || this.j.w()) ? this : q(this.f5500h - 1);
        }

        @Override // g.a.d.b.e
        public e o() {
            int i2 = this.f5500h;
            int[] iArr = this.f5501i;
            return new c(i2, iArr, this.j.A(i2, iArr));
        }

        @Override // g.a.d.b.e
        public e p(e eVar, e eVar2) {
            n nVar = this.j;
            n nVar2 = ((c) eVar).j;
            n nVar3 = ((c) eVar2).j;
            n nVarO = nVar.O(this.f5500h, this.f5501i);
            n nVarC = nVar2.C(nVar3, this.f5500h, this.f5501i);
            if (nVarO == nVar) {
                nVarO = (n) nVarO.clone();
            }
            nVarO.h(nVarC, 0);
            nVarO.E(this.f5500h, this.f5501i);
            return new c(this.f5500h, this.f5501i, nVarO);
        }

        @Override // g.a.d.b.e
        public e q(int i2) {
            if (i2 < 1) {
                return this;
            }
            int i3 = this.f5500h;
            int[] iArr = this.f5501i;
            return new c(i3, iArr, this.j.B(i2, i3, iArr));
        }

        @Override // g.a.d.b.e
        public e r(e eVar) {
            return a(eVar);
        }

        @Override // g.a.d.b.e
        public boolean s() {
            return this.j.R();
        }

        @Override // g.a.d.b.e
        public BigInteger t() {
            return this.j.S();
        }
    }

    public static class d extends b {

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        public BigInteger f5502g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        public BigInteger f5503h;

        /* JADX INFO: renamed from: i, reason: collision with root package name */
        public BigInteger f5504i;

        public d(BigInteger bigInteger, BigInteger bigInteger2, BigInteger bigInteger3) {
            if (bigInteger3 == null || bigInteger3.signum() < 0 || bigInteger3.compareTo(bigInteger) >= 0) {
                throw new IllegalArgumentException("x value invalid in Fp field element");
            }
            this.f5502g = bigInteger;
            this.f5503h = bigInteger2;
            this.f5504i = bigInteger3;
        }

        public static BigInteger u(BigInteger bigInteger) {
            int iBitLength = bigInteger.bitLength();
            if (iBitLength < 96 || bigInteger.shiftRight(iBitLength - 64).longValue() != -1) {
                return null;
            }
            return g.a.d.b.c.f5471b.shiftLeft(iBitLength).subtract(bigInteger);
        }

        public BigInteger A(BigInteger bigInteger) {
            int iF = f();
            int i2 = (iF + 31) >> 5;
            int[] iArrO = g.a.d.d.m.o(iF, this.f5502g);
            int[] iArrO2 = g.a.d.d.m.o(iF, bigInteger);
            int[] iArrJ = g.a.d.d.m.j(i2);
            g.a.d.d.b.d(iArrO, iArrO2, iArrJ);
            return g.a.d.d.m.P(i2, iArrJ);
        }

        public BigInteger B(BigInteger bigInteger, BigInteger bigInteger2) {
            return C(bigInteger.multiply(bigInteger2));
        }

        public BigInteger C(BigInteger bigInteger) {
            if (this.f5503h == null) {
                return bigInteger.mod(this.f5502g);
            }
            boolean z = bigInteger.signum() < 0;
            if (z) {
                bigInteger = bigInteger.abs();
            }
            int iBitLength = this.f5502g.bitLength();
            boolean zEquals = this.f5503h.equals(g.a.d.b.c.f5471b);
            while (bigInteger.bitLength() > iBitLength + 1) {
                BigInteger bigIntegerShiftRight = bigInteger.shiftRight(iBitLength);
                BigInteger bigIntegerSubtract = bigInteger.subtract(bigIntegerShiftRight.shiftLeft(iBitLength));
                if (!zEquals) {
                    bigIntegerShiftRight = bigIntegerShiftRight.multiply(this.f5503h);
                }
                bigInteger = bigIntegerShiftRight.add(bigIntegerSubtract);
            }
            while (bigInteger.compareTo(this.f5502g) >= 0) {
                bigInteger = bigInteger.subtract(this.f5502g);
            }
            return (!z || bigInteger.signum() == 0) ? bigInteger : this.f5502g.subtract(bigInteger);
        }

        public BigInteger D(BigInteger bigInteger, BigInteger bigInteger2) {
            BigInteger bigIntegerSubtract = bigInteger.subtract(bigInteger2);
            return bigIntegerSubtract.signum() < 0 ? bigIntegerSubtract.add(this.f5502g) : bigIntegerSubtract;
        }

        @Override // g.a.d.b.e
        public e a(e eVar) {
            return new d(this.f5502g, this.f5503h, x(this.f5504i, eVar.t()));
        }

        @Override // g.a.d.b.e
        public e b() {
            BigInteger bigIntegerAdd = this.f5504i.add(g.a.d.b.c.f5471b);
            if (bigIntegerAdd.compareTo(this.f5502g) == 0) {
                bigIntegerAdd = g.a.d.b.c.f5470a;
            }
            return new d(this.f5502g, this.f5503h, bigIntegerAdd);
        }

        @Override // g.a.d.b.e
        public e d(e eVar) {
            return new d(this.f5502g, this.f5503h, B(this.f5504i, A(eVar.t())));
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof d)) {
                return false;
            }
            d dVar = (d) obj;
            return this.f5502g.equals(dVar.f5502g) && this.f5504i.equals(dVar.f5504i);
        }

        @Override // g.a.d.b.e
        public int f() {
            return this.f5502g.bitLength();
        }

        @Override // g.a.d.b.e
        public e g() {
            return new d(this.f5502g, this.f5503h, A(this.f5504i));
        }

        public int hashCode() {
            return this.f5502g.hashCode() ^ this.f5504i.hashCode();
        }

        @Override // g.a.d.b.e
        public e j(e eVar) {
            return new d(this.f5502g, this.f5503h, B(this.f5504i, eVar.t()));
        }

        @Override // g.a.d.b.e
        public e k(e eVar, e eVar2, e eVar3) {
            BigInteger bigInteger = this.f5504i;
            BigInteger bigIntegerT = eVar.t();
            BigInteger bigIntegerT2 = eVar2.t();
            BigInteger bigIntegerT3 = eVar3.t();
            return new d(this.f5502g, this.f5503h, C(bigInteger.multiply(bigIntegerT).subtract(bigIntegerT2.multiply(bigIntegerT3))));
        }

        @Override // g.a.d.b.e
        public e l(e eVar, e eVar2, e eVar3) {
            BigInteger bigInteger = this.f5504i;
            BigInteger bigIntegerT = eVar.t();
            BigInteger bigIntegerT2 = eVar2.t();
            BigInteger bigIntegerT3 = eVar3.t();
            return new d(this.f5502g, this.f5503h, C(bigInteger.multiply(bigIntegerT).add(bigIntegerT2.multiply(bigIntegerT3))));
        }

        @Override // g.a.d.b.e
        public e m() {
            if (this.f5504i.signum() == 0) {
                return this;
            }
            BigInteger bigInteger = this.f5502g;
            return new d(bigInteger, this.f5503h, bigInteger.subtract(this.f5504i));
        }

        @Override // g.a.d.b.e
        public e n() {
            if (i() || h()) {
                return this;
            }
            if (!this.f5502g.testBit(0)) {
                throw new RuntimeException("not done yet");
            }
            if (this.f5502g.testBit(1)) {
                BigInteger bigIntegerAdd = this.f5502g.shiftRight(2).add(g.a.d.b.c.f5471b);
                BigInteger bigInteger = this.f5502g;
                return v(new d(bigInteger, this.f5503h, this.f5504i.modPow(bigIntegerAdd, bigInteger)));
            }
            if (this.f5502g.testBit(2)) {
                BigInteger bigIntegerModPow = this.f5504i.modPow(this.f5502g.shiftRight(3), this.f5502g);
                BigInteger bigIntegerB = B(bigIntegerModPow, this.f5504i);
                if (B(bigIntegerB, bigIntegerModPow).equals(g.a.d.b.c.f5471b)) {
                    return v(new d(this.f5502g, this.f5503h, bigIntegerB));
                }
                return v(new d(this.f5502g, this.f5503h, B(bigIntegerB, g.a.d.b.c.f5472c.modPow(this.f5502g.shiftRight(2), this.f5502g))));
            }
            BigInteger bigIntegerShiftRight = this.f5502g.shiftRight(1);
            BigInteger bigIntegerModPow2 = this.f5504i.modPow(bigIntegerShiftRight, this.f5502g);
            BigInteger bigInteger2 = g.a.d.b.c.f5471b;
            if (!bigIntegerModPow2.equals(bigInteger2)) {
                return null;
            }
            BigInteger bigInteger3 = this.f5504i;
            BigInteger bigIntegerY = y(y(bigInteger3));
            BigInteger bigIntegerAdd2 = bigIntegerShiftRight.add(bigInteger2);
            BigInteger bigIntegerSubtract = this.f5502g.subtract(bigInteger2);
            Random random = new Random();
            while (true) {
                BigInteger bigInteger4 = new BigInteger(this.f5502g.bitLength(), random);
                if (bigInteger4.compareTo(this.f5502g) < 0 && C(bigInteger4.multiply(bigInteger4).subtract(bigIntegerY)).modPow(bigIntegerShiftRight, this.f5502g).equals(bigIntegerSubtract)) {
                    BigInteger[] bigIntegerArrW = w(bigInteger4, bigInteger3, bigIntegerAdd2);
                    BigInteger bigInteger5 = bigIntegerArrW[0];
                    BigInteger bigInteger6 = bigIntegerArrW[1];
                    if (B(bigInteger6, bigInteger6).equals(bigIntegerY)) {
                        return new d(this.f5502g, this.f5503h, z(bigInteger6));
                    }
                    if (!bigInteger5.equals(g.a.d.b.c.f5471b) && !bigInteger5.equals(bigIntegerSubtract)) {
                        return null;
                    }
                }
            }
        }

        @Override // g.a.d.b.e
        public e o() {
            BigInteger bigInteger = this.f5502g;
            BigInteger bigInteger2 = this.f5503h;
            BigInteger bigInteger3 = this.f5504i;
            return new d(bigInteger, bigInteger2, B(bigInteger3, bigInteger3));
        }

        @Override // g.a.d.b.e
        public e p(e eVar, e eVar2) {
            BigInteger bigInteger = this.f5504i;
            BigInteger bigIntegerT = eVar.t();
            BigInteger bigIntegerT2 = eVar2.t();
            return new d(this.f5502g, this.f5503h, C(bigInteger.multiply(bigInteger).add(bigIntegerT.multiply(bigIntegerT2))));
        }

        @Override // g.a.d.b.e
        public e r(e eVar) {
            return new d(this.f5502g, this.f5503h, D(this.f5504i, eVar.t()));
        }

        @Override // g.a.d.b.e
        public BigInteger t() {
            return this.f5504i;
        }

        public final e v(e eVar) {
            if (eVar.o().equals(this)) {
                return eVar;
            }
            return null;
        }

        public final BigInteger[] w(BigInteger bigInteger, BigInteger bigInteger2, BigInteger bigInteger3) {
            int iBitLength = bigInteger3.bitLength();
            int lowestSetBit = bigInteger3.getLowestSetBit();
            BigInteger bigIntegerB = g.a.d.b.c.f5471b;
            BigInteger bigIntegerC = bigInteger;
            BigInteger bigIntegerB2 = bigIntegerB;
            BigInteger bigIntegerC2 = g.a.d.b.c.f5472c;
            BigInteger bigIntegerB3 = bigIntegerB2;
            for (int i2 = iBitLength - 1; i2 >= lowestSetBit + 1; i2--) {
                bigIntegerB = B(bigIntegerB, bigIntegerB3);
                if (bigInteger3.testBit(i2)) {
                    bigIntegerB3 = B(bigIntegerB, bigInteger2);
                    bigIntegerB2 = B(bigIntegerB2, bigIntegerC);
                    bigIntegerC2 = C(bigIntegerC.multiply(bigIntegerC2).subtract(bigInteger.multiply(bigIntegerB)));
                    bigIntegerC = C(bigIntegerC.multiply(bigIntegerC).subtract(bigIntegerB3.shiftLeft(1)));
                } else {
                    BigInteger bigIntegerC3 = C(bigIntegerB2.multiply(bigIntegerC2).subtract(bigIntegerB));
                    BigInteger bigIntegerC4 = C(bigIntegerC.multiply(bigIntegerC2).subtract(bigInteger.multiply(bigIntegerB)));
                    bigIntegerC2 = C(bigIntegerC2.multiply(bigIntegerC2).subtract(bigIntegerB.shiftLeft(1)));
                    bigIntegerC = bigIntegerC4;
                    bigIntegerB2 = bigIntegerC3;
                    bigIntegerB3 = bigIntegerB;
                }
            }
            BigInteger bigIntegerB4 = B(bigIntegerB, bigIntegerB3);
            BigInteger bigIntegerB5 = B(bigIntegerB4, bigInteger2);
            BigInteger bigIntegerC5 = C(bigIntegerB2.multiply(bigIntegerC2).subtract(bigIntegerB4));
            BigInteger bigIntegerC6 = C(bigIntegerC.multiply(bigIntegerC2).subtract(bigInteger.multiply(bigIntegerB4)));
            BigInteger bigIntegerB6 = B(bigIntegerB4, bigIntegerB5);
            for (int i3 = 1; i3 <= lowestSetBit; i3++) {
                bigIntegerC5 = B(bigIntegerC5, bigIntegerC6);
                bigIntegerC6 = C(bigIntegerC6.multiply(bigIntegerC6).subtract(bigIntegerB6.shiftLeft(1)));
                bigIntegerB6 = B(bigIntegerB6, bigIntegerB6);
            }
            return new BigInteger[]{bigIntegerC5, bigIntegerC6};
        }

        public BigInteger x(BigInteger bigInteger, BigInteger bigInteger2) {
            BigInteger bigIntegerAdd = bigInteger.add(bigInteger2);
            return bigIntegerAdd.compareTo(this.f5502g) >= 0 ? bigIntegerAdd.subtract(this.f5502g) : bigIntegerAdd;
        }

        public BigInteger y(BigInteger bigInteger) {
            BigInteger bigIntegerShiftLeft = bigInteger.shiftLeft(1);
            return bigIntegerShiftLeft.compareTo(this.f5502g) >= 0 ? bigIntegerShiftLeft.subtract(this.f5502g) : bigIntegerShiftLeft;
        }

        public BigInteger z(BigInteger bigInteger) {
            if (bigInteger.testBit(0)) {
                bigInteger = this.f5502g.subtract(bigInteger);
            }
            return bigInteger.shiftRight(1);
        }
    }

    public abstract e a(e eVar);

    public abstract e b();

    public int c() {
        return t().bitLength();
    }

    public abstract e d(e eVar);

    public byte[] e() {
        return g.a.f.b.a((f() + 7) / 8, t());
    }

    public abstract int f();

    public abstract e g();

    public boolean h() {
        return c() == 1;
    }

    public boolean i() {
        return t().signum() == 0;
    }

    public abstract e j(e eVar);

    public e k(e eVar, e eVar2, e eVar3) {
        return j(eVar).r(eVar2.j(eVar3));
    }

    public e l(e eVar, e eVar2, e eVar3) {
        return j(eVar).a(eVar2.j(eVar3));
    }

    public abstract e m();

    public abstract e n();

    public abstract e o();

    public e p(e eVar, e eVar2) {
        return o().a(eVar.j(eVar2));
    }

    public e q(int i2) {
        e eVarO = this;
        for (int i3 = 0; i3 < i2; i3++) {
            eVarO = eVarO.o();
        }
        return eVarO;
    }

    public abstract e r(e eVar);

    public boolean s() {
        return t().testBit(0);
    }

    public abstract BigInteger t();

    public String toString() {
        return t().toString(16);
    }
}
