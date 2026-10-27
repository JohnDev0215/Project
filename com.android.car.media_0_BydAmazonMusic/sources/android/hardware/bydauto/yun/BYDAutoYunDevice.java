package android.hardware.bydauto.yun;

import android.content.Context;
import android.hardware.bydauto.AbsBYDAutoDevice;
import android.hardware.bydauto.yun.tsimpl.YunManagerImpl;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class BYDAutoYunDevice extends AbsBYDAutoDevice {
    public static final String PERMISSION_BYDAUTO_YUN_COMMON = "android.permission.BYDAUTO_YUN_COMMON";
    public static final String PERMISSION_BYDAUTO_YUN_GET = "android.permission.BYDAUTO_YUN_GET";
    public static final String PERMISSION_BYDAUTO_YUN_SET = "android.permission.BYDAUTO_YUN_SET";
    public static final String TAG = "BYDAutoYunDevice";
    public static final int YUN_COMMAND_BUSY = -2147482647;
    public static final int YUN_COMMAND_FAILED = -2147482648;
    public static final int YUN_COMMAND_INVALID_VALUE = -2147482645;
    public static final int YUN_COMMAND_SUCCESS = 0;
    public static final int YUN_COMMAND_TIMEOUT = -2147482646;
    private static volatile BYDAutoYunDevice sBYDAutoYunDevice;
    private YunManagerImpl sYunManagerImpl;

    public BYDAutoYunDevice(Context context) {
        super(context);
        this.sYunManagerImpl = YunManagerImpl.getsInstance(context);
    }

    public static synchronized BYDAutoYunDevice getInstance(Context context) {
        if (sBYDAutoYunDevice == null) {
            synchronized (BYDAutoYunDevice.class) {
                if (sBYDAutoYunDevice == null) {
                    sBYDAutoYunDevice = new BYDAutoYunDevice(context.getApplicationContext());
                }
            }
        }
        return sBYDAutoYunDevice;
    }

    @Deprecated
    public void getAllStatus() {
    }

    @Deprecated
    public byte[] getCanBytes(int i, int i2) {
        return this.sYunManagerImpl.getCanBytes(i, i2);
    }

    @Deprecated
    public void getCanBytesSync(int i, int i2) {
        this.sYunManagerImpl.getCanBytesSync(i, i2);
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public int getDevicetype() {
        return 0;
    }

    @Override // android.hardware.IBYDAutoDevice
    @Deprecated
    public int[] getFeatureList() {
        return new int[0];
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public String getGetPermission() {
        return PERMISSION_BYDAUTO_YUN_GET;
    }

    public int getRemainBattery() {
        return this.sYunManagerImpl.getRemainBattery();
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public String getSetPermission() {
        return "";
    }

    @Override // android.hardware.IBYDAutoDevice
    @Deprecated
    public int getType() {
        return 0;
    }

    public byte getVehicleSpeed() {
        return this.sYunManagerImpl.getVehicleSpeed();
    }

    public byte getWaterThermometer() {
        return this.sYunManagerImpl.getWaterThermometer();
    }

    public boolean isConnectTbox() {
        return this.sYunManagerImpl.isConnectTbox();
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice, android.hardware.IBYDAutoDevice
    @Deprecated
    public boolean postEvent(int i, int i2, int i3, Object obj) {
        return false;
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice, android.hardware.IBYDAutoDevice
    @Deprecated
    public boolean postEvent(int i, int i2, byte[] bArr, Object obj) {
        return false;
    }

    public void registerListener(AbsBYDAutoYunListener absBYDAutoYunListener) {
        this.sYunManagerImpl.registerListener(absBYDAutoYunListener);
    }

    @Deprecated
    public void remoteAcControl(int i, int i2, int i3, int i4) {
        this.sYunManagerImpl.remoteAcControl(i, i2, i3, i4);
    }

    @Deprecated
    public void remoteControl(int i) {
        this.sYunManagerImpl.remoteControl(i);
    }

    @Deprecated
    public void remoteSeatControl(int i, int i2, int i3) {
        this.sYunManagerImpl.remoteSeatControl(i, i2, i3);
    }

    @Deprecated
    public void remoteSteeringHeatingControl(int i, int i2) {
        this.sYunManagerImpl.remoteSteeringHeatingControl(i, i2);
    }

    @Deprecated
    public void remoteUnlockFourDoors() {
        this.sYunManagerImpl.remoteUnlockFourDoors();
    }

    public void setAppActivateInfo(ActivateInfo activateInfo) {
        this.sYunManagerImpl.setAppActivateInfo(activateInfo);
    }

    public void subscribe(List<CanInfo> list) {
        this.sYunManagerImpl.subscribe(list);
    }

    public void unSubscribe() {
        this.sYunManagerImpl.unSubscribe();
    }

    public void unregisterListener(AbsBYDAutoYunListener absBYDAutoYunListener) {
        this.sYunManagerImpl.unregisterListener(absBYDAutoYunListener);
    }

    @Deprecated
    public void registerListener(AbsBYDAutoYunListener absBYDAutoYunListener, int[] iArr) {
        this.sYunManagerImpl.registerListener(absBYDAutoYunListener, iArr);
    }

    @Deprecated
    public void remoteSeatControl(int i, int i2, int i3, int i4, int i5) {
        this.sYunManagerImpl.remoteSeatControl(i, i2, i3, i4, i5);
    }

    public void setAppActivateInfo(ActivateInfoV2 activateInfoV2) {
        this.sYunManagerImpl.setAppActivateInfo(activateInfoV2);
    }
}
