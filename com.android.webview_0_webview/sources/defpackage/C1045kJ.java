package defpackage;

/* JADX INFO: renamed from: kJ, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class C1045kJ {
    public static final PI e = new PI(24, 0);
    public static final PI f = new PI(32, 1);
    public final PI a;
    public final int b;
    public final int c;
    public long d;

    public C1045kJ(int i) {
        this.a = e;
        this.b = i;
        this.c = 0;
        this.d = 0L;
    }

    public static boolean b(int i) {
        return (i & 3) != 0;
    }

    public boolean a(int i) {
        return (this.c & i) == i;
    }

    public boolean c(int i) {
        return (this.c & 3) == i;
    }

    public boolean d(int i, int i2) {
        if (this.b == i) {
            if ((this.c & 3) == i2) {
                return true;
            }
        }
        return false;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || C1045kJ.class != obj.getClass()) {
            return false;
        }
        C1045kJ c1045kJ = (C1045kJ) obj;
        PI pi = this.a;
        PI pi2 = c1045kJ.a;
        return (pi == pi2 ? true : pi == null ? false : pi.equals(pi2)) && this.c == c1045kJ.c && this.d == c1045kJ.d && this.b == c1045kJ.b;
    }

    public int hashCode() {
        PI pi = this.a;
        int iHashCode = ((((pi == null ? 0 : pi.hashCode()) + 31) * 31) + this.c) * 31;
        long j = this.d;
        return ((iHashCode + ((int) (j ^ (j >>> 32)))) * 31) + this.b;
    }

    public C1045kJ(int i, int i2, long j) {
        this.a = f;
        this.b = i;
        this.c = i2;
        this.d = j;
    }

    public C1045kJ(C0989jJ c0989jJ) {
        RI ri = new RI(c0989jJ);
        PI piF = ri.f();
        this.a = piF;
        int i = piF.b;
        if (i >= 0) {
            int i2 = piF.a;
            if (i2 < 24) {
                StringBuilder sbF = Z7.f("Incorrect message size, expecting at least 24, but got: ");
                sbF.append(piF.a);
                throw new TI(sbF.toString());
            }
            if (i == 0 && i2 != 24) {
                StringBuilder sbF2 = Z7.f("Incorrect message size for a message with 0 fields, expecting 24, but got: ");
                sbF2.append(piF.a);
                throw new TI(sbF2.toString());
            }
            if (piF.b == 1 && piF.a != 32) {
                StringBuilder sbF3 = Z7.f("Incorrect message size for a message with 1 fields, expecting 32, but got: ");
                sbF3.append(piF.a);
                throw new TI(sbF3.toString());
            }
            if (ri.m(8) == 0) {
                this.b = ri.m(12);
                int iM = ri.m(16);
                this.c = iM;
                if (b(iM)) {
                    if (this.a.a >= 32) {
                        this.d = ri.p(24);
                        return;
                    } else {
                        StringBuilder sbF4 = Z7.f("Incorrect message size, expecting at least 32 for a message with a request identifier, but got: ");
                        sbF4.append(this.a.a);
                        throw new TI(sbF4.toString());
                    }
                }
                this.d = 0L;
                return;
            }
            throw new TI("Non-zero interface ID, expecting zero since associated interfaces are not yet supported.");
        }
        StringBuilder sbF5 = Z7.f("Incorrect number of fields, expecting at least 0, but got: ");
        sbF5.append(piF.b);
        throw new TI(sbF5.toString());
    }
}
