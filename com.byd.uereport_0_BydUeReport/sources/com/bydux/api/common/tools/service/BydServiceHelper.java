package com.bydux.api.common.tools.service;

import android.app.ActivityManager;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Context;
import android.os.Build;
import android.os.IBinder;
import android.os.Process;
import com.bydux.api.common.tools.log.BydLogHelper;
import d0.e;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class BydServiceHelper {
    private static final String TAG = "BydServiceHelper";

    public static void addServiceToServiceManager(IBinder iBinder, String str) {
        BydLogHelper.logV(TAG, "addServiceToServiceManager", "binder=" + iBinder + ", name=" + str);
        try {
            Class.forName("android.os.ServiceManager").getMethod("addService", String.class, IBinder.class).invoke(new Object(), str, iBinder);
        } catch (Exception e8) {
            BydLogHelper.logE(TAG, "addServiceToServiceManager", e8.getMessage());
            e8.printStackTrace();
        }
    }

    private static Notification getNotification(Context context, String str) {
        int i8 = Build.VERSION.SDK_INT;
        if (i8 >= 26) {
            ((NotificationManager) context.getSystemService("notification")).createNotificationChannel(new NotificationChannel(str, str, 0));
        }
        Notification notificationA = new e.c(context, i8 >= 26 ? str : null).g(str).f(str).l(str).j(2).m(System.currentTimeMillis()).h(2).a();
        notificationA.flags = 16;
        return notificationA;
    }

    public static IBinder getServiceFromServiceManager(String str) {
        try {
            return (IBinder) Class.forName("android.os.ServiceManager").getMethod("getService", String.class).invoke(new Object(), str);
        } catch (Exception e8) {
            e8.printStackTrace();
            return null;
        }
    }

    public static boolean isServiceStarted(Context context, String str) {
        List<ActivityManager.RunningServiceInfo> runningServices = ((ActivityManager) context.getSystemService("activity")).getRunningServices(1000);
        for (int i8 = 0; i8 < runningServices.size(); i8++) {
            if (runningServices.get(i8).service.getClassName().equals(str)) {
                return true;
            }
        }
        return false;
    }

    public static void startForeground(Service service) {
        String simpleName = service.getClass().getSimpleName();
        BydLogHelper.logV(TAG, "startForeground", "name=" + simpleName);
        if (Build.VERSION.SDK_INT >= 26) {
            service.startForeground(Process.myPid(), getNotification(service, simpleName));
        }
    }
}
