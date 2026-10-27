package b.a.d.a;

import android.text.TextUtils;
import com.byd.mvp.mvp.XActivity;
import java.util.concurrent.ConcurrentHashMap;

/* JADX INFO: compiled from: ActivityCache.java */
/* JADX INFO: loaded from: classes2.dex */
public final class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static ConcurrentHashMap<String, XActivity> f320a = new ConcurrentHashMap<>();

    public static void a(String str, XActivity xActivity) {
        if (TextUtils.isEmpty(str) || xActivity == null) {
            return;
        }
        XActivity xActivity2 = f320a.get(str);
        b.a.d.b.a.a("ActivityCache", "----put-----ax: " + xActivity2);
        if (xActivity2 != null) {
            xActivity2.finish();
            f320a.remove(str);
        }
        f320a.put(str, xActivity);
    }

    public static void b(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        f320a.remove(str);
    }
}
