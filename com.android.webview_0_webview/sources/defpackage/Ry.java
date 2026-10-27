package defpackage;

import android.content.Context;
import android.os.Bundle;
import org.chromium.content.browser.ChildProcessLauncherHelperImpl;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class Ry implements Runnable {
    public final /* synthetic */ Context s;
    public final /* synthetic */ boolean t;

    public Ry(Context context, boolean z) {
        this.s = context;
        this.t = z;
    }

    @Override // java.lang.Runnable
    public void run() {
        Context context = this.s;
        boolean z = this.t;
        Fz fz = z ? ChildProcessLauncherHelperImpl.n : ChildProcessLauncherHelperImpl.o;
        if (fz != null) {
            if (!(fz.b == null || fz.d != null)) {
                return;
            }
        }
        Bundle bundle = new Bundle();
        ChildProcessLauncherHelperImpl.d(bundle);
        Xt xtB = ChildProcessLauncherHelperImpl.b(context, z);
        if (z) {
            ChildProcessLauncherHelperImpl.n = new Fz(context, xtB, bundle);
        } else {
            ChildProcessLauncherHelperImpl.o = new Fz(context, xtB, bundle);
        }
    }
}
