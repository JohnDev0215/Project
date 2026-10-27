package com.android.settingslib.core.instrumentation;

import android.content.ComponentName;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.text.TextUtils;
import android.util.Log;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentSkipListSet;

/* JADX INFO: loaded from: classes3.dex */
public class SharedPreferencesLogger implements SharedPreferences {
    private static final String LOG_TAG = "SharedPreferencesLogger";
    private final Context mContext;
    private final MetricsFeatureProvider mMetricsFeature;
    private final Set<String> mPreferenceKeySet = new ConcurrentSkipListSet();
    private final String mTag;

    public SharedPreferencesLogger(Context context, String tag, MetricsFeatureProvider metricsFeature) {
        this.mContext = context;
        this.mTag = tag;
        this.mMetricsFeature = metricsFeature;
    }

    @Override // android.content.SharedPreferences
    public Map<String, ?> getAll() {
        return null;
    }

    @Override // android.content.SharedPreferences
    public String getString(String key, String defValue) {
        return defValue;
    }

    @Override // android.content.SharedPreferences
    public Set<String> getStringSet(String key, Set<String> defValues) {
        return defValues;
    }

    @Override // android.content.SharedPreferences
    public int getInt(String key, int defValue) {
        return defValue;
    }

    @Override // android.content.SharedPreferences
    public long getLong(String key, long defValue) {
        return defValue;
    }

    @Override // android.content.SharedPreferences
    public float getFloat(String key, float defValue) {
        return defValue;
    }

    @Override // android.content.SharedPreferences
    public boolean getBoolean(String key, boolean defValue) {
        return defValue;
    }

    @Override // android.content.SharedPreferences
    public boolean contains(String key) {
        return false;
    }

    @Override // android.content.SharedPreferences
    public SharedPreferences.Editor edit() {
        return new EditorLogger();
    }

    @Override // android.content.SharedPreferences
    public void registerOnSharedPreferenceChangeListener(SharedPreferences.OnSharedPreferenceChangeListener listener) {
    }

    @Override // android.content.SharedPreferences
    public void unregisterOnSharedPreferenceChangeListener(SharedPreferences.OnSharedPreferenceChangeListener listener) {
    }

    protected void logValue(String key, Object value) {
        logValue(key, value, false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r6v0 */
    /* JADX WARN: Type inference failed for: r7v0 */
    /* JADX WARN: Type inference failed for: r7v1 */
    /* JADX WARN: Type inference failed for: r7v2 */
    /* JADX WARN: Type inference failed for: r7v3 */
    /* JADX WARN: Type inference failed for: r7v4 */
    /* JADX WARN: Type inference failed for: r7v5 */
    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    public void logValue(String str, Object obj, boolean z) {
        ?? BooleanValue;
        int i;
        int iIntValue;
        String strBuildPrefKey = buildPrefKey(this.mTag, str);
        if (!z && !this.mPreferenceKeySet.contains(strBuildPrefKey)) {
            this.mPreferenceKeySet.add(strBuildPrefKey);
            return;
        }
        if (obj instanceof Long) {
            Long l = (Long) obj;
            if (l.longValue() > 2147483647L) {
                iIntValue = Integer.MAX_VALUE;
            } else if (l.longValue() < -2147483648L) {
                iIntValue = Integer.MIN_VALUE;
            } else {
                iIntValue = l.intValue();
            }
            BooleanValue = iIntValue;
        } else if (obj instanceof Integer) {
            BooleanValue = ((Integer) obj).intValue();
        } else if (obj instanceof Boolean) {
            BooleanValue = ((Boolean) obj).booleanValue();
        } else if (obj instanceof Float) {
            float fFloatValue = ((Float) obj).floatValue();
            if (fFloatValue > 2.1474836E9f) {
                i = Integer.MAX_VALUE;
            } else if (fFloatValue < -2.1474836E9f) {
                i = Integer.MIN_VALUE;
            } else {
                i = (int) fFloatValue;
            }
            BooleanValue = i;
        } else if (obj instanceof String) {
            try {
                BooleanValue = Integer.parseInt((String) obj);
            } catch (NumberFormatException e) {
                Log.w(LOG_TAG, "Tried to log unloggable object=" + obj);
                return;
            }
        } else {
            Log.w(LOG_TAG, "Tried to log unloggable object=" + obj);
            return;
        }
        this.mMetricsFeature.action(0, 853, 0, strBuildPrefKey, BooleanValue == true ? 1 : 0);
    }

    void logPackageName(String key, String value) {
        String prefKey = this.mTag + "/" + key;
        this.mMetricsFeature.action(0, 853, 0, prefKey + ":" + value, 0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void safeLogValue(String key, String value) {
        new AsyncPackageCheck().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, key, value);
    }

    public static String buildPrefKey(String tag, String key) {
        return tag + "/" + key;
    }

    private class AsyncPackageCheck extends AsyncTask<String, Void, Void> {
        private AsyncPackageCheck() {
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public Void doInBackground(String... params) {
            String key = params[0];
            String value = params[1];
            PackageManager pm = SharedPreferencesLogger.this.mContext.getPackageManager();
            try {
                ComponentName name = ComponentName.unflattenFromString(value);
                if (value != null) {
                    value = name.getPackageName();
                }
            } catch (Exception e) {
            }
            try {
                pm.getPackageInfo(value, 4194304);
                SharedPreferencesLogger.this.logPackageName(key, value);
                return null;
            } catch (PackageManager.NameNotFoundException e2) {
                SharedPreferencesLogger.this.logValue(key, value, true);
                return null;
            }
        }
    }

    public class EditorLogger implements SharedPreferences.Editor {
        public EditorLogger() {
        }

        @Override // android.content.SharedPreferences.Editor
        public SharedPreferences.Editor putString(String key, String value) {
            SharedPreferencesLogger.this.safeLogValue(key, value);
            return this;
        }

        @Override // android.content.SharedPreferences.Editor
        public SharedPreferences.Editor putStringSet(String key, Set<String> values) {
            SharedPreferencesLogger.this.safeLogValue(key, TextUtils.join(",", values));
            return this;
        }

        @Override // android.content.SharedPreferences.Editor
        public SharedPreferences.Editor putInt(String key, int value) {
            SharedPreferencesLogger.this.logValue(key, Integer.valueOf(value));
            return this;
        }

        @Override // android.content.SharedPreferences.Editor
        public SharedPreferences.Editor putLong(String key, long value) {
            SharedPreferencesLogger.this.logValue(key, Long.valueOf(value));
            return this;
        }

        @Override // android.content.SharedPreferences.Editor
        public SharedPreferences.Editor putFloat(String key, float value) {
            SharedPreferencesLogger.this.logValue(key, Float.valueOf(value));
            return this;
        }

        @Override // android.content.SharedPreferences.Editor
        public SharedPreferences.Editor putBoolean(String key, boolean value) {
            SharedPreferencesLogger.this.logValue(key, Boolean.valueOf(value));
            return this;
        }

        @Override // android.content.SharedPreferences.Editor
        public SharedPreferences.Editor remove(String key) {
            return this;
        }

        @Override // android.content.SharedPreferences.Editor
        public SharedPreferences.Editor clear() {
            return this;
        }

        @Override // android.content.SharedPreferences.Editor
        public boolean commit() {
            return true;
        }

        @Override // android.content.SharedPreferences.Editor
        public void apply() {
        }
    }
}
