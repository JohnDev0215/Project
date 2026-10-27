package org.chromium.chrome.browser.tab;

import J.N;
import defpackage.aj3;
import defpackage.m53;
import defpackage.n3;
import defpackage.zi3;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.content_public.browser.WebContents;
import org.chromium.ui.base.WindowAndroid;

/* JADX INFO: loaded from: classes2.dex */
public class TrustedCdn extends m53 {
    public final Tab f;
    public final long g;
    public String h;

    public interface a extends zi3 {
        public static final aj3<a> d = new aj3<>(a.class);

        boolean s0();
    }

    public TrustedCdn(Tab tab) {
        super(tab);
        this.f = tab;
        this.g = N.M1Q9lmqc(this);
    }

    public static String d(Tab tab) {
        Tab tab2;
        WebContents webContentsC;
        WindowAndroid windowAndroidV;
        TrustedCdn trustedCdn = tab != null ? (TrustedCdn) tab.B().b(TrustedCdn.class) : null;
        if (trustedCdn == null || (webContentsC = (tab2 = trustedCdn.f).c()) == null || (windowAndroidV = webContentsC.V()) == null) {
            return null;
        }
        aj3<a> aj3Var = a.d;
        a aVar = (a) a.d.e(windowAndroidV.r);
        if (aVar == null || !aVar.s0() || n3.o0(tab2.c()) == 5) {
            return null;
        }
        return trustedCdn.h;
    }

    @Override // defpackage.m53
    public final void a(WebContents webContents) {
        N.M003oy2o(this.g, this);
        this.h = null;
    }

    @Override // defpackage.m53
    public final void b() {
        N.MM2LHRfv(this.g, this);
    }

    @Override // defpackage.m53
    public final void c(WebContents webContents) {
        N.MyyZwXPU(this.g, this, webContents);
    }

    @CalledByNative
    public final void setPublisherUrl(String str) {
        this.h = str;
    }
}
