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
        LogUtil.debug(TAG, "getBydSystemVersion");
        enforcePermission(BYDAutoVersionDevice.PERMISSION_BYDAUTO_VERSION_GET);
        if (this.mSettingsManagerImpl.getVersionSettingsManager() != null) {
            return this.mSettingsManagerImpl.getVersionSettingsManager().getBydSystemVersion();
        }
        LogUtil.error(TAG, "SettingsAdapter still connecting, please wait");
        return null;
    }

    public String getHwVersion() {
        LogUtil.debug(TAG, "getHwVersion");
        enforcePermission(BYDAutoVersionDevice.PERMISSION_BYDAUTO_VERSION_GET);
        if (this.mSettingsManagerImpl.getVersionSettingsManager() != null) {
            return this.mSettingsManagerImpl.getVersionSettingsManager().getHwVersion();
        }
        LogUtil.error(TAG, "SettingsAdapter still connecting, please wait");
        return null;
    }

    public String getInstrumentHwVersion() {
        LogUtil.debug(TAG, "getInstrumentHwVersion");
        enforcePermission(BYDAutoVersionDevice.PERMISSION_BYDAUTO_VERSION_GET);
        if (this.mSettingsManagerImpl.getVersionSettingsManager() != null) {
            return this.mSettingsManagerImpl.getVersionSettingsManager().getInstrumentHwVersion();
        }
        LogUtil.error(TAG, "SettingsAdapter still connecting, please wait");
        return null;
    }

    public String getInstrumentSwVersion() {
        LogUtil.debug(TAG, "getInstrumentSwVersion");
        enforcePermission(BYDAutoVersionDevice.PERMISSION_BYDAUTO_VERSION_GET);
        if (this.mSettingsManagerImpl.getVersionSettingsManager() != null) {
            return this.mSettingsManagerImpl.getVersionSettingsManager().getInstrumentSwVersion();
        }
        LogUtil.error(TAG, "SettingsAdapter still connecting, please wait");
        return null;
    }

    public String[] getMcuVersion() {
        LogUtil.debug(TAG, "getMcuVersion");
        enforcePermission(BYDAutoVersionDevice.PERMISSION_BYDAUTO_VERSION_GET);
        if (this.mSettingsManagerImpl.getVersionSettingsManager() != null) {
            return this.mSettingsManagerImpl.getVersionSettingsManager().getMcuVersion();
        }
        LogUtil.error(TAG, "SettingsAdapter still connecting, please wait");
        return null;
    }

    public String getVehicleSN() {
        LogUtil.debug(TAG, "getVehicleSN");
        enforcePermission(BYDAutoVersionDevice.PERMISSION_BYDAUTO_VERSION_GET);
        if (this.mSettingsManagerImpl.getVersionSettingsManager() != null) {
            return this.mSettingsManagerImpl.getVersionSettingsManager().getVehicleSN();
        }
        LogUtil.error(TAG, "SettingsAdapter still connecting, please wait");
        return null;
    }
}
