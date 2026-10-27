package androidx.media;

import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.text.TextUtils;
import android.util.Log;
import androidx.media.MediaBrowserServiceCompat;
import androidx.media.MediaBrowserServiceCompat.b;
import java.util.Iterator;

/* JADX INFO: compiled from: MediaBrowserServiceCompat.java */
/* JADX INFO: loaded from: classes.dex */
public final class f implements Runnable {
    public final /* synthetic */ MediaBrowserServiceCompat.i e;
    public final /* synthetic */ int f;
    public final /* synthetic */ String g;
    public final /* synthetic */ int h;
    public final /* synthetic */ MediaBrowserServiceCompat.h i;

    public f(int i, int i2, Bundle bundle, MediaBrowserServiceCompat.h hVar, MediaBrowserServiceCompat.j jVar, String str) {
        this.i = hVar;
        this.e = jVar;
        this.f = i;
        this.g = str;
        this.h = i2;
    }

    @Override // java.lang.Runnable
    public final void run() {
        MediaBrowserServiceCompat.b bVar;
        IBinder iBinderA = ((MediaBrowserServiceCompat.j) this.e).a();
        MediaBrowserServiceCompat.h hVar = this.i;
        MediaBrowserServiceCompat.this.h.remove(iBinderA);
        MediaBrowserServiceCompat mediaBrowserServiceCompat = MediaBrowserServiceCompat.this;
        Iterator<MediaBrowserServiceCompat.b> it = mediaBrowserServiceCompat.g.iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            MediaBrowserServiceCompat.b next = it.next();
            if (next.c == this.f) {
                bVar = (TextUtils.isEmpty(this.g) || this.h <= 0) ? MediaBrowserServiceCompat.this.new b(next.a, next.b, next.c, this.e) : null;
                it.remove();
            }
        }
        if (bVar == null) {
            bVar = MediaBrowserServiceCompat.this.new b(this.g, this.h, this.f, this.e);
        }
        mediaBrowserServiceCompat.h.put(iBinderA, bVar);
        try {
            iBinderA.linkToDeath(bVar, 0);
        } catch (RemoteException unused) {
            Log.w("MBServiceCompat", "IBinder is already dead.");
        }
    }
}
