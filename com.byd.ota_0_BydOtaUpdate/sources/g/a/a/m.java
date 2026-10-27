package g.a.a;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/* JADX INFO: loaded from: classes.dex */
public class m extends q {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final ConcurrentMap<a, m> f3916c = new ConcurrentHashMap();

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final String f3917d;
    public byte[] s;

    public static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final int f3918a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final byte[] f3919b;

        public a(byte[] bArr) {
            this.f3918a = g.a.f.a.z(bArr);
            this.f3919b = bArr;
        }

        public boolean equals(Object obj) {
            if (obj instanceof a) {
                return g.a.f.a.a(this.f3919b, ((a) obj).f3919b);
            }
            return false;
        }

        public int hashCode() {
            return this.f3918a;
        }
    }

    public m(m mVar, String str) {
        if (!v(str, 0)) {
            throw new IllegalArgumentException("string " + str + " not a valid OID branch");
        }
        this.f3917d = mVar.r() + "." + str;
    }

    public m(String str) {
        if (str == null) {
            throw new IllegalArgumentException("'identifier' cannot be null");
        }
        if (w(str)) {
            this.f3917d = str;
            return;
        }
        throw new IllegalArgumentException("string " + str + " not an OID");
    }

    public m(byte[] bArr) {
        StringBuffer stringBuffer = new StringBuffer();
        boolean z = true;
        long j = 0;
        BigInteger bigIntegerShiftLeft = null;
        for (int i2 = 0; i2 != bArr.length; i2++) {
            int i3 = bArr[i2] & 255;
            if (j <= 72057594037927808L) {
                long j2 = j + ((long) (i3 & 127));
                if ((i3 & 128) == 0) {
                    if (z) {
                        if (j2 < 40) {
                            stringBuffer.append('0');
                        } else if (j2 < 80) {
                            stringBuffer.append('1');
                            j2 -= 40;
                        } else {
                            stringBuffer.append('2');
                            j2 -= 80;
                        }
                        z = false;
                    }
                    stringBuffer.append('.');
                    stringBuffer.append(j2);
                    j = 0;
                } else {
                    j = j2 << 7;
                }
            } else {
                BigInteger bigIntegerOr = (bigIntegerShiftLeft == null ? BigInteger.valueOf(j) : bigIntegerShiftLeft).or(BigInteger.valueOf(i3 & 127));
                if ((i3 & 128) == 0) {
                    if (z) {
                        stringBuffer.append('2');
                        bigIntegerOr = bigIntegerOr.subtract(BigInteger.valueOf(80L));
                        z = false;
                    }
                    stringBuffer.append('.');
                    stringBuffer.append(bigIntegerOr);
                    j = 0;
                    bigIntegerShiftLeft = null;
                } else {
                    bigIntegerShiftLeft = bigIntegerOr.shiftLeft(7);
                }
            }
        }
        this.f3917d = stringBuffer.toString();
        this.s = g.a.f.a.f(bArr);
    }

    public static m p(byte[] bArr) {
        m mVar = f3916c.get(new a(bArr));
        return mVar == null ? new m(bArr) : mVar;
    }

    public static m s(Object obj) {
        if (obj == null || (obj instanceof m)) {
            return (m) obj;
        }
        if (obj instanceof e) {
            e eVar = (e) obj;
            if (eVar.c() instanceof m) {
                return (m) eVar.c();
            }
        }
        if (!(obj instanceof byte[])) {
            throw new IllegalArgumentException("illegal object in getInstance: " + obj.getClass().getName());
        }
        try {
            return (m) q.j((byte[]) obj);
        } catch (IOException e2) {
            throw new IllegalArgumentException("failed to construct object identifier from byte[]: " + e2.getMessage());
        }
    }

    public static m t(x xVar, boolean z) {
        q qVarP = xVar.p();
        return (z || (qVarP instanceof m)) ? s(qVarP) : p(n.n(qVarP).p());
    }

    public static boolean v(String str, int i2) {
        boolean z;
        char cCharAt;
        int length = str.length();
        do {
            z = false;
            while (true) {
                length--;
                if (length < i2) {
                    return z;
                }
                cCharAt = str.charAt(length);
                if ('0' > cCharAt || cCharAt > '9') {
                    break;
                }
                z = true;
            }
            if (cCharAt != '.') {
                break;
            }
        } while (z);
        return false;
    }

    public static boolean w(String str) {
        char cCharAt;
        if (str.length() < 3 || str.charAt(1) != '.' || (cCharAt = str.charAt(0)) < '0' || cCharAt > '2') {
            return false;
        }
        return v(str, 2);
    }

    @Override // g.a.a.q
    public boolean g(q qVar) {
        if (qVar == this) {
            return true;
        }
        if (qVar instanceof m) {
            return this.f3917d.equals(((m) qVar).f3917d);
        }
        return false;
    }

    @Override // g.a.a.q
    public void h(p pVar) throws IOException {
        byte[] bArrQ = q();
        pVar.c(6);
        pVar.i(bArrQ.length);
        pVar.d(bArrQ);
    }

    @Override // g.a.a.l
    public int hashCode() {
        return this.f3917d.hashCode();
    }

    @Override // g.a.a.q
    public int i() throws IOException {
        int length = q().length;
        return y1.a(length) + 1 + length;
    }

    @Override // g.a.a.q
    public boolean k() {
        return false;
    }

    public m n(String str) {
        return new m(this, str);
    }

    public final void o(ByteArrayOutputStream byteArrayOutputStream) {
        x1 x1Var = new x1(this.f3917d);
        int i2 = Integer.parseInt(x1Var.b()) * 40;
        String strB = x1Var.b();
        if (strB.length() <= 18) {
            y(byteArrayOutputStream, ((long) i2) + Long.parseLong(strB));
        } else {
            z(byteArrayOutputStream, new BigInteger(strB).add(BigInteger.valueOf(i2)));
        }
        while (x1Var.a()) {
            String strB2 = x1Var.b();
            if (strB2.length() <= 18) {
                y(byteArrayOutputStream, Long.parseLong(strB2));
            } else {
                z(byteArrayOutputStream, new BigInteger(strB2));
            }
        }
    }

    public final synchronized byte[] q() {
        if (this.s == null) {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            o(byteArrayOutputStream);
            this.s = byteArrayOutputStream.toByteArray();
        }
        return this.s;
    }

    public String r() {
        return this.f3917d;
    }

    public String toString() {
        return r();
    }

    public m u() {
        a aVar = new a(q());
        ConcurrentMap<a, m> concurrentMap = f3916c;
        m mVar = concurrentMap.get(aVar);
        if (mVar != null) {
            return mVar;
        }
        m mVarPutIfAbsent = concurrentMap.putIfAbsent(aVar, this);
        return mVarPutIfAbsent == null ? this : mVarPutIfAbsent;
    }

    public boolean x(m mVar) {
        String strR = r();
        String strR2 = mVar.r();
        return strR.length() > strR2.length() && strR.charAt(strR2.length()) == '.' && strR.startsWith(strR2);
    }

    public final void y(ByteArrayOutputStream byteArrayOutputStream, long j) {
        byte[] bArr = new byte[9];
        int i2 = 8;
        bArr[8] = (byte) (((int) j) & 127);
        while (j >= 128) {
            j >>= 7;
            i2--;
            bArr[i2] = (byte) ((((int) j) & 127) | 128);
        }
        byteArrayOutputStream.write(bArr, i2, 9 - i2);
    }

    public final void z(ByteArrayOutputStream byteArrayOutputStream, BigInteger bigInteger) {
        int iBitLength = (bigInteger.bitLength() + 6) / 7;
        if (iBitLength == 0) {
            byteArrayOutputStream.write(0);
            return;
        }
        byte[] bArr = new byte[iBitLength];
        int i2 = iBitLength - 1;
        for (int i3 = i2; i3 >= 0; i3--) {
            bArr[i3] = (byte) ((bigInteger.intValue() & 127) | 128);
            bigInteger = bigInteger.shiftRight(7);
        }
        bArr[i2] = (byte) (bArr[i2] & 127);
        byteArrayOutputStream.write(bArr, 0, iBitLength);
    }
}
