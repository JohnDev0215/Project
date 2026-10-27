package defpackage;

import J.N;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Patterns;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.regex.Pattern;
import org.chromium.base.CollectionUtil;
import org.chromium.base.library_loader.a;
import org.chromium.url.GURL;

/* JADX INFO: compiled from: UrlUtilities.java */
/* JADX INFO: loaded from: classes2.dex */
public final class bk3 {
    public static final Pattern a = Pattern.compile("^(www[0-9]*|web|ftp|wap|home|mobile|amp)\\.");
    public static final ArrayList b = new ArrayList(Arrays.asList("about", "data", "file", "http", "https", "inline", "javascript"));
    public static final ArrayList c = new ArrayList(Arrays.asList("data", "blob", "file", "filesystem", "http", "https"));
    public static final HashSet<String> d = CollectionUtil.e("chrome", "chrome-native", "about");

    public static String a(String str) {
        String strReplaceFirst = a.matcher(N.M25QTkfm(N.MpCt7siL(str))).replaceFirst("");
        me meVarC = me.c();
        if (strReplaceFirst != null) {
            return meVarC.d(strReplaceFirst, meVarC.c).toString();
        }
        meVarC.getClass();
        return null;
    }

    public static String b(String str, boolean z) {
        return TextUtils.isEmpty(str) ? str : N.M3l9sLoY(str, z);
    }

    public static String c(GURL gurl) {
        return (!GURL.l(gurl) && n(gurl)) ? gurl.g() : "";
    }

    public static boolean d(GURL gurl) {
        return gurl.i().equals("android-app") || gurl.i().equals("intent");
    }

    public static boolean e(GURL gurl) {
        if (GURL.l(gurl)) {
            return false;
        }
        return b.contains(gurl.i());
    }

    public static boolean f(String str) {
        return a.l.d() ? k(str) : TextUtils.equals(str, "chrome-native://newtab/") || TextUtils.equals(str, "chrome://newtab/") || TextUtils.equals(str, "about:newtab");
    }

    public static boolean g(GURL gurl) {
        if (gurl.b) {
            return c.contains(gurl.i());
        }
        return false;
    }

    public static boolean h(String str) {
        return m(Uri.parse(str).getScheme());
    }

    public static boolean i(GURL gurl) {
        return m(gurl.i());
    }

    public static boolean j(GURL gurl) {
        return d.contains(gurl.i());
    }

    @Deprecated
    public static boolean k(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        return l(oj2.h(str));
    }

    public static boolean l(GURL gurl) {
        if (gurl.b && j(gurl)) {
            return "newtab".equals(gurl.e());
        }
        return false;
    }

    public static boolean m(String str) {
        return "http".equals(str) || "https".equals(str);
    }

    public static boolean n(GURL gurl) {
        return gurl != null && gurl.i().equals("tel");
    }

    public static boolean o(String str) {
        return Patterns.WEB_URL.matcher(str).matches() && h(str);
    }

    public static String p(String str) {
        String strTrim = str.trim();
        return strTrim.startsWith("https://") ? strTrim.substring(8) : strTrim.startsWith("http://") ? strTrim.substring(7) : strTrim;
    }

    public static boolean q(String str, String str2) {
        if (TextUtils.equals(str, str2)) {
            return true;
        }
        return N.Ml0jhLAz(str, str2);
    }
}
