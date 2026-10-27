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
import androidx.appcompat.widget.ActivityChooserModel;
import androidx.core.app.NotificationCompat;
import com.bydux.api.common.tools.log.BydLogHelper;
import java.util.List;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes.dex */
public class BydServiceHelper {
    private static final String TAG = "BydServiceHelper";

    public static void addServiceToServiceManager(IBinder iBinder, String str) {
        BydLogHelper.logV(TAG, "addServiceToServiceManager", "binder=" + iBinder + ", name=" + str);
        try {
            Class.forName("android.os.ServiceManager").getMethod("addService", String.class, IBinder.class).invoke(new Object(), str, iBinder);
        } catch (Exception e) {
            BydLogHelper.logE(TAG, "addServiceToServiceManager", e.getMessage());
            e.printStackTrace();
        }
    }

    private static Notification getNotification(Context context, String str) {
        int i = Build.VERSION.SDK_INT;
        if (i >= 26) {
            ((NotificationManager) context.getSystemService("notification")).createNotificationChannel(new NotificationChannel(str, str, 0));
        }
        Notification notificationBuild = new NotificationCompat.Builder(context, i >= 26 ? str : null).setContentTitle(str).setContentText(str).setTicker(str).setPriority(2).setWhen(System.currentTimeMillis()).setDefaults(2).build();
        notificationBuild.flags = 16;
        return notificationBuild;
    }

    public static IBinder getServiceFromServiceManager(String str) {
        try {
            return (IBinder) Class.forName("android.os.ServiceManager").getMethod("getService", String.class).invoke(new Object(), str);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static boolean isServiceStarted(Context context, String str) {
        List<ActivityManager.RunningServiceInfo> runningServices = ((ActivityManager) context.getSystemService(ActivityChooserModel.ATTRIBUTE_ACTIVITY)).getRunningServices(1000);
        for (int i = 0; i < runningServices.size(); i++) {
            if (runningServices.get(i).service.getClassName().equals(str)) {
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
