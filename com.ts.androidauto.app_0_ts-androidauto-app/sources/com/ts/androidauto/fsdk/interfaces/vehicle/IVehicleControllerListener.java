package com.ts.androidauto.fsdk.interfaces.vehicle;

import com.ts.androidauto.fsdk.data.VehicleFuleData;
import com.ts.androidauto.fsdk.data.VehicleGear;

/* JADX INFO: loaded from: classes2.dex */
public interface IVehicleControllerListener {
    default void onVehicleNightModeChanged(boolean nightMode) {
    }

    default void onVehicleInfoReceived(boolean info, boolean serialNum) {
    }

    default void onVehicleGearChanged(VehicleGear gear) {
    }

    default void onDoorStatusChanged(boolean[] doorStatus) {
    }

    default void onTyreStatusChanged(float[] tyreStatus) {
    }

    default void onVehicleParkingBreakChanged(boolean parking) {
    }

    default void onDrivingStatusChanged(int status) {
    }

    default void onVehicleOdometerChanged(int meter) {
    }

    default void onVehicleFuleStatusChanged(VehicleFuleData fuleData) {
    }

    default void onMicMuteHwKeyChanged(boolean status) {
    }
}
