package c.a.d;

import android.util.Log;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/* JADX INFO: compiled from: SystemPropertiesUtils.java */
/* JADX INFO: loaded from: classes.dex */
public class j {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static String f1633a = "persist.vendor.byd.cfg.huc.steering.wheel.position";

    public static String a(String str, String str2) throws IllegalArgumentException {
        try {
            Class<?> cls = Class.forName("android.os.SystemProperties");
            Method declaredMethod = cls.getDeclaredMethod("get", String.class, String.class);
            declaredMethod.setAccessible(true);
            return (String) declaredMethod.invoke(cls, str, str2);
        } catch (ClassNotFoundException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            Log.e("SystemPropertiesUtils", "Failed to invoke SystemProperties.get()", e);
            return str2;
        }
    }
}
