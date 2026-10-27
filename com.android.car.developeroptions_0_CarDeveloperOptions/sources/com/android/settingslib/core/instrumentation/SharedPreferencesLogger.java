package com.android.settingslib.core.instrumentation;

import android.content.ComponentName;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.text.TextUtils;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentSkipListSet;

/* JADX INFO: loaded from: classes.dex */
public class SharedPreferencesLogger implements SharedPreferences {
    private final Context mContext;
    private final MetricsFeatureProvider mMetricsFeature;
    private final Set<String> mPreferenceKeySet = new ConcurrentSkipListSet();
    private final String mTag;

    @Override // android.content.SharedPreferences
    public boolean contains(String str) {
        return false;
    }

    @Override // android.content.SharedPreferences
    public Map<String, ?> getAll() {
        return null;
    }

    @Override // android.content.SharedPreferences
    public boolean getBoolean(String str, boolean z) {
        return z;
    }

    @Override // android.content.SharedPreferences
    public float getFloat(String str, float f) {
        return f;
    }

    @Override // android.content.SharedPreferences
    public int getInt(String str, int i) {
        return i;
    }

    @Override // android.content.SharedPreferences
    public long getLong(String str, long j) {
        return j;
    }

    @Override // android.content.SharedPreferences
    public String getString(String str, String str2) {
        return str2;
    }

    @Override // android.content.SharedPreferences
    public Set<String> getStringSet(String str, Set<String> set) {
        return set;
    }

    @Override // android.content.SharedPreferences
    public void registerOnSharedPreferenceChangeListener(SharedPreferences.OnSharedPreferenceChangeListener onSharedPreferenceChangeListener) {
    }

    @Override // android.content.SharedPreferences
    public void unregisterOnSharedPreferenceChangeListener(SharedPreferences.OnSharedPreferenceChangeListener onSharedPreferenceChangeListener) {
    }

    public SharedPreferencesLogger(Context context, String str, MetricsFeatureProvider metricsFeatureProvider) {
        this.mContext = context;
        this.mTag = str;
        this.mMetricsFeature = metricsFeatureProvider;
    }

    @Override // android.content.SharedPreferences
    public SharedPreferences.Editor edit() {
        return new EditorLogger();
    }

    protected void logValue(String str, Object obj) {
        logValue(str, obj, false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:12:0x002c  */
    /* JADX WARN: Type inference failed for: r1v0, types: [com.android.settingslib.core.instrumentation.MetricsFeatureProvider] */
    /* JADX WARN: Type inference failed for: r6v0 */
    /* JADX WARN: Type inference failed for: r6v1, types: [int] */
    /* JADX WARN: Type inference failed for: r6v2 */
    /* JADX WARN: Type inference failed for: r8v11 */
    /* JADX WARN: Type inference failed for: r8v17 */
    /* JADX WARN: Type inference failed for: r8v18 */
    /* JADX WARN: Type inference failed for: r8v19 */
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
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void logValue(java.lang.String r8, java.lang.Object r9, boolean r10) {
        /*
            r7 = this;
            java.lang.String r0 = r7.mTag
            java.lang.String r5 = buildPrefKey(r0, r8)
            if (r10 != 0) goto L16
            java.util.Set<java.lang.String> r8 = r7.mPreferenceKeySet
            boolean r8 = r8.contains(r5)
            if (r8 != 0) goto L16
            java.util.Set<java.lang.String> r7 = r7.mPreferenceKeySet
            r7.add(r5)
            return
        L16:
            boolean r8 = r9 instanceof java.lang.Long
            r10 = -2147483648(0xffffffff80000000, float:-0.0)
            r0 = 2147483647(0x7fffffff, float:NaN)
            if (r8 == 0) goto L40
            java.lang.Long r9 = (java.lang.Long) r9
            long r1 = r9.longValue()
            r3 = 2147483647(0x7fffffff, double:1.060997895E-314)
            int r8 = (r1 > r3 ? 1 : (r1 == r3 ? 0 : -1))
            if (r8 <= 0) goto L2e
        L2c:
            r10 = r0
            goto L3e
        L2e:
            long r0 = r9.longValue()
            r2 = -2147483648(0xffffffff80000000, double:NaN)
            int r8 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r8 >= 0) goto L3a
            goto L3e
        L3a:
            int r10 = r9.intValue()
        L3e:
            r6 = r10
            goto L81
        L40:
            boolean r8 = r9 instanceof java.lang.Integer
            if (r8 == 0) goto L4c
            java.lang.Integer r9 = (java.lang.Integer) r9
            int r8 = r9.intValue()
        L4a:
            r6 = r8
            goto L81
        L4c:
            boolean r8 = r9 instanceof java.lang.Boolean
            if (r8 == 0) goto L57
            java.lang.Boolean r9 = (java.lang.Boolean) r9
            boolean r8 = r9.booleanValue()
            goto L4a
        L57:
            boolean r8 = r9 instanceof java.lang.Float
            if (r8 == 0) goto L71
            java.lang.Float r9 = (java.lang.Float) r9
            float r8 = r9.floatValue()
            r9 = 1325400064(0x4f000000, float:2.1474836E9)
            int r9 = (r8 > r9 ? 1 : (r8 == r9 ? 0 : -1))
            if (r9 <= 0) goto L68
            goto L2c
        L68:
            r9 = -822083584(0xffffffffcf000000, float:-2.1474836E9)
            int r9 = (r8 > r9 ? 1 : (r8 == r9 ? 0 : -1))
            if (r9 >= 0) goto L6f
            goto L3e
        L6f:
            int r10 = (int) r8
            goto L3e
        L71:
            boolean r8 = r9 instanceof java.lang.String
            java.lang.String r10 = "Tried to log unloggable object="
            java.lang.String r0 = "SharedPreferencesLogger"
            if (r8 == 0) goto L9e
            r8 = r9
            java.lang.String r8 = (java.lang.String) r8     // Catch: java.lang.NumberFormatException -> L8b
            int r8 = java.lang.Integer.parseInt(r8)     // Catch: java.lang.NumberFormatException -> L8b
            goto L4a
        L81:
            com.android.settingslib.core.instrumentation.MetricsFeatureProvider r1 = r7.mMetricsFeature
            r2 = 0
            r3 = 853(0x355, float:1.195E-42)
            r4 = 0
            r1.action(r2, r3, r4, r5, r6)
            return
        L8b:
            java.lang.StringBuilder r7 = new java.lang.StringBuilder
            r7.<init>()
            r7.append(r10)
            r7.append(r9)
            java.lang.String r7 = r7.toString()
            android.util.Log.w(r0, r7)
            return
        L9e:
            java.lang.StringBuilder r7 = new java.lang.StringBuilder
            r7.<init>()
            r7.append(r10)
            r7.append(r9)
            java.lang.String r7 = r7.toString()
            android.util.Log.w(r0, r7)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.settingslib.core.instrumentation.SharedPreferencesLogger.logValue(java.lang.String, java.lang.Object, boolean):void");
    }

    void logPackageName(String str, String str2) {
        String str3 = this.mTag + "/" + str;
        this.mMetricsFeature.action(0, 853, 0, str3 + ":" + str2, 0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void safeLogValue(String str, String str2) {
        new AsyncPackageCheck().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, str, str2);
    }

    public static String buildPrefKey(String str, String str2) {
        return str + "/" + str2;
    }

    private class AsyncPackageCheck extends AsyncTask<String, Void, Void> {
        private AsyncPackageCheck() {
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public Void doInBackground(String... strArr) {
            String str = strArr[0];
            String packageName = strArr[1];
            PackageManager packageManager = SharedPreferencesLogger.this.mContext.getPackageManager();
            try {
                ComponentName componentNameUnflattenFromString = ComponentName.unflattenFromString(packageName);
                if (packageName != null) {
                    packageName = componentNameUnflattenFromString.getPackageName();
                }
            } catch (Exception unused) {
            }
            try {
                packageManager.getPackageInfo(packageName, 4194304);
                SharedPreferencesLogger.this.logPackageName(str, packageName);
                return null;
            } catch (PackageManager.NameNotFoundException unused2) {
                SharedPreferencesLogger.this.logValue(str, packageName, true);
                return null;
            }
        }
    }

    public class EditorLogger implements SharedPreferences.Editor {
        @Override // android.content.SharedPreferences.Editor
        public void apply() {
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
        public SharedPreferences.Editor remove(String str) {
            return this;
        }

        public EditorLogger() {
        }

        @Override // android.content.SharedPreferences.Editor
        public SharedPreferences.Editor putString(String str, String str2) {
            SharedPreferencesLogger.this.safeLogValue(str, str2);
            return this;
        }

        @Override // android.content.SharedPreferences.Editor
        public SharedPreferences.Editor putStringSet(String str, Set<String> set) {
            SharedPreferencesLogger.this.safeLogValue(str, TextUtils.join(",", set));
            return this;
        }

        @Override // android.content.SharedPreferences.Editor
        public SharedPreferences.Editor putInt(String str, int i) {
            SharedPreferencesLogger.this.logValue(str, Integer.valueOf(i));
            return this;
        }

        @Override // android.content.SharedPreferences.Editor
        public SharedPreferences.Editor putLong(String str, long j) {
            SharedPreferencesLogger.this.logValue(str, Long.valueOf(j));
            return this;
        }

        @Override // android.content.SharedPreferences.Editor
        public SharedPreferences.Editor putFloat(String str, float f) {
            SharedPreferencesLogger.this.logValue(str, Float.valueOf(f));
            return this;
        }

        @Override // android.content.SharedPreferences.Editor
        public SharedPreferences.Editor putBoolean(String str, boolean z) {
            SharedPreferencesLogger.this.logValue(str, Boolean.valueOf(z));
            return this;
        }
    }
}
