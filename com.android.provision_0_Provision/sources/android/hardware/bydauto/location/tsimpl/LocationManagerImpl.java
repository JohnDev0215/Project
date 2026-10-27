package android.hardware.bydauto.location.tsimpl;

import android.content.Context;
import android.hardware.bydauto.SettingsManagerImpl;
import android.hardware.bydauto.location.AbsBYDAutoLocationListener;
import android.hardware.bydauto.location.BYDAutoLocationDevice;
import com.ts.lib.settings.common.utilities.LogUtil;
import com.ts.lib.settings.location.OnLocationChangedListener;

/* JADX INFO: loaded from: classes.dex */
public class LocationManagerImpl {
    protected static final String TAG = "TS.LocationManagerImpl";
    private static volatile LocationManagerImpl sInstance;
    private Context mContext;
    private SettingsManagerImpl mSettingsManagerImpl;
    private AbsBYDAutoLocationListener mAbsBydAutoLocationListener = null;
    private boolean mIgnoreBydPermission = false;

    LocationManagerImpl(Context context) {
        this.mContext = context;
        enforcePermission(BYDAutoLocationDevice.PERMISSION_BYDAUTO_LOCATION_COMMON);
        SettingsManagerImpl settingsManagerImpl = SettingsManagerImpl.getInstance(this.mContext);
        this.mSettingsManagerImpl = settingsManagerImpl;
        settingsManagerImpl.setLocationChangedListener(new OnLocationChangedListener() { // from class: android.hardware.bydauto.location.tsimpl.LocationManagerImpl.1
            public void onLocUseChange() {
                LocationManagerImpl.this.mAbsBydAutoLocationListener.onLocUseChange();
            }
        });
    }

    public static LocationManagerImpl getInstance(Context context) {
        if (sInstance == null) {
            synchronized (LocationManagerImpl.class) {
                if (sInstance == null) {
                    sInstance = new LocationManagerImpl(context);
                }
            }
        }
        return sInstance;
    }

    private void enforcePermission(String str) {
        if (!this.mIgnoreBydPermission && this.mContext.checkCallingOrSelfPermission(str) != 0) {
            throw new SecurityException("requires permission " + str);
        }
    }

    public String[] getLocUsePackageName() {
        LogUtil.debug(TAG, "getLocUsePackageName");
        enforcePermission(BYDAutoLocationDevice.PERMISSION_BYDAUTO_LOCATION_GET);
        if (this.mSettingsManagerImpl.getLocationSettingManager() == null) {
            LogUtil.error(TAG, "SettingsAdapter still connecting, please wait");
            return null;
        }
        return this.mSettingsManagerImpl.getLocationSettingManager().getLocUsePackageName();
    }

    public void getAllStatus() {
        LogUtil.debug(TAG, ":not impl.getAllStatus");
        enforcePermission(BYDAutoLocationDevice.PERMISSION_BYDAUTO_LOCATION_GET);
    }

    public boolean postEvent(int i, int i2, int i3, Object obj) {
        LogUtil.debug(TAG, ":not impl.postEvent1");
        enforcePermission(BYDAutoLocationDevice.PERMISSION_BYDAUTO_LOCATION_COMMON);
        return false;
    }

    public boolean postEvent(int i, int i2, float f, Object obj) {
        LogUtil.debug(TAG, ":not impl.postEvent2");
        enforcePermission(BYDAutoLocationDevice.PERMISSION_BYDAUTO_LOCATION_COMMON);
        return false;
    }

    public int getType() {
        LogUtil.debug(TAG, ":not impl.getType");
        enforcePermission(BYDAutoLocationDevice.PERMISSION_BYDAUTO_LOCATION_GET);
        return -1;
    }

    public int[] getFeatureList() {
        LogUtil.debug(TAG, ":not impl.getFeatureList");
        enforcePermission(BYDAutoLocationDevice.PERMISSION_BYDAUTO_LOCATION_GET);
        return null;
    }

    public void registerListener(AbsBYDAutoLocationListener absBYDAutoLocationListener) {
        LogUtil.debug(TAG, "registerListener is run");
        enforcePermission(BYDAutoLocationDevice.PERMISSION_BYDAUTO_LOCATION_COMMON);
        this.mAbsBydAutoLocationListener = absBYDAutoLocationListener;
    }

    public void unregisterListener() {
        LogUtil.debug(TAG, "unregisterListener is run");
        enforcePermission(BYDAutoLocationDevice.PERMISSION_BYDAUTO_LOCATION_COMMON);
        this.mAbsBydAutoLocationListener = null;
    }
}
