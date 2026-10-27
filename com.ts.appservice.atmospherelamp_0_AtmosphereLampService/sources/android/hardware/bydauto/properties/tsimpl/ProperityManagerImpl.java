package android.hardware.bydauto.properties.tsimpl;

import android.content.Context;
import android.hardware.bydauto.SettingsManagerImpl;
import com.ts.lib.settings.common.utilities.LogUtil;

/* JADX INFO: loaded from: classes.dex */
public class ProperityManagerImpl {
    private static final String TAG = "ProperityManagerImpl";
    private static volatile ProperityManagerImpl sInstance;
    private Context mContext;
    private SettingsManagerImpl mSettingsManagerImpl;

    private ProperityManagerImpl(Context context) {
        this.mContext = context;
        this.mSettingsManagerImpl = SettingsManagerImpl.getInstance(context);
    }

    public static ProperityManagerImpl getsInstance(Context context) {
        if (sInstance == null) {
            synchronized (ProperityManagerImpl.class) {
                if (sInstance == null) {
                    sInstance = new ProperityManagerImpl(context);
                }
            }
        }
        return sInstance;
    }

    public String getSystemPersist(String key) {
        LogUtil.debug(TAG, "getSystemPersist is execute");
        if (this.mSettingsManagerImpl.getPropertySettingsManager() == null) {
            LogUtil.error(TAG, "SettingsAdapter still connecting, please wait");
            return null;
        }
        return this.mSettingsManagerImpl.getPropertySettingsManager().getSystemPersist(key);
    }

    public int getSystemPersistInt(String key) {
        LogUtil.debug(TAG, "getSystemPersistInt is execute");
        if (this.mSettingsManagerImpl.getPropertySettingsManager() == null) {
            LogUtil.error(TAG, "SettingsAdapter still connecting, please wait");
            return -1;
        }
        return this.mSettingsManagerImpl.getPropertySettingsManager().getSystemPersistInt(key);
    }

    public boolean getSystemPersistBoolean(String key) {
        LogUtil.debug(TAG, "getSystemPersistBoolean is execute");
        if (this.mSettingsManagerImpl.getPropertySettingsManager() == null) {
            LogUtil.error(TAG, "SettingsAdapter still connecting, please wait");
            return false;
        }
        return this.mSettingsManagerImpl.getPropertySettingsManager().getSystemPersistBoolean(key);
    }

    public int getSettingsInt(String key) {
        LogUtil.debug(TAG, "getSettingsInt is execute");
        if (this.mSettingsManagerImpl.getPropertySettingsManager() == null) {
            LogUtil.error(TAG, "SettingsAdapter still connecting, please wait");
            return -1;
        }
        return this.mSettingsManagerImpl.getPropertySettingsManager().getSettingsInt(key);
    }

    public boolean getSettingsBoolean(String key) {
        LogUtil.debug(TAG, "getSettingsBoolean is execute");
        if (this.mSettingsManagerImpl.getPropertySettingsManager() == null) {
            LogUtil.error(TAG, "SettingsAdapter still connecting, please wait");
            return false;
        }
        return this.mSettingsManagerImpl.getPropertySettingsManager().getSettingsBoolean(key);
    }

    public String getSettingsString(String key) {
        LogUtil.debug(TAG, "getSettingsString is execute");
        if (this.mSettingsManagerImpl.getPropertySettingsManager() == null) {
            LogUtil.error(TAG, "SettingsAdapter still connecting, please wait");
            return null;
        }
        return this.mSettingsManagerImpl.getPropertySettingsManager().getSettingsString(key);
    }

    public String getBtContact(String address) {
        LogUtil.debug(TAG, "getBtContact is execute");
        if (this.mSettingsManagerImpl.getPropertySettingsManager() == null) {
            LogUtil.error(TAG, "SettingsAdapter still connecting, please wait");
            return null;
        }
        return this.mSettingsManagerImpl.getPropertySettingsManager().getBtContact(address);
    }

    public void setSettingsInt(String key, int value) {
        LogUtil.debug(TAG, "setSettingsInt is execute");
        if (this.mSettingsManagerImpl.getPropertySettingsManager() == null) {
            LogUtil.error(TAG, "SettingsAdapter still connecting, please wait");
        } else {
            this.mSettingsManagerImpl.getPropertySettingsManager().setSettingsInt(key, value);
        }
    }

    public void setSettingsBoolean(String key, int value) {
        LogUtil.debug(TAG, "setSettingsBoolean is execute");
        if (this.mSettingsManagerImpl.getPropertySettingsManager() == null) {
            LogUtil.error(TAG, "SettingsAdapter still connecting, please wait");
        } else {
            this.mSettingsManagerImpl.getPropertySettingsManager().setSettingsBoolean(key, value);
        }
    }

    public void setSystemPersist(String key, String value) {
        LogUtil.debug(TAG, "setSystemPersist is execute");
        if (this.mSettingsManagerImpl.getPropertySettingsManager() == null) {
            LogUtil.error(TAG, "SettingsAdapter still connecting, please wait");
        } else {
            this.mSettingsManagerImpl.getPropertySettingsManager().setSystemPersist(key, value);
        }
    }
}
