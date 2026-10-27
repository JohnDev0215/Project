package com.amazon.music.metrics.mts.event.definition.automotive;

import com.amazon.music.metrics.mts.MTSEvent;
import com.amazon.music.metrics.mts.event.types.CarClassification;
import com.amazon.music.metrics.mts.event.types.CarSubDeviceAction;

/* JADX INFO: loaded from: classes4.dex */
public class CarDetectionBluetoothEvent extends MTSEvent {
    public CarDetectionBluetoothEvent(CarClassification carClassification, CarSubDeviceAction carSubDeviceAction, String str, String str2) {
        super("carDetectionBluetooth", 1L);
        addAttribute("carClassification", carClassification.getMetricValue());
        addAttribute("carSubDeviceAction", carSubDeviceAction.getMetricValue());
        addAttribute("bluetoothId", str);
        addAttribute("subDeviceName", str2);
    }
}
