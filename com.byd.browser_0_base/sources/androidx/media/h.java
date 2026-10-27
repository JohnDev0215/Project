package androidx.media;

import android.os.Bundle;
import android.os.IBinder;
import android.support.v4.os.ResultReceiver;
import android.util.Log;
import androidx.media.MediaBrowserServiceCompat;

/* JADX INFO: compiled from: MediaBrowserServiceCompat.java */
/* JADX INFO: loaded from: classes.dex */
public final class h implements Runnable {
    public final /* synthetic */ MediaBrowserServiceCompat.i e;
    public final /* synthetic */ String f;
    public final /* synthetic */ ResultReceiver g;
    public final /* synthetic */ MediaBrowserServiceCompat.h h;

    public h(MediaBrowserServiceCompat.h hVar, MediaBrowserServiceCompat.j jVar, String str, Bundle bundle, ResultReceiver resultReceiver) {
        this.h = hVar;
        this.e = jVar;
        this.f = str;
        this.g = resultReceiver;
    }

    @Override // java.lang.Runnable
    public final void run() {
        IBinder iBinderA = ((MediaBrowserServiceCompat.j) this.e).a();
        MediaBrowserServiceCompat.h hVar = this.h;
        if (MediaBrowserServiceCompat.this.h.get(iBinderA) != null) {
            MediaBrowserServiceCompat.this.getClass();
            this.g.send(-1, null);
        } else {
            Log.w("MBServiceCompat", "search for callback that isn't registered query=" + this.f);
        }
    }
}
