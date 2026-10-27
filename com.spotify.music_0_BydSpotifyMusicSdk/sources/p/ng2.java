package p;

import java.util.Collection;
import java.util.Iterator;

/* JADX INFO: loaded from: classes.dex */
public abstract class ng2 extends mg2 {
    public static boolean a0(String str, String str2, boolean z) {
        v03.j(str, "<this>");
        v03.j(str2, "suffix");
        return !z ? str.endsWith(str2) : d0(str.length() - str2.length(), 0, str2.length(), str, str2, true);
    }

    public static boolean b0(String str, String str2, boolean z) {
        return str == null ? str2 == null : !z ? str.equals(str2) : str.equalsIgnoreCase(str2);
    }

    public static boolean c0(CharSequence charSequence) {
        v03.j(charSequence, "<this>");
        if (charSequence.length() == 0) {
            return true;
        }
        Iterable i51Var = new i51(0, charSequence.length() - 1, 1);
        if ((i51Var instanceof Collection) && ((Collection) i51Var).isEmpty()) {
            return true;
        }
        Iterator it = i51Var.iterator();
        while (it.hasNext()) {
            if (!bk0.J(charSequence.charAt(((h51) it).b()))) {
                return false;
            }
        }
        return true;
    }

    public static final boolean d0(int i, int i2, int i3, String str, String str2, boolean z) {
        v03.j(str, "<this>");
        v03.j(str2, "other");
        return !z ? str.regionMatches(i, str2, i2, i3) : str.regionMatches(z, i, str2, i2, i3);
    }

    public static String e0(String str, String str2, String str3) {
        v03.j(str, "<this>");
        int iL0 = kotlin.text.b.l0(0, str, str2, false);
        if (iL0 < 0) {
            return str;
        }
        int length = str2.length();
        int i = length >= 1 ? length : 1;
        int length2 = str3.length() + (str.length() - length);
        if (length2 < 0) {
            throw new OutOfMemoryError();
        }
        StringBuilder sb = new StringBuilder(length2);
        int i2 = 0;
        do {
            sb.append((CharSequence) str, i2, iL0);
            sb.append(str3);
            i2 = iL0 + length;
            if (iL0 >= str.length()) {
                break;
            }
            iL0 = kotlin.text.b.l0(iL0 + i, str, str2, false);
        } while (iL0 > 0);
        sb.append((CharSequence) str, i2, str.length());
        String string = sb.toString();
        v03.i(string, "stringBuilder.append(this, i, length).toString()");
        return string;
    }

    public static boolean f0(int i, String str, String str2, boolean z) {
        v03.j(str, "<this>");
        return !z ? str.startsWith(str2, i) : d0(i, 0, str2.length(), str, str2, z);
    }

    public static boolean g0(String str, String str2, boolean z) {
        v03.j(str, "<this>");
        v03.j(str2, "prefix");
        return !z ? str.startsWith(str2) : d0(0, 0, str2.length(), str, str2, z);
    }
}
