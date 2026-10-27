package com.byd.vrassistant.hint;

import android.app.Application;
import android.util.Log;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

/* JADX INFO: loaded from: classes.dex */
public class AppUtils {
    private static final String TAG = "AppUtils";
    static Application application;

    static Application getApp() {
        if (application == null) {
            synchronized (AppUtils.class) {
                if (application == null) {
                    application = getApplicationByReflect();
                }
            }
        }
        return application;
    }

    static Application getApplicationByReflect() {
        try {
            Object objInvoke = Class.forName("android.app.ActivityThread").getMethod("getApplication", new Class[0]).invoke(getActivityThread(), new Object[0]);
            if (objInvoke == null) {
                return null;
            }
            return (Application) objInvoke;
        } catch (ClassNotFoundException e) {
            Log.e(TAG, "getApplicationByReflect: " + e.getMessage());
            return null;
        } catch (IllegalAccessException e2) {
            Log.e(TAG, "getApplicationByReflect: " + e2.getMessage());
            return null;
        } catch (NoSuchMethodException e3) {
            Log.e(TAG, "getApplicationByReflect: " + e3.getMessage());
            return null;
        } catch (InvocationTargetException e4) {
            Log.e(TAG, "getApplicationByReflect: " + e4.getMessage());
            return null;
        }
    }

    private static Object getActivityThread() {
        Object activityThreadInActivityThreadStaticField = getActivityThreadInActivityThreadStaticField();
        return activityThreadInActivityThreadStaticField != null ? activityThreadInActivityThreadStaticField : getActivityThreadInActivityThreadStaticMethod();
    }

    private static Object getActivityThreadInActivityThreadStaticField() {
        try {
            Field declaredField = Class.forName("android.app.ActivityThread").getDeclaredField("sCurrentActivityThread");
            declaredField.setAccessible(true);
            return declaredField.get(null);
        } catch (Exception e) {
            Log.e("UtilsActivityLifecycle", "getActivityThreadInActivityThreadStaticField: " + e.getMessage());
            return null;
        }
    }

    private static Object getActivityThreadInActivityThreadStaticMethod() {
        try {
            return Class.forName("android.app.ActivityThread").getMethod("currentActivityThread", new Class[0]).invoke(null, new Object[0]);
        } catch (Exception e) {
            Log.e("UtilsActivityLifecycle", "getActivityThreadInActivityThreadStaticMethod: " + e.getMessage());
            return null;
        }
    }
}
