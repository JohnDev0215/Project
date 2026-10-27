package com.byd.data.common.energy;

import android.util.Log;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/* JADX INFO: compiled from: SystemPropertiesUtils.java */
/* JADX INFO: loaded from: classes.dex */
public class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static String f3371a = "persist.sys.energytype";

    public static String a(String str, String str2) {
        try {
            Class<?> cls = Class.forName("android.os.SystemProperties");
            Method declaredMethod = cls.getDeclaredMethod("get", String.class, String.class);
            declaredMethod.setAccessible(true);
            return (String) declaredMethod.invoke(cls, str, str2);
        } catch (ClassNotFoundException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e2) {
            Log.e("SystemPropertiesUtils", "Failed to invoke SystemProperties.get()", e2);
            return str2;
        }
    }

    public static boolean b() {
        try {
            int iIntValue = Integer.valueOf(a(f3371a, "1")).intValue();
            Log.i("SystemPropertiesUtils", "isCarEVByProp:" + iIntValue);
            return iIntValue != 2;
        } catch (Exception e2) {
            e2.printStackTrace();
            return true;
        }
    }
}
