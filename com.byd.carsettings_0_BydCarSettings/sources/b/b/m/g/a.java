package b.b.m.g;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/* JADX INFO: compiled from: BydReflect.java */
/* JADX INFO: loaded from: classes2.dex */
public class a {
    public static Object a(Object obj, String str) {
        try {
            Method method = obj.getClass().getMethod(str, new Class[0]);
            method.setAccessible(true);
            return method.invoke(obj, new Object[0]);
        } catch (IllegalAccessException | NoSuchMethodException | InvocationTargetException e2) {
            b.b.m.c.a.c("BydReflect", ", Invoke no param failed : " + e2.toString());
            return null;
        }
    }

    public static Object b(Object obj, String str, Class<?> cls, Object obj2) {
        try {
            Method method = obj.getClass().getMethod(str, cls);
            method.setAccessible(true);
            return method.invoke(obj, obj2);
        } catch (IllegalAccessException | NoSuchMethodException | InvocationTargetException e2) {
            e2.printStackTrace();
            b.b.m.c.a.c("BydReflect", ", Invoke single param failed : " + e2.toString());
            return null;
        }
    }

    public static Object c(Object obj, String str, Class<?>[] clsArr, Object[] objArr) {
        try {
            Method method = obj.getClass().getMethod(str, clsArr);
            method.setAccessible(true);
            return method.invoke(obj, objArr);
        } catch (IllegalAccessException | NoSuchMethodException | InvocationTargetException e2) {
            b.b.m.c.a.c("BydReflect", ", Invoke param arr failed : " + e2.toString());
            return null;
        }
    }

    public static void d(Object obj, String str, Object obj2) {
        try {
            Field declaredField = obj.getClass().getDeclaredField(str);
            declaredField.setAccessible(true);
            declaredField.set(obj, obj2);
        } catch (IllegalAccessException | NoSuchFieldException e2) {
            b.b.m.c.a.c("BydReflect", ", Reflect field failed : " + e2.toString());
        }
    }
}
