package com.amazon.music.subscription.external;

import com.amazon.hermes.ServiceInfo;

/* JADX INFO: loaded from: classes4.dex */
public class MusicSubscriptionServiceExternalServiceInfo implements ServiceInfo {
    @Override // com.amazon.hermes.ServiceInfo
    public String getServiceName() {
        return "MusicSubscriptionServiceExternal";
    }

    @Override // com.amazon.hermes.ServiceInfo
    public String getServicePackage() {
        return "com.amazon.music.subscription";
    }
}
