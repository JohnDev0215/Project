package android.hardware.bydauto.mic;

import android.content.Context;
import android.hardware.bydauto.AbsBYDAutoDevice;
import android.hardware.bydauto.mic.tsimpl.MicManagerImpl;

/* JADX INFO: loaded from: classes.dex */
public class BYDAutoMicDevice extends AbsBYDAutoDevice {
    private static volatile BYDAutoMicDevice mBYDAutoMicDevice;
    private MicManagerImpl mMicManagerImpl;

    @Override // android.hardware.IBYDAutoDevice
    public int[] getFeatureList() {
        return new int[0];
    }

    @Override // android.hardware.IBYDAutoDevice
    public int getType() {
        return 0;
    }

    private BYDAutoMicDevice(Context context) {
        super(context);
        this.mMicManagerImpl = MicManagerImpl.getsInstance(context);
    }

    public static BYDAutoMicDevice getInstance(Context context) {
        if (mBYDAutoMicDevice == null) {
            synchronized (BYDAutoMicDevice.class) {
                if (mBYDAutoMicDevice == null) {
                    mBYDAutoMicDevice = new BYDAutoMicDevice(context.getApplicationContext());
                }
            }
        }
        return mBYDAutoMicDevice;
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public int getDevicetype() {
        return this.mMicManagerImpl.getDevicetype();
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public String getSetPermission() {
        return this.mMicManagerImpl.getSetPermission();
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public String getGetPermission() {
        return this.mMicManagerImpl.getGetPermission();
    }

    public int getChargeStatus() {
        return this.mMicManagerImpl.getChargeStatus();
    }

    public int getPowerStatus() {
        return this.mMicManagerImpl.getPowerStatus();
    }

    public String getMicUsePackageName() {
        return this.mMicManagerImpl.getMicUsePackageName();
    }

    public void registerDeviceEventListener(DeviceEventListener deviceEventListener) {
        this.mMicManagerImpl.registerListener(deviceEventListener);
    }

    public void unregisterDeviceEventListener() {
        this.mMicManagerImpl.unregisterListener();
    }
}
