package b.b.g.r;

import android.content.Context;
import b.b.m.c.a;
import com.bydux.api.common.tools.log.BydLogHelper;

/* JADX INFO: compiled from: LogUtil.java */
/* JADX INFO: loaded from: classes2.dex */
public class q implements a.InterfaceC0085a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static int f1828a = 3;

    public q(Context context, int i) {
        f1828a = i;
        if (i < 5 && context != null) {
            BydLogHelper.init(context);
        }
    }

    public static String f(String[] strArr) {
        StringBuilder sb = new StringBuilder();
        if (strArr != null && strArr.length > 0) {
            for (String str : strArr) {
                sb.append(str);
                sb.append("/");
            }
        }
        if (sb.toString().endsWith("/")) {
            sb.deleteCharAt(sb.toString().length() - 1);
        }
        return sb.toString();
    }

    public static String g(String str) {
        return "CSS_" + str;
    }

    @Override // b.b.m.c.a.InterfaceC0085a
    public void a(String str, String... strArr) {
        if (f1828a > 1) {
            return;
        }
        BydLogHelper.logD(g(str), "", f(strArr));
    }

    @Override // b.b.m.c.a.InterfaceC0085a
    public void b(String str, String... strArr) {
        if (f1828a > 3) {
            return;
        }
        BydLogHelper.logW(g(str), "", f(strArr));
    }

    @Override // b.b.m.c.a.InterfaceC0085a
    public void c(String str, String... strArr) {
        if (f1828a > 4) {
            return;
        }
        BydLogHelper.logE(g(str), "", f(strArr));
    }

    @Override // b.b.m.c.a.InterfaceC0085a
    public void d(String str, String... strArr) {
        if (f1828a > 0) {
            return;
        }
        BydLogHelper.logV(g(str), "", f(strArr));
    }

    @Override // b.b.m.c.a.InterfaceC0085a
    public void e(String str, String... strArr) {
        if (f1828a > 2) {
            return;
        }
        BydLogHelper.logI(g(str), "", f(strArr));
    }
}
