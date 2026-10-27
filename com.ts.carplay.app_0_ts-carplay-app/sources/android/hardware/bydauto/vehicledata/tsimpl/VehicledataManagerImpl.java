package android.hardware.bydauto.vehicledata.tsimpl;

import android.content.Context;
import android.hardware.bydauto.vehicledata.AbsBYDAutoVehicleDataListener;

/* JADX INFO: loaded from: classes.dex */
public class VehicledataManagerImpl {
    protected static final String TAG = "VehicledataManagerImpl";
    private static VehicledataManagerImpl mVehicledataManagerImpl;

    public VehicledataManagerImpl(Context con) {
    }

    public static synchronized VehicledataManagerImpl getInstance(Context con) {
        if (mVehicledataManagerImpl == null) {
            mVehicledataManagerImpl = new VehicledataManagerImpl(con);
        }
        return mVehicledataManagerImpl;
    }

    public int getDevicetype() {
        return 0;
    }

    public String getSetPermission() {
        return null;
    }

    public String getGetPermission() {
        return null;
    }

    public int sendVehicleDataAcq(byte[] vehicleData) {
        return 0;
    }

    public int sendRegisterTable(int type, byte[] table) {
        return 0;
    }

    public int sendWholeFrameData(byte[] vehicleData) {
        return 0;
    }

    public boolean postEvent(int device_type, int event_type, int value, Object data) {
        return true;
    }

    public boolean postEvent(int device_type, int event_type, byte[] value, Object data) {
        return true;
    }

    public int getType() {
        return 0;
    }

    public int[] getFeatureList() {
        return null;
    }

    public void registerListener(AbsBYDAutoVehicleDataListener l) {
    }

    public void registerListener(AbsBYDAutoVehicleDataListener l, int[] featureIDs) {
    }

    public void unregisterListener(AbsBYDAutoVehicleDataListener l) {
    }
}
