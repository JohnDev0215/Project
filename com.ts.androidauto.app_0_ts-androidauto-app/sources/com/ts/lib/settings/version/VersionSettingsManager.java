package com.ts.lib.settings.version;

import android.os.IBinder;
import android.os.RemoteException;
import com.ts.androidauto.fsdk.common.AndroidAutoConst;
import com.ts.lib.settings.SettingsAdapterBase;
import com.ts.lib.settings.common.utilities.LogUtil;
import com.ts.lib.settings.version.IVersionSettingsService;

/* JADX INFO: loaded from: classes2.dex */
public class VersionSettingsManager implements SettingsAdapterBase {
    private static final String TAG = VersionSettingsManager.class.getSimpleName();
    private IVersionSettingsService mVersionSettingsService;

    public VersionSettingsManager(IBinder binder) {
        LogUtil.debug(TAG, "init theme manager");
        this.mVersionSettingsService = IVersionSettingsService.Stub.asInterface(binder);
    }

    public String getBydSystemVersion() {
        if (this.mVersionSettingsService == null) {
            LogUtil.error(TAG, "mVersionSettingsService is null");
            return AndroidAutoConst.DEFAULT_SCREEN_NAME;
        }
        LogUtil.debug(TAG, "getBydSystemVersion");
        try {
            return this.mVersionSettingsService.getBydSystemVersion();
        } catch (RemoteException ex) {
            LogUtil.error(TAG, "getBydSystemVersion error: ", ex);
            return AndroidAutoConst.DEFAULT_SCREEN_NAME;
        }
    }

    public String getHwVersion() {
        if (this.mVersionSettingsService == null) {
            LogUtil.error(TAG, "mVersionSettingsService is null");
            return AndroidAutoConst.DEFAULT_SCREEN_NAME;
        }
        LogUtil.debug(TAG, "getHwVersion");
        try {
            return this.mVersionSettingsService.getHwVersion();
        } catch (RemoteException ex) {
            LogUtil.error(TAG, "getHwVersion error: ", ex);
            return AndroidAutoConst.DEFAULT_SCREEN_NAME;
        }
    }

    public String getInstrumentHwVersion() {
        if (this.mVersionSettingsService == null) {
            LogUtil.error(TAG, "mVersionSettingsService is null");
            return AndroidAutoConst.DEFAULT_SCREEN_NAME;
        }
        LogUtil.debug(TAG, "getInstrumentHwVersion");
        try {
            return this.mVersionSettingsService.getInstrumentHwVersion();
        } catch (RemoteException ex) {
            LogUtil.error(TAG, "getInstrumentHwVersion error: ", ex);
            return AndroidAutoConst.DEFAULT_SCREEN_NAME;
        }
    }

    public String getInstrumentSwVersion() {
        if (this.mVersionSettingsService == null) {
            LogUtil.error(TAG, "mVersionSettingsService is null");
            return AndroidAutoConst.DEFAULT_SCREEN_NAME;
        }
        LogUtil.debug(TAG, "getInstrumentSwVersion");
        try {
            return this.mVersionSettingsService.getInstrumentSwVersion();
        } catch (RemoteException ex) {
            LogUtil.error(TAG, "getInstrumentSwVersion error: ", ex);
            return AndroidAutoConst.DEFAULT_SCREEN_NAME;
        }
    }

    public String getVehicleSN() {
        if (this.mVersionSettingsService == null) {
            LogUtil.error(TAG, "mVersionSettingsService is null");
            return AndroidAutoConst.DEFAULT_SCREEN_NAME;
        }
        LogUtil.debug(TAG, "getVehicleSN");
        try {
            return this.mVersionSettingsService.getVehicleSN();
        } catch (RemoteException ex) {
            LogUtil.error(TAG, "getVehicleSN error: ", ex);
            return AndroidAutoConst.DEFAULT_SCREEN_NAME;
        }
    }

    public String[] getMcuVersion() {
        if (this.mVersionSettingsService == null) {
            LogUtil.error(TAG, "mVersionSettingsService is null");
            return null;
        }
        LogUtil.debug(TAG, "getMcuVersion");
        try {
            return this.mVersionSettingsService.getMcuVersion();
        } catch (RemoteException ex) {
            LogUtil.error(TAG, "getMcuVersion error: ", ex);
            return null;
        }
    }

    @Override // com.ts.lib.settings.SettingsAdapterBase
    public void onSettingsDisconnected() {
    }
}
