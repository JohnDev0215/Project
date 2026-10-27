package com.byd.vrassistant.utils;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/* JADX INFO: loaded from: classes2.dex */
public class RefInvoke {
    public static Object createObject(String str) {
        try {
            return createObject(Class.forName(str), new Class[0], new Object[0]);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Object createObject(Class cls) {
        return createObject(cls, new Class[0], new Object[0]);
    }

    public static Object createObject(String str, Class cls, Object obj) {
        try {
            return createObject(Class.forName(str), new Class[]{cls}, new Object[]{obj});
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Object createObject(Class cls, Class cls2, Object obj) {
        return createObject(cls, new Class[]{cls2}, new Object[]{obj});
    }

    public static Object createObject(String str, Class[] clsArr, Object[] objArr) {
        try {
            return createObject(Class.forName(str), clsArr, objArr);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Object createObject(Class cls, Class[] clsArr, Object[] objArr) {
        try {
            Constructor declaredConstructor = cls.getDeclaredConstructor(clsArr);
            declaredConstructor.setAccessible(true);
            return declaredConstructor.newInstance(objArr);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Object invokeInstanceMethod(Object obj, String str, Class[] clsArr, Object[] objArr) {
        if (obj == null) {
            return null;
        }
        try {
            Method declaredMethod = obj.getClass().getDeclaredMethod(str, clsArr);
            declaredMethod.setAccessible(true);
            return declaredMethod.invoke(obj, objArr);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Object invokeInstanceMethod(Object obj, String str, Class cls, Object obj2) {
        return invokeInstanceMethod(obj, str, new Class[]{cls}, new Object[]{obj2});
    }

    public static Object invokeInstanceMethod(Object obj, String str) {
        return invokeInstanceMethod(obj, str, new Class[0], new Object[0]);
    }

    public static Object invokeStaticMethod(String str, String str2) {
        return invokeStaticMethod(str, str2, new Class[0], new Object[0]);
    }

    public static Object invokeStaticMethod(String str, String str2, Class cls, Object obj) {
        return invokeStaticMethod(str, str2, new Class[]{cls}, new Object[]{obj});
    }

    public static Object invokeStaticMethod(String str, String str2, Class[] clsArr, Object[] objArr) {
        try {
            return invokeStaticMethod(Class.forName(str), str2, clsArr, objArr);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Object invokeStaticMethod(Class cls, String str) {
        return invokeStaticMethod(cls, str, new Class[0], new Object[0]);
    }

    public static Object invokeStaticMethod(Class cls, String str, Class cls2, Object obj) {
        return invokeStaticMethod(cls, str, new Class[]{cls2}, new Object[]{obj});
    }

    public static Object invokeStaticMethod(Class cls, String str, Class[] clsArr, Object[] objArr) {
        try {
            Method declaredMethod = cls.getDeclaredMethod(str, clsArr);
            declaredMethod.setAccessible(true);
            return declaredMethod.invoke(null, objArr);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Object getFieldObject(Object obj, String str) {
        return getFieldObject(obj.getClass(), obj, str);
    }

    public static Object getFieldObject(String str, Object obj, String str2) {
        try {
            return getFieldObject(Class.forName(str), obj, str2);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Object getFieldObject(Class cls, Object obj, String str) {
        try {
            Field declaredField = cls.getDeclaredField(str);
            declaredField.setAccessible(true);
            return declaredField.get(obj);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void setFieldObject(Object obj, String str, Object obj2) {
        setFieldObject(obj.getClass(), obj, str, obj2);
    }

    public static void setFieldObject(Class cls, Object obj, String str, Object obj2) {
        try {
            Field declaredField = cls.getDeclaredField(str);
            declaredField.setAccessible(true);
            declaredField.set(obj, obj2);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void setFieldObject(String str, Object obj, String str2, Object obj2) {
        try {
            setFieldObject(Class.forName(str), obj, str2, obj2);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static Object getStaticFieldObject(String str, String str2) {
        return getFieldObject(str, (Object) null, str2);
    }

    public static Object getStaticFieldObject(Class cls, String str) {
        return getFieldObject(cls, (Object) null, str);
    }

    public static void setStaticFieldObject(String str, String str2, Object obj) {
        setFieldObject(str, (Object) null, str2, obj);
    }

    public static void setStaticFieldObject(Class cls, String str, Object obj) {
        setFieldObject(cls, (Object) null, str, obj);
    }
}
