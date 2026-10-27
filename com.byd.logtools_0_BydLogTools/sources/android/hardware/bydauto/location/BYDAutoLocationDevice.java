package android.hardware.bydauto.location;

import android.content.Context;
import android.hardware.bydauto.AbsBYDAutoDevice;
import android.hardware.bydauto.location.tsimpl.LocationManagerImpl;

/* JADX INFO: loaded from: classes.dex */
public final class BYDAutoLocationDevice extends AbsBYDAutoDevice {
    public static final double LOCATION_ALTITUDE_INVALID = 8001.0d;
    public static final double LOCATION_ALTITUDE_MAX = 8000.0d;
    public static final double LOCATION_ALTITUDE_MIN = -8000.0d;
    public static final int LOCATION_COMMAND_BUSY = -2147482647;
    public static final int LOCATION_COMMAND_FAILED = -2147482648;
    public static final int LOCATION_COMMAND_INVALID_VALUE = -2147482645;
    public static final int LOCATION_COMMAND_SUCCESS = 0;
    public static final int LOCATION_COMMAND_TIMEOUT = -2147482646;
    public static final int LOCATION_FIXPOSITION_FAIL = 0;
    public static final int LOCATION_FIXPOSITION_SUCCESS = 1;
    public static final int LOCATION_GPS_SPEED_MAX = 240;
    public static final int LOCATION_GPS_SPEED_MIN = 0;
    public static final double LOCATION_LATITUDE_MAX = 90.0d;
    public static final double LOCATION_LATITUDE_MIN = 0.0d;
    public static final int LOCATION_LATITUDE_TYPE_NORTH = 2;
    public static final int LOCATION_LATITUDE_TYPE_SOUTH = 1;
    public static final double LOCATION_LONGITUDE_MAX = 180.0d;
    public static final double LOCATION_LONGITUDE_MIN = 0.0d;
    public static final int LOCATION_LONGITUDE_TYPE_EAST = 1;
    public static final int LOCATION_LONGITUDE_TYPE_WEST = 2;
    public static final float LOCATION_ORIENTATION_INVALID = 360.0f;
    public static final float LOCATION_ORIENTATION_MAX = 359.0f;
    public static final float LOCATION_ORIENTATION_MIN = 0.0f;
    public static final String PERMISSION_BYDAUTO_LOCATION_COMMON = "android.permission.BYDAUTO_LOCATION_COMMON";
    public static final String PERMISSION_BYDAUTO_LOCATION_GET = "android.permission.BYDAUTO_LOCATION_GET";
    public static final String PERMISSION_BYDAUTO_LOCATION_SET = "android.permission.BYDAUTO_LOCATION_SET";
    protected static final String TAG = "BYDAutoLocationDevice";
    public static final int VISIBLE_SATELLITE_NUMBER_MAX = 50;
    public static final int VISIBLE_SATELLITE_NUMBER_MIN = 0;
    private static BYDAutoLocationDevice sBydAutoLocationDevice;
    private LocationManagerImpl mLocationManagerImpl;

    BYDAutoLocationDevice(Context context) {
        super(context);
        this.mLocationManagerImpl = null;
        this.mLocationManagerImpl = LocationManagerImpl.getInstance(context);
    }

    public static synchronized BYDAutoLocationDevice getInstance(Context context) {
        if (sBydAutoLocationDevice == null) {
            sBydAutoLocationDevice = new BYDAutoLocationDevice(context);
        }
        return sBydAutoLocationDevice;
    }

    public void getAllStatus() {
        this.mLocationManagerImpl.getAllStatus();
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public int getDevicetype() {
        return 1017;
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public int[] getFeatureList() {
        return this.mLocationManagerImpl.getFeatureList();
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public String getGetPermission() {
        return PERMISSION_BYDAUTO_LOCATION_GET;
    }

    public String[] getLocUsePackageName() {
        return this.mLocationManagerImpl.getLocUsePackageName();
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public String getSetPermission() {
        return PERMISSION_BYDAUTO_LOCATION_SET;
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public int getType() {
        return this.mLocationManagerImpl.getType();
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public boolean postEvent(int i, int i2, int i3, Object obj) {
        return this.mLocationManagerImpl.postEvent(i, i2, i3, obj);
    }

    public void registerListener(AbsBYDAutoLocationListener absBYDAutoLocationListener) {
        this.mLocationManagerImpl.registerListener(absBYDAutoLocationListener);
    }

    public void unregisterListener() {
        this.mLocationManagerImpl.unregisterListener();
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public boolean postEvent(int i, int i2, float f2, Object obj) {
        return this.mLocationManagerImpl.postEvent(i, i2, f2, obj);
    }
}
