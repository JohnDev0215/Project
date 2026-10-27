package defpackage;

import org.chromium.ui.base.WindowAndroid;

/* JADX INFO: compiled from: BrowserControlsManagerSupplier.java */
/* JADX INFO: loaded from: classes.dex */
public final class hj extends bj3<fj> {
    public static final aj3<hj> j = new aj3<>(hj.class);

    public hj() {
        super(j);
    }

    public static fj t(WindowAndroid windowAndroid) {
        zp1 zp1Var = windowAndroid == null ? null : (zp1) j.e(windowAndroid.r);
        if (zp1Var == null) {
            return null;
        }
        return (fj) zp1Var.get();
    }
}
