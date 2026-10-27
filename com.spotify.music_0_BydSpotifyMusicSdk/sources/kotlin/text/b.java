package kotlin.text;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import kotlin.Pair;
import p.b90;
import p.bk0;
import p.fz0;
import p.g51;
import p.i41;
import p.i51;
import p.lu;
import p.ng2;
import p.rb;
import p.rn2;
import p.ry0;
import p.u4;
import p.v03;

/* JADX INFO: loaded from: classes.dex */
public abstract class b extends ng2 {
    public static boolean A0(CharSequence charSequence, String str) {
        return charSequence instanceof String ? ng2.g0((String) charSequence, str, false) : u0(charSequence, 0, str, 0, str.length(), false);
    }

    public static final String B0(CharSequence charSequence, i51 i51Var) {
        v03.j(charSequence, "<this>");
        v03.j(i51Var, "range");
        return charSequence.subSequence(Integer.valueOf(i51Var.c).intValue(), Integer.valueOf(i51Var.e).intValue() + 1).toString();
    }

    public static String C0(String str, String str2) {
        v03.j(str2, "delimiter");
        int iO0 = o0(str, str2, 0, false, 6);
        if (iO0 == -1) {
            return str;
        }
        String strSubstring = str.substring(str2.length() + iO0, str.length());
        v03.i(strSubstring, "this as java.lang.String…ing(startIndex, endIndex)");
        return strSubstring;
    }

    public static String D0(String str, char c, String str2) {
        v03.j(str, "<this>");
        v03.j(str2, "missingDelimiterValue");
        int iQ0 = q0(str, c, 0, 6);
        if (iQ0 == -1) {
            return str2;
        }
        String strSubstring = str.substring(iQ0 + 1, str.length());
        v03.i(strSubstring, "this as java.lang.String…ing(startIndex, endIndex)");
        return strSubstring;
    }

    public static String E0(String str, char c) {
        int iN0 = n0(str, c, 0, false, 6);
        if (iN0 == -1) {
            return str;
        }
        String strSubstring = str.substring(0, iN0);
        v03.i(strSubstring, "this as java.lang.String…ing(startIndex, endIndex)");
        return strSubstring;
    }

    public static String F0(String str, char c) {
        v03.j(str, "<this>");
        v03.j(str, "missingDelimiterValue");
        int iQ0 = q0(str, c, 0, 6);
        if (iQ0 == -1) {
            return str;
        }
        String strSubstring = str.substring(0, iQ0);
        v03.i(strSubstring, "this as java.lang.String…ing(startIndex, endIndex)");
        return strSubstring;
    }

    public static CharSequence G0(CharSequence charSequence) {
        v03.j(charSequence, "<this>");
        int length = charSequence.length() - 1;
        int i = 0;
        boolean z = false;
        while (i <= length) {
            boolean zJ = bk0.J(charSequence.charAt(!z ? i : length));
            if (z) {
                if (!zJ) {
                    break;
                }
                length--;
            } else if (zJ) {
                i++;
            } else {
                z = true;
            }
        }
        return charSequence.subSequence(i, length + 1);
    }

    public static boolean h0(CharSequence charSequence, char c) {
        v03.j(charSequence, "<this>");
        return n0(charSequence, c, 0, false, 2) >= 0;
    }

    public static boolean i0(CharSequence charSequence, String str) {
        v03.j(charSequence, "<this>");
        v03.j(str, "other");
        return o0(charSequence, str, 0, false, 2) >= 0;
    }

    public static boolean j0(String str, char c) {
        return str.length() > 0 && bk0.r(str.charAt(k0(str)), c, false);
    }

    public static final int k0(CharSequence charSequence) {
        v03.j(charSequence, "<this>");
        return charSequence.length() - 1;
    }

    public static final int l0(int i, CharSequence charSequence, String str, boolean z) {
        v03.j(charSequence, "<this>");
        v03.j(str, "string");
        return (z || !(charSequence instanceof String)) ? m0(charSequence, str, i, charSequence.length(), z, false) : ((String) charSequence).indexOf(str, i);
    }

    public static final int m0(CharSequence charSequence, CharSequence charSequence2, int i, int i2, boolean z, boolean z2) {
        g51 g51Var;
        if (z2) {
            int iK0 = k0(charSequence);
            if (i > iK0) {
                i = iK0;
            }
            if (i2 < 0) {
                i2 = 0;
            }
            g51Var = new g51(i, i2, -1);
        } else {
            if (i < 0) {
                i = 0;
            }
            int length = charSequence.length();
            if (i2 > length) {
                i2 = length;
            }
            g51Var = new i51(i, i2, 1);
        }
        boolean z3 = charSequence instanceof String;
        int i3 = g51Var.k;
        int i4 = g51Var.e;
        int i5 = g51Var.c;
        if (z3 && (charSequence2 instanceof String)) {
            if ((i3 > 0 && i5 <= i4) || (i3 < 0 && i4 <= i5)) {
                while (!ng2.d0(0, i5, charSequence2.length(), (String) charSequence2, (String) charSequence, z)) {
                    if (i5 != i4) {
                        i5 += i3;
                    }
                }
                return i5;
            }
        } else if ((i3 > 0 && i5 <= i4) || (i3 < 0 && i4 <= i5)) {
            while (!u0(charSequence2, 0, charSequence, i5, charSequence2.length(), z)) {
                if (i5 != i4) {
                    i5 += i3;
                }
            }
            return i5;
        }
        return -1;
    }

    public static int n0(CharSequence charSequence, char c, int i, boolean z, int i2) {
        if ((i2 & 2) != 0) {
            i = 0;
        }
        if ((i2 & 4) != 0) {
            z = false;
        }
        v03.j(charSequence, "<this>");
        return (z || !(charSequence instanceof String)) ? p0(i, charSequence, z, new char[]{c}) : ((String) charSequence).indexOf(c, i);
    }

    public static /* synthetic */ int o0(CharSequence charSequence, String str, int i, boolean z, int i2) {
        if ((i2 & 2) != 0) {
            i = 0;
        }
        if ((i2 & 4) != 0) {
            z = false;
        }
        return l0(i, charSequence, str, z);
    }

    public static final int p0(int i, CharSequence charSequence, boolean z, char[] cArr) {
        int i2;
        v03.j(charSequence, "<this>");
        v03.j(cArr, "chars");
        boolean z2 = true;
        if (!z && cArr.length == 1 && (charSequence instanceof String)) {
            return ((String) charSequence).indexOf(kotlin.collections.b.t0(cArr), i);
        }
        if (i < 0) {
            i = 0;
        }
        i51 i51Var = new i51(i, k0(charSequence), 1);
        int i3 = i51Var.e;
        int i4 = i51Var.k;
        if (i4 <= 0 ? i < i3 : i > i3) {
            z2 = false;
        }
        if (!z2) {
            i = i3;
        }
        while (z2) {
            if (i != i3) {
                i2 = i + i4;
            } else {
                if (!z2) {
                    throw new NoSuchElementException();
                }
                i2 = i;
                z2 = false;
            }
            char cCharAt = charSequence.charAt(i);
            for (char c : cArr) {
                if (bk0.r(c, cCharAt, z)) {
                    return i;
                }
            }
            i = i2;
        }
        return -1;
    }

    public static int q0(CharSequence charSequence, char c, int i, int i2) {
        if ((i2 & 2) != 0) {
            i = k0(charSequence);
        }
        v03.j(charSequence, "<this>");
        if (charSequence instanceof String) {
            return ((String) charSequence).lastIndexOf(c, i);
        }
        char[] cArr = {c};
        if (charSequence instanceof String) {
            return ((String) charSequence).lastIndexOf(kotlin.collections.b.t0(cArr), i);
        }
        int iK0 = k0(charSequence);
        if (i > iK0) {
            i = iK0;
        }
        while (-1 < i) {
            if (bk0.r(cArr[0], charSequence.charAt(i), false)) {
                return i;
            }
            i--;
        }
        return -1;
    }

    public static int r0(String str, String str2, int i) {
        int iK0 = (i & 2) != 0 ? k0(str) : 0;
        v03.j(str, "<this>");
        v03.j(str2, "string");
        return str.lastIndexOf(str2, iK0);
    }

    public static final List s0(final CharSequence charSequence) {
        v03.j(charSequence, "<this>");
        return kotlin.sequences.a.J0(new rn2(t0(charSequence, new String[]{"\r\n", "\n", "\r"}, false, 0), new ry0() { // from class: kotlin.text.StringsKt__StringsKt$splitToSequence$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // p.ry0
            public final Object invoke(Object obj) {
                i51 i51Var = (i51) obj;
                v03.j(i51Var, "it");
                return b.B0(charSequence, i51Var);
            }
        }));
    }

    public static b90 t0(CharSequence charSequence, String[] strArr, final boolean z, int i) {
        w0(i);
        final List listH0 = rb.h0(strArr);
        return new b90(charSequence, 0, i, new fz0() { // from class: kotlin.text.StringsKt__StringsKt$rangesDelimitedBy$2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(2);
            }

            /* JADX WARN: Removed duplicated region for block: B:54:0x00da A[EDGE_INSN: B:59:0x00da->B:54:0x00da BREAK  A[LOOP:0: B:26:0x006a->B:37:0x009d], EDGE_INSN: B:65:0x00da->B:54:0x00da BREAK  A[LOOP:2: B:42:0x00a7->B:53:0x00d8]] */
            /* JADX WARN: Removed duplicated region for block: B:54:0x00da A[EDGE_INSN: B:59:0x00da->B:54:0x00da BREAK  A[LOOP:0: B:26:0x006a->B:37:0x009d]] */
            @Override // p.fz0
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct code enable 'Show inconsistent code' option in preferences
            */
            public final java.lang.Object g(java.lang.Object r14, java.lang.Object r15) {
                /*
                    Method dump skipped, instruction units count: 245
                    To view this dump change 'Code comments level' option to 'DEBUG'
                */
                throw new UnsupportedOperationException("Method not decompiled: kotlin.text.StringsKt__StringsKt$rangesDelimitedBy$2.g(java.lang.Object, java.lang.Object):java.lang.Object");
            }
        });
    }

    public static final boolean u0(CharSequence charSequence, int i, CharSequence charSequence2, int i2, int i3, boolean z) {
        v03.j(charSequence, "<this>");
        v03.j(charSequence2, "other");
        if (i2 < 0 || i < 0 || i > charSequence.length() - i3 || i2 > charSequence2.length() - i3) {
            return false;
        }
        for (int i4 = 0; i4 < i3; i4++) {
            if (!bk0.r(charSequence.charAt(i + i4), charSequence2.charAt(i2 + i4), z)) {
                return false;
            }
        }
        return true;
    }

    public static String v0(String str, String str2) {
        if (!A0(str2, str)) {
            return str2;
        }
        String strSubstring = str2.substring(str.length());
        v03.i(strSubstring, "this as java.lang.String).substring(startIndex)");
        return strSubstring;
    }

    public static final void w0(int i) {
        if (i < 0) {
            throw new IllegalArgumentException(u4.e("Limit must be non-negative, but was ", i).toString());
        }
    }

    public static final List x0(int i, CharSequence charSequence, String str, boolean z) {
        w0(i);
        int length = 0;
        int iL0 = l0(0, charSequence, str, z);
        if (iL0 == -1 || i == 1) {
            return v03.B(charSequence.toString());
        }
        boolean z2 = i > 0;
        int i2 = 10;
        if (z2 && i <= 10) {
            i2 = i;
        }
        ArrayList arrayList = new ArrayList(i2);
        do {
            arrayList.add(charSequence.subSequence(length, iL0).toString());
            length = str.length() + iL0;
            if (z2 && arrayList.size() == i - 1) {
                break;
            }
            iL0 = l0(length, charSequence, str, z);
        } while (iL0 != -1);
        arrayList.add(charSequence.subSequence(length, charSequence.length()).toString());
        return arrayList;
    }

    public static List y0(CharSequence charSequence, final char[] cArr) {
        v03.j(charSequence, "<this>");
        final boolean z = false;
        if (cArr.length == 1) {
            return x0(0, charSequence, String.valueOf(cArr[0]), false);
        }
        w0(0);
        i41 i41Var = new i41(new b90(charSequence, 0, 0, new fz0() { // from class: kotlin.text.StringsKt__StringsKt$rangesDelimitedBy$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(2);
            }

            @Override // p.fz0
            public final Object g(Object obj, Object obj2) {
                CharSequence charSequence2 = (CharSequence) obj;
                int iIntValue = ((Number) obj2).intValue();
                v03.j(charSequence2, "$this$$receiver");
                int iP0 = b.p0(iIntValue, charSequence2, z, cArr);
                if (iP0 < 0) {
                    return null;
                }
                return new Pair(Integer.valueOf(iP0), 1);
            }
        }));
        ArrayList arrayList = new ArrayList(lu.W(i41Var));
        Iterator it = i41Var.iterator();
        while (it.hasNext()) {
            arrayList.add(B0(charSequence, (i51) it.next()));
        }
        return arrayList;
    }

    public static List z0(CharSequence charSequence, String[] strArr) {
        if (strArr.length == 1) {
            String str = strArr[0];
            if (str.length() != 0) {
                return x0(0, charSequence, str, false);
            }
        }
        i41 i41Var = new i41(t0(charSequence, strArr, false, 0));
        ArrayList arrayList = new ArrayList(lu.W(i41Var));
        Iterator it = i41Var.iterator();
        while (it.hasNext()) {
            arrayList.add(B0(charSequence, (i51) it.next()));
        }
        return arrayList;
    }
}
