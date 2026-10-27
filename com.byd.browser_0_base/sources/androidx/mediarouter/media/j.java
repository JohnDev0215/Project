package androidx.mediarouter.media;

import androidx.mediarouter.media.h;

/* JADX INFO: compiled from: MediaRouter.java */
/* JADX INFO: loaded from: classes.dex */
public final class j implements Runnable {
    public final /* synthetic */ int e;
    public final /* synthetic */ l f;

    public j(l lVar, int i) {
        this.f = lVar;
        this.e = i;
    }

    @Override // java.lang.Runnable
    public final void run() {
        h.C0020h c0020h = h.d.this.r;
        if (c0020h != null) {
            c0020h.l(this.e);
        }
    }
}
