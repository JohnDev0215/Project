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
    protected static final String TAG = "BYDAutoYunDevice";
    public static final int YUN_COMMAND_BUSY = -2147482647;
    public static final int YUN_COMMAND_FAILED = -2147482648;
    public static final int YUN_COMMAND_INVALID_VALUE = -2147482645;
    public static final int YUN_COMMAND_SUCCESS = 0;
    public static final int YUN_COMMAND_TIMEOUT = -2147482646;
    private static volatile BYDAutoYunDevice sBYDAutoYunDevice;
    private YunManagerImpl sYunManagerImpl;

    BYDAutoYunDevice(Context con) {
        super(con);
        this.sYunManagerImpl = YunManagerImpl.getsInstance(con);
    }

    public static synchronized BYDAutoYunDevice getInstance(Context con) {
        if (sBYDAutoYunDevice == null) {
            synchronized (BYDAutoYunDevice.class) {
                if (sBYDAutoYunDevice == null) {
                    sBYDAutoYunDevice = new BYDAutoYunDevice(con.getApplicationContext());
                }
            }
        }
        return sBYDAutoYunDevice;
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public int getDevicetype() {
        return 0;
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public String getSetPermission() {
        return "";
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public String getGetPermission() {
        return PERMISSION_BYDAUTO_YUN_GET;
    }

    @Deprecated
    public void getAllStatus() {
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice, android.hardware.IBYDAutoDevice
    @Deprecated
    public boolean postEvent(int device_type, int event_type, byte[] value, Object data) {
        return false;
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice, android.hardware.IBYDAutoDevice
    @Deprecated
    public boolean postEvent(int device_type, int event_type, int value, Object data) {
        return false;
    }

    @Override // android.hardware.IBYDAutoDevice
    @Deprecated
    public int getType() {
        return 0;
    }

    @Override // android.hardware.IBYDAutoDevice
    @Deprecated
    public int[] getFeatureList() {
        return new int[0];
    }

    public void registerListener(AbsBYDAutoYunListener listener) {
        this.sYunManagerImpl.registerListener(listener);
    }

    @Deprecated
    public void registerListener(AbsBYDAutoYunListener listener, int[] featureIDs) {
        this.sYunManagerImpl.registerListener(listener, featureIDs);
    }

    public void unregisterListener(AbsBYDAutoYunListener listener) {
        this.sYunManagerImpl.unregisterListener(listener);
    }

    @Deprecated
    public void remoteAcControl(int p1, int p2, int p3, int p4) {
        this.sYunManagerImpl.remoteAcControl(p1, p2, p3, p4);
    }

    @Deprecated
    public void remoteSeatControl(int p1, int p2, int p3) {
        this.sYunManagerImpl.remoteSeatControl(p1, p2, p3);
    }

    @Deprecated
    public void remoteSeatControl(int p1, int p2, int p3, int p4, int p5) {
        this.sYunManagerImpl.remoteSeatControl(p1, p2, p3, p4, p5);
    }

    @Deprecated
    public void remoteSteeringHeatingControl(int p1, int p2) {
        this.sYunManagerImpl.remoteSteeringHeatingControl(p1, p2);
    }

    public void subscribe(List<CanInfo> canList) {
        this.sYunManagerImpl.subscribe(canList);
    }

    public void unSubscribe() {
        this.sYunManagerImpl.unSubscribe();
    }

    @Deprecated
    public void getCanBytesSync(int id, int cid) {
        this.sYunManagerImpl.getCanBytesSync(id, cid);
    }

    @Deprecated
    public byte[] getCanBytes(int id, int cid) {
        return this.sYunManagerImpl.getCanBytes(id, cid);
    }

    @Deprecated
    public void remoteControl(int contorl) {
        this.sYunManagerImpl.remoteControl(contorl);
    }

    @Deprecated
    public void remoteUnlockFourDoors() {
        this.sYunManagerImpl.remoteUnlockFourDoors();
    }

    public byte getWaterThermometer() {
        return this.sYunManagerImpl.getWaterThermometer();
    }

    public int getRemainBattery() {
        return this.sYunManagerImpl.getRemainBattery();
    }

    public byte getVehicleSpeed() {
        return this.sYunManagerImpl.getVehicleSpeed();
    }

    public void setAppActivateInfo(ActivateInfo info) {
        this.sYunManagerImpl.setAppActivateInfo(info);
    }

    public void setAppActivateInfo(ActivateInfoV2 info) {
        this.sYunManagerImpl.setAppActivateInfo(info);
    }

    public boolean isConnectTbox() {
        return this.sYunManagerImpl.isConnectTbox();
    }
}
