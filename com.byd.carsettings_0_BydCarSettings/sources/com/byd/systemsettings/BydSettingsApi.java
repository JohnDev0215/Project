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
import com.byd.systemsettings.model.ApiMethod;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class BydSettingsApi {
    private static final String ACTION = "action";
    public static final String ACTION_BYD_SYSTEM_SETTING = "com.byd.action.ACTION_BYD_SYSTEM_SETTING";
    private static final String ACTIVITY = "activity";
    private static final int API_SUPPORT_SYSTEM_SETTINGS = 1;
    private static final int API_UNSUPPORT = 0;
    private static final int CALLS_DISCONNECTED_MAX = 10;
    public static final String CAR_SETTING = "car_setting";
    public static final String CELLULAR_DATA = "cellular_data";
    public static final String CPAA_DEVICE_LIST = "cpaa_device_list";
    public static final String DILINK_SETTING = "dilink_setting";
    public static final String DILINK_VERSION_MANAGE = "dilink_version_manage";
    private static final String KEY_PKG = "pkg";
    public static final String MAIN = "Main";
    private static final String MSG_SERVICE_DISCONNECTED = "BydSettingsApi service is disconnected ,and doing reconnect.";
    public static final int NAVIBAR_MODE_NORMAL = 1;
    public static final String NAVIBAR_MODE_ON_SCREENSAVER = "navibar_mode_on_screensaver";
    public static final int NAVIBAR_MODE_POWER_OFF = 0;
    public static final int NAVIBAR_MODE_SCREENSAVER = 2;
    public static final String NOTIFICATION_APP_LIST = "notification_app_list";
    public static final String RESTORE_FACTORY_SETTINGS = "restore_factory_settings";
    public static final String SELF_START = "self_start";
    private static final String SERVICE_CLASS = "com.byd.dialogservice.DialogService";
    public static final String SOUND_SETTINGS = "sound_settings";
    private static final String SYSTEM = "system";
    private static final String SYSTEM_SERVICE_CLASS = "com.byd.uereport.MultiModuleService";
    private static final String SYSTEM_SERVICE_PKG = "com.byd.uereport";
    public static final String SYS_LANGUAGE_SETTINGS = "sys_language_settings";
    private static final String TAG = "BydSettingsApi";
    private static final String UE_API_VERSION = "UE_API_VERSION";
    private OnServiceConnectCallback mConnectCallback;
    private ISystemSettings systemSettings;
    private static final String SERVICE_PKG = "com.byd.carsettings";
    private static final String[] SYSTEM_PKG_ARR = {SERVICE_PKG, "com.byd.bluetoothcall", "com.android.systemui", "com.byd.localmusic"};
    public static final String SCREEN_SAVER = "screen_saver";
    public static final String FINISH_SCREEN_SAVER = "finish_screen_saver";
    public static final String BLUETOOTH = "Bluetooth";
    public static final String DISMISS_BT_SETTINGS = "dismiss_bt_settings";
    public static final String WIFI = "WiFi";
    public static final String DISMISS_WIFI_SETTINGS = "dismiss_wifi_settings";
    public static final String HOTSPOT = "Hotspot";
    public static final String DISMISS_HOTSPOT_SETTINGS = "dismiss_hotspot_settings";
    public static final String CAR_PLAY = "car_play";
    public static final String DISMISS_CARPLAY = "dismiss_carplay";
    public static final String CPAA = "cpaa";
    public static final String DISMISS_CPAA_SETTINGS = "dismiss_cpaa_settings";
    public static final String PRIVACY_MANAGE = "privacy_manage";
    public static final String DISMISS_ALL_DIALOG = "dismiss_all_dialog";
    public static final String[] SUPPORT_SYSTEM_SETTINGS_ACTION_ARR = {SCREEN_SAVER, FINISH_SCREEN_SAVER, BLUETOOTH, DISMISS_BT_SETTINGS, WIFI, DISMISS_WIFI_SETTINGS, HOTSPOT, DISMISS_HOTSPOT_SETTINGS, CAR_PLAY, DISMISS_CARPLAY, CPAA, DISMISS_CPAA_SETTINGS, PRIVACY_MANAGE, DISMISS_ALL_DIALOG};
    private static volatile BydSettingsApi instance = null;
    private static volatile boolean isServiceConnected = false;
    private volatile boolean hightLevelDialogShowing = false;
    private int ueApiVersion = 0;
    private Context appContext = null;
    private LinkedList<ApiMethod> callsDisconnected = new LinkedList<>();
    private DeathRecipientCallback deathProxy = new DeathRecipientCallback();
    private ServiceConnection connection = new ServiceConnection() { // from class: com.byd.systemsettings.BydSettingsApi.1
        @Override // android.content.ServiceConnection
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            boolean unused = BydSettingsApi.isServiceConnected = true;
            Log.i(BydSettingsApi.TAG, "---onServiceConnected---name: " + componentName + ", service: " + iBinder);
            BydSettingsApi.this.systemSettings = ISystemSettings.Stub.asInterface(iBinder);
            if (BydSettingsApi.this.mConnectCallback != null) {
                BydSettingsApi.this.mConnectCallback.onServiceConnectChanged(true);
            }
            if (BydSettingsApi.this.callsDisconnected != null && !BydSettingsApi.this.callsDisconnected.isEmpty()) {
                Log.w(BydSettingsApi.TAG, "---onServiceConnected---callsDisconnected.size: " + BydSettingsApi.this.callsDisconnected.size());
                for (int i = 0; i < 10; i++) {
                    ApiMethod apiMethod = (ApiMethod) BydSettingsApi.this.callsDisconnected.poll();
                    Log.w(BydSettingsApi.TAG, "---onServiceConnected---call: " + apiMethod);
                    if (apiMethod == null) {
                        break;
                    }
                    BydSettingsApi.this.invokeApiCall(apiMethod);
                }
                Log.w(BydSettingsApi.TAG, "---onServiceConnected--recall completed. callsDisconnected.size: " + BydSettingsApi.this.callsDisconnected.size());
            }
            try {
                BydSettingsApi.this.deathProxy.setServiceName(componentName.toString());
                iBinder.linkToDeath(BydSettingsApi.this.deathProxy, 0);
            } catch (RemoteException e2) {
                e2.printStackTrace();
                Log.e(BydSettingsApi.TAG, "---onServiceConnected---e: " + e2.toString());
            }
        }

        @Override // android.content.ServiceConnection
        public void onServiceDisconnected(ComponentName componentName) {
            boolean unused = BydSettingsApi.isServiceConnected = false;
            Log.e(BydSettingsApi.TAG, "---onServiceDisconnected---name: " + componentName);
            if (BydSettingsApi.this.mConnectCallback != null) {
                BydSettingsApi.this.mConnectCallback.onServiceConnectChanged(false);
            }
        }
    };
    private ServiceConnection connectionDeath = new ServiceConnection() { // from class: com.byd.systemsettings.BydSettingsApi.2
        @Override // android.content.ServiceConnection
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            Log.i(BydSettingsApi.TAG, "---onServiceConnected---service: " + iBinder);
            try {
                BydSettingsApi.this.deathProxy.setServiceName(componentName.toString());
                iBinder.linkToDeath(BydSettingsApi.this.deathProxy, 0);
            } catch (RemoteException e2) {
                e2.printStackTrace();
                Log.e(BydSettingsApi.TAG, "---onServiceConnected---e: " + e2.toString());
            }
        }

        @Override // android.content.ServiceConnection
        public void onServiceDisconnected(ComponentName componentName) {
            Log.i(BydSettingsApi.TAG, "---onServiceDisconnected---name: " + componentName);
        }
    };

    public interface OnServiceConnectCallback {
        void onServiceConnectChanged(boolean z);
    }

    private BydSettingsApi() {
    }

    private boolean checkServiceConnectState() {
        if (this.systemSettings != null && isServiceConnected) {
            return true;
        }
        Log.e(TAG, MSG_SERVICE_DISCONNECTED);
        bindService(this.appContext);
        return false;
    }

    private boolean checkSystemApp(Context context) {
        if (context == null) {
            return false;
        }
        for (String str : SYSTEM_PKG_ARR) {
            if (str.equals(context.getPackageName())) {
                return true;
            }
        }
        return false;
    }

    private int dismissAllDialogOnOldApi() {
        Log.i(TAG, "---dismissAllDialogOnOldApi---");
        startServiceApi(DISMISS_ALL_DIALOG);
        return 0;
    }

    private int dismissBtSettingsOnOldApi() {
        Log.i(TAG, "---dismissBtSettingsOnOldApi---");
        return 0;
    }

    private int dismissCpaaSettingsOnOldApi() {
        Log.i(TAG, "---dismissCpaaSettingsOnOldApi---");
        return 0;
    }

    private int dismissHotspotSettingsOnOldApi() {
        Log.i(TAG, "---dismissHotspotSettingsOnOldApi---");
        return 0;
    }

    private int dismissWifiSettingsOnOldApi() {
        Log.i(TAG, "---dismissWifiSettingsOnOldApi---");
        return 0;
    }

    private int doActionOnOldApi(String str, String str2) {
        Log.i(TAG, "---doActionOnOldApi---action: " + str + ", params: " + str2);
        if (TextUtils.isEmpty(str) || this.appContext == null) {
            return -1;
        }
        if (!RESTORE_FACTORY_SETTINGS.equals(str)) {
            return startServiceApi(str);
        }
        HashMap<String, String> map = new HashMap<>();
        map.put(KEY_PKG, this.appContext.getPackageName());
        return startServiceApi(RESTORE_FACTORY_SETTINGS, map);
    }

    private int finishScreensaverOnOldApi() {
        Log.i(TAG, "---finishScreensaverOnOldApi---");
        return startServiceApi(FINISH_SCREEN_SAVER);
    }

    public static BydSettingsApi getApi(Context context, OnServiceConnectCallback onServiceConnectCallback) {
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
        instance.mConnectCallback = onServiceConnectCallback;
        return instance;
    }

    private Object invoke(Object obj, String str) {
        try {
            Method method = obj.getClass().getMethod(str, new Class[0]);
            method.setAccessible(true);
            return method.invoke(obj, new Object[0]);
        } catch (Exception e2) {
            Log.e(TAG, ", invoke e: " + e2.toString());
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void invokeApiCall(ApiMethod apiMethod) {
        if (apiMethod == null || apiMethod.obj == null || TextUtils.isEmpty(apiMethod.methodName)) {
            return;
        }
        Object obj = apiMethod.obj;
        String str = apiMethod.methodName;
        Class<?>[] clsArr = apiMethod.paramClassArr;
        Object[] objArr = apiMethod.paramArr;
        if (clsArr == null || clsArr.length <= 0 || objArr == null || objArr.length != clsArr.length) {
            invoke(obj, str, clsArr, objArr);
        } else {
            invoke(obj, str);
        }
    }

    private void listenToScreensaverDeathOnOldApi() {
        if (this.appContext == null) {
            return;
        }
        Log.i(TAG, "---listenToScreensaverDeathOnOldApi---");
        Intent intent = new Intent("android.intent.action.VIEW", Uri.parse("byd_pad://byd_car_settings:2933/launcher?action=bindService"));
        intent.setComponent(new ComponentName(SERVICE_PKG, SERVICE_CLASS));
        this.appContext.bindService(intent, this.connectionDeath, 1);
    }

    private int showBtSettingsOnOldApi() {
        Log.i(TAG, "---showBtSettingsOnOldApi---");
        return startServiceApi(BLUETOOTH);
    }

    private int showCpaaSettingsOnOldApi() {
        Log.i(TAG, "---showCpaaSettingsOnOldApi---");
        return startServiceApi(CAR_PLAY);
    }

    private int showHotspotSettingsOnOldApi() {
        Log.i(TAG, "---showHotspotSettingsOnOldApi---");
        return startServiceApi(HOTSPOT);
    }

    private int showScreensaverOnOldApi() {
        Log.i(TAG, "---showScreensaverOnOldApi---");
        return startServiceApi(SCREEN_SAVER);
    }

    private int showWifiSettingsOnOldApi() {
        Log.i(TAG, "---showWifiSettingsOnOldApi---");
        return startServiceApi(WIFI);
    }

    private int startServiceApi(String str) {
        return startServiceApi(str, null);
    }

    public void bindService(Context context) {
        Log.i(TAG, "---bindService---isServiceConnected: " + isServiceConnected);
        if (context == null || isServiceConnected) {
            return;
        }
        this.appContext = context.getApplicationContext();
        Intent intent = new Intent();
        intent.setAction(ACTION_BYD_SYSTEM_SETTING);
        intent.setComponent(new ComponentName(SYSTEM_SERVICE_PKG, SYSTEM_SERVICE_CLASS));
        boolean zCheckSystemApp = checkSystemApp(context);
        Log.i(TAG, "---bindService---systemApp: " + zCheckSystemApp);
        if (zCheckSystemApp) {
            Parcel parcelObtain = Parcel.obtain();
            Class<?>[] clsArr = {Intent.class, ServiceConnection.class, Integer.TYPE, UserHandle.class};
            Object[] objArr = {intent, this.connection, 1, UserHandle.readFromParcel(parcelObtain)};
            try {
                Method method = context.getClass().getMethod("bindServiceAsUser", clsArr);
                method.setAccessible(true);
                method.invoke(context, objArr);
            } catch (IllegalAccessException | NoSuchMethodException | InvocationTargetException e2) {
                Log.e(TAG, ", Invoke param arr failed : " + e2.toString());
                Log.i(TAG, "---do as normal bindService---");
                context.bindService(intent, this.connection, 1);
            }
            parcelObtain.recycle();
        } else {
            context.bindService(intent, this.connection, 1);
        }
        this.ueApiVersion = getApiVersion(context);
        Log.i(TAG, "---bindService---ueApiVersion: " + this.ueApiVersion);
    }

    public int callMethod(String str) {
        Log.i(TAG, "----callMethod---method: " + str + ", systemSettings: " + this.systemSettings);
        ISystemSettings iSystemSettings = this.systemSettings;
        if (iSystemSettings == null) {
            return -1;
        }
        try {
            invoke(iSystemSettings, str);
            return 0;
        } catch (Exception e2) {
            e2.printStackTrace();
            return -1;
        }
    }

    public void dismissAllDialog() {
        if (!supportSystemSettings()) {
            dismissAllDialogOnOldApi();
            return;
        }
        if (!checkServiceConnectState()) {
            this.callsDisconnected.offer(new ApiMethod(this, "dismissAllDialog", null));
        } else {
            try {
                this.systemSettings.dismissAllDialog();
            } catch (RemoteException e2) {
                e2.printStackTrace();
            }
        }
    }

    public void dismissBtSettings() {
        Log.i(TAG, "---dismissBtSettings---");
        if (!supportSystemSettings()) {
            dismissBtSettingsOnOldApi();
            return;
        }
        if (!checkServiceConnectState()) {
            this.callsDisconnected.offer(new ApiMethod(this, "dismissBtSettings", null));
        } else {
            try {
                this.systemSettings.dismissBtSettings();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    public void dismissCpaaSettings() {
        Log.i(TAG, "---dismissCpaaSettings---");
        if (!supportSystemSettings()) {
            dismissCpaaSettingsOnOldApi();
            return;
        }
        if (!checkServiceConnectState()) {
            this.callsDisconnected.offer(new ApiMethod(this, "dismissCpaaSettings", null));
        } else {
            try {
                this.systemSettings.dismissCpaaSettings();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    public void dismissHotspotSettings() {
        Log.i(TAG, "---dismissHotspotSettings---");
        if (!supportSystemSettings()) {
            dismissHotspotSettingsOnOldApi();
            return;
        }
        if (!checkServiceConnectState()) {
            this.callsDisconnected.offer(new ApiMethod(this, "dismissHotspotSettings", null));
        } else {
            try {
                this.systemSettings.dismissHotspotSettings();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    public void dismissWifiSettings() {
        Log.i(TAG, "---dismissWifiSettings---");
        if (!supportSystemSettings()) {
            dismissWifiSettingsOnOldApi();
            return;
        }
        if (!checkServiceConnectState()) {
            this.callsDisconnected.offer(new ApiMethod(this, "dismissWifiSettings", null));
        } else {
            try {
                this.systemSettings.dismissWifiSettings();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    public void doAction(String str, String str2) {
        Log.i(TAG, "---doAction---action: " + str + ", params: " + str2);
        if (!supportSystemSettings(str)) {
            doActionOnOldApi(str, str2);
            return;
        }
        if (!checkServiceConnectState()) {
            this.callsDisconnected.offer(new ApiMethod(this, "doAction", new Object[]{str, str2}));
            return;
        }
        try {
            this.systemSettings.doAction(str, str2);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public void finishScreensaver() {
        Log.i(TAG, "---finishScreensaver---");
        if (!supportSystemSettings()) {
            finishScreensaverOnOldApi();
            return;
        }
        if (!checkServiceConnectState()) {
            this.callsDisconnected.offer(new ApiMethod(this, "finishScreensaver", null));
        } else {
            try {
                this.systemSettings.finishScreensaver();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    public int getApiVersion(Context context) {
        if (context == null) {
            return 0;
        }
        return Settings.System.getInt(context.getContentResolver(), UE_API_VERSION, 0);
    }

    public int getScreensaverState() {
        Context context = this.appContext;
        if (context != null) {
            return Settings.Global.getInt(context.getContentResolver(), NAVIBAR_MODE_ON_SCREENSAVER, -1);
        }
        Log.e(TAG, "---getScreensaverState---Context is null.");
        return -1;
    }

    public boolean hasHightLevelDialogShowing() {
        if (!supportSystemSettings()) {
            return this.hightLevelDialogShowing;
        }
        if (!checkServiceConnectState()) {
            this.callsDisconnected.offer(new ApiMethod(this, "hasHightLevelDialogShowing", null));
            return this.hightLevelDialogShowing;
        }
        try {
            return this.systemSettings.hasDialogShowing();
        } catch (RemoteException e2) {
            e2.printStackTrace();
            return false;
        }
    }

    public void listenToScreensaverDeath(OnServiceDeathCallback onServiceDeathCallback) {
        this.deathProxy.setCallback(onServiceDeathCallback);
        if (supportSystemSettings()) {
            return;
        }
        listenToScreensaverDeathOnOldApi();
    }

    public void registerSystemSettingsCallback(ISystemSettingsCallback iSystemSettingsCallback) {
        Log.i(TAG, "---registerSystemSettingsCallback---isServiceConnected: " + isServiceConnected + ", systemSettings: " + this.systemSettings);
        if (supportSystemSettings()) {
            if (!checkServiceConnectState()) {
                this.callsDisconnected.offer(new ApiMethod(this, "registerSystemSettingsCallback", null));
                return;
            }
            try {
                Log.i(TAG, "---registerSystemSettingsCallback---");
                this.systemSettings.registerSystemSettingsCallback(iSystemSettingsCallback);
            } catch (RemoteException e2) {
                e2.printStackTrace();
            }
        }
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
            } catch (RuntimeException e2) {
                Log.e(TAG, "----release---e: " + e2);
            }
        }
    }

    public void setHightLevelDialogShowing(boolean z) {
        this.hightLevelDialogShowing = z;
    }

    public void showBtSettings() {
        Log.i(TAG, "---showBtSettings---");
        if (!supportSystemSettings()) {
            showBtSettingsOnOldApi();
            return;
        }
        if (!checkServiceConnectState()) {
            this.callsDisconnected.offer(new ApiMethod(this, "showBtSettings", null));
        } else {
            try {
                this.systemSettings.showBtSettings();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    public void showCpaaSettings() {
        Log.i(TAG, "---showCpaaSettings---");
        if (!supportSystemSettings()) {
            showCpaaSettingsOnOldApi();
            return;
        }
        if (!checkServiceConnectState()) {
            this.callsDisconnected.offer(new ApiMethod(this, "showCpaaSettings", null));
        } else {
            try {
                this.systemSettings.showCpaaSettings();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    public void showHotspotSettings() {
        Log.i(TAG, "---showHotspotSettings---");
        if (!supportSystemSettings()) {
            showHotspotSettingsOnOldApi();
            return;
        }
        if (!checkServiceConnectState()) {
            this.callsDisconnected.offer(new ApiMethod(this, "showHotspotSettings", null));
        } else {
            try {
                this.systemSettings.showHotspotSettings();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    public void showScreensaver() {
        Log.i(TAG, "---showScreensaver---");
        if (!supportSystemSettings()) {
            showScreensaverOnOldApi();
            return;
        }
        if (!checkServiceConnectState()) {
            this.callsDisconnected.offer(new ApiMethod(this, "showScreensaver", null));
        } else {
            try {
                this.systemSettings.showScreensaver();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    public void showWifiSettings() {
        Log.i(TAG, "---showWifiSettings---");
        if (!supportSystemSettings()) {
            showWifiSettingsOnOldApi();
            return;
        }
        if (!checkServiceConnectState()) {
            this.callsDisconnected.offer(new ApiMethod(this, "showWifiSettings", null));
        } else {
            try {
                this.systemSettings.showWifiSettings();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    public boolean supportSystemSettings() {
        return this.ueApiVersion >= 1;
    }

    public void unregisterSystemSettingsCallback(ISystemSettingsCallback iSystemSettingsCallback) {
        if (supportSystemSettings()) {
            if (!checkServiceConnectState()) {
                this.callsDisconnected.offer(new ApiMethod(this, "unregisterSystemSettingsCallback", null));
                return;
            }
            try {
                Log.i(TAG, "---unregisterSystemSettingsCallback---");
                this.systemSettings.unregisterSystemSettingsCallback(iSystemSettingsCallback);
            } catch (RemoteException e2) {
                e2.printStackTrace();
            }
        }
    }

    private int startServiceApi(String str, HashMap<String, String> map) {
        Context context = this.appContext;
        if (context == null) {
            return -1;
        }
        PackageManager packageManager = context.getPackageManager();
        StringBuffer stringBuffer = new StringBuffer("byd_pad://byd_car_settings:2933/launcher");
        stringBuffer.append("?");
        stringBuffer.append(ACTION);
        stringBuffer.append("=");
        stringBuffer.append(str);
        if (map != null && map.size() > 0) {
            for (String str2 : map.keySet()) {
                stringBuffer.append("&");
                stringBuffer.append(str2);
                stringBuffer.append("=");
                stringBuffer.append(map.get(str2));
            }
        }
        Log.d(TAG, "----startServiceApi----scheme = " + stringBuffer.toString());
        Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(stringBuffer.toString()));
        intent.setComponent(new ComponentName(SERVICE_PKG, SERVICE_CLASS));
        List<ResolveInfo> listQueryIntentServices = packageManager.queryIntentServices(intent, 0);
        Log.d(TAG, "----startServiceApi----resolveInfos = " + listQueryIntentServices);
        if (listQueryIntentServices.isEmpty()) {
            return -1;
        }
        this.appContext.startService(intent);
        return 0;
    }

    private boolean supportSystemSettings(String str) {
        if (!supportSystemSettings()) {
            return false;
        }
        for (String str2 : SUPPORT_SYSTEM_SETTINGS_ACTION_ARR) {
            if (str2.equals(str)) {
                return true;
            }
        }
        return false;
    }

    public static Object invoke(Object obj, String str, Class<?>[] clsArr, Object[] objArr) {
        try {
            Method method = obj.getClass().getMethod(str, clsArr);
            method.setAccessible(true);
            return method.invoke(obj, objArr);
        } catch (IllegalAccessException | NoSuchMethodException | InvocationTargetException e2) {
            Log.e(TAG, ", Invoke param arr failed : " + e2.toString());
            return null;
        }
    }
}
