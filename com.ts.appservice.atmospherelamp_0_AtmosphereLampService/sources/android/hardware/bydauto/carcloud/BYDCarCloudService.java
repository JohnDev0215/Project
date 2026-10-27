package android.hardware.bydauto.carcloud;

import android.content.Context;
import android.hardware.bydauto.AbsBYDAutoDevice;
import android.hardware.bydauto.carcloud.tsimpl.CarCloudManagerImpl;

/* JADX INFO: loaded from: classes.dex */
public class BYDCarCloudService extends AbsBYDAutoDevice {
    protected static final String TAG = "BYDCarCloudService";
    private static volatile BYDCarCloudService mBYDCarCloudService = null;
    private CarCloudManagerImpl mCarCloudManagerImpl;

    protected BYDCarCloudService(Context con) {
        super(con);
        this.mCarCloudManagerImpl = null;
        this.mCarCloudManagerImpl = CarCloudManagerImpl.getInstance(con);
    }

    @Override // android.hardware.IBYDAutoDevice
    public int getType() {
        return 0;
    }

    @Override // android.hardware.IBYDAutoDevice
    public int[] getFeatureList() {
        return new int[0];
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public int getDevicetype() {
        return 0;
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public String getSetPermission() {
        return null;
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public String getGetPermission() {
        return null;
    }

    public static synchronized BYDCarCloudService getInstance(Context con) {
        if (mBYDCarCloudService == null) {
            mBYDCarCloudService = new BYDCarCloudService(con.getApplicationContext());
        }
        return mBYDCarCloudService;
    }
}
