package com.amazon.mp3.auto.carmode.detection;

import dagger.internal.Factory;
import dagger.internal.Preconditions;

/* JADX INFO: loaded from: classes3.dex */
public final class CarDetectorModule_CarBluetoothDetectorProviderFactory implements Factory<CarBluetoothDetector> {
    private final CarDetectorModule module;

    public CarDetectorModule_CarBluetoothDetectorProviderFactory(CarDetectorModule module) {
        this.module = module;
    }

    @Override // javax.inject.Provider
    public CarBluetoothDetector get() {
        return carBluetoothDetectorProvider(this.module);
    }

    public static CarDetectorModule_CarBluetoothDetectorProviderFactory create(CarDetectorModule module) {
        return new CarDetectorModule_CarBluetoothDetectorProviderFactory(module);
    }

    public static CarBluetoothDetector carBluetoothDetectorProvider(CarDetectorModule instance) {
        return (CarBluetoothDetector) Preconditions.checkNotNullFromProvides(instance.carBluetoothDetectorProvider());
    }
}
