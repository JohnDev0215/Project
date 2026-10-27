package com.android.inputmethod.compat;

import android.net.ConnectivityManager;
import java.lang.reflect.Method;

/* JADX INFO: loaded from: classes2.dex */
public final class ConnectivityManagerCompatUtils {
    private static final Method METHOD_isActiveNetworkMetered = CompatUtils.getMethod(ConnectivityManager.class, "isActiveNetworkMetered", new Class[0]);

    public static boolean isActiveNetworkMetered(final ConnectivityManager manager) {
        return ((Boolean) CompatUtils.invoke(manager, Boolean.valueOf(manager.getActiveNetworkInfo().getType() == 0), METHOD_isActiveNetworkMetered, new Object[0])).booleanValue();
    }
}
