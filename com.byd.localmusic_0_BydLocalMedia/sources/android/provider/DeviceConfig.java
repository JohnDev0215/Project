package android.provider;

import android.Manifest;
import android.annotation.SystemApi;
import android.app.ActivityThread;
import android.content.Context;
import android.database.ContentObserver;
import android.net.Uri;
import android.provider.Settings;
import android.util.ArrayMap;
import android.util.Log;
import android.util.Pair;
import com.android.internal.util.Preconditions;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Executor;

/* JADX INFO: loaded from: classes3.dex */
@SystemApi
public final class DeviceConfig {

    @SystemApi
    public static final String NAMESPACE_ACTIVITY_MANAGER = "activity_manager";

    @SystemApi
    public static final String NAMESPACE_ACTIVITY_MANAGER_NATIVE_BOOT = "activity_manager_native_boot";
    public static final String NAMESPACE_ANDROID = "android";

    @SystemApi
    public static final String NAMESPACE_APP_COMPAT = "app_compat";

    @SystemApi
    public static final String NAMESPACE_ATTENTION_MANAGER_SERVICE = "attention_manager_service";

    @SystemApi
    public static final String NAMESPACE_AUTOFILL = "autofill";

    @SystemApi
    public static final String NAMESPACE_BIOMETRICS = "biometrics";

    @SystemApi
    public static final String NAMESPACE_BLOBSTORE = "blobstore";
    public static final String NAMESPACE_CONFIGURATION = "configuration";

    @SystemApi
    public static final String NAMESPACE_CONNECTIVITY = "connectivity";
    public static final String NAMESPACE_CONNECTIVITY_THERMAL_POWER_MANAGER = "connectivity_thermal_power_manager";
    public static final String NAMESPACE_CONTACTS_PROVIDER = "contacts_provider";

    @SystemApi
    public static final String NAMESPACE_CONTENT_CAPTURE = "content_capture";

    @SystemApi
    @Deprecated
    public static final String NAMESPACE_DEX_BOOT = "dex_boot";

    @SystemApi
    public static final String NAMESPACE_DISPLAY_MANAGER = "display_manager";

    @SystemApi
    public static final String NAMESPACE_GAME_DRIVER = "game_driver";

    @SystemApi
    public static final String NAMESPACE_INPUT_NATIVE_BOOT = "input_native_boot";

    @SystemApi
    public static final String NAMESPACE_INTELLIGENCE_ATTENTION = "intelligence_attention";
    public static final String NAMESPACE_INTELLIGENCE_CONTENT_SUGGESTIONS = "intelligence_content_suggestions";

    @SystemApi
    public static final String NAMESPACE_MEDIA_NATIVE = "media_native";

    @SystemApi
    public static final String NAMESPACE_NETD_NATIVE = "netd_native";

    @SystemApi
    public static final String NAMESPACE_PACKAGE_MANAGER_SERVICE = "package_manager_service";

    @SystemApi
    public static final String NAMESPACE_PERMISSIONS = "permissions";

    @SystemApi
    public static final String NAMESPACE_PRIVACY = "privacy";

    @SystemApi
    public static final String NAMESPACE_ROLLBACK = "rollback";

    @SystemApi
    public static final String NAMESPACE_ROLLBACK_BOOT = "rollback_boot";

    @SystemApi
    public static final String NAMESPACE_RUNTIME_NATIVE = "runtime_native";

    @SystemApi
    public static final String NAMESPACE_RUNTIME_NATIVE_BOOT = "runtime_native_boot";

    @SystemApi
    public static final String NAMESPACE_SCHEDULER = "scheduler";
    public static final String NAMESPACE_SETTINGS_STATS = "settings_stats";
    public static final String NAMESPACE_SETTINGS_UI = "settings_ui";

    @SystemApi
    @Deprecated
    public static final String NAMESPACE_STORAGE = "storage";

    @SystemApi
    public static final String NAMESPACE_STORAGE_NATIVE_BOOT = "storage_native_boot";

    @SystemApi
    public static final String NAMESPACE_SYSTEMUI = "systemui";

    @SystemApi
    public static final String NAMESPACE_TELEPHONY = "telephony";
    public static final String NAMESPACE_WIDGET = "widget";
    public static final String NAMESPACE_WINDOW_MANAGER = "window_manager";

    @SystemApi
    public static final String NAMESPACE_WINDOW_MANAGER_NATIVE_BOOT = "window_manager_native_boot";
    private static final String TAG = "DeviceConfig";
    public static final Uri CONTENT_URI = Uri.parse("content://settings/config");

    @SystemApi
    public static final String NAMESPACE_TEXTCLASSIFIER = "textclassifier";

    @SystemApi
    public static final String NAMESPACE_RUNTIME = "runtime";
    private static final List<String> PUBLIC_NAMESPACES = Arrays.asList(NAMESPACE_TEXTCLASSIFIER, NAMESPACE_RUNTIME);
    private static final Object sLock = new Object();
    private static ArrayMap<OnPropertiesChangedListener, Pair<String, Executor>> sListeners = new ArrayMap<>();
    private static Map<String, Pair<ContentObserver, Integer>> sNamespaces = new HashMap();

    @SystemApi
    public static class BadConfigException extends Exception {
    }

    @SystemApi
    public interface OnPropertiesChangedListener {
        void onPropertiesChanged(Properties properties);
    }

    private DeviceConfig() {
    }

    @SystemApi
    public static String getProperty(String str, String str2) {
        return getProperties(str, str2).getString(str2, null);
    }

    @SystemApi
    public static Properties getProperties(String str, String... strArr) {
        return new Properties(str, Settings.Config.getStrings(ActivityThread.currentApplication().getContentResolver(), str, Arrays.asList(strArr)));
    }

    @SystemApi
    public static String getString(String str, String str2, String str3) {
        String property = getProperty(str, str2);
        return property != null ? property : str3;
    }

    @SystemApi
    public static boolean getBoolean(String str, String str2, boolean z) {
        String property = getProperty(str, str2);
        return property != null ? Boolean.parseBoolean(property) : z;
    }

    @SystemApi
    public static int getInt(String str, String str2, int i) {
        String property = getProperty(str, str2);
        if (property == null) {
            return i;
        }
        try {
            return Integer.parseInt(property);
        } catch (NumberFormatException unused) {
            Log.e(TAG, "Parsing integer failed for " + str + SettingsStringUtil.DELIMITER + str2);
            return i;
        }
    }

    @SystemApi
    public static long getLong(String str, String str2, long j) {
        String property = getProperty(str, str2);
        if (property == null) {
            return j;
        }
        try {
            return Long.parseLong(property);
        } catch (NumberFormatException unused) {
            Log.e(TAG, "Parsing long failed for " + str + SettingsStringUtil.DELIMITER + str2);
            return j;
        }
    }

    @SystemApi
    public static float getFloat(String str, String str2, float f) {
        String property = getProperty(str, str2);
        if (property == null) {
            return f;
        }
        try {
            return Float.parseFloat(property);
        } catch (NumberFormatException unused) {
            Log.e(TAG, "Parsing float failed for " + str + SettingsStringUtil.DELIMITER + str2);
            return f;
        }
    }

    @SystemApi
    public static boolean setProperty(String str, String str2, String str3, boolean z) {
        return Settings.Config.putString(ActivityThread.currentApplication().getContentResolver(), str, str2, str3, z);
    }

    @SystemApi
    public static boolean setProperties(Properties properties) throws BadConfigException {
        return Settings.Config.setStrings(ActivityThread.currentApplication().getContentResolver(), properties.getNamespace(), properties.mMap);
    }

    @SystemApi
    public static void resetToDefaults(int i, String str) {
        Settings.Config.resetToDefaults(ActivityThread.currentApplication().getContentResolver(), i, str);
    }

    @SystemApi
    public static void addOnPropertiesChangedListener(String str, Executor executor, OnPropertiesChangedListener onPropertiesChangedListener) {
        enforceReadPermission(ActivityThread.currentApplication().getApplicationContext(), str);
        synchronized (sLock) {
            Pair<String, Executor> pair = sListeners.get(onPropertiesChangedListener);
            if (pair == null) {
                sListeners.put(onPropertiesChangedListener, new Pair<>(str, executor));
                incrementNamespace(str);
            } else if (str.equals(pair.first)) {
                sListeners.put(onPropertiesChangedListener, new Pair<>(str, executor));
            } else {
                decrementNamespace(sListeners.get(onPropertiesChangedListener).first);
                sListeners.put(onPropertiesChangedListener, new Pair<>(str, executor));
                incrementNamespace(str);
            }
        }
    }

    @SystemApi
    public static void removeOnPropertiesChangedListener(OnPropertiesChangedListener onPropertiesChangedListener) {
        Preconditions.checkNotNull(onPropertiesChangedListener);
        synchronized (sLock) {
            if (sListeners.containsKey(onPropertiesChangedListener)) {
                decrementNamespace(sListeners.get(onPropertiesChangedListener).first);
                sListeners.remove(onPropertiesChangedListener);
            }
        }
    }

    private static Uri createNamespaceUri(String str) {
        Preconditions.checkNotNull(str);
        return CONTENT_URI.buildUpon().appendPath(str).build();
    }

    private static void incrementNamespace(String str) {
        Preconditions.checkNotNull(str);
        Pair<ContentObserver, Integer> pair = sNamespaces.get(str);
        if (pair != null) {
            sNamespaces.put(str, new Pair<>(pair.first, Integer.valueOf(pair.second.intValue() + 1)));
            return;
        }
        ContentObserver contentObserver = new ContentObserver(null) { // from class: android.provider.DeviceConfig.1
            @Override // android.database.ContentObserver
            public void onChange(boolean z, Uri uri) {
                if (uri != null) {
                    DeviceConfig.handleChange(uri);
                }
            }
        };
        ActivityThread.currentApplication().getContentResolver().registerContentObserver(createNamespaceUri(str), true, contentObserver);
        sNamespaces.put(str, new Pair<>(contentObserver, 1));
    }

    private static void decrementNamespace(String str) {
        Preconditions.checkNotNull(str);
        Pair<ContentObserver, Integer> pair = sNamespaces.get(str);
        if (pair == null) {
            return;
        }
        if (pair.second.intValue() > 1) {
            sNamespaces.put(str, new Pair<>(pair.first, Integer.valueOf(pair.second.intValue() - 1)));
        } else {
            ActivityThread.currentApplication().getContentResolver().unregisterContentObserver(pair.first);
            sNamespaces.remove(str);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void handleChange(Uri uri) {
        Preconditions.checkNotNull(uri);
        List<String> pathSegments = uri.getPathSegments();
        String str = pathSegments.get(1);
        Properties.Builder builder = new Properties.Builder(str);
        try {
            Properties properties = getProperties(str, new String[0]);
            for (int i = 2; i < pathSegments.size(); i++) {
                String str2 = pathSegments.get(i);
                builder.setString(str2, properties.getString(str2, null));
            }
            final Properties propertiesBuild = builder.build();
            synchronized (sLock) {
                for (int i2 = 0; i2 < sListeners.size(); i2++) {
                    if (str.equals(sListeners.valueAt(i2).first)) {
                        final OnPropertiesChangedListener onPropertiesChangedListenerKeyAt = sListeners.keyAt(i2);
                        sListeners.valueAt(i2).second.execute(new Runnable() { // from class: android.provider.-$$Lambda$DeviceConfig$6U9gBH6h5Oab2DB_e83az4n_WEo
                            @Override // java.lang.Runnable
                            public final void run() {
                                onPropertiesChangedListenerKeyAt.onPropertiesChanged(propertiesBuild);
                            }
                        });
                    }
                }
            }
        } catch (SecurityException unused) {
            Log.e(TAG, "OnPropertyChangedListener update failed: permission violation.");
        }
    }

    public static void enforceReadPermission(Context context, String str) {
        if (context.checkCallingOrSelfPermission(Manifest.permission.READ_DEVICE_CONFIG) != 0 && !PUBLIC_NAMESPACES.contains(str)) {
            throw new SecurityException("Permission denial: reading from settings requires:android.permission.READ_DEVICE_CONFIG");
        }
    }

    public static List<String> getPublicNamespaces() {
        return PUBLIC_NAMESPACES;
    }

    @SystemApi
    public static class Properties {
        private Set<String> mKeyset;
        private final HashMap<String, String> mMap;
        private final String mNamespace;

        public Properties(String str, Map<String, String> map) {
            Preconditions.checkNotNull(str);
            this.mNamespace = str;
            HashMap<String, String> map2 = new HashMap<>();
            this.mMap = map2;
            if (map != null) {
                map2.putAll(map);
            }
        }

        public String getNamespace() {
            return this.mNamespace;
        }

        public Set<String> getKeyset() {
            if (this.mKeyset == null) {
                this.mKeyset = Collections.unmodifiableSet(this.mMap.keySet());
            }
            return this.mKeyset;
        }

        public String getString(String str, String str2) {
            Preconditions.checkNotNull(str);
            String str3 = this.mMap.get(str);
            return str3 != null ? str3 : str2;
        }

        public boolean getBoolean(String str, boolean z) {
            Preconditions.checkNotNull(str);
            String str2 = this.mMap.get(str);
            return str2 != null ? Boolean.parseBoolean(str2) : z;
        }

        public int getInt(String str, int i) {
            Preconditions.checkNotNull(str);
            String str2 = this.mMap.get(str);
            if (str2 == null) {
                return i;
            }
            try {
                return Integer.parseInt(str2);
            } catch (NumberFormatException unused) {
                Log.e(DeviceConfig.TAG, "Parsing int failed for " + str);
                return i;
            }
        }

        public long getLong(String str, long j) {
            Preconditions.checkNotNull(str);
            String str2 = this.mMap.get(str);
            if (str2 == null) {
                return j;
            }
            try {
                return Long.parseLong(str2);
            } catch (NumberFormatException unused) {
                Log.e(DeviceConfig.TAG, "Parsing long failed for " + str);
                return j;
            }
        }

        public float getFloat(String str, float f) {
            Preconditions.checkNotNull(str);
            String str2 = this.mMap.get(str);
            if (str2 == null) {
                return f;
            }
            try {
                return Float.parseFloat(str2);
            } catch (NumberFormatException unused) {
                Log.e(DeviceConfig.TAG, "Parsing float failed for " + str);
                return f;
            }
        }

        public static final class Builder {
            private final Map<String, String> mKeyValues = new HashMap();
            private final String mNamespace;

            public Builder(String str) {
                this.mNamespace = str;
            }

            public Builder setString(String str, String str2) {
                this.mKeyValues.put(str, str2);
                return this;
            }

            public Builder setBoolean(String str, boolean z) {
                this.mKeyValues.put(str, Boolean.toString(z));
                return this;
            }

            public Builder setInt(String str, int i) {
                this.mKeyValues.put(str, Integer.toString(i));
                return this;
            }

            public Builder setLong(String str, long j) {
                this.mKeyValues.put(str, Long.toString(j));
                return this;
            }

            public Builder setFloat(String str, float f) {
                this.mKeyValues.put(str, Float.toString(f));
                return this;
            }

            public Properties build() {
                return new Properties(this.mNamespace, this.mKeyValues);
            }
        }
    }
}
