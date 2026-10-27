package com.android.car.ui.pluginsupport;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import spi.d9;
import spi.w8;
import spi.x8;
import spi.y8;

/* JADX INFO: loaded from: classes2.dex */
@RequiresApi(28)
final class OemApiUtil {
    private static final String TAG = "carui";

    private OemApiUtil() {
    }

    private static boolean classExists(@Nullable String str) {
        if (str == null) {
            return false;
        }
        try {
            Class.forName(str);
            return true;
        } catch (ClassNotFoundException unused) {
            return false;
        }
    }

    @SuppressLint({"PrivateApi"})
    public static w8 getPluginFactory(Context context, String str) {
        Object objNewInstance;
        PluginVersionProviderAdapterV1 pluginVersionProviderAdapterV1;
        try {
            objNewInstance = Class.forName("com.android.car.ui.plugin.PluginVersionProviderImpl").getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
        } catch (ClassNotFoundException e) {
            Log.i(TAG, "PluginVersionProviderImpl not found.", e);
            objNewInstance = null;
        } catch (ReflectiveOperationException e2) {
            Log.e(TAG, "PluginVersionProviderImpl could not be instantiated!", e2);
            objNewInstance = null;
        }
        if (objNewInstance instanceof d9) {
            pluginVersionProviderAdapterV1 = new PluginVersionProviderAdapterV1((d9) objNewInstance);
        } else {
            Log.e(TAG, "PluginVersionProviderImpl was not instanceof any known versions of PluginVersionProviderOEMV#.");
            pluginVersionProviderAdapterV1 = null;
        }
        if (pluginVersionProviderAdapterV1 == null) {
            return null;
        }
        Object pluginFactory = pluginVersionProviderAdapterV1.getPluginFactory(1, context, str);
        if (pluginFactory instanceof x8) {
            return new PluginFactoryAdapterV1((x8) pluginFactory);
        }
        if (classExists("com.android.car.ui.plugin.oemapis.PluginFactoryOEMV2") && (pluginFactory instanceof y8)) {
            return new PluginFactoryAdapterV2((y8) pluginFactory);
        }
        Log.e(TAG, "PluginVersionProvider found, but did not provide a factory implementing any known interfaces!");
        return null;
    }
}
