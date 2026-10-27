package spi;

import android.content.ComponentCallbacks2;
import android.content.Context;

/* JADX INFO: loaded from: classes2.dex */
public final class f2 {
    private f2() {
    }

    public static Object a(Context context) {
        ComponentCallbacks2 componentCallbacks2A = o4.a(context.getApplicationContext());
        boolean z = componentCallbacks2A instanceof xc;
        Object[] objArr = {componentCallbacks2A.getClass()};
        if (z) {
            return ((xc) componentCallbacks2A).a();
        }
        throw new IllegalArgumentException(String.format("Hilt BroadcastReceiver must be attached to an @AndroidEntryPoint Application. Found: %s", objArr));
    }
}
