package Q8;

import g1.AbstractC1274a;
import h8.AbstractC1387d;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import n7.AbstractC1996k;
import n7.AbstractC2000o;

/* JADX INFO: loaded from: classes.dex */
public abstract class h extends o {
    public static boolean C0(CharSequence charSequence, CharSequence charSequence2, boolean z6) {
        A7.m.f(charSequence, "<this>");
        A7.m.f(charSequence2, "other");
        if (charSequence2 instanceof String) {
            if (K0(charSequence, (String) charSequence2, 0, z6, 2) < 0) {
                return false;
            }
        } else if (I0(charSequence, charSequence2, 0, charSequence.length(), z6, false) < 0) {
            return false;
        }
        return true;
    }

    public static boolean D0(CharSequence charSequence, char c) {
        A7.m.f(charSequence, "<this>");
        return J0(charSequence, c, 0, false, 2) >= 0;
    }

    public static boolean E0(CharSequence charSequence, String str) {
        return charSequence instanceof String ? o.s0((String) charSequence, str, false) : R0(charSequence, charSequence.length() - str.length(), str, 0, str.length(), false);
    }

    public static boolean F0(String str, char c) {
        A7.m.f(str, "<this>");
        return str.length() > 0 && AbstractC1274a.F(str.charAt(G0(str)), c, false);
    }

    public static int G0(CharSequence charSequence) {
        A7.m.f(charSequence, "<this>");
        return charSequence.length() - 1;
    }

    public static final int H0(int i7, CharSequence charSequence, String str, boolean z6) {
        A7.m.f(charSequence, "<this>");
        A7.m.f(str, "string");
        return (z6 || !(charSequence instanceof String)) ? I0(charSequence, str, i7, charSequence.length(), z6, false) : ((String) charSequence).indexOf(str, i7);
    }

    public static final int I0(CharSequence charSequence, CharSequence charSequence2, int i7, int i10, boolean z6, boolean z10) {
        G7.b bVar;
        if (z10) {
            int iG0 = G0(charSequence);
            if (i7 > iG0) {
                i7 = iG0;
            }
            if (i10 < 0) {
                i10 = 0;
            }
            bVar = new G7.b(i7, i10, -1);
        } else {
            if (i7 < 0) {
                i7 = 0;
            }
            int length = charSequence.length();
            if (i10 > length) {
                i10 = length;
            }
            bVar = new G7.d(i7, i10, 1);
        }
        boolean z11 = charSequence instanceof String;
        int i11 = bVar.f2713n;
        int i12 = bVar.f2712m;
        int i13 = bVar.l;
        if (z11 && (charSequence2 instanceof String)) {
            if ((i11 > 0 && i13 <= i12) || (i11 < 0 && i12 <= i13)) {
                while (true) {
                    String str = (String) charSequence2;
                    if (!o.u0(0, i13, str.length(), str, (String) charSequence, z6)) {
                        if (i13 == i12) {
                            break;
                        }
                        i13 += i11;
                    } else {
                        return i13;
                    }
                }
            }
        } else if ((i11 > 0 && i13 <= i12) || (i11 < 0 && i12 <= i13)) {
            while (!R0(charSequence2, 0, charSequence, i13, charSequence2.length(), z6)) {
                if (i13 != i12) {
                    i13 += i11;
                }
            }
            return i13;
        }
        return -1;
    }

    public static int J0(CharSequence charSequence, char c, int i7, boolean z6, int i10) {
        if ((i10 & 2) != 0) {
            i7 = 0;
        }
        if ((i10 & 4) != 0) {
            z6 = false;
        }
        A7.m.f(charSequence, "<this>");
        return (z6 || !(charSequence instanceof String)) ? L0(charSequence, new char[]{c}, i7, z6) : ((String) charSequence).indexOf(c, i7);
    }

    public static /* synthetic */ int K0(CharSequence charSequence, String str, int i7, boolean z6, int i10) {
        if ((i10 & 2) != 0) {
            i7 = 0;
        }
        if ((i10 & 4) != 0) {
            z6 = false;
        }
        return H0(i7, charSequence, str, z6);
    }

    public static final int L0(CharSequence charSequence, char[] cArr, int i7, boolean z6) {
        A7.m.f(charSequence, "<this>");
        if (!z6 && cArr.length == 1 && (charSequence instanceof String)) {
            return ((String) charSequence).indexOf(AbstractC1996k.X(cArr), i7);
        }
        if (i7 < 0) {
            i7 = 0;
        }
        int iG0 = G0(charSequence);
        if (i7 > iG0) {
            return -1;
        }
        while (true) {
            char cCharAt = charSequence.charAt(i7);
            for (char c : cArr) {
                if (AbstractC1274a.F(c, cCharAt, z6)) {
                    return i7;
                }
            }
            if (i7 == iG0) {
                return -1;
            }
            i7++;
        }
    }

    public static boolean M0(CharSequence charSequence) {
        A7.m.f(charSequence, "<this>");
        for (int i7 = 0; i7 < charSequence.length(); i7++) {
            if (!AbstractC1274a.T(charSequence.charAt(i7))) {
                return false;
            }
        }
        return true;
    }

    public static char N0(CharSequence charSequence) {
        if (charSequence.length() != 0) {
            return charSequence.charAt(G0(charSequence));
        }
        throw new NoSuchElementException("Char sequence is empty.");
    }

    public static int O0(char c, int i7, int i10, CharSequence charSequence) {
        if ((i10 & 2) != 0) {
            i7 = G0(charSequence);
        }
        A7.m.f(charSequence, "<this>");
        if (charSequence instanceof String) {
            return ((String) charSequence).lastIndexOf(c, i7);
        }
        char[] cArr = {c};
        if (charSequence instanceof String) {
            return ((String) charSequence).lastIndexOf(AbstractC1996k.X(cArr), i7);
        }
        int iG0 = G0(charSequence);
        if (i7 > iG0) {
            i7 = iG0;
        }
        while (-1 < i7) {
            if (AbstractC1274a.F(cArr[0], charSequence.charAt(i7), false)) {
                return i7;
            }
            i7--;
        }
        return -1;
    }

    public static int P0(String str, int i7, String str2) {
        int iG0 = (i7 & 2) != 0 ? G0(str) : 0;
        A7.m.f(str, "<this>");
        A7.m.f(str2, "string");
        return str.lastIndexOf(str2, iG0);
    }

    public static String Q0(int i7, String str) {
        CharSequence charSequenceSubSequence;
        A7.m.f(str, "<this>");
        if (i7 < 0) {
            throw new IllegalArgumentException(A7.k.g("Desired length ", i7, " is less than zero."));
        }
        if (i7 <= str.length()) {
            charSequenceSubSequence = str.subSequence(0, str.length());
        } else {
            StringBuilder sb = new StringBuilder(i7);
            int length = i7 - str.length();
            int i10 = 1;
            if (1 <= length) {
                while (true) {
                    sb.append('0');
                    if (i10 == length) {
                        break;
                    }
                    i10++;
                }
            }
            sb.append((CharSequence) str);
            charSequenceSubSequence = sb;
        }
        return charSequenceSubSequence.toString();
    }

    public static final boolean R0(CharSequence charSequence, int i7, CharSequence charSequence2, int i10, int i11, boolean z6) {
        A7.m.f(charSequence, "<this>");
        A7.m.f(charSequence2, "other");
        if (i10 < 0 || i7 < 0 || i7 > charSequence.length() - i11 || i10 > charSequence2.length() - i11) {
            return false;
        }
        for (int i12 = 0; i12 < i11; i12++) {
            if (!AbstractC1274a.F(charSequence.charAt(i7 + i12), charSequence2.charAt(i10 + i12), z6)) {
                return false;
            }
        }
        return true;
    }

    public static String S0(String str, String str2) {
        A7.m.f(str, "<this>");
        if (!o.z0(str, str2, false)) {
            return str;
        }
        String strSubstring = str.substring(str2.length());
        A7.m.e(strSubstring, "substring(...)");
        return strSubstring;
    }

    public static final void T0(int i7) {
        if (i7 < 0) {
            throw new IllegalArgumentException(android.hardware.bydauto.ac.tsimpl.a.e(i7, "Limit must be non-negative, but was ").toString());
        }
    }

    public static final List U0(int i7, CharSequence charSequence, String str, boolean z6) {
        T0(i7);
        int length = 0;
        int iH0 = H0(0, charSequence, str, z6);
        if (iH0 == -1 || i7 == 1) {
            return AbstractC1387d.j(charSequence.toString());
        }
        boolean z10 = i7 > 0;
        int i10 = 10;
        if (z10 && i7 <= 10) {
            i10 = i7;
        }
        ArrayList arrayList = new ArrayList(i10);
        do {
            arrayList.add(charSequence.subSequence(length, iH0).toString());
            length = str.length() + iH0;
            if (z10 && arrayList.size() == i7 - 1) {
                break;
            }
            iH0 = H0(length, charSequence, str, z6);
        } while (iH0 != -1);
        arrayList.add(charSequence.subSequence(length, charSequence.length()).toString());
        return arrayList;
    }

    public static List V0(CharSequence charSequence, String[] strArr, int i7, int i10) {
        boolean z6 = false;
        if ((i10 & 4) != 0) {
            i7 = 0;
        }
        A7.m.f(charSequence, "<this>");
        if (strArr.length == 1) {
            String str = strArr[0];
            if (str.length() != 0) {
                return U0(i7, charSequence, str, false);
            }
        }
        T0(i7);
        c<G7.d> cVar = new c(charSequence, 0, i7, new p(1, AbstractC1996k.x(strArr), z6));
        ArrayList arrayList = new ArrayList(AbstractC2000o.J(new P8.p(cVar, 0), 10));
        for (G7.d dVar : cVar) {
            A7.m.f(dVar, "range");
            arrayList.add(charSequence.subSequence(dVar.l, dVar.f2712m + 1).toString());
        }
        return arrayList;
    }

    public static List W0(String str, char[] cArr) {
        A7.m.f(str, "<this>");
        boolean z6 = false;
        if (cArr.length == 1) {
            return U0(0, str, String.valueOf(cArr[0]), false);
        }
        T0(0);
        c<G7.d> cVar = new c(str, 0, 0, new p(0, cArr, z6));
        ArrayList arrayList = new ArrayList(AbstractC2000o.J(new P8.p(cVar, 0), 10));
        for (G7.d dVar : cVar) {
            A7.m.f(dVar, "range");
            arrayList.add(str.subSequence(dVar.l, dVar.f2712m + 1).toString());
        }
        return arrayList;
    }

    public static boolean X0(String str, char c) {
        return str.length() > 0 && AbstractC1274a.F(str.charAt(0), c, false);
    }

    public static String Y0(String str, String str2, String str3) {
        A7.m.f(str2, "delimiter");
        A7.m.f(str3, "missingDelimiterValue");
        int iK0 = K0(str, str2, 0, false, 6);
        if (iK0 == -1) {
            return str3;
        }
        String strSubstring = str.substring(str2.length() + iK0, str.length());
        A7.m.e(strSubstring, "substring(...)");
        return strSubstring;
    }

    public static String Z0(String str) {
        int iJ0 = J0(str, '$', 0, false, 6);
        if (iJ0 == -1) {
            return str;
        }
        String strSubstring = str.substring(iJ0 + 1, str.length());
        A7.m.e(strSubstring, "substring(...)");
        return strSubstring;
    }

    public static String a1(char c, String str, String str2) {
        A7.m.f(str, "<this>");
        A7.m.f(str2, "missingDelimiterValue");
        int iO0 = O0(c, 0, 6, str);
        if (iO0 == -1) {
            return str2;
        }
        String strSubstring = str.substring(iO0 + 1, str.length());
        A7.m.e(strSubstring, "substring(...)");
        return strSubstring;
    }

    public static String b1(String str, String str2, String str3) {
        A7.m.f(str, "<this>");
        A7.m.f(str3, "missingDelimiterValue");
        int iK0 = K0(str, str2, 0, false, 6);
        if (iK0 == -1) {
            return str3;
        }
        String strSubstring = str.substring(0, iK0);
        A7.m.e(strSubstring, "substring(...)");
        return strSubstring;
    }

    public static String c1(String str, char c) {
        A7.m.f(str, "<this>");
        A7.m.f(str, "missingDelimiterValue");
        int iJ0 = J0(str, c, 0, false, 6);
        if (iJ0 == -1) {
            return str;
        }
        String strSubstring = str.substring(0, iJ0);
        A7.m.e(strSubstring, "substring(...)");
        return strSubstring;
    }

    public static String d1(String str, String str2) {
        A7.m.f(str, "<this>");
        A7.m.f(str2, "missingDelimiterValue");
        int iP0 = P0(str, 6, ".");
        if (iP0 == -1) {
            return str2;
        }
        String strSubstring = str.substring(0, iP0);
        A7.m.e(strSubstring, "substring(...)");
        return strSubstring;
    }

    public static String e1(String str, char c) {
        A7.m.f(str, "<this>");
        A7.m.f(str, "missingDelimiterValue");
        int iO0 = O0(c, 0, 6, str);
        if (iO0 == -1) {
            return str;
        }
        String strSubstring = str.substring(0, iO0);
        A7.m.e(strSubstring, "substring(...)");
        return strSubstring;
    }

    public static String f1(int i7, String str) {
        A7.m.f(str, "<this>");
        if (i7 < 0) {
            throw new IllegalArgumentException(A7.k.g("Requested character count ", i7, " is less than zero.").toString());
        }
        int length = str.length();
        if (i7 > length) {
            i7 = length;
        }
        String strSubstring = str.substring(0, i7);
        A7.m.e(strSubstring, "substring(...)");
        return strSubstring;
    }

    public static CharSequence g1(String str) {
        A7.m.f(str, "<this>");
        int length = str.length() - 1;
        int i7 = 0;
        boolean z6 = false;
        while (i7 <= length) {
            boolean zT = AbstractC1274a.T(str.charAt(!z6 ? i7 : length));
            if (z6) {
                if (!zT) {
                    break;
                }
                length--;
            } else if (zT) {
                i7++;
            } else {
                z6 = true;
            }
        }
        return str.subSequence(i7, length + 1);
    }

    public static String h1(String str, char... cArr) {
        CharSequence charSequenceSubSequence;
        A7.m.f(str, "<this>");
        int length = str.length();
        int i7 = 0;
        while (true) {
            if (i7 >= length) {
                charSequenceSubSequence = "";
                break;
            }
            char cCharAt = str.charAt(i7);
            int length2 = cArr.length;
            int i10 = 0;
            while (true) {
                if (i10 >= length2) {
                    i10 = -1;
                    break;
                }
                if (cCharAt == cArr[i10]) {
                    break;
                }
                i10++;
            }
            if (!(i10 >= 0)) {
                charSequenceSubSequence = str.subSequence(i7, str.length());
                break;
            }
            i7++;
        }
        return charSequenceSubSequence.toString();
    }
}
