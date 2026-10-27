package com.amazon.musicidentityservice.external;

import com.amazon.hermes.ApiInfo;
import com.amazon.hermes.ServiceInfo;

/* JADX INFO: loaded from: classes3.dex */
public class GetMusicRequestIdentityContextTokenApiInfo implements ApiInfo {
    @Override // com.amazon.hermes.ApiInfo
    public String getApiName() {
        return "GetMusicRequestIdentityContextToken";
    }

    @Override // com.amazon.hermes.ApiInfo
    public String getApiVersion() {
        return "";
    }

    @Override // com.amazon.hermes.ApiInfo
    public ServiceInfo getServiceInfo() {
        return new MusicIdentityServiceExternalServiceInfo();
    }
}
