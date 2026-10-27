package c.a.a.d;

import c.a.a.c.e;
import cn.hutool.core.text.finder.StrFinder;
import java.nio.charset.Charset;
import okhttp3.HttpUrl;

/* JADX INFO: compiled from: CharSequenceUtil.java */
/* JADX INFO: loaded from: classes.dex */
public class b {
    public static byte[] a(CharSequence charSequence, String str) {
        return b(charSequence, i(str) ? Charset.defaultCharset() : Charset.forName(str));
    }

    public static byte[] b(CharSequence charSequence, Charset charset) {
        if (charSequence == null) {
            return null;
        }
        return charset == null ? charSequence.toString().getBytes() : charSequence.toString().getBytes(charset);
    }

    public static String c(CharSequence charSequence) {
        return f(charSequence, new e() { // from class: c.a.a.d.a
            @Override // c.a.a.c.e
            public final boolean accept(Object obj) {
                return b.l((Character) obj);
            }
        });
    }

    public static boolean d(CharSequence charSequence, CharSequence charSequence2) {
        return e(charSequence, charSequence2, false);
    }

    public static boolean e(CharSequence charSequence, CharSequence charSequence2, boolean z) {
        if (charSequence == null) {
            return charSequence2 == null;
        }
        if (charSequence2 == null) {
            return false;
        }
        return z ? charSequence.toString().equalsIgnoreCase(charSequence2.toString()) : charSequence.toString().contentEquals(charSequence2);
    }

    public static String f(CharSequence charSequence, e<Character> eVar) {
        if (charSequence == null || eVar == null) {
            return r(charSequence);
        }
        int length = charSequence.length();
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            char cCharAt = charSequence.charAt(i);
            if (eVar.accept(Character.valueOf(cCharAt))) {
                sb.append(cCharAt);
            }
        }
        return sb.toString();
    }

    public static String g(CharSequence charSequence, Object... objArr) {
        return charSequence == null ? "null" : (cn.hutool.core.util.a.f(objArr) || i(charSequence)) ? charSequence.toString() : c.a(charSequence.toString(), objArr);
    }

    public static int h(CharSequence charSequence, CharSequence charSequence2, int i, boolean z) {
        return (j(charSequence) || j(charSequence2)) ? d(charSequence, charSequence2) ? 0 : -1 : new StrFinder(charSequence2, z).setText(charSequence).start(i);
    }

    public static boolean i(CharSequence charSequence) {
        int length;
        if (charSequence != null && (length = charSequence.length()) != 0) {
            for (int i = 0; i < length; i++) {
                if (!cn.hutool.core.util.b.a(charSequence.charAt(i))) {
                    return false;
                }
            }
        }
        return true;
    }

    public static boolean j(CharSequence charSequence) {
        return charSequence == null || charSequence.length() == 0;
    }

    public static boolean k(CharSequence charSequence, int i, CharSequence charSequence2, int i2, int i3, boolean z) {
        if (charSequence == null || charSequence2 == null) {
            return false;
        }
        return charSequence.toString().regionMatches(z, i, charSequence2.toString(), i2, i3);
    }

    static /* synthetic */ boolean l(Character ch) {
        return !cn.hutool.core.util.b.a(ch.charValue());
    }

    public static String m(CharSequence charSequence, int i, CharSequence charSequence2, CharSequence charSequence3, boolean z) {
        if (j(charSequence) || j(charSequence2)) {
            return r(charSequence);
        }
        if (charSequence3 == null) {
            charSequence3 = HttpUrl.FRAGMENT_ENCODE_SET;
        }
        int length = charSequence.length();
        int length2 = charSequence2.length();
        if (length < length2) {
            return r(charSequence);
        }
        if (i > length) {
            return r(charSequence);
        }
        if (i < 0) {
            i = 0;
        }
        StringBuilder sb = new StringBuilder((length - length2) + charSequence3.length());
        if (i != 0) {
            sb.append(charSequence.subSequence(0, i));
        }
        while (true) {
            int iH = h(charSequence, charSequence2, i, z);
            if (iH <= -1) {
                break;
            }
            sb.append(charSequence.subSequence(i, iH));
            sb.append(charSequence3);
            i = iH + length2;
        }
        if (i < length) {
            sb.append(charSequence.subSequence(i, length));
        }
        return sb.toString();
    }

    public static String n(CharSequence charSequence, CharSequence charSequence2, CharSequence charSequence3) {
        return m(charSequence, 0, charSequence2, charSequence3, false);
    }

    public static boolean o(CharSequence charSequence, CharSequence charSequence2, boolean z) {
        return p(charSequence, charSequence2, z, false);
    }

    public static boolean p(CharSequence charSequence, CharSequence charSequence2, boolean z, boolean z2) {
        if (charSequence == null || charSequence2 == null) {
            if (z2) {
                return false;
            }
            return charSequence == null && charSequence2 == null;
        }
        if (charSequence.toString().regionMatches(z, 0, charSequence2.toString(), 0, charSequence2.length())) {
            return (z2 && e(charSequence, charSequence2, z)) ? false : true;
        }
        return false;
    }

    public static boolean q(CharSequence charSequence, CharSequence charSequence2) {
        return o(charSequence, charSequence2, true);
    }

    public static String r(CharSequence charSequence) {
        if (charSequence == null) {
            return null;
        }
        return charSequence.toString();
    }
}
