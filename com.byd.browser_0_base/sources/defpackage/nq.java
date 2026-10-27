package defpackage;

import org.chromium.base.task.PostTask;

/* JADX INFO: compiled from: ChromeNativeBackgroundTaskDelegate.java */
/* JADX INFO: loaded from: classes.dex */
public final class nq extends n3 {
    public final /* synthetic */ Runnable H;
    public final /* synthetic */ boolean I;

    /* JADX INFO: renamed from: J, reason: collision with root package name */
    public final /* synthetic */ Runnable f49J;

    public nq(Runnable runnable, boolean z, Runnable runnable2) {
        this.H = runnable;
        this.I = z;
        this.f49J = runnable2;
    }

    @Override // defpackage.n3, defpackage.nj
    public final void K() {
        PostTask.c(rh3.a, this.H);
    }

    @Override // defpackage.nj
    public final boolean f1() {
        return this.I;
    }

    @Override // defpackage.n3, defpackage.nj
    public final void q(Exception exc) {
        PostTask.c(rh3.a, this.f49J);
    }
}
