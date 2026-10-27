package g5;

import android.content.Context;
import android.net.wifi.WifiManager;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/* JADX INFO: compiled from: NetworkUtil.java */
/* JADX INFO: loaded from: classes2.dex */
public class i {
    public static void a(Context context) {
        try {
            Class<?> cls = Class.forName("android.net.TetheringManager");
            Object systemService = context.getApplicationContext().getSystemService(cls);
            if (systemService == null) {
                return;
            }
            Method method = cls.getMethod("stopTethering", Integer.TYPE);
            method.setAccessible(true);
            method.invoke(systemService, 0);
        } catch (ClassNotFoundException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e8) {
            e8.printStackTrace();
        }
    }

    public static boolean b(Context context) {
        try {
            WifiManager wifiManager = (WifiManager) context.getSystemService("wifi");
            Method declaredMethod = wifiManager.getClass().getDeclaredMethod("isWifiApEnabled", new Class[0]);
            declaredMethod.setAccessible(true);
            return ((Boolean) declaredMethod.invoke(wifiManager, new Object[0])).booleanValue();
        } catch (IllegalAccessException | NoSuchMethodException | InvocationTargetException e8) {
            e8.printStackTrace();
            return false;
        }
    }
}
