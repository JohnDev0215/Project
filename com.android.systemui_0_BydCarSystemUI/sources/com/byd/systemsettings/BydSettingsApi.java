package com.byd.systemsettings;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import android.os.UserHandle;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.Log;
import com.byd.systemsettings.ISystemSettings;
import com.byd.systemsettings.callback.DeathRecipientCallback;
import com.byd.systemsettings.callback.OnServiceDeathCallback;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public final class BydSettingsApi {
    private static final String ACTION = "action";
    public static final String ACTION_BYD_SYSTEM_SETTING = "com.byd.action.ACTION_BYD_SYSTEM_SETTING";
    private static final String ACTIVITY = "activity";
    private static final int API_SUPPORT_SYSTEM_SETTINGS = 1;
    private static final int API_UNSUPPORT = 0;
    public static final String BLUETOOTH = "Bluetooth";
    public static final String CAR_PLAY = "car_play";
    public static final String CAR_SETTING = "car_setting";
    public static final String CELLULAR_DATA = "cellular_data";
    public static final String CPAA_DEVICE_LIST = "cpaa_device_list";
    public static final String DILINK_SETTING = "dilink_setting";
    public static final String DILINK_VERSION_MANAGE = "dilink_version_manage";
    public static final String FINISH_SCREEN_SAVER = "finish_screen_saver";
    public static final String HOTSPOT = "Hotspot";
    private static final String KEY_PKG = "pkg";
    public static final String MAIN = "Main";
    private static final String MSG_SERVICE_DISCONNECTED = "BydSettingsApi service is disconnected ,and doing reconnect.";
    public static final int NAVIBAR_MODE_NORMAL = 1;
    public static final String NAVIBAR_MODE_ON_SCREENSAVER = "navibar_mode_on_screensaver";
    public static final int NAVIBAR_MODE_POWER_OFF = 0;
    public static final int NAVIBAR_MODE_SCREENSAVER = 2;
    public static final String NOTIFICATION_APP_LIST = "notification_app_list";
    public static final String PRIVACY_MANAGE = "privacy_manage";
    public static final String RESTORE_FACTORY_SETTINGS = "restore_factory_settings";
    public static final String SCREEN_SAVER = "screen_saver";
    public static final String SELF_START = "self_start";
    private static final String SERVICE_CLASS = "com.byd.dialogservice.DialogService";
    private static final String SERVICE_PKG = "com.byd.carsettings";
    public static final String SOUND_SETTINGS = "sound_settings";
    private static final String SYSTEM = "system";
    private static final String SYSTEM_SERVICE_CLASS = "com.byd.uereport.MultiModuleService";
    private static final String SYSTEM_SERVICE_PKG = "com.byd.uereport";
    public static final String SYS_LANGUAGE_SETTINGS = "sys_language_settings";
    private static final String TAG = "BydSettingsApi";
    private static final String UE_API_VERSION = "UE_API_VERSION";
    public static final String WIFI = "WiFi";
    private OnServiceConnectCallback mConnectCallback;
    private ISystemSettings systemSettings;
    private static final String[] SYSTEM_PKG_ARR = {"com.byd.carsettings", "com.byd.bluetoothcall", "com.android.systemui", "com.byd.localmusic"};
    public static final String DISMISS_BT_SETTINGS = "dismiss_bt_settings";
    public static final String DISMISS_WIFI_SETTINGS = "dismiss_wifi_settings";
    public static final String DISMISS_HOTSPOT_SETTINGS = "dismiss_hotspot_settings";
    public static final String DISMISS_CARPLAY = "dismiss_carplay";
    public static final String CPAA = "cpaa";
    public static final String DISMISS_CPAA_SETTINGS = "dismiss_cpaa_settings";
    public static final String DISMISS_ALL_DIALOG = "dismiss_all_dialog";
    public static final String[] SUPPORT_SYSTEM_SETTINGS_ACTION_ARR = {"screen_saver", "finish_screen_saver", "Bluetooth", DISMISS_BT_SETTINGS, "WiFi", DISMISS_WIFI_SETTINGS, "Hotspot", DISMISS_HOTSPOT_SETTINGS, "car_play", DISMISS_CARPLAY, CPAA, DISMISS_CPAA_SETTINGS, "privacy_manage", DISMISS_ALL_DIALOG};
    private static volatile BydSettingsApi instance = null;
    private static volatile boolean isServiceConnected = false;
    private volatile boolean hightLevelDialogShowing = false;
    private int ueApiVersion = 0;
    private Context appContext = null;
    private DeathRecipientCallback deathProxy = new DeathRecipientCallback();
    private ServiceConnection connection = new ServiceConnection() { // from class: com.byd.systemsettings.BydSettingsApi.1
        @Override // android.content.ServiceConnection
        public void onServiceConnected(ComponentName name, IBinder service) {
            boolean unused = BydSettingsApi.isServiceConnected = true;
            Log.i(BydSettingsApi.TAG, "---onServiceConnected---name: " + name + ", service: " + service);
            BydSettingsApi.this.systemSettings = ISystemSettings.Stub.asInterface(service);
            if (BydSettingsApi.this.mConnectCallback != null) {
                BydSettingsApi.this.mConnectCallback.onServiceConnectChanged(true);
            }
            try {
                BydSettingsApi.this.deathProxy.setServiceName(name.toString());
                service.linkToDeath(BydSettingsApi.this.deathProxy, 0);
            } catch (RemoteException e) {
                e.printStackTrace();
                Log.e(BydSettingsApi.TAG, "---onServiceConnected---e: " + e.toString());
            }
        }

        @Override // android.content.ServiceConnection
        public void onServiceDisconnected(ComponentName name) {
            boolean unused = BydSettingsApi.isServiceConnected = false;
            Log.e(BydSettingsApi.TAG, "---onServiceDisconnected---name: " + name);
            if (BydSettingsApi.this.mConnectCallback != null) {
                BydSettingsApi.this.mConnectCallback.onServiceConnectChanged(false);
            }
        }
    };
    private ServiceConnection connectionDeath = new ServiceConnection() { // from class: com.byd.systemsettings.BydSettingsApi.2
        @Override // android.content.ServiceConnection
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.i(BydSettingsApi.TAG, "---onServiceConnected---service: " + service);
            try {
                BydSettingsApi.this.deathProxy.setServiceName(name.toString());
                service.linkToDeath(BydSettingsApi.this.deathProxy, 0);
            } catch (RemoteException e) {
                e.printStackTrace();
                Log.e(BydSettingsApi.TAG, "---onServiceConnected---e: " + e.toString());
            }
        }

        @Override // android.content.ServiceConnection
        public void onServiceDisconnected(ComponentName name) {
            Log.i(BydSettingsApi.TAG, "---onServiceDisconnected---name: " + name);
        }
    };

    public interface OnServiceConnectCallback {
        void onServiceConnectChanged(boolean connected);
    }

    private BydSettingsApi() {
    }

    public static BydSettingsApi getApi(Context context, OnServiceConnectCallback callback) {
        Context applicationContext = context.getApplicationContext();
        if (instance == null) {
            synchronized (BydSettingsApi.class) {
                if (instance == null) {
                    instance = new BydSettingsApi();
                    instance.bindService(applicationContext);
                }
            }
        }
        if (!isServiceConnected) {
            instance.bindService(applicationContext);
        }
        instance.mConnectCallback = callback;
        return instance;
    }

    public void bindService(Context ctx) {
        Log.i(TAG, "---bindService---isServiceConnected: " + isServiceConnected);
        if (ctx == null || isServiceConnected) {
            return;
        }
        this.appContext = ctx.getApplicationContext();
        Intent intent = new Intent();
        intent.setAction(ACTION_BYD_SYSTEM_SETTING);
        intent.setComponent(new ComponentName(SYSTEM_SERVICE_PKG, SYSTEM_SERVICE_CLASS));
        boolean zCheckSystemApp = checkSystemApp(ctx);
        Log.i(TAG, "---bindService---systemApp: " + zCheckSystemApp);
        if (zCheckSystemApp) {
            Parcel parcelObtain = Parcel.obtain();
            Class<?>[] clsArr = {Intent.class, ServiceConnection.class, Integer.TYPE, UserHandle.class};
            Object[] objArr = {intent, this.connection, 1, UserHandle.readFromParcel(parcelObtain)};
            try {
                Method method = ctx.getClass().getMethod("bindServiceAsUser", clsArr);
                method.setAccessible(true);
                method.invoke(ctx, objArr);
            } catch (IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
                Log.e(TAG, ", Invoke param arr failed : " + e.toString());
                Log.i(TAG, "---do as normal bindService---");
                ctx.bindService(intent, this.connection, 1);
            }
            parcelObtain.recycle();
        } else {
            ctx.bindService(intent, this.connection, 1);
        }
        this.ueApiVersion = getApiVersion(ctx);
        Log.i(TAG, "---bindService---ueApiVersion: " + this.ueApiVersion);
    }

    private boolean checkSystemApp(Context ctx) {
        if (ctx == null) {
            return false;
        }
        for (String str : SYSTEM_PKG_ARR) {
            if (str.equals(ctx.getPackageName())) {
                return true;
            }
        }
        return false;
    }

    public int getApiVersion(Context ctx) {
        if (ctx == null) {
            return 0;
        }
        return Settings.System.getInt(ctx.getContentResolver(), UE_API_VERSION, 0);
    }

    public void setHightLevelDialogShowing(boolean hightLevelDialogShowing) {
        this.hightLevelDialogShowing = hightLevelDialogShowing;
    }

    public void release(Context context) {
        if (context == null) {
            return;
        }
        Context applicationContext = context.getApplicationContext();
        isServiceConnected = false;
        ServiceConnection serviceConnection = this.connection;
        if (serviceConnection != null) {
            try {
                applicationContext.unbindService(serviceConnection);
            } catch (RuntimeException e) {
                Log.e(TAG, "----release---e: " + e);
            }
        }
    }

    private boolean checkServiceConnectState() {
        if (this.systemSettings != null && isServiceConnected) {
            return true;
        }
        Log.e(TAG, MSG_SERVICE_DISCONNECTED);
        bindService(this.appContext);
        return false;
    }

    public int callMethod(String method) {
        Log.i(TAG, "----callMethod---method: " + method + ", systemSettings: " + this.systemSettings);
        ISystemSettings iSystemSettings = this.systemSettings;
        if (iSystemSettings == null) {
            return -1;
        }
        try {
            invoke(iSystemSettings, method);
            return 0;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    private Object invoke(Object obj, String methodName) {
        try {
            Method method = obj.getClass().getMethod(methodName, new Class[0]);
            method.setAccessible(true);
            return method.invoke(obj, new Object[0]);
        } catch (Exception e) {
            Log.e(TAG, ", invoke e: " + e.toString());
            return null;
        }
    }

    public void doAction(String action, String params) {
        Log.i(TAG, "---doAction---action: " + action + ", params: " + params);
        if (!supportSystemSettings(action)) {
            doActionOnOldApi(action, params);
        } else if (checkServiceConnectState()) {
            try {
                this.systemSettings.doAction(action, params);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public boolean supportSystemSettings() {
        return this.ueApiVersion >= 1;
    }

    private boolean supportSystemSettings(String action) {
        if (!supportSystemSettings()) {
            return false;
        }
        for (String str : SUPPORT_SYSTEM_SETTINGS_ACTION_ARR) {
            if (str.equals(action)) {
                return true;
            }
        }
        return false;
    }

    public void showScreensaver() {
        Log.i(TAG, "---showScreensaver---");
        if (!supportSystemSettings()) {
            showScreensaverOnOldApi();
        } else if (checkServiceConnectState()) {
            try {
                this.systemSettings.showScreensaver();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void showBtSettings() {
        Log.i(TAG, "---showBtSettings---");
        if (!supportSystemSettings()) {
            showBtSettingsOnOldApi();
        } else if (checkServiceConnectState()) {
            try {
                this.systemSettings.showBtSettings();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void showWifiSettings() {
        Log.i(TAG, "---showWifiSettings---");
        if (!supportSystemSettings()) {
            showWifiSettingsOnOldApi();
        } else if (checkServiceConnectState()) {
            try {
                this.systemSettings.showWifiSettings();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void showHotspotSettings() {
        Log.i(TAG, "---showHotspotSettings---");
        if (!supportSystemSettings()) {
            showHotspotSettingsOnOldApi();
        } else if (checkServiceConnectState()) {
            try {
                this.systemSettings.showHotspotSettings();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void showCpaaSettings() {
        Log.i(TAG, "---showCpaaSettings---");
        if (!supportSystemSettings()) {
            showCpaaSettingsOnOldApi();
        } else if (checkServiceConnectState()) {
            try {
                this.systemSettings.showCpaaSettings();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void finishScreensaver() {
        Log.i(TAG, "---finishScreensaver---");
        if (!supportSystemSettings()) {
            finishScreensaverOnOldApi();
        } else if (checkServiceConnectState()) {
            try {
                this.systemSettings.finishScreensaver();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void dismissBtSettings() {
        Log.i(TAG, "---dismissBtSettings---");
        if (!supportSystemSettings()) {
            dismissBtSettingsOnOldApi();
        } else if (checkServiceConnectState()) {
            try {
                this.systemSettings.dismissBtSettings();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void dismissWifiSettings() {
        Log.i(TAG, "---dismissWifiSettings---");
        if (!supportSystemSettings()) {
            dismissWifiSettingsOnOldApi();
        } else if (checkServiceConnectState()) {
            try {
                this.systemSettings.dismissWifiSettings();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void dismissHotspotSettings() {
        Log.i(TAG, "---dismissHotspotSettings---");
        if (!supportSystemSettings()) {
            dismissHotspotSettingsOnOldApi();
        } else if (checkServiceConnectState()) {
            try {
                this.systemSettings.dismissHotspotSettings();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void dismissCpaaSettings() {
        Log.i(TAG, "---dismissCpaaSettings---");
        if (!supportSystemSettings()) {
            dismissCpaaSettingsOnOldApi();
        } else if (checkServiceConnectState()) {
            try {
                this.systemSettings.dismissCpaaSettings();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void dismissAllDialog() {
        if (!supportSystemSettings()) {
            dismissAllDialogOnOldApi();
        } else if (checkServiceConnectState()) {
            try {
                this.systemSettings.dismissAllDialog();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    public boolean hasHightLevelDialogShowing() {
        if (!supportSystemSettings()) {
            return this.hightLevelDialogShowing;
        }
        if (!checkServiceConnectState()) {
            return this.hightLevelDialogShowing;
        }
        try {
            return this.systemSettings.hasDialogShowing();
        } catch (RemoteException e) {
            e.printStackTrace();
            return false;
        }
    }

    private int doActionOnOldApi(String action, String params) {
        Log.i(TAG, "---doActionOnOldApi---action: " + action + ", params: " + params);
        if (TextUtils.isEmpty(action) || this.appContext == null) {
            return -1;
        }
        if (RESTORE_FACTORY_SETTINGS.equals(action)) {
            HashMap<String, String> map = new HashMap<>();
            map.put(KEY_PKG, this.appContext.getPackageName());
            return startServiceApi(RESTORE_FACTORY_SETTINGS, map);
        }
        return startServiceApi(action);
    }

    private int showScreensaverOnOldApi() {
        Log.i(TAG, "---showScreensaverOnOldApi---");
        return startServiceApi("screen_saver");
    }

    private int showBtSettingsOnOldApi() {
        Log.i(TAG, "---showBtSettingsOnOldApi---");
        return startServiceApi("Bluetooth");
    }

    private int showWifiSettingsOnOldApi() {
        Log.i(TAG, "---showWifiSettingsOnOldApi---");
        return startServiceApi("WiFi");
    }

    private int showHotspotSettingsOnOldApi() {
        Log.i(TAG, "---showHotspotSettingsOnOldApi---");
        return startServiceApi("Hotspot");
    }

    private int showCpaaSettingsOnOldApi() {
        Log.i(TAG, "---showCpaaSettingsOnOldApi---");
        return startServiceApi("car_play");
    }

    private int finishScreensaverOnOldApi() {
        Log.i(TAG, "---finishScreensaverOnOldApi---");
        return startServiceApi("finish_screen_saver");
    }

    private int dismissBtSettingsOnOldApi() {
        Log.i(TAG, "---dismissBtSettingsOnOldApi---");
        return 0;
    }

    private int dismissWifiSettingsOnOldApi() {
        Log.i(TAG, "---dismissWifiSettingsOnOldApi---");
        return 0;
    }

    private int dismissHotspotSettingsOnOldApi() {
        Log.i(TAG, "---dismissHotspotSettingsOnOldApi---");
        return 0;
    }

    private int dismissCpaaSettingsOnOldApi() {
        Log.i(TAG, "---dismissCpaaSettingsOnOldApi---");
        return 0;
    }

    private int dismissAllDialogOnOldApi() {
        Log.i(TAG, "---dismissAllDialogOnOldApi---");
        startServiceApi(DISMISS_ALL_DIALOG);
        return 0;
    }

    private int startServiceApi(String action) {
        return startServiceApi(action, null);
    }

    private int startServiceApi(String action, HashMap<String, String> map) {
        Context context = this.appContext;
        if (context == null) {
            return -1;
        }
        PackageManager packageManager = context.getPackageManager();
        StringBuffer stringBuffer = new StringBuffer("byd_pad://byd_car_settings:2933/launcher");
        stringBuffer.append("?").append("action").append("=").append(action);
        if (map != null && map.size() > 0) {
            for (String str : map.keySet()) {
                stringBuffer.append("&");
                stringBuffer.append(str).append("=").append(map.get(str));
            }
        }
        Log.d(TAG, "----startServiceApi----scheme = " + stringBuffer.toString());
        Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(stringBuffer.toString()));
        intent.setComponent(new ComponentName("com.byd.carsettings", "com.byd.dialogservice.DialogService"));
        List<ResolveInfo> listQueryIntentServices = packageManager.queryIntentServices(intent, 0);
        Log.d(TAG, "----startServiceApi----resolveInfos = " + listQueryIntentServices);
        if (listQueryIntentServices.isEmpty()) {
            return -1;
        }
        this.appContext.startService(intent);
        return 0;
    }

    public int getScreensaverState() {
        Context context = this.appContext;
        if (context == null) {
            Log.e(TAG, "---getScreensaverState---Context is null.");
            return -1;
        }
        return Settings.Global.getInt(context.getContentResolver(), "navibar_mode_on_screensaver", -1);
    }

    public void registerSystemSettingsCallback(ISystemSettingsCallback cb) {
        Log.i(TAG, "---registerSystemSettingsCallback---isServiceConnected: " + isServiceConnected + ", systemSettings: " + this.systemSettings);
        if (supportSystemSettings() && checkServiceConnectState()) {
            try {
                Log.i(TAG, "---registerSystemSettingsCallback---");
                this.systemSettings.registerSystemSettingsCallback(cb);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    public void unregisterSystemSettingsCallback(ISystemSettingsCallback cb) {
        if (supportSystemSettings() && checkServiceConnectState()) {
            try {
                Log.i(TAG, "---unregisterSystemSettingsCallback---");
                this.systemSettings.unregisterSystemSettingsCallback(cb);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    public void listenToScreensaverDeath(OnServiceDeathCallback callback) {
        this.deathProxy.setCallback(callback);
        if (supportSystemSettings()) {
            return;
        }
        listenToScreensaverDeathOnOldApi();
    }

    private void listenToScreensaverDeathOnOldApi() {
        if (this.appContext == null) {
            return;
        }
        Log.i(TAG, "---listenToScreensaverDeathOnOldApi---");
        Intent intent = new Intent("android.intent.action.VIEW", Uri.parse("byd_pad://byd_car_settings:2933/launcher?action=bindService"));
        intent.setComponent(new ComponentName("com.byd.carsettings", "com.byd.dialogservice.DialogService"));
        this.appContext.bindService(intent, this.connectionDeath, 1);
    }
}
