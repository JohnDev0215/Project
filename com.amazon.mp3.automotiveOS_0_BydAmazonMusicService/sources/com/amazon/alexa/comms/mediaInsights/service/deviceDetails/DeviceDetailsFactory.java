package com.amazon.alexa.comms.mediaInsights.service.deviceDetails;

import lombok.NonNull;

/* JADX INFO: loaded from: classes2.dex */
public class DeviceDetailsFactory {

    @NonNull
    private NetworkDetailsFactory networkDetailsFactory;

    public DeviceDetailsFactory(@NonNull NetworkDetailsFactory networkDetailsFactory) {
        if (networkDetailsFactory == null) {
            throw new IllegalArgumentException("networkDetailsFactory is null");
        }
        this.networkDetailsFactory = networkDetailsFactory;
    }

    public DeviceDetails create() {
        return new NetworkDetailsDecorator(new StaticDeviceDetailsDecorator(new DeviceDetailsImpl()), this.networkDetailsFactory);
    }
}
