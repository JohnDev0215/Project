package com.ts.appservice.settings.impl.property;

import android.content.Context;
import android.os.SystemProperties;
import android.provider.Settings;
import com.ts.lib.settings.common.utilities.LogUtil;
import com.ts.lib.settings.property.IPropertySettingsService;

/* JADX INFO: loaded from: classes.dex */
public class IPropertyServiceBinder extends IPropertySettingsService.Stub {
    private static final String TAG = IPropertyServiceBinder.class.getSimpleName();
    private Context mContext;

    public IPropertyServiceBinder(Context context) {
        this.mContext = context;
    }

    public String getSystemPersist(String key) {
        LogUtil.debug(TAG, "getSystemPersist is execute");
        if (key != null) {
            String systemPersist = SystemProperties.get(key, (String) null);
            LogUtil.info(TAG, "getSystemPersist systemPersist:" + systemPersist);
            return systemPersist;
        }
        LogUtil.error(TAG, "getSystemPersist: value is null");
        return null;
    }

    public int getSystemPersistInt(String key) {
        LogUtil.debug(TAG, "getSystemPersistInt is execute");
        if (key != null) {
            int systemPersist = SystemProperties.getInt(key, -1);
            LogUtil.info(TAG, "getSystemPersistInt systemPersist:" + systemPersist);
            return systemPersist;
        }
        LogUtil.error(TAG, "getSystemPersistInt: value is null");
        return -1;
    }

    public boolean getSystemPersistBoolean(String key) {
        LogUtil.debug(TAG, "getSystemPersistBoolean is execute");
        boolean systemPersist = false;
        if (key != null) {
            systemPersist = SystemProperties.getBoolean(key, false);
        } else {
            LogUtil.error(TAG, "getSystemPersistBoolean: value is null");
        }
        LogUtil.info(TAG, "getSystemPersistBoolean  systemPersist:" + systemPersist);
        return systemPersist;
    }

    public int getSettingsInt(String key) {
        LogUtil.debug(TAG, "getSettingsInt is execute");
        return Settings.System.getInt(this.mContext.getContentResolver(), key, -1);
    }

    public boolean getSettingsBoolean(String key) {
        LogUtil.debug(TAG, "getSettingsBoolean is execute");
        return false;
    }

    public String getSettingsString(String key) {
        LogUtil.debug(TAG, "getSettingsString is execute");
        return Settings.System.getString(this.mContext.getContentResolver(), key);
    }

    public String getBtContact(String address) {
        LogUtil.debug(TAG, "getBtContact is execute");
        return null;
    }

    public void setSettingsInt(String key, int value) {
        LogUtil.debug(TAG, "setSettingsInt key:" + key + " value:" + value);
        Settings.System.putInt(this.mContext.getContentResolver(), key, value);
    }

    public void setSettingsBoolean(String key, int value) {
        LogUtil.debug(TAG, "setSettingsBoolean key:" + key + " value:" + value);
    }

    public void setSystemPersist(String key, String value) {
        LogUtil.info(TAG, "setSystemPersist key:" + key + " value:" + value);
        SystemProperties.set(key, value);
    }
}
