package com.amazon.musicplayqueueservice.model.client.external.nonvoiceenabled;

import com.amazon.hermes.ApiInfo;
import com.amazon.hermes.ServiceInfo;

/* JADX INFO: loaded from: classes5.dex */
public class CreateQueueApiInfo implements ApiInfo {
    @Override // com.amazon.hermes.ApiInfo
    public String getApiName() {
        return "createQueue";
    }

    @Override // com.amazon.hermes.ApiInfo
    public String getApiVersion() {
        return "";
    }

    @Override // com.amazon.hermes.ApiInfo
    public ServiceInfo getServiceInfo() {
        return new MusicPlayQueueServiceExternalNonVoiceEnabledClientServiceInfo();
    }
}
