package defpackage;

import defpackage.rw;
import org.chromium.chrome.browser.compositor.CompositorView;

/* JADX INFO: compiled from: CompositorSurfaceManagerImpl.java */
/* JADX INFO: loaded from: classes.dex */
public final class pw implements Runnable {
    public final /* synthetic */ rw e;

    public pw(rw rwVar) {
        this.e = rwVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        rw rwVar = this.e;
        rw.b bVar = rwVar.g;
        if (bVar == null) {
            return;
        }
        bVar.b().getSurface();
        ((CompositorView) rwVar.i).h(true);
        rwVar.g = null;
        rwVar.c(bVar);
    }
}
