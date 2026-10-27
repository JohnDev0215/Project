package com.byd.vrsettings.service;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Handler;
import android.os.IBinder;
import com.byd.vrsettings.BydVoiceSettingApplication;
import com.byd.vrsettings.NetFloatWindowService;
import com.byd.vrsettings.utils.RLog;
import com.byd.vrsettings.utils.SharedPreferencesUtils;
import com.byd.vrsettings.utils.SystemAbility;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class DialogManager {
    private static final String SERVICE_CLASS = "com.byd.dialogservice.DialogService";
    private static final String SERVICE_PKG = "com.byd.carsettings";
    private static final String TAG = "NEU_DialogManager";
    NetFloatWindowService mService;
    MyServiceConnection mServiceConnection;
    public boolean isResumeShowNetDialog = true;
    String schemeWifi = "byd_pad://byd_car_settings:2933/launcher?activity=WiFi";

    private static final class SingletonHolder {
        private static final DialogManager INSTANCE = new DialogManager();

        private SingletonHolder() {
        }
    }

    public static DialogManager get() {
        return SingletonHolder.INSTANCE;
    }

    public void showNetTipDialog(final boolean z) {
        if (!new SystemAbility().supportNet()) {
            RLog.e(TAG, "!support net showNetTipDialog");
            return;
        }
        String str = TAG;
        RLog.e(str, "showNetTipDialog");
        if (this.mService != null) {
            RLog.e(str, "mService != null");
            this.mService.showFloatingWindow(z);
        } else {
            RLog.e(str, "mService is  null");
            new Handler().postDelayed(new Runnable() { // from class: com.byd.vrsettings.service.DialogManager.1
                @Override // java.lang.Runnable
                public void run() {
                    if (DialogManager.this.mService == null) {
                        RLog.e(DialogManager.TAG, "mService == null");
                    } else {
                        DialogManager.this.mService.showFloatingWindow(z);
                    }
                }
            }, 1000L);
        }
    }

    public void dismissDialog() {
        if (!new SystemAbility().supportNet()) {
            RLog.e(TAG, "!support net dismissDialog");
            return;
        }
        String str = TAG;
        RLog.e(str, "dismissDialog");
        NetFloatWindowService netFloatWindowService = this.mService;
        if (netFloatWindowService != null) {
            netFloatWindowService.removeFloatingWindow();
        } else {
            RLog.e(str, "mService == null");
        }
    }

    public void startSystemNetPage() {
        RLog.e(TAG, "startWifiPage");
        openWifiSetting();
    }

    public void openWifiSetting() {
        String str = TAG;
        RLog.d(str, "openWifiSetting: ");
        Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(this.schemeWifi));
        intent.setComponent(new ComponentName(SERVICE_PKG, SERVICE_CLASS));
        List<ResolveInfo> listQueryIntentServices = BydVoiceSettingApplication.getContext().getPackageManager().queryIntentServices(intent, 0);
        RLog.i(str, "----startIntentByPath----resolveInfos = " + listQueryIntentServices);
        if (listQueryIntentServices.isEmpty()) {
            return;
        }
        BydVoiceSettingApplication.getContext().startService(intent);
    }

    public static void setNetDialogTodayNotTip(Context context) {
        long jCurrentTimeMillis = System.currentTimeMillis();
        SharedPreferencesUtils.setParam(context, SharedPreferencesUtils.SP_KEY_NET_DIALOG_TODAY_NOT_TIP, Long.valueOf(jCurrentTimeMillis));
        RLog.e(TAG, "setNetDialogTodayNotTip:" + jCurrentTimeMillis);
    }

    public boolean isNetDialogTodayNotTip(Context context) {
        boolean zIsNow = OtaDataManager.isNow(((Long) SharedPreferencesUtils.getParam(context, SharedPreferencesUtils.SP_KEY_NET_DIALOG_TODAY_NOT_TIP, 0L)).longValue());
        RLog.e(TAG, "isToday:" + zIsNow);
        return zIsNow;
    }

    public void setLight(boolean z) {
        NetFloatWindowService netFloatWindowService = this.mService;
        if (netFloatWindowService != null) {
            netFloatWindowService.setLight(z);
        }
    }

    public void start() {
        if (!new SystemAbility().supportNet()) {
            RLog.e(TAG, "turkey and so on not use net");
            return;
        }
        this.mServiceConnection = new MyServiceConnection();
        BydVoiceSettingApplication.getContext().bindService(new Intent(BydVoiceSettingApplication.getContext(), (Class<?>) NetFloatWindowService.class), this.mServiceConnection, 1);
        RLog.e(TAG, "start NetFloatWindow Service");
    }

    public class MyServiceConnection implements ServiceConnection {
        public MyServiceConnection() {
        }

        @Override // android.content.ServiceConnection
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            DialogManager.this.mService = ((NetFloatWindowService.LocalBinder) iBinder).getService();
            RLog.e(DialogManager.TAG, "onServiceConnected");
        }

        @Override // android.content.ServiceConnection
        public void onServiceDisconnected(ComponentName componentName) {
            DialogManager.this.mService = null;
            RLog.e(DialogManager.TAG, "onServiceDisconnected");
        }
    }
}
