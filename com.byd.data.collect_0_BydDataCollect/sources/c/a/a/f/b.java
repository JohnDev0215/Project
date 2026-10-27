package c.a.a.f;

import c.a.a.d.e;
import java.nio.charset.Charset;

/* JADX INFO: compiled from: CharSequenceUtil.java */
/* JADX INFO: loaded from: classes.dex */
public class b {
    public static byte[] a(CharSequence charSequence, Charset charset) {
        if (charSequence == null) {
            return null;
        }
        return charset == null ? charSequence.toString().getBytes() : charSequence.toString().getBytes(charset);
    }

    public static String b(CharSequence charSequence) {
        return e(charSequence, new e() { // from class: c.a.a.f.a
            @Override // c.a.a.d.e
            public final boolean accept(Object obj) {
                return b.k((Character) obj);
            }
        });
    }

    public static boolean c(CharSequence charSequence, CharSequence charSequence2) {
        return d(charSequence, charSequence2, false);
    }

    public static boolean d(CharSequence charSequence, CharSequence charSequence2, boolean z) {
        if (charSequence == null) {
            return charSequence2 == null;
        }
        if (charSequence2 == null) {
            return false;
        }
        return z ? charSequence.toString().equalsIgnoreCase(charSequence2.toString()) : charSequence.toString().contentEquals(charSequence2);
    }

    public static String e(CharSequence charSequence, e<Character> eVar) {
        if (charSequence == null || eVar == null) {
            return q(charSequence);
        }
        int length = charSequence.length();
        StringBuilder sb = new StringBuilder(length);
        for (int i2 = 0; i2 < length; i2++) {
            char cCharAt = charSequence.charAt(i2);
            if (eVar.accept(Character.valueOf(cCharAt))) {
                sb.append(cCharAt);
            }
        }
        return sb.toString();
    }

    public static String f(CharSequence charSequence, Object... objArr) {
        return charSequence == null ? "null" : (c.a.a.g.a.f(objArr) || h(charSequence)) ? charSequence.toString() : c.a(charSequence.toString(), objArr);
    }

    public static int g(CharSequence charSequence, CharSequence charSequence2, int i2, boolean z) {
        return (i(charSequence) || i(charSequence2)) ? c(charSequence, charSequence2) ? 0 : -1 : new c.a.a.f.d.b(charSequence2, z).d(charSequence).a(i2);
    }

    public static boolean h(CharSequence charSequence) {
        int length;
        if (charSequence != null && (length = charSequence.length()) != 0) {
            for (int i2 = 0; i2 < length; i2++) {
                if (!c.a.a.g.b.a(charSequence.charAt(i2))) {
                    return false;
                }
            }
        }
        return true;
    }

    public static boolean i(CharSequence charSequence) {
        return charSequence == null || charSequence.length() == 0;
    }

    public static boolean j(CharSequence charSequence, int i2, CharSequence charSequence2, int i3, int i4, boolean z) {
        if (charSequence == null || charSequence2 == null) {
            return false;
        }
        return charSequence.toString().regionMatches(z, i2, charSequence2.toString(), i3, i4);
    }

    static /* synthetic */ boolean k(Character ch) {
        return !c.a.a.g.b.a(ch.charValue());
    }

    public static String l(CharSequence charSequence, int i2, CharSequence charSequence2, CharSequence charSequence3, boolean z) {
        if (i(charSequence) || i(charSequence2)) {
            return q(charSequence);
        }
        if (charSequence3 == null) {
            charSequence3 = "";
        }
        int length = charSequence.length();
        int length2 = charSequence2.length();
        if (length < length2) {
            return q(charSequence);
        }
        if (i2 > length) {
            return q(charSequence);
        }
        if (i2 < 0) {
            i2 = 0;
        }
        StringBuilder sb = new StringBuilder((length - length2) + charSequence3.length());
        if (i2 != 0) {
            sb.append(charSequence.subSequence(0, i2));
        }
        while (true) {
            int iG = g(charSequence, charSequence2, i2, z);
            if (iG <= -1) {
                break;
            }
            sb.append(charSequence.subSequence(i2, iG));
            sb.append(charSequence3);
            i2 = iG + length2;
        }
        if (i2 < length) {
            sb.append(charSequence.subSequence(i2, length));
        }
        return sb.toString();
    }

    public static String m(CharSequence charSequence, CharSequence charSequence2, CharSequence charSequence3) {
        return l(charSequence, 0, charSequence2, charSequence3, false);
    }

    public static boolean n(CharSequence charSequence, CharSequence charSequence2, boolean z) {
        return o(charSequence, charSequence2, z, false);
    }

    public static boolean o(CharSequence charSequence, CharSequence charSequence2, boolean z, boolean z2) {
        if (charSequence == null || charSequence2 == null) {
            if (z2) {
                return false;
            }
            return charSequence == null && charSequence2 == null;
        }
        if (charSequence.toString().regionMatches(z, 0, charSequence2.toString(), 0, charSequence2.length())) {
            return (z2 && d(charSequence, charSequence2, z)) ? false : true;
        }
        return false;
    }

    public static boolean p(CharSequence charSequence, CharSequence charSequence2) {
        return n(charSequence, charSequence2, true);
    }

    public static String q(CharSequence charSequence) {
        if (charSequence == null) {
            return null;
        }
        return charSequence.toString();
    }
}
