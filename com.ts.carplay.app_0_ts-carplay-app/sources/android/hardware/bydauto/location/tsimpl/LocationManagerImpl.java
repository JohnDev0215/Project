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

    LocationManagerImpl(Context con) {
        this.mContext = con;
        enforcePermission(BYDAutoLocationDevice.PERMISSION_BYDAUTO_LOCATION_COMMON);
        SettingsManagerImpl settingsManagerImpl = SettingsManagerImpl.getInstance(this.mContext);
        this.mSettingsManagerImpl = settingsManagerImpl;
        settingsManagerImpl.setLocationChangedListener(new OnLocationChangedListener() { // from class: android.hardware.bydauto.location.tsimpl.LocationManagerImpl.1
            @Override // com.ts.lib.settings.location.OnLocationChangedListener
            public void onLocUseChange() {
                LocationManagerImpl.this.mAbsBydAutoLocationListener.onLocUseChange();
            }
        });
    }

    public static LocationManagerImpl getInstance(Context con) {
        if (sInstance == null) {
            synchronized (LocationManagerImpl.class) {
                if (sInstance == null) {
                    sInstance = new LocationManagerImpl(con);
                }
            }
        }
        return sInstance;
    }

    private void enforcePermission(String permissionName) {
        if (!this.mIgnoreBydPermission && this.mContext.checkCallingOrSelfPermission(permissionName) != 0) {
            throw new SecurityException("requires permission " + permissionName);
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

    public boolean postEvent(int device_type, int event_type, int value, Object data) {
        LogUtil.debug(TAG, ":not impl.postEvent1");
        enforcePermission(BYDAutoLocationDevice.PERMISSION_BYDAUTO_LOCATION_COMMON);
        return false;
    }

    public boolean postEvent(int device_type, int event_type, float value, Object data) {
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

    public void registerListener(AbsBYDAutoLocationListener locationListener) {
        LogUtil.debug(TAG, "registerListener is run");
        enforcePermission(BYDAutoLocationDevice.PERMISSION_BYDAUTO_LOCATION_COMMON);
        this.mAbsBydAutoLocationListener = locationListener;
    }

    public void unregisterListener() {
        LogUtil.debug(TAG, "unregisterListener is run");
        enforcePermission(BYDAutoLocationDevice.PERMISSION_BYDAUTO_LOCATION_COMMON);
        this.mAbsBydAutoLocationListener = null;
    }
}
