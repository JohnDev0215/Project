package f.b0.g;

import f.l;
import f.m;
import f.q;
import f.y;
import java.util.List;
import java.util.regex.Pattern;
import okhttp3.HttpUrl;

/* JADX INFO: compiled from: HttpHeaders.java */
/* JADX INFO: loaded from: classes.dex */
public final class e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final Pattern f5078a = Pattern.compile(" +([^ \"=]*)=(:?\"([^\"]*)\"|([^ \"=]*)) *(:?,|$)");

    public static long a(q qVar) {
        return h(qVar.a("Content-Length"));
    }

    public static long b(y yVar) {
        return a(yVar.S());
    }

    public static boolean c(y yVar) {
        if (yVar.W().f().equals("HEAD")) {
            return false;
        }
        int iO = yVar.O();
        return (((iO >= 100 && iO < 200) || iO == 204 || iO == 304) && b(yVar) == -1 && !"chunked".equalsIgnoreCase(yVar.Q("Transfer-Encoding"))) ? false : true;
    }

    public static int d(String str, int i2) {
        try {
            long j = Long.parseLong(str);
            if (j > 2147483647L) {
                return Integer.MAX_VALUE;
            }
            if (j < 0) {
                return 0;
            }
            return (int) j;
        } catch (NumberFormatException unused) {
            return i2;
        }
    }

    public static void e(m mVar, HttpUrl httpUrl, q qVar) {
        if (mVar == m.f5360a) {
            return;
        }
        List<l> listF = l.f(httpUrl, qVar);
        if (listF.isEmpty()) {
            return;
        }
        mVar.b(httpUrl, listF);
    }

    public static int f(String str, int i2, String str2) {
        while (i2 < str.length() && str2.indexOf(str.charAt(i2)) == -1) {
            i2++;
        }
        return i2;
    }

    public static int g(String str, int i2) {
        char cCharAt;
        while (i2 < str.length() && ((cCharAt = str.charAt(i2)) == ' ' || cCharAt == '\t')) {
            i2++;
        }
        return i2;
    }

    public static long h(String str) {
        if (str == null) {
            return -1L;
        }
        try {
            return Long.parseLong(str);
        } catch (NumberFormatException unused) {
            return -1L;
        }
    }
}
