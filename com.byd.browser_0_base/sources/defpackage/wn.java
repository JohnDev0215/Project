package defpackage;

import android.content.Context;
import org.chromium.content.browser.ChildProcessLauncherHelperImpl;

/* JADX INFO: compiled from: ChildProcessLauncherHelperImpl.java */
/* JADX INFO: loaded from: classes3.dex */
public final class wn implements Runnable {
    public final /* synthetic */ Context e;
    public final /* synthetic */ int f;

    public wn(Context context, int i) {
        this.e = context;
        this.f = i;
    }

    @Override // java.lang.Runnable
    public final void run() {
        Context context = this.e;
        ChildProcessLauncherHelperImpl.a(context, true);
        ChildProcessLauncherHelperImpl.v = new re(context, this.f, ChildProcessLauncherHelperImpl.r);
        rn.a().b = ChildProcessLauncherHelperImpl.v;
    }
}
