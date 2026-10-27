package d.a.a.f.b.b;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

/* JADX INFO: compiled from: BydLogHelper.java */
/* JADX INFO: loaded from: classes.dex */
public class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static boolean f4630a = true;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static boolean f4631b = true;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private static boolean f4632c = true;

    private static String a(String str, String str2, int i2) {
        String str3 = ("[" + str + "]") + "(" + i2 + ")";
        if (TextUtils.isEmpty(str2)) {
            return str3;
        }
        return str3 + str2;
    }

    public static void b(Context context) {
        f4630a = d.a.a.f.a.a.b().e(context);
        f4631b = true;
        f4632c = d.a.a.f.a.a.b().d(context);
    }

    public static void c(String str) {
        if (f4631b) {
            StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
            if (stackTrace == null || stackTrace.length < 4) {
                o("", "", str, 0);
                return;
            }
            String fileName = stackTrace[3].getFileName();
            int iLastIndexOf = fileName.lastIndexOf(46);
            if (iLastIndexOf > -1 && iLastIndexOf < fileName.length()) {
                fileName = fileName.substring(0, iLastIndexOf);
            }
            o(fileName, stackTrace[3].getMethodName(), str, stackTrace[3].getLineNumber());
        }
    }

    public static void d(String str, String str2) {
        if (f4631b) {
            StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
            if (stackTrace == null || stackTrace.length < 4) {
                o("", str, str2, 0);
                return;
            }
            String fileName = stackTrace[3].getFileName();
            int iLastIndexOf = fileName.lastIndexOf(46);
            if (iLastIndexOf > -1 && iLastIndexOf < fileName.length()) {
                fileName = fileName.substring(0, iLastIndexOf);
            }
            o(fileName, str, str2, stackTrace[3].getLineNumber());
        }
    }

    public static void e(String str, String str2, String str3) {
        if (f4631b) {
            StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
            if (stackTrace == null || stackTrace.length < 4) {
                o(str, str2, str3, 0);
            } else {
                o(str, str2, str3, stackTrace[3].getLineNumber());
            }
        }
    }

    public static void f(String str) {
        if (f4632c) {
            StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
            if (stackTrace == null || stackTrace.length < 4) {
                p("", "", str, 0);
                return;
            }
            String fileName = stackTrace[3].getFileName();
            int iLastIndexOf = fileName.lastIndexOf(46);
            if (iLastIndexOf > -1 && iLastIndexOf < fileName.length()) {
                fileName = fileName.substring(0, iLastIndexOf);
            }
            p(fileName, stackTrace[3].getMethodName(), str, stackTrace[3].getLineNumber());
        }
    }

    public static void g(String str, String str2) {
        if (f4632c) {
            StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
            if (stackTrace == null || stackTrace.length < 4) {
                p("", str, str2, 0);
                return;
            }
            String fileName = stackTrace[3].getFileName();
            int iLastIndexOf = fileName.lastIndexOf(46);
            if (iLastIndexOf > -1 && iLastIndexOf < fileName.length()) {
                fileName = fileName.substring(0, iLastIndexOf);
            }
            p(fileName, str, str2, stackTrace[3].getLineNumber());
        }
    }

    public static void h(String str, String str2) {
        i(str, "", str2);
    }

    public static void i(String str, String str2, String str3) {
        if (f4631b) {
            StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
            if (stackTrace == null || stackTrace.length < 4) {
                q(str, str2, str3, 0);
            } else {
                q(str, str2, str3, stackTrace[3].getLineNumber());
            }
        }
    }

    public static void j(String str) {
        if (f4630a) {
            StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
            if (stackTrace == null || stackTrace.length < 4) {
                r("", "", str, 0);
                return;
            }
            String fileName = stackTrace[3].getFileName();
            int iLastIndexOf = fileName.lastIndexOf(46);
            if (iLastIndexOf > -1 && iLastIndexOf < fileName.length()) {
                fileName = fileName.substring(0, iLastIndexOf);
            }
            r(fileName, stackTrace[3].getMethodName(), str, stackTrace[3].getLineNumber());
        }
    }

    public static void k(String str, String str2) {
        if (f4630a) {
            StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
            if (stackTrace == null || stackTrace.length < 4) {
                r("", str, str2, 0);
                return;
            }
            String fileName = stackTrace[3].getFileName();
            int iLastIndexOf = fileName.lastIndexOf(46);
            if (iLastIndexOf > -1 && iLastIndexOf < fileName.length()) {
                fileName = fileName.substring(0, iLastIndexOf);
            }
            r(fileName, str, str2, stackTrace[3].getLineNumber());
        }
    }

    public static void l(String str, String str2, String str3) {
        if (f4630a) {
            StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
            if (stackTrace == null || stackTrace.length < 4) {
                r(str, str2, str3, 0);
            } else {
                r(str, str2, str3, stackTrace[3].getLineNumber());
            }
        }
    }

    public static void m(String str, String str2) {
        n(str, "", str2);
    }

    public static void n(String str, String str2, String str3) {
        if (f4631b) {
            StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
            if (stackTrace == null || stackTrace.length < 4) {
                s(str, str2, str3, 0);
            } else {
                s(str, str2, str3, stackTrace[3].getLineNumber());
            }
        }
    }

    private static void o(String str, String str2, String str3, int i2) {
        Log.d(str, a(str2, str3, i2));
    }

    private static void p(String str, String str2, String str3, int i2) {
        Log.e(str, a(str2, str3, i2));
    }

    private static void q(String str, String str2, String str3, int i2) {
        Log.i(str, a(str2, str3, i2));
    }

    private static void r(String str, String str2, String str3, int i2) {
        Log.v(str, a(str2, str3, i2));
    }

    private static void s(String str, String str2, String str3, int i2) {
        Log.w(str, a(str2, str3, i2));
    }
}
