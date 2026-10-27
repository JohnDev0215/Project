package android.hardware.bydauto.version.tsimpl;

import android.content.Context;
import android.hardware.bydauto.SettingsManagerImpl;
import android.hardware.bydauto.version.BYDAutoVersionDevice;
import com.ts.lib.settings.common.utilities.LogUtil;

/* JADX INFO: loaded from: classes.dex */
public class VersionManagerImpl {
    public static final String TAG = VersionManagerImpl.class.getSimpleName();
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
        enforcePermission(BYDAutoVersionDevice.PERMISSION_BYDAUTO_VERSION_COMMON);
    }

    private void enforcePermission(String permissionName) {
        if (!this.mIgnoreBydPermission && this.mContext.checkCallingOrSelfPermission(permissionName) != 0) {
            throw new SecurityException("requires permission " + permissionName);
        }
    }

    public String getBydSystemVersion() {
        LogUtil.debug(TAG, "getBydSystemVersion");
        enforcePermission(BYDAutoVersionDevice.PERMISSION_BYDAUTO_VERSION_GET);
        if (this.mSettingsManagerImpl.getVersionSettingsManager() == null) {
            LogUtil.error(TAG, "SettingsAdapter still connecting, please wait");
            return null;
        }
        return this.mSettingsManagerImpl.getVersionSettingsManager().getBydSystemVersion();
    }

    public String getHwVersion() {
        LogUtil.debug(TAG, "getHwVersion");
        enforcePermission(BYDAutoVersionDevice.PERMISSION_BYDAUTO_VERSION_GET);
        if (this.mSettingsManagerImpl.getVersionSettingsManager() == null) {
            LogUtil.error(TAG, "SettingsAdapter still connecting, please wait");
            return null;
        }
        return this.mSettingsManagerImpl.getVersionSettingsManager().getHwVersion();
    }

    public String getInstrumentHwVersion() {
        LogUtil.debug(TAG, "getInstrumentHwVersion");
        enforcePermission(BYDAutoVersionDevice.PERMISSION_BYDAUTO_VERSION_GET);
        if (this.mSettingsManagerImpl.getVersionSettingsManager() == null) {
            LogUtil.error(TAG, "SettingsAdapter still connecting, please wait");
            return null;
        }
        return this.mSettingsManagerImpl.getVersionSettingsManager().getInstrumentHwVersion();
    }

    public String getInstrumentSwVersion() {
        LogUtil.debug(TAG, "getInstrumentSwVersion");
        enforcePermission(BYDAutoVersionDevice.PERMISSION_BYDAUTO_VERSION_GET);
        if (this.mSettingsManagerImpl.getVersionSettingsManager() == null) {
            LogUtil.error(TAG, "SettingsAdapter still connecting, please wait");
            return null;
        }
        return this.mSettingsManagerImpl.getVersionSettingsManager().getInstrumentSwVersion();
    }

    public String getVehicleSN() {
        LogUtil.debug(TAG, "getVehicleSN");
        enforcePermission(BYDAutoVersionDevice.PERMISSION_BYDAUTO_VERSION_GET);
        if (this.mSettingsManagerImpl.getVersionSettingsManager() == null) {
            LogUtil.error(TAG, "SettingsAdapter still connecting, please wait");
            return null;
        }
        return this.mSettingsManagerImpl.getVersionSettingsManager().getVehicleSN();
    }

    public String[] getMcuVersion() {
        LogUtil.debug(TAG, "getMcuVersion");
        enforcePermission(BYDAutoVersionDevice.PERMISSION_BYDAUTO_VERSION_GET);
        if (this.mSettingsManagerImpl.getVersionSettingsManager() == null) {
            LogUtil.error(TAG, "SettingsAdapter still connecting, please wait");
            return null;
        }
        return this.mSettingsManagerImpl.getVersionSettingsManager().getMcuVersion();
    }
}
