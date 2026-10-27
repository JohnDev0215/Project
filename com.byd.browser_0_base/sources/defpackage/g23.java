package defpackage;

import org.chromium.chrome.browser.tab.Tab;
import org.chromium.ui.base.WindowAndroid;

/* JADX INFO: compiled from: TabModelSelectorSupplier.java */
/* JADX INFO: loaded from: classes2.dex */
public final class g23 extends bj3<y13> {
    public static final aj3<g23> j = new aj3<>(g23.class);

    public g23() {
        super(j);
    }

    public static zp1<y13> t(WindowAndroid windowAndroid) {
        return (zp1) j.e(windowAndroid.r);
    }

    public static Tab u(WindowAndroid windowAndroid) {
        zp1<y13> zp1VarT = t(windowAndroid);
        if (zp1VarT == null || !zp1VarT.f()) {
            return null;
        }
        return zp1VarT.get().i();
    }
}
