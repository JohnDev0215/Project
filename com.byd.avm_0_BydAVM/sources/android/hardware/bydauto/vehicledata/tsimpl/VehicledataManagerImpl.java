package android.hardware.bydauto.vehicledata.tsimpl;

import android.content.Context;
import android.hardware.bydauto.vehicledata.AbsBYDAutoVehicleDataListener;

/* JADX INFO: loaded from: classes.dex */
public class VehicledataManagerImpl {
    protected static final String TAG = "VehicledataManagerImpl";
    private static VehicledataManagerImpl mVehicledataManagerImpl;

    public VehicledataManagerImpl(Context context) {
    }

    public static synchronized VehicledataManagerImpl getInstance(Context context) {
        if (mVehicledataManagerImpl == null) {
            mVehicledataManagerImpl = new VehicledataManagerImpl(context);
        }
        return mVehicledataManagerImpl;
    }

    public int getDevicetype() {
        return 0;
    }

    public int[] getFeatureList() {
        return null;
    }

    public String getGetPermission() {
        return null;
    }

    public String getSetPermission() {
        return null;
    }

    public int getType() {
        return 0;
    }

    public boolean postEvent(int i, int i2, int i3, Object obj) {
        return true;
    }

    public boolean postEvent(int i, int i2, byte[] bArr, Object obj) {
        return true;
    }

    public void registerListener(AbsBYDAutoVehicleDataListener absBYDAutoVehicleDataListener) {
    }

    public void registerListener(AbsBYDAutoVehicleDataListener absBYDAutoVehicleDataListener, int[] iArr) {
    }

    public int sendRegisterTable(int i, byte[] bArr) {
        return 0;
    }

    public int sendVehicleDataAcq(byte[] bArr) {
        return 0;
    }

    public int sendWholeFrameData(byte[] bArr) {
        return 0;
    }

    public void unregisterListener(AbsBYDAutoVehicleDataListener absBYDAutoVehicleDataListener) {
    }
}
