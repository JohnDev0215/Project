package g5;

import android.util.Log;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/* JADX INFO: compiled from: SystemPropertiesUtils.java */
/* JADX INFO: loaded from: classes2.dex */
public class l {
    public static String a(String str, String str2) throws IllegalArgumentException {
        try {
            Class<?> cls = Class.forName("android.os.SystemProperties");
            Method declaredMethod = cls.getDeclaredMethod("get", String.class, String.class);
            declaredMethod.setAccessible(true);
            return (String) declaredMethod.invoke(cls, str, str2);
        } catch (ClassNotFoundException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e8) {
            Log.e("SystemPropertiesUtils", "Failed to invoke SystemProperties.get()", e8);
            return str2;
        }
    }

    public static void b(String str, String str2) throws IllegalArgumentException {
        try {
            Class<?> cls = Class.forName("android.os.SystemProperties");
            Method declaredMethod = cls.getDeclaredMethod("set", String.class, String.class);
            declaredMethod.setAccessible(true);
            declaredMethod.invoke(cls, str, str2);
        } catch (ClassNotFoundException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e8) {
            Log.e("SystemPropertiesUtils", "Failed to invoke SystemProperties.set()", e8);
        }
    }
}
