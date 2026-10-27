package com.byd.foundation.utils;

import android.content.Context;
import android.content.pm.PackageManager;
import dalvik.system.DexFile;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class ReflectUtil {
    private static final String TAG = "ReflectUtil";

    public static List<Class<?>> getClassesInPackage(Context context, String str) {
        ArrayList arrayList = new ArrayList();
        try {
            Enumeration<String> enumerationEntries = new DexFile(context.getPackageManager().getApplicationInfo(context.getPackageName(), 128).sourceDir).entries();
            while (enumerationEntries.hasMoreElements()) {
                String strNextElement = enumerationEntries.nextElement();
                if (strNextElement.startsWith(str)) {
                    arrayList.add(Class.forName(strNextElement));
                }
            }
        } catch (PackageManager.NameNotFoundException | IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return arrayList;
    }

    public static Object getDeclaredFieldValue(String str, String str2) {
        try {
            Class<?> cls = Class.forName(str);
            Field declaredField = cls.getDeclaredField(str2);
            declaredField.setAccessible(true);
            return declaredField.get(cls);
        } catch (ClassNotFoundException | IllegalAccessException | NoSuchFieldException e) {
            LogUtils.e(TAG, e.getMessage());
            return null;
        }
    }

    public static Object getDefaultValue(Class<?> cls) {
        if (!Number.class.isAssignableFrom(cls)) {
            return Boolean.TYPE.isAssignableFrom(cls) ? Boolean.FALSE : Object.class.isAssignableFrom(cls) ? null : 0;
        }
        if (Float.class.isAssignableFrom(cls)) {
            return Float.valueOf(0.0f);
        }
        if (Long.class.isAssignableFrom(cls)) {
            return 0L;
        }
        if (Short.class.isAssignableFrom(cls)) {
            return (short) 0;
        }
        if (Double.class.isAssignableFrom(cls)) {
            return Double.valueOf(0.0d);
        }
        return 0;
    }

    public static Class getGenericType(Class cls) {
        Type[] genericInterfaces = cls.getGenericInterfaces();
        if (genericInterfaces.length <= 0) {
            return null;
        }
        Type type = ((ParameterizedType) genericInterfaces[0]).getActualTypeArguments()[0];
        if (type instanceof Class) {
            return (Class) type;
        }
        return null;
    }

    public static <T> T newInstance(Class<T> cls) {
        try {
            Constructor<T> declaredConstructor = cls.getDeclaredConstructor(new Class[0]);
            declaredConstructor.setAccessible(true);
            return cls.cast(declaredConstructor.newInstance(new Object[0]));
        } catch (Throwable th) {
            th.printStackTrace();
            return null;
        }
    }

    public static <T> T newInstance(Class<T> cls, Object... objArr) {
        try {
            Class<?>[] clsArr = new Class[objArr.length];
            for (int i = 0; i < objArr.length; i++) {
                clsArr[i] = objArr[i].getClass();
            }
            Constructor<T> declaredConstructor = cls.getDeclaredConstructor(clsArr);
            declaredConstructor.setAccessible(true);
            return cls.cast(declaredConstructor.newInstance(objArr));
        } catch (Throwable th) {
            th.printStackTrace();
            return null;
        }
    }
}
