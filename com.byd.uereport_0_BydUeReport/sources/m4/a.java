package m4;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/* JADX INFO: compiled from: BydReflect.java */
/* JADX INFO: loaded from: classes2.dex */
public class a {
    public static Object a(Object obj, String str) {
        try {
            Field declaredField = obj.getClass().getDeclaredField(str);
            declaredField.setAccessible(true);
            return declaredField.get(obj);
        } catch (IllegalAccessException | NoSuchFieldException e8) {
            x3.a.b("BydReflect", ", Reflect field failed : " + e8.toString());
            return null;
        }
    }

    public static Object b(Object obj, String str) {
        try {
            Method method = obj.getClass().getMethod(str, new Class[0]);
            method.setAccessible(true);
            return method.invoke(obj, new Object[0]);
        } catch (IllegalAccessException | NoSuchMethodException | InvocationTargetException e8) {
            x3.a.b("BydReflect", ", Invoke no param failed : " + e8.toString());
            return null;
        }
    }

    public static Object c(Object obj, String str, Class<?> cls, Object obj2) {
        try {
            Method method = obj.getClass().getMethod(str, cls);
            method.setAccessible(true);
            return method.invoke(obj, obj2);
        } catch (IllegalAccessException | NoSuchMethodException | InvocationTargetException e8) {
            e8.printStackTrace();
            x3.a.b("BydReflect", ", Invoke single param failed : " + e8.toString());
            return null;
        }
    }

    public static Object d(Object obj, String str, Class<?>[] clsArr, Object[] objArr) {
        try {
            Method method = obj.getClass().getMethod(str, clsArr);
            method.setAccessible(true);
            return method.invoke(obj, objArr);
        } catch (IllegalAccessException | NoSuchMethodException | InvocationTargetException e8) {
            x3.a.b("BydReflect", ", Invoke param arr failed : " + e8.toString());
            return null;
        }
    }

    public static void e(Object obj, String str, Object obj2) {
        try {
            Field declaredField = obj.getClass().getDeclaredField(str);
            declaredField.setAccessible(true);
            declaredField.set(obj, obj2);
        } catch (IllegalAccessException | NoSuchFieldException e8) {
            x3.a.b("BydReflect", ", Reflect field failed : " + e8.toString());
        }
    }
}
