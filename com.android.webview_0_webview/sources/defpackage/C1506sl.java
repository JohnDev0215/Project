package defpackage;

import java.util.Collections;
import java.util.Map;

/* JADX INFO: renamed from: sl, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class C1506sl {
    public static volatile C1506sl b;
    public static final C1506sl c = new C1506sl();
    public final Map a = Collections.emptyMap();

    public static C1506sl a() {
        C1506sl c1506sl = b;
        if (c1506sl == null) {
            synchronized (C1506sl.class) {
                c1506sl = b;
                if (c1506sl == null) {
                    Class cls = AbstractC1451rl.a;
                    C1506sl c1506sl2 = null;
                    if (cls != null) {
                        try {
                            c1506sl2 = (C1506sl) cls.getDeclaredMethod("getEmptyRegistry", new Class[0]).invoke(null, new Object[0]);
                        } catch (Exception unused) {
                        }
                    }
                    if (c1506sl2 == null) {
                        c1506sl2 = c;
                    }
                    b = c1506sl2;
                    c1506sl = c1506sl2;
                }
            }
        }
        return c1506sl;
    }
}
