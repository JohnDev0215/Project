package com.android.launcher3.uioverrides.plugins;

import android.content.ComponentName;
import android.content.Context;
import androidx.preference.PreferenceDataStore;
import com.android.launcher3.util.MainThreadInitializedObject;
import com.android.systemui.plugins.Plugin;
import com.android.systemui.plugins.PluginListener;
import java.util.Collections;
import java.util.Set;

/* JADX INFO: loaded from: classes4.dex */
public class PluginManagerWrapper {
    public static final MainThreadInitializedObject<PluginManagerWrapper> INSTANCE = new MainThreadInitializedObject<>(new MainThreadInitializedObject.ObjectProvider() { // from class: com.android.launcher3.uioverrides.plugins.-$$Lambda$PluginManagerWrapper$wcrwVKrOKSKOcLjAzNnTL7QDkFk
        @Override // com.android.launcher3.util.MainThreadInitializedObject.ObjectProvider
        public final Object get(Context context) {
            return PluginManagerWrapper.lambda$wcrwVKrOKSKOcLjAzNnTL7QDkFk(context);
        }
    });
    public static final String PLUGIN_CHANGED = "com.android.systemui.action.PLUGIN_CHANGED";
    private static final String PREFIX_PLUGIN_ENABLED = "PLUGIN_ENABLED_";

    public static boolean hasPlugins(Context context) {
        return false;
    }

    public static /* synthetic */ PluginManagerWrapper lambda$wcrwVKrOKSKOcLjAzNnTL7QDkFk(Context context) {
        return new PluginManagerWrapper(context);
    }

    public void addPluginListener(PluginListener<? extends Plugin> listener, Class<?> pluginClass) {
    }

    public void addPluginListener(PluginListener<? extends Plugin> listener, Class<?> pluginClass, boolean allowMultiple) {
    }

    public void removePluginListener(PluginListener<? extends Plugin> listener) {
    }

    private PluginManagerWrapper(Context c) {
    }

    public Set<String> getPluginActions() {
        return Collections.emptySet();
    }

    public PreferenceDataStore getPluginEnabler() {
        return new PreferenceDataStore() { // from class: com.android.launcher3.uioverrides.plugins.PluginManagerWrapper.1
        };
    }

    public static String pluginEnabledKey(ComponentName cn) {
        return PREFIX_PLUGIN_ENABLED + cn.flattenToString();
    }
}
