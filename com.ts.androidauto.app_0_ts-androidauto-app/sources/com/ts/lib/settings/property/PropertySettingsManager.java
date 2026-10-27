package com.ts.lib.settings.property;

import android.os.IBinder;
import android.os.RemoteException;
import com.ts.lib.settings.SettingsAdapterBase;
import com.ts.lib.settings.common.utilities.LogUtil;
import com.ts.lib.settings.property.IPropertySettingsService;

/* JADX INFO: loaded from: classes2.dex */
public class PropertySettingsManager implements SettingsAdapterBase {
    private static final String TAG = PropertySettingsManager.class.getSimpleName();
    private IPropertySettingsService mPropertySettingsService;

    public PropertySettingsManager(IBinder binder) {
        LogUtil.debug(TAG, "init property manager");
        this.mPropertySettingsService = IPropertySettingsService.Stub.asInterface(binder);
    }

    public String getSystemPersist(String key) {
        if (this.mPropertySettingsService == null) {
            LogUtil.error(TAG, "mPropertySettingsService is null");
            return null;
        }
        LogUtil.debug(TAG, "getSystemPersist key " + key);
        try {
            return this.mPropertySettingsService.getSystemPersist(key);
        } catch (RemoteException ex) {
            LogUtil.error(TAG, "getSystemPersist error: ", ex);
            return null;
        }
    }

    public int getSystemPersistInt(String key) {
        if (this.mPropertySettingsService == null) {
            LogUtil.error(TAG, "mPropertySettingsService is null");
            return -1;
        }
        LogUtil.debug(TAG, "getSystemPersistInt key " + key);
        try {
            return this.mPropertySettingsService.getSystemPersistInt(key);
        } catch (RemoteException ex) {
            LogUtil.error(TAG, "getSystemPersistInt error: ", ex);
            return -1;
        }
    }

    public boolean getSystemPersistBoolean(String key) {
        if (this.mPropertySettingsService == null) {
            LogUtil.error(TAG, "mPropertySettingsService is null");
            return false;
        }
        LogUtil.debug(TAG, "getSystemPersistBoolean key " + key);
        try {
            return this.mPropertySettingsService.getSystemPersistBoolean(key);
        } catch (RemoteException ex) {
            LogUtil.error(TAG, "getSystemPersistBoolean error: ", ex);
            return false;
        }
    }

    public int getSettingsInt(String key) {
        if (this.mPropertySettingsService == null) {
            LogUtil.error(TAG, "mPropertySettingsService is null");
            return -1;
        }
        LogUtil.debug(TAG, "getSettingsInt key " + key);
        try {
            return this.mPropertySettingsService.getSettingsInt(key);
        } catch (RemoteException ex) {
            LogUtil.error(TAG, "getSettingsInt error: ", ex);
            return -1;
        }
    }

    public boolean getSettingsBoolean(String key) {
        if (this.mPropertySettingsService == null) {
            LogUtil.error(TAG, "mPropertySettingsService is null");
            return false;
        }
        LogUtil.debug(TAG, "getSettingsBoolean key " + key);
        try {
            return this.mPropertySettingsService.getSettingsBoolean(key);
        } catch (RemoteException ex) {
            LogUtil.error(TAG, "getSettingsBoolean error: ", ex);
            return false;
        }
    }

    public String getSettingsString(String key) {
        if (this.mPropertySettingsService == null) {
            LogUtil.error(TAG, "mPropertySettingsService is null");
            return null;
        }
        LogUtil.debug(TAG, "getSettingsString key " + key);
        try {
            return this.mPropertySettingsService.getSettingsString(key);
        } catch (RemoteException ex) {
            LogUtil.error(TAG, "getSettingsString error: ", ex);
            return null;
        }
    }

    public String getBtContact(String address) {
        if (this.mPropertySettingsService == null) {
            LogUtil.error(TAG, "mPropertySettingsService is null");
            return null;
        }
        LogUtil.debug(TAG, "getBtContact address " + address);
        try {
            return this.mPropertySettingsService.getBtContact(address);
        } catch (RemoteException ex) {
            LogUtil.error(TAG, "getBtContact error: ", ex);
            return null;
        }
    }

    public void setSettingsInt(String key, int value) {
        if (this.mPropertySettingsService == null) {
            LogUtil.error(TAG, "mPropertySettingsService is null");
        }
        LogUtil.debug(TAG, "setSettingsInt key " + key + " value " + value);
        try {
            this.mPropertySettingsService.setSettingsInt(key, value);
        } catch (RemoteException ex) {
            LogUtil.error(TAG, "setSettingsInt error: ", ex);
        }
    }

    public void setSettingsBoolean(String key, int value) {
        if (this.mPropertySettingsService == null) {
            LogUtil.error(TAG, "mPropertySettingsService is null");
        }
        LogUtil.debug(TAG, "setSettingsBoolean key " + key + " value " + value);
        try {
            this.mPropertySettingsService.setSettingsBoolean(key, value);
        } catch (RemoteException ex) {
            LogUtil.error(TAG, "setSettingsBoolean error: ", ex);
        }
    }

    public void setSystemPersist(String key, String value) {
        if (this.mPropertySettingsService == null) {
            LogUtil.error(TAG, "mPropertySettingsService is null");
        }
        LogUtil.debug(TAG, "setSystemPersist key " + key + " value " + value);
        try {
            this.mPropertySettingsService.setSystemPersist(key, value);
        } catch (RemoteException ex) {
            LogUtil.error(TAG, "setSystemPersist error: ", ex);
        }
    }

    @Override // com.ts.lib.settings.SettingsAdapterBase
    public void onSettingsDisconnected() {
        LogUtil.debug(TAG, "onSettingsDisconnected");
    }
}
