package com.bumptech.glide.o;

import android.content.Context;
import android.util.Log;
import com.bumptech.glide.o.c;

/* JADX INFO: loaded from: classes.dex */
public class f implements d {
    @Override // com.bumptech.glide.o.d
    public c a(Context context, c.a aVar) {
        boolean z = androidx.core.content.a.a(context, "android.permission.ACCESS_NETWORK_STATE") == 0;
        if (Log.isLoggable("ConnectivityMonitor", 3)) {
            Log.d("ConnectivityMonitor", z ? "ACCESS_NETWORK_STATE permission granted, registering connectivity monitor" : "ACCESS_NETWORK_STATE permission missing, cannot register connectivity monitor");
        }
        return z ? new e(context, aVar) : new j();
    }
}
