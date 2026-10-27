package android.hardware.bydauto.version;

import android.content.Context;
import android.hardware.bydauto.AbsBYDAutoDevice;
import android.hardware.bydauto.version.tsimpl.VersionManagerImpl;

/* JADX INFO: loaded from: classes.dex */
public final class BYDAutoVersionDevice extends AbsBYDAutoDevice {
    public static final String PERMISSION_BYDAUTO_VERSION_COMMON = "android.permission.BYDAUTO_VERSION_COMMON";
    public static final String PERMISSION_BYDAUTO_VERSION_GET = "android.permission.BYDAUTO_VERSION_GET";
    public static final String PERMISSION_BYDAUTO_VERSION_SET = "android.permission.BYDAUTO_VERSION_SET";
    protected static final String TAG = "BYDAutoVersionDevice";
    private static BYDAutoVersionDevice sBydAutoVersionDevice;
    private VersionManagerImpl mVersionManagerImpl;

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public int getDevicetype() {
        return 0;
    }

    @Override // android.hardware.IBYDAutoDevice
    public int[] getFeatureList() {
        return new int[0];
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public String getGetPermission() {
        return PERMISSION_BYDAUTO_VERSION_GET;
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public String getSetPermission() {
        return PERMISSION_BYDAUTO_VERSION_SET;
    }

    @Override // android.hardware.IBYDAutoDevice
    public int getType() {
        return 0;
    }

    BYDAutoVersionDevice(Context context) {
        super(context);
        this.mVersionManagerImpl = VersionManagerImpl.getsInstance(context);
    }

    public static synchronized BYDAutoVersionDevice getInstance(Context context) {
        if (sBydAutoVersionDevice == null) {
            sBydAutoVersionDevice = new BYDAutoVersionDevice(context.getApplicationContext());
        }
        return sBydAutoVersionDevice;
    }

    public String getBydSystemVersion() {
        return this.mVersionManagerImpl.getBydSystemVersion();
    }

    public String getHwVersion() {
        return this.mVersionManagerImpl.getHwVersion();
    }

    public String getInstrumentHwVersion() {
        return this.mVersionManagerImpl.getInstrumentHwVersion();
    }

    public String getInstrumentSwVersion() {
        return this.mVersionManagerImpl.getInstrumentSwVersion();
    }

    public String getVehicleSN() {
        return this.mVersionManagerImpl.getVehicleSN();
    }

    public String[] getMcuVersion() {
        return this.mVersionManagerImpl.getMcuVersion();
    }
}
