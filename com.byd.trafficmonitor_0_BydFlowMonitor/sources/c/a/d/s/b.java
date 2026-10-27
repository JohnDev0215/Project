package c.a.d.s;

import java.lang.reflect.Method;

/* JADX INFO: compiled from: CarInfo.java */
/* JADX INFO: loaded from: classes.dex */
public class b {
    public static int a() {
        try {
            Class<?> cls = Class.forName("android.os.SystemProperties");
            Method method = cls.getMethod("getInt", String.class, Integer.TYPE);
            method.setAccessible(true);
            Object objInvoke = method.invoke(cls, "persist.vendor.byd.cfg.huc.steering.wheel.position", 0);
            if (objInvoke == null || !(objInvoke instanceof Integer)) {
                return 0;
            }
            return ((Integer) objInvoke).intValue();
        } catch (Exception e2) {
            e2.printStackTrace();
            return 0;
        }
    }

    public static boolean b() {
        return a() == 1;
    }
}
