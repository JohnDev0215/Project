package com.amazon.alexa.comms.mediaInsights.service.deviceDetails;

import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes2.dex */
public class DeviceDetailsImpl implements DeviceDetails {
    private Map<String, String> deviceDetailsMap = new HashMap();

    @Override // com.amazon.alexa.comms.mediaInsights.service.deviceDetails.DeviceDetails
    public Map<String, String> asMap() {
        return this.deviceDetailsMap;
    }
}
