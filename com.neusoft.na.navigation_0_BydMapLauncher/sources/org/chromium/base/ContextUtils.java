package org.chromium.base;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import org.chromium.base.annotations.JNINamespace;

/* JADX INFO: loaded from: classes3.dex */
@JNINamespace("base::android")
public class ContextUtils {
    private static final String TAG = "ContextUtils";
    private static Context sApplicationContext;

    private static native void nativeInitNativeSideApplicationContext(Context context);

    private static class Holder {
        private static SharedPreferences sSharedPreferences = ContextUtils.fetchAppSharedPreferences();

        private Holder() {
        }
    }

    public static Context getApplicationContext() {
        return sApplicationContext;
    }

    public static void initApplicationContext(Context context) {
        Context context2 = sApplicationContext;
        if (context2 != null && context2 != context) {
            Log.d(TAG, "Multiple contexts detected, ignoring new application context.");
        } else {
            initJavaSideApplicationContext(context);
        }
    }

    public static void initApplicationContextForNative() {
        Context context = sApplicationContext;
        if (context == null) {
            throw new RuntimeException("Cannot have native global application context be null.");
        }
        nativeInitNativeSideApplicationContext(context);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static SharedPreferences fetchAppSharedPreferences() {
        return PreferenceManager.getDefaultSharedPreferences(sApplicationContext);
    }

    public static SharedPreferences getAppSharedPreferences() {
        return Holder.sSharedPreferences;
    }

    public static void initApplicationContextForTests(Context context) {
        initJavaSideApplicationContext(context);
        SharedPreferences unused = Holder.sSharedPreferences = fetchAppSharedPreferences();
    }

    private static void initJavaSideApplicationContext(Context context) {
        if (context == null) {
            throw new RuntimeException("Global application context cannot be set to null.");
        }
        sApplicationContext = context;
    }
}
