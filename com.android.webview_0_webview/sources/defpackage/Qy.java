package defpackage;

import org.chromium.content.browser.ChildProcessLauncherHelperImpl;
import org.chromium.content.browser.LauncherThread;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class Qy extends AbstractC1680vu {
    public final /* synthetic */ ChildProcessLauncherHelperImpl a;

    public Qy(ChildProcessLauncherHelperImpl childProcessLauncherHelperImpl) {
        this.a = childProcessLauncherHelperImpl;
    }

    @Override // defpackage.AbstractC1680vu
    public C1406qu a(Xt xt, InterfaceC1351pu interfaceC1351pu) {
        ChildProcessLauncherHelperImpl childProcessLauncherHelperImpl = this.a;
        if (!childProcessLauncherHelperImpl.d) {
            return null;
        }
        Fz fz = childProcessLauncherHelperImpl.c ? ChildProcessLauncherHelperImpl.n : ChildProcessLauncherHelperImpl.o;
        if (fz == null) {
            return null;
        }
        if ((fz.b == null || fz.d != null) || fz.a != xt || fz.d != null) {
            return null;
        }
        fz.d = interfaceC1351pu;
        C1406qu c1406qu = fz.b;
        if (fz.c) {
            LauncherThread.c.post(new Ez(fz, interfaceC1351pu));
            fz.b = null;
            fz.c = false;
        }
        return c1406qu;
    }
}
