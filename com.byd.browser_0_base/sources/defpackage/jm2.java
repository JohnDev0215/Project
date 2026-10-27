package defpackage;

import org.chromium.chrome.browser.signin.SigninFirstRunFragment;
import org.chromium.chrome.browser.signin.services.SigninManager;

/* JADX INFO: compiled from: SigninFirstRunMediator.java */
/* JADX INFO: loaded from: classes2.dex */
public final class jm2 implements SigninManager.a {
    public final /* synthetic */ km2 a;

    public jm2(km2 km2Var) {
        this.a = km2Var;
    }

    @Override // org.chromium.chrome.browser.signin.services.SigninManager.a
    public final void a() {
        km2 km2Var = this.a;
        if (km2Var.l) {
            return;
        }
        ((SigninFirstRunFragment) km2Var.h).H0();
    }

    @Override // org.chromium.chrome.browser.signin.services.SigninManager.a
    public final void b() {
        km2 km2Var = this.a;
        km2Var.j.l(lm2.f, false);
        km2Var.j.l(lm2.g, false);
    }
}
