package g.a.a.u2;

import g.a.a.b1;
import g.a.a.c;
import g.a.a.c0;
import g.a.a.c1;
import g.a.a.e;
import g.a.a.f0;
import g.a.a.g;
import g.a.a.g1;
import g.a.a.h;
import g.a.a.h0;
import g.a.a.i1;
import g.a.a.j;
import g.a.a.j0;
import g.a.a.k1;
import g.a.a.l1;
import g.a.a.m;
import g.a.a.m0;
import g.a.a.n;
import g.a.a.n0;
import g.a.a.o0;
import g.a.a.p0;
import g.a.a.q;
import g.a.a.r;
import g.a.a.t;
import g.a.a.u0;
import g.a.a.v0;
import g.a.a.w0;
import g.a.a.x;
import g.a.a.y;
import g.a.a.z;
import g.a.f.i;
import g.a.f.j.d;
import java.io.IOException;
import java.math.BigInteger;
import java.util.Enumeration;

/* JADX INFO: loaded from: classes.dex */
public class a {
    public static void a(String str, boolean z, q qVar, StringBuffer stringBuffer) {
        StringBuilder sb;
        BigInteger bigIntegerP;
        String str2;
        String strF;
        String strR;
        StringBuilder sb2;
        int length;
        String strD = i.d();
        if (!(qVar instanceof r)) {
            if (qVar instanceof x) {
                String str3 = str + "    ";
                stringBuffer.append(str);
                stringBuffer.append(qVar instanceof j0 ? "BER Tagged [" : "Tagged [");
                x xVar = (x) qVar;
                stringBuffer.append(Integer.toString(xVar.q()));
                stringBuffer.append(']');
                if (!xVar.r()) {
                    stringBuffer.append(" IMPLICIT ");
                }
                stringBuffer.append(strD);
                if (!xVar.isEmpty()) {
                    a(str3, z, xVar.p(), stringBuffer);
                    return;
                } else {
                    stringBuffer.append(str3);
                    stringBuffer.append("EMPTY");
                }
            } else if (qVar instanceof t) {
                Enumeration enumerationS = ((t) qVar).s();
                String str4 = str + "    ";
                stringBuffer.append(str);
                stringBuffer.append(qVar instanceof h0 ? "BER Set" : "DER Set");
                while (true) {
                    stringBuffer.append(strD);
                    while (enumerationS.hasMoreElements()) {
                        Object objNextElement = enumerationS.nextElement();
                        if (objNextElement == null) {
                            break;
                        } else {
                            a(str4, z, objNextElement instanceof q ? (q) objNextElement : ((e) objNextElement).c(), stringBuffer);
                        }
                    }
                    return;
                    stringBuffer.append(str4);
                    stringBuffer.append("NULL");
                }
            } else {
                if (!(qVar instanceof n)) {
                    if (qVar instanceof m) {
                        sb = new StringBuilder();
                        sb.append(str);
                        sb.append("ObjectIdentifier(");
                        sb.append(((m) qVar).r());
                    } else if (qVar instanceof c) {
                        sb = new StringBuilder();
                        sb.append(str);
                        sb.append("Boolean(");
                        sb.append(((c) qVar).r());
                    } else {
                        if (!(qVar instanceof j)) {
                            if (qVar instanceof o0) {
                                o0 o0Var = (o0) qVar;
                                stringBuffer.append(str + "DER Bit String[" + o0Var.p().length + ", " + o0Var.r() + "] ");
                                if (z) {
                                    strF = e(str, o0Var.p());
                                }
                            } else {
                                if (qVar instanceof v0) {
                                    sb = new StringBuilder();
                                    sb.append(str);
                                    sb.append("IA5String(");
                                    strR = ((v0) qVar).d();
                                } else if (qVar instanceof i1) {
                                    sb = new StringBuilder();
                                    sb.append(str);
                                    sb.append("UTF8String(");
                                    strR = ((i1) qVar).d();
                                } else if (qVar instanceof b1) {
                                    sb = new StringBuilder();
                                    sb.append(str);
                                    sb.append("PrintableString(");
                                    strR = ((b1) qVar).d();
                                } else if (qVar instanceof l1) {
                                    sb = new StringBuilder();
                                    sb.append(str);
                                    sb.append("VisibleString(");
                                    strR = ((l1) qVar).d();
                                } else if (qVar instanceof n0) {
                                    sb = new StringBuilder();
                                    sb.append(str);
                                    sb.append("BMPString(");
                                    strR = ((n0) qVar).d();
                                } else if (qVar instanceof g1) {
                                    sb = new StringBuilder();
                                    sb.append(str);
                                    sb.append("T61String(");
                                    strR = ((g1) qVar).d();
                                } else if (qVar instanceof u0) {
                                    sb = new StringBuilder();
                                    sb.append(str);
                                    sb.append("GraphicString(");
                                    strR = ((u0) qVar).d();
                                } else if (qVar instanceof k1) {
                                    sb = new StringBuilder();
                                    sb.append(str);
                                    sb.append("VideotexString(");
                                    strR = ((k1) qVar).d();
                                } else if (qVar instanceof y) {
                                    sb = new StringBuilder();
                                    sb.append(str);
                                    sb.append("UTCTime(");
                                    strR = ((y) qVar).p();
                                } else if (qVar instanceof h) {
                                    sb = new StringBuilder();
                                    sb.append(str);
                                    sb.append("GeneralizedTime(");
                                    strR = ((h) qVar).r();
                                } else {
                                    if (qVar instanceof z) {
                                        str2 = "BER";
                                    } else if (qVar instanceof m0) {
                                        str2 = "DER";
                                    } else if (qVar instanceof g) {
                                        sb = new StringBuilder();
                                        sb.append(str);
                                        sb.append("DER Enumerated(");
                                        bigIntegerP = ((g) qVar).p();
                                    } else {
                                        if (qVar instanceof p0) {
                                            p0 p0Var = (p0) qVar;
                                            stringBuffer.append(str + "External " + strD);
                                            StringBuilder sb3 = new StringBuilder();
                                            sb3.append(str);
                                            sb3.append("    ");
                                            String string = sb3.toString();
                                            if (p0Var.o() != null) {
                                                stringBuffer.append(string + "Direct Reference: " + p0Var.o().r() + strD);
                                            }
                                            if (p0Var.r() != null) {
                                                stringBuffer.append(string + "Indirect Reference: " + p0Var.r().toString() + strD);
                                            }
                                            if (p0Var.n() != null) {
                                                a(string, z, p0Var.n(), stringBuffer);
                                            }
                                            stringBuffer.append(string + "Encoding: " + p0Var.p() + strD);
                                            a(string, z, p0Var.q(), stringBuffer);
                                            return;
                                        }
                                        sb = new StringBuilder();
                                        sb.append(str);
                                        sb.append(qVar.toString());
                                        sb.append(strD);
                                        strF = sb.toString();
                                    }
                                    strF = f(str2, str, z, qVar, strD);
                                }
                                sb.append(strR);
                                sb.append(") ");
                                sb.append(strD);
                                strF = sb.toString();
                            }
                            stringBuffer.append(strF);
                            return;
                        }
                        sb = new StringBuilder();
                        sb.append(str);
                        sb.append("Integer(");
                        bigIntegerP = ((j) qVar).q();
                        sb.append(bigIntegerP);
                    }
                    sb.append(")");
                    sb.append(strD);
                    strF = sb.toString();
                    stringBuffer.append(strF);
                    return;
                }
                n nVar = (n) qVar;
                if (qVar instanceof c0) {
                    sb2 = new StringBuilder();
                    sb2.append(str);
                    sb2.append("BER Constructed Octet String[");
                    length = nVar.p().length;
                } else {
                    sb2 = new StringBuilder();
                    sb2.append(str);
                    sb2.append("DER Octet String[");
                    length = nVar.p().length;
                }
                sb2.append(length);
                sb2.append("] ");
                stringBuffer.append(sb2.toString());
                if (z) {
                    strF = e(str, nVar.p());
                    stringBuffer.append(strF);
                    return;
                }
            }
            stringBuffer.append(strD);
            return;
        }
        Enumeration enumerationR = ((r) qVar).r();
        String str5 = str + "    ";
        stringBuffer.append(str);
        stringBuffer.append(qVar instanceof f0 ? "BER Sequence" : qVar instanceof c1 ? "DER Sequence" : "Sequence");
        while (true) {
            stringBuffer.append(strD);
            while (enumerationR.hasMoreElements()) {
                Object objNextElement2 = enumerationR.nextElement();
                if (objNextElement2 == null || objNextElement2.equals(w0.f4105c)) {
                    break;
                } else {
                    a(str5, z, objNextElement2 instanceof q ? (q) objNextElement2 : ((e) objNextElement2).c(), stringBuffer);
                }
            }
            return;
            stringBuffer.append(str5);
            stringBuffer.append("NULL");
        }
    }

    public static String b(byte[] bArr, int i2, int i3) {
        StringBuffer stringBuffer = new StringBuffer();
        for (int i4 = i2; i4 != i2 + i3; i4++) {
            if (bArr[i4] >= 32 && bArr[i4] <= 126) {
                stringBuffer.append((char) bArr[i4]);
            }
        }
        return stringBuffer.toString();
    }

    public static String c(Object obj) {
        return d(obj, false);
    }

    public static String d(Object obj, boolean z) {
        q qVarC;
        StringBuffer stringBuffer = new StringBuffer();
        if (obj instanceof q) {
            qVarC = (q) obj;
        } else {
            if (!(obj instanceof e)) {
                return "unknown object type " + obj.toString();
            }
            qVarC = ((e) obj).c();
        }
        a("", z, qVarC, stringBuffer);
        return stringBuffer.toString();
    }

    public static String e(String str, byte[] bArr) {
        String strB;
        String strD = i.d();
        StringBuffer stringBuffer = new StringBuffer();
        String str2 = str + "    ";
        stringBuffer.append(strD);
        for (int i2 = 0; i2 < bArr.length; i2 += 32) {
            int length = bArr.length - i2;
            stringBuffer.append(str2);
            if (length > 32) {
                stringBuffer.append(i.b(d.c(bArr, i2, 32)));
                stringBuffer.append("    ");
                strB = b(bArr, i2, 32);
            } else {
                stringBuffer.append(i.b(d.c(bArr, i2, bArr.length - i2)));
                for (int length2 = bArr.length - i2; length2 != 32; length2++) {
                    stringBuffer.append("  ");
                }
                stringBuffer.append("    ");
                strB = b(bArr, i2, bArr.length - i2);
            }
            stringBuffer.append(strB);
            stringBuffer.append(strD);
        }
        return stringBuffer.toString();
    }

    public static String f(String str, String str2, boolean z, q qVar, String str3) {
        g.a.a.a aVarP = g.a.a.a.p(qVar);
        StringBuffer stringBuffer = new StringBuffer();
        if (!aVarP.k()) {
            return str2 + str + " ApplicationSpecific[" + aVarP.n() + "] (" + i.b(d.b(aVarP.o())) + ")" + str3;
        }
        try {
            r rVarN = r.n(aVarP.q(16));
            stringBuffer.append(str2 + str + " ApplicationSpecific[" + aVarP.n() + "]" + str3);
            Enumeration enumerationR = rVarN.r();
            while (enumerationR.hasMoreElements()) {
                a(str2 + "    ", z, (q) enumerationR.nextElement(), stringBuffer);
            }
        } catch (IOException e2) {
            stringBuffer.append(e2);
        }
        return stringBuffer.toString();
    }
}
