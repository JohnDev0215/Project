package com.amazon.alexa.mobilytics.lifecycle;

import android.app.Application;
import android.content.Context;
import com.amazon.alexa.mobilytics.util.Log;
import java.util.concurrent.atomic.AtomicBoolean;

/* JADX INFO: loaded from: classes2.dex */
final class LifecycleDispatcher {
    private static final String TAG = Log.tag(LifecycleDispatcher.class);
    private static final AtomicBoolean INITIALIZED = new AtomicBoolean(false);
    private static final LifecycleCallbackListener callbackListener = new LifecycleCallbackListener();

    private LifecycleDispatcher() {
    }

    public static void initialize(Context context) {
        if (INITIALIZED.getAndSet(true)) {
            return;
        }
        Log.i(TAG, "Initializing ActivityLifecycleCallback listener.");
        ((Application) context.getApplicationContext()).registerActivityLifecycleCallbacks(callbackListener);
    }
}
