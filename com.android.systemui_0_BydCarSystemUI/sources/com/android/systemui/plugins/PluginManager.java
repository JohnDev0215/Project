package com.android.systemui.plugins;

import android.text.TextUtils;
import com.android.systemui.plugins.annotations.ProvidesInterface;
import com.android.systemui.util.SystemUIException;

/* JADX INFO: loaded from: classes.dex */
public interface PluginManager {
    public static final String NOTIFICATION_CHANNEL_ID = "ALR";
    public static final String PLUGIN_CHANGED = "com.android.systemui.action.PLUGIN_CHANGED";

    <T extends Plugin> void addPluginListener(PluginListener<T> listener, Class<?> cls);

    <T extends Plugin> void addPluginListener(PluginListener<T> listener, Class<?> cls, boolean allowMultiple);

    <T extends Plugin> void addPluginListener(String action, PluginListener<T> listener, Class<?> cls);

    <T extends Plugin> void addPluginListener(String action, PluginListener<T> listener, Class cls, boolean allowMultiple);

    <T> boolean dependsOn(Plugin p, Class<T> cls);

    <T extends Plugin> T getOneShotPlugin(Class<T> cls);

    <T extends Plugin> T getOneShotPlugin(String action, Class<?> cls);

    void removePluginListener(PluginListener<?> listener);

    static <P> String getAction(Class<P> cls) {
        ProvidesInterface providesInterface = (ProvidesInterface) cls.getDeclaredAnnotation(ProvidesInterface.class);
        if (providesInterface == null) {
            throw new SystemUIException(cls + " doesn't provide an interface");
        }
        if (TextUtils.isEmpty(providesInterface.action())) {
            throw new SystemUIException(cls + " doesn't provide an action");
        }
        return providesInterface.action();
    }
}
