package com.google.zxing.oned.rss.expanded.decoders;

import com.google.zxing.FormatException;
import com.google.zxing.NotFoundException;

/* JADX INFO: compiled from: GeneralAppIdDecoder.java */
/* JADX INFO: loaded from: classes.dex */
final class r {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final com.google.zxing.common.a f4419a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final CurrentParsingState f4420b = new CurrentParsingState();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final StringBuilder f4421c = new StringBuilder();

    r(com.google.zxing.common.a aVar) {
        this.f4419a = aVar;
    }

    private m b(int i) {
        char c2;
        int iF = f(i, 5);
        if (iF == 15) {
            return new m(i + 5, '$');
        }
        if (iF >= 5 && iF < 15) {
            return new m(i + 5, (char) ((iF + 48) - 5));
        }
        int iF2 = f(i, 6);
        if (iF2 >= 32 && iF2 < 58) {
            return new m(i + 6, (char) (iF2 + 33));
        }
        switch (iF2) {
            case 58:
                c2 = '*';
                break;
            case 59:
                c2 = ',';
                break;
            case 60:
                c2 = '-';
                break;
            case 61:
                c2 = '.';
                break;
            case 62:
                c2 = '/';
                break;
            default:
                throw new IllegalStateException("Decoding invalid alphanumeric value: ".concat(String.valueOf(iF2)));
        }
        return new m(i + 6, c2);
    }

    private m d(int i) throws FormatException {
        char c2;
        int iF = f(i, 5);
        if (iF == 15) {
            return new m(i + 5, '$');
        }
        if (iF >= 5 && iF < 15) {
            return new m(i + 5, (char) ((iF + 48) - 5));
        }
        int iF2 = f(i, 7);
        if (iF2 >= 64 && iF2 < 90) {
            return new m(i + 7, (char) (iF2 + 1));
        }
        if (iF2 >= 90 && iF2 < 116) {
            return new m(i + 7, (char) (iF2 + 7));
        }
        switch (f(i, 8)) {
            case 232:
                c2 = '!';
                break;
            case 233:
                c2 = '\"';
                break;
            case 234:
                c2 = '%';
                break;
            case 235:
                c2 = '&';
                break;
            case 236:
                c2 = '\'';
                break;
            case 237:
                c2 = '(';
                break;
            case 238:
                c2 = ')';
                break;
            case 239:
                c2 = '*';
                break;
            case 240:
                c2 = '+';
                break;
            case 241:
                c2 = ',';
                break;
            case 242:
                c2 = '-';
                break;
            case 243:
                c2 = '.';
                break;
            case 244:
                c2 = '/';
                break;
            case 245:
                c2 = ':';
                break;
            case 246:
                c2 = ';';
                break;
            case 247:
                c2 = '<';
                break;
            case 248:
                c2 = '=';
                break;
            case 249:
                c2 = '>';
                break;
            case 250:
                c2 = '?';
                break;
            case 251:
                c2 = '_';
                break;
            case 252:
                c2 = ' ';
                break;
            default:
                throw FormatException.getFormatInstance();
        }
        return new m(i + 8, c2);
    }

    private o e(int i) throws FormatException {
        int i2 = i + 7;
        if (i2 > this.f4419a.l()) {
            int iF = f(i, 4);
            return iF == 0 ? new o(this.f4419a.l(), 10, 10) : new o(this.f4419a.l(), iF - 1, 10);
        }
        int iF2 = f(i, 7) - 8;
        return new o(i2, iF2 / 11, iF2 % 11);
    }

    static int g(com.google.zxing.common.a aVar, int i, int i2) {
        int i3 = 0;
        for (int i4 = 0; i4 < i2; i4++) {
            if (aVar.h(i + i4)) {
                i3 |= 1 << ((i2 - i4) - 1);
            }
        }
        return i3;
    }

    private boolean h(int i) {
        int i2 = i + 3;
        if (i2 > this.f4419a.l()) {
            return false;
        }
        while (i < i2) {
            if (this.f4419a.h(i)) {
                return false;
            }
            i++;
        }
        return true;
    }

    private boolean i(int i) {
        int i2;
        if (i + 1 > this.f4419a.l()) {
            return false;
        }
        for (int i3 = 0; i3 < 5 && (i2 = i3 + i) < this.f4419a.l(); i3++) {
            if (i3 == 2) {
                if (!this.f4419a.h(i + 2)) {
                    return false;
                }
            } else if (this.f4419a.h(i2)) {
                return false;
            }
        }
        return true;
    }

    private boolean j(int i) {
        int i2;
        if (i + 1 > this.f4419a.l()) {
            return false;
        }
        for (int i3 = 0; i3 < 4 && (i2 = i3 + i) < this.f4419a.l(); i3++) {
            if (this.f4419a.h(i2)) {
                return false;
            }
        }
        return true;
    }

    private boolean k(int i) {
        int iF;
        if (i + 5 > this.f4419a.l()) {
            return false;
        }
        int iF2 = f(i, 5);
        if (iF2 < 5 || iF2 >= 16) {
            return i + 6 <= this.f4419a.l() && (iF = f(i, 6)) >= 16 && iF < 63;
        }
        return true;
    }

    private boolean l(int i) {
        int iF;
        if (i + 5 > this.f4419a.l()) {
            return false;
        }
        int iF2 = f(i, 5);
        if (iF2 >= 5 && iF2 < 16) {
            return true;
        }
        if (i + 7 > this.f4419a.l()) {
            return false;
        }
        int iF3 = f(i, 7);
        if (iF3 < 64 || iF3 >= 116) {
            return i + 8 <= this.f4419a.l() && (iF = f(i, 8)) >= 232 && iF < 253;
        }
        return true;
    }

    private boolean m(int i) {
        if (i + 7 > this.f4419a.l()) {
            return i + 4 <= this.f4419a.l();
        }
        int i2 = i;
        while (true) {
            int i3 = i + 3;
            if (i2 >= i3) {
                return this.f4419a.h(i3);
            }
            if (this.f4419a.h(i2)) {
                return true;
            }
            i2++;
        }
    }

    private l n() {
        while (k(this.f4420b.a())) {
            m mVarB = b(this.f4420b.a());
            this.f4420b.h(mVarB.a());
            if (mVarB.c()) {
                return new l(new n(this.f4420b.a(), this.f4421c.toString()), true);
            }
            this.f4421c.append(mVarB.b());
        }
        if (h(this.f4420b.a())) {
            this.f4420b.b(3);
            this.f4420b.g();
        } else if (i(this.f4420b.a())) {
            if (this.f4420b.a() + 5 < this.f4419a.l()) {
                this.f4420b.b(5);
            } else {
                this.f4420b.h(this.f4419a.l());
            }
            this.f4420b.f();
        }
        return new l(false);
    }

    private n o() throws FormatException {
        l lVarQ;
        boolean zB;
        do {
            int iA = this.f4420b.a();
            if (this.f4420b.c()) {
                lVarQ = n();
                zB = lVarQ.b();
            } else if (this.f4420b.d()) {
                lVarQ = p();
                zB = lVarQ.b();
            } else {
                lVarQ = q();
                zB = lVarQ.b();
            }
            if (!(iA != this.f4420b.a()) && !zB) {
                break;
            }
        } while (!zB);
        return lVarQ.a();
    }

    private l p() throws FormatException {
        while (l(this.f4420b.a())) {
            m mVarD = d(this.f4420b.a());
            this.f4420b.h(mVarD.a());
            if (mVarD.c()) {
                return new l(new n(this.f4420b.a(), this.f4421c.toString()), true);
            }
            this.f4421c.append(mVarD.b());
        }
        if (h(this.f4420b.a())) {
            this.f4420b.b(3);
            this.f4420b.g();
        } else if (i(this.f4420b.a())) {
            if (this.f4420b.a() + 5 < this.f4419a.l()) {
                this.f4420b.b(5);
            } else {
                this.f4420b.h(this.f4419a.l());
            }
            this.f4420b.e();
        }
        return new l(false);
    }

    private l q() throws FormatException {
        while (m(this.f4420b.a())) {
            o oVarE = e(this.f4420b.a());
            this.f4420b.h(oVarE.a());
            if (oVarE.d()) {
                return new l(oVarE.e() ? new n(this.f4420b.a(), this.f4421c.toString()) : new n(this.f4420b.a(), this.f4421c.toString(), oVarE.c()), true);
            }
            this.f4421c.append(oVarE.b());
            if (oVarE.e()) {
                return new l(new n(this.f4420b.a(), this.f4421c.toString()), true);
            }
            this.f4421c.append(oVarE.c());
        }
        if (j(this.f4420b.a())) {
            this.f4420b.e();
            this.f4420b.b(4);
        }
        return new l(false);
    }

    String a(StringBuilder sb, int i) throws NotFoundException, FormatException {
        String str = null;
        while (true) {
            n nVarC = c(i, str);
            String strA = q.a(nVarC.b());
            if (strA != null) {
                sb.append(strA);
            }
            String strValueOf = nVarC.d() ? String.valueOf(nVarC.c()) : null;
            if (i == nVarC.a()) {
                return sb.toString();
            }
            i = nVarC.a();
            str = strValueOf;
        }
    }

    n c(int i, String str) throws FormatException {
        this.f4421c.setLength(0);
        if (str != null) {
            this.f4421c.append(str);
        }
        this.f4420b.h(i);
        n nVarO = o();
        return (nVarO == null || !nVarO.d()) ? new n(this.f4420b.a(), this.f4421c.toString()) : new n(this.f4420b.a(), this.f4421c.toString(), nVarO.c());
    }

    int f(int i, int i2) {
        return g(this.f4419a, i, i2);
    }
}
