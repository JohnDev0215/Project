package android.hardware.bydauto.version.tsimpl;

import android.content.Context;
import android.hardware.bydauto.SettingsManagerImpl;
import com.ts.lib.settings.common.utilities.LogUtil;

/* JADX INFO: loaded from: classes.dex */
public class VersionManagerImpl {
    public static final String TAG = "VersionManagerImpl";
    private static volatile VersionManagerImpl sInstance;
    private Context mContext;
    private boolean mIgnoreBydPermission = false;
    private SettingsManagerImpl mSettingsManagerImpl;

    public static VersionManagerImpl getsInstance(Context context) {
        if (sInstance == null) {
            synchronized (VersionManagerImpl.class) {
                if (sInstance == null) {
                    sInstance = new VersionManagerImpl(context);
                }
            }
        }
        return sInstance;
    }

    VersionManagerImpl(Context context) {
        this.mContext = context;
        this.mSettingsManagerImpl = SettingsManagerImpl.getInstance(context);
        enforcePermission("android.permission.BYDAUTO_VERSION_COMMON");
    }

    private void enforcePermission(String str) {
        if (!this.mIgnoreBydPermission && this.mContext.checkCallingOrSelfPermission(str) != 0) {
            throw new SecurityException("requires permission " + str);
        }
    }

    public String getBydSystemVersion() {
        String str = TAG;
        LogUtil.debug(str, "getBydSystemVersion");
        enforcePermission("android.permission.BYDAUTO_VERSION_GET");
        if (this.mSettingsManagerImpl.getVersionSettingsManager() == null) {
            LogUtil.error(str, "SettingsAdapter still connecting, please wait");
            return null;
        }
        return this.mSettingsManagerImpl.getVersionSettingsManager().getBydSystemVersion();
    }

    public String getHwVersion() {
        String str = TAG;
        LogUtil.debug(str, "getHwVersion");
        enforcePermission("android.permission.BYDAUTO_VERSION_GET");
        if (this.mSettingsManagerImpl.getVersionSettingsManager() == null) {
            LogUtil.error(str, "SettingsAdapter still connecting, please wait");
            return null;
        }
        return this.mSettingsManagerImpl.getVersionSettingsManager().getHwVersion();
    }

    public String getInstrumentHwVersion() {
        String str = TAG;
        LogUtil.debug(str, "getInstrumentHwVersion");
        enforcePermission("android.permission.BYDAUTO_VERSION_GET");
        if (this.mSettingsManagerImpl.getVersionSettingsManager() == null) {
            LogUtil.error(str, "SettingsAdapter still connecting, please wait");
            return null;
        }
        return this.mSettingsManagerImpl.getVersionSettingsManager().getInstrumentHwVersion();
    }

    public String getInstrumentSwVersion() {
        String str = TAG;
        LogUtil.debug(str, "getInstrumentSwVersion");
        enforcePermission("android.permission.BYDAUTO_VERSION_GET");
        if (this.mSettingsManagerImpl.getVersionSettingsManager() == null) {
            LogUtil.error(str, "SettingsAdapter still connecting, please wait");
            return null;
        }
        return this.mSettingsManagerImpl.getVersionSettingsManager().getInstrumentSwVersion();
    }

    public String getVehicleSN() {
        String str = TAG;
        LogUtil.debug(str, "getVehicleSN");
        enforcePermission("android.permission.BYDAUTO_VERSION_GET");
        if (this.mSettingsManagerImpl.getVersionSettingsManager() == null) {
            LogUtil.error(str, "SettingsAdapter still connecting, please wait");
            return null;
        }
        return this.mSettingsManagerImpl.getVersionSettingsManager().getVehicleSN();
    }

    public String[] getMcuVersion() {
        String str = TAG;
        LogUtil.debug(str, "getMcuVersion");
        enforcePermission("android.permission.BYDAUTO_VERSION_GET");
        if (this.mSettingsManagerImpl.getVersionSettingsManager() == null) {
            LogUtil.error(str, "SettingsAdapter still connecting, please wait");
            return null;
        }
        return this.mSettingsManagerImpl.getVersionSettingsManager().getMcuVersion();
    }
}
