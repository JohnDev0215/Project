package com.amazon.weblab.mobile.metrics;

import android.content.Context;

/* JADX INFO: loaded from: classes5.dex */
public class ApplicationContextHolder {
    private static Context sApplicationContext;

    public static Context getApplicationContext() throws IllegalStateException {
        Context context = sApplicationContext;
        if (context != null) {
            return context;
        }
        throw new IllegalStateException("application context has not been set");
    }

    public static void setApplicationContext(Context context) throws IllegalArgumentException {
        if (context == null) {
            throw new IllegalArgumentException("application context cannot be null");
        }
        sApplicationContext = context.getApplicationContext();
    }
}
