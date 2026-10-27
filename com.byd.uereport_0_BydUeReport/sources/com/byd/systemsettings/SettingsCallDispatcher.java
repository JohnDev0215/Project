package com.byd.systemsettings;

import android.content.BroadcastReceiver;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.os.Binder;
import android.os.RemoteException;
import android.provider.Settings;
import android.text.TextUtils;
import com.byd.systemsettings.ISystemSettings;
import com.byd.systemsettings.system.SystemSettingsModule;
import com.byd.systemsettings.system.callback.IAvmOperator;
import x3.a;

/* JADX INFO: loaded from: classes2.dex */
public final class SettingsCallDispatcher {
    public static final String ACTION = "com.byd.action.ACTION_BYD_SYSTEM_SETTING";
    public static final String ACTION_DISMISS_ALL_SETTINGS_DIALOG = "android.intent.byd.ACTION_DISMISS_ALL_SETTINGS_DIALOG";
    public static final String ACTION_HOME_KEY_EVENT = "action_home_key_event";
    public static final int API_SUPPORT_SYSTEM_SETTINGS = 1;
    public static final int API_UNSUPPORT = -1;
    public static final String DISMISS_ALL_DIALOG = "dismiss_all_dialog";
    private static final String TAG = "SettingsCallDispatcher";
    public static final String UE_API_VERSION = "UE_API_VERSION";
    private volatile boolean isInited;
    private Context mContext;
    private final BroadcastReceiver mHomeKeyEventReceiver;
    public ISystemSettings.Stub mSystemSettingsInterface;

    public static class Singleton {
        private static SettingsCallDispatcher instance = new SettingsCallDispatcher();

        public static SettingsCallDispatcher get() {
            return instance;
        }
    }

    private boolean checkUserSetup() {
        boolean zIsUserSetupCompleted = isUserSetupCompleted(this.mContext);
        a.a(TAG, "---checkUserSetup---userSetupCompleted: " + zIsUserSetupCompleted);
        return zIsUserSetupCompleted;
    }

    public static boolean isUserSetupCompleted(Context context) {
        int i8 = Settings.Secure.getInt(context.getContentResolver(), "user_setup_complete", 0);
        a.a(TAG, "---isUserSetupCompleted---value: " + i8);
        return i8 != 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void registerSystemSettingsCallback(ISystemSettingsCallback iSystemSettingsCallback) {
        a.a(TAG, "---registerSystemSettingsCallback---");
        SystemSettingsModule.getInstance().registerSystemSettingsCallback(iSystemSettingsCallback);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void unregisterSystemSettingsCallback(ISystemSettingsCallback iSystemSettingsCallback) {
        a.a(TAG, "---unregisterSystemSettingsCallback---");
        SystemSettingsModule.getInstance().unregisterSystemSettingsCallback(iSystemSettingsCallback);
    }

    public void dismissAllDialog() {
        a.a(TAG, "---dismissAllDialog---");
        SystemSettingsModule.getInstance().dismissAllDialog();
    }

    public void dismissBtSettings() {
        a.a(TAG, "---dismissBtSettings---");
        SystemSettingsModule.getInstance().dismissBtSettings();
    }

    public void dismissCpaaSettings() {
        a.a(TAG, "---dismissCpaaSettings---");
        SystemSettingsModule.getInstance().dismissCpaaSettings();
    }

    public void dismissHotspotSettings() {
        a.a(TAG, "---dismissHotspotSettings---");
        SystemSettingsModule.getInstance().dismissHotspotSettings();
    }

    public void dismissWifiSettings() {
        a.a(TAG, "---dismissWifiSettings---");
        SystemSettingsModule.getInstance().dismissWifiSettings();
    }

    public void doAction(String str, String str2) {
        a.a(TAG, "---doAction---action: " + str + ", params: " + str2);
        if (checkUserSetup()) {
            SystemSettingsModule.getInstance().doAction(str, str2);
        }
    }

    public void finishScreensaver() {
        a.a(TAG, "---finishScreensaver---");
        SystemSettingsModule.getInstance().finishScreensaverOrScreenOff(false);
    }

    public void finishScreensaverOrScreenOff(boolean z7) {
        if (checkUserSetup()) {
            a.a(TAG, "---finishScreensaverOrScreenOff---fromAvm: " + z7);
            SystemSettingsModule.getInstance().finishScreensaverOrScreenOff(z7);
        }
    }

    public ISystemSettings.Stub getSystemSettingsInterface() {
        return this.mSystemSettingsInterface;
    }

    public boolean hasDialogShowing() {
        a.a(TAG, "---dismissAllDialog---");
        return SystemSettingsModule.getInstance().hasDialogShowing();
    }

    public void init(Context context) {
        if (context == null || this.isInited) {
            return;
        }
        this.isInited = true;
        a.a(TAG, "----init-----");
        this.mContext = context.getApplicationContext();
        SystemSettingsModule.getInstance().init(this.mContext);
        IntentFilter intentFilter = new IntentFilter("android.intent.action.CLOSE_SYSTEM_DIALOGS");
        intentFilter.addAction(ACTION_DISMISS_ALL_SETTINGS_DIALOG);
        context.registerReceiver(this.mHomeKeyEventReceiver, intentFilter);
        updateApiVersion(this.mContext, 1);
    }

    public void onConfigurationChanged(Configuration configuration) {
        SystemSettingsModule.getInstance().onConfigurationChanged(configuration);
    }

    public void onCpaaDisplay() {
        a.a(TAG, "---onCpaaDisplay---");
    }

    public void onServiceDestroy() {
        BroadcastReceiver broadcastReceiver;
        a.a(TAG, "---onServiceDestroy---");
        Context context = this.mContext;
        if (context != null && (broadcastReceiver = this.mHomeKeyEventReceiver) != null) {
            context.unregisterReceiver(broadcastReceiver);
        }
        SystemSettingsModule.getInstance().onServiceDestroy();
        this.mContext = null;
        this.isInited = false;
    }

    public void registerIAvmOperator(IAvmOperator iAvmOperator) {
        SystemSettingsModule.getInstance().registerIAvmOperator(iAvmOperator);
    }

    public void showBtSettings() {
        if (checkUserSetup()) {
            a.a(TAG, "---showBtSettings---");
            SystemSettingsModule.getInstance().showBtSettings();
        }
    }

    public void showCpaaSettings() {
        if (checkUserSetup()) {
            a.a(TAG, "---showCpaaSettings---");
            SystemSettingsModule.getInstance().showCpaaSettings();
        }
    }

    public void showHotspotSettings() {
        if (checkUserSetup()) {
            a.a(TAG, "---showHotspotSettings---");
            SystemSettingsModule.getInstance().showHotspotSettings();
        }
    }

    public void showScreensaver() {
        if (checkUserSetup()) {
            a.a(TAG, "---showScreensaver---");
            SystemSettingsModule.getInstance().showScreensaverOrScreenOff(false);
        }
    }

    public void showScreensaverOrScreenOff(boolean z7) {
        if (checkUserSetup()) {
            a.a(TAG, "---showScreensaverOrScreenOff---fromAvm: " + z7);
            SystemSettingsModule.getInstance().showScreensaverOrScreenOff(z7);
        }
    }

    public void showWifiSettings() {
        if (checkUserSetup()) {
            a.a(TAG, "---showWifiSettings---");
            SystemSettingsModule.getInstance().showWifiSettings();
        }
    }

    public void updateApiVersion(Context context, int i8) {
        if (context == null) {
            return;
        }
        ContentResolver contentResolver = context.getContentResolver();
        if (Settings.System.getInt(contentResolver, UE_API_VERSION, 0) >= i8) {
            return;
        }
        Settings.System.putInt(contentResolver, UE_API_VERSION, i8);
    }

    private SettingsCallDispatcher() {
        this.isInited = false;
        this.mHomeKeyEventReceiver = new BroadcastReceiver() { // from class: com.byd.systemsettings.SettingsCallDispatcher.1
            private static final String SYSTEM_HOME_KEY = "homekey";
            private static final String SYSTEM_HOME_KEY_LONG = "recentapps";
            private static final String SYSTEM_REASON = "reason";

            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context, Intent intent) {
                String action = intent.getAction();
                a.a(SettingsCallDispatcher.TAG, ", ---onReceive---action: " + action);
                if (SettingsCallDispatcher.ACTION_DISMISS_ALL_SETTINGS_DIALOG.equals(action)) {
                    Singleton.get().dismissAllDialog();
                    return;
                }
                if ("android.intent.action.CLOSE_SYSTEM_DIALOGS".equals(action)) {
                    String stringExtra = intent.getStringExtra(SYSTEM_REASON);
                    a.a(SettingsCallDispatcher.TAG, ", ---onReceive---reason: " + stringExtra);
                    if (TextUtils.equals(stringExtra, SYSTEM_HOME_KEY)) {
                        Singleton.get().dismissAllDialog();
                    }
                }
            }
        };
        this.mSystemSettingsInterface = new ISystemSettings.Stub() { // from class: com.byd.systemsettings.SettingsCallDispatcher.2
            private void printCallerInfo(String str) {
                if (Singleton.get().mContext == null) {
                    return;
                }
                PackageManager packageManager = Singleton.get().mContext.getPackageManager();
                a.a(SettingsCallDispatcher.TAG, "--printCallerInfo--method: " + str + ", callerPid: " + Binder.getCallingPid() + ", caller: " + packageManager.getNameForUid(Binder.getCallingUid()));
            }

            @Override // com.byd.systemsettings.ISystemSettings
            public void dismissAllDialog() throws RemoteException {
                printCallerInfo("dismissAllDialog");
                Singleton.get().dismissAllDialog();
            }

            @Override // com.byd.systemsettings.ISystemSettings
            public void dismissBtSettings() throws RemoteException {
                printCallerInfo("dismissBtSettings");
                Singleton.get().dismissBtSettings();
            }

            @Override // com.byd.systemsettings.ISystemSettings
            public void dismissCpaaSettings() throws RemoteException {
                printCallerInfo("dismissCpaaSettings");
                Singleton.get().dismissCpaaSettings();
            }

            @Override // com.byd.systemsettings.ISystemSettings
            public void dismissHotspotSettings() throws RemoteException {
                printCallerInfo("dismissHotspotSettings");
                Singleton.get().dismissHotspotSettings();
            }

            @Override // com.byd.systemsettings.ISystemSettings
            public void dismissWifiSettings() throws RemoteException {
                printCallerInfo("dismissWifiSettings");
                Singleton.get().dismissWifiSettings();
            }

            @Override // com.byd.systemsettings.ISystemSettings
            public void doAction(String str, String str2) throws RemoteException {
                printCallerInfo("doAction");
                Singleton.get().doAction(str, str2);
            }

            @Override // com.byd.systemsettings.ISystemSettings
            public void finishScreensaver() throws RemoteException {
                printCallerInfo("finishScreensaver");
                Singleton.get().finishScreensaver();
            }

            @Override // com.byd.systemsettings.ISystemSettings
            public boolean hasDialogShowing() throws RemoteException {
                printCallerInfo("hasDialogShowing");
                return Singleton.get().hasDialogShowing();
            }

            @Override // com.byd.systemsettings.ISystemSettings
            public void registerSystemSettingsCallback(ISystemSettingsCallback iSystemSettingsCallback) throws RemoteException {
                printCallerInfo("registerSystemSettingsCallback");
                Singleton.get().registerSystemSettingsCallback(iSystemSettingsCallback);
            }

            @Override // com.byd.systemsettings.ISystemSettings
            public void showBtSettings() throws RemoteException {
                printCallerInfo("showBtSettings");
                Singleton.get().showBtSettings();
            }

            @Override // com.byd.systemsettings.ISystemSettings
            public void showCpaaSettings() throws RemoteException {
                printCallerInfo("showCpaaSettings");
                Singleton.get().showCpaaSettings();
            }

            @Override // com.byd.systemsettings.ISystemSettings
            public void showHotspotSettings() throws RemoteException {
                printCallerInfo("showHotspotSettings");
                Singleton.get().showHotspotSettings();
            }

            @Override // com.byd.systemsettings.ISystemSettings
            public void showScreensaver() throws RemoteException {
                printCallerInfo("showScreensaver");
                Singleton.get().showScreensaver();
            }

            @Override // com.byd.systemsettings.ISystemSettings
            public void showWifiSettings() throws RemoteException {
                printCallerInfo("showWifiSettings");
                Singleton.get().showWifiSettings();
            }

            @Override // com.byd.systemsettings.ISystemSettings
            public void unregisterSystemSettingsCallback(ISystemSettingsCallback iSystemSettingsCallback) throws RemoteException {
                printCallerInfo("unregisterSystemSettingsCallback");
                Singleton.get().unregisterSystemSettingsCallback(iSystemSettingsCallback);
            }
        };
    }
}
