package defpackage;

import defpackage.rw;

/* JADX INFO: compiled from: CompositorSurfaceManagerImpl.java */
/* JADX INFO: loaded from: classes.dex */
public final class qw implements Runnable {
    public final /* synthetic */ rw.b e;
    public final /* synthetic */ rw f;

    public qw(rw rwVar, rw.b bVar) {
        this.f = rwVar;
        this.e = bVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.f.a(this.e);
    }
}
