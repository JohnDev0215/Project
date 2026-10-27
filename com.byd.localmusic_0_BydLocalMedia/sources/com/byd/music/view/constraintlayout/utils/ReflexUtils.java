package com.byd.music.view.constraintlayout.utils;

import android.util.Log;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/* JADX INFO: loaded from: classes5.dex */
public final class ReflexUtils {
    private static final String TAG = "ReflexUtils";

    public static Object getStaticFieldValue(Class<?> cls, String str) {
        try {
            Field declaredField = cls.getDeclaredField(str);
            declaredField.setAccessible(true);
            return declaredField.get(null);
        } catch (IllegalAccessException | NoSuchFieldException e) {
            Log.e(TAG, "get static field failure.", e);
            return null;
        }
    }

    public static Object getFieldValue(Object obj, String str) {
        try {
            Field declaredField = obj.getClass().getDeclaredField(str);
            declaredField.setAccessible(true);
            return declaredField.get(obj);
        } catch (IllegalAccessException | NoSuchFieldException e) {
            Log.e(TAG, "get field failure.", e);
            return null;
        }
    }

    public static void setStaticField(Class<?> cls, String str, Object obj) {
        try {
            Field declaredField = cls.getDeclaredField(str);
            declaredField.setAccessible(true);
            declaredField.set(null, obj);
        } catch (IllegalAccessException | NoSuchFieldException e) {
            Log.e(TAG, "set field failure.", e);
        }
    }

    public static void setField(Object obj, String str, Object obj2) {
        try {
            Field declaredField = obj.getClass().getDeclaredField(str);
            declaredField.setAccessible(true);
            declaredField.set(obj, obj2);
        } catch (IllegalAccessException | NoSuchFieldException e) {
            Log.e(TAG, "set field failure.", e);
        }
    }

    public static Method getMethod(Class<?> cls, String str, Class<?>... clsArr) {
        try {
            return cls.getDeclaredMethod(str, clsArr);
        } catch (NoSuchMethodException e) {
            Log.e(TAG, "get method failure.", e);
            return null;
        }
    }

    public static Object invokeStaticMethod(Method method, Object... objArr) {
        try {
            method.setAccessible(true);
            if (objArr != null && objArr.length != 0) {
                return method.invoke(null, objArr);
            }
            return method.invoke(null, new Object[0]);
        } catch (IllegalAccessException | InvocationTargetException e) {
            Log.e(TAG, "invoke static method failure.", e);
            return null;
        }
    }

    public static Object invokeMethod(Object obj, Method method, Object... objArr) {
        try {
            method.setAccessible(true);
            if (objArr != null && objArr.length != 0) {
                return method.invoke(obj, objArr);
            }
            return method.invoke(obj, new Object[0]);
        } catch (IllegalAccessException | InvocationTargetException e) {
            Log.e(TAG, "invoke method failure.", e);
            return null;
        }
    }

    public static <T> void copyAllField(T t, T t2) {
        Field[] fields = getFields(t.getClass());
        if (fields == null || fields.length == 0) {
            return;
        }
        for (Field field : fields) {
            field.setAccessible(true);
            try {
                field.set(t2, field.get(t));
            } catch (IllegalAccessException e) {
                Log.e(TAG, "copy field failure.", e);
            }
        }
    }

    private static Field[] getFields(Class<?> cls) {
        Method method = getMethod(Class.class, "getDeclaredFields", new Class[0]);
        if (method == null) {
            return null;
        }
        method.setAccessible(true);
        try {
            return (Field[]) method.invoke(cls, new Object[0]);
        } catch (IllegalAccessException | InvocationTargetException e) {
            Log.e(TAG, "get fields failure.", e);
            return null;
        }
    }
}
