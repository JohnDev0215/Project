package androidx.media;

import android.os.Bundle;
import android.os.IBinder;
import android.support.v4.os.ResultReceiver;
import android.util.Log;
import androidx.media.MediaBrowserServiceCompat;

/* JADX INFO: compiled from: MediaBrowserServiceCompat.java */
/* JADX INFO: loaded from: classes.dex */
public final class i implements Runnable {
    public final /* synthetic */ MediaBrowserServiceCompat.i e;
    public final /* synthetic */ String f;
    public final /* synthetic */ Bundle g;
    public final /* synthetic */ ResultReceiver h;
    public final /* synthetic */ MediaBrowserServiceCompat.h i;

    public i(MediaBrowserServiceCompat.h hVar, MediaBrowserServiceCompat.j jVar, String str, Bundle bundle, ResultReceiver resultReceiver) {
        this.i = hVar;
        this.e = jVar;
        this.f = str;
        this.g = bundle;
        this.h = resultReceiver;
    }

    @Override // java.lang.Runnable
    public final void run() {
        IBinder iBinderA = ((MediaBrowserServiceCompat.j) this.e).a();
        MediaBrowserServiceCompat.h hVar = this.i;
        if (MediaBrowserServiceCompat.this.h.get(iBinderA) != null) {
            MediaBrowserServiceCompat.this.getClass();
            this.h.send(-1, null);
            return;
        }
        Log.w("MBServiceCompat", "sendCustomAction for callback that isn't registered action=" + this.f + ", extras=" + this.g);
    }
}
