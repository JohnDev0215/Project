package dagger.hilt.android.internal.managers;

import android.content.ComponentCallbacks2;
import android.content.Context;

/* JADX INFO: compiled from: BroadcastReceiverComponentManager.java */
/* JADX INFO: loaded from: classes.dex */
public final class e {
    public static Object a(Context context) {
        ComponentCallbacks2 componentCallbacks2A = k4.a.a(context.getApplicationContext());
        o4.d.a(componentCallbacks2A instanceof o4.b, "Hilt BroadcastReceiver must be attached to an @AndroidEntryPoint Application. Found: %s", componentCallbacks2A.getClass());
        return ((o4.b) componentCallbacks2A).f();
    }
}
