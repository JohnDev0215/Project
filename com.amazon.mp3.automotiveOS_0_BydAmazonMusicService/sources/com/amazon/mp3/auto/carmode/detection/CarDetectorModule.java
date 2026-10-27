package com.amazon.mp3.auto.carmode.detection;

import android.bluetooth.BluetoothAdapter;
import com.amazon.music.metrics.MetricsHolder;
import com.amazon.music.metrics.MetricsManager;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: CarDetectorModule.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0007\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H\u0007J\u0010\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0004H\u0007¨\u0006\b"}, d2 = {"Lcom/amazon/mp3/auto/carmode/detection/CarDetectorModule;", "", "()V", "carBluetoothDetectorProvider", "Lcom/amazon/mp3/auto/carmode/detection/CarBluetoothDetector;", "carDetectorProvider", "Lcom/amazon/mp3/auto/carmode/detection/CarDetector;", "carBluetoothDetector", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class CarDetectorModule {
    public final CarDetector carDetectorProvider(CarBluetoothDetector carBluetoothDetector) {
        Intrinsics.checkNotNullParameter(carBluetoothDetector, "carBluetoothDetector");
        return new CarDetector(carBluetoothDetector);
    }

    public final CarBluetoothDetector carBluetoothDetectorProvider() {
        BluetoothAdapter defaultAdapter = BluetoothAdapter.getDefaultAdapter();
        MetricsManager manager = MetricsHolder.getManager();
        Intrinsics.checkNotNullExpressionValue(manager, "getManager()");
        return new CarBluetoothDetector(defaultAdapter, manager);
    }
}
