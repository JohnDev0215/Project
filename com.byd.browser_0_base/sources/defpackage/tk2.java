package defpackage;

import org.chromium.content_public.browser.WebContents;
import org.chromium.ui.base.WindowAndroid;

/* JADX INFO: compiled from: ShareServiceImplementationFactory.java */
/* JADX INFO: loaded from: classes2.dex */
public final class tk2 implements a21<ok2> {
    public final WebContents e;
    public nw2<ck2> f;
    public WindowAndroid g;

    public tk2(WebContents webContents) {
        this.e = webContents;
        WindowAndroid windowAndroidV = webContents.V();
        this.g = windowAndroidV;
        aj3<gk2> aj3Var = gk2.j;
        this.f = (zp1) gk2.j.e(windowAndroidV.r);
    }

    @Override // defpackage.a21
    public final y11 d() {
        return new rk2(this.e, new sk2(this));
    }
}
