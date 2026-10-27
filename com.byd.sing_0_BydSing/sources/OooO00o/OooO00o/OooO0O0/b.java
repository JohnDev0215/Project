package OooO00o.OooO00o.OooO0O0;

import OooO00o.OooO00o.OooO0O0.OooO0o0.c;
import android.util.Log;
import com.loostone.libbottom.util.JniUtil;
import kotlin.jvm.internal.h;
import kotlin.text.StringsKt__StringsKt;
import kotlin.text.o;

/* JADX INFO: loaded from: classes.dex */
public final class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final b f41a = new b();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static int f42b = 1;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static Boolean f43c;

    public final String a(String str) {
        if (!c()) {
            return str;
        }
        int iW = StringsKt__StringsKt.w(str, "lst_", 0, false, 6, null);
        String strJ = str;
        if (iW != -1) {
            int iW2 = iW;
            do {
                try {
                    int iW3 = StringsKt__StringsKt.w(strJ, "_tsl", iW2, false, 4, null);
                    if (iW3 != -1 && iW3 > iW2) {
                        int i = iW2 + 4;
                        if (strJ == null) {
                            throw new NullPointerException("null cannot be cast to non-null type java.lang.String");
                        }
                        String strSubstring = strJ.substring(i, iW3);
                        h.c(strSubstring, "(this as java.lang.Strin…ing(startIndex, endIndex)");
                        strJ = o.j(strJ, strSubstring, JniUtil.f3799OooO00o.encryption(strSubstring, iW2), false, 4, null);
                    }
                } catch (Exception e) {
                    e("LogTuning", h.i("encryptMsg, error :: msg = ", str));
                    e.printStackTrace();
                }
                iW2 = StringsKt__StringsKt.w(strJ, "lst_", iW2 + 4, false, 4, null);
            } while (iW2 != -1);
        }
        return strJ;
    }

    public final void b(String str, String str2) {
        h.d(str, "tag");
        h.d(str2, "msg");
        if (f42b > 4) {
            return;
        }
        Log.d("LogTuning", str + ":  " + a(str2));
    }

    public final boolean c() {
        if (f43c == null) {
            f43c = Boolean.valueOf(c.f35a.a("loostone.log.enc", 1) > 0);
        }
        Boolean bool = f43c;
        if (bool == null) {
            return true;
        }
        return bool.booleanValue();
    }

    public final void d(String str, String str2) {
        h.d(str, "tag");
        h.d(str2, "msg");
        if (f42b > 1) {
            return;
        }
        Log.e("LogTuning", str + ":  " + a(str2));
    }

    public final void e(String str, String str2) {
        h.d(str, "tag");
        h.d(str2, "msg");
        if (f42b > 3) {
            return;
        }
        Log.i("LogTuning", str + ":  " + a(str2));
    }

    public final void f(String str, String str2) {
        h.d(str, "tag");
        h.d(str2, "msg");
        if (f42b > 5) {
            return;
        }
        Log.v("LogTuning", str + ":  " + a(str2));
    }

    public final void g(String str, String str2) {
        h.d(str, "tag");
        h.d(str2, "msg");
        if (f42b > 2) {
            return;
        }
        Log.w("LogTuning", str + ":  " + a(str2));
    }
}
