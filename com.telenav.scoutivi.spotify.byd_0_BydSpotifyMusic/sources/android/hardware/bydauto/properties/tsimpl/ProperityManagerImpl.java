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

    public String getBtContact(String str) {
        LogUtil.debug(TAG, "getBtContact is execute");
        if (this.mSettingsManagerImpl.getPropertySettingsManager() != null) {
            return this.mSettingsManagerImpl.getPropertySettingsManager().getBtContact(str);
        }
        LogUtil.error(TAG, "SettingsAdapter still connecting, please wait");
        return null;
    }

    public boolean getSettingsBoolean(String str) {
        LogUtil.debug(TAG, "getSettingsBoolean is execute");
        if (this.mSettingsManagerImpl.getPropertySettingsManager() != null) {
            return this.mSettingsManagerImpl.getPropertySettingsManager().getSettingsBoolean(str);
        }
        LogUtil.error(TAG, "SettingsAdapter still connecting, please wait");
        return false;
    }

    public int getSettingsInt(String str) {
        LogUtil.debug(TAG, "getSettingsInt is execute");
        if (this.mSettingsManagerImpl.getPropertySettingsManager() != null) {
            return this.mSettingsManagerImpl.getPropertySettingsManager().getSettingsInt(str);
        }
        LogUtil.error(TAG, "SettingsAdapter still connecting, please wait");
        return -1;
    }

    public String getSettingsString(String str) {
        LogUtil.debug(TAG, "getSettingsString is execute");
        if (this.mSettingsManagerImpl.getPropertySettingsManager() != null) {
            return this.mSettingsManagerImpl.getPropertySettingsManager().getSettingsString(str);
        }
        LogUtil.error(TAG, "SettingsAdapter still connecting, please wait");
        return null;
    }

    public String getSystemPersist(String str) {
        LogUtil.debug(TAG, "getSystemPersist is execute");
        if (this.mSettingsManagerImpl.getPropertySettingsManager() != null) {
            return this.mSettingsManagerImpl.getPropertySettingsManager().getSystemPersist(str);
        }
        LogUtil.error(TAG, "SettingsAdapter still connecting, please wait");
        return null;
    }

    public boolean getSystemPersistBoolean(String str) {
        LogUtil.debug(TAG, "getSystemPersistBoolean is execute");
        if (this.mSettingsManagerImpl.getPropertySettingsManager() != null) {
            return this.mSettingsManagerImpl.getPropertySettingsManager().getSystemPersistBoolean(str);
        }
        LogUtil.error(TAG, "SettingsAdapter still connecting, please wait");
        return false;
    }

    public int getSystemPersistInt(String str) {
        LogUtil.debug(TAG, "getSystemPersistInt is execute");
        if (this.mSettingsManagerImpl.getPropertySettingsManager() != null) {
            return this.mSettingsManagerImpl.getPropertySettingsManager().getSystemPersistInt(str);
        }
        LogUtil.error(TAG, "SettingsAdapter still connecting, please wait");
        return -1;
    }

    public void setSettingsBoolean(String str, int i7) {
        LogUtil.debug(TAG, "setSettingsBoolean is execute");
        if (this.mSettingsManagerImpl.getPropertySettingsManager() == null) {
            LogUtil.error(TAG, "SettingsAdapter still connecting, please wait");
        } else {
            this.mSettingsManagerImpl.getPropertySettingsManager().setSettingsBoolean(str, i7);
        }
    }

    public void setSettingsInt(String str, int i7) {
        LogUtil.debug(TAG, "setSettingsInt is execute");
        if (this.mSettingsManagerImpl.getPropertySettingsManager() == null) {
            LogUtil.error(TAG, "SettingsAdapter still connecting, please wait");
        } else {
            this.mSettingsManagerImpl.getPropertySettingsManager().setSettingsInt(str, i7);
        }
    }

    public void setSystemPersist(String str, String str2) {
        LogUtil.debug(TAG, "setSystemPersist is execute");
        if (this.mSettingsManagerImpl.getPropertySettingsManager() == null) {
            LogUtil.error(TAG, "SettingsAdapter still connecting, please wait");
        } else {
            this.mSettingsManagerImpl.getPropertySettingsManager().setSystemPersist(str, str2);
        }
    }
}
