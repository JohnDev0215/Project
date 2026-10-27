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
import androidx.core.app.NotificationCompat;
import com.bydux.api.common.tools.log.BydLogHelper;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public class BydServiceHelper {
    private static final String TAG = "BydServiceHelper";

    public static void addServiceToServiceManager(IBinder binder, String name) {
        BydLogHelper.logV(TAG, "addServiceToServiceManager", "binder=" + binder + ", name=" + name);
        try {
            Class.forName("android.os.ServiceManager").getMethod("addService", String.class, IBinder.class).invoke(new Object(), name, binder);
        } catch (Exception e) {
            BydLogHelper.logE(TAG, "addServiceToServiceManager", e.getMessage());
            e.printStackTrace();
        }
    }

    public static IBinder getServiceFromServiceManager(String name) {
        try {
            return (IBinder) Class.forName("android.os.ServiceManager").getMethod("getService", String.class).invoke(new Object(), name);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static boolean isServiceStarted(Context context, String serviceName) {
        List<ActivityManager.RunningServiceInfo> runningServices = ((ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE)).getRunningServices(1000);
        for (int i = 0; i < runningServices.size(); i++) {
            if (runningServices.get(i).service.getClassName().equals(serviceName)) {
                return true;
            }
        }
        return false;
    }

    public static void startForeground(Service context) {
        String simpleName = context.getClass().getSimpleName();
        BydLogHelper.logV(TAG, "startForeground", "name=" + simpleName);
        if (Build.VERSION.SDK_INT >= 26) {
            context.startForeground(Process.myPid(), getNotification(context, simpleName));
        }
    }

    private static Notification getNotification(Context context, String name) {
        if (Build.VERSION.SDK_INT >= 26) {
            ((NotificationManager) context.getSystemService("notification")).createNotificationChannel(new NotificationChannel(name, name, 0));
        }
        Notification notificationBuild = new NotificationCompat.Builder(context, Build.VERSION.SDK_INT >= 26 ? name : null).setContentTitle(name).setContentText(name).setTicker(name).setPriority(2).setWhen(System.currentTimeMillis()).setDefaults(2).build();
        notificationBuild.flags = 16;
        return notificationBuild;
    }
}
