package com.byd.systemsettings.system;

import android.content.Context;
import android.content.res.Configuration;
import android.os.RemoteException;
import android.text.TextUtils;
import com.byd.systemsettings.ISystemSettingsCallback;
import com.byd.systemsettings.common.Fields;
import com.byd.systemsettings.system.BydPowerManagerDevice;
import com.byd.systemsettings.system.BydSystemSettings;
import com.byd.systemsettings.system.callback.IAvmOperator;
import com.byd.systemsettings.system.callback.ICallDispatcher;
import com.byd.systemsettings.system.dm.BydDeviceManager;
import com.byd.systemsettings.system.model.ClockStyle;
import com.byd.systemsettings.system.utils.BydThemeConfig;
import com.byd.systemsettings.system.utils.CarIdentifier;
import com.byd.systemsettings.ui.dialog.SystemDialog;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import m4.f;
import p2.g;
import x3.a;

/* JADX INFO: loaded from: classes2.dex */
public final class SystemSettingsModule implements ICallDispatcher, IAvmOperator {
    public static final String BLUETOOTH = "Bluetooth";
    public static final String CAR_PLAY = "car_play";
    public static final String CPAA = "cpaa";
    public static final String CPAA_DEVICE_LIST = "cpaa_device_list";
    public static final String DISMISS_ALL_DIALOG = "dismiss_all_dialog";
    public static final String DISMISS_BT_SETTINGS = "dismiss_bt_settings";
    public static final String DISMISS_CARPLAY = "dismiss_carplay";
    public static final String DISMISS_CPAA_SETTINGS = "dismiss_cpaa_settings";
    public static final String DISMISS_HOTSPOT_SETTINGS = "dismiss_hotspot_settings";
    public static final String DISMISS_WIFI_SETTINGS = "dismiss_wifi_settings";
    public static final String FINISH_SCREEN_SAVER = "finish_screen_saver";
    public static final String HOTSPOT = "Hotspot";
    public static final String NOTIFICATION_APP_LIST = "notification_app_list";
    public static final String PRIVACY_MANAGE = "privacy_manage";
    public static final String SCREEN_SAVER = "screen_saver";
    private static final String TAG = "SystemSettingsModule";
    public static final String WIFI = "WiFi";
    private static volatile SystemSettingsModule instance;
    private static Context mContext;
    private IAvmOperator avmOperator;
    private List<ISystemSettingsCallback> callbackList = new ArrayList();
    private volatile boolean isShowScreensaverFromAvm = false;
    private volatile boolean isFinishScreensaverFromAvm = false;

    private SystemSettingsModule() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void callOnDismissAllDialogCompleted() {
        List<ISystemSettingsCallback> list = this.callbackList;
        if (list == null || list.isEmpty()) {
            return;
        }
        Iterator it = new ArrayList(this.callbackList).iterator();
        while (it.hasNext()) {
            try {
                ((ISystemSettingsCallback) it.next()).onDismissAllDialogCompleted();
            } catch (RemoteException e8) {
                a.a(TAG, "---callOnDismissAllDialogCompleted---e: " + e8.toString());
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void callStateChanged(String str, int i8) {
    }

    public static Context getAppContext() {
        return mContext;
    }

    public static SystemSettingsModule getInstance() {
        if (instance == null) {
            synchronized (SystemSettingsModule.class) {
                if (instance == null) {
                    instance = new SystemSettingsModule();
                }
            }
        }
        return instance;
    }

    @Override // com.byd.systemsettings.system.callback.ICallDispatcher
    public void dismissAllDialog() {
        a.a(TAG, "---dismissAllDialog---");
        g.a().b(new Runnable() { // from class: com.byd.systemsettings.system.SystemSettingsModule.12
            @Override // java.lang.Runnable
            public void run() {
                SystemDialog.finishScreensaver();
                SystemDialog.finishBtPairCode();
                SystemDialog.finishBtSettings();
                SystemDialog.finishWifiSettings();
                SystemDialog.finishCpaaSettings();
                SystemDialog.finishHotspotSettings();
                SystemSettingsModule.this.callOnDismissAllDialogCompleted();
            }
        });
    }

    @Override // com.byd.systemsettings.system.callback.ICallDispatcher
    public void dismissBtSettings() {
        a.a(TAG, "---dismissBtSettings---");
        g.a().b(new Runnable() { // from class: com.byd.systemsettings.system.SystemSettingsModule.8
            @Override // java.lang.Runnable
            public void run() {
                SystemDialog.finishBtSettings();
                SystemSettingsModule.this.callStateChanged("finishBtSettings", 0);
            }
        });
    }

    @Override // com.byd.systemsettings.system.callback.ICallDispatcher
    public void dismissCpaaSettings() {
        a.a(TAG, "---dismissCpaaSettings---");
        g.a().b(new Runnable() { // from class: com.byd.systemsettings.system.SystemSettingsModule.11
            @Override // java.lang.Runnable
            public void run() {
                SystemDialog.finishCpaaSettings();
                SystemSettingsModule.this.callStateChanged("finishCpaaSettings", 0);
            }
        });
    }

    @Override // com.byd.systemsettings.system.callback.ICallDispatcher
    public void dismissHotspotSettings() {
        a.a(TAG, "---dismissHotspotSettings---");
        g.a().b(new Runnable() { // from class: com.byd.systemsettings.system.SystemSettingsModule.10
            @Override // java.lang.Runnable
            public void run() {
                SystemDialog.finishHotspotSettings();
                SystemSettingsModule.this.callStateChanged("finishHotspotSettings", 0);
            }
        });
    }

    @Override // com.byd.systemsettings.system.callback.ICallDispatcher
    public void dismissWifiSettings() {
        a.a(TAG, "---dismissWifiSettings---");
        g.a().b(new Runnable() { // from class: com.byd.systemsettings.system.SystemSettingsModule.9
            @Override // java.lang.Runnable
            public void run() {
                SystemDialog.finishWifiSettings();
                SystemSettingsModule.this.callStateChanged("finishWifiSettings", 0);
            }
        });
    }

    @Override // com.byd.systemsettings.system.callback.ICallDispatcher
    public void doAction(final String str, String str2) {
        a.a(TAG, "---doAction---action: " + str + ", params: " + str2);
        if (TextUtils.isEmpty(str)) {
            return;
        }
        g.a().b(new Runnable() { // from class: com.byd.systemsettings.system.SystemSettingsModule.1
            /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
            /* JADX WARN: Removed duplicated region for block: B:53:0x00b9  */
            @Override // java.lang.Runnable
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct code enable 'Show inconsistent code' option in preferences
            */
            public void run() {
                /*
                    Method dump skipped, instruction units count: 354
                    To view this dump change 'Code comments level' option to 'DEBUG'
                */
                throw new UnsupportedOperationException("Method not decompiled: com.byd.systemsettings.system.SystemSettingsModule.AnonymousClass1.run():void");
            }
        });
    }

    @Override // com.byd.systemsettings.system.callback.ICallDispatcher
    public void finishScreensaverOrScreenOff(boolean z7) {
        a.a(TAG, "---finishScreensaverOrScreenOff---fromAvm: " + z7);
        this.isFinishScreensaverFromAvm = z7;
        g.a().b(new Runnable() { // from class: com.byd.systemsettings.system.SystemSettingsModule.3
            @Override // java.lang.Runnable
            public void run() {
                SystemDialog.finishScreensaver();
                SystemSettingsModule.this.callStateChanged("finishScreensaver", 0);
            }
        });
    }

    @Override // com.byd.systemsettings.system.callback.ICallDispatcher
    public boolean hasDialogShowing() {
        a.a(TAG, "---hasDialogShowing---");
        return SystemDialog.hasSystemDialogShowing();
    }

    @Override // com.byd.systemsettings.system.callback.IAvmOperator
    public void hideAvm() {
        IAvmOperator iAvmOperator;
        a.a(TAG, "---hideAvm---isShowScreensaverFromAvm: " + this.isShowScreensaverFromAvm);
        if (!this.isShowScreensaverFromAvm || (iAvmOperator = this.avmOperator) == null) {
            return;
        }
        iAvmOperator.hideAvm();
    }

    public void init(Context context) {
        if (context == null) {
            return;
        }
        mContext = context.getApplicationContext();
        BydPowerManagerDevice.Singleton.get().init(mContext);
        BydDeviceManager.Singleton.get().init(mContext);
        ConfigurationChangedChecker.init(mContext);
        SystemDialog.init(mContext);
        CarIdentifier.readVehicleConfig(mContext);
        if (!f.d()) {
            f.e(new BydThemeConfig());
        }
        ClockStyle screenSaverClockStyle = BydSystemSettings.Singleton.get().getScreenSaverClockStyle(context);
        a.c(TAG, "---init---style: " + screenSaverClockStyle);
        if (screenSaverClockStyle == ClockStyle.CLOSE) {
            int i8 = mContext.getSharedPreferences(Fields.FILE_SYSTEM_SETTINGS, 0).getInt(Fields.KEY_SCREEN_OFF_STATE, 1);
            a.c(TAG, "---init---state: " + i8);
            if (i8 != 1) {
                BydPowerManagerDevice.Singleton.get().setBacklightState(true);
            }
        }
    }

    public boolean isShowScreensaverFromAvm() {
        return this.isShowScreensaverFromAvm;
    }

    public void onConfigurationChanged(Configuration configuration) {
        CarIdentifier.readVehicleConfig(mContext);
        ConfigurationChangedChecker.onConfigurationChanged(mContext, configuration);
    }

    public void onServiceDestroy() {
        a.a(TAG, "---onServiceDestroy---");
        BydDeviceManager.Singleton.get().onServiceDestroy(mContext);
        BydPowerManagerDevice.Singleton.get().release();
        List<ISystemSettingsCallback> list = this.callbackList;
        if (list != null) {
            list.clear();
        }
        mContext = null;
        this.avmOperator = null;
    }

    public void registerIAvmOperator(IAvmOperator iAvmOperator) {
        this.avmOperator = iAvmOperator;
    }

    @Override // com.byd.systemsettings.system.callback.ICallDispatcher
    public void registerSystemSettingsCallback(ISystemSettingsCallback iSystemSettingsCallback) {
        a.a(TAG, "---registerSystemSettingsCallback---");
        this.callbackList.add(iSystemSettingsCallback);
    }

    @Override // com.byd.systemsettings.system.callback.IAvmOperator
    public void showAvm() {
        IAvmOperator iAvmOperator;
        a.a(TAG, "---hideAvm---isFinishScreensaverFromAvm: " + this.isFinishScreensaverFromAvm);
        if (!this.isFinishScreensaverFromAvm || (iAvmOperator = this.avmOperator) == null) {
            return;
        }
        iAvmOperator.showAvm();
    }

    @Override // com.byd.systemsettings.system.callback.ICallDispatcher
    public void showBtSettings() {
        a.a(TAG, "---showBtSettings---");
        g.a().b(new Runnable() { // from class: com.byd.systemsettings.system.SystemSettingsModule.4
            @Override // java.lang.Runnable
            public void run() {
                SystemDialog.showBtSettings(SystemSettingsModule.mContext);
                SystemSettingsModule.this.callStateChanged("showBtSettings", 0);
            }
        });
    }

    @Override // com.byd.systemsettings.system.callback.ICallDispatcher
    public void showCpaaSettings() {
        a.a(TAG, "---showCpaaSettings---");
        g.a().b(new Runnable() { // from class: com.byd.systemsettings.system.SystemSettingsModule.7
            @Override // java.lang.Runnable
            public void run() {
                SystemDialog.showCpaaSettings(SystemSettingsModule.mContext);
                SystemSettingsModule.this.callStateChanged("showCpaaSettings", 0);
            }
        });
    }

    @Override // com.byd.systemsettings.system.callback.ICallDispatcher
    public void showHotspotSettings() {
        a.a(TAG, "---showHotspotSettings---");
        g.a().b(new Runnable() { // from class: com.byd.systemsettings.system.SystemSettingsModule.6
            @Override // java.lang.Runnable
            public void run() {
                SystemDialog.showHotspotSettings(SystemSettingsModule.mContext);
                SystemSettingsModule.this.callStateChanged("showHotspotSettings", 0);
            }
        });
    }

    @Override // com.byd.systemsettings.system.callback.ICallDispatcher
    public void showScreensaverOrScreenOff(boolean z7) {
        a.a(TAG, "---showScreensaverOrScreenOff---formAvm: " + z7);
        this.isShowScreensaverFromAvm = z7;
        g.a().b(new Runnable() { // from class: com.byd.systemsettings.system.SystemSettingsModule.2
            @Override // java.lang.Runnable
            public void run() {
                SystemDialog.showScreensaverOrScreenOff(SystemSettingsModule.mContext);
                SystemSettingsModule.this.callStateChanged("showScreensaver", 0);
            }
        });
    }

    @Override // com.byd.systemsettings.system.callback.ICallDispatcher
    public void showWifiSettings() {
        a.a(TAG, "---showWifiSettings---");
        g.a().b(new Runnable() { // from class: com.byd.systemsettings.system.SystemSettingsModule.5
            @Override // java.lang.Runnable
            public void run() {
                SystemDialog.showWifiSettings(SystemSettingsModule.mContext);
                SystemSettingsModule.this.callStateChanged("showWifiSettings", 0);
            }
        });
    }

    @Override // com.byd.systemsettings.system.callback.ICallDispatcher
    public void unregisterSystemSettingsCallback(ISystemSettingsCallback iSystemSettingsCallback) {
        a.a(TAG, "---unregisterSystemSettingsCallback---");
        this.callbackList.remove(iSystemSettingsCallback);
    }
}
