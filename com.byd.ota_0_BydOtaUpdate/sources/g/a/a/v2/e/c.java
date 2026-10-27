package g.a.a.v2.e;

import g.a.a.e;
import g.a.a.j1;
import g.a.a.q;
import g.a.a.w;
import g.a.f.i;
import java.io.IOException;
import java.util.Hashtable;

/* JADX INFO: loaded from: classes.dex */
public class c {
    public static void a(StringBuffer stringBuffer, g.a.a.v2.b bVar, Hashtable hashtable) {
        if (!bVar.j()) {
            if (bVar.g() != null) {
                b(stringBuffer, bVar.g(), hashtable);
                return;
            }
            return;
        }
        g.a.a.v2.a[] aVarArrI = bVar.i();
        boolean z = true;
        for (int i2 = 0; i2 != aVarArrI.length; i2++) {
            if (z) {
                z = false;
            } else {
                stringBuffer.append('+');
            }
            b(stringBuffer, aVarArrI[i2], hashtable);
        }
    }

    public static void b(StringBuffer stringBuffer, g.a.a.v2.a aVar, Hashtable hashtable) {
        String strR = (String) hashtable.get(aVar.h());
        if (strR == null) {
            strR = aVar.h().r();
        }
        stringBuffer.append(strR);
        stringBuffer.append('=');
        stringBuffer.append(i(aVar.i()));
    }

    public static boolean c(g.a.a.v2.a aVar, g.a.a.v2.a aVar2) {
        if (aVar == aVar2) {
            return true;
        }
        return aVar != null && aVar2 != null && aVar.h().equals(aVar2.h()) && e(i(aVar.i())).equals(e(i(aVar2.i())));
    }

    public static String d(byte[] bArr) {
        int length = bArr.length;
        char[] cArr = new char[length];
        for (int i2 = 0; i2 != length; i2++) {
            cArr[i2] = (char) (bArr[i2] & 255);
        }
        return new String(cArr);
    }

    public static String e(String str) {
        String strG = i.g(str);
        int i2 = 0;
        if (strG.length() > 0 && strG.charAt(0) == '#') {
            e eVarF = f(strG);
            if (eVarF instanceof w) {
                strG = i.g(((w) eVarF).d());
            }
        }
        if (strG.length() > 1) {
            while (true) {
                int i3 = i2 + 1;
                if (i3 >= strG.length() || strG.charAt(i2) != '\\' || strG.charAt(i3) != ' ') {
                    break;
                }
                i2 += 2;
            }
            int length = strG.length() - 1;
            while (true) {
                int i4 = length - 1;
                if (i4 <= 0 || strG.charAt(i4) != '\\' || strG.charAt(length) != ' ') {
                    break;
                }
                length -= 2;
            }
            if (i2 > 0 || length < strG.length() - 1) {
                strG = strG.substring(i2, length + 1);
            }
        }
        return h(strG);
    }

    public static q f(String str) {
        try {
            return q.j(g.a.f.j.d.a(str.substring(1)));
        } catch (IOException e2) {
            throw new IllegalStateException("unknown encoding in name: " + e2);
        }
    }

    public static boolean g(g.a.a.v2.b bVar, g.a.a.v2.b bVar2) {
        if (!bVar.j()) {
            if (bVar2.j()) {
                return false;
            }
            return c(bVar.g(), bVar2.g());
        }
        if (!bVar2.j()) {
            return false;
        }
        g.a.a.v2.a[] aVarArrI = bVar.i();
        g.a.a.v2.a[] aVarArrI2 = bVar2.i();
        if (aVarArrI.length != aVarArrI2.length) {
            return false;
        }
        for (int i2 = 0; i2 != aVarArrI.length; i2++) {
            if (!c(aVarArrI[i2], aVarArrI2[i2])) {
                return false;
            }
        }
        return true;
    }

    public static String h(String str) {
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

    public static String i(e eVar) {
        StringBuffer stringBuffer = new StringBuffer();
        if (!(eVar instanceof w) || (eVar instanceof j1)) {
            try {
                stringBuffer.append("#" + d(g.a.f.j.d.b(eVar.c().f("DER"))));
            } catch (IOException unused) {
                throw new IllegalArgumentException("Other value has no encoded form");
            }
        } else {
            String strD = ((w) eVar).d();
            if (strD.length() > 0 && strD.charAt(0) == '#') {
                strD = "\\" + strD;
            }
            stringBuffer.append(strD);
        }
        int length = stringBuffer.length();
        int i2 = (stringBuffer.length() >= 2 && stringBuffer.charAt(0) == '\\' && stringBuffer.charAt(1) == '#') ? 2 : 0;
        while (i2 != length) {
            if (stringBuffer.charAt(i2) == ',' || stringBuffer.charAt(i2) == '\"' || stringBuffer.charAt(i2) == '\\' || stringBuffer.charAt(i2) == '+' || stringBuffer.charAt(i2) == '=' || stringBuffer.charAt(i2) == '<' || stringBuffer.charAt(i2) == '>' || stringBuffer.charAt(i2) == ';') {
                stringBuffer.insert(i2, "\\");
                i2++;
                length++;
            }
            i2++;
        }
        if (stringBuffer.length() > 0) {
            for (int i3 = 0; stringBuffer.length() > i3 && stringBuffer.charAt(i3) == ' '; i3 += 2) {
                stringBuffer.insert(i3, "\\");
            }
        }
        for (int length2 = stringBuffer.length() - 1; length2 >= 0 && stringBuffer.charAt(length2) == ' '; length2--) {
            stringBuffer.insert(length2, '\\');
        }
        return stringBuffer.toString();
    }
}
