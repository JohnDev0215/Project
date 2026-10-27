package com.ts.androidauto.fsdk.interfaces.vehicle;

import android.location.GpsStatus;
import android.location.Location;
import com.ts.androidauto.fsdk.common.VehicleConst;
import com.ts.androidauto.fsdk.data.VehicleAccelerometerData;
import com.ts.androidauto.fsdk.data.VehicleFuleData;
import com.ts.androidauto.fsdk.data.VehicleGear;
import com.ts.androidauto.fsdk.data.VehicleGyroscopeData;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes2.dex */
public interface IVehicleControllerAdapter {
    void addVehicleControllerListener(IVehicleControllerListener iVehicleControllerListener);

    boolean getCurrentCameraRearState();

    VehicleGear getGearPosition();

    Location getLastLocation();

    float getSpeed();

    ArrayList<VehicleConst.SensorType> getSupportedSensorList();

    VehicleAccelerometerData getVehicleAccelerometerData();

    int getVehicleDrivingStatus();

    VehicleFuleData getVehicleFuleData();

    GpsStatus getVehicleGpsStatus();

    VehicleGyroscopeData getVehicleGyroscopeData();

    boolean getWhetherEnableWirelessAa();

    void init();

    boolean isNightMode();

    boolean isParkingBreak();

    void registerSensorListener();

    void removeVehicleControllerListener(IVehicleControllerListener iVehicleControllerListener);

    void unregisterSensorListener();
}
