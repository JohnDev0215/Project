package p;

import java.io.EOFException;
import java.util.ArrayList;
import okio.ByteString;

/* JADX INFO: loaded from: classes.dex */
public abstract class f {
    public static final ByteString a;
    public static final ByteString b;
    public static final ByteString c;
    public static final ByteString d;
    public static final ByteString e;

    static {
        ByteString byteString = ByteString.k;
        a = qo.p("/");
        b = qo.p("\\");
        c = qo.p("/\\");
        d = qo.p(".");
        e = qo.p("..");
    }

    public static final int a(os1 os1Var) {
        if (os1Var.c.e() == 0) {
            return -1;
        }
        ByteString byteString = os1Var.c;
        if (byteString.k(0) != 47) {
            if (byteString.k(0) != 92) {
                if (byteString.e() <= 2 || byteString.k(1) != 58 || byteString.k(2) != 92) {
                    return -1;
                }
                char cK = (char) byteString.k(0);
                return (('a' > cK || cK >= '{') && ('A' > cK || cK >= '[')) ? -1 : 3;
            }
            if (byteString.e() > 2 && byteString.k(1) == 92) {
                int iG = byteString.g(2, b);
                return iG == -1 ? byteString.e() : iG;
            }
        }
        return 1;
    }

    public static final os1 b(os1 os1Var, os1 os1Var2, boolean z) {
        v03.j(os1Var, "<this>");
        v03.j(os1Var2, "child");
        if (a(os1Var2) != -1 || os1Var2.g() != null) {
            return os1Var2;
        }
        ByteString byteStringC = c(os1Var);
        if (byteStringC == null && (byteStringC = c(os1Var2)) == null) {
            byteStringC = f(os1.e);
        }
        fo foVar = new fo();
        foVar.T0(os1Var.c);
        if (foVar.e > 0) {
            foVar.T0(byteStringC);
        }
        foVar.T0(os1Var2.c);
        return d(foVar, z);
    }

    public static final ByteString c(os1 os1Var) {
        ByteString byteString = os1Var.c;
        ByteString byteString2 = a;
        if (ByteString.i(byteString, byteString2) != -1) {
            return byteString2;
        }
        ByteString byteString3 = b;
        if (ByteString.i(os1Var.c, byteString3) != -1) {
            return byteString3;
        }
        return null;
    }

    public static final os1 d(fo foVar, boolean z) throws EOFException {
        ByteString byteString;
        char cG0;
        ByteString byteString2;
        ByteString byteStringV;
        fo foVar2 = new fo();
        ByteString byteStringE = null;
        int i = 0;
        while (true) {
            if (!foVar.x0(0L, a)) {
                byteString = b;
                if (!foVar.x0(0L, byteString)) {
                    break;
                }
            }
            byte b2 = foVar.readByte();
            if (byteStringE == null) {
                byteStringE = e(b2);
            }
            i++;
        }
        boolean z2 = i >= 2 && v03.b(byteStringE, byteString);
        ByteString byteString3 = c;
        if (z2) {
            v03.h(byteStringE);
            foVar2.T0(byteStringE);
            foVar2.T0(byteStringE);
        } else if (i > 0) {
            v03.h(byteStringE);
            foVar2.T0(byteStringE);
        } else {
            long jB0 = foVar.b0(byteString3);
            if (byteStringE == null) {
                byteStringE = jB0 == -1 ? f(os1.e) : e(foVar.G0(jB0));
            }
            if (v03.b(byteStringE, byteString) && foVar.e >= 2 && foVar.G0(1L) == 58 && (('a' <= (cG0 = (char) foVar.G0(0L)) && cG0 < '{') || ('A' <= cG0 && cG0 < '['))) {
                if (jB0 == 2) {
                    foVar2.Y(foVar, 3L);
                } else {
                    foVar2.Y(foVar, 2L);
                }
            }
        }
        boolean z3 = foVar2.e > 0;
        ArrayList arrayList = new ArrayList();
        while (true) {
            boolean zQ = foVar.Q();
            byteString2 = d;
            if (zQ) {
                break;
            }
            long jB02 = foVar.b0(byteString3);
            if (jB02 == -1) {
                byteStringV = foVar.v(foVar.e);
            } else {
                byteStringV = foVar.v(jB02);
                foVar.readByte();
            }
            ByteString byteString4 = e;
            if (v03.b(byteStringV, byteString4)) {
                if (!z3 || !arrayList.isEmpty()) {
                    if (!z || (!z3 && (arrayList.isEmpty() || v03.b(ou.i0(arrayList), byteString4)))) {
                        arrayList.add(byteStringV);
                    } else if (!z2 || arrayList.size() != 1) {
                        if (!arrayList.isEmpty()) {
                            arrayList.remove(v03.q(arrayList));
                        }
                    }
                }
            } else if (!v03.b(byteStringV, byteString2) && !v03.b(byteStringV, ByteString.k)) {
                arrayList.add(byteStringV);
            }
        }
        int size = arrayList.size();
        for (int i2 = 0; i2 < size; i2++) {
            if (i2 > 0) {
                foVar2.T0(byteStringE);
            }
            foVar2.T0((ByteString) arrayList.get(i2));
        }
        if (foVar2.e == 0) {
            foVar2.T0(byteString2);
        }
        return new os1(foVar2.v(foVar2.e));
    }

    public static final ByteString e(byte b2) {
        if (b2 == 47) {
            return a;
        }
        if (b2 == 92) {
            return b;
        }
        throw new IllegalArgumentException(u4.e("not a directory separator: ", b2));
    }

    public static final ByteString f(String str) {
        if (v03.b(str, "/")) {
            return a;
        }
        if (v03.b(str, "\\")) {
            return b;
        }
        throw new IllegalArgumentException(u4.x("not a directory separator: ", str));
    }
}
