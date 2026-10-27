package androidx.media;

import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import androidx.media.MediaBrowserServiceCompat;
import defpackage.oj2;
import defpackage.qv1;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: compiled from: MediaBrowserServiceCompat.java */
/* JADX INFO: loaded from: classes.dex */
public final class c implements Runnable {
    public final /* synthetic */ MediaBrowserServiceCompat.i e;
    public final /* synthetic */ String f;
    public final /* synthetic */ IBinder g;
    public final /* synthetic */ Bundle h;
    public final /* synthetic */ MediaBrowserServiceCompat.h i;

    public c(MediaBrowserServiceCompat.h hVar, MediaBrowserServiceCompat.j jVar, String str, IBinder iBinder, Bundle bundle) {
        this.i = hVar;
        this.e = jVar;
        this.f = str;
        this.g = iBinder;
        this.h = bundle;
    }

    @Override // java.lang.Runnable
    public final void run() {
        IBinder iBinderA = ((MediaBrowserServiceCompat.j) this.e).a();
        MediaBrowserServiceCompat.h hVar = this.i;
        MediaBrowserServiceCompat.b bVar = MediaBrowserServiceCompat.this.h.get(iBinderA);
        String str = this.f;
        if (bVar == null) {
            Log.w("MBServiceCompat", "addSubscription for callback that isn't registered id=" + str);
            return;
        }
        MediaBrowserServiceCompat mediaBrowserServiceCompat = MediaBrowserServiceCompat.this;
        mediaBrowserServiceCompat.getClass();
        HashMap<String, List<qv1<IBinder, Bundle>>> map = bVar.e;
        List<qv1<IBinder, Bundle>> arrayList = map.get(str);
        if (arrayList == null) {
            arrayList = new ArrayList<>();
        }
        Iterator<qv1<IBinder, Bundle>> it = arrayList.iterator();
        while (true) {
            boolean zHasNext = it.hasNext();
            Bundle bundle = this.h;
            IBinder iBinder = this.g;
            if (!zHasNext) {
                arrayList.add(new qv1<>(iBinder, bundle));
                map.put(str, arrayList);
                if (bundle == null) {
                    mediaBrowserServiceCompat.b();
                } else {
                    mediaBrowserServiceCompat.b();
                }
                throw new IllegalStateException("onLoadChildren must call detach() or sendResult() before returning for package=" + bVar.a + " id=" + str);
            }
            qv1<IBinder, Bundle> next = it.next();
            if (iBinder == next.a && oj2.a(bundle, next.b)) {
                return;
            }
        }
    }
}
