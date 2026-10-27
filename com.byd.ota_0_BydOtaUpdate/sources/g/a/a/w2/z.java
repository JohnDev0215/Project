package g.a.a.w2;

import g.a.a.c1;
import g.a.a.e1;
import g.a.a.j1;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;

/* JADX INFO: loaded from: classes.dex */
public class z extends g.a.a.l {
    public static final g.a.a.m E3;
    public static final g.a.a.m F3;
    public static final g.a.a.m G3;
    public static final g.a.a.m H3;
    public static final g.a.a.m I3;
    public static final g.a.a.m J3;
    public static final g.a.a.m K3;
    public static final g.a.a.m L3;
    public static final g.a.a.m M3;
    public static final g.a.a.m N3;
    public static final g.a.a.m O3;
    public static final g.a.a.m P3;
    public static final g.a.a.m Q3;
    public static final g.a.a.m R3;
    public static final g.a.a.m S3;
    public static final g.a.a.m T3;
    public static final g.a.a.m U3;
    public static final g.a.a.m V3;
    public static final g.a.a.m W3;
    public static final g.a.a.m X3;
    public static final g.a.a.m Y3;
    public static final g.a.a.m Z3;
    public static final g.a.a.m a4;
    public static final g.a.a.m b4;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final g.a.a.m f4163c;
    public static final g.a.a.m c4;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final g.a.a.m f4164d;
    public static final g.a.a.m d4;
    public static final g.a.a.m e4;
    public static final g.a.a.m f4;
    public static final g.a.a.m g4;
    public static final g.a.a.m h4;
    public static boolean i4;
    public static final Hashtable j4;
    public static final Hashtable k4;
    public static final Hashtable l4;
    public static final Hashtable m4;
    public static final Hashtable n4;
    public static final Hashtable o4;
    public static final Boolean p4;
    public static final Boolean q4;
    public static final g.a.a.m s;
    public static final g.a.a.m x;
    public static final g.a.a.m y;
    public Vector r4 = new Vector();
    public Vector s4 = new Vector();
    public Vector t4 = new Vector();
    public g.a.a.r u4;
    public boolean v4;
    public int w4;

    static {
        g.a.a.m mVar = new g.a.a.m("2.5.4.6");
        f4163c = mVar;
        g.a.a.m mVar2 = new g.a.a.m("2.5.4.10");
        f4164d = mVar2;
        g.a.a.m mVar3 = new g.a.a.m("2.5.4.11");
        s = mVar3;
        g.a.a.m mVar4 = new g.a.a.m("2.5.4.12");
        x = mVar4;
        g.a.a.m mVar5 = new g.a.a.m("2.5.4.3");
        y = mVar5;
        g.a.a.m mVar6 = new g.a.a.m("2.5.4.5");
        E3 = mVar6;
        g.a.a.m mVar7 = new g.a.a.m("2.5.4.9");
        F3 = mVar7;
        G3 = mVar6;
        g.a.a.m mVar8 = new g.a.a.m("2.5.4.7");
        H3 = mVar8;
        g.a.a.m mVar9 = new g.a.a.m("2.5.4.8");
        I3 = mVar9;
        g.a.a.m mVar10 = new g.a.a.m("2.5.4.4");
        J3 = mVar10;
        g.a.a.m mVar11 = new g.a.a.m("2.5.4.42");
        K3 = mVar11;
        g.a.a.m mVar12 = new g.a.a.m("2.5.4.43");
        L3 = mVar12;
        g.a.a.m mVar13 = new g.a.a.m("2.5.4.44");
        M3 = mVar13;
        g.a.a.m mVar14 = new g.a.a.m("2.5.4.45");
        N3 = mVar14;
        g.a.a.m mVar15 = new g.a.a.m("2.5.4.15");
        O3 = mVar15;
        g.a.a.m mVar16 = new g.a.a.m("2.5.4.17");
        P3 = mVar16;
        g.a.a.m mVar17 = new g.a.a.m("2.5.4.46");
        Q3 = mVar17;
        g.a.a.m mVar18 = new g.a.a.m("2.5.4.65");
        R3 = mVar18;
        g.a.a.m mVar19 = new g.a.a.m("1.3.6.1.5.5.7.9.1");
        S3 = mVar19;
        g.a.a.m mVar20 = new g.a.a.m("1.3.6.1.5.5.7.9.2");
        T3 = mVar20;
        g.a.a.m mVar21 = new g.a.a.m("1.3.6.1.5.5.7.9.3");
        U3 = mVar21;
        g.a.a.m mVar22 = new g.a.a.m("1.3.6.1.5.5.7.9.4");
        V3 = mVar22;
        g.a.a.m mVar23 = new g.a.a.m("1.3.6.1.5.5.7.9.5");
        W3 = mVar23;
        g.a.a.m mVar24 = new g.a.a.m("1.3.36.8.3.14");
        X3 = mVar24;
        g.a.a.m mVar25 = new g.a.a.m("2.5.4.16");
        Y3 = mVar25;
        Z3 = new g.a.a.m("2.5.4.54");
        g.a.a.m mVar26 = a0.a2;
        a4 = mVar26;
        g.a.a.m mVar27 = a0.b2;
        b4 = mVar27;
        g.a.a.m mVar28 = g.a.a.p2.n.d0;
        c4 = mVar28;
        g.a.a.m mVar29 = g.a.a.p2.n.e0;
        d4 = mVar29;
        g.a.a.m mVar30 = g.a.a.p2.n.k0;
        e4 = mVar30;
        f4 = mVar28;
        g.a.a.m mVar31 = new g.a.a.m("0.9.2342.19200300.100.1.25");
        g4 = mVar31;
        g.a.a.m mVar32 = new g.a.a.m("0.9.2342.19200300.100.1.1");
        h4 = mVar32;
        i4 = false;
        Hashtable hashtable = new Hashtable();
        j4 = hashtable;
        Hashtable hashtable2 = new Hashtable();
        k4 = hashtable2;
        Hashtable hashtable3 = new Hashtable();
        l4 = hashtable3;
        Hashtable hashtable4 = new Hashtable();
        m4 = hashtable4;
        n4 = hashtable;
        o4 = hashtable4;
        p4 = new Boolean(true);
        q4 = new Boolean(false);
        hashtable.put(mVar, "C");
        hashtable.put(mVar2, "O");
        hashtable.put(mVar4, "T");
        hashtable.put(mVar3, "OU");
        hashtable.put(mVar5, "CN");
        hashtable.put(mVar8, "L");
        hashtable.put(mVar9, "ST");
        hashtable.put(mVar6, "SERIALNUMBER");
        hashtable.put(mVar28, "E");
        hashtable.put(mVar31, "DC");
        hashtable.put(mVar32, "UID");
        hashtable.put(mVar7, "STREET");
        hashtable.put(mVar10, "SURNAME");
        hashtable.put(mVar11, "GIVENNAME");
        hashtable.put(mVar12, "INITIALS");
        hashtable.put(mVar13, "GENERATION");
        hashtable.put(mVar30, "unstructuredAddress");
        hashtable.put(mVar29, "unstructuredName");
        hashtable.put(mVar14, "UniqueIdentifier");
        hashtable.put(mVar17, "DN");
        hashtable.put(mVar18, "Pseudonym");
        hashtable.put(mVar25, "PostalAddress");
        hashtable.put(mVar24, "NameAtBirth");
        hashtable.put(mVar22, "CountryOfCitizenship");
        hashtable.put(mVar23, "CountryOfResidence");
        hashtable.put(mVar21, "Gender");
        hashtable.put(mVar20, "PlaceOfBirth");
        hashtable.put(mVar19, "DateOfBirth");
        hashtable.put(mVar16, "PostalCode");
        hashtable.put(mVar15, "BusinessCategory");
        hashtable.put(mVar26, "TelephoneNumber");
        hashtable.put(mVar27, "Name");
        hashtable2.put(mVar, "C");
        hashtable2.put(mVar2, "O");
        hashtable2.put(mVar3, "OU");
        hashtable2.put(mVar5, "CN");
        hashtable2.put(mVar8, "L");
        hashtable2.put(mVar9, "ST");
        hashtable2.put(mVar7, "STREET");
        hashtable2.put(mVar31, "DC");
        hashtable2.put(mVar32, "UID");
        hashtable3.put(mVar, "C");
        hashtable3.put(mVar2, "O");
        hashtable3.put(mVar3, "OU");
        hashtable3.put(mVar5, "CN");
        hashtable3.put(mVar8, "L");
        hashtable3.put(mVar9, "ST");
        hashtable3.put(mVar7, "STREET");
        hashtable4.put("c", mVar);
        hashtable4.put("o", mVar2);
        hashtable4.put("t", mVar4);
        hashtable4.put("ou", mVar3);
        hashtable4.put("cn", mVar5);
        hashtable4.put("l", mVar8);
        hashtable4.put("st", mVar9);
        hashtable4.put("sn", mVar6);
        hashtable4.put("serialnumber", mVar6);
        hashtable4.put("street", mVar7);
        hashtable4.put("emailaddress", mVar28);
        hashtable4.put("dc", mVar31);
        hashtable4.put("e", mVar28);
        hashtable4.put("uid", mVar32);
        hashtable4.put("surname", mVar10);
        hashtable4.put("givenname", mVar11);
        hashtable4.put("initials", mVar12);
        hashtable4.put("generation", mVar13);
        hashtable4.put("unstructuredaddress", mVar30);
        hashtable4.put("unstructuredname", mVar29);
        hashtable4.put("uniqueidentifier", mVar14);
        hashtable4.put("dn", mVar17);
        hashtable4.put("pseudonym", mVar18);
        hashtable4.put("postaladdress", mVar25);
        hashtable4.put("nameofbirth", mVar24);
        hashtable4.put("countryofcitizenship", mVar22);
        hashtable4.put("countryofresidence", mVar23);
        hashtable4.put("gender", mVar21);
        hashtable4.put("placeofbirth", mVar20);
        hashtable4.put("dateofbirth", mVar19);
        hashtable4.put("postalcode", mVar16);
        hashtable4.put("businesscategory", mVar15);
        hashtable4.put("telephonenumber", mVar26);
        hashtable4.put("name", mVar27);
    }

    public z() {
    }

    public z(g.a.a.r rVar) {
        Vector vector;
        this.u4 = rVar;
        Enumeration enumerationR = rVar.r();
        while (enumerationR.hasMoreElements()) {
            g.a.a.t tVarO = g.a.a.t.o(((g.a.a.e) enumerationR.nextElement()).c());
            int i2 = 0;
            while (i2 < tVarO.size()) {
                g.a.a.r rVarN = g.a.a.r.n(tVarO.r(i2).c());
                if (rVarN.size() != 2) {
                    throw new IllegalArgumentException("badly sized pair");
                }
                this.r4.addElement(g.a.a.m.s(rVarN.q(0)));
                g.a.a.e eVarQ = rVarN.q(1);
                if (!(eVarQ instanceof g.a.a.w) || (eVarQ instanceof j1)) {
                    try {
                        this.s4.addElement("#" + h(g.a.f.j.d.b(eVarQ.c().f("DER"))));
                    } catch (IOException unused) {
                        throw new IllegalArgumentException("cannot encode value");
                    }
                } else {
                    String strD = ((g.a.a.w) eVarQ).d();
                    if (strD.length() <= 0 || strD.charAt(0) != '#') {
                        vector = this.s4;
                    } else {
                        vector = this.s4;
                        strD = "\\" + strD;
                    }
                    vector.addElement(strD);
                }
                this.t4.addElement(i2 != 0 ? p4 : q4);
                i2++;
            }
        }
    }

    public static z l(Object obj) {
        return (obj == null || (obj instanceof z)) ? (z) obj : obj instanceof g.a.a.v2.c ? new z(g.a.a.r.n(((g.a.a.v2.c) obj).c())) : new z(g.a.a.r.n(obj));
    }

    @Override // g.a.a.l, g.a.a.e
    public g.a.a.q c() {
        if (this.u4 == null) {
            g.a.a.f fVar = new g.a.a.f();
            g.a.a.f fVar2 = new g.a.a.f();
            if (this.r4.size() != 0) {
                new g.a.a.f().a((g.a.a.m) this.r4.elementAt(0));
                throw null;
            }
            fVar.a(new e1(fVar2));
            this.u4 = new c1(fVar);
        }
        return this.u4;
    }

    @Override // g.a.a.l
    public boolean equals(Object obj) {
        int i2;
        int i3;
        boolean z;
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof z) && !(obj instanceof g.a.a.r)) {
            return false;
        }
        if (c().equals(((g.a.a.e) obj).c())) {
            return true;
        }
        try {
            z zVarL = l(obj);
            int size = this.r4.size();
            if (size != zVarL.r4.size()) {
                return false;
            }
            boolean[] zArr = new boolean[size];
            int i5 = -1;
            if (this.r4.elementAt(0).equals(zVarL.r4.elementAt(0))) {
                i3 = 1;
                i5 = size;
                i2 = 0;
            } else {
                i2 = size - 1;
                i3 = -1;
            }
            while (i2 != i5) {
                g.a.a.m mVar = (g.a.a.m) this.r4.elementAt(i2);
                String str = (String) this.s4.elementAt(i2);
                int i6 = 0;
                while (true) {
                    if (i6 >= size) {
                        z = false;
                        break;
                    }
                    if (!zArr[i6] && mVar.equals((g.a.a.m) zVarL.r4.elementAt(i6)) && k(str, (String) zVarL.s4.elementAt(i6))) {
                        zArr[i6] = true;
                        z = true;
                        break;
                    }
                    i6++;
                }
                if (!z) {
                    return false;
                }
                i2 += i3;
            }
            return true;
        } catch (IllegalArgumentException unused) {
            return false;
        }
    }

    public final void g(StringBuffer stringBuffer, Hashtable hashtable, g.a.a.m mVar, String str) {
        String strR = (String) hashtable.get(mVar);
        if (strR == null) {
            strR = mVar.r();
        }
        stringBuffer.append(strR);
        stringBuffer.append('=');
        int length = stringBuffer.length();
        stringBuffer.append(str);
        int length2 = stringBuffer.length();
        if (str.length() >= 2 && str.charAt(0) == '\\' && str.charAt(1) == '#') {
            length += 2;
        }
        while (length < length2 && stringBuffer.charAt(length) == ' ') {
            stringBuffer.insert(length, "\\");
            length += 2;
            length2++;
        }
        while (true) {
            length2--;
            if (length2 <= length || stringBuffer.charAt(length2) != ' ') {
                break;
            } else {
                stringBuffer.insert(length2, '\\');
            }
        }
        while (length <= length2) {
            char cCharAt = stringBuffer.charAt(length);
            if (cCharAt != '\"' && cCharAt != '\\' && cCharAt != '+' && cCharAt != ',') {
                switch (cCharAt) {
                    case ';':
                    case '<':
                    case '=':
                    case '>':
                        break;
                    default:
                        length++;
                        break;
                }
            }
            stringBuffer.insert(length, "\\");
            length += 2;
            length2++;
        }
    }

    public final String h(byte[] bArr) {
        int length = bArr.length;
        char[] cArr = new char[length];
        for (int i2 = 0; i2 != length; i2++) {
            cArr[i2] = (char) (bArr[i2] & 255);
        }
        return new String(cArr);
    }

    @Override // g.a.a.l
    public int hashCode() {
        if (this.v4) {
            return this.w4;
        }
        this.v4 = true;
        for (int i2 = 0; i2 != this.r4.size(); i2++) {
            String strM = m(i((String) this.s4.elementAt(i2)));
            int iHashCode = this.w4 ^ this.r4.elementAt(i2).hashCode();
            this.w4 = iHashCode;
            this.w4 = strM.hashCode() ^ iHashCode;
        }
        return this.w4;
    }

    public final String i(String str) {
        String strG = g.a.f.i.g(str.trim());
        if (strG.length() <= 0 || strG.charAt(0) != '#') {
            return strG;
        }
        g.a.a.e eVarJ = j(strG);
        return eVarJ instanceof g.a.a.w ? g.a.f.i.g(((g.a.a.w) eVarJ).d().trim()) : strG;
    }

    public final g.a.a.q j(String str) {
        try {
            return g.a.a.q.j(g.a.f.j.d.a(str.substring(1)));
        } catch (IOException e2) {
            throw new IllegalStateException("unknown encoding in name: " + e2);
        }
    }

    public final boolean k(String str, String str2) {
        String strI = i(str);
        String strI2 = i(str2);
        return strI.equals(strI2) || m(strI).equals(m(strI2));
    }

    public final String m(String str) {
        StringBuffer stringBuffer = new StringBuffer();
        if (str.length() != 0) {
            char cCharAt = str.charAt(0);
            stringBuffer.append(cCharAt);
            int i2 = 1;
            while (i2 < str.length()) {
                char cCharAt2 = str.charAt(i2);
                if (cCharAt != ' ' || cCharAt2 != ' ') {
                    stringBuffer.append(cCharAt2);
                }
                i2++;
                cCharAt = cCharAt2;
            }
        }
        return stringBuffer.toString();
    }

    public String n(boolean z, Hashtable hashtable) {
        StringBuffer stringBuffer = new StringBuffer();
        Vector vector = new Vector();
        StringBuffer stringBuffer2 = null;
        for (int i2 = 0; i2 < this.r4.size(); i2++) {
            if (((Boolean) this.t4.elementAt(i2)).booleanValue()) {
                stringBuffer2.append('+');
                g(stringBuffer2, hashtable, (g.a.a.m) this.r4.elementAt(i2), (String) this.s4.elementAt(i2));
            } else {
                stringBuffer2 = new StringBuffer();
                g(stringBuffer2, hashtable, (g.a.a.m) this.r4.elementAt(i2), (String) this.s4.elementAt(i2));
                vector.addElement(stringBuffer2);
            }
        }
        boolean z2 = true;
        if (z) {
            for (int size = vector.size() - 1; size >= 0; size--) {
                if (z2) {
                    z2 = false;
                } else {
                    stringBuffer.append(',');
                }
                stringBuffer.append(vector.elementAt(size).toString());
            }
        } else {
            for (int i3 = 0; i3 < vector.size(); i3++) {
                if (z2) {
                    z2 = false;
                } else {
                    stringBuffer.append(',');
                }
                stringBuffer.append(vector.elementAt(i3).toString());
            }
        }
        return stringBuffer.toString();
    }

    public String toString() {
        return n(i4, j4);
    }
}
