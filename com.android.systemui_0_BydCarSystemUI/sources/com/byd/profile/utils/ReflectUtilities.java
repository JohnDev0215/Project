package com.byd.profile.utils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/* JADX INFO: loaded from: classes2.dex */
public class ReflectUtilities {
    private static final String TAG = "ReflectUtilities";

    public static Object getField(String clazzName, Object target, String name) throws Exception {
        return getField(Class.forName(clazzName), target, name);
    }

    public static Object getField(Class clazz, Object target, String name) throws Exception {
        Field declaredField = clazz.getDeclaredField(name);
        declaredField.setAccessible(true);
        return declaredField.get(target);
    }

    public static Object getFieldNoException(String clazzName, Object target, String name) {
        try {
            return getFieldNoException(Class.forName(clazzName), target, name);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Object getFieldNoException(Class clazz, Object target, String name) {
        try {
            return getField(clazz, target, name);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void setField(String clazzName, Object target, String name, Object value) throws Exception {
        setField(Class.forName(clazzName), target, name, value);
    }

    public static void setField(Class clazz, Object target, String name, Object value) throws Exception {
        Field declaredField = clazz.getDeclaredField(name);
        declaredField.setAccessible(true);
        declaredField.set(target, value);
    }

    public static void setFieldNoException(String clazzName, Object target, String name, Object value) {
        try {
            setFieldNoException(Class.forName(clazzName), target, name, value);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void setFieldNoException(Class clazz, Object target, String name, Object value) {
        try {
            setField(clazz, target, name, value);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Object invoke(String clazzName, Object target, String name, Object... args) throws Exception {
        return invoke(Class.forName(clazzName), target, name, args);
    }

    public static Object invoke(Class clazz, Object target, String name, Object... args) throws Exception {
        Class<?>[] clsArr;
        if (args != null) {
            clsArr = new Class[args.length];
            for (int i = 0; i < args.length; i++) {
                clsArr[i] = args[i].getClass();
            }
        } else {
            clsArr = null;
        }
        Method declaredMethod = clazz.getDeclaredMethod(name, clsArr);
        declaredMethod.setAccessible(true);
        return declaredMethod.invoke(target, args);
    }

    public static Object invoke(String clazzName, Object target, String name, Class[] parameterTypes, Object... args) throws Exception {
        return invoke(Class.forName(clazzName), target, name, parameterTypes, args);
    }

    public static Object invoke(Class clazz, Object target, String name, Class[] parameterTypes, Object... args) throws Exception {
        Method declaredMethod = clazz.getDeclaredMethod(name, parameterTypes);
        declaredMethod.setAccessible(true);
        return declaredMethod.invoke(target, args);
    }

    public static Object invokeNoException(String clazzName, Object target, String name, Class[] parameterTypes, Object... args) {
        try {
            return invokeNoException(Class.forName(clazzName), target, name, parameterTypes, args);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Object invokeNoException(Class clazz, Object target, String name, Class[] parameterTypes, Object... args) {
        try {
            return invoke(clazz, target, name, parameterTypes, args);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
