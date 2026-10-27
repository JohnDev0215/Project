package androidx.media;

import android.os.Bundle;
import android.os.IBinder;
import android.support.v4.os.ResultReceiver;
import android.util.Log;
import androidx.media.MediaBrowserServiceCompat;

/* JADX INFO: compiled from: MediaBrowserServiceCompat.java */
/* JADX INFO: loaded from: classes.dex */
public final class e implements Runnable {
    public final /* synthetic */ MediaBrowserServiceCompat.i e;
    public final /* synthetic */ String f;
    public final /* synthetic */ ResultReceiver g;
    public final /* synthetic */ MediaBrowserServiceCompat.h h;

    public e(MediaBrowserServiceCompat.h hVar, MediaBrowserServiceCompat.j jVar, String str, ResultReceiver resultReceiver) {
        this.h = hVar;
        this.e = jVar;
        this.f = str;
        this.g = resultReceiver;
    }

    @Override // java.lang.Runnable
    public final void run() {
        IBinder iBinderA = ((MediaBrowserServiceCompat.j) this.e).a();
        MediaBrowserServiceCompat.h hVar = this.h;
        if (MediaBrowserServiceCompat.this.h.get(iBinderA) == null) {
            Log.w("MBServiceCompat", "getMediaItem for callback that isn't registered id=" + this.f);
            return;
        }
        MediaBrowserServiceCompat.this.getClass();
        int i = 2 & 2;
        ResultReceiver resultReceiver = this.g;
        if (i != 0) {
            resultReceiver.send(-1, null);
            return;
        }
        Bundle bundle = new Bundle();
        bundle.putParcelable("media_item", null);
        resultReceiver.send(0, bundle);
    }
}
