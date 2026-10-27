package defpackage;

import org.chromium.chrome.browser.tab.Tab;
import org.chromium.chrome.features.start_surface.StartSurfaceConfiguration;

/* JADX INFO: compiled from: StartSurfaceUserData.java */
/* JADX INFO: loaded from: classes2.dex */
public final class ds2 implements lk3 {
    public static boolean h;
    public boolean e;
    public boolean f;
    public boolean g;

    /* JADX INFO: compiled from: StartSurfaceUserData.java */
    public static class a {
        public static final ds2 a = new ds2();
    }

    public ds2() {
        h = true;
    }

    public static ds2 a(Tab tab) {
        if (tab.isDestroyed()) {
            return null;
        }
        return (ds2) tab.B().b(ds2.class);
    }

    public static void b(Tab tab) {
        if (tab == null || tab.getLaunchType() != 12) {
            return;
        }
        ds2 ds2VarA = a(tab);
        if (ds2VarA == null) {
            ds2VarA = new ds2();
        }
        ds2VarA.e = true;
        tab.B().d(ds2.class, ds2VarA);
    }

    public static void c(Tab tab) {
        if (tab == null || !StartSurfaceConfiguration.a()) {
            return;
        }
        ds2 ds2VarA = a(tab);
        if (ds2VarA == null) {
            ds2VarA = new ds2();
        }
        if (ds2VarA.f) {
            return;
        }
        ds2VarA.f = true;
        tab.B().d(ds2.class, ds2VarA);
    }
}
