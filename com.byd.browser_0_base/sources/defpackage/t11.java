package defpackage;

import org.chromium.chrome.browser.tab.Tab;
import org.chromium.chrome.browser.tab.TabImpl;
import org.chromium.content_public.browser.NavigationController;
import org.chromium.content_public.browser.NavigationHandle;
import org.chromium.content_public.browser.WebContents;
import org.chromium.ui.base.WindowAndroid;

/* JADX INFO: compiled from: InterceptNavigationDelegateClientImpl.java */
/* JADX INFO: loaded from: classes2.dex */
public final class t11 extends nh0 {
    public final /* synthetic */ u11 e;

    public t11(u11 u11Var) {
        this.e = u11Var;
    }

    @Override // defpackage.nh0, defpackage.n23
    public final void F0(Tab tab) {
        this.e.c.a(null);
    }

    @Override // defpackage.n23
    public final void P0(Tab tab, WindowAndroid windowAndroid) {
        if (windowAndroid != null) {
            u11 u11Var = this.e;
            w11 w11Var = u11Var.c;
            TabImpl tabImpl = u11Var.a;
            w11Var.c = tabImpl.x.d(tabImpl);
        }
    }

    @Override // defpackage.nh0, defpackage.n23
    public final void o1(TabImpl tabImpl, NavigationHandle navigationHandle) {
        w11 w11Var = this.e.c;
        w11Var.getClass();
        if (navigationHandle.g) {
            u11 u11Var = (u11) w11Var.a;
            WebContents webContents = u11Var.a.g;
            if (w11Var.d && webContents != null) {
                webContents.m().u();
            } else if (w11Var.e && webContents != null) {
                NavigationController navigationControllerM = webContents.m();
                int i = u11Var.a().d;
                int iC = w11Var.c();
                while (true) {
                    iC--;
                    if (iC <= i) {
                        break;
                    } else {
                        navigationControllerM.p(iC);
                    }
                }
            }
            w11Var.d = false;
            w11Var.e = false;
        }
    }

    @Override // defpackage.nh0, defpackage.n23
    public final void u(Tab tab) {
        this.e.c.a(tab.c());
    }
}
