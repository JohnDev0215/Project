package defpackage;

import android.content.Context;
import android.os.Bundle;
import org.chromium.base.library_loader.a;
import org.chromium.content.browser.ChildProcessLauncherHelperImpl;

/* JADX INFO: compiled from: ChildProcessLauncherHelperImpl.java */
/* JADX INFO: loaded from: classes3.dex */
public final class vn implements Runnable {
    public final /* synthetic */ Context e;
    public final /* synthetic */ boolean f;

    public vn(Context context, boolean z) {
        this.e = context;
        this.f = z;
    }

    @Override // java.lang.Runnable
    public final void run() {
        boolean z = this.f;
        lq2 lq2Var = z ? ChildProcessLauncherHelperImpl.o : ChildProcessLauncherHelperImpl.p;
        if (lq2Var != null) {
            if (!(lq2Var.b == null || lq2Var.d != null)) {
                return;
            }
        }
        Bundle bundle = new Bundle();
        bundle.putBoolean("org.chromium.base.process_launcher.extra.bind_to_caller", false);
        a.C0070a c0070a = a.l.g;
        c0070a.b();
        c0070a.d(bundle);
        Context context = this.e;
        gn gnVarA = ChildProcessLauncherHelperImpl.a(context, z);
        if (z) {
            ChildProcessLauncherHelperImpl.o = new lq2(context, gnVarA, bundle);
        } else {
            ChildProcessLauncherHelperImpl.p = new lq2(context, gnVarA, bundle);
        }
    }
}
