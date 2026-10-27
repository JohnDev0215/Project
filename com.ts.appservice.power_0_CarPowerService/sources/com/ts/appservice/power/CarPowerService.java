package com.ts.appservice.power;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Configuration;
import android.os.IBinder;
import com.ts.lib.power.util.LogUtil;

/* JADX INFO: loaded from: classes.dex */
public class CarPowerService extends Service {
    private static final String ACTION_VENDOR_FACTORY_RESET = "android.intent.action.VENDOR_FACTORY_RESET";
    private static final String CONTENTTEXT = "ts car power text";
    private static final String CONTENTTITLE = "ts car power title";
    private static final String FACTORY_RESET_REBOOT_VALUE = "factory_reset_reboot";
    private static final String NOTIFICATIONID = "channelId";
    private static final String NOTIFICATIONNAME = "channelName";
    private static final String RESET_REBOOT = "reason";
    private static final String TAG = CarPowerService.class.getSimpleName();
    private CarPowerBinder mCarPowerBinder;
    private Context mContext;
    private NotificationManager mNotificationManager;

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        LogUtil.warning(TAG, "lifecycle---onCreate start");
        this.mContext = getApplicationContext();
        showStartForeground(1);
        this.mCarPowerBinder = new CarPowerBinder(this.mContext);
        IntentFilter intentFilter = new IntentFilter();
        MyBroadcastReceiver myBroadcastReceiver = new MyBroadcastReceiver();
        intentFilter.addAction(ACTION_VENDOR_FACTORY_RESET);
        registerReceiver(myBroadcastReceiver, intentFilter);
        LogUtil.warning(TAG, "lifecycle---onCreate end");
    }

    private class MyBroadcastReceiver extends BroadcastReceiver {
        private MyBroadcastReceiver() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            String resetRebootValue;
            LogUtil.info(CarPowerService.TAG, "onReceive : intent " + intent);
            if (intent.getAction().equals(CarPowerService.ACTION_VENDOR_FACTORY_RESET) && (resetRebootValue = intent.getStringExtra(CarPowerService.RESET_REBOOT)) != null && resetRebootValue.length() > 0 && resetRebootValue.equals(CarPowerService.FACTORY_RESET_REBOOT_VALUE)) {
                LogUtil.info(CarPowerService.TAG, "start reboot");
                CarPowerService.this.mCarPowerBinder.reboot(resetRebootValue);
            }
        }
    }

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        LogUtil.warning(TAG, "lifecycle---onBind");
        return this.mCarPowerBinder;
    }

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int flags, int startId) {
        LogUtil.info(TAG, "lifecycle---onStartCommand");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override // android.app.Service, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        CarPowerBinder carPowerBinder = this.mCarPowerBinder;
        if (carPowerBinder != null) {
            carPowerBinder.onConfigurationChanged(newConfig);
        }
    }

    @Override // android.app.Service
    public boolean onUnbind(Intent intent) {
        LogUtil.warning(TAG, "lifecycle---onUnbind");
        return super.onUnbind(intent);
    }

    @Override // android.app.Service
    public void onDestroy() {
        LogUtil.warning(TAG, "lifecycle---onDestroy start");
        hideStartForeground(true);
        this.mCarPowerBinder.removeAllCallbackAndMessage();
        super.onDestroy();
        LogUtil.warning(TAG, "lifecycle---onDestroy end");
    }

    private void showStartForeground(int id) {
        LogUtil.info(TAG, "showStartForeground id : " + id);
        this.mNotificationManager = (NotificationManager) getSystemService("notification");
        NotificationChannel channel = new NotificationChannel(NOTIFICATIONID, NOTIFICATIONNAME, 4);
        this.mNotificationManager.createNotificationChannel(channel);
        Notification.Builder builder = new Notification.Builder(this.mContext).setSmallIcon(android.R.mipmap.sym_def_app_icon).setContentTitle(CONTENTTITLE).setContentText(CONTENTTEXT);
        builder.setChannelId(NOTIFICATIONID);
        startForeground(id, builder.build());
    }

    private void hideStartForeground(boolean bool) {
        LogUtil.info(TAG, "hideStartForeground bool : " + bool);
        stopForeground(bool);
    }
}
