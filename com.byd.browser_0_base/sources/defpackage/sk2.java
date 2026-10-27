package defpackage;

import defpackage.rk2;
import org.chromium.content_public.browser.WebContents;
import org.chromium.ui.base.WindowAndroid;

/* JADX INFO: compiled from: ShareServiceImplementationFactory.java */
/* JADX INFO: loaded from: classes2.dex */
public final class sk2 implements rk2.a {
    public final /* synthetic */ tk2 a;

    public sk2(tk2 tk2Var) {
        this.a = tk2Var;
    }

    public final ck2 a() {
        tk2 tk2Var = this.a;
        WindowAndroid windowAndroid = tk2Var.g;
        WebContents webContents = tk2Var.e;
        if (windowAndroid.equals(webContents.V())) {
            return tk2Var.f.get();
        }
        WindowAndroid windowAndroidV = webContents.V();
        tk2Var.g = windowAndroidV;
        aj3<gk2> aj3Var = gk2.j;
        zp1 zp1Var = (zp1) gk2.j.e(windowAndroidV.r);
        tk2Var.f = zp1Var;
        return (ck2) zp1Var.get();
    }
}
