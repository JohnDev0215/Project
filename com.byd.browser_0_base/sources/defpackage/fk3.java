package defpackage;

import android.app.Activity;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import org.chromium.base.CollectionUtil;
import org.chromium.base.ThreadUtils;
import org.chromium.chrome.browser.AppHooks;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.chrome.browser.usage_stats.UsageStatsBridge;

/* JADX INFO: compiled from: UsageStatsService.java */
/* JADX INFO: loaded from: classes2.dex */
public final class fk3 {
    public static fk3 i;
    public final Profile a;
    public final gi0 b;
    public final hp1 c;
    public final zw2 d;
    public final ob3 e;
    public final ArrayList f;
    public final d70 g;
    public boolean h;

    public fk3() {
        Profile profileD = Profile.d();
        this.a = profileD;
        UsageStatsBridge usageStatsBridge = new UsageStatsBridge(profileD, this);
        this.b = new gi0(usageStatsBridge);
        hp1 hp1Var = new hp1(profileD);
        this.c = hp1Var;
        zw2 zw2Var = new zw2(usageStatsBridge, hp1Var);
        this.d = zw2Var;
        this.e = new ob3(usageStatsBridge);
        this.f = new ArrayList();
        AppHooks.get().getClass();
        this.g = new d70();
        zw2Var.c.f(new xj(2)).g(new dr(29, this));
        Object obj = ThreadUtils.a;
        this.h = oj2.k(profileD).a("usage_stats_reporting.enabled");
    }

    public static void a(Activity activity, z2 z2Var, aq1 aq1Var) {
        fk3 fk3VarB = b();
        fk3VarB.getClass();
        Object obj = ThreadUtils.a;
        fk3VarB.f.add(new WeakReference(new zu1(activity, z2Var, fk3VarB.b, fk3VarB.e, fk3VarB.d, aq1Var)));
    }

    public static fk3 b() {
        if (i == null) {
            i = new fk3();
        }
        return i;
    }

    public final void c(List<String> list, boolean z) {
        for (zu1 zu1Var : CollectionUtil.f(this.f)) {
            for (String str : list) {
                Tab tab = zu1Var.j;
                if (tab != null && tab.isInitialized()) {
                    ww2 ww2VarZ1 = ww2.z1(zu1Var.j, zu1Var.i);
                    if (str.equals(zu1Var.k) || str.equals(ww2VarZ1.h)) {
                        if (zu1Var.y1(str, z)) {
                            xs3 xs3Var = new xs3(2, zu1Var.k, System.currentTimeMillis());
                            gi0 gi0Var = zu1Var.f;
                            gi0Var.getClass();
                            gi0Var.b.h(new yo1(gi0Var, 6, xs3Var, new s62()), new xo(10));
                            String str2 = zu1Var.k;
                            ob3 ob3Var = zu1Var.g;
                            ob3Var.getClass();
                            ob3Var.a.f(new n91(3, str2)).g(new gp(zu1Var, 23, "reportUsageStop"));
                            zu1Var.k = null;
                        }
                    }
                }
            }
        }
    }
}
