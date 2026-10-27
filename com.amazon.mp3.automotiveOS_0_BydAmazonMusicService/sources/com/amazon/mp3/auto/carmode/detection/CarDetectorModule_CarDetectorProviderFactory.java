package com.amazon.mp3.auto.carmode.detection;

import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

/* JADX INFO: loaded from: classes3.dex */
public final class CarDetectorModule_CarDetectorProviderFactory implements Factory<CarDetector> {
    private final Provider<CarBluetoothDetector> carBluetoothDetectorProvider;
    private final CarDetectorModule module;

    public CarDetectorModule_CarDetectorProviderFactory(CarDetectorModule module, Provider<CarBluetoothDetector> carBluetoothDetectorProvider) {
        this.module = module;
        this.carBluetoothDetectorProvider = carBluetoothDetectorProvider;
    }

    @Override // javax.inject.Provider
    public CarDetector get() {
        return carDetectorProvider(this.module, this.carBluetoothDetectorProvider.get());
    }

    public static CarDetectorModule_CarDetectorProviderFactory create(CarDetectorModule module, Provider<CarBluetoothDetector> carBluetoothDetectorProvider) {
        return new CarDetectorModule_CarDetectorProviderFactory(module, carBluetoothDetectorProvider);
    }

    public static CarDetector carDetectorProvider(CarDetectorModule instance, CarBluetoothDetector carBluetoothDetector) {
        return (CarDetector) Preconditions.checkNotNullFromProvides(instance.carDetectorProvider(carBluetoothDetector));
    }
}
