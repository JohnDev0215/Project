package defpackage;

import defpackage.aa2;
import org.chromium.chrome.browser.tab.TabImpl;

/* JADX INFO: compiled from: InterceptNavigationDelegateImpl.java */
/* JADX INFO: loaded from: classes2.dex */
public final class v11 implements Runnable {
    public final /* synthetic */ w11 e;

    public v11(w11 w11Var) {
        this.e = w11Var;
    }

    @Override // java.lang.Runnable
    public final void run() {
        w11 w11Var = this.e;
        TabImpl tabImpl = ((u11) w11Var.a).a;
        if (tabImpl.g == null) {
            return;
        }
        boolean z = tabImpl.getLaunchType() == 1;
        s11 s11Var = w11Var.a;
        if (z) {
            aa2.b bVar = ((u11) s11Var).a().a;
            if (bVar != null && bVar.d) {
                ((u11) s11Var).a.M().finishAndRemoveTask();
            } else {
                ((u11) s11Var).a.M().moveTaskToBack(false);
            }
        }
        TabImpl tabImpl2 = ((u11) s11Var).a;
        if (tabImpl2.m) {
            return;
        }
        tabImpl2.M().G2().a(tabImpl2);
    }
}
