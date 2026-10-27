package android.hardware.bydauto.version.tsimpl;

import android.content.Context;
import android.hardware.bydauto.SettingsManagerImpl;
import android.hardware.bydauto.version.BYDAutoVersionDevice;
import com.ts.lib.settings.common.utilities.LogUtil;

/* JADX INFO: loaded from: classes.dex */
public class VersionManagerImpl {
    public static final String TAG = "VersionManagerImpl";
    private static volatile VersionManagerImpl sInstance;
    private Context mContext;
    private boolean mIgnoreBydPermission = false;
    private SettingsManagerImpl mSettingsManagerImpl;

    VersionManagerImpl(Context context) {
        this.mContext = context;
        this.mSettingsManagerImpl = SettingsManagerImpl.getInstance(context);
        enforcePermission(BYDAutoVersionDevice.PERMISSION_BYDAUTO_VERSION_COMMON);
    }

    private void enforcePermission(String str) {
        if (this.mIgnoreBydPermission || this.mContext.checkCallingOrSelfPermission(str) == 0) {
            return;
        }
        throw new SecurityException("requires permission " + str);
    }

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

    public String getBydSystemVersion() {
        String str = TAG;
        LogUtil.debug(str, "getBydSystemVersion");
        enforcePermission(BYDAutoVersionDevice.PERMISSION_BYDAUTO_VERSION_GET);
        if (this.mSettingsManagerImpl.getVersionSettingsManager() != null) {
            return this.mSettingsManagerImpl.getVersionSettingsManager().getBydSystemVersion();
        }
        LogUtil.error(str, "SettingsAdapter still connecting, please wait");
        return null;
    }

    public String getHwVersion() {
        String str = TAG;
        LogUtil.debug(str, "getHwVersion");
        enforcePermission(BYDAutoVersionDevice.PERMISSION_BYDAUTO_VERSION_GET);
        if (this.mSettingsManagerImpl.getVersionSettingsManager() != null) {
            return this.mSettingsManagerImpl.getVersionSettingsManager().getHwVersion();
        }
        LogUtil.error(str, "SettingsAdapter still connecting, please wait");
        return null;
    }

    public String getInstrumentHwVersion() {
        String str = TAG;
        LogUtil.debug(str, "getInstrumentHwVersion");
        enforcePermission(BYDAutoVersionDevice.PERMISSION_BYDAUTO_VERSION_GET);
        if (this.mSettingsManagerImpl.getVersionSettingsManager() != null) {
            return this.mSettingsManagerImpl.getVersionSettingsManager().getInstrumentHwVersion();
        }
        LogUtil.error(str, "SettingsAdapter still connecting, please wait");
        return null;
    }

    public String getInstrumentSwVersion() {
        String str = TAG;
        LogUtil.debug(str, "getInstrumentSwVersion");
        enforcePermission(BYDAutoVersionDevice.PERMISSION_BYDAUTO_VERSION_GET);
        if (this.mSettingsManagerImpl.getVersionSettingsManager() != null) {
            return this.mSettingsManagerImpl.getVersionSettingsManager().getInstrumentSwVersion();
        }
        LogUtil.error(str, "SettingsAdapter still connecting, please wait");
        return null;
    }

    public String[] getMcuVersion() {
        String str = TAG;
        LogUtil.debug(str, "getMcuVersion");
        enforcePermission(BYDAutoVersionDevice.PERMISSION_BYDAUTO_VERSION_GET);
        if (this.mSettingsManagerImpl.getVersionSettingsManager() != null) {
            return this.mSettingsManagerImpl.getVersionSettingsManager().getMcuVersion();
        }
        LogUtil.error(str, "SettingsAdapter still connecting, please wait");
        return null;
    }

    public String getVehicleSN() {
        String str = TAG;
        LogUtil.debug(str, "getVehicleSN");
        enforcePermission(BYDAutoVersionDevice.PERMISSION_BYDAUTO_VERSION_GET);
        if (this.mSettingsManagerImpl.getVersionSettingsManager() != null) {
            return this.mSettingsManagerImpl.getVersionSettingsManager().getVehicleSN();
        }
        LogUtil.error(str, "SettingsAdapter still connecting, please wait");
        return null;
    }
}
