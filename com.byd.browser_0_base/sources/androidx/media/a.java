package androidx.media;

import android.os.Bundle;
import android.os.IBinder;
import android.os.Message;
import android.os.RemoteException;
import android.util.Log;
import androidx.media.MediaBrowserServiceCompat;
import androidx.media.MediaBrowserServiceCompat.b;
import defpackage.C0175b;

/* JADX INFO: compiled from: MediaBrowserServiceCompat.java */
/* JADX INFO: loaded from: classes.dex */
public final class a implements Runnable {
    public final /* synthetic */ MediaBrowserServiceCompat.i e;
    public final /* synthetic */ String f;
    public final /* synthetic */ int g;
    public final /* synthetic */ int h;
    public final /* synthetic */ MediaBrowserServiceCompat.h i;

    public a(int i, int i2, Bundle bundle, MediaBrowserServiceCompat.h hVar, MediaBrowserServiceCompat.j jVar, String str) {
        this.i = hVar;
        this.e = jVar;
        this.f = str;
        this.g = i;
        this.h = i2;
    }

    @Override // java.lang.Runnable
    public final void run() {
        MediaBrowserServiceCompat.i iVar = this.e;
        IBinder iBinderA = ((MediaBrowserServiceCompat.j) iVar).a();
        MediaBrowserServiceCompat.h hVar = this.i;
        MediaBrowserServiceCompat.this.h.remove(iBinderA);
        MediaBrowserServiceCompat.b bVar = MediaBrowserServiceCompat.this.new b(this.f, this.g, this.h, this.e);
        MediaBrowserServiceCompat mediaBrowserServiceCompat = MediaBrowserServiceCompat.this;
        mediaBrowserServiceCompat.getClass();
        bVar.f = mediaBrowserServiceCompat.a();
        mediaBrowserServiceCompat.getClass();
        MediaBrowserServiceCompat.a aVar = bVar.f;
        String str = this.f;
        if (aVar != null) {
            try {
                mediaBrowserServiceCompat.h.put(iBinderA, bVar);
                iBinderA.linkToDeath(bVar, 0);
                mediaBrowserServiceCompat.getClass();
                return;
            } catch (RemoteException unused) {
                Log.w("MBServiceCompat", "Calling onConnect() failed. Dropping client. pkg=" + str);
                mediaBrowserServiceCompat.h.remove(iBinderA);
                return;
            }
        }
        StringBuilder sbN = C0175b.n("No root for client ", str, " from service ");
        sbN.append(a.class.getName());
        Log.i("MBServiceCompat", sbN.toString());
        try {
            MediaBrowserServiceCompat.j jVar = (MediaBrowserServiceCompat.j) iVar;
            jVar.getClass();
            Message messageObtain = Message.obtain();
            messageObtain.what = 2;
            messageObtain.arg1 = 2;
            messageObtain.setData(null);
            jVar.a.send(messageObtain);
        } catch (RemoteException unused2) {
            Log.w("MBServiceCompat", "Calling onConnectFailed() failed. Ignoring. pkg=" + str);
        }
    }
}
