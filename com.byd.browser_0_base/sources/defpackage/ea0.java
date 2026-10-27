package defpackage;

import org.chromium.chrome.browser.AppHooks;

/* JADX INFO: compiled from: DirectActionInitializer.java */
/* JADX INFO: loaded from: classes.dex */
public final class ea0 implements hk1, v80 {
    public final y13 e;

    public ea0(int i, jf1 jf1Var, ve3 ve3Var, y13 y13Var, xm0 xm0Var) {
        this.e = y13Var;
    }

    @Override // defpackage.hk1
    public final void G0() {
        AppHooks.get().getClass();
    }

    @Override // defpackage.v80
    public final void onDestroy() {
    }
}
