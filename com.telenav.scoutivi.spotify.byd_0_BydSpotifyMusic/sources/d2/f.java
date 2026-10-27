package d2;

import android.content.Context;
import android.util.Log;
import d2.c;

/* JADX INFO: compiled from: DefaultConnectivityMonitorFactory.java */
/* JADX INFO: loaded from: classes.dex */
public class f implements d {
    @Override // d2.d
    public c a(Context context, c.a aVar) {
        boolean z6 = x.a.a(context, "android.permission.ACCESS_NETWORK_STATE") == 0;
        if (Log.isLoggable("ConnectivityMonitor", 3)) {
            Log.d("ConnectivityMonitor", z6 ? "ACCESS_NETWORK_STATE permission granted, registering connectivity monitor" : "ACCESS_NETWORK_STATE permission missing, cannot register connectivity monitor");
        }
        return z6 ? new e(context, aVar) : new j();
    }
}
