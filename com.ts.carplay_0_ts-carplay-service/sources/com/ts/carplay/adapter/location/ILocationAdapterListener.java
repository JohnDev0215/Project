package com.ts.carplay.adapter.location;

/* JADX INFO: loaded from: classes.dex */
public interface ILocationAdapterListener {
    default void onVehicleSpeedChanged(float vehicleSpeed) {
    }

    default void onVehicleGearModeChanged(int vehicleGearMode) {
    }

    default void onVehicleGpggaUpdate(String data) {
    }

    default void onVehicleGprmcUpdate(String data) {
    }
}
