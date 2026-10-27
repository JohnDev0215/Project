package g5;

import android.content.Context;
import com.bydux.api.common.tools.log.BydLogHelper;
import x3.a;

/* JADX INFO: compiled from: LogUtil.java */
/* JADX INFO: loaded from: classes2.dex */
public class h implements a.InterfaceC0144a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static int f5086a = 3;

    public h(Context context, int i8) {
        f5086a = i8;
        if (i8 < 5 && context != null) {
            BydLogHelper.init(context);
        }
    }

    public static String e(String[] strArr) {
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

    public static String f(String str) {
        return "Ue_" + str;
    }

    @Override // x3.a.InterfaceC0144a
    public void a(String str, String... strArr) {
        if (f5086a > 1) {
            return;
        }
        BydLogHelper.logD(f(str), "", e(strArr));
    }

    @Override // x3.a.InterfaceC0144a
    public void b(String str, String... strArr) {
        if (f5086a > 3) {
            return;
        }
        BydLogHelper.logW(f(str), "", e(strArr));
    }

    @Override // x3.a.InterfaceC0144a
    public void c(String str, String... strArr) {
        if (f5086a > 4) {
            return;
        }
        BydLogHelper.logE(f(str), "", e(strArr));
    }

    @Override // x3.a.InterfaceC0144a
    public void d(String str, String... strArr) {
        if (f5086a > 2) {
            return;
        }
        BydLogHelper.logI(f(str), "", e(strArr));
    }
}
