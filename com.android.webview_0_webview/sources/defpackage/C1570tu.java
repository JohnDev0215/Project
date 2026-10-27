package defpackage;

import java.util.Objects;
import org.chromium.content.browser.ChildProcessLauncherHelperImpl;

/* JADX INFO: renamed from: tu, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class C1570tu implements InterfaceC1351pu {
    public final /* synthetic */ boolean a;
    public final /* synthetic */ boolean b;
    public final /* synthetic */ C1735wu c;

    public C1570tu(C1735wu c1735wu, boolean z, boolean z2) {
        this.c = c1735wu;
        this.a = z;
        this.b = z2;
    }

    @Override // defpackage.InterfaceC1351pu
    public void a(C1406qu c1406qu) {
        C1735wu c1735wu = this.c;
        C1406qu c1406qu2 = c1735wu.g;
        if ((c1406qu2 == null ? 0 : c1406qu2.l) != 0) {
            AbstractC1680vu abstractC1680vu = c1735wu.b;
            C1406qu c1406qu3 = c1735wu.g;
            Qy qy = (Qy) abstractC1680vu;
            Objects.requireNonNull(qy);
            int i = c1406qu3.l;
            if (i == 0 || ((ChildProcessLauncherHelperImpl) ChildProcessLauncherHelperImpl.r.remove(Integer.valueOf(i))) == null) {
                return;
            }
            Objects.requireNonNull(qy.a);
            ChildProcessLauncherHelperImpl childProcessLauncherHelperImpl = qy.a;
            Yy yy = childProcessLauncherHelperImpl.a;
            if (yy != null) {
                int size = (yy.u.size() - 1) - yy.f(c1406qu3);
                synchronized (childProcessLauncherHelperImpl.k) {
                    childProcessLauncherHelperImpl.l = size;
                }
                Yy yy2 = qy.a.a;
                yy2.u.remove(yy2.f(c1406qu3));
                Objects.requireNonNull(qy.a);
            }
        }
    }

    @Override // defpackage.InterfaceC1351pu
    public void b() {
    }

    @Override // defpackage.InterfaceC1351pu
    public void c(C1406qu c1406qu) {
        Ts.a("ChildProcLauncher", "ChildProcessConnection.start failed, trying again", new Object[0]);
        this.c.a.post(new RunnableC1515su(this));
    }
}
