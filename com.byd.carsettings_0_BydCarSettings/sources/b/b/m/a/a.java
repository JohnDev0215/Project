package b.b.m.a;

import android.text.TextUtils;
import com.byd.mvp.mvp.XActivity;
import java.util.concurrent.ConcurrentHashMap;

/* JADX INFO: compiled from: ActivityCache.java */
/* JADX INFO: loaded from: classes2.dex */
public final class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static ConcurrentHashMap<String, XActivity> f2096a = new ConcurrentHashMap<>();

    public static void a(String str) {
        XActivity xActivity = f2096a.get(str);
        if (xActivity == null) {
            return;
        }
        xActivity.finish();
    }

    public static void b() {
        b.b.m.c.a.a("ActivityCache", "----finishAll-----");
        if (f2096a.isEmpty()) {
            return;
        }
        for (XActivity xActivity : f2096a.values()) {
            b.b.m.c.a.a("ActivityCache", "----finishAll-----act: " + xActivity);
            xActivity.finish();
        }
        f2096a.clear();
    }

    public static void c(String str) {
        b.b.m.c.a.a("ActivityCache", "----finishWithout-----key: " + str);
        if (f2096a.isEmpty()) {
            return;
        }
        for (XActivity xActivity : f2096a.values()) {
            if (!xActivity.getClass().getName().equals(str)) {
                b.b.m.c.a.a("ActivityCache", "----finishWithout-----act: " + xActivity);
                xActivity.finish();
            }
        }
    }

    public static boolean d() {
        if (f2096a.isEmpty()) {
            return false;
        }
        for (XActivity xActivity : f2096a.values()) {
            b.b.m.c.a.a("ActivityCache", "----hasActivityOnForeground-----act: " + xActivity + ", OnForeground: " + xActivity.p());
            if (!xActivity.a() && xActivity.p()) {
                return true;
            }
        }
        return false;
    }

    public static void e(String str, XActivity xActivity) {
        if (TextUtils.isEmpty(str) || xActivity == null) {
            return;
        }
        XActivity xActivity2 = f2096a.get(str);
        b.b.m.c.a.a("ActivityCache", "----put-----ax: " + xActivity2);
        if (xActivity2 != null) {
            xActivity2.finish();
            f2096a.remove(str);
        }
        f2096a.put(str, xActivity);
    }

    public static void f(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        f2096a.remove(str);
    }
}
