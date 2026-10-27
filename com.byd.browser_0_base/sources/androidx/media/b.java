package androidx.media;

import androidx.media.MediaBrowserServiceCompat;

/* JADX INFO: compiled from: MediaBrowserServiceCompat.java */
/* JADX INFO: loaded from: classes.dex */
public final class b implements Runnable {
    public final /* synthetic */ MediaBrowserServiceCompat.i e;
    public final /* synthetic */ MediaBrowserServiceCompat.h f;

    public b(MediaBrowserServiceCompat.h hVar, MediaBrowserServiceCompat.j jVar) {
        this.f = hVar;
        this.e = jVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        MediaBrowserServiceCompat.b bVarRemove = MediaBrowserServiceCompat.this.h.remove(((MediaBrowserServiceCompat.j) this.e).a());
        if (bVarRemove != null) {
            ((MediaBrowserServiceCompat.j) bVarRemove.d).a().unlinkToDeath(bVarRemove, 0);
        }
    }
}
