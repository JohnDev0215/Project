package f.k0.i;

import f.g0;
import f.p;
import f.q;
import f.w;
import f.x;
import java.util.List;
import okio.ByteString;

/* JADX INFO: compiled from: HttpHeaders.java */
/* JADX INFO: loaded from: classes.dex */
public final class e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final ByteString f5231a = ByteString.i("\"\\");

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static final ByteString f5232b = ByteString.i("\t ,=");

    public static long a(w wVar) {
        return h(wVar.c("Content-Length"));
    }

    public static long b(g0 g0Var) {
        return a(g0Var.S());
    }

    public static boolean c(g0 g0Var) {
        if (g0Var.Y().f().equals("HEAD")) {
            return false;
        }
        int iJ = g0Var.j();
        return (((iJ >= 100 && iJ < 200) || iJ == 204 || iJ == 304) && b(g0Var) == -1 && !"chunked".equalsIgnoreCase(g0Var.C("Transfer-Encoding"))) ? false : true;
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

    public static void e(q qVar, x xVar, w wVar) {
        if (qVar == q.f5492a) {
            return;
        }
        List<p> listF = p.f(xVar, wVar);
        if (listF.isEmpty()) {
            return;
        }
        qVar.b(xVar, listF);
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

    private static long h(String str) {
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
