package defpackage;

import java.lang.reflect.InvocationTargetException;

/* JADX INFO: compiled from: MethodCall.java */
/* JADX INFO: loaded from: classes.dex */
public class og {
    public static Object a(Object obj, String str, Class<?> cls, Object obj2) {
        try {
            return obj.getClass().getMethod(str, cls).invoke(obj, obj2);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            return null;
        } catch (NoSuchMethodException e2) {
            e2.printStackTrace();
            return null;
        } catch (InvocationTargetException e3) {
            e3.printStackTrace();
            return null;
        }
    }
}
