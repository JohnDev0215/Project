package e.d0.g;

import e.a0;
import e.l;
import e.m;
import e.s;
import java.util.List;
import java.util.regex.Pattern;
import okhttp3.HttpUrl;

/* JADX INFO: compiled from: HttpHeaders.java */
/* JADX INFO: loaded from: classes.dex */
public final class e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final Pattern f3399a = Pattern.compile(" +([^ \"=]*)=(:?\"([^\"]*)\"|([^ \"=]*)) *(:?,|$)");

    public static long a(s sVar) {
        return h(sVar.a("Content-Length"));
    }

    public static long b(a0 a0Var) {
        return a(a0Var.J());
    }

    public static boolean c(a0 a0Var) {
        if (a0Var.O().f().equals("HEAD")) {
            return false;
        }
        int iS = a0Var.s();
        return (((iS >= 100 && iS < 200) || iS == 204 || iS == 304) && b(a0Var) == -1 && !"chunked".equalsIgnoreCase(a0Var.H("Transfer-Encoding"))) ? false : true;
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

    public static void e(m mVar, HttpUrl httpUrl, s sVar) {
        if (mVar == m.f3614a) {
            return;
        }
        List<l> listF = l.f(httpUrl, sVar);
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
