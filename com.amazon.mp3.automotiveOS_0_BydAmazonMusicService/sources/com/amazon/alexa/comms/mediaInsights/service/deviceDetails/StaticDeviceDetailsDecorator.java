package com.amazon.alexa.comms.mediaInsights.service.deviceDetails;

import android.os.Build;
import java.util.HashMap;
import java.util.Map;
import lombok.NonNull;

/* JADX INFO: loaded from: classes2.dex */
public class StaticDeviceDetailsDecorator implements DeviceDetails {
    private static HashMap<String, String> staticDeviceDetailsMap;

    @NonNull
    private DeviceDetails deviceDetails;

    public enum StaticDeviceDetailKeys {
        DSN,
        DEVICE_ID,
        SOFTWARE_VERSION,
        HARDWARE,
        BUILD_TYPE,
        PLATFORM,
        MODEL
    }

    public StaticDeviceDetailsDecorator(@NonNull DeviceDetails deviceDetails) {
        if (deviceDetails == null) {
            throw new IllegalArgumentException("deviceDetails is null");
        }
        this.deviceDetails = deviceDetails;
    }

    static {
        HashMap<String, String> map = new HashMap<>();
        staticDeviceDetailsMap = map;
        map.put(StaticDeviceDetailKeys.DSN.toString(), "Unknown");
        staticDeviceDetailsMap.put(StaticDeviceDetailKeys.DEVICE_ID.toString(), Build.ID);
        staticDeviceDetailsMap.put(StaticDeviceDetailKeys.SOFTWARE_VERSION.toString(), Build.VERSION.RELEASE);
        staticDeviceDetailsMap.put(StaticDeviceDetailKeys.HARDWARE.toString(), Build.HARDWARE);
        staticDeviceDetailsMap.put(StaticDeviceDetailKeys.BUILD_TYPE.toString(), Build.TYPE);
        staticDeviceDetailsMap.put(StaticDeviceDetailKeys.PLATFORM.toString(), Build.DEVICE);
        staticDeviceDetailsMap.put(StaticDeviceDetailKeys.MODEL.toString(), Build.MODEL);
    }

    @Override // com.amazon.alexa.comms.mediaInsights.service.deviceDetails.DeviceDetails
    public Map<String, String> asMap() {
        Map<String, String> mapAsMap = this.deviceDetails.asMap();
        mapAsMap.putAll(staticDeviceDetailsMap);
        return mapAsMap;
    }
}
