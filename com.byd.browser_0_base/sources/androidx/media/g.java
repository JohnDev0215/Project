package androidx.media;

import android.os.IBinder;
import androidx.media.MediaBrowserServiceCompat;

/* JADX INFO: compiled from: MediaBrowserServiceCompat.java */
/* JADX INFO: loaded from: classes.dex */
public final class g implements Runnable {
    public final /* synthetic */ MediaBrowserServiceCompat.i e;
    public final /* synthetic */ MediaBrowserServiceCompat.h f;

    public g(MediaBrowserServiceCompat.h hVar, MediaBrowserServiceCompat.j jVar) {
        this.f = hVar;
        this.e = jVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        IBinder iBinderA = ((MediaBrowserServiceCompat.j) this.e).a();
        MediaBrowserServiceCompat.b bVarRemove = MediaBrowserServiceCompat.this.h.remove(iBinderA);
        if (bVarRemove != null) {
            iBinderA.unlinkToDeath(bVarRemove, 0);
        }
    }
}
