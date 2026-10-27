package android.hardware.bydauto.mic;

import android.content.Context;
import android.hardware.bydauto.AbsBYDAutoDevice;
import android.hardware.bydauto.mic.tsimpl.MicManagerImpl;

/* JADX INFO: loaded from: classes.dex */
public class BYDAutoMicDevice extends AbsBYDAutoDevice {
    private static volatile BYDAutoMicDevice mBYDAutoMicDevice;
    private MicManagerImpl mMicManagerImpl;

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

    public int getChargeStatus() {
        return this.mMicManagerImpl.getChargeStatus();
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public int getDevicetype() {
        return this.mMicManagerImpl.getDevicetype();
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public int[] getFeatureList() {
        return new int[0];
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public String getGetPermission() {
        return this.mMicManagerImpl.getGetPermission();
    }

    public String getMicUsePackageName() {
        return this.mMicManagerImpl.getMicUsePackageName();
    }

    public int getPowerStatus() {
        return this.mMicManagerImpl.getPowerStatus();
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public String getSetPermission() {
        return this.mMicManagerImpl.getSetPermission();
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public int getType() {
        return 0;
    }

    public void registerDeviceEventListener(DeviceEventListener deviceEventListener) {
        this.mMicManagerImpl.registerListener(deviceEventListener);
    }

    public void unregisterDeviceEventListener() {
        this.mMicManagerImpl.unregisterListener();
    }
}
