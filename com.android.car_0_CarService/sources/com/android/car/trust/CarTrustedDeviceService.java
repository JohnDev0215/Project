package com.android.car.trust;

import android.app.ActivityManager;
import android.bluetooth.BluetoothDevice;
import android.car.trust.TrustedDeviceInfo;
import android.content.Context;
import android.util.Log;
import com.android.car.CarServiceBase;
import com.android.car.trust.CarTrustAgentBleManager;
import java.io.PrintWriter;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
@Deprecated
public class CarTrustedDeviceService implements CarServiceBase, CarTrustAgentBleManager.BleEventCallback {
    private static final String TAG = CarTrustedDeviceService.class.getSimpleName();
    private CarTrustAgentBleManager mCarTrustAgentBleManager;
    private CarTrustAgentEnrollmentService mCarTrustAgentEnrollmentService;
    private CarTrustAgentUnlockService mCarTrustAgentUnlockService;
    private final Context mContext;

    public CarTrustedDeviceService(Context context) {
        this.mContext = context;
        BlePeripheralManager blePeripheralManager = new BlePeripheralManager(context);
        this.mCarTrustAgentBleManager = new CarTrustAgentBleManager(context, blePeripheralManager);
        this.mCarTrustAgentEnrollmentService = new CarTrustAgentEnrollmentService(this.mContext, this, this.mCarTrustAgentBleManager);
        this.mCarTrustAgentUnlockService = new CarTrustAgentUnlockService(this.mContext, this, this.mCarTrustAgentBleManager);
    }

    @Override // com.android.car.CarServiceBase
    public synchronized void init() {
        this.mCarTrustAgentEnrollmentService.init();
        this.mCarTrustAgentUnlockService.init();
        this.mCarTrustAgentBleManager.addBleEventCallback(this);
    }

    @Override // com.android.car.CarServiceBase
    public synchronized void release() {
        this.mCarTrustAgentBleManager.cleanup();
        this.mCarTrustAgentEnrollmentService.release();
        this.mCarTrustAgentUnlockService.release();
    }

    public CarTrustAgentEnrollmentService getCarTrustAgentEnrollmentService() {
        return this.mCarTrustAgentEnrollmentService;
    }

    public CarTrustAgentUnlockService getCarTrustAgentUnlockService() {
        return this.mCarTrustAgentUnlockService;
    }

    @Override // com.android.car.trust.CarTrustAgentBleManager.BleEventCallback
    public void onRemoteDeviceConnected(BluetoothDevice device) {
        this.mCarTrustAgentEnrollmentService.onRemoteDeviceConnected(device);
        this.mCarTrustAgentUnlockService.onRemoteDeviceConnected(device);
    }

    @Override // com.android.car.trust.CarTrustAgentBleManager.BleEventCallback
    public void onRemoteDeviceDisconnected(BluetoothDevice device) {
        this.mCarTrustAgentEnrollmentService.onRemoteDeviceDisconnected(device);
        this.mCarTrustAgentUnlockService.onRemoteDeviceDisconnected(device);
    }

    @Override // com.android.car.trust.CarTrustAgentBleManager.BleEventCallback
    public void onClientDeviceNameRetrieved(String deviceName) {
        this.mCarTrustAgentEnrollmentService.onClientDeviceNameRetrieved(deviceName);
    }

    void cleanupBleService() {
        if (Log.isLoggable(TAG, 3)) {
            Log.d(TAG, "cleanupBleService");
        }
        this.mCarTrustAgentBleManager.stopGattServer();
        this.mCarTrustAgentBleManager.stopEnrollmentAdvertising();
        this.mCarTrustAgentBleManager.stopUnlockAdvertising();
    }

    @Override // com.android.car.CarServiceBase
    public void dump(PrintWriter writer) {
        writer.println("*CarTrustedDeviceService*");
        int uid = ActivityManager.getCurrentUser();
        writer.println("current user id: " + uid);
        List<TrustedDeviceInfo> deviceInfos = this.mCarTrustAgentEnrollmentService.getEnrolledDeviceInfosForUser(uid);
        writer.println(getDeviceInfoListString(uid, deviceInfos));
        this.mCarTrustAgentEnrollmentService.dump(writer);
        this.mCarTrustAgentUnlockService.dump(writer);
    }

    private static String getDeviceInfoListString(int uid, List<TrustedDeviceInfo> deviceInfos) {
        StringBuilder sb = new StringBuilder();
        sb.append("device list of (user : ");
        sb.append(uid);
        sb.append("):");
        if (deviceInfos != null && deviceInfos.size() > 0) {
            for (int i = 0; i < deviceInfos.size(); i++) {
                sb.append("\n\tdevice# ");
                sb.append(i + 1);
                sb.append(" : ");
                sb.append(deviceInfos.get(i).toString());
            }
        } else {
            sb.append("\n\tno device listed");
        }
        return sb.toString();
    }
}
