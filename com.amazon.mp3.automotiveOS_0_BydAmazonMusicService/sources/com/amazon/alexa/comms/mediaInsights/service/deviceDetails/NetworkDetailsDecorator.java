package com.amazon.alexa.comms.mediaInsights.service.deviceDetails;

import java.util.Map;
import lombok.NonNull;

/* JADX INFO: loaded from: classes2.dex */
public class NetworkDetailsDecorator implements DeviceDetails {

    @NonNull
    private DeviceDetails deviceDetails;

    @NonNull
    private NetworkDetailsFactory networkDetailsfactory;

    public NetworkDetailsDecorator(@NonNull DeviceDetails deviceDetails, @NonNull NetworkDetailsFactory networkDetailsFactory) {
        if (deviceDetails == null) {
            throw new IllegalArgumentException("deviceDetails is null");
        }
        if (networkDetailsFactory == null) {
            throw new IllegalArgumentException("networkDetailsfactory is null");
        }
        this.deviceDetails = deviceDetails;
        this.networkDetailsfactory = networkDetailsFactory;
    }

    @Override // com.amazon.alexa.comms.mediaInsights.service.deviceDetails.DeviceDetails
    public Map<String, String> asMap() {
        Map<String, String> mapAsMap = this.deviceDetails.asMap();
        mapAsMap.putAll(this.networkDetailsfactory.create());
        return mapAsMap;
    }
}
