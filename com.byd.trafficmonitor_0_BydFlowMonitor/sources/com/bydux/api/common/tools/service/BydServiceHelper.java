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
import b.h.d.g;
import com.bydux.api.common.tools.log.BydLogHelper;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class BydServiceHelper {
    private static final String TAG = "BydServiceHelper";

    public static void addServiceToServiceManager(IBinder iBinder, String str) {
        BydLogHelper.logV(TAG, "addServiceToServiceManager", "binder=" + iBinder + ", name=" + str);
        try {
            Class.forName("android.os.ServiceManager").getMethod("addService", String.class, IBinder.class).invoke(new Object(), str, iBinder);
        } catch (Exception e2) {
            BydLogHelper.logE(TAG, "addServiceToServiceManager", e2.getMessage());
            e2.printStackTrace();
        }
    }

    private static Notification getNotification(Context context, String str) {
        int i2 = Build.VERSION.SDK_INT;
        if (i2 >= 26) {
            ((NotificationManager) context.getSystemService("notification")).createNotificationChannel(new NotificationChannel(str, str, 0));
        }
        Notification notificationA = new g.c(context, i2 >= 26 ? str : null).e(str).d(str).h(str).g(2).i(System.currentTimeMillis()).f(2).a();
        notificationA.flags = 16;
        return notificationA;
    }

    public static IBinder getServiceFromServiceManager(String str) {
        try {
            return (IBinder) Class.forName("android.os.ServiceManager").getMethod("getService", String.class).invoke(new Object(), str);
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public static boolean isServiceStarted(Context context, String str) {
        List<ActivityManager.RunningServiceInfo> runningServices = ((ActivityManager) context.getSystemService("activity")).getRunningServices(1000);
        for (int i2 = 0; i2 < runningServices.size(); i2++) {
            if (runningServices.get(i2).service.getClassName().equals(str)) {
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
