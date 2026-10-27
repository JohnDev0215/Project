package com.byd.systemsettings;

import android.content.Context;
import android.os.RemoteException;
import android.util.Log;
import com.byd.systemsettings.BydSettingsApi;
import com.byd.systemsettings.ISystemSettingsCallback;

/* JADX INFO: loaded from: classes.dex */
public class SystemSettingsApi {
    private static final String TAG = "SystemSettingsApi";
    private static volatile SystemSettingsApi instance;
    private BydSettingsApi settingsApi;

    private SystemSettingsApi() {
    }

    private void bindApi(Context context) {
        if (context == null) {
            return;
        }
        this.settingsApi = BydSettingsApi.getApi(context.getApplicationContext(), new BydSettingsApi.OnServiceConnectCallback() { // from class: com.byd.systemsettings.SystemSettingsApi.1
            @Override // com.byd.systemsettings.BydSettingsApi.OnServiceConnectCallback
            public void onServiceConnectChanged(boolean z) {
                Log.i(SystemSettingsApi.TAG, "----onServiceConnectChanged----connected: " + z);
                if (z) {
                    SystemSettingsApi.this.settingsApi.registerSystemSettingsCallback(new ISystemSettingsCallback.Stub() { // from class: com.byd.systemsettings.SystemSettingsApi.1.1
                        @Override // com.byd.systemsettings.ISystemSettingsCallback
                        public void onDismissAllDialogCompleted() throws RemoteException {
                            Log.i(SystemSettingsApi.TAG, "---onDismissAllDialogCompleted---");
                        }

                        @Override // com.byd.systemsettings.ISystemSettingsCallback
                        public void onStateChanged(String str, int i) throws RemoteException {
                            Log.i(SystemSettingsApi.TAG, "---onStateChanged---action: " + str + ", state: " + i);
                        }
                    });
                }
            }
        });
    }

    public static SystemSettingsApi getInstance(Context context) {
        if (instance == null) {
            synchronized (SystemSettingsApi.class) {
                if (instance == null) {
                    instance = new SystemSettingsApi();
                    instance.bindApi(context);
                }
            }
        }
        return instance;
    }

    public void dismissAllDialog() {
        Log.i(TAG, "---dismissAllDialog---");
        BydSettingsApi bydSettingsApi = this.settingsApi;
        if (bydSettingsApi != null) {
            bydSettingsApi.dismissAllDialog();
            return;
        }
        Log.e(TAG, "---dismissAllDialog---settingsApi: " + this.settingsApi);
    }

    public void dismissBtSettings() {
        Log.i(TAG, "---dismissBtSettings---");
        BydSettingsApi bydSettingsApi = this.settingsApi;
        if (bydSettingsApi != null) {
            bydSettingsApi.dismissBtSettings();
            return;
        }
        Log.e(TAG, "---dismissBtSettings---settingsApi: " + this.settingsApi);
    }

    public void dismissCpaaSettings() {
        Log.i(TAG, "---dismissCpaaSettings---");
        BydSettingsApi bydSettingsApi = this.settingsApi;
        if (bydSettingsApi != null) {
            bydSettingsApi.dismissCpaaSettings();
            return;
        }
        Log.e(TAG, "---dismissCpaaSettings---settingsApi: " + this.settingsApi);
    }

    public void dismissHotspotSettings() {
        Log.i(TAG, "---dismissHotspotSettings---");
        BydSettingsApi bydSettingsApi = this.settingsApi;
        if (bydSettingsApi != null) {
            bydSettingsApi.dismissHotspotSettings();
            return;
        }
        Log.e(TAG, "---dismissHotspotSettings---settingsApi: " + this.settingsApi);
    }

    public void dismissWifiSettings() {
        Log.i(TAG, "---dismissWifiSettings---");
        BydSettingsApi bydSettingsApi = this.settingsApi;
        if (bydSettingsApi != null) {
            bydSettingsApi.dismissWifiSettings();
            return;
        }
        Log.e(TAG, "---dismissWifiSettings---settingsApi: " + this.settingsApi);
    }

    public void finishScreensaver() {
        Log.i(TAG, "---finishScreensaver---");
        BydSettingsApi bydSettingsApi = this.settingsApi;
        if (bydSettingsApi != null) {
            bydSettingsApi.finishScreensaver();
            return;
        }
        Log.e(TAG, "---finishScreensaver---settingsApi: " + this.settingsApi);
    }

    public void showBtSettings() {
        Log.i(TAG, "---showBtSettings---");
        BydSettingsApi bydSettingsApi = this.settingsApi;
        if (bydSettingsApi != null) {
            bydSettingsApi.showBtSettings();
            return;
        }
        Log.e(TAG, "---showBtSettings---settingsApi: " + this.settingsApi);
    }

    public void showCpaaSettings() {
        Log.i(TAG, "---showCpaaSettings---");
        BydSettingsApi bydSettingsApi = this.settingsApi;
        if (bydSettingsApi != null) {
            bydSettingsApi.showCpaaSettings();
            return;
        }
        Log.e(TAG, "---showCpaaSettings---settingsApi: " + this.settingsApi);
    }

    public void showHotspotSettings() {
        Log.i(TAG, "---showHotspotSettings---");
        BydSettingsApi bydSettingsApi = this.settingsApi;
        if (bydSettingsApi != null) {
            bydSettingsApi.showHotspotSettings();
            return;
        }
        Log.e(TAG, "---showHotspotSettings---settingsApi: " + this.settingsApi);
    }

    public void showScreensaver() {
        Log.i(TAG, "---showScreensaver---");
        BydSettingsApi bydSettingsApi = this.settingsApi;
        if (bydSettingsApi != null) {
            bydSettingsApi.showScreensaver();
            return;
        }
        Log.e(TAG, "---showScreensaver---settingsApi: " + this.settingsApi);
    }

    public void showWifiSettings() {
        Log.i(TAG, "---showWifiSettings---");
        BydSettingsApi bydSettingsApi = this.settingsApi;
        if (bydSettingsApi != null) {
            bydSettingsApi.showWifiSettings();
            return;
        }
        Log.e(TAG, "---showWifiSettings---settingsApi: " + this.settingsApi);
    }
}
