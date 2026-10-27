package defpackage;

import java.util.function.BooleanSupplier;
import org.chromium.chrome.browser.tab.TrustedCdn;
import org.chromium.ui.base.WindowAndroid;

/* JADX INFO: compiled from: CustomTabTrustedCdnPublisherUrlVisibility.java */
/* JADX INFO: loaded from: classes.dex */
public final class v40 implements TrustedCdn.a, v80, zi3 {
    public WindowAndroid e;
    public BooleanSupplier f;

    public v40(g3 g3Var, r2 r2Var, s20 s20Var) {
        this.e = g3Var;
        this.f = s20Var;
        r2Var.f(this);
        TrustedCdn.a.d.a(this.e.r, this);
    }

    @Override // defpackage.v80
    public final void onDestroy() {
        TrustedCdn.a.d.b(this);
        this.e = null;
        this.f = null;
    }

    @Override // org.chromium.chrome.browser.tab.TrustedCdn.a
    public final boolean s0() {
        return this.f.getAsBoolean();
    }
}
