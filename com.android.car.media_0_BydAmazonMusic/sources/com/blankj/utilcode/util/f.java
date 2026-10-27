package com.blankj.utilcode.util;

import android.app.Activity;
import android.os.Build;
import android.text.TextUtils;
import com.blankj.utilcode.util.Utils;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/* JADX INFO: compiled from: UtilsBridge.java */
/* JADX INFO: loaded from: classes2.dex */
public final class f {

    /* JADX INFO: compiled from: UtilsBridge.java */
    public static final class a {
        public String a;
        public LinkedHashMap<String, String> b = new LinkedHashMap<>();
        public LinkedHashMap<String, String> c = new LinkedHashMap<>();

        public a(String str) {
            this.a = str;
        }

        public final void a(Map<String, String> map) {
            LinkedHashMap<String, String> linkedHashMap = this.c;
            if (map == null || map.isEmpty()) {
                return;
            }
            for (Map.Entry<String, String> entry : map.entrySet()) {
                b(linkedHashMap, entry.getKey(), entry.getValue());
            }
        }

        public final void b(Map<String, String> map, String str, String str2) {
            if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
                return;
            }
            int length = 19 - str.length();
            if (length > 0) {
                StringBuilder sbK = spi.c.k(str);
                sbK.append("                   ".substring(0, length));
                str = sbK.toString();
            }
            map.put(str, str2);
        }

        public final String c() {
            StringBuilder sb = new StringBuilder();
            for (Map.Entry<String, String> entry : this.c.entrySet()) {
                sb.append(entry.getKey());
                sb.append(": ");
                sb.append(entry.getValue());
                sb.append("\n");
            }
            return sb.toString();
        }

        public final String toString() {
            StringBuilder sb = new StringBuilder();
            StringBuilder sbK = spi.c.k("************* ");
            sbK.append(this.a);
            sbK.append(" Head ****************\n");
            String string = sbK.toString();
            sb.append(string);
            for (Map.Entry<String, String> entry : this.b.entrySet()) {
                sb.append(entry.getKey());
                sb.append(": ");
                sb.append(entry.getValue());
                sb.append("\n");
            }
            sb.append("Rom Info           : ");
            sb.append(RomUtils.getRomInfo());
            sb.append("\n");
            sb.append("Device Manufacturer: ");
            sb.append(Build.MANUFACTURER);
            sb.append("\n");
            sb.append("Device Model       : ");
            sb.append(Build.MODEL);
            sb.append("\n");
            sb.append("Android Version    : ");
            sb.append(Build.VERSION.RELEASE);
            sb.append("\n");
            sb.append("Android SDK        : ");
            sb.append(Build.VERSION.SDK_INT);
            sb.append("\n");
            sb.append("App VersionName    : ");
            sb.append(AppUtils.getAppVersionName());
            sb.append("\n");
            sb.append("App VersionCode    : ");
            sb.append(AppUtils.getAppVersionCode());
            sb.append("\n");
            sb.append(c());
            sb.append(string);
            sb.append("\n");
            return sb.toString();
        }
    }

    public static void a(Utils.ActivityLifecycleCallbacks activityLifecycleCallbacks) {
        e eVar = e.g;
        Objects.requireNonNull(eVar);
        Activity activity = e.h;
        if (activity == null || activityLifecycleCallbacks == null) {
            return;
        }
        ThreadUtils.runOnUiThread(new c(eVar, activity, activityLifecycleCallbacks));
    }

    public static void addOnAppStatusChangedListener(Utils.OnAppStatusChangedListener onAppStatusChangedListener) {
        e.g.addOnAppStatusChangedListener(onAppStatusChangedListener);
    }

    public static <T> Utils.Task<T> b(Utils.Task<T> task) {
        ThreadUtils.getCachedPool().execute(task);
        return task;
    }

    public static List<Activity> c() {
        return e.g.c();
    }

    public static SPUtils d() {
        return SPUtils.getInstance("Utils");
    }

    public static boolean e() {
        return !e.g.f;
    }

    public static void f(Utils.ActivityLifecycleCallbacks activityLifecycleCallbacks) {
        e eVar = e.g;
        Objects.requireNonNull(eVar);
        Activity activity = e.h;
        if (activity == null || activityLifecycleCallbacks == null) {
            return;
        }
        ThreadUtils.runOnUiThread(new d(eVar, activity, activityLifecycleCallbacks));
    }

    public static void removeOnAppStatusChangedListener(Utils.OnAppStatusChangedListener onAppStatusChangedListener) {
        e.g.removeOnAppStatusChangedListener(onAppStatusChangedListener);
    }
}
