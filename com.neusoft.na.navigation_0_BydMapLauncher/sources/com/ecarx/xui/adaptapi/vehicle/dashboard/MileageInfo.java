package com.ecarx.xui.adaptapi.vehicle.dashboard;

import android.car.VehiclePropertyType;
import android.util.Log;
import androidx.core.view.MotionEventCompat;
import com.ecarx.xui.adaptapi.hvac.specific.Define;
import com.neusoft.alfus.RpcManager;

/* JADX INFO: loaded from: classes2.dex */
public class MileageInfo implements IMileageInfo {
    public static final String TAG = "MileageInfo";
    private long enduranceMileage;

    @Override // com.ecarx.xui.adaptapi.vehicle.dashboard.IMileageInfo
    public long getTripDuration() {
        return -2147483647L;
    }

    @Override // com.ecarx.xui.adaptapi.vehicle.dashboard.IMileageInfo
    public long getTripMileage() {
        return -2147483647L;
    }

    public void setEnduranceMileage(long j) {
        this.enduranceMileage = j;
    }

    @Override // com.ecarx.xui.adaptapi.vehicle.dashboard.IMileageInfo
    public long getTotalMileage() {
        byte[] bArr = new byte[25];
        bArr[0] = 24;
        byte[] message = RpcManager.getInstance().getMessage(1881, bArr);
        int i = ((message[4] & 255) | (((message[2] << 16) & VehiclePropertyType.MASK) | ((message[3] << 8) & MotionEventCompat.ACTION_POINTER_INDEX_MASK))) / 10;
        Log.d(TAG, "IPK total mileage" + i);
        int i2 = i * 1000;
        Log.d(TAG, "getTotalMileage result = " + i2);
        return i2;
    }

    @Override // com.ecarx.xui.adaptapi.vehicle.dashboard.IMileageInfo
    public long getEnduranceMileage() {
        return this.enduranceMileage;
    }

    @Override // com.ecarx.xui.adaptapi.vehicle.dashboard.IMileageInfo
    public long getNextMaintenanceMileage() {
        byte[] bArr = new byte[25];
        bArr[0] = Define.cSubCodeSetAcSync;
        byte[] message = RpcManager.getInstance().getMessage(1881, bArr);
        int i = (((char) message[2]) & 255) | (((((char) message[1]) & 255) << 8) & MotionEventCompat.ACTION_POINTER_INDEX_MASK);
        Log.d(TAG, "IPK NextMaintenanceMileage" + i);
        int i2 = i * 1000;
        Log.d(TAG, "getNextMaintenanceMileage result = " + i2);
        return i2;
    }
}
