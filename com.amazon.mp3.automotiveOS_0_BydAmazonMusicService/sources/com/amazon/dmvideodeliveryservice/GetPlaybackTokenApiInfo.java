package com.amazon.dmvideodeliveryservice;

import com.amazon.hermes.ApiInfo;
import com.amazon.hermes.ServiceInfo;

/* JADX INFO: loaded from: classes2.dex */
public class GetPlaybackTokenApiInfo implements ApiInfo {
    @Override // com.amazon.hermes.ApiInfo
    public String getApiName() {
        return "getPlaybackToken";
    }

    @Override // com.amazon.hermes.ApiInfo
    public String getApiVersion() {
        return "";
    }

    @Override // com.amazon.hermes.ApiInfo
    public ServiceInfo getServiceInfo() {
        return new DigitalMusicVideoDeliveryServiceServiceInfo();
    }
}
