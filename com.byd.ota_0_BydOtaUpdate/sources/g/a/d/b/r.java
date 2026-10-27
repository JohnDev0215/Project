package g.a.d.b;

import java.math.BigInteger;

/* JADX INFO: loaded from: classes.dex */
public class r {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final BigInteger f5524a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final int f5525b;

    public r(BigInteger bigInteger, int i2) {
        if (i2 < 0) {
            throw new IllegalArgumentException("scale may not be negative");
        }
        this.f5524a = bigInteger;
        this.f5525b = i2;
    }

    public r a(r rVar) {
        c(rVar);
        return new r(this.f5524a.add(rVar.f5524a), this.f5525b);
    }

    public r b(int i2) {
        if (i2 < 0) {
            throw new IllegalArgumentException("scale may not be negative");
        }
        int i3 = this.f5525b;
        return i2 == i3 ? this : new r(this.f5524a.shiftLeft(i2 - i3), i2);
    }

    public final void c(r rVar) {
        if (this.f5525b != rVar.f5525b) {
            throw new IllegalArgumentException("Only SimpleBigDecimal of same scale allowed in arithmetic operations");
        }
    }

    public int d(BigInteger bigInteger) {
        return this.f5524a.compareTo(bigInteger.shiftLeft(this.f5525b));
    }

    public BigInteger e() {
        return this.f5524a.shiftRight(this.f5525b);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof r)) {
            return false;
        }
        r rVar = (r) obj;
        return this.f5524a.equals(rVar.f5524a) && this.f5525b == rVar.f5525b;
    }

    public int f() {
        return this.f5525b;
    }

    public r g() {
        return new r(this.f5524a.negate(), this.f5525b);
    }

    public BigInteger h() {
        return a(new r(c.f5471b, 1).b(this.f5525b)).e();
    }

    public int hashCode() {
        return this.f5524a.hashCode() ^ this.f5525b;
    }

    public r i(BigInteger bigInteger) {
        return new r(this.f5524a.subtract(bigInteger.shiftLeft(this.f5525b)), this.f5525b);
    }

    public r j(r rVar) {
        return a(rVar.g());
    }

    public String toString() {
        if (this.f5525b == 0) {
            return this.f5524a.toString();
        }
        BigInteger bigIntegerE = e();
        BigInteger bigIntegerSubtract = this.f5524a.subtract(bigIntegerE.shiftLeft(this.f5525b));
        if (this.f5524a.signum() == -1) {
            bigIntegerSubtract = c.f5471b.shiftLeft(this.f5525b).subtract(bigIntegerSubtract);
        }
        if (bigIntegerE.signum() == -1 && !bigIntegerSubtract.equals(c.f5470a)) {
            bigIntegerE = bigIntegerE.add(c.f5471b);
        }
        String string = bigIntegerE.toString();
        char[] cArr = new char[this.f5525b];
        String string2 = bigIntegerSubtract.toString(2);
        int length = string2.length();
        int i2 = this.f5525b - length;
        for (int i3 = 0; i3 < i2; i3++) {
            cArr[i3] = '0';
        }
        for (int i4 = 0; i4 < length; i4++) {
            cArr[i2 + i4] = string2.charAt(i4);
        }
        String str = new String(cArr);
        StringBuffer stringBuffer = new StringBuffer(string);
        stringBuffer.append(".");
        stringBuffer.append(str);
        return stringBuffer.toString();
    }
}
