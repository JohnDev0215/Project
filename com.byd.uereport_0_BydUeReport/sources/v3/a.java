package v3;

import android.text.TextUtils;
import com.byd.mvp.mvp.XActivity;
import java.util.concurrent.ConcurrentHashMap;

/* JADX INFO: compiled from: ActivityCache.java */
/* JADX INFO: loaded from: classes2.dex */
public final class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static ConcurrentHashMap<String, XActivity> f8568a = new ConcurrentHashMap<>();

    public static void a(String str, XActivity xActivity) {
        if (TextUtils.isEmpty(str) || xActivity == null) {
            return;
        }
        XActivity xActivity2 = f8568a.get(str);
        x3.a.a("ActivityCache", "----put-----ax: " + xActivity2);
        if (xActivity2 != null) {
            xActivity2.finish();
            f8568a.remove(str);
        }
        f8568a.put(str, xActivity);
    }

    public static void b(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        f8568a.remove(str);
    }
}
