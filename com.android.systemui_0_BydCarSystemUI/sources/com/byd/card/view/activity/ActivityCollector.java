package com.byd.card.view.activity;

import android.app.Activity;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public class ActivityCollector {
    public static HashMap<Class<?>, Activity> activities = new LinkedHashMap();

    public static void addActivity(Activity activity, Class<?> clz) {
        activities.put(clz, activity);
    }

    public static <T extends Activity> boolean isActivityExist(Class<T> clz) {
        Activity activity = getActivity(clz);
        return (activity == null || activity.isFinishing() || activity.isDestroyed()) ? false : true;
    }

    public static <T extends Activity> T getActivity(Class<T> clazz) {
        return (T) activities.get(clazz);
    }

    public static void removeActivity(Activity activity) {
        if (activities.containsValue(activity)) {
            activities.remove(activity.getClass());
        }
    }

    public static void removeAllActivity() {
        HashMap<Class<?>, Activity> map = activities;
        if (map != null && map.size() > 0) {
            for (Map.Entry<Class<?>, Activity> entry : activities.entrySet()) {
                if (!entry.getValue().isFinishing()) {
                    entry.getValue().finish();
                }
            }
        }
        activities.clear();
    }
}
