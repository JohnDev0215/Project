package com.ts.lib.settings.appconfig;

import android.os.IBinder;
import android.os.RemoteException;
import com.ts.lib.settings.SettingsAdapterBase;
import com.ts.lib.settings.appconfig.IAppConfigSettingsCallback;
import com.ts.lib.settings.appconfig.IAppConfigSettingsService;
import com.ts.lib.settings.common.utilities.LogUtil;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class AppConfigSettingsManger implements SettingsAdapterBase {
    private static final String TAG = AppConfigSettingsManger.class.getSimpleName();
    private IAppConfigSettingsService mAppConfigSettingsService;
    private List<OnAppConfigChangedListener> mAppConfigChangedListenerList = new ArrayList();
    private List<OnCarTypeChangedListener> mCarTypeChangedListenerList = new ArrayList();
    private final IAppConfigSettingsCallback mAppConfigSettingsCallback = new IAppConfigSettingsCallback.Stub() { // from class: com.ts.lib.settings.appconfig.AppConfigSettingsManger.1
        @Override // com.ts.lib.settings.appconfig.IAppConfigSettingsCallback
        public void onChangeConfigArea(int state) {
            AppConfigSettingsManger.this.callbackAppConfigChanged(state);
        }

        @Override // com.ts.lib.settings.appconfig.IAppConfigSettingsCallback
        public void onChangedCpaa(int state) {
            AppConfigSettingsManger.this.callbackCpaaInstallChanged(state);
        }

        @Override // com.ts.lib.settings.appconfig.IAppConfigSettingsCallback
        public void onStatusChangeCarType(boolean status) {
            AppConfigSettingsManger.this.callbackCarTypeChanged(status);
        }
    };

    public AppConfigSettingsManger(IBinder binder) {
        LogUtil.debug(TAG, "init app config Manager");
        this.mAppConfigSettingsService = IAppConfigSettingsService.Stub.asInterface(binder);
        subscribeAppConfigCallback();
    }

    public int setConfigArea(String code) {
        LogUtil.debug(TAG, "setConfigArea code : " + code);
        IAppConfigSettingsService iAppConfigSettingsService = this.mAppConfigSettingsService;
        if (iAppConfigSettingsService == null) {
            LogUtil.error(TAG, "mAppConfigSettingsService is null");
            return -1;
        }
        try {
            return iAppConfigSettingsService.setConfigArea(code);
        } catch (RemoteException ex) {
            LogUtil.error(TAG, "setConfigArea failed ", ex);
            return -1;
        }
    }

    public int setCpaaInstall(boolean state) {
        LogUtil.debug(TAG, "setCpaaInstall status : " + state);
        IAppConfigSettingsService iAppConfigSettingsService = this.mAppConfigSettingsService;
        if (iAppConfigSettingsService == null) {
            LogUtil.error(TAG, "mAppConfigSettingsService is null");
            return -1;
        }
        try {
            return iAppConfigSettingsService.setCpaaInstall(state);
        } catch (RemoteException ex) {
            LogUtil.error(TAG, "setCpaaStatus failed ", ex);
            return -1;
        }
    }

    public int setCpaaInstallType(int type, boolean state) {
        LogUtil.debug(TAG, "setCpaaInstall type :" + type + " state : " + state);
        IAppConfigSettingsService iAppConfigSettingsService = this.mAppConfigSettingsService;
        if (iAppConfigSettingsService == null) {
            LogUtil.error(TAG, "mAppConfigSettingsService is null");
            return -1;
        }
        try {
            return iAppConfigSettingsService.setCpaaInstallType(type, state);
        } catch (RemoteException ex) {
            LogUtil.error(TAG, "setCpaaStatus failed ", ex);
            return -1;
        }
    }

    public int setCarType(int code) {
        LogUtil.debug(TAG, "setCarType code : " + code);
        IAppConfigSettingsService iAppConfigSettingsService = this.mAppConfigSettingsService;
        if (iAppConfigSettingsService == null) {
            LogUtil.error(TAG, "mAppConfigSettingsService is null");
            return -1;
        }
        try {
            return iAppConfigSettingsService.setCarType(code);
        } catch (RemoteException ex) {
            LogUtil.error(TAG, "setCarType failed ", ex);
            return -1;
        }
    }

    public void setDefaultLanguages(int countryCode) {
        LogUtil.debug(TAG, "setDefaultLanguage code : " + countryCode);
        IAppConfigSettingsService iAppConfigSettingsService = this.mAppConfigSettingsService;
        if (iAppConfigSettingsService == null) {
            LogUtil.error(TAG, "mAppConfigSettingsService is null");
            return;
        }
        try {
            iAppConfigSettingsService.setDefaultLanguages(countryCode);
        } catch (RemoteException ex) {
            LogUtil.error(TAG, "setDefaultLanguage failed ", ex);
        }
    }

    public void subscribeAppConfigCallback(OnAppConfigChangedListener listener) {
        synchronized (this.mAppConfigChangedListenerList) {
            if (!this.mAppConfigChangedListenerList.contains(listener) && listener != null) {
                this.mAppConfigChangedListenerList.add(listener);
                LogUtil.debug(TAG, "subscribeAppConfigCallback ok --> listener size: " + this.mAppConfigChangedListenerList.size());
            }
        }
    }

    public void unsubscribeAppConfigCallback(OnAppConfigChangedListener listener) {
        synchronized (this.mAppConfigChangedListenerList) {
            if (listener != null) {
                if (this.mAppConfigChangedListenerList.contains(listener)) {
                    this.mAppConfigChangedListenerList.remove(listener);
                    LogUtil.debug(TAG, "unsubscribeAppConfigCallback ok --> listener size: " + this.mAppConfigChangedListenerList.size());
                }
            }
        }
    }

    public void subscribeCarTypeCallback(OnCarTypeChangedListener listener) {
        synchronized (this.mCarTypeChangedListenerList) {
            if (!this.mCarTypeChangedListenerList.contains(listener) && listener != null) {
                this.mCarTypeChangedListenerList.add(listener);
                LogUtil.debug(TAG, "subscribeCarTypeCallback ok --> listener size: " + this.mCarTypeChangedListenerList.size());
            }
        }
    }

    public void unsubscribeCarTypeCallback(OnCarTypeChangedListener listener) {
        synchronized (this.mCarTypeChangedListenerList) {
            if (listener != null) {
                if (this.mCarTypeChangedListenerList.contains(listener)) {
                    this.mCarTypeChangedListenerList.remove(listener);
                    LogUtil.debug(TAG, "unsubscribeCarTypeCallback ok --> listener size: " + this.mCarTypeChangedListenerList.size());
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void callbackAppConfigChanged(int state) {
        for (OnAppConfigChangedListener listener : this.mAppConfigChangedListenerList) {
            listener.onChangedConfigArea(state);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void callbackCpaaInstallChanged(int state) {
        for (OnAppConfigChangedListener listener : this.mAppConfigChangedListenerList) {
            listener.onChangedCpaa(state);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void callbackCarTypeChanged(boolean status) {
        for (OnCarTypeChangedListener listener : this.mCarTypeChangedListenerList) {
            listener.onStatusChangeCarType(status);
        }
    }

    private void subscribeAppConfigCallback() {
        if (this.mAppConfigSettingsService == null) {
            LogUtil.error(TAG, "mAppConfigSettingsService is null");
            return;
        }
        LogUtil.debug(TAG, "subscribeAppConfigCallback");
        try {
            this.mAppConfigSettingsService.subscribeAppConfigCallback(this.mAppConfigSettingsCallback);
        } catch (RemoteException ex) {
            LogUtil.error(TAG, "subscribeCartipsCallback error ", ex);
        }
    }

    private void unsubscribeAppConfigCallback() {
        if (this.mAppConfigSettingsService == null) {
            LogUtil.error(TAG, "unsubscribeAppConfigCallback is null");
            return;
        }
        LogUtil.debug(TAG, "unsubscribeAppConfigCallback");
        try {
            this.mAppConfigSettingsService.unsubscribeAppConfigCallback(this.mAppConfigSettingsCallback);
        } catch (RemoteException ex) {
            LogUtil.error(TAG, "unsubscribeAppConfigCallback error ", ex);
        }
    }

    @Override // com.ts.lib.settings.SettingsAdapterBase
    public void onSettingsDisconnected() {
        unsubscribeAppConfigCallback();
        this.mAppConfigSettingsService = null;
    }
}
