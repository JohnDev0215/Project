package defpackage;

import java.nio.ByteOrder;
import java.nio.charset.Charset;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class RI {
    public final C0989jJ a;
    public final int b;
    public final QI c;

    public RI(C0989jJ c0989jJ) {
        QI qi = new QI(c0989jJ.a.limit(), c0989jJ.b.size());
        this.a = c0989jJ;
        c0989jJ.a.order(ByteOrder.LITTLE_ENDIAN);
        this.b = 0;
        this.c = qi;
    }

    public void a() {
        this.c.c--;
    }

    public void b() {
        QI qi = this.c;
        long j = qi.c + 1;
        qi.c = j;
        if (j >= 100) {
            throw new TI("Recursion depth limit exceeded.");
        }
    }

    public PI c(PI[] piArr) {
        PI piF = f();
        int length = piArr.length - 1;
        if (piF.b <= piArr[length].b) {
            PI pi = null;
            while (true) {
                if (length < 0) {
                    break;
                }
                PI pi2 = piArr[length];
                if (piF.b >= pi2.b) {
                    pi = pi2;
                    break;
                }
                length--;
            }
            if (pi == null || pi.a != piF.a) {
                throw new TI("Header doesn't correspond to any known version.");
            }
        } else if (piF.a < piArr[length].a) {
            throw new TI("Message newer than the last known version cannot be shorter than required by the last known version.");
        }
        return piF;
    }

    public boolean d(int i, int i2) {
        w(i, 1);
        w(i, 1);
        return (this.a.a.get(this.b + i) & (1 << i2)) != 0;
    }

    public byte[] e(int i, int i2, int i3) {
        RI riS = s(i, JI.b(i2));
        if (riS == null) {
            return null;
        }
        byte[] bArr = new byte[riS.h(1L, i3).b];
        riS.a.a.position(riS.b + 8);
        riS.a.a.get(bArr);
        return bArr;
    }

    public PI f() {
        this.c.a(this.b, r1 + 8);
        PI piG = g(0, false);
        QI qi = this.c;
        int i = this.b;
        qi.a(i + 8, i + piG.a);
        return piG;
    }

    public final PI g(int i, boolean z) {
        int iM = m(i + 0);
        int iM2 = m(i + 4);
        if (iM < 0) {
            throw new TI("Negative size. Unsigned integers are not valid for java.");
        }
        if (iM2 >= 0 || (z && iM2 == -1)) {
            return new PI(iM, iM2);
        }
        throw new TI("Negative elements or version. Unsigned integers are not valid for java.");
    }

    public final PI h(long j, int i) {
        PI piF = f();
        long j2 = piF.a;
        int i2 = piF.b;
        if (j2 < (j * ((long) i2)) + 8) {
            throw new TI("Array header is incorrect.");
        }
        if (i == -1 || i2 == i) {
            return piF;
        }
        throw new TI("Incorrect array length. Expected: " + i + ", but got: " + piF.b + ".");
    }

    public PI i(int i) {
        return h(8L, i);
    }

    public PI j(int i) {
        PI piG = g(i, true);
        int i2 = piG.a;
        if (i2 == 0) {
            if (piG.b != 0) {
                StringBuilder sbF = Z7.f("Unexpected version tag for a null union. Expecting 0, found: ");
                sbF.append(piG.b);
                throw new TI(sbF.toString());
            }
        } else if (i2 != 16) {
            throw new TI("Unexpected size of an union. The size must be 0 for a null union, or 16 for a non-null union.");
        }
        return piG;
    }

    public double k(int i) {
        w(i, 8);
        return this.a.a.getDouble(this.b + i);
    }

    public float l(int i) {
        w(i, 4);
        return this.a.a.getFloat(this.b + i);
    }

    public int m(int i) {
        w(i, 4);
        return this.a.a.getInt(this.b + i);
    }

    public C0934iJ n(int i, boolean z) {
        VJ vjJ = v(i, z).j();
        if (vjJ == null) {
            return null;
        }
        return new C0934iJ(vjJ);
    }

    public int[] o(int i, int i2, int i3) {
        RI riS = s(i, JI.b(i2));
        if (riS == null) {
            return null;
        }
        int[] iArr = new int[riS.h(4L, i3).b];
        riS.a.a.position(riS.b + 8);
        riS.a.a.asIntBuffer().get(iArr);
        return iArr;
    }

    public long p(int i) {
        w(i, 8);
        return this.a.a.getLong(this.b + i);
    }

    public VJ q(int i, boolean z) {
        return v(i, z).j();
    }

    public VJ[] r(int i, int i2, int i3) {
        RI riS = s(i, JI.b(i2));
        if (riS == null) {
            return null;
        }
        int i4 = riS.h(4L, i3).b;
        VJ[] vjArr = new VJ[i4];
        for (int i5 = 0; i5 < i4; i5++) {
            vjArr[i5] = riS.q((i5 * 4) + 8, JI.c(i2));
        }
        return vjArr;
    }

    public RI s(int i, boolean z) {
        int i2 = this.b + i;
        long jP = p(i);
        if (jP != 0) {
            return new RI(this.a, this.c, (int) (((long) i2) + jP));
        }
        if (z) {
            return null;
        }
        throw new TI("Trying to decode null pointer for a non-nullable type.");
    }

    public InterfaceC0710eJ t(int i, boolean z, AbstractC0599cJ abstractC0599cJ) {
        VJ vjJ = v(i, z).j();
        if (vjJ.a()) {
            return abstractC0599cJ.a(vjJ, m(i + 4));
        }
        return null;
    }

    public String u(int i, boolean z) {
        byte[] bArrE = e(i, z ? 1 : 0, -1);
        if (bArrE == null) {
            return null;
        }
        return new String(bArrE, Charset.forName("utf8"));
    }

    public InterfaceC0544bK v(int i, boolean z) {
        OJ oj;
        int iM = m(i);
        if (iM != -1) {
            QI qi = this.c;
            if (iM < qi.a) {
                throw new TI("Trying to access handle out of order.");
            }
            if (iM >= qi.e) {
                throw new TI("Trying to access non present handle.");
            }
            qi.a = iM + 1;
            oj = (OJ) this.a.b.get(iM);
        } else {
            if (!z) {
                throw new TI("Trying to decode an invalid handle for a non-nullable type.");
            }
            oj = PJ.s;
        }
        return oj.H();
    }

    public final void w(int i, int i2) {
        if (this.a.a.limit() < i + i2) {
            throw new TI("Buffer is smaller than expected.");
        }
    }

    public RI(C0989jJ c0989jJ, QI qi, int i) {
        this.a = c0989jJ;
        c0989jJ.a.order(ByteOrder.LITTLE_ENDIAN);
        this.b = i;
        this.c = qi;
    }
}
